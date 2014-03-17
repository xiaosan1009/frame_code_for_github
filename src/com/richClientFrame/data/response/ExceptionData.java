/**
 * 
 */
package com.richClientFrame.data.response;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ExceptionData
 * ������ �� ��ʾ�趨��������֤���쳣������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.11.04
 * @author king
 */
public class ExceptionData {
    
    /**
     * ��ʾ�趨��������֤���쳣��ĿID.
     */
    private String exceptTargetId = "";
    
    /**
     * <pre>
     * ��ʾ�趨��������֤���쳣��������.
     * </pre>
     */
    private List<String> checkTargetIdx = new ArrayList<String>();

    /**
     * ���캯��.
     */
    public ExceptionData() {
    }
    
    /**
     * ���캯��.
     * @param exceptTargetId ��ĿID
     */
    public ExceptionData(String exceptTargetId) {
        super();
        this.exceptTargetId = exceptTargetId;
    }

    /**
     * ���캯��.
     * @param exceptTargetId ��ĿID
     * @param checkTargetIdx ��������
     */
    public ExceptionData(String exceptTargetId, List<String> checkTargetIdx) {
        super();
        this.exceptTargetId = exceptTargetId;
        this.checkTargetIdx = checkTargetIdx;
    }

    /**
     * ��ĿIDȡ��.
     * @return ��ĿID
     */
    public String getExceptTargetId() {
        return exceptTargetId;
    }

    /**
     * ��ĿID�趨.
     * @param exceptTargetId ��ĿID
     */
    public void setExceptTargetId(String exceptTargetId) {
        this.exceptTargetId = exceptTargetId;
    }

    /**
     * ��������ȡ��.
     * @return ��������
     */
    public List<String> getCheckTargetIdx() {
        return checkTargetIdx;
    }

    /**
     * ���������趨.
     * @param checkTargetIdx ��������
     */
    public void setCheckTargetIdx(List<String> checkTargetIdx) {
        this.checkTargetIdx = checkTargetIdx;
    }
}
