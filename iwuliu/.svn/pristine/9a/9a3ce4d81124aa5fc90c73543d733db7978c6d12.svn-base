/**
 * 版权所有：版权所有(C) 2015
 * 文件名称：TypeConversionUtil.java
 * 系统编号: Z0001002
 * 系统名称：物流管理平台
 * 模块编号：
 * 模块名称：
 * 设计文件：
 * 完成日期：2016-07-11
 * 作    者: YK
 * 内容摘要：
 */
package com.cn.gazelle.logistics.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类 名 称：TypeConversionUtil
 * 内容描述：
 * 方法描述：该类有 个方法
 * 01
 *
 * @authot YK
 */
public class TypeConversionUtil {
    /**
     * @param b 字节数组
     * @return 16进制字符串
     * @throws
     * @Title:bytes2HexString
     * @Description:字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        StringBuffer result = new StringBuffer();
        String hex;
        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            result.append(hex.toUpperCase());
        }
        return result.toString();
    }

    /**
     * @param src 16进制字符串
     * @return 字节数组
     * @throws
     * @Title:hexString2Bytes
     * @Description:16进制字符串转字节数组
     */
    public static byte[] hexString2Bytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = Integer
                    .valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }

    /**
     * @param strPart 字符串
     * @return 16进制字符串
     * @throws
     * @Title:string2HexString
     * @Description:字符串转16进制字符串
     */
    public static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    /**
     * @param src 16进制字符串
     * @return 字节数组
     * @throws
     * @Title:hexString2String
     * @Description:16进制字符串转字符串
     */
    public static String hexString2String(String src) {
        String temp = "";
        for (int i = 0; i < src.length() / 2; i++) {
            temp = temp
                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),
                    16).byteValue();
        }
        return temp;
    }

    /**
     * @param src
     * @return
     * @throws
     * @Title:char2Byte
     * @Description:字符转成字节数据char-->integer-->byte
     */
    public static Byte char2Byte(Character src) {
        return Integer.valueOf((int) src).byteValue();
    }

    /**
     * @param a   转化数据
     * @param len 占用字节数
     * @return
     * @throws
     * @Title:intToHexString
     * @Description:10进制数字转成16进制
     */
    public static String intToHexString(int a, int len) {
        len <<= 1;
        String hexString = Integer.toHexString(a);
        int b = len - hexString.length();
        if (b > 0) {
            for (int i = 0; i < b; i++) {
//                hexString = "0" + hexString;
                hexString = hexString;
            }
        }
        return  hexString.toUpperCase();
    }
    /**
     * @param a   转化数据
     * @return
     * @throws
     * @Title:intToHexString
     * @Description:16进制数字转成10进制
     */
    public static long hexStringToint(String a) {
        long b = 0;
        b = Long.parseLong(a, 16);
        return b;
    }

    /**
     * @param timestamp 格林威治时间
     * @return
     * @throws
     * @Title:gmtToDate
     * @Description:格林威治时间转普通时间
     */
    public static String gmtToDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(timestamp * 1000));
        return date;
    }

    /**
     * @return
     * @throws
     * @Description:当前时转格林威治时间
     */
    public static long currentTime2GMT() {
        return new Date().getTime()/1000;
    }

    /**
     * @param hex 16进制字符串
     * @return
     * @throws
     * @Title:convertHexToString
     * @Description:16进制转ASC字符
     */
    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {
            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    /**
     * @param hexString 16进制字符串
     * @return
     * @throws
     * @Title:hexString2binaryString
     * @Description:16进制转二进制
     */
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000"
                    + Integer.toBinaryString(Integer.parseInt(hexString
                    .substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    /**
     * @param hexString 16进制字符串
     * @return
     * @throws
     * @Title:hexString2binaryString
     * @Description:16进制前低后高
     */
    public static String hexStringReverse(String hexString) {
        int len = hexString.length();
        char[] ch = hexString.toCharArray();
        char ch_new[] = new char[len];
        int a = 0; // 偶数行
        int b = 1; // 奇数行
        for (int i = len - 2; i >= 0; i = i - 2) {
            ch_new[a] = ch[i];
            a = a + 2;
        }
        for (int i = len - 1; i > 0; i = i - 2) {
            ch_new[b] = ch[i];
            b = b + 2;
        }
        String str = new String(ch_new);
        return str;
    }
    /**
     * @param hexString 16进制字符串
     * @return
     * @throws
     * @Title:hexStr2Float
     * @Description:16进制字符串转float
     */

    public static String hexStr2Float(String hexString) {
        String value = Float.intBitsToFloat(Integer.valueOf(hexString.trim(), 16)) + "";
        return value;
    }



    public static void main(String[] args) {
        /*System.out.println(TypeConversionUtil.gmtToDate(1467958385));
        System.out.println(TypeConversionUtil.convertHexToString("4944445F3231364730325F532056312E322E3100"));
        System.out.println(TypeConversionUtil.hexString2binaryString("af"));
        System.out.println(TypeConversionUtil.hexStringReverse("c1f96052"));
        System.out.println(TypeConversionUtil.currentTime2GMT());
        */
        // 固定截取obd_id
        String str = "404086000431303031313132353239393837000000000000004004C1F0695200F169529C91110000000000698300000D0000000400036401014C00030022032104210521062107210C210D210E210F2110211121132115211C211F21212124212E212F2130213121322133213C214221432144214521472149214A214C214D214E219AE90D0A";
        System.out.println(str.substring(10,50));
        // 固定截取协议类型
        System.out.println(str.substring(50,54));
        //stat_data数据
        System.out.println(str.substring(54,122));
//        System.out.println("C1F0695200F169529C91110000000000698300000D0000000400036401014C000300".length());

        System.out.println(TypeConversionUtil.hexStringToint("5269F0C1"));
//        System.out.println(TypeConversionUtil.gmtToDate(1382674625));
//        01190A0D04121A1480D60488C5721800000000AF
        System.out.println("=="+intToHexString((int) (new Date().getTime()/1000),16));
        System.out.println("=="+hexStringReverse(intToHexString((int) (new Date().getTime()/1000),16)));
        System.out.println("=="+hexStr2Float("3E1E9E9F"));



    }
}
