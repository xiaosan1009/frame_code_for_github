/**
 * 
 */
package com.richClientFrame.check;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.CheckInfo;
import com.richClientFrame.info.ControlErrorMap;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� DateChecker
 * ������ �� ������֤��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.06.26
 * @author king
 */
public class DateChecker {

    /**
     * ��֤���ͣ����գ�.
     */
    public static final int TYPE_MMDD = 1;

    /**
     * ��֤���ͣ������գ�.
     */
    public static final int TYPE_YYYYMMDD = 2;

    /**
     * ���캯��.
     */
    public DateChecker() {
        super();
    }
    
    /**
     * ������֤����.
     * 
     * @param param reqeust
     * @param fieldId ��ĿID
     * @param value �����ֵ
     * @param isRequired �Ƿ�Ϊ����������  ���룺true�� �Ǳ��룺false
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    public CheckInfo checkDate(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        
        String result = ConstantsUtil.Check.DEFAULT;
        
        final CheckInfo checkInfo = new CheckInfo();
        
        // �Ǳ������벢��û�������ʱ����֤ͨ��
        if (!isRequired && value.length() == 0) {
            return checkInfo;
        }

        final ControlErrorMap errMap = ControlErrorMap.getInstance();

        // ȡ���쳣����
        final String[] patternList = errMap.getErrPattern(param, fieldId);
        if (patternList == null || patternList.length < 2) {
            throw new RichClientWebException("[error.mst]����������֤����Ҫ����������������ϢΪ" 
                    + "[dispcode:" + param.dispCode + " field:" + fieldId + "]");
        } else {
            try {
                // ��֤����
                final String strCheckType = patternList[0];
                final int checkType = Integer.parseInt(strCheckType);
                final String sign = patternList[1];
                // ��ʼ����
                String strStartDate = null;
                String strEndDate = null;
                if (patternList.length == 4) {
                    strStartDate = patternList[2];
                    strEndDate = patternList[3];
                }
                // ��������
                
                // �Ǳ������벢������Ϊ[----/--/--]��[--/--]��ʱ����֤ͨ��
                if  (!isRequired) {
                    switch (checkType) {
                        case TYPE_MMDD:
                            if (ConstantsUtil.Default.MMDD_NOTHING.equals(value)) {
                                return checkInfo;
                            }
                            break;
                        case TYPE_YYYYMMDD:
                            if (ConstantsUtil.Default.YYYYMMDD_NOTHING.equals(value)) {
                                return checkInfo;
                            }                            
                            break;
                        default:
                            throw new RichClientWebException(
                                    "[error.mst]��������[" + checkType + "]���ô���,û�д��������ͣ�");
                    }
                // �������벢��û�������������Ϊ[----/--/--]��[--/--]��ʱ����֤ʧ��
                } else if (isRequired) {
                    if (value.length() == 0) {
                        checkInfo.setDetailCode(ConstantsUtil.Check.NOTHING_INPUT);
                        return checkInfo;
                    }                    
                    switch (checkType) {
                        case TYPE_MMDD:
                            if (ConstantsUtil.Default.MMDD_NOTHING.equals(value)) {
                                checkInfo.setDetailCode(ConstantsUtil.Check.NOTHING_INPUT);
                                return checkInfo;
                            }
                            break;
                        case TYPE_YYYYMMDD:
                            if (ConstantsUtil.Default.YYYYMMDD_NOTHING.equals(value)) {
                                checkInfo.setDetailCode(ConstantsUtil.Check.NOTHING_INPUT);
                                return checkInfo;
                            }
                            break;
                        default:
                            throw new RichClientWebException(
                                    "[error.mst]��������[" + checkType + "]���ô���,û�д��������ͣ�");
                    }
                    
                }
                
                // ȥ���ָ���
                if (CommonUtil.isNotEmpty(value)) {
                    value = parseDate(value, sign);
                }

                // ��֤���ڸ�ʽ
                result = checkDateFormat(value, checkType);
                
                if (CommonUtil.isNotEmpty(strStartDate) 
                        && !ConstantsUtil.Check.DEFAULT.equals(checkDateFormat(strStartDate, checkType))) {
                    throw new RichClientWebException("[error.mst]��ʼ����[" 
                            + strStartDate + "]�ĸ�ʽ����ȷ,Ӧ��Ϊ���ڸ�ʽ������Ϊ[dispcode:" 
                            + param.dispCode + " field:" + fieldId + "]");
                }
                if (CommonUtil.isNotEmpty(strEndDate) 
                        && !ConstantsUtil.Check.DEFAULT.equals(checkDateFormat(strEndDate, checkType))) {
                    throw new RichClientWebException("[error.mst]��������[" 
                            + strEndDate + "]�ĸ�ʽ����ȷ,Ӧ��Ϊ���ڸ�ʽ������Ϊ[dispcode:" 
                            + param.dispCode + " field:" + fieldId + "]");
                }

                // ��֤���ڷ�Χ�Ƿ���Ч
                if (CommonUtil.isNotEmpty(strStartDate) 
                        && CommonUtil.isNotEmpty(strEndDate) 
                        && ConstantsUtil.Check.DEFAULT.equals(result)) {
                    result = checkDateRange(Integer.parseInt(value), strStartDate, strEndDate);
                    if (ConstantsUtil.Check.RANGE_ERROR.equals(result)) {
                        final List<String> pattern = new ArrayList<String>();
                        pattern.add(strStartDate);
                        pattern.add(strEndDate);
                        checkInfo.setPattern(pattern);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RichClientWebException("[error.mst]������֤���ڵ����ݸ�ʽ�����⣬��Ҫ��ֵ����" 
                        + "��Ӧ�Ĳ���Ϊ��[dispcode:" 
                        + param.dispCode + " field:" + fieldId + "]");
            }
        }
        
        checkInfo.setDetailCode(result);
        return checkInfo;
    }

    /**
     * ��֤���ڸ�ʽ.
     * 
     * @param value YYYYMMDD��ʽ/MMDD��ʽ:[/]ȥ��
     * @param type  ������ʽ(YYYYMMDD����MMDD)
     * @return ������0������0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    private String checkDateFormat(String value, int type) 
        throws RichClientWebException {
        
        String result = ConstantsUtil.Check.DEFAULT;

        // ��֤�Ƿ�Ϊ����
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return ConstantsUtil.Check.INPUT_ERROR;
        }

        switch (type) {
            case TYPE_MMDD:
                result = checkMDFormat(value);
                break;
            case TYPE_YYYYMMDD:
                result = checkYMDFormat(value);
                break;
            default:
                throw new RichClientWebException("There is no such pattern for "
                        + "a date format type. ( " + type + " )");
        }
        return result;
    }

    /**
     * ������������
     * 
     * @param value ��������
     * @param sign �ָ���
     * @return ��������������
     * @throws RichClientWebException RichClientWebException
     */
    private String parseDate(String value, String sign) throws RichClientWebException {
        if (CommonUtil.isEmpty(sign)) {
            return value;
        }
        String checkValue = ConstantsUtil.Str.EMPTY;
        if (ConstantsUtil.Str.LINE.equals(sign)) {
            checkValue = CommonUtil.removeLine(value);
        } else if (ConstantsUtil.Str.SLASH.equals(sign)) {
            checkValue = CommonUtil.removeSlash(value);
        } else if (ConstantsUtil.Str.STAR.equals(sign)) {
            checkValue = CommonUtil.removeAllChar(value);
        } else {
            throw new RichClientWebException("[error.mst]û�д˷ָ�����" 
                    + "��Ӧ�Ĳ���Ϊ��[��������:" + value + " �ָ���:" + sign + "]");
        }
        return checkValue;
    }
    
    /**
     * ��֤���ڸ�ʽ�����ԣ�YYYYMMDD��
     * 
     * @param value ����ֵ
     * @return ������0������0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    private String checkYMDFormat(String value) throws RichClientWebException {
        
        String result = ConstantsUtil.Check.DEFAULT;
        
        if (value.length() != 8) {
            return ConstantsUtil.Check.INPUT_ERROR;
        }

        try {
            final int year = Integer.parseInt(value.substring(0, 4));
            final int month = Integer.parseInt(value.substring(4, 6));
            final int date = Integer.parseInt(value.substring(6, 8));
            
            result = checkValidDate(year, month, date);

        } catch (IndexOutOfBoundsException e) {
            return ConstantsUtil.Check.INPUT_ERROR;
        }

        return result;
    }

    /**
     * ��֤���ڸ�ʽ�����ԣ�MMDD��
     * 
     * @param value ����ֵ
     * @return ������0������0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    private String checkMDFormat(String value) 
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        if (value.length() != 4) {
            return ConstantsUtil.Check.INPUT_ERROR;
        }

        try {
            // 2/29����Ч
            final int year = 2008;
            final int month = Integer.parseInt(value.substring(0, 2));
            final int date = Integer.parseInt(value.substring(2, 4));
            
            result = checkValidDate(year, month, date);

        } catch (IndexOutOfBoundsException e) {
            return ConstantsUtil.Check.INPUT_ERROR;
        }

        return result;
    }
    
    /**
     * ��֤���ڸ�ʽ�Ƿ���Ч.
     * @param year ��
     * @param month ��
     * @param day ��
     * @return ������0������0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    private String checkValidDate(int year, int month, int day) 
        throws RichClientWebException {
        final String result = ConstantsUtil.Check.DEFAULT;
        
        final Calendar cal = new GregorianCalendar();
        cal.setLenient(false);
        cal.set(year, month - 1, day);
              
        try {
            cal.getTime();
        } catch (IllegalArgumentException iae) {
            return ConstantsUtil.Check.INPUT_ERROR;
        }
        
        
        return result;
        
    }

    /**
     * ��֤���ڷ�Χ�Ƿ���Ч.
     * 
     * @param intValue ����ֵ
     * @param start ��Χ��ʼ����
     * @param end ��Χ����ʱ��
     * @return ������0������0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    private String checkDateRange(int intValue, String start, String end) 
        throws RichClientWebException {
        final String result = ConstantsUtil.Check.DEFAULT;
        
        try {
            final int intStart = Integer.parseInt(start);
            final int intEnd = Integer.parseInt(end);
            
            if (intStart > intEnd) {
                throw new RichClientWebException("[error.mst]��ʼ����[" 
                        + start + "]���ڽ�������[" + end + "]");
            }
            if (intValue < intStart || intValue > intEnd) {
                return ConstantsUtil.Check.RANGE_ERROR;
            }
        } catch (NumberFormatException nfe) {
            throw new RichClientWebException("[error.mst]������֤���ڵ����ݸ�ʽ�����⣬��Ҫ��ֵ����" 
                    + "��Ӧ�Ĳ���Ϊ����ʼ����[" + start + "]����������[" + end + "]");
        }
        
        return result;
    }

}
