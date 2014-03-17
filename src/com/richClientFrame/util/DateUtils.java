package com.richClientFrame.util;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ����ʱ���ʽת����.
 * @author King
 * @since 2010.07.02
 */
public final class DateUtils {
    
    // ʱ���ʽ
    public static final int FORMAT_HHMM = 1;
    public static final int FORMAT_HHMMSS = 2;
    
    /**
     * ��ʽΪ��yyyy��.
     */
    public static final String FORMAT_YEAR = "yyyy";
    /**
     * ��ʽΪ��MM��.
     */
    public static final String FORMAT_MONTH = "MM";
    
    /**
     * ��ʽΪ��yyyyMMdd��.
     */
    public static final String FORMAT_DD = "dd";
    
    /**
     * ��ʽΪ��yyyyMMdd��.
     */
    public static final String FORMAT_YYYYMM = "yyyyMM";
    
    /**
     * ��ʽΪ��yyyyMMdd��.
     */
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    /**
     * ��ʽΪ��yyyy/MM/dd��.
     */
    public static final String FORMAT_YMD = "yyyy/MM/dd";
    /**
     * ��ʽΪ��yyyy-MM-dd��.
     */
    public static final String FORMAT_YMD2 = "yyyy-MM-dd";
    /**
     * ��ʽΪ��yyyy��MM��dd�ա�.
     */
    public static final String FORMAT_YEAR_MONTH_DATE = "yyyy��MM��dd��";
    /**
     * ��ʽΪ��yyyy��MM�¡�.
     */
    public static final String FORMAT_YEAR_MONTH = "yyyy��MM��";
    /**
     * ��ʽΪ��yyyy��MM��dd��(E)��.
     */
    public static final String FORMAT_YEAR_MONTH_DATE_DAY = "yyyy��MM��dd��(E)";
    
    /**
     * ��ʽΪ��yyyy/MM/dd HH:mm:ss��.
     */
    public static final String FORMAT_YYYYMMDDHHSS = "yyyy/MM/dd HH:mm:ss";
    /**
     * ��ʽΪ��yyyy-MM-dd HH:mm:ss��.
     */
    public static final String FORMAT_YYYYMMDDHHSS2 = "yyyy-MM-dd HH:mm:ss";
    /**
     * ��ʽΪ��yyyyMMddHHmmss��.
     */
    public static final String FORMAT_YYYYMMDDHHSS3 = "yyyyMMddHHmmss";
    /**
     * ��ʽΪ��yyyy/MM/dd HH:mm��.
     */
    public static final String FORMAT_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";
    /**
     * ��ʽΪ��yyyy/MM/dd/HH/mm��.
     */
    public static final String FORMAT_YYYYMMDDHHMM2 = "yyyy/MM/dd/HH/mm";
    /**
     * ��ʽΪ��HH:mm:ss��.
     */
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    /**
     * ��ʽΪ��HH/mm��.
     */
    public static final String FORMAT_HHMM2 = "HH/mm";
    /**
     * ��ʽΪ��yyyy��MM��dd��HH:mm:ss��.
     */
    public static final String FORMAT_YEAR_MONTH_DATE_TIME = "yyyy��MM��dd��HH:mm:ss";
    /**
     * ��ʽΪ��yyyy��MM��dd�� HH:mm��.
     */
    public static final String FORMAT_YEAR_MONTH_DATE_TIME2 = "yyyy��MM��dd�� HH:mm";
    
    /**
     * ���캯��
     */
    private DateUtils() {
        super();
    }
    
    /**
     * ���ڸ�ʽ��.
     * @param cal ����
     * @param format ��ʽ
     * @return ��ʽ���������
     */
    public static final String makeFormat(Calendar cal, String format) {
        
        DateFormat df = new SimpleDateFormat(format); 
        String date = df.format(cal.getTime());
        
        return date;
    }
    
    /**
     * ��ǰʱ��ת��������.
     * @param format ��ʽ
     * @return ת�����ʱ��
     */
    public static long getNowTimeNum(String format) {
        
        return Long.parseLong(getNowTime(format));
        
    }
    
    public static Date getStringToDate(String dateStr, String formatStr) 
        throws RichClientWebException {
        final DateFormat format = new SimpleDateFormat(formatStr); 
        Date date = null; 
        try { 
            date = format.parse(dateStr);  
        } catch (ParseException e) {
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR);
        } 

        return date;
    }
    
    /**
     * ����ʱ���Ը�ʽ�������ʽȡ��.
     * @param format ��ʽ
     * @return ��ʽ�����ʱ��
     */
    public static String getNowTime(String format) {
        
        return makeFormat(GregorianCalendar.getInstance(), format);
    }
    
    /**
     * �����յĸ�ʽ��.
     * @param year ��
     * @param month ��
     * @param day ��
     * @param format ��ʽ
     * @return ��ʽ��������
     */
    public static long getFormatDate(int year, int month, int day, String format) {
        
        final Calendar cal = new GregorianCalendar(year, month - 1, day);
        
        return Long.parseLong(makeFormat(cal, format));
    }
    
    /**
     * ���һ��ȡ��.
     * @param year ��
     * @param month ��
     * @param format ��ʽ
     * @return ���һ��
     */
    public static long getFinalDay(int year, int month, String format) {
        
        final Calendar cal = new GregorianCalendar(year, month - 1, 1);
        
        final int day = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, day);
        
        return Long.parseLong(makeFormat(cal, format));
    }
    
    /**
     * ��һ�������ȡ��.
     * @param year ��
     * @param month ��
     * @return ��һ�������
     */
    public static int getfirstDayOfWeek(int year, int month) {
        
        final Calendar cal1 = new GregorianCalendar(year, month - 1, 1);
        
        return cal1.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * ���һ������ڵ�ȡ��.
     * @param year ��
     * @param month ��
     * @return ���һ�������
     */
    public static int getLastDayOfWeek(int year, int month) {
        final Calendar cal1 = new GregorianCalendar(year, month, 1);
        cal1.add(Calendar.DATE, -1);
        return cal1.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * �������ڱ��µĵڼ��ܣ����ڼ���ȡ��.
     * @param ymd ������
     * @return �������ڱ��µĵڼ��ܣ����ڼ�
     */
    public static int[] getIndexByYmd(int ymd) {
        
        int[] nRet = null;
        
        final String tmp = String.valueOf(ymd);
        if (tmp.length() >= 8) {
            final int year = Integer.parseInt(tmp.substring(0, 4));
            final int month = Integer.parseInt(tmp.substring(4, 6));
            final int day = Integer.parseInt(tmp.substring(6, 8));
            
            nRet = getIndexByYmd(year, month, day);
        }
        
        return nRet;
    }
    
    /**
     * �������ڱ��µĵڼ��ܣ����ڼ���ȡ��.
     * @param year ��
     * @param month ��
     * @param day ��
     * @return �������ڱ��µĵڼ��ܣ����ڼ�
     */
    public static int[] getIndexByYmd(int year, int month, int day){
        
        final int[] nRet = new int[2];
        
        final Calendar cal = new GregorianCalendar(year, month - 1, day);
        final int firstday = getfirstDayOfWeek(year, month);
        
        // ���еĵڼ���
        nRet[0] = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        
        // ���ڼ�
        nRet[1] = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        if (firstday <= nRet[1]) {
            nRet[0] = nRet[0] - 1;
        }
        
        return nRet;
    }
    
    /**
     * ������addNum��ֵ�Լ�kind�����ͱ仯.
     * @param year ��
     * @param month ��
     * @param day ��
     * @return �仯�������
     */
    public static int[] add(int year, int month, int day, int kind, int addNum){
        
        final int[] nRet = {-1, -1, -1};
        
        if (year != -1) {
            
            final Calendar cal = GregorianCalendar.getInstance();
            // ��
            cal.set(Calendar.YEAR, year);
            // ��
            cal.set(Calendar.MONTH, month - 1);
            // ��
            cal.set(Calendar.DATE, day);
            
            cal.add(kind, addNum);
            
            nRet[0] = cal.get(Calendar.YEAR);
            nRet[1] = cal.get(Calendar.MONTH);        
            nRet[2] = cal.get(Calendar.DATE);
        }
        return nRet;
    }
    
    /**
     * ʱ��ĸ�ʽ��.
     * @param type HHMM/HHMMSS
     * @param time ʱ��
     * @return ��ʽ�����ʱ��
     * @throws RichClientWebException AjaxWebException
     */
    public static String getTimeFormat(int type, int time)
        throws RichClientWebException {
        
        return getTimeFormat(type, String.valueOf(time));
    }
    
    /**
     * ʱ��ĸ�ʽ��.
     * @param type HH:MM/HH:MM:SS
     * @param hour Сʱ
     * @param min ����
     * @param sec ��
     * @return ��ʽ�����ʱ��
     * @throws RichClientWebException AjaxWebException
     */
    public static String getTimeFormat(int type, int hour, int min, int sec)
        throws RichClientWebException {
        
        final DecimalFormat decform = new DecimalFormat("00");
        
        int time = 0;
        if (type == FORMAT_HHMM) {
            time = Integer.parseInt(decform.format(hour) + decform.format(min));
            
        } else if (type == FORMAT_HHMMSS) {
            time = Integer.parseInt(decform.format(hour) + decform.format(min) + decform.format(sec));
            
        } else {
            throw new RichClientWebException("No such type of time format.(type= " + type + ")");
        }
        
        return getTimeFormat(type, time);
    }
    
    /**
     * ������ʽ��ʱ��ת��.
     * @param type HHMM/HHMMSS
     * @param time ʱ��
     * @return ת�����ʱ��
     * @throws RichClientWebException AjaxWebException
     */
    public static String getTimeFormat(int type, String time)
        throws RichClientWebException {
        
        String ret = null;
        if (type == FORMAT_HHMM) {            
            if (time.length() < 4) {
                final DecimalFormat decForm = new DecimalFormat("0000");
                time = decForm.format(Integer.parseInt(time));
            }
            final String hh = time.substring(0, 2);
            final String mm = time.substring(2, 4);
            ret = hh + ":" + mm;
            
        } else if (type == FORMAT_HHMMSS) {
            
            if (time.length() < 6) {
                final DecimalFormat decForm = new DecimalFormat("000000");
                time = decForm.format(Integer.parseInt(time));
            }
            final String hh = time.substring(0, 2);
            final String mm = time.substring(2, 4);
            final String ss = time.substring(4, 6);
            ret = hh + ":" + mm + ":" + ss;
        } else {
            
            throw new RichClientWebException("No such type of time format.(type= " + type + ")");
        }
        
        return ret;
        
    }
    
    /**
     * ��ȫ���ں��ת��.
     * @param dateValue ����
     * @param format ��ʽ
     * @return ת���������
     */
    public static String formatString(String dateValue, String format) {
        if (CommonUtil.isEmpty(dateValue)) {
            return dateValue;
        }
        
        final String str = "00000000000000";
        dateValue += str.substring(0, str.length() - dateValue.length());
        
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(dateValue.substring(0,4)));
        cal.set(Calendar.MONTH, Integer.parseInt(dateValue.substring(4, 6)) - 1);
        cal.set(Calendar.DATE, Integer.parseInt(dateValue.substring(6, 8)));

        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateValue.substring(8, 10)));
        cal.set(Calendar.MINUTE, Integer.parseInt(dateValue.substring(10, 12)));
        cal.set(Calendar.SECOND, Integer.parseInt(dateValue.substring(12, 14)));

        final String retVal = makeFormat(cal, format);

        return retVal;
    }

    /**
     * ��������addNum����.
     * @param addNum ��������
     * @param format ��ʽ
     * @return ���Ӻ������
     */
    public static long add2(int addNum , String format) {
        final Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, addNum - 1);

        final DateFormat df = new SimpleDateFormat(format); 
        final long date = Long.parseLong(df.format(cal.getTime()));
        return date;
    }
    
    public static String dateTimeFormat(String format, Date date) {
        final Calendar cal = Calendar.getInstance();
        date.getTime();
        cal.setTime(date);
        final String dateFormat = makeFormat(cal, format);
        return dateFormat;
    }
}
