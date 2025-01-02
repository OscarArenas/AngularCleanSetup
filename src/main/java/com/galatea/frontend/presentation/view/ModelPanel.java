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
import com.galatea.frontend.utils.StringFormatter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModelPanel extends JPanel {
    public final static String DELETE_MODEL = "DELETE_MODEL";

    private final String modelName;
    private JLabel modelLabel;
    private JTextField modelTextField;
    private JLabel crudOperationLabel;
    private JPanel operationsContainerPanel;
    private JButton deleteModelButton;
    private JButton addOperationButton;
    private ArrayList<OperationPanel> operationPanelList;

    private boolean enableAddOperationButton = false;
    private ModelContainerPanel modelCanvas;

    ModelPanel(String name, ModelContainerPanel modelCanvas) {
        this.modelCanvas = modelCanvas;
        modelName = name;
        initComponents();
    }

    private void initComponents() {
        setBorder(BorderFactory.createTitledBorder(modelName));
        setLayout(new GridBagLayout());

        operationsContainerPanel = new JPanel();
        operationPanelList = new ArrayList<>();
        operationsContainerPanel.setLayout(new BoxLayout(operationsContainerPanel, BoxLayout.Y_AXIS));
        addOperationButton = new JButton("Add operation");
        addOperationButton.setFocusable(false);

        modelLabel = new JLabel("Model name:");
        modelTextField = new JTextField(Constants.MAX_LENGTH);
        modelTextField.requestFocusInWindow();

        addOperation();

        ((AbstractDocument) modelTextField.getDocument()).setDocumentFilter(new CustomDocumentFilter());

        modelTextField.getDocument().addDocumentListener(new DocumentListener() {
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
                setBorder(BorderFactory.createTitledBorder(modelName + " " + StringFormatter.convertKebabToCamel(modelTextField.getText())));
                enableButton();
            }
        });

        crudOperationLabel = new JLabel("CRUD operations:");

        addOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperation();
                ModelPanel.this.paintControls();

                ModelPanel.this.revalidate();
                ModelPanel.this.repaint();
            }
        });

        deleteModelButton = new JButton();
        deleteModelButton.setFocusable(false);
        deleteModelButton.setActionCommand(DELETE_MODEL);
        deleteModelButton.setToolTipText("Delete model");
        deleteModelButton.setText("Delete");

        deleteModelButton.addActionListener(e -> modelCanvas.removeModelPanel(ModelPanel.this));

        paintControls();
    }

    public void verifyOperationName(String text) {
        enableAddOperationButton = hasValidValues();
        enableButton();
    }

    private void enableButton() {
        enableAddOperationButton = hasValidValues();
        addOperationButton.setEnabled(enableAddOperationButton);
    }

    private void addOperation() {
        OperationPanel operationPanel = new OperationPanel(this);
        enableAddOperationButton = false;
        operationsContainerPanel.add(operationPanel);
        operationPanelList.add(operationPanel);
        enableButton();
    }

    public void removeOperationPanel(OperationPanel operationPanel) {
        if (operationPanelList.size() > 1) {
            remove(operationPanel);
            operationPanelList.remove(operationPanel);
            operationsContainerPanel.remove(operationPanel);
            revalidate();
            repaint();
        }

        enableAddOperationButton = hasValidValues();
        enableButton();
    }

    private void paintControls() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(modelLabel, gbc);
        gbc.gridx = 1;
        add(modelTextField, gbc);
        gbc.gridx = 4;
        add(deleteModelButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(crudOperationLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(addOperationButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        add(operationsContainerPanel, gbc);
    }

    public boolean hasValidValues() {
        boolean isValid = Constants.isValidInput(modelTextField.getText());

        if (isValid) {
            for (OperationPanel op : operationPanelList) {
                if (!op.hasValidValues()) {
                    return false;
                }
            }
        }

        return isValid;
    }

    public ArrayList<String> getUsecase() {
        ArrayList<String> usecaseList = new ArrayList<>();

        final String name = modelTextField.getText();

        if (!name.isEmpty()) {
            for (OperationPanel op : operationPanelList) {
                if (op.hasValidValues()) {
                    final String useCaseName = op.getOperation().getName() + "-" + name;
                    usecaseList.add(useCaseName);
                }
            }
        }

        return usecaseList;
    }
}
