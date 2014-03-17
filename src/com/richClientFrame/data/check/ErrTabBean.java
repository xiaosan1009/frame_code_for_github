package com.richClientFrame.data.check;

import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ErrTabBean
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Apr 24, 2013 11:14:24 AM
 * @author king
 */
public class ErrTabBean {
    
    private List<ResponseTab> errList = new ArrayList<ResponseTab>();
    
    private Map<String, ResponseTab> errMap = new HashMap<String, ResponseTab>();
    
    private boolean error;
    
    /**
     * ���캯��.
     */
    public ErrTabBean() {
    }
    
    /**
     * ���캯��.
     * @param error error
     */
    public ErrTabBean(boolean error) {
        this.error = error;
    }
    
    /**
     * @Description: 
     * @author king
     * @since Apr 24, 2013 11:16:58 AM 
     * @version V1.0
     * @param tab ������Ϣ����
     */
    public void add(ResponseTab tab) {
        final String key = tab.getTargetId() + tab.getTargetIndex();
        final String detailCode = tab.getDetailCode();
        if (errMap.containsKey(key) || ConstantsUtil.Check.DEFAULT.equals(detailCode)) {
            return;
        }
        errList.add(tab);
        errMap.put(key, tab);
    }
    
    /**
     * @Description: 
     * @author king
     * @since Apr 24, 2013 11:31:08 AM 
     * @version V1.0
     * @param tabs ������Ϣ�������
     */
    public void addAll(List<ResponseTab> tabs) {
        for (int i = 0; i < tabs.size(); i++) {
            add(tabs.get(i));
        }
    }

    /**
     * @Description: 
     * @author king
     * @since Apr 24, 2013 11:20:29 AM 
     * @version V1.0
     * @return ������Ϣ����
     */
    public List<ResponseTab> getErrList() {
        return errList;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}
