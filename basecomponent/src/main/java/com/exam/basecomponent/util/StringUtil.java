package com.exam.basecomponent.util;

import android.content.Context;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

/**
 * @author LowAndroider
 * @date 2018/9/26
 */
public class StringUtil {

    /**
     * 字符串去空格
     *
     * @param srcText 源字符串
     * @return 去空格后的字符串
     */
    public static String delSpace(String srcText) {
        return srcText.replaceAll("[ 　]", "");

    }

    /**
     * 字符串保留数字
     *
     * @param srcText 源字符串
     * @return 数字字符串
     */
    public static String getNumber(String srcText) {
        return srcText.replaceAll("[^0-9.]", "");
    }

    public static double toNum(String srcText, @NotNull Context context) {
        if (srcText.length() == 0) {
            Toast.makeText(context, "请填充数据", Toast.LENGTH_SHORT).show();
            return -1;
        }
        try {
            return Double.parseDouble(getNumber(srcText));
        } catch (NumberFormatException e) {
            Toast.makeText(context, "请填充正确的数据", Toast.LENGTH_SHORT).show();
            return -1;
        }
    }
}

