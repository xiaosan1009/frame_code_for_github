package com.richClientFrame.data.param;

import com.richClientFrame.dao.TableRowMap;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� UpdateParam
 * ������ �� ���´������������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.09.07
 * @author king
 */
public class UpdateParam {
    
    private boolean dbResult;
    
    private TableRowMap result;

    public boolean isDbResult() {
        return dbResult;
    }

    public void setDbResult(boolean dbResult) {
        this.dbResult = dbResult;
    }

    public TableRowMap getResult() {
        return result;
    }

    public void setResult(TableRowMap result) {
        this.result = result;
    }

}
