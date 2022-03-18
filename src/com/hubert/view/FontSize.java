package com.hubert.view;

public enum FontSize {
    SMALL,
    MEDIUM,
    BIG;

    public static String getCssPath(FontSize font) {
        switch(font) {
            case SMALL:
                return "css/fontSmall.css";
            case MEDIUM:
                return  "css/fontMedium.css";
            case BIG:
                return "css/fontBig.css";
            default:
                //TODO
                return null;
        }
    }
}
