package com.example.giggle.oschina2.util;

/**
 * Created by Giggle on 2015-12-12.
 */
// TODO: 2015-12-12 将WEB字体大小保存/提取
public class FontSizeUtils {

    public static String getFontSize(int index){
        String fontSize = "";
        switch (index) {
            case 0:
                fontSize = "javascript:showSuperBigSize()";
                break;
            case 1:
                fontSize = "javascript:showBigSize()";
                break;
            case 2:
                fontSize = "javascript:showMidSize()";
                break;
            default:
                fontSize = "javascript:showSmallSize()";
                break;
        }
        return fontSize;
    }
}
