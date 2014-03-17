package com.richClientFrame.check;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.CheckInfo;
import com.richClientFrame.info.ControlErrorMap;
import com.richClientFrame.util.ConstantsUtil;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： FileChecker
 * 类描述 ： 文件验证类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.10.13
 * @author king
 */
public class FileChecker {
    
    /**
     * 文件验证处理.
     * 
     * @param param reqeust
     * @param fieldId 项目ID
     * @param value 输入的值
     * @param isRequired 是否为必须输入项  必须：true， 非必须：false
     * @return 正常：0，异常：0以外的数（在ResponseTab中有详细的定义）
     * @throws RichClientWebException RichClientWebException
     * @throws Exception
     */
    public CheckInfo checkFile(Param param, String fieldId, String value, boolean isRequired)
        throws RichClientWebException {
        
        final String result = ConstantsUtil.Check.FILE_FORMAT_ERROR;
        
        final CheckInfo checkInfo = new CheckInfo();
        
        // 非必须输入并且没有输入的时候，验证通过
        if (!isRequired && value.length() == 0) {
            return checkInfo;
        }

        final ControlErrorMap errMap = ControlErrorMap.getInstance();

        // 取得异常类型
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
