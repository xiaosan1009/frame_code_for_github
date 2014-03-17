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
 * ϵͳ��ͨ������.
 * @author King
 * @since 2010.03.19
 */
public class CommonUtil {
    
    private static LogUtil sLog = new LogUtil(CommonUtil.class, true);

    /**
     * ���캯��.
     */
    public CommonUtil() {
        super();

    }
        
    /**
     * HTML��������ת��(��ֹxss©��).
     * @param str ת��ǰ������
     * @return ת���������
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
     * ���û�����Ķ����ı�ԭ�����.
     * @param inputTxt ת��ǰ���ı�
     * @return �����ı�ԭ�����
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
     * �ַ�����ָ�����ַ�ɾ��.
     * @param str �ַ���
     * @param c ɾ���ַ�
     * @return �仯����ַ���
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
     * ɾ���ַ����еĶ���.
     * @param str �ַ���
     * @return �仯����ַ���
     */
    public static final String removeComma(String str) {
        return removeChar(str, ',');
    }
    
    /**
     * ɾ���ַ����е�ð��.
     * @param str �ַ���
     * @return �仯����ַ���
     */
    public static final String removeColon(String str) {
        return removeChar(str, ConstantsUtil.Char.COLON);
    }
    
    /**
     * ������'/'ȥ��.
     * @param str �ַ���
     * @return �仯���ַ���
     */
    public static final String removeSlash(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return removeChar(str, ConstantsUtil.Char.SLASH);        
    }
    
    /**
     * ������'-'ȥ��.
     * @param str �ַ���
     * @return �仯���ַ���
     */
    public static final String removeLine(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return removeChar(str, ConstantsUtil.Char.LINE);        
    }
    
    /**
     * ���������з����ַ���ȥ��ȥ����������֤�á�.
     * @param str �ַ���
     * @return �仯���ַ���
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
     * ָ���������У�value�����׷�ӵ�����λ����length�����ַ���padding��.
     * @param value ������
     * @param padding �����ַ�
     * @param length ���䵽����
     * @return ��������ַ���
     */
    public static String lpad(String value, String padding, int length) {
        final StringBuffer sb = new StringBuffer(value);
        while (sb.length() < length) {
            sb.insert(0, padding);
        }
        return sb.toString();
    }
    
    /**
     * ����ΪNULL�����ַ��滻.
     * @param value ����
     * @return �滻��������
     */
    public static String nvl(Object value) {
        return nvl(value, ConstantsUtil.Str.EMPTY);
    }
    
    /**
     * ����ΪNULL�����ַ��滻.
     * @param value ����
     * @return �滻��������
     */
    public static String nvl(String value) {
        return nvl(value, ConstantsUtil.Str.EMPTY);
    }

    /**
     * ����ΪNULL����ָ���ַ��滻.
     * @param value ����
     * @param altVal ָ���滻�ַ�
     * @return �滻��������
     */
    public static String nvl(String value, String altVal) {
        if (value == null) {
            return altVal;
        }
        return value;
    }
    
    /**
     * ����ΪNULL����ָ���ַ��滻.
     * @param value ����
     * @param altVal ָ���滻�ַ�
     * @return �滻��������
     */
    public static String nvl(Object value, String altVal) {
        if (value == null) {
            return altVal;
        }
        return String.valueOf(value);
    }
    
    /**
     * ����ΪNULL����ַ���ʱ����ָ���ַ��滻.
     * @param value ����
     * @param altVal ָ���滻�ַ�
     * @return �滻��������
     */
    public static String evl(String value, String altVal) {
        if (value == null || value.length() == 0) {
            return altVal;
        }
        return value;
        
    }
    
    /**
     * ����ΪNULL����ַ���ʱ����ָ���ַ��滻.
     * @param value ����
     * @param altVal ָ���滻�ַ�
     * @return �滻��������
     */
    public static String nevl(String value, String altVal) {
        if (value == null || value.length() == 0) {
            return ConstantsUtil.Str.EMPTY;
        }
        return altVal;
        
    }
    
    /**
     * ����ΪNULL����ַ���ʱ����ָ���ַ��滻.
     * @param value ����
     * @param altVal ָ���滻�ַ�
     * @return �滻��������
     */
    public static String nevl(Object value, String altVal) {
        if (value == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return altVal;
    }
    
    /**
     * ����ΪNULL����ָ�����ַ��滻.
     * @param value ����
     * @param altVal ָ���滻�ַ�
     * @return �滻��������
     */
    public static String evl(Object value, String altVal) {
        if (value == null) {
            return altVal;
        }
        return String.valueOf(value);
    }
    
    /**
     * ����Ϊ"-----"���Կ��ַ��滻.
     * @param value ����
     * @return �滻��������
     */
    public static String unnvl(String value) {
        if (value.equals(ConstantsUtil.Default.VALUE_NOTHING)) {
            return "";
        }
        return value;
    }
    
    /**
     * ����Ϊ"--:--"���Կ��ַ��滻.
     * @param value ����
     * @return �滻��������
     */
    public static String unnvl2(String value) {
        if (value.equals(ConstantsUtil.Default.HHMM_NOTHING)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return value;
    }
    
    /**
     * ����Ϊ"-----"����'0'�滻.
     * @param value ����
     * @return �滻��������
     */
    public static String unnvl3(String value) {
        if (value.length() == 0 || value.equals(ConstantsUtil.Default.VALUE_NOTHING)) {
            return "0";
        }
        return value;
    }
    
    /**
     * �ֽڱȽ�.
     * @param value �ֽ�
     * @param bit �Ƚ��Լ�
     * @return �ȽϽ��
     */
    public static boolean isBitOn(int value, int bit) {
        
        boolean bRet = false;
        
        if ((value & bit) == bit) {
            bRet = true;
        }
        
        return bRet;
    }
    
    /**
     * ʱ��ת��.
     * 
     * @param value ʱ��
     * @param altVal �������Ϊ�գ����ص�����
     * @param format ʱ���ʽ
     * @return ת����Ľ��
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
     * ����ת��.
     * 
     * @param value ����
     * @return ת����Ľ��
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
     * ת����CSV��ʽ������.
     * 
     * @param strArray ��������
     * @return ת����Ľ��
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
     * ת���ɵ�ַ��ʽ������.
     * 
     * @param remoteAddr ת��������
     * @return ת����Ľ��
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
     * ת���ɵ�ַ��ʽ������.
     * 
     * @param remoteAddr ת��������
     * @return ת����Ľ��
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
     * ����ת��.
     * 
     * @param totalDay ��ǰ����
     * @param format ת����ʽ
     * @return ת����Ľ��
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
     * ������ֳ����޶����ȣ���߽ضϲ���'...'.
     * 
     * @param val ����
     * @param num �޶�����
     * @return ת����Ľ��
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
     * �����ļ�����.
     * 
     * @param in �������ļ�
     * @param out �������ɵ��ļ�
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
     * �ϴ�·����ȡ��.
     * 
     * @param sessionID session ID
     * @return �ϴ�·��
     */
    public static String getSessionUploadPath(String sessionID) {
        
        return ControlConfig.getSesfPath() + sessionID 
            + File.separator + ControlConfig.DEF_UPLOADDIR;
    }
    
    /**
     * �ϴ�·����ȡ��.
     * 
     * @return �ϴ�·��
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
     * ��ʱ�ļ�·����ȡ��.
     * 
     * @return ��ʱ�ļ�·��
     * @throws RichClientWebException RichClientWebException
     */
    public static String getFileTempPath() throws RichClientWebException {
        
        return ControlConfig.getRealPath() 
            + ControlConfig.getInstance().getConfiguration().getUploadPath() + File.separator;
    }
    
    /**
     * ���̵�ַ��ȡ��.
     * 
     * @return �ϴ�·��
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
     * ����ת����ʽ.
     * 
     * @param value ����
     * @param format ��ʽ
     * @return ת����Ľ��
     */
    public static String numFormat(int value, String format) {

        String retValue = null;

        final DecimalFormat decform = new DecimalFormat(format);
        retValue = decform.format(value);

        return retValue;
    }
    
    /**
     * ����ת����ʽ.
     * 
     * @param value ����
     * @param format ��ʽ
     * @return ת����Ľ��
     */
    public static String numFormat(long value, String format) {
        
        String retValue = null;
        
        final DecimalFormat decform = new DecimalFormat(format);
        retValue = decform.format(value);
        
        return retValue;
    }
    
    /**
     * ����ת����ʽ.
     * 
     * @param value ����
     * @param format ��ʽ
     * @return ת����Ľ��
     */
    public static String numFormat(float value, String format) {
        
        String retValue = null;
        
        final DecimalFormat decform = new DecimalFormat(format);
        retValue = decform.format(value);
        
        return retValue;        
    }
    
    /**
     * ����ת����ʽ.
     * 
     * @param value ����
     * @param format ��ʽ
     * @return ת����Ľ��
     */
    public static String numFormat(double value, String format) {
        
        String retValue = null;
        
        final DecimalFormat decform = new DecimalFormat(format);
        retValue = decform.format(value);
        
        return retValue;        
    }
    
    /**
     * �ַ���null��ʱ�򣬿��ַ�����ʾ.
     * @param value �ַ���
     * @return �任����ַ���
     */
    public static String ncs(String value) {
        return ncs(value, ConstantsUtil.Str.EMPTY);
    }
    
    /**
     * �ַ���null��ʱ�����������ʾ.
     * @param value �ַ���
     * @param altVal �����
     * @return �任����ַ���
     */
    public static String ncs(String value, String altVal) {
        if (value == null) {
            return altVal;
        }
        return value;
    }
    
    /**
     * 16����������ת����10����������.
     * @param orgStr 16����������
     * @return �任����ַ���
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
     * �ֽ���ʮ������ת��.
     * 
     * @param data �ֽ���������
     * @return ת����Ľ��
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
     * �ֽ���ʮ������ת��.
     * 
     * @param data �ֽ�����
     * @return ת����Ľ��
     */
    private static String convertBinaryToHex(byte data) {

        String str = Integer.toHexString(data & 0x00FF);
        str = "00" + str;
        return str.substring(str.length() - 2);
    }

    /**
     * ȥ�ո���.
     * 
     * @param str ��������
     * @return �����Ľ��
     */
    public static String trim(String str) {
        str = StringUtils.trimToEmpty(str);
        str = StringUtils.strip(str, " ��");
        return str;
    }

    /**
     * �ǿ��ж�.
     * 
     * @param str �ж�����
     * @return �жϵĽ����true���ǿգ�false���ա�
     */
    public static boolean isNotEmpty(String str) {
        return !ConstantsUtil.Str.EMPTY.equals(trim(nvl(str)));
    }
    
    /**
     * �ǿ��ж�.
     * 
     * @param str �ж�����
     * @return �жϵĽ����true���ǿգ�false���ա�
     */
    public static boolean isNotEmpty(Object str) {
        return !ConstantsUtil.Str.EMPTY.equals(trim(nvl(str)));
    }

    /**
     * ���ж�.
     * 
     * @param str �ж�����
     * @return �жϵĽ����true���գ�false���ǿա�
     */
    public static boolean isEmpty(String str) {
        return ConstantsUtil.Str.EMPTY.equals(trim(nvl(str)));
    }
    
    /**
     * ���ж�.
     * 
     * @param str �ж�����
     * @return �жϵĽ����true���գ�false���ǿա�
     */
    public static boolean isEmpty(Object str) {
        return ConstantsUtil.Str.EMPTY.equals(trim(nvl(str)));
    }
    
    /**
     * �ַ���ת��.
     * 
     * @param obj ת������
     * @return ת����Ľ��
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
     * ���鿽��.
     * 
     * @param array Ҫ����������
     * @return �����������
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
     * �ַ���תutf-8.
     * 
     * @param str Ҫת�����ַ���
     * @return ת������ַ���
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
     * �ַ���תgb2312.
     * 
     * @param str Ҫת�����ַ���
     * @return ת������ַ���
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
     * @Description: ���������ֽ�����ת����IPV4��ʮ���Ʒֶα�ʾ��ʽ��ip��ַ�ַ���
     * @author king
     * @since 2013-7-24 ����10:58:14 
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
     * @Description: ����������ʮ���Ʒֶθ�ʽ��ʾ��ipv4��ַ�ַ���ת�����ֽ�����
     * @author king
     * @since 2013-7-24 ����10:58:57 
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
