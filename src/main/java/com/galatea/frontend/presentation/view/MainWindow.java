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

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.galatea.frontend.presentation.controller.ModelPanelController;
import com.galatea.frontend.presentation.model.ProjectData;
import com.galatea.frontend.presentation.controller.MenuController;
import com.galatea.frontend.presentation.controller.SetupController;
import com.galatea.frontend.presentation.controller.StatusBarController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.prefs.Preferences;

/**
 * @author Oscar Arenas
 */
public class MainWindow extends JFrame {
    private static final String LAST_USED_FOLDER = "lastUsedFolder";

    private final ProjectData projectData;
    private CustomMenuBar customMenuBar;
    private SetupPanel setupPanel;
    private JTabbedPane tabbedPane;
    private StatusBarPanel statusBar;

    private ModelContainerPanel modelContainerPanel;

    public MainWindow(ProjectData projectData) {
        this.projectData = projectData;
        initComponents();
    }

    private void initComponents() {
        setTitle("AngularCleanSetup");

        customMenuBar = new CustomMenuBar();
        setJMenuBar(customMenuBar);

        setupPanel = new SetupPanel();
        statusBar = new StatusBarPanel();

        modelContainerPanel = new ModelContainerPanel();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Models", modelContainerPanel);

        JPanel containerPanel = new JPanel();

        tabbedPane.addTab("Presentation", containerPanel);

        tabbedPane.addChangeListener(e -> {
            final int selectedIndex = tabbedPane.getSelectedIndex();
        });

        add(setupPanel, BorderLayout.PAGE_START);
        add(tabbedPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.PAGE_END);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) (0.95 * screenSize.width);
        int screenHeight = (int) (0.9 * screenSize.height);

        setMinimumSize(new Dimension(screenWidth, screenHeight));

        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void about() {
        String message = "Version: 1.0\n";
        message += "\nUse only kebab-case";
        message += "\n2025\n";

        JOptionPane.showMessageDialog(this, message, "About",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void closeApp() {
        int ans = JOptionPane.showOptionDialog(this,
                "Are you sure you want to close the application?",
                "Close application", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Yes", "No"}, "default");

        if (ans == JOptionPane.OK_OPTION) {
            dispose();
        }
    }

    public void clear() {

    }

    public void setFlatDarkLaf() {
        setLookAndFeel(new FlatDarkLaf());
    }

    public void setFlatLightLaf() {
        setLookAndFeel(new FlatLightLaf());
    }

    private void setLookAndFeel(LookAndFeel lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ignored) {
        }
    }

    public void registerMenuController(MenuController menuController) {
        for (Component component : customMenuBar.getComponents()) {
            if (component instanceof JMenu menu) {
                for (Component item : menu.getMenuComponents()) {
                    if (item instanceof AbstractButton button) {
                        button.addActionListener(menuController);
                    }
                }
            }
        }
    }

    public void registerStatusBarController(StatusBarController statusBarController) {
        for (Component component : statusBar.getComponents()) {
            if (component instanceof JPanel panel) {
                for (Component item : panel.getComponents()) {
                    if (item instanceof AbstractButton button) {
                        button.addActionListener(statusBarController);
                    }
                }
            }
        }
    }

    public void registerSetupController(SetupController setupController) {
        for (Component component : setupPanel.getComponents()) {
            if (component instanceof AbstractButton button) {
                button.addActionListener(setupController);
            }
        }
    }

    public void registerModelContainerController(ModelPanelController modelPanelController) {
        for (Component component : modelContainerPanel.getComponents()) {
            if (component instanceof JPanel panel) {
                for (Component item : panel.getComponents()) {
                    if (item instanceof AbstractButton button) {
                        button.addActionListener(modelPanelController);
                    }
                }
            }
        }
    }

    public void addModelPanel() {
        modelContainerPanel.addModelPanel();
    }

    public void deleteModelPanel() {

    }

    public void deleteOperationPanel() {

    }

    public void build() {
        projectData.setName(setupPanel.getProjectName());
        projectData.setPath(setupPanel.getProjectPath());
        projectData.setType(setupPanel.getAngularProjectType());
        projectData.setAngularVersion(setupPanel.getAngularVersion());
        projectData.setBdsVersion(setupPanel.getBdsVersion());
        String featurePath = setupPanel.getFeaturePath();

        if (featurePath.isEmpty()) {
            featurePath = setupPanel.getProjectPath();
            setupPanel.setFeaturePath(featurePath);
        }
        projectData.setFeaturePath(featurePath);
    }

    public void getProjectPath() {
        Preferences preferences = Preferences.userRoot().node(MainWindow.class.getName());
        String lastUsedFolder = preferences.get(LAST_USED_FOLDER, new File(".").getAbsolutePath());

        String directoryPath = getFolderPath(lastUsedFolder);

        if (directoryPath != null && !directoryPath.trim().isEmpty()) {
            preferences.put(LAST_USED_FOLDER, directoryPath);
            statusBar.setProjectPathLabel(directoryPath);
            setupPanel.setProjectPath(directoryPath);
        }
    }

    public void getFeaturePath() {
        String initialPath = !setupPanel.getFeaturePath().isEmpty() ? setupPanel.getFeaturePath() : setupPanel.getProjectPath();
        String path = getFolderPath(initialPath);

        if (path != null && !path.trim().isEmpty()) {
            setupPanel.setFeaturePath(path);
        }
    }

    private String getFolderPath(String path) {
        JFileChooser fileChooser = new JFileChooser(path);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String directoryPath = "";
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            directoryPath = selectedDirectory.getAbsolutePath();
            setupPanel.setProjectNameTextField(selectedDirectory.getName());
        }

        return directoryPath;
    }

    public void showErrorMessage(String message) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
