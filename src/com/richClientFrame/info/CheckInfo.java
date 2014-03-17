package com.richClientFrame.info;

import com.richClientFrame.util.ConstantsUtil;

import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� CheckInfo
 * ������ �� ��֤���������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.03.24
 * @author king
 */
public class CheckInfo {
    
    private String detailCode = ConstantsUtil.Check.DEFAULT;
    
    private List<String> pattern;

    /**
     * ȡ����֤���ֵ.
     * @return ��֤���ֵ
     */
    public String getDetailCode() {
        return detailCode;
    }

    /**
     * ������֤���ֵ.
     * @param detailCode ��֤���ֵ
     */
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    /**
     * ȡ����֤�����̬��Ϣ����.
     * @return ��֤�����̬��Ϣ����
     */
    public List<String> getPattern() {
        return pattern;
    }

    /**
     * ������֤�����̬��Ϣ����.
     * @param pattern ��֤�����̬��Ϣ����
     */
    public void setPattern(List<String> pattern) {
        this.pattern = pattern;
    }
    
    /**
     * �ж���֤����Ƿ�Ϊ������[true:����;false:��ȷ].
     * @return ��֤����Ƿ�Ϊ����
     */
    public boolean isError() {
        return !ConstantsUtil.Check.DEFAULT.equals(detailCode);
    }

}
