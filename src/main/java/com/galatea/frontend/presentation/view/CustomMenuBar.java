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

/**
 * @author Oscar Arenas
 */
public class CustomMenuBar extends JMenuBar {
    public final static String CLEAR = "CLEAR";
    public final static String EXIT = "EXIT";
    public final static String ABOUT = "ABOUT";
    public final static String DARK_THEME = "DARK_THEME";
    public final static String LIGHT_THEME = "LIGHT_THEME";

    public CustomMenuBar() {
        initComponents();
    }

    private void initComponents() {
        JMenu controlMenu = new JMenu("Control");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem itemMenuClearAll = new JMenuItem("Clear");
        itemMenuClearAll.setActionCommand(CLEAR);

        JMenuItem darkThemeItem = new JMenuItem("Dark Theme");
        darkThemeItem.setActionCommand(DARK_THEME);

        JMenuItem lightThemeItem = new JMenuItem("Light Theme");
        lightThemeItem.setActionCommand(LIGHT_THEME);

        JMenuItem itemMenuExit = new JMenuItem("Exit");
        itemMenuExit.setActionCommand(EXIT);

        controlMenu.add(itemMenuClearAll);
        controlMenu.add(new JSeparator());
        controlMenu.add(darkThemeItem);
        controlMenu.add(lightThemeItem);
        controlMenu.add(new JSeparator());
        controlMenu.add(itemMenuExit);

        JMenuItem itemMenuAbout = new JMenuItem("About");
        itemMenuAbout.setActionCommand(ABOUT);

        helpMenu.add(itemMenuAbout);

        add(controlMenu);
        add(helpMenu);
    }
}
