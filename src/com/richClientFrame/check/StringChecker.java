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
 * ��Ŀ���� �� Web2.0��������
 * ������ �� StringChecker
 * ������ �� �ַ�����֤��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.06.26
 * @author king
 */
public class StringChecker {
    
    // ϵͳ��ͨ��Ч�ַ�
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
     * ϵͳ��ͨ��Ч�ַ�.
     */
    public static final Character[] COMMON_VALID = {
        INVALID_SIGN27,
    };
    
    /**
     * ϵͳ��ͨ��Ч�ַ�.
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
     * ���캯��.
     */
    public StringChecker() {
        super();
    }
    
    /**
     * ��֤STRING������.
     * @param param request
     * @param fieldId �ؼ�ID
     * @param value ����ֵ
     * @param isRequired ����������Ŀ��true���Ǳ���������Ŀ��false
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     * @throws Exception
     */
    public CheckInfo checkString(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        final CheckInfo checkInfo = new CheckInfo();
        
        // �Ǳ������벢��û�������ʱ����֤ͨ��
        if (!isRequired && value.length() == 0) {
            return checkInfo;
        }
        
        final ControlErrorMap errMap = ControlErrorMap.getInstance();
        
        // ��Ч�ַ�����
        final List<Character> invalidList = new ArrayList<Character>();
        
        // ��Ч�ַ�����
        List<Character> validList = new ArrayList<Character>();
        
        // ϵͳ��ͨ��Ч�ַ�����
        final List<Character> commonList = Arrays.asList(COMMON_VALID);
        final List<Character> commonInvalidList = Arrays.asList(COMMON_INVALID);
        validList.addAll(commonList);
        invalidList.addAll(commonInvalidList);
        
        // ȡ���쳣����
        final String[] patternList = errMap.getErrPattern(param, fieldId);
        if (patternList == null || patternList.length < 4) {
            throw new RichClientWebException("[FILE ERROR] At least 4 variables for " 
                    + "a STRING check pattern. ( dispcode:" 
                    + param.dispCode + " field:" + fieldId + " )");
        } else {
            final String semiangleSign = patternList[0];
            final String numberSign = patternList[1];
            // ����ַ���ʶ
            final boolean semiangleFlag = checkSemiangle(semiangleSign);
            // ��ֵ��ʶ
            final boolean numberFlag = checkNumberValue(numberSign);
            // ��С������
            final String strMinLength = patternList[2];
            // ���������
            final String strMaxLength = patternList[3];
            // ��Ч��������
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
            
            // �ַ�����Χ��֤
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
            
            // �����֤����ֵ��֤
            if (ConstantsUtil.Check.DEFAULT.equals(result)) {
                for (int c = 0; c < value.length(); c++) {
                    if (!ConstantsUtil.Check.DEFAULT.equals(result)) {
                        break;
                    }
                    final char valueC = value.charAt(c);
                    // �����ֵ��֤
                    if (semiangleFlag && numberFlag) {
                        result = checkSemiangleNumber(valueC);
                    // ������ֻ���ֵ��֤
                    } else if (semiangleFlag && !numberFlag) {
                        result = checkHankakuChar(valueC, validList);
                    // ȫ�����ֵ��֤
                    } else if (!semiangleFlag && numberFlag) {
                        result = checkNumber(valueC);
                    }
                }
            }
            
            // ��Ч������֤
            if (ConstantsUtil.Check.DEFAULT.equals(result) && !numberFlag && validList != null) {
                result = checkInvalidString(value, invalidList);
            }
            
            //�����ַ��֤
            if (checkMailValue(patternList[0]) && checkMailValue(patternList[1])) {
                result = checkMailValid(value);
            }
        }
        checkInfo.setDetailCode(result);
        return checkInfo;
    }
    
    /**
     * ��֤�����ַ
     * @param email ����ֵ
     * @return ������-1���쳣��-1�����������ResponseTab������ϸ�Ķ��壩
     */
    private String checkMailValid(String email) {
        String result = ConstantsUtil.Check.DEFAULT;
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            result = ConstantsUtil.Check.EMAIL_FORMAT_ERROR;
        }
        return result;
    }
    
    /**
     * ��ֵ��1���������䡾2���û��ɡ�true��
     * @param value ����ֵ
     * @return ��ǣ�true��ȫ��ǣ�false
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
     * ��ֵ��1���û��ɡ�true��
     * @param value ����ֵ
     * @return ��ֵ��true��Ӣ���֣�false
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
     * ���䡾2���û��ɡ�true��
     * @param value ����ֵ
     * @return ���䣺true�������䣺false
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
     * ��֤�Ƿ�Ϊ����ַ����߰����ֵ.
     * @param value ����ֵ
     * @param validList �Ϸ��ַ�����
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    public String checkHankakuChar(char value, final List<Character> validList) 
        throws RichClientWebException {
        final String ret = ConstantsUtil.Check.DEFAULT;
        
        // ����ַ���֤
        if ((value < 'a' || value > 'z') && (value < 'A' || value > 'Z')) {
            // �ǰ���ַ�ʱ�������ֵ��֤
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
     * ȫ�����ֵ��֤.
     * @param value ����ֵ
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    public String checkNumber(char value) throws RichClientWebException {
        final String ret = ConstantsUtil.Check.DEFAULT;
        
        // ȫ����ֵ��֤
        if (value < '��' || value > '��') {
            // ����ȫ����ֵʱ�������֤
            if (ConstantsUtil.Check.DEFAULT.equals(checkSemiangleNumber(value))) {
                return ConstantsUtil.Check.DEFAULT;
            } else {
                return ConstantsUtil.Check.NUM_ONLY_ERROR;
            }
        }
        
        return ret;
    }
    
    /**
     * ��֤�Ƿ�Ϊ�����ֵ.
     * @param value ����ֵ
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
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
     * �ַ�����Χ��֤.
     * @param value ����ֵ
     * @param minlength ��С�ַ�������
     * @param maxlength ����ַ�������
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    public String checkStringLength(String value, int minlength, int maxlength) 
        throws RichClientWebException {
        
        String result = ConstantsUtil.Check.DEFAULT;
        
        // �ֽ�������
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
     * ��Ч������֤.
     * @param value ����ֵ
     * @param invalidList ��Ч��������
     * @return ������-1���쳣��-1�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     */
    public String checkInvalidString(String value, List<Character> invalidList) 
        throws RichClientWebException {
        String result = ConstantsUtil.Check.DEFAULT;
        
        for (int k = 0; k < value.length(); k++) {
            final char charC = value.charAt(k);
            // ������Ч����
            if (invalidList.contains(charC)) {
                //result = false;
                result = ConstantsUtil.Check.INPUT_INVALID;
                break;
            }
        }
        
        return result;
    }

}
