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
import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� TimeChecker
 * ������ �� ʱ����֤��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.06.26
 * @author king
 */
public class TimeChecker {
    
    /**
     * ��֤��ʽ��ʱ�֣�.
     */
    public static final int TYPE_HHMM = 1;
    
    /**
     * ��֤��ʽ��ʱ���룩.
     */
    public static final int TYPE_HHMMSS = 2;

    /**
     * ���캯��.
     */
    public TimeChecker() {
        super();
    }
    
    /**
     * ʱ��������֤.
     * @param param request
     * @param fieldId �ؼ�ID
     * @param value ����ֵ
     * @param isRequired ����������Ŀ��true���Ǳ���������Ŀ��false
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     * @throws Exception
     */ 
    public CheckInfo checkTime(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        final CheckInfo checkInfo = new CheckInfo();
                
        ControlErrorMap errMap = null;
        errMap = ControlErrorMap.getInstance();
        
        // ��֤����
        String strCheckType = "";
        // ��ʼʱ��
        String strStartTime = "";
        // ����ʱ��
        String strEndTime = "";
        // ȡ���쳣����
        final String[] patternList = errMap.getErrPattern(param, fieldId);
        if (patternList == null || patternList.length < 3) {
            throw new RichClientWebException("��error.mst��ʱ��������֤����Ҫ����3����������ϢΪ" 
                    + "������ID:" + param.dispCode + " �ؼ�ID:" + fieldId + "��");
        } else {
            try {
                strCheckType = patternList[0];
                final int checkType = Integer.parseInt(strCheckType);
                strStartTime = patternList[1];
                strEndTime = patternList[2];
                
                switch (checkType) {
                    case TYPE_HHMM:
                        // �Ǳ������벢��û�������ʱ����֤ͨ��
                        if (!isRequired && value.equals(ConstantsUtil.Default.HHMM_NOTHING)) {
                            return checkInfo;
                            // �������벢��û�������ʱ���쳣
                        } else if (isRequired && value.equals(ConstantsUtil.Default.HHMM_NOTHING)) {
                            checkInfo.setDetailCode(ConstantsUtil.Check.NOTHING_INPUT);
                            return checkInfo;
                        }
                    case TYPE_HHMMSS:
                        // �Ǳ������벢��û�������ʱ����֤ͨ��
                        if (!isRequired && value.equals(ConstantsUtil.Default.HHMMSS_NOTHING)) {
                            return checkInfo;
                            // �������벢��û�������ʱ���쳣
                        } else if (isRequired && value.equals(ConstantsUtil.Default.HHMMSS_NOTHING)) {
                            checkInfo.setDetailCode(ConstantsUtil.Check.NOTHING_INPUT);
                            return checkInfo;
                        }
                    default:
                }
                
                // ʱ��������֤
                result = checkTimeFormat(value, checkType);
                
                // ʱ�䷶Χ��֤
                if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                    // ð��ȥ��
                    final String chkValue = CommonUtil.removeColon(value);
                    result = checkTimeRange(Integer.parseInt(chkValue), strStartTime, strEndTime);
                    if (ConstantsUtil.Check.RANGE_ERROR.equals(result)) {
                        final List<String> pattern = new ArrayList<String>();
                        pattern.add(strStartTime);
                        pattern.add(strEndTime);
                        checkInfo.setPattern(pattern);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RichClientWebException("[FILE ERROR] Use numbers for a " 
                        + "TIME check pattern. ( dispcode:" 
                        + param.dispCode + " field:" + fieldId + " )");
            }
        }
        checkInfo.setDetailCode(result);
        return checkInfo;
        
    }
    
    /**
     * ʱ���ʽ��֤.
     * @param value ��HH:MM����ʽ���ߡ�HH:MM:SS����ʽ
     * @param type ��֤����
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    public String checkTimeFormat(String value, int type) 
        throws RichClientWebException {
        
        String result = ConstantsUtil.Check.DEFAULT;
        
        switch (type) {
            case TYPE_HHMM:
                if (value.length() != 5) {
                    return ConstantsUtil.Check.INPUT_ERROR;
                }
                result = checkHHMMFormat(value);
                break;
            case TYPE_HHMMSS:
                if (value.length() != 8) {
                    return ConstantsUtil.Check.INPUT_ERROR;
                }
                result = checkHHMMSSFormat(value);
                break;
            default:
        }
        
        return result;
    }
    
    /**
     * ʱ���ʽ��֤
     * @param value ��HH:MM����ʽ
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    private String checkHHMMFormat(String value) 
        throws RichClientWebException {
        final String result = ConstantsUtil.Check.DEFAULT;
        
        try {
            final String[] hhmmssList = value.split(":");
            if (hhmmssList == null || hhmmssList.length != 2) {
                return ConstantsUtil.Check.INPUT_ERROR;
            }
            
            final int hh = Integer.parseInt(hhmmssList[0]);
            final int mm = Integer.parseInt(hhmmssList[1]);
            
            if (hh < 0 || 24 < hh) {
                return ConstantsUtil.Check.INPUT_ERROR;
            } else if (mm < 0 || 60 <= mm) {
                return ConstantsUtil.Check.INPUT_ERROR;
            }
            
        } catch (NumberFormatException e) {
            return ConstantsUtil.Check.INPUT_ERROR;
        }
        
        return result;
    }
    
    /**
     * ʱ���ʽ��֤
     * @param value ��HH:MM:SS����ʽ
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    private String checkHHMMSSFormat(String value) 
        throws RichClientWebException {
        final String result = ConstantsUtil.Check.DEFAULT;
        
        try {
            final String[] hhmmssList = value.split(":");
            if (hhmmssList == null || hhmmssList.length != 3) {
                return ConstantsUtil.Check.INPUT_ERROR;
            }
            
            final int hh = Integer.parseInt(hhmmssList[0]);
            final int mm = Integer.parseInt(hhmmssList[1]);
            final int ss = Integer.parseInt(hhmmssList[2]);
            
            if (hh < 0 || 24 < hh) {
                return ConstantsUtil.Check.INPUT_ERROR;
            } else if (mm < 0 || 60 <= mm) {
                return ConstantsUtil.Check.INPUT_ERROR;
            } else if (ss < 0 || 60 <= ss) {
                return ConstantsUtil.Check.INPUT_ERROR;
            }
            
        } catch (NumberFormatException e) {
            return ConstantsUtil.Check.INPUT_ERROR;
        }
        
        return result;
    }
    
    /**
     * ʱ�䷶Χ��֤.
     * @param intValue ����ʱ�䡾HH:MM����ʽ���ߡ�HH:MM:SS����ʽ
     * @param start ��ʼʱ��
     * @param end ����ʱ��
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    public String checkTimeRange(int intValue, String start, String end) 
        throws RichClientWebException {
        final String result = ConstantsUtil.Check.DEFAULT;
        
        try {
            final int intStart = Integer.parseInt(start);
            final int intEnd = Integer.parseInt(end);
        
            if (intStart > intEnd) {
                throw new RichClientWebException("The start time ( " 
                        + start + " ) is after the end time ( " + end + " ).");
            }
        
            if (intValue < intStart || intValue > intEnd) {
                return ConstantsUtil.Check.RANGE_ERROR;
            }    
        } catch (NumberFormatException nfe) {
            throw new RichClientWebException("The start time ( " 
                    + start + " ) or the end time ( " + end + " ) is not a number.");
        }    
        
        return result;
    }

    /**
     * ��ʼʱ�������ʱ���С��֤.
     * @param startTime ��ʼʱ��
     * @param endTime ����ʱ��
     * @param equalFlg ������ȱ�ʶ
     * @return result ��������
     * @throws RichClientWebException RichClientWebException
     */
    public String timeScopeCheck(String startTime, String endTime, boolean equalFlg) 
        throws RichClientWebException {
        
        String result = ConstantsUtil.Check.DEFAULT;
        
        try {
            
            // ��ʼʱ��
            final long intStart = Long.parseLong(startTime);
            
            // ����ʱ��
            final long intEnd = Long.parseLong(endTime);
        
            // ʱ��Ƚ�
            if (equalFlg) {
                // ��ʼʱ��<=����ʱ��
                if (intStart > intEnd) {
                    result = ConstantsUtil.Check.TIME_DISORDER;
                }
            } else {
                // ��ʼʱ��<����ʱ��
                if (intStart >= intEnd) {
                    result = ConstantsUtil.Check.TIME_DISORDER;
                }
            }
        } catch (NumberFormatException nfe) {
            throw new RichClientWebException("The start time ( " 
                    + startTime + " ) or the end time ( " + endTime + " ) is not a number.");
        }    
        
        return result;
    }

}
