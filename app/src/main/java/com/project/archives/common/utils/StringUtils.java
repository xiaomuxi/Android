package com.project.archives.common.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;

public class StringUtils {
    public final static String UTF_8 = "utf-8";

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value == null || "".equalsIgnoreCase(value.trim()) || "null".equalsIgnoreCase(value.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判空处理工具
     */
    public static boolean notNull(Object obj) {
        if ("null".equals(obj) || "".equals(obj) || null == obj || "" == obj) {
            return false;
        } else {
            return true;
        }
    }
        /**
         * 校验字符串, 如果如果为空, 则赋值"", 不是则返回字符串
         *
         * @param str
         * @return
         */
    public static String checkString(String str) {
        return isEmpty(str) ? "" : str;
    }

    /**
     * 只要有一个为空, 就返回true
     *
     * @param args
     * @return
     */
    public static boolean isEmpty(String... args) {
        for (String str : args) {
            if (StringUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true
     */
    public static boolean equals(String... args) {
        String last = null;
        for (String str : args) {
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equals(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 返回一个高亮spannable
     *
     * @param content 文本内容
     * @param color   高亮颜色
     * @param start   起始位置
     * @param end     结束位置
     * @return 高亮spannable
     */
    public static CharSequence getHighLightText(String content, int color, int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 获取链接样式的字符串，即字符串下面有下划线
     *
     * @param resId 文字资源
     * @return 返回链接样式的字符串
     */
    public static Spanned getHtmlStyleString(int resId) {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"\"><u><b>").append(UIUtils.getString(resId)).append(" </b></u></a>");
        return Html.fromHtml(sb.toString());
    }

    /**
     * 格式化文件大小，不保留末尾的0
     */
    public static String formatFileSize(long len) {
        return formatFileSize(len, false);
    }

    /**
     * 格式化文件大小，保留末尾的0，达到长度一致
     */
    private static String formatFileSize(long len, boolean keepZero) {
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
        if (len < 1024) {
            size = String.valueOf(len + "B");
        } else if (len < 10 * 1024) {
            // [0, 10KB)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            // [10KB, 100KB)，保留一位小数
            size = String.valueOf(len * 10 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            // [100KB, 1MB)，个位四舍五入
            size = String.valueOf(len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [1MB, 10MB)，保留两位小数
            if (keepZero) {
                size = String.valueOf(formatKeepTwoZero.format(len * 100 / 1024 / 1024 / (float) 100)) + "MB";
            } else {
                size = String.valueOf(len * 100 / 1024 / 1024 / (float) 100) + "MB";
            }
        } else if (len < 100 * 1024 * 1024) {
            // [10MB, 100MB)，保留一位小数
            if (keepZero) {
                size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024 / 1024 / (float) 10)) + "MB";
            } else {
                size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10) + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)，个位四舍五入
            size = String.valueOf(len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100) + "GB";
        }
        return size;
    }

    /**
     * 将流转换成字符串
     * @param file
     */
    public static String streamToString(File file) {
        String content = "";
        if (null == file) return content;
        try {
            int i = 0;
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((i = fis.read()) != -1) {
                bos.write(i);
            }
            content = bos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 将bitmap转换成字符串
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToString(Bitmap bitmap) {
        String content = "";
        if (null == bitmap || bitmap.isRecycled()) return content;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        content = Base64.encodeToString(bytes, Base64.DEFAULT);
        return content;
    }

    /**
     * 对字符串进行中文乱码处理
     * @param str
     * @return
     */
    public static String recode(String str) {
        String formart = "";

        try {
            boolean ISO = Charset.forName("ISO-8859-1").newEncoder()
                    .canEncode(str);
            if (ISO) {
                formart = new String(str.getBytes("ISO-8859-1"), "GB2312");
                Log.i("1234      ISO8859-1", formart);
            } else {
                formart = str;
                Log.i("1234      stringExtra", str);
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return formart;
    }

    /**
     * 设置控件文本部分字体颜色
     * @param text
     * @param color
     * @param startIndex
     * @param length
     * @return
     */
    public static SpannableStringBuilder setTextColor(String text, String color, int startIndex, int length){
        if(isEmpty(text)){
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(color)), startIndex, startIndex+length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //颜色
        return spannableStringBuilder;
    }

    /**
     * 设置控件文本部分字体颜色(多个颜色设置)
     * @param text
     * @param colorNums 有几种颜色
     * @param colors
     * @param startIndex
     * @param length
     * @return
     */
    public static SpannableStringBuilder setTextColor(String text, int colorNums, String[] colors, int[] startIndex, int[] length){
        if(isEmpty(text)){
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        for (int i=0;i<colorNums;i++){
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(colors[i])), startIndex[i], startIndex[i]+length[i], Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //颜色

        }
        return spannableStringBuilder;
    }

    /**
     * 设置控件Text部分字体大小
     * @param text
     * @param dp 字体大小
     * @param startIndex
     * @param length
     * @return
     */
    public static SpannableStringBuilder setTextSize(String text, int dp, int startIndex, int length){
        if(isEmpty(text)){
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(UIUtils.dip2px(dp)), startIndex, startIndex+length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号
        return spannableStringBuilder;
    }

    /**
     *
     * @param text
     * @param size 字体大小(dp)
     * @param sizeStartIndex 要设置的字在字符串中的索引位置
     * @param sizelength 长度
     * @param color
     * @param colorStartIndex
     * @param colorLength
     * @return
     */
    public static SpannableStringBuilder setTextSizeAndColor(String text, int size, int sizeStartIndex, int sizelength, String color, int colorStartIndex, int colorLength){
        if(isEmpty(text)){
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(UIUtils.dip2px(size)), sizeStartIndex, sizeStartIndex+sizelength, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(color)), colorStartIndex, colorStartIndex+colorLength, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //颜色
        return spannableStringBuilder;
    }

    /**
     * 过滤出来文本中所有的数字
     * @param number
     * @return
     */
    public static String filterNumber(String number){
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }

    /**
     * 截取过长文本以...结尾
     * @param text
     * @param end
     * @return
     */
    public static String subStringEndDot(String text, int end){
        text = checkString(text);
        if(text.length()>end){
            text = text.substring(0,end)+"...";
        }
        return text;
    }

    public static String getLevelByNumber(int number) {
        String level = "--";
        switch (number) {
            case 1:
                level = "正处";
                break;
            case 2:
                level = "副处";
                break;
            case 3:
                level = "正科";
                break;
            case 4:
                level = "副科";
                break;
            case 5:
                level = "科员";
                break;
            case 6:
                level = "其他";
                break;
        }
        return level;
    }

    public static String getLiaoJieResult(int takingResult) {
        String result = "--";
        switch (takingResult) {
            case 1:
                result = "失实";
                break;
            case 2:
                result = "适当处理";
                break;
            case 3:
                result = "转立案";
                break;
            case 4:
                result = "其他";
                break;
        }

        return result;
    }

    public static String getDangjiByNumber(int number) {

        String result = "--";
        switch (number) {
            case 1:
                result = "党内警告";
                break;
            case 2:
                result = "党内严重警告";
                break;
            case 3:
                result = "撤销党内职务";
                break;
            case 4:
                result = "留党察看";
                break;
            case 5:
                result = "开除党籍";
                break;
        }

        return result;
    }

    public static String getZhengjiByNumber(int number) {

        String result = "--";
        switch (number) {
            case 1:
                result = "行政警告";
                break;
            case 2:
                result = "记过";
                break;
            case 3:
                result = "记大过";
                break;
            case 4:
                result = "降级";
                break;
            case 5:
                result = "撤职";
                break;
            case 6:
                result = "开除";
                break;
            case 7:
                result = "免予处分";
                break;
        }

        return result;
    }

    /**
     * 办理机关
     * @param number
     * @return
     */
    public static String getOrganByNumber(int number) {

        String result = "--";
        switch (number) {
            case 1:
                result = "第一纪检监察室";
                break;
            case 2:
                result = "第二纪检监察室";
                break;
            case 3:
                result = "第三纪检监察室";
                break;
            case 4:
                result = "信访室";
                break;
            case 5:
                result = "党风政风监督室";
                break;
            case 6:
                result = "案件监察室";
                break;
            case 7:
                result = "其他";
                break;
        }

        return result;
    }

    /**
     * 处理情况
     * @param number
     * @return
     */
    public static String getTrueResultByNumber(int number) {

        String result = "--";
        switch (number) {
            case 1:
                result = "失实";
                break;
            case 2:
                result = "部分属实适当处理";
                break;
            case 3:
                result = "转初核";
                break;
            case 4:
                result = "转立案";
                break;
            case 5:
                result = "其他";
                break;
        }

        return result;
    }


    /**
     * 组织处理情况
     * @param number
     * @return
     */
    public static String getResultSituationByNumber(int number) {

        String result = "--";
        switch (number) {
            case 1:
                result = "通报批评";
                break;
            case 2:
                result = "解聘";
                break;
            case 3:
                result = "辞退";
                break;
            case 4:
                result = "调整岗位";
                break;
            case 5:
                result = "免职";
                break;
            case 6:
                result = "取消预备党员资格";
                break;
            case 7:
                result = "取消荣誉称号";
                break;
            case 8:
                result = "诫勉谈话";
                break;
            case 9:
                result = "停职";
                break;
            case 10:
                result = "公开道歉";
                break;
            case 11:
                result = "引咎辞职";
                break;
            case 12:
                result = "责令辞职";
                break;
            case 13:
                result = "其他组织处理";
                break;
            case 14:
                result = "不实澄清";
                break;
            case 15:
                result = "提醒谈话";
                break;
        }

        return result;
    }

    /**
     * 线索来源
     * @param number
     * @return
     */
    public static String getXiansuoByNumber(int number) {

        String result = "--";
        switch (number) {
            case 1:
                result = "信访举报";
                break;
            case 2:
                result = "上级交办";
                break;
            case 3:
                result = "公检法等机关移送";
                break;
            case 4:
                result = "监督检查中发现";
                break;
            case 5:
                result = "纪律审查中发现";
                break;
            case 6:
                result = "审计中发现";
                break;
            case 7:
                result = "巡视中发现";
                break;
            case 8:
                result = "其他";
                break;
        }

        return result;
    }
    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
