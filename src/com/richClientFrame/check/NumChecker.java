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
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： NumChecker
 * 类描述 ： 数值验证类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.06.26
 * @author king
 */
public class NumChecker {
    
    /**
     * 构造函数.
     */
    public NumChecker() {
        super();
    }
    
    /**
     * FLOAT型数值验证.
     * @param param request
     * @param fieldId 控件ID
     * @param value 输入值
     * @param isRequired 必须输入项目：true，非必须输入项目：false
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     * @throws Exception
     */
    public CheckInfo checkFloat(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        final CheckInfo checkInfo = new CheckInfo();
        
        // 非必须输入并且没有输入的时候，验证通过
        if (!isRequired && value.length() == 0) {
            return checkInfo;
        }
        
        final ControlErrorMap errMap = ControlErrorMap.getInstance();
        
        // 取得异常类型
        final String[] patternList = errMap.getErrPattern(param, fieldId);
        if (patternList == null || patternList.length % 2 != 0) {
            throw new RichClientWebException("【error.mst】数值(float)验证的配置参数必须成对配置，" 
                    + "对应的【画面ID:" + param.dispCode + "】【控件ID:" + fieldId + "】");
        } else {
            // FLOAT性数值验证
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                result = checkFloatValue(value);
            }
            
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                boolean tempResult = false;
                String strMinValue = ConstantsUtil.Str.EMPTY;
                String strMaxValue = ConstantsUtil.Str.EMPTY;
                for (int i = 0; i < patternList.length; i = i + 2) {
                    // 最小值
                    strMinValue = patternList[i + 0]; 
                    // 最大值
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
     * FLOAT型数值验证.
     * @param value 输入值
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException
     */
    public String checkFloatValue(String value) {
        String result = ConstantsUtil.Check.DEFAULT;
        
        // 验证半角
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
     * 验证FLOAT数值的范围.
     * @param chkValue 输入值
     * @param minValue 范围最小值
     * @param maxValue 范围最大值
     * @return 正常：true，异常：false
     * @throws RichClientWebException RichClientWebException
     */
    public boolean checkFloatRange(float chkValue, String minValue, String maxValue) 
        throws RichClientWebException {
        boolean result = true;
        
        // 逗号去除后，转换数值
        try {
            final float chkMinValue = Float.parseFloat(CommonUtil.removeComma(minValue));
            final float chkMaxValue = Float.parseFloat(CommonUtil.removeComma(maxValue));
            
            if (chkMinValue > chkMaxValue) {
                throw new RichClientWebException("【error.mst】数值最小值【" 
                        + minValue + "】大于最大值【" + maxValue + "】");
            }
            
            if (chkValue < chkMinValue || chkValue > chkMaxValue) {
                result = false;
            }
            
        } catch (NumberFormatException e) {
            throw new RichClientWebException("【error.mst】数值最大值及最小值应为数字，信息为：最大值【" 
                    + minValue + "】，最小值【" + maxValue + "】");
        }                
        
        return result;
    }
    
    /**
     * LONG型数值验证.
     * @param param request
     * @param fieldId 控件ID
     * @param value 输入值
     * @param isRequired 必须输入项目：true，非必须输入项目：false
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    public CheckInfo checkLong(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        final CheckInfo checkInfo = new CheckInfo();
        
        // 非必须输入并且没有输入的时候，验证通过
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
            throw new RichClientWebException("【error.mst】数值(long)验证的配置参数必须成对配置，" 
                    + "对应的【画面ID:" + param.dispCode + "】【控件ID:" + fieldId + "】");
        } else {
            
            // LONG型数值验证
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                result = checkLongValue(value);
            }
            
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                boolean tempResult = false;
                String strMinValue = ConstantsUtil.Str.EMPTY;
                String strMaxValue = ConstantsUtil.Str.EMPTY;
                for (int i = 0; i < patternList.length; i = i + 2) {
                    // 最小值
                    strMinValue = patternList[i + 0];
                    // 最大值
                    strMaxValue = patternList[i + 1];
            
                    // 验证LONG数值的范围
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
     * LONG型数值验证.
     * @param value 输入值
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException
     */
    public String checkLongValue(String value) {
        
        String result = ConstantsUtil.Check.DEFAULT;
        
        // 验证半角
        result = checkNumChar(value);
        
        if (ConstantsUtil.Check.DEFAULT.equals(result)) {
            
            // 去除逗号
            final String chkValue = CommonUtil.removeComma(value);
            
            // Long型数值验证
            try {
                Long.parseLong(chkValue);
            } catch (NumberFormatException e) {
                result = ConstantsUtil.Check.INPUT_ERROR;
            }
        }
        
        return result;
    }
    
    /**
     * 验证LONG数值的范围.
     * @param chkValue 输入值
     * @param minValue 范围最小值
     * @param maxValue 范围最大值
     * @return 正常：true，异常：false
     * @throws RichClientWebException RichClientWebException
     */
    public boolean checkLongRange(long chkValue, String minValue, String maxValue) 
        throws RichClientWebException {
        boolean result = true;
        
        // 逗号去除后，转换数值
        try {
            final long chkMinValue = Long.parseLong(CommonUtil.removeComma(minValue));
            final long chkMaxValue = Long.parseLong(CommonUtil.removeComma(maxValue));
            
            if (chkMinValue > chkMaxValue) {
                throw new RichClientWebException("【error.mst】最小值【" 
                        + minValue + "】大于最大值【" + maxValue + "】");
            }
            
            if (chkValue < chkMinValue || chkValue > chkMaxValue) {
                result = false;
            }
            
        } catch (NumberFormatException e) {
            throw new RichClientWebException("【error.mst】最小值【" 
                    + minValue + "】或者最大值【" + maxValue + "】不为数值");
        }
        
        return result;
        
    }
    
    /**
     * 验证输入数据是否为半角，非半角为异常
     * @param value 输入值
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
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
