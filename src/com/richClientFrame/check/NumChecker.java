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
 * ������ �� NumChecker
 * ������ �� ��ֵ��֤��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.06.26
 * @author king
 */
public class NumChecker {
    
    /**
     * ���캯��.
     */
    public NumChecker() {
        super();
    }
    
    /**
     * FLOAT����ֵ��֤.
     * @param param request
     * @param fieldId �ؼ�ID
     * @param value ����ֵ
     * @param isRequired ����������Ŀ��true���Ǳ���������Ŀ��false
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     * @throws Exception
     */
    public CheckInfo checkFloat(Param param, String fieldId, String value, boolean isRequired)
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
        if (patternList == null || patternList.length % 2 != 0) {
            throw new RichClientWebException("��error.mst����ֵ(float)��֤�����ò�������ɶ����ã�" 
                    + "��Ӧ�ġ�����ID:" + param.dispCode + "�����ؼ�ID:" + fieldId + "��");
        } else {
            // FLOAT����ֵ��֤
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                result = checkFloatValue(value);
            }
            
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                boolean tempResult = false;
                String strMinValue = ConstantsUtil.Str.EMPTY;
                String strMaxValue = ConstantsUtil.Str.EMPTY;
                for (int i = 0; i < patternList.length; i = i + 2) {
                    // ��Сֵ
                    strMinValue = patternList[i + 0]; 
                    // ���ֵ
                    strMaxValue = patternList[i + 1];
            
                    if (!tempResult) {
                        tempResult = checkFloatRange(Float.parseFloat(
                                CommonUtil.removeComma(value)), strMinValue, strMaxValue);
                    }
                    if (tempResult) {
                        break;
                    }
                }
                if (!tempResult) {
                    result = ConstantsUtil.Check.RANGE_ERROR;
                    final List<String> pattern = new ArrayList<String>();
                    pattern.add(strMinValue);
                    pattern.add(strMaxValue);
                    checkInfo.setPattern(pattern);
                }
            }
        }
        checkInfo.setDetailCode(result);
        return checkInfo;
    }
    
    /**
     * FLOAT����ֵ��֤.
     * @param value ����ֵ
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException
     */
    public String checkFloatValue(String value) {
        String result = ConstantsUtil.Check.DEFAULT;
        
        // ��֤���
        result = checkNumChar(value);
        
        if (ConstantsUtil.Check.DEFAULT.equals(result)) {
            final String chkValue = CommonUtil.removeComma(value);
            
            try {
                Float.parseFloat(chkValue);
            } catch (NumberFormatException e) {
                result = ConstantsUtil.Check.INPUT_ERROR;
            }
        }
        
        return result;
    }
    
    /**
     * ��֤FLOAT��ֵ�ķ�Χ.
     * @param chkValue ����ֵ
     * @param minValue ��Χ��Сֵ
     * @param maxValue ��Χ���ֵ
     * @return ������true���쳣��false
     * @throws RichClientWebException RichClientWebException
     */
    public boolean checkFloatRange(float chkValue, String minValue, String maxValue) 
        throws RichClientWebException {
        boolean result = true;
        
        // ����ȥ����ת����ֵ
        try {
            final float chkMinValue = Float.parseFloat(CommonUtil.removeComma(minValue));
            final float chkMaxValue = Float.parseFloat(CommonUtil.removeComma(maxValue));
            
            if (chkMinValue > chkMaxValue) {
                throw new RichClientWebException("��error.mst����ֵ��Сֵ��" 
                        + minValue + "���������ֵ��" + maxValue + "��");
            }
            
            if (chkValue < chkMinValue || chkValue > chkMaxValue) {
                result = false;
            }
            
        } catch (NumberFormatException e) {
            throw new RichClientWebException("��error.mst����ֵ���ֵ����СֵӦΪ���֣���ϢΪ�����ֵ��" 
                    + minValue + "������Сֵ��" + maxValue + "��");
        }                
        
        return result;
    }
    
    /**
     * LONG����ֵ��֤.
     * @param param request
     * @param fieldId �ؼ�ID
     * @param value ����ֵ
     * @param isRequired ����������Ŀ��true���Ǳ���������Ŀ��false
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    public CheckInfo checkLong(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        final CheckInfo checkInfo = new CheckInfo();
        
        // �Ǳ������벢��û�������ʱ����֤ͨ��
        if (!isRequired && value.length() == 0) {
            return checkInfo;
        }
        
        ControlErrorMap errMap = null;
        try {
            errMap = ControlErrorMap.getInstance();
        } catch (Exception e) {
            throw new RichClientWebException(
                    "Failed to get ControlErrorMap instance." + e.toString());
        }
        
        final String[] patternList = errMap.getErrPattern(param, fieldId);
        if (patternList == null || patternList.length % 2 != 0) {
            throw new RichClientWebException("��error.mst����ֵ(long)��֤�����ò�������ɶ����ã�" 
                    + "��Ӧ�ġ�����ID:" + param.dispCode + "�����ؼ�ID:" + fieldId + "��");
        } else {
            
            // LONG����ֵ��֤
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                result = checkLongValue(value);
            }
            
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                boolean tempResult = false;
                String strMinValue = ConstantsUtil.Str.EMPTY;
                String strMaxValue = ConstantsUtil.Str.EMPTY;
                for (int i = 0; i < patternList.length; i = i + 2) {
                    // ��Сֵ
                    strMinValue = patternList[i + 0];
                    // ���ֵ
                    strMaxValue = patternList[i + 1];
            
                    // ��֤LONG��ֵ�ķ�Χ
                    if (!tempResult) {
                        tempResult = checkLongRange(Long.parseLong(CommonUtil.removeComma(value)), 
                                strMinValue, strMaxValue);
                    }
                    if (tempResult) {
                        break;
                    }
                }
                if (!tempResult) {
                    result = ConstantsUtil.Check.RANGE_ERROR;
                    final List<String> pattern = new ArrayList<String>();
                    pattern.add(strMinValue);
                    pattern.add(strMaxValue);
                    checkInfo.setPattern(pattern);
                }
            }
        }
        checkInfo.setDetailCode(result);
        return checkInfo;
    }
    
    /**
     * LONG����ֵ��֤.
     * @param value ����ֵ
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException
     */
    public String checkLongValue(String value) {
        
        String result = ConstantsUtil.Check.DEFAULT;
        
        // ��֤���
        result = checkNumChar(value);
        
        if (ConstantsUtil.Check.DEFAULT.equals(result)) {
            
            // ȥ������
            final String chkValue = CommonUtil.removeComma(value);
            
            // Long����ֵ��֤
            try {
                Long.parseLong(chkValue);
            } catch (NumberFormatException e) {
                result = ConstantsUtil.Check.INPUT_ERROR;
            }
        }
        
        return result;
    }
    
    /**
     * ��֤LONG��ֵ�ķ�Χ.
     * @param chkValue ����ֵ
     * @param minValue ��Χ��Сֵ
     * @param maxValue ��Χ���ֵ
     * @return ������true���쳣��false
     * @throws RichClientWebException RichClientWebException
     */
    public boolean checkLongRange(long chkValue, String minValue, String maxValue) 
        throws RichClientWebException {
        boolean result = true;
        
        // ����ȥ����ת����ֵ
        try {
            final long chkMinValue = Long.parseLong(CommonUtil.removeComma(minValue));
            final long chkMaxValue = Long.parseLong(CommonUtil.removeComma(maxValue));
            
            if (chkMinValue > chkMaxValue) {
                throw new RichClientWebException("��error.mst����Сֵ��" 
                        + minValue + "���������ֵ��" + maxValue + "��");
            }
            
            if (chkValue < chkMinValue || chkValue > chkMaxValue) {
                result = false;
            }
            
        } catch (NumberFormatException e) {
            throw new RichClientWebException("��error.mst����Сֵ��" 
                    + minValue + "���������ֵ��" + maxValue + "����Ϊ��ֵ");
        }
        
        return result;
        
    }
    
    /**
     * ��֤���������Ƿ�Ϊ��ǣ��ǰ��Ϊ�쳣
     * @param value ����ֵ
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException
     */
    private String checkNumChar(String value) {
        String result = ConstantsUtil.Check.DEFAULT;
        
        for (int idx = 0; idx < value.length(); idx++) {
            
            final char c = value.charAt(idx);
            if ((c < '0' || c > '9') && (c != '.' && c != '-')) {
                result = ConstantsUtil.Check.INPUT_ERROR;
                break;
            }
        }
        
        return result;
    }
    
    
}
