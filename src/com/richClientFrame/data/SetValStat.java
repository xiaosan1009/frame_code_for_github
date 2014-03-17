/**
 * 
 */
package com.richClientFrame.data;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.util.ConstantsUtil;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： SetValStat
 * 类描述 ： 名称管理MASTER文件类型类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.08.07
 * @author king
 */
public class SetValStat {
    
    private String value;
    
    private String type;
    
    private String nameCN;
    
    private String nameEng;
    

    public SetValStat() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNameCN() {
        return nameCN;
    }

    public void setNameCN(String nameCN) {
        this.nameCN = nameCN;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }
    
    public String getName(String lang) {
        if (lang.equals(ConstantsUtil.Lang.CN)) {
            return getNameCN();
        } else if (lang.equals(ConstantsUtil.Lang.EN)) {
            return getNameEng();
        } else {
            return "";
        }
    }
    
    public String getName() throws RichClientWebException{
        return getName(ControlConfig.getInstance().getConfiguration().getLang());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
