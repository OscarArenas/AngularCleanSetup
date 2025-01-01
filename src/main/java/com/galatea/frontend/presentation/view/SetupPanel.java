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

import com.galatea.frontend.utils.Constants;
import com.galatea.frontend.utils.CustomDocumentFilter;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

/**
 * @author Oscar Arenas
 */
public class SetupPanel extends JPanel {
    public final static String PROJECT_PATH = "PROJECT_PATH";
    public final static String FEATURE_PATH = "FEATURE_PATH";

    private JLabel projectNameLabel;
    private JTextField projectNameTextField;
    //
    private JPanel angularVersionPanel;
    private JLabel angularVersionLabel;
    private ButtonGroup buttonGroup;
    private JRadioButton radioButtonAngularVersion16;
    private JRadioButton radioButtonAngularVersion17;
    //
    private JPanel angularProjectTypePanel;
    private JLabel angularProjectTypeLabel;
    private ButtonGroup buttonGroupAngularProjectType;
    private JRadioButton radioButtonWidgetProject;
    private JRadioButton radioButtonMFWidgetProject;
    private JRadioButton radioButtonMFProject;
    //
    private JPanel bdsVersionPanel;
    private JLabel bdsVersionLabel;
    private ButtonGroup buttonGroupBdsVersion;
    private JRadioButton radioButtonBDSVersion13;
    private JRadioButton radioButtonBDSVersion14;
    //
    private JButton projectFolderPathChooserButton;
    private String projectPath = "";
    private JButton featureFolderPathChooserButton;
    private String featurePath = "";

    private JLabel projectPathLabel;
    private JLabel featurePathLabel;

    private String angularProjectType = Constants.ANGULAR_WIDGET;
    private String angularVersion = Constants.ANGULAR_VERSION_16;
    private String bdsVersion = Constants.BDS_VERSION_13;
    private boolean showFeaturePath = false;

    public SetupPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new MigLayout());
        setBorder(BorderFactory.createTitledBorder("Project setup"));

        projectNameLabel = new JLabel("Project name:");
        projectNameTextField = new JTextField(Constants.MAX_LENGTH);

        ((AbstractDocument) projectNameTextField.getDocument()).setDocumentFilter(new CustomDocumentFilter());

        projectPathLabel = new JLabel("Project path:");

        projectFolderPathChooserButton = new JButton("Search path");
        projectFolderPathChooserButton.setActionCommand(PROJECT_PATH);
        projectFolderPathChooserButton.setFocusable(false);

        featurePathLabel = new JLabel("Feature path:");
        featurePathLabel.setVisible(false);

        featureFolderPathChooserButton = new JButton("Select path");
        featureFolderPathChooserButton.setActionCommand(FEATURE_PATH);
        featureFolderPathChooserButton.setFocusable(false);
        featureFolderPathChooserButton.setVisible(false);

        angularVersionPanel = new JPanel();
        angularVersionLabel = new JLabel("Angular version:");

        radioButtonAngularVersion16 = new JRadioButton(Constants.ANGULAR_VERSION_16, true);
        radioButtonAngularVersion16.setActionCommand(Constants.ANGULAR_VERSION_16);
        radioButtonAngularVersion16.setFocusable(false);
        radioButtonAngularVersion16.addActionListener(e -> angularVersion = Constants.ANGULAR_VERSION_16);

        radioButtonAngularVersion17 = new JRadioButton(Constants.ANGULAR_VERSION_17);
        radioButtonAngularVersion17.setActionCommand(Constants.ANGULAR_VERSION_17);
        radioButtonAngularVersion17.setFocusable(false);
        radioButtonAngularVersion17.addActionListener(e -> angularVersion = Constants.ANGULAR_VERSION_17);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonAngularVersion16);
        buttonGroup.add(radioButtonAngularVersion17);

        angularVersionPanel.add(radioButtonAngularVersion16);
        angularVersionPanel.add(radioButtonAngularVersion17);

        angularProjectTypePanel = new JPanel();
        angularProjectTypeLabel = new JLabel("Angular project type:");
        radioButtonWidgetProject = new JRadioButton("Widget", true);
        radioButtonWidgetProject.setActionCommand(Constants.ANGULAR_WIDGET);
        radioButtonWidgetProject.addActionListener(e -> {
            angularProjectType = Constants.ANGULAR_WIDGET;
            if (showFeaturePath) {
                repaintForm(false);
            }
        });

        radioButtonMFWidgetProject = new JRadioButton("MF with widget");
        radioButtonMFWidgetProject.setActionCommand(Constants.ANGULAR_MF_WIDGET);
        radioButtonMFWidgetProject.addActionListener(e -> {
            angularProjectType = Constants.ANGULAR_MF_WIDGET;
            if (showFeaturePath) {
                repaintForm(false);
            }
        });

        radioButtonMFProject = new JRadioButton("MF without widget");
        radioButtonMFProject.setActionCommand(Constants.ANGULAR_MF);
        radioButtonMFProject.addActionListener(e -> {
            angularProjectType = Constants.ANGULAR_MF;
            if (!showFeaturePath) {
                repaintForm(true);
            }
        });

        radioButtonWidgetProject.setFocusable(false);
        radioButtonMFWidgetProject.setFocusable(false);
        radioButtonMFProject.setFocusable(false);

        buttonGroupAngularProjectType = new ButtonGroup();
        buttonGroupAngularProjectType.add(radioButtonWidgetProject);
        buttonGroupAngularProjectType.add(radioButtonMFWidgetProject);
        buttonGroupAngularProjectType.add(radioButtonMFProject);

        angularProjectTypePanel.add(radioButtonWidgetProject);
        angularProjectTypePanel.add(radioButtonMFWidgetProject);
        angularProjectTypePanel.add(radioButtonMFProject);

        bdsVersionPanel = new JPanel();
        bdsVersionLabel = new JLabel("BDS version:");

        radioButtonBDSVersion13 = new JRadioButton(Constants.BDS_VERSION_13, true);
        radioButtonBDSVersion13.setActionCommand(Constants.BDS_VERSION_13);
        radioButtonBDSVersion13.setFocusable(false);
        radioButtonBDSVersion13.addActionListener(e -> bdsVersion = Constants.BDS_VERSION_13);

        radioButtonBDSVersion14 = new JRadioButton(Constants.BDS_VERSION_14);
        radioButtonBDSVersion14.setActionCommand(Constants.BDS_VERSION_14);
        radioButtonBDSVersion14.setFocusable(false);
        radioButtonBDSVersion14.addActionListener(e -> bdsVersion = Constants.BDS_VERSION_14);

        buttonGroupBdsVersion = new ButtonGroup();
        buttonGroupBdsVersion.add(radioButtonBDSVersion13);
        buttonGroupBdsVersion.add(radioButtonBDSVersion14);

        bdsVersionPanel.add(radioButtonBDSVersion13);
        bdsVersionPanel.add(radioButtonBDSVersion14);

        drawForm();
    }

    public void repaintForm(boolean repaint) {
        removeAll();
        showFeaturePath = repaint;
        featurePathLabel.setVisible(repaint);
        featureFolderPathChooserButton.setVisible(repaint);
        drawForm();
        validate();
        repaint();
    }

    public void drawForm() {
        add(projectNameLabel);
        add(projectNameTextField);

        add(projectPathLabel, "gap unrelated");
        add(projectFolderPathChooserButton, "wrap");

        add(angularProjectTypeLabel);
        add(angularProjectTypePanel, "span, grow");

        add(angularVersionLabel);
        add(angularVersionPanel);
        add(bdsVersionLabel);
        add(bdsVersionPanel, "wrap");

        add(featurePathLabel);
        add(featureFolderPathChooserButton);
    }


    public String getProjectName() {
        return projectNameTextField.getText();
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
        featurePath = "";
    }

    public String getAngularVersion() {
        return angularVersion;
    }

    public String getBdsVersion() {
        return bdsVersion;
    }

    public String getAngularProjectType() {
        return angularProjectType;
    }

    public String getFeaturePath() {
        return featurePath;
    }

    public void setFeaturePath(String featurePath) {
        this.featurePath = featurePath;
    }

    public void setProjectNameTextField(String projectName) {
        projectNameTextField.setText(projectName);
        projectNameTextField.setToolTipText(projectName);
    }
}
