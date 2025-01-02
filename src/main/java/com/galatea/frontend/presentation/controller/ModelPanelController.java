package com.galatea.frontend.presentation.controller;

import com.galatea.frontend.presentation.view.MainWindow;
import com.galatea.frontend.presentation.view.ModelContainerPanel;
import com.galatea.frontend.presentation.view.ModelPanel;
import com.galatea.frontend.presentation.view.OperationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModelPanelController implements ActionListener {
    private final MainWindow view;

    public ModelPanelController(MainWindow view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.compareTo(ModelContainerPanel.ADD_MODEL) == 0) {
            view.addModelPanel();
        } else if (command.compareTo(ModelPanel.DELETE_MODEL) == 0) {
            view.deleteModelPanel();
        } else if (command.compareTo(OperationPanel.DELETE_OPERATION) == 0) {
            view.deleteOperationPanel();
        }
    }
}
