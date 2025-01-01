package com.galatea.frontend;

import com.formdev.flatlaf.FlatDarkLaf;
import com.galatea.frontend.presentation.controller.StatusBarController;
import com.galatea.frontend.presentation.model.ProjectData;
import com.galatea.frontend.presentation.view.MainWindow;
import com.galatea.frontend.presentation.controller.MenuController;
import com.galatea.frontend.presentation.controller.SetupController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ignored) {
        }

        ProjectData projectData = new ProjectData();
        MainWindow mainWindow = new MainWindow(projectData);

        MenuController menuController
                = new MenuController(mainWindow);
        StatusBarController statusBarController
                = new StatusBarController(mainWindow);
        SetupController setupController
                = new SetupController(mainWindow);

        // Register controllers
        mainWindow.registerMenuController(menuController);
        mainWindow.registerStatusBarController(statusBarController);
        mainWindow.registerSetupController(setupController);

        mainWindow.setVisible(true);
    }
}
