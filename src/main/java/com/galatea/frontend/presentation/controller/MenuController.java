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
package com.galatea.frontend.presentation.controller;

import com.galatea.frontend.presentation.view.CustomMenuBar;
import com.galatea.frontend.presentation.view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oscar Arenas
 */
public class MenuController implements ActionListener {

    private final MainWindow view;

    public MenuController(MainWindow view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case CustomMenuBar.CLEAR -> view.clear();
            case CustomMenuBar.ABOUT -> view.about();
            case CustomMenuBar.EXIT -> view.closeApp();
            case CustomMenuBar.DARK_THEME -> view.setFlatDarkLaf();
            case CustomMenuBar.LIGHT_THEME -> view.setFlatLightLaf();
        }
    }
}
