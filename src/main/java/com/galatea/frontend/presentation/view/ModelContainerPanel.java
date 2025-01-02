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

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Oscar Arenas
 */
public class ModelContainerPanel extends JPanel {
    public final static String ADD_MODEL = "ADD_MODEL";
    public static final int MINIMUM_QUANTITY_OF_MODELS = 2;

    public static final int COLUMNS = 2;
    public static final int gap = 5;

    private ArrayList<ModelPanel> modelPanelList;
    private JPanel addButtonPanel;
    private JButton addModelButton;
    private JPanel modelsPanel;
    private JScrollPane scrollPane;

    private int totalRows = 1;

    public ModelContainerPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        modelPanelList = new ArrayList<>();

        addButtonPanel = new JPanel();
        addModelButton = new JButton("Add model");
        addModelButton.setActionCommand(ADD_MODEL);
        addModelButton.setFocusable(false);

        addButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        addButtonPanel.add(addModelButton);

        modelsPanel = new JPanel();
        modelsPanel.setLayout(new GridLayout(1, COLUMNS, gap, gap));

        scrollPane = new JScrollPane(modelsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(addButtonPanel, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);

        addModelPanel();
        addModelPanel();
    }

    public boolean hasValidValues() {
        for (ModelPanel model : modelPanelList) {
            if (!model.isValid()) {
                return false;
            }
        }
        return true;
    }

    public void addModelPanel() {
        String name = "Model: ";
        ModelPanel newModel = new ModelPanel(name, this);
        modelPanelList.add(newModel);

        final int rows = (int) Math.ceil(modelPanelList.size() / 2.0);

        if (rows != totalRows) {
            totalRows = rows;
            resetPanels();
            SwingUtilities.invokeLater(() -> {
                JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            });
        } else {
            modelsPanel.add(newModel);
        }

        modelsPanel.revalidate();
        modelsPanel.repaint();
    }

    public void removeModelPanel(ModelPanel modelPanel) {
        if (modelPanelList.size() > MINIMUM_QUANTITY_OF_MODELS) {
            modelsPanel.remove(modelPanel);
            modelPanelList.remove(modelPanel);
            modelsPanel.revalidate();
            modelsPanel.repaint();

            final int rows = (int) Math.ceil(modelPanelList.size() / 2.0);

            if (rows != totalRows) {
                totalRows = rows;
                resetPanels();
            }
        }
    }

    private void resetPanels() {
        modelsPanel.removeAll();

        modelsPanel.setLayout(new GridLayout(totalRows, COLUMNS, gap, gap));

        for (ModelPanel model : modelPanelList) {
            modelsPanel.add(model);
        }
    }

    public ArrayList<String> getUsecases() {
        ArrayList<String> usecaseList = new ArrayList<>();

        for (ModelPanel model : modelPanelList) {
            if (model.hasValidValues()) {
                usecaseList.addAll(model.getUsecase());
            }
        }

        return usecaseList;
    }

    public int sizeModelList() {
        return modelPanelList.size();
    }
}
