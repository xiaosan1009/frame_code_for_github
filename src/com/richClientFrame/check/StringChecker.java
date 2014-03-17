/**
 * 
 */
package com.richClientFrame.check;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.CheckInfo;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.info.ControlErrorMap;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： StringChecker
 * 类描述 ： 字符串验证类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.06.26
 * @author king
 */
public class StringChecker {
    
    // 系统共通有效字符
    public static final char INVALID_SIGN1 = '`';
    public static final char INVALID_SIGN2 = '~';
    public static final char INVALID_SIGN3 = '!';
    public static final char INVALID_SIGN4 = '@';
    public static final char INVALID_SIGN5 = '#';
    public static final char INVALID_SIGN6 = '$';
    public static final char INVALID_SIGN7 = '%';
    public static final char INVALID_SIGN8 = '^';
    public static final char INVALID_SIGN9 = '&';
    public static final char INVALID_SIGN10 = '*';
    public static final char INVALID_SIGN11 = '(';
    public static final char INVALID_SIGN12 = ')';
    public static final char INVALID_SIGN13 = '-';
    public static final char INVALID_SIGN14 = '_';
    public static final char INVALID_SIGN15 = '=';
    public static final char INVALID_SIGN16 = '+';
    public static final char INVALID_SIGN17 = '[';
    public static final char INVALID_SIGN18 = '{';
    public static final char INVALID_SIGN19 = ']';
    public static final char INVALID_SIGN20 = '}';
    public static final char INVALID_SIGN21 = '\\';
    public static final char INVALID_SIGN22 = '|';
    public static final char INVALID_SIGN23 = ';';
    public static final char INVALID_SIGN24 = ':';
    public static final char INVALID_SIGN25 = '\'';
    public static final char INVALID_SIGN26 = '"';
    public static final char INVALID_SIGN27 = ',';
    public static final char INVALID_SIGN28 = '<';
    public static final char INVALID_SIGN29 = '.';
    public static final char INVALID_SIGN30 = '>';
    public static final char INVALID_SIGN31 = '/';
    public static final char INVALID_SIGN32 = '?';
    
    /**
     * 系统共通有效字符.
     */
    public static final Character[] COMMON_VALID = {
        INVALID_SIGN27,
    };
    
    /**
     * 系统共通无效字符.
     */
    public static final Character[] COMMON_INVALID = {
        INVALID_SIGN1,
        INVALID_SIGN2,
        INVALID_SIGN3,
        INVALID_SIGN4,
        INVALID_SIGN5,
        INVALID_SIGN6,
        INVALID_SIGN7,
        INVALID_SIGN8,
        INVALID_SIGN9,
        INVALID_SIGN10,
        INVALID_SIGN11,
        INVALID_SIGN12,
        INVALID_SIGN13,
        INVALID_SIGN14,
        INVALID_SIGN15,
        INVALID_SIGN16,
        INVALID_SIGN17,
        INVALID_SIGN18,
        INVALID_SIGN19,
        INVALID_SIGN20,
        INVALID_SIGN21,
        INVALID_SIGN22,
        INVALID_SIGN23,
        INVALID_SIGN24,
        INVALID_SIGN25,
        INVALID_SIGN26,
        INVALID_SIGN28,
        INVALID_SIGN29,
        INVALID_SIGN30,
        INVALID_SIGN31,
        INVALID_SIGN32,
    };

    /**
     * 构造函数.
     */
    public StringChecker() {
        super();
    }
    
    /**
     * 验证STRING型数据.
     * @param param request
     * @param fieldId 控件ID
     * @param value 输入值
     * @param isRequired 必须输入项目：true，非必须输入项目：false
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     * @throws Exception
     */
    public CheckInfo checkString(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        final CheckInfo checkInfo = new CheckInfo();
        
        // 非必须输入并且没有输入的时候，验证通过
        if (!isRequired && value.length() == 0) {
            return checkInfo;
        }
        
        final ControlErrorMap errMap = ControlErrorMap.getInstance();
        
        // 无效字符数组
        final List<Character> invalidList = new ArrayList<Character>();
        
        // 有效字符数组
        List<Character> validList = new ArrayList<Character>();
        
        // 系统共通有效字符加入
        final List<Character> commonList = Arrays.asList(COMMON_VALID);
        final List<Character> commonInvalidList = Arrays.asList(COMMON_INVALID);
        validList.addAll(commonList);
        invalidList.addAll(commonInvalidList);
        
        // 取得异常类型
        final String[] patternList = errMap.getErrPattern(param, fieldId);
        if (patternList == null || patternList.length < 4) {
            throw new RichClientWebException("[FILE ERROR] At least 4 variables for " 
                    + "a STRING check pattern. ( dispcode:" 
                    + param.dispCode + " field:" + fieldId + " )");
        } else {
            final String semiangleSign = patternList[0];
            final String numberSign = patternList[1];
            // 半角字符标识
            final boolean semiangleFlag = checkSemiangle(semiangleSign);
            // 数值标识
            final boolean numberFlag = checkNumberValue(numberSign);
            // 最小文字数
            final String strMinLength = patternList[2];
            // 最大文字数
            final String strMaxLength = patternList[3];
            // 无效文字数组
            if (!"3".equals(numberSign)) {
                for (int i = 4; i < patternList.length; i++) {
                    if (CommonUtil.isEmpty(patternList[i])) {
                        continue;
                    }
                    if (invalidList.contains(patternList[i].charAt(0))) {
                        final int index = invalidList.indexOf(patternList[i].charAt(0));
                        invalidList.remove(index);
                    }
                    validList.add(patternList[i].charAt(0));
                }
            } else {
                validList = null;
            }
            
            // 字符串范围验证
            try {
                result = checkStringLength(value, 
                        Integer.parseInt(strMinLength), Integer.parseInt(strMaxLength));
                if (ConstantsUtil.Check.OVER_LENGTH.equals(result)) {
                    final List<String> pattern = new ArrayList<String>();
                    pattern.add(strMinLength);
                    pattern.add(strMaxLength);
                    checkInfo.setPattern(pattern);
                }
            } catch (NumberFormatException e) {
                throw new RichClientWebException("[FILE ERROR] The minimum length ( " 
                        + strMinLength + " ) or the maximum length ( " + strMaxLength 
                        + " ) is not a number. ( dispcode:" 
                        + param.dispCode + " field:" + fieldId + " )");
            }
            
            // 半角验证，数值验证
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                for (int c = 0; c < value.length(); c++) {
                    if (!ConstantsUtil.Check.DEFAULT.equals(result)) {
                        break;
                    }
                    final char valueC = value.charAt(c);
                    // 半角数值验证
                    if (semiangleFlag && numberFlag) {
                        result = checkSemiangleNumber(valueC);
                    // 半角文字或数值验证
                    } else if (semiangleFlag && !numberFlag) {
                        result = checkHankakuChar(valueC, validList);
                    // 全半角数值验证
                    } else if (!semiangleFlag && numberFlag) {
                        result = checkNumber(valueC);
                    }
                }
            }
            
            // 无效文字验证
            if (ConstantsUtil.Check.DEFAULT.equals(result) && !numberFlag && validList != null) {
                result = checkInvalidString(value, invalidList);
            }
            
            //邮箱地址验证
            if (checkMailValue(patternList[0]) && checkMailValue(patternList[1])) {
                result = checkMailValid(value);
            }
        }
        checkInfo.setDetailCode(result);
        return checkInfo;
    }
    
    /**
     * 验证邮箱地址
     * @param email 输入值
     * @return 正常：-1，异常：-1以外的数（在ResponseTab中有详细的定义）
     */
    private String checkMailValid(String email) {
        String result = ConstantsUtil.Check.DEFAULT;
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            result = ConstantsUtil.Check.EMAIL_FORMAT_ERROR;
        }
        return result;
    }
    
    /**
     * 数值【1】或者邮箱【2】置换成【true】
     * @param value 输入值
     * @return 半角：true，全半角：false
     * @throws RichClientWebException RichClientWebException
     */
    private boolean checkSemiangle(String value) 
        throws RichClientWebException {
        boolean ret = false;
        
        if ("1".equals(value) || "2".equals(value)) {
            ret = true;
        }
        
        return ret;
    }
    
    /**
     * 数值【1】置换成【true】
     * @param value 输入值
     * @return 数值：true，英数字：false
     * @throws RichClientWebException RichClientWebException
     */
    private boolean checkNumberValue(String value) 
        throws RichClientWebException {
        boolean ret = false;
        
        if ("1".equals(value)) {
            ret = true;
        }
        
        return ret;
    }
    
    /**
     * 邮箱【2】置换成【true】
     * @param value 输入值
     * @return 邮箱：true，非邮箱：false
     * @throws RichClientWebException RichClientWebException
     */
    private boolean checkMailValue(String value) 
        throws RichClientWebException {
        boolean ret = false;
        
        if ("2".equals(value)) {
            ret = true;
        }
        
        return ret;
    }
    
    /**
     * 验证是否为半角字符或者半角数值.
     * @param value 输入值
     * @param validList 合法字符数组
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    public String checkHankakuChar(char value, final List<Character> validList) 
        throws RichClientWebException {
        final String ret = ConstantsUtil.Check.DEFAULT;
        
        // 半角字符验证
        if ((value < 'a' || value > 'z') && (value < 'A' || value > 'Z')) {
            // 非半角字符时，半角数值验证
            if (ConstantsUtil.Check.DEFAULT.equals(checkSemiangleNumber(value))) {
                return ConstantsUtil.Check.DEFAULT;
            } else {
                if (validList == null || validList.contains(value)) {
                    return ConstantsUtil.Check.DEFAULT;
                }
                return ConstantsUtil.Check.INPUT_ERROR;
            }
        }
        
        return ret;
    }
    
    /**
     * 全半角数值验证.
     * @param value 输入值
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    public String checkNumber(char value) throws RichClientWebException {
        final String ret = ConstantsUtil.Check.DEFAULT;
        
        // 全角数值验证
        if (value < '０' || value > '９') {
            // 不是全角数值时，半角验证
            if (ConstantsUtil.Check.DEFAULT.equals(checkSemiangleNumber(value))) {
                return ConstantsUtil.Check.DEFAULT;
            } else {
                return ConstantsUtil.Check.NUM_ONLY_ERROR;
            }
        }
        
        return ret;
    }
    
    /**
     * 验证是否为半角数值.
     * @param value 输入值
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    public String checkSemiangleNumber(char value) throws RichClientWebException {
        String ret = ConstantsUtil.Check.DEFAULT;
        
        if (value < '0' || value > '9') {
            ret = ConstantsUtil.Check.NUM_ONLY_ERROR;
        }
        
        return ret;
    }
    
    /**
     * 字符串范围验证.
     * @param value 输入值
     * @param minlength 最小字符串长度
     * @param maxlength 最大字符串长度
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    public String checkStringLength(String value, int minlength, int maxlength) 
        throws RichClientWebException {
        
        String result = ConstantsUtil.Check.DEFAULT;
        
        // 字节数数组
        byte[] bytes = null;
        int len = 0;

        try {
            bytes = value.getBytes(ControlConfig.DEF_CHARCODE);
            if (value.length() != bytes.length) {
                for (int nCnt = 0; nCnt < value.length(); nCnt++) {
                    final String ch = value.substring(nCnt, nCnt + 1);
                    final byte[] chBytes = ch.getBytes(ControlConfig.DEF_CHARCODE);
                    if (chBytes.length == 3) {
                        len += 2;
                    } else {
                        len += chBytes.length;
                    }
                }
            } else {
                len = bytes.length;
            }
        } catch (Exception e) {
            throw new RichClientWebException(e.toString());
        }
        
        if (len < minlength || len > maxlength) {
            result = ConstantsUtil.Check.OVER_LENGTH;
        }
        
        return result;
    }
    
    /**
     * 无效文字验证.
     * @param value 输入值
     * @param invalidList 无效文字数组
     * @return 正常：-1，异常：-1以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     */
    public String checkInvalidString(String value, List<Character> invalidList) 
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        for (int k = 0; k < value.length(); k++) {
            final char charC = value.charAt(k);
            // 含有无效文字
            if (invalidList.contains(charC)) {
                //result = false;
                result = ConstantsUtil.Check.INPUT_INVALID;
                break;
            }
        }
        
        return result;
    }

}
