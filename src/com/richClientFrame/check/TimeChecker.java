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
 * 类名称 ： TimeChecker
 * 类描述 ： 时间验证类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.06.26
 * @author king
 */
public class TimeChecker {
    
    /**
     * 验证格式（时分）.
     */
    public static final int TYPE_HHMM = 1;
    
    /**
     * 验证格式（时分秒）.
     */
    public static final int TYPE_HHMMSS = 2;

    /**
     * 构造函数.
     */
    public TimeChecker() {
        super();
    }
    
    /**
     * 时间类型验证.
     * @param param request
     * @param fieldId 控件ID
     * @param value 输入值
     * @param isRequired 必须输入项目：true，非必须输入项目：false
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     * @throws Exception
     */ 
    public CheckInfo checkTime(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        final CheckInfo checkInfo = new CheckInfo();
                
        ControlErrorMap errMap = null;
        errMap = ControlErrorMap.getInstance();
        
        // 验证类型
        String strCheckType = "";
        // 开始时间
        String strStartTime = "";
        // 结束时间
        String strEndTime = "";
        // 取得异常类型
        final String[] patternList = errMap.getErrPattern(param, fieldId);
        if (patternList == null || patternList.length < 3) {
            throw new RichClientWebException("【error.mst】时间类型验证至少要配置3个参数，信息为" 
                    + "【画面ID:" + param.dispCode + " 控件ID:" + fieldId + "】");
        } else {
            try {
                strCheckType = patternList[0];
                final int checkType = Integer.parseInt(strCheckType);
                strStartTime = patternList[1];
                strEndTime = patternList[2];
                
                switch (checkType) {
                    case TYPE_HHMM:
                        // 非必须输入并且没有输入的时候，验证通过
                        if (!isRequired && value.equals(ConstantsUtil.Default.HHMM_NOTHING)) {
                            return checkInfo;
                            // 必须输入并且没有输入的时候，异常
                        } else if (isRequired && value.equals(ConstantsUtil.Default.HHMM_NOTHING)) {
                            checkInfo.setDetailCode(ConstantsUtil.Check.NOTHING_INPUT);
                            return checkInfo;
                        }
                    case TYPE_HHMMSS:
                        // 非必须输入并且没有输入的时候，验证通过
                        if (!isRequired && value.equals(ConstantsUtil.Default.HHMMSS_NOTHING)) {
                            return checkInfo;
                            // 必须输入并且没有输入的时候，异常
                        } else if (isRequired && value.equals(ConstantsUtil.Default.HHMMSS_NOTHING)) {
                            checkInfo.setDetailCode(ConstantsUtil.Check.NOTHING_INPUT);
                            return checkInfo;
                        }
                    default:
                }
                
                // 时间类型验证
                result = checkTimeFormat(value, checkType);
                
                // 时间范围验证
                if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                    // 冒号去除
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
     * 时间格式验证.
     * @param value 【HH:MM】格式或者【HH:MM:SS】形式
     * @param type 验证类型
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
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
     * 时间格式验证
     * @param value 【HH:MM】格式
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
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
     * 时间格式验证
     * @param value 【HH:MM:SS】格式
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
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
     * 时间范围验证.
     * @param intValue 输入时间【HH:MM】格式或者【HH:MM:SS】形式
     * @param start 开始时间
     * @param end 结束时间
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
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
     * 开始时间与结束时间大小验证.
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param equalFlg 可以相等标识
     * @return result 错误类型
     * @throws RichClientWebException RichClientWebException
     */
    public String timeScopeCheck(String startTime, String endTime, boolean equalFlg) 
        throws RichClientWebException {
        
        String result = ConstantsUtil.Check.DEFAULT;
        
        try {
            
            // 开始时间
            final long intStart = Long.parseLong(startTime);
            
            // 结束时间
            final long intEnd = Long.parseLong(endTime);
        
            // 时间比较
            if (equalFlg) {
                // 开始时间<=结束时间
                if (intStart > intEnd) {
                    result = ConstantsUtil.Check.TIME_DISORDER;
                }
            } else {
                // 开始时间<结束时间
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
