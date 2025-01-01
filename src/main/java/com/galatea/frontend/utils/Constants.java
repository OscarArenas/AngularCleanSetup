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
package com.galatea.frontend.utils;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;

public class Constants {
    public static final String ANGULAR_VERSION_16 = "16";
    public static final String ANGULAR_VERSION_17 = "17";
    public static final String BDS_VERSION_13 = "13";
    public static final String BDS_VERSION_14 = "14";
    public static final String ANGULAR_WIDGET = "widget";
    public static final String ANGULAR_MF_WIDGET = "mf-widget";
    public static final String ANGULAR_MF = "mf";

    public static final int MAX_LENGTH = 15;
    public static final int MIN_LENGTH_INPUT = 3;
    public static final String[] HTTP_METHODS = {"GET", "POST", "PUT", "DELETE"};
    public static final String[] COMPONENTS_TYPES = {"", "Header", "Header + Button", "Filter", "Filter + Button", "Table with pagination", "Table without pagination", "Form", "Modal"};
    public static final ImageIcon DELETE_ICON = new ImageIcon("C:/Users/oscar.h.arenas/Documents/SpringBootProjects/AngularCleanSetup/src/main/resources/icons/icons/trash.png");
    public static final String REGEX = "^[a-z]+(-[a-z]+)*$";

    static {
        Arrays.sort(Constants.COMPONENTS_TYPES, Comparator.naturalOrder());
    }

    public static boolean isValidInput(String text) {
        return text != null && text.trim().length() >= MIN_LENGTH_INPUT && text.matches(REGEX);
    }
}
