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

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatter {
    public static @NotNull String convertKebabToConstant(@NotNull String kebabCase) {
        kebabCase = removeTrailingHyphen(kebabCase);
        if (kebabCase.trim().isEmpty()) {
            return "";
        }
        return kebabCase.trim().toUpperCase().replace("-", "_");
    }

    public static @NotNull String convertKebabToTitle(@NotNull String kebabCase) {
        kebabCase = removeTrailingHyphen(kebabCase);
        if (kebabCase.trim().isEmpty()) {
            return "";
        }
        kebabCase = kebabCase.trim().replace("-", " ");
        return kebabCase.substring(0, 1).toUpperCase() + kebabCase.substring(1);
    }

    public static @NotNull String convertKebabToCamel(@NotNull String kebabCase) {
        kebabCase = removeTrailingHyphen(kebabCase);
        if (kebabCase.trim().isEmpty()) {
            return "";
        }

        kebabCase = kebabCase.trim();
        kebabCase = Character.toUpperCase(kebabCase.charAt(0)) + kebabCase.substring(1);

        return convertKebabToCamelId(kebabCase);
    }

    public static @NotNull String convertKebabToCamelId(@NotNull String kebabCase) {
        kebabCase = removeTrailingHyphen(kebabCase);
        if (kebabCase.trim().isEmpty()) {
            return "";
        }

        Pattern pattern = Pattern.compile("-(\\w)");
        Matcher matcher = pattern.matcher(kebabCase.trim());
        StringBuilder camelCase = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(camelCase, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(camelCase);

        return camelCase.toString();
    }

    public static String removeTrailingHyphen(@NotNull String text) {
        if (text.endsWith("-")) {
            return text.substring(0, text.length() - 1);
        }
        return text;
    }
}
