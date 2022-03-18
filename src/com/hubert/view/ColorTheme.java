package com.hubert.view;

public enum ColorTheme {
    LIGHT,
    DEFAULT,
    DARK;

    public static String getCssPath(ColorTheme color) {
        switch(color) {
            case LIGHT:
                return "css/themeLight.css";
            case DEFAULT:
                return  "css/themeDefault.css";
            case DARK:
                return "css/themeDark.css";
            default:
                //TODO
                return null;
        }
    }
}
