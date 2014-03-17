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
 * 日期时间格式转换类.
 * @author King
 * @since 2010.07.02
 */
public final class DateUtils {
    
    // 时间格式
    public static final int FORMAT_HHMM = 1;
    public static final int FORMAT_HHMMSS = 2;
    
    /**
     * 格式为【yyyy】.
     */
    public static final String FORMAT_YEAR = "yyyy";
    /**
     * 格式为【MM】.
     */
    public static final String FORMAT_MONTH = "MM";
    
    /**
     * 格式为【yyyyMMdd】.
     */
    public static final String FORMAT_DD = "dd";
    
    /**
     * 格式为【yyyyMMdd】.
     */
    public static final String FORMAT_YYYYMM = "yyyyMM";
    
    /**
     * 格式为【yyyyMMdd】.
     */
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    /**
     * 格式为【yyyy/MM/dd】.
     */
    public static final String FORMAT_YMD = "yyyy/MM/dd";
    /**
     * 格式为【yyyy-MM-dd】.
     */
    public static final String FORMAT_YMD2 = "yyyy-MM-dd";
    /**
     * 格式为【yyyy年MM月dd日】.
     */
    public static final String FORMAT_YEAR_MONTH_DATE = "yyyy年MM月dd日";
    /**
     * 格式为【yyyy年MM月】.
     */
    public static final String FORMAT_YEAR_MONTH = "yyyy年MM月";
    /**
     * 格式为【yyyy年MM月dd日(E)】.
     */
    public static final String FORMAT_YEAR_MONTH_DATE_DAY = "yyyy年MM月dd日(E)";
    
    /**
     * 格式为【yyyy/MM/dd HH:mm:ss】.
     */
    public static final String FORMAT_YYYYMMDDHHSS = "yyyy/MM/dd HH:mm:ss";
    /**
     * 格式为【yyyy-MM-dd HH:mm:ss】.
     */
    public static final String FORMAT_YYYYMMDDHHSS2 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式为【yyyyMMddHHmmss】.
     */
    public static final String FORMAT_YYYYMMDDHHSS3 = "yyyyMMddHHmmss";
    /**
     * 格式为【yyyy/MM/dd HH:mm】.
     */
    public static final String FORMAT_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";
    /**
     * 格式为【yyyy/MM/dd/HH/mm】.
     */
    public static final String FORMAT_YYYYMMDDHHMM2 = "yyyy/MM/dd/HH/mm";
    /**
     * 格式为【HH:mm:ss】.
     */
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    /**
     * 格式为【HH/mm】.
     */
    public static final String FORMAT_HHMM2 = "HH/mm";
    /**
     * 格式为【yyyy年MM月dd日HH:mm:ss】.
     */
    public static final String FORMAT_YEAR_MONTH_DATE_TIME = "yyyy年MM月dd日HH:mm:ss";
    /**
     * 格式为【yyyy年MM月dd日 HH:mm】.
     */
    public static final String FORMAT_YEAR_MONTH_DATE_TIME2 = "yyyy年MM月dd日 HH:mm";
    
    /**
     * 构造函数
     */
    private DateUtils() {
        super();
    }
    
    /**
     * 日期格式化.
     * @param cal 日期
     * @param format 格式
     * @return 格式化后的日期
     */
    public static final String makeFormat(Calendar cal, String format) {
        
        DateFormat df = new SimpleDateFormat(format); 
        String date = df.format(cal.getTime());
        
        return date;
    }
    
    /**
     * 当前时间转换成数字.
     * @param format 格式
     * @return 转化后的时间
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
     * 现在时间以格式化后的形式取得.
     * @param format 格式
     * @return 格式化后的时间
     */
    public static String getNowTime(String format) {
        
        return makeFormat(GregorianCalendar.getInstance(), format);
    }
    
    /**
     * 年月日的格式化.
     * @param year 年
     * @param month 月
     * @param day 日
     * @param format 格式
     * @return 格式化的数据
     */
    public static long getFormatDate(int year, int month, int day, String format) {
        
        final Calendar cal = new GregorianCalendar(year, month - 1, day);
        
        return Long.parseLong(makeFormat(cal, format));
    }
    
    /**
     * 最后一天取得.
     * @param year 年
     * @param month 月
     * @param format 格式
     * @return 最后一天
     */
    public static long getFinalDay(int year, int month, String format) {
        
        final Calendar cal = new GregorianCalendar(year, month - 1, 1);
        
        final int day = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, day);
        
        return Long.parseLong(makeFormat(cal, format));
    }
    
    /**
     * 第一天的星期取得.
     * @param year 年
     * @param month 月
     * @return 第一天的星期
     */
    public static int getfirstDayOfWeek(int year, int month) {
        
        final Calendar cal1 = new GregorianCalendar(year, month - 1, 1);
        
        return cal1.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 最后一天的星期的取得.
     * @param year 年
     * @param month 月
     * @return 最后一天的星期
     */
    public static int getLastDayOfWeek(int year, int month) {
        final Calendar cal1 = new GregorianCalendar(year, month, 1);
        cal1.add(Calendar.DATE, -1);
        return cal1.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 年月日在本月的第几周，星期几的取得.
     * @param ymd 年月日
     * @return 年月日在本月的第几周，星期几
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
     * 年月日在本月的第几周，星期几的取得.
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 年月日在本月的第几周，星期几
     */
    public static int[] getIndexByYmd(int year, int month, int day){
        
        final int[] nRet = new int[2];
        
        final Calendar cal = new GregorianCalendar(year, month - 1, day);
        final int firstday = getfirstDayOfWeek(year, month);
        
        // 月中的第几周
        nRet[0] = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        
        // 星期几
        nRet[1] = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        if (firstday <= nRet[1]) {
            nRet[0] = nRet[0] - 1;
        }
        
        return nRet;
    }
    
    /**
     * 日期以addNum的值以及kind的类型变化.
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 变化后的日期
     */
    public static int[] add(int year, int month, int day, int kind, int addNum){
        
        final int[] nRet = {-1, -1, -1};
        
        if (year != -1) {
            
            final Calendar cal = GregorianCalendar.getInstance();
            // 年
            cal.set(Calendar.YEAR, year);
            // 月
            cal.set(Calendar.MONTH, month - 1);
            // 日
            cal.set(Calendar.DATE, day);
            
            cal.add(kind, addNum);
            
            nRet[0] = cal.get(Calendar.YEAR);
            nRet[1] = cal.get(Calendar.MONTH);        
            nRet[2] = cal.get(Calendar.DATE);
        }
        return nRet;
    }
    
    /**
     * 时间的格式化.
     * @param type HHMM/HHMMSS
     * @param time 时间
     * @return 格式化后的时间
     * @throws RichClientWebException AjaxWebException
     */
    public static String getTimeFormat(int type, int time)
        throws RichClientWebException {
        
        return getTimeFormat(type, String.valueOf(time));
    }
    
    /**
     * 时间的格式化.
     * @param type HH:MM/HH:MM:SS
     * @param hour 小时
     * @param min 分钟
     * @param sec 秒
     * @return 格式化后的时间
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
     * 文字形式的时间转换.
     * @param type HHMM/HHMMSS
     * @param time 时间
     * @return 转换后的时间
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
     * 补全日期后的转换.
     * @param dateValue 日期
     * @param format 格式
     * @return 转换后的日期
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
     * 日期增加addNum天数.
     * @param addNum 增加天数
     * @param format 格式
     * @return 增加后的日期
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
