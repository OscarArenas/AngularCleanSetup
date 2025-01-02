/*
 * Copyright (C) 2025 Oscar Arenas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.galatea.frontend.presentation.view;

import com.galatea.frontend.presentation.model.Operation;
import com.galatea.frontend.utils.Constants;
import com.galatea.frontend.utils.CustomDocumentFilter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.Objects;

public class OperationPanel extends JPanel {
    public final static String DELETE_OPERATION = "DELETE_OPERATION";

    private final ModelPanel modelPanel;
    private JLabel operationNameLabel;
    private JTextField operationNameTextField;
    private JLabel httpLabel;
    private JComboBox<String> comboBoxHTTP;
    private JButton deleteOperationButton;

    public OperationPanel(ModelPanel modelPanel) {
        this.modelPanel = modelPanel;
        initComponents();
    }

    private void initComponents() {
        setBorder(BorderFactory.createTitledBorder(""));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        operationNameLabel = new JLabel("Operation name:");
        operationNameTextField = new JTextField(Constants.MAX_LENGTH);

        ((AbstractDocument) operationNameTextField.getDocument()).setDocumentFilter(new CustomDocumentFilter());

        operationNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabel();
            }

            private void updateLabel() {
                modelPanel.verifyOperationName(operationNameTextField.getText());
            }
        });

        httpLabel = new JLabel("HTTP method:");
        comboBoxHTTP = new JComboBox<>(Constants.HTTP_METHODS);
        comboBoxHTTP.setFocusable(false);

        deleteOperationButton = new JButton();
        deleteOperationButton.setText("Delete");
        deleteOperationButton.setActionCommand(DELETE_OPERATION);
        deleteOperationButton.setToolTipText("Delete operation");
        deleteOperationButton.setFocusable(false);

        deleteOperationButton.addActionListener(e -> modelPanel.removeOperationPanel(OperationPanel.this));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(operationNameLabel, gbc);
        gbc.gridx = 1;
        add(operationNameTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(httpLabel, gbc);
        gbc.gridx = 3;
        add(comboBoxHTTP, gbc);
        gbc.gridx = 4;
        add(deleteOperationButton, gbc);
    }

    public boolean hasValidValues() {
        return Constants.isValidInput(operationNameTextField.getText());
    }

    public Operation getOperation() {
        final String name = operationNameTextField.getText().replaceAll("_+$", "");
        final String http = Objects.requireNonNull((String) comboBoxHTTP.getSelectedItem());

        return new Operation(name, http);
    }
}
