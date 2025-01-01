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

public class StatusBarPanel extends JPanel {
    public final static String BUILD = "BUILD";

    private JLabel projectPathLabel;
    private JButton buildButton;

    public StatusBarPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(2,1,5,5));
        setBorder(BorderFactory.createTitledBorder(""));

        projectPathLabel = new JLabel();
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(projectPathLabel);

        buildButton = new JButton("Build");
        buildButton.setActionCommand(BUILD);
        buildButton.setFocusable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buildButton);

        add(buttonPanel);
        add(labelPanel);
    }

    public void setProjectPathLabel(String text) {
        projectPathLabel.setText(text);
    }
}
