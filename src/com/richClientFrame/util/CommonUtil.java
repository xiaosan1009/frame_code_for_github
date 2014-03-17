package com.richClientFrame.util;

import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlConfig;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 系统共通方法类.
 * @author King
 * @since 2010.03.19
 */
public class CommonUtil {
    
    private static LogUtil sLog = new LogUtil(CommonUtil.class, true);

    /**
     * 构造函数.
     */
    public CommonUtil() {
        super();

    }
        
    /**
     * HTML数据类型转换(防止xss漏洞).
     * @param str 转换前的数据
     * @return 转换后的数据
     */
    public static String escapeHtml(String str) {
        
//        sLog.info("escapeHtml", "start", "str = " + str);
        
        if (str == null) {
            return null;
        }
        
        str = toString(str);

        if (str.indexOf("&") != -1) {
            str = str.replaceAll("&", "&amp;");
        }
        if (str.indexOf("<") != -1) {
            str = str.replaceAll("<", "&lt;");
        }
        if (str.indexOf(">") != -1) {
            str = str.replaceAll(">", "&gt;");
        }
        if (str.indexOf("\"") != -1) {
            str = str.replaceAll("\"", "&quot;");
        }
        if (str.indexOf("'") != -1) {
            str = str.replaceAll("'", "&#039;");
        }
//        if (str.indexOf(" ") != -1) {
//            str = str.replaceAll(" ", "&nbsp;");
//        }
//        if (str.indexOf("\n") != -1) {
//            str = str.replaceAll("\n", "<br>");
//        }

//        sLog.info("escapeHtml", "end", "str = " + str);
        return str;
    }
    
    /**
     * 将用户输入的多行文本原样输出.
     * @param inputTxt 转换前的文本
     * @return 多行文本原样输出
     * */
    public static String formatTxt(String inputTxt) {
        String outputTxt = "";
        char c;
        if (inputTxt != null) {
            for (int i = 0; i < inputTxt.length(); i++) {
                c = inputTxt.charAt(i);
                switch (c) {
                    case 13:
                        outputTxt += "<br>";
                        break;
                    case 32:
                        outputTxt += "&nbsp;";
                        break;
                    default:
                        outputTxt += c;
                }
            }
        }
        return outputTxt;
    }
    
    /**
     * 字符串中指定的字符删除.
     * @param str 字符串
     * @param c 删除字符
     * @return 变化后的字符串
     */
    public static final String removeChar(String str, char c) {
        String change = "";
        for (int i = 0; i < str.length(); i++) {
            final char tmp = str.charAt(i);
            if (tmp != c) {
                change = change + tmp;
            }
        }
        return change;
    }
    
    /**
     * 删除字符串中的逗号.
     * @param str 字符串
     * @return 变化后的字符串
     */
    public static final String removeComma(String str) {
        return removeChar(str, ',');
    }
    
    /**
     * 删除字符串中的冒号.
     * @param str 字符串
     * @return 变化后的字符串
     */
    public static final String removeColon(String str) {
        return removeChar(str, ConstantsUtil.Char.COLON);
    }
    
    /**
     * 文字列'/'去除.
     * @param str 字符串
     * @return 变化后字符串
     */
    public static final String removeSlash(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return removeChar(str, ConstantsUtil.Char.SLASH);        
    }
    
    /**
     * 文字列'-'去除.
     * @param str 字符串
     * @return 变化后字符串
     */
    public static final String removeLine(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return removeChar(str, ConstantsUtil.Char.LINE);        
    }
    
    /**
     * 文字列所有非数字符号去除去除【日期验证用】.
     * @param str 字符串
     * @return 变化后字符串
     */
    public static final String removeAllChar(String str) {
        if (isEmpty(str)) {
            return str;
        }
        str = removeLine(str);
        str = removeSlash(str);
        return str;
    }
    
    /**
     * 指定在文字列（value）后边追加到具体位数（length）的字符（padding）.
     * @param value 文字列
     * @param padding 补充字符
     * @param length 补充到长度
     * @return 补正后的字符串
     */
    public static String lpad(String value, String padding, int length) {
        final StringBuffer sb = new StringBuffer(value);
        while (sb.length() < length) {
            sb.insert(0, padding);
        }
        return sb.toString();
    }
    
    /**
     * 数据为NULL，空字符替换.
     * @param value 数据
     * @return 替换后文字列
     */
    public static String nvl(Object value) {
        return nvl(value, ConstantsUtil.Str.EMPTY);
    }
    
    /**
     * 数据为NULL，空字符替换.
     * @param value 数据
     * @return 替换后文字列
     */
    public static String nvl(String value) {
        return nvl(value, ConstantsUtil.Str.EMPTY);
    }

    /**
     * 数据为NULL，以指定字符替换.
     * @param value 数据
     * @param altVal 指定替换字符
     * @return 替换后文字列
     */
    public static String nvl(String value, String altVal) {
        if (value == null) {
            return altVal;
        }
        return value;
    }
    
    /**
     * 数据为NULL，以指定字符替换.
     * @param value 数据
     * @param altVal 指定替换字符
     * @return 替换后文字列
     */
    public static String nvl(Object value, String altVal) {
        if (value == null) {
            return altVal;
        }
        return String.valueOf(value);
    }
    
    /**
     * 数据为NULL或空字符的时候，以指定字符替换.
     * @param value 数据
     * @param altVal 指定替换字符
     * @return 替换后文字列
     */
    public static String evl(String value, String altVal) {
        if (value == null || value.length() == 0) {
            return altVal;
        }
        return value;
        
    }
    
    /**
     * 数据为NULL或空字符的时候，以指定字符替换.
     * @param value 数据
     * @param altVal 指定替换字符
     * @return 替换后文字列
     */
    public static String nevl(String value, String altVal) {
        if (value == null || value.length() == 0) {
            return ConstantsUtil.Str.EMPTY;
        }
        return altVal;
        
    }
    
    /**
     * 数据为NULL或空字符的时候，以指定字符替换.
     * @param value 数据
     * @param altVal 指定替换字符
     * @return 替换后文字列
     */
    public static String nevl(Object value, String altVal) {
        if (value == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return altVal;
    }
    
    /**
     * 对象为NULL，以指定的字符替换.
     * @param value 对象
     * @param altVal 指定替换字符
     * @return 替换后文字列
     */
    public static String evl(Object value, String altVal) {
        if (value == null) {
            return altVal;
        }
        return String.valueOf(value);
    }
    
    /**
     * 数据为"-----"，以空字符替换.
     * @param value 数据
     * @return 替换后文字列
     */
    public static String unnvl(String value) {
        if (value.equals(ConstantsUtil.Default.VALUE_NOTHING)) {
            return "";
        }
        return value;
    }
    
    /**
     * 数据为"--:--"，以空字符替换.
     * @param value 数据
     * @return 替换后文字列
     */
    public static String unnvl2(String value) {
        if (value.equals(ConstantsUtil.Default.HHMM_NOTHING)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return value;
    }
    
    /**
     * 数据为"-----"，以'0'替换.
     * @param value 数据
     * @return 替换后文字列
     */
    public static String unnvl3(String value) {
        if (value.length() == 0 || value.equals(ConstantsUtil.Default.VALUE_NOTHING)) {
            return "0";
        }
        return value;
    }
    
    /**
     * 字节比较.
     * @param value 字节
     * @param bit 比较自己
     * @return 比较结果
     */
    public static boolean isBitOn(int value, int bit) {
        
        boolean bRet = false;
        
        if ((value & bit) == bit) {
            bRet = true;
        }
        
        return bRet;
    }
    
    /**
     * 时间转换.
     * 
     * @param value 时间
     * @param altVal 如果数据为空，返回的数据
     * @param format 时间格式
     * @return 转换后的结果
     * @throws RichClientWebException RichClientWebException
     */
    public static String convTime(String value, String altVal, int format)
        throws RichClientWebException {
        
        if (value == null || value.length() == 0) {
            return altVal;
        } else {
            value = DateUtils.getTimeFormat(format, Integer.parseInt(value));
        }
        
        return value;
    }
    
    /**
     * 日期转换.
     * 
     * @param value 日期
     * @return 转换后的结果
     */
    public static Timestamp convTime(long value) {
        
        final String strWork = String.valueOf(value);
        
        final String monthWork = strWork.substring(4, 6);
        final int month = Integer.parseInt(monthWork);
        
        final Calendar cal = new GregorianCalendar(Integer.parseInt(strWork.substring(0, 4)),
                        month - 1,
                        Integer.parseInt(strWork.substring(6,8)),
                        Integer.parseInt(strWork.substring(8,10)),
                        Integer.parseInt(strWork.substring(10,12)),
                        Integer.parseInt(strWork.substring(12,14)));
        
        final Timestamp convTime = new Timestamp(cal.getTimeInMillis());
        
        return convTime;
    }
    
    /**
     * 转换成CSV形式的数据.
     * 
     * @param strArray 数据数组
     * @return 转换后的结果
     */
    public static String makeCsvString(String[] strArray) {
        
        if (strArray == null) {
            return null;
        }
        
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < strArray.length; i++) {
            buf.append(strArray[i]);
            if (i != strArray.length - 1) {
                buf.append(ConstantsUtil.Str.COMMA);
            }
        }
        
        return buf.toString();
        
    }
    
    /**
     * 转换成地址形式的数据.
     * 
     * @param remoteAddr 转换的数据
     * @return 转换后的结果
     */
    public static String convAddr(String remoteAddr) {
        
        if (remoteAddr == null) {
            return null;
        }
        
        final StringBuffer buf = new StringBuffer();
        
        final String[] str = remoteAddr.split(ConstantsUtil.Str.REGEX_DOT);
        
        if (str == null || (str != null && str.length != 4)) {
            return null;
        }
        
        final DecimalFormat fmt = new DecimalFormat("000");
        for (int nCnt = 0; nCnt < str.length; nCnt++) {
            buf.append(fmt.format(Integer.parseInt(str[nCnt])));
        }
        
        return buf.toString();
    }
    
    /**
     * 转换成地址形式的数据.
     * 
     * @param remoteAddr 转换的数据
     * @return 转换后的结果
     */
    public static String convAddrRev(Long remoteAddr) {
        
        if (remoteAddr == 0) {
            return "0";
        }
        
        final String str = String.valueOf(remoteAddr);
        final StringBuffer buf = new StringBuffer();
        
        buf.append(Integer.parseInt(str.substring(0,3)));
        buf.append(ConstantsUtil.Str.DOT);
        buf.append(Integer.parseInt(str.substring(3,6)));
        buf.append(ConstantsUtil.Str.DOT);
        buf.append(Integer.parseInt(str.substring(6,9)));
        buf.append(ConstantsUtil.Str.DOT);
        buf.append(Integer.parseInt(str.substring(9,12)));
        
        return buf.toString();
    }
    
    /**
     * 日期转换.
     * 
     * @param totalDay 当前日期
     * @param format 转换格式
     * @return 转换后的结果
     */
    public static String convCardDate2(int totalDay, String format) {
        
        final GregorianCalendar gcal = new GregorianCalendar(2000, 0, 1);
        gcal.add(GregorianCalendar.DAY_OF_YEAR, totalDay);
        
        String strYMD = format;
        strYMD = Integer.toString(gcal.get(GregorianCalendar.YEAR)) 
            + ConstantsUtil.Str.SLASH 
            + Integer.toString(gcal.get(GregorianCalendar.MONTH) + 1) 
            + ConstantsUtil.Str.SLASH + Integer.toString(gcal.get(GregorianCalendar.DATE)); 
        
        return strYMD;            
    }
    
    /**
     * 如果文字超过限定长度，后边截断补上'...'.
     * 
     * @param val 文字
     * @param num 限定长度
     * @return 转换后的结果
     */
    public static String convListStr(String val, int num) {
        
        String strRes;
        
        if (val.length() <= num) {
            return val;
        }
            
        strRes = val.substring(0,num);
        strRes += "...";
        
        return strRes;
    
    }
    
    /**
     * 拷贝文件内容.
     * 
     * @param in 拷贝的文件
     * @param out 拷贝生成的文件
     * @throws Exception Exception
     */
    public void copyFile(File in, File out) throws Exception {
        
        final FileChannel sourceChannel = new FileInputStream(in).getChannel();
        
        final FileChannel destinationChannel = new FileOutputStream(out).getChannel();
        
        sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
        
        sourceChannel.close();
        destinationChannel.close();
    }
    
    /**
     * 上传路径的取得.
     * 
     * @param sessionID session ID
     * @return 上传路径
     */
    public static String getSessionUploadPath(String sessionID) {
        
        return ControlConfig.getSesfPath() + sessionID 
            + File.separator + ControlConfig.DEF_UPLOADDIR;
    }
    
    /**
     * 上传路径的取得.
     * 
     * @return 上传路径
     * @throws RichClientWebException RichClientWebException
     */
    public static String getUploadRealPath() throws RichClientWebException {
        String path = ControlConfig.getInstance().getConfiguration().getAbsolutePath();
        if (CommonUtil.isEmpty(path)) {
            path = ControlConfig.getRealPath();
        } else {
            path += File.separator;
        }
        return path;
    }
    
    /**
     * 临时文件路径的取得.
     * 
     * @return 临时文件路径
     * @throws RichClientWebException RichClientWebException
     */
    public static String getFileTempPath() throws RichClientWebException {
        
        return ControlConfig.getRealPath() 
            + ControlConfig.getInstance().getConfiguration().getUploadPath() + File.separator;
    }
    
    /**
     * 工程地址的取得.
     * 
     * @return 上传路径
     * @throws RichClientWebException RichClientWebException
     */
    public static String getAppUrlPath() throws RichClientWebException {
        String path = ConstantsUtil.Str.EMPTY;
        if (ControlConfig.getInstance().getConfiguration().isUploadHostUrlAuto()) {
            try {
                path += "http://" + InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR);
            }
        } else {
            path = ControlConfig.getInstance().getConfiguration().getUploadHostUrl();
        }
        return path;
    }

    /**
     * 数字转换格式.
     * 
     * @param value 数字
     * @param format 格式
     * @return 转换后的结果
     */
    public static String numFormat(int value, String format) {

        String retValue = null;

        final DecimalFormat decform = new DecimalFormat(format);
        retValue = decform.format(value);

        return retValue;
    }
    
    /**
     * 数字转换格式.
     * 
     * @param value 数字
     * @param format 格式
     * @return 转换后的结果
     */
    public static String numFormat(long value, String format) {
        
        String retValue = null;
        
        final DecimalFormat decform = new DecimalFormat(format);
        retValue = decform.format(value);
        
        return retValue;
    }
    
    /**
     * 数字转换格式.
     * 
     * @param value 数字
     * @param format 格式
     * @return 转换后的结果
     */
    public static String numFormat(float value, String format) {
        
        String retValue = null;
        
        final DecimalFormat decform = new DecimalFormat(format);
        retValue = decform.format(value);
        
        return retValue;        
    }
    
    /**
     * 数字转换格式.
     * 
     * @param value 数字
     * @param format 格式
     * @return 转换后的结果
     */
    public static String numFormat(double value, String format) {
        
        String retValue = null;
        
        final DecimalFormat decform = new DecimalFormat(format);
        retValue = decform.format(value);
        
        return retValue;        
    }
    
    /**
     * 字符串null的时候，空字符串表示.
     * @param value 字符串
     * @return 变换后的字符串
     */
    public static String ncs(String value) {
        return ncs(value, ConstantsUtil.Str.EMPTY);
    }
    
    /**
     * 字符串null的时候，用替代符表示.
     * @param value 字符串
     * @param altVal 替代符
     * @return 变换后的字符串
     */
    public static String ncs(String value, String altVal) {
        if (value == null) {
            return altVal;
        }
        return value;
    }
    
    /**
     * 16进制文字列转换成10进制文字列.
     * @param orgStr 16进制文字列
     * @return 变换后的字符串
     */
    public static byte[] convertHexToBinary(String orgStr) {

        if (orgStr == null) {
            return new byte[0];
        }

        final int count = orgStr.length() / 2;
        final byte[] retData = new byte[count];

        for (int i = 0; i < count; i++) {
            final int tmp = Integer.parseInt(orgStr.substring(2 * i, 2 * i + 2), 16);
            final Integer tmpInt = new Integer(tmp);
            retData[i] = tmpInt.byteValue();
        }
        return retData;
    }
    
    /**
     * 字节向十六进制转换.
     * 
     * @param data 字节数组内容
     * @return 转换后的结果
     */
    public static String convertBinaryToHex(byte[] data) {

        if (data == null || data.length == 0) {
            return "";
        }

        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            buf.append(convertBinaryToHex(data[i]));
        }

        return buf.toString();
    }

    /**
     * 字节向十六进制转换.
     * 
     * @param data 字节内容
     * @return 转换后的结果
     */
    private static String convertBinaryToHex(byte data) {

        String str = Integer.toHexString(data & 0x00FF);
        str = "00" + str;
        return str.substring(str.length() - 2);
    }

    /**
     * 去空格处理.
     * 
     * @param str 处理内容
     * @return 处理后的结果
     */
    public static String trim(String str) {
        str = StringUtils.trimToEmpty(str);
        str = StringUtils.strip(str, " 　");
        return str;
    }

    /**
     * 非空判断.
     * 
     * @param str 判断内容
     * @return 判断的结果【true：非空；false：空】
     */
    public static boolean isNotEmpty(String str) {
        return !ConstantsUtil.Str.EMPTY.equals(trim(nvl(str)));
    }
    
    /**
     * 非空判断.
     * 
     * @param str 判断内容
     * @return 判断的结果【true：非空；false：空】
     */
    public static boolean isNotEmpty(Object str) {
        return !ConstantsUtil.Str.EMPTY.equals(trim(nvl(str)));
    }

    /**
     * 空判断.
     * 
     * @param str 判断内容
     * @return 判断的结果【true：空；false：非空】
     */
    public static boolean isEmpty(String str) {
        return ConstantsUtil.Str.EMPTY.equals(trim(nvl(str)));
    }
    
    /**
     * 空判断.
     * 
     * @param str 判断内容
     * @return 判断的结果【true：空；false：非空】
     */
    public static boolean isEmpty(Object str) {
        return ConstantsUtil.Str.EMPTY.equals(trim(nvl(str)));
    }
    
    /**
     * 字符串转换.
     * 
     * @param obj 转换内容
     * @return 转换后的结果
     */
    public static String toString(Object obj) {
        return trim(nvl(obj));
    }
    
    /**
     * @Description: 
     * @author king
     * @since Sep 6, 2013 11:34:02 AM 
     * @version V1.0
     * @param obj obj
     * @return parseBoolean
     */
    public static boolean toBoolean(Object obj) {
        return Boolean.parseBoolean(toString(obj));
    }
    
    /**
     * 数组拷贝.
     * 
     * @param array 要拷贝的数组
     * @return 拷贝后的数组
     */
    public static ResponseTab[] arrayCopy(ResponseTab[]...array) {
        int arrayLenth = 0;
        for (Object[] arr : array) {
            arrayLenth += arr.length;
        }
        final ResponseTab[] objArray = new ResponseTab[arrayLenth];
        int addPosition = 0;
        for (Object[] arr : array) {
            System.arraycopy(arr, 0, objArray, addPosition, arr.length);
            addPosition += arr.length;
        }
        return objArray;
    }
    
    /**
     * 字符串转utf-8.
     * 
     * @param str 要转换的字符串
     * @return 转换后的字符串
     */
    public static String toUtf8String(String str) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * 字符串转gb2312.
     * 
     * @param str 要转换的字符串
     * @return 转换后的字符串
     */
    public static String toGb2312String(String str) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("gb2312");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /** 
     * @Description: 将给定的字节数组转换成IPV4的十进制分段表示格式的ip地址字符串
     * @author king
     * @since 2013-7-24 上午10:58:14 
     * 
     * @param addr addr
     * @return addr
     */
    public static String binaryArray2Ipv4Address(byte[] addr) {
        String ip = "";
        for (int i = 0; i < addr.length; i++) {
            ip += (addr[i] & 0xFF) + ".";
        }
        return ip.substring(0, ip.length() - 1);
    }

    /** 
     * @Description: 将给定的用十进制分段格式表示的ipv4地址字符串转换成字节数组
     * @author king
     * @since 2013-7-24 上午10:58:57 
     * 
     * @param ipAdd ipAdd
     * @return ipAdd
     */
    public static byte[] ipv4Address2BinaryArray(String ipAdd) {
        final byte[] binIP = new byte[4];
        final String[] strs = ipAdd.split("\\.");
        for (int i = 0; i < strs.length; i++) {
            binIP[i] = (byte)Integer.parseInt(strs[i]);
        }
        return binIP;
    }
    
}
