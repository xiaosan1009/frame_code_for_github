package com.richClientFrame.check;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.CheckInfo;
import com.richClientFrame.info.ControlErrorMap;
import com.richClientFrame.util.ConstantsUtil;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� FileChecker
 * ������ �� �ļ���֤��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.10.13
 * @author king
 */
public class FileChecker {
    
    /**
     * �ļ���֤����.
     * 
     * @param param reqeust
     * @param fieldId ��ĿID
     * @param value �����ֵ
     * @param isRequired �Ƿ�Ϊ����������  ���룺true�� �Ǳ��룺false
     * @return ������0���쳣��0�����������ResponseTab������ϸ�Ķ��壩
     * @throws RichClientWebException RichClientWebException
     * @throws Exception
     */
    public CheckInfo checkFile(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        
        final String result = ConstantsUtil.Check.FILE_FORMAT_ERROR;
        
        final CheckInfo checkInfo = new CheckInfo();
        
        // �Ǳ������벢��û�������ʱ����֤ͨ��
        if (!isRequired && value.length() == 0) {
            return checkInfo;
        }

        final ControlErrorMap errMap = ControlErrorMap.getInstance();

        // ȡ���쳣����
        final String[] patternList = errMap.getErrPattern(param, fieldId);
        if (patternList == null || patternList.length < 1) {
            throw new RichClientWebException("[FILE ERROR] At least 3 variables are required " 
                    + "for a FILE check pattern. ( dispcode:" 
                    + param.dispCode + " field:" + fieldId + " )");
        } else {
            final String suffix = value.split(ConstantsUtil.Str.REGEX_DOT)[1];
            
            for (int i = 0; i < patternList.length; i++) {
                if (suffix.toUpperCase().equals(patternList[i].toUpperCase())) {
                    return checkInfo;
                }
            }
        }
        
        checkInfo.setDetailCode(result);
        return checkInfo;
    }

}
