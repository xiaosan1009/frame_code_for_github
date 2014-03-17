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
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： DateChecker
 * 类描述 ： 日期验证类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.06.26
 * @author king
 */
public class DateChecker {

    /**
     * 验证类型（月日）.
     */
    public static final int TYPE_MMDD = 1;

    /**
     * 验证类型（年月日）.
     */
    public static final int TYPE_YYYYMMDD = 2;

    /**
     * 构造函数.
     */
    public DateChecker() {
        super();
    }
    
    /**
     * 日期验证处理.
     * 
     * @param param reqeust
     * @param fieldId 项目ID
     * @param value 输入的值
     * @param isRequired 是否为必须输入项  必须：true， 非必须：false
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    public CheckInfo checkDate(Param param, String fieldId, String value, boolean isRequired)
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
        if (patternList == null || patternList.length < 2) {
            throw new RichClientWebException("[error.mst]日期类型验证至少要配置两个参数，信息为" 
                    + "[dispcode:" + param.dispCode + " field:" + fieldId + "]");
        } else {
            try {
                // 验证种类
                final String strCheckType = patternList[0];
                final int checkType = Integer.parseInt(strCheckType);
                final String sign = patternList[1];
                // 开始日期
                String strStartDate = null;
                String strEndDate = null;
                if (patternList.length == 4) {
                    strStartDate = patternList[2];
                    strEndDate = patternList[3];
                }
                // 结束日期
                
                // 非必须输入并且输入为[----/--/--]或[--/--]的时候，验证通过
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
                                    "[error.mst]日期类型[" + checkType + "]设置错误,没有此日期类型！");
                    }
                // 必须输入并且没有输入或者输入为[----/--/--]或[--/--]的时候，验证失败
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
                                    "[error.mst]日期类型[" + checkType + "]设置错误,没有此日期类型！");
                    }
                    
                }
                
                // 去除分隔符
                if (CommonUtil.isNotEmpty(value)) {
                    value = parseDate(value, sign);
                }

                // 验证日期格式
                result = checkDateFormat(value, checkType);
                
                if (CommonUtil.isNotEmpty(strStartDate) 
                        && !ConstantsUtil.Check.DEFAULT.equals(checkDateFormat(strStartDate, checkType))) {
                    throw new RichClientWebException("[error.mst]开始日期[" 
                            + strStartDate + "]的格式不正确,应该为日期格式，参数为[dispcode:" 
                            + param.dispCode + " field:" + fieldId + "]");
                }
                if (CommonUtil.isNotEmpty(strEndDate) 
                        && !ConstantsUtil.Check.DEFAULT.equals(checkDateFormat(strEndDate, checkType))) {
                    throw new RichClientWebException("[error.mst]结束日期[" 
                            + strEndDate + "]的格式不正确,应该为日期格式，参数为[dispcode:" 
                            + param.dispCode + " field:" + fieldId + "]");
                }

                // 验证日期范围是否有效
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
                throw new RichClientWebException("[error.mst]配置验证日期的数据格式有问题，需要数值类型" 
                        + "对应的参数为：[dispcode:" 
                        + param.dispCode + " field:" + fieldId + "]");
            }
        }
        
        checkInfo.setDetailCode(result);
        return checkInfo;
    }

    /**
     * 验证日期格式.
     * 
     * @param value YYYYMMDD形式/MMDD形式:[/]去除
     * @param type  日期样式(YYYYMMDD或者MMDD)
     * @return 正常：0，错误：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    private String checkDateFormat(String value, int type) 
        throws RichClientWebException {
        
        String result = ConstantsUtil.Check.DEFAULT;

        // 验证是否为数字
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
     * 处理日期数据
     * 
     * @param value 日期数据
     * @param sign 分隔符
     * @return 处理后的日期数据
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
            throw new RichClientWebException("[error.mst]没有此分隔符。" 
                    + "对应的参数为：[日期数据:" + value + " 分隔符:" + sign + "]");
        }
        return checkValue;
    }
    
    /**
     * 验证日期格式完整性（YYYYMMDD）
     * 
     * @param value 输入值
     * @return 正常：0，错误：0以外的数（在ResponseTab中有详细的定义）
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
     * 验证日期格式完整性（MMDD）
     * 
     * @param value 输入值
     * @return 正常：0，错误：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    private String checkMDFormat(String value) 
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        if (value.length() != 4) {
            return ConstantsUtil.Check.INPUT_ERROR;
        }

        try {
            // 2/29日有效
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
     * 验证日期格式是否有效.
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 正常：0，错误：0以外的数（在ResponseTab中有详细的定义）
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
     * 验证日期范围是否有效.
     * 
     * @param intValue 输入值
     * @param start 范围开始日期
     * @param end 范围结束时间
     * @return 正常：0，错误：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    private String checkDateRange(int intValue, String start, String end) 
        throws RichClientWebException {
        final String result = ConstantsUtil.Check.DEFAULT;
        
        try {
            final int intStart = Integer.parseInt(start);
            final int intEnd = Integer.parseInt(end);
            
            if (intStart > intEnd) {
                throw new RichClientWebException("[error.mst]开始日期[" 
                        + start + "]大于结束日期[" + end + "]");
            }
            if (intValue < intStart || intValue > intEnd) {
                return ConstantsUtil.Check.RANGE_ERROR;
            }
        } catch (NumberFormatException nfe) {
            throw new RichClientWebException("[error.mst]配置验证日期的数据格式有问题，需要数值类型" 
                    + "对应的参数为：开始日期[" + start + "]，结束日期[" + end + "]");
        }
        
        return result;
    }

}
