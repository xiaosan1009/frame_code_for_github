/**
 * 
 */
package com.richClientFrame.data;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.util.ConstantsUtil;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� SetValStat
 * ������ �� ���ƹ���MASTER�ļ�������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.08.07
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
