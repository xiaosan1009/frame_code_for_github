package com.richClientFrame.info;

import com.richClientFrame.util.ConstantsUtil;

import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： CheckInfo
 * 类描述 ： 验证结果保持类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.03.24
 * @author king
 */
public class CheckInfo {
    
    private String detailCode = ConstantsUtil.Check.DEFAULT;
    
    private List<String> pattern;

    /**
     * 取得验证结果值.
     * @return 验证结果值
     */
    public String getDetailCode() {
        return detailCode;
    }

    /**
     * 设置验证结果值.
     * @param detailCode 验证结果值
     */
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    /**
     * 取得验证结果动态消息数组.
     * @return 验证结果动态消息数组
     */
    public List<String> getPattern() {
        return pattern;
    }

    /**
     * 设置验证结果动态消息数组.
     * @param pattern 验证结果动态消息数组
     */
    public void setPattern(List<String> pattern) {
        this.pattern = pattern;
    }
    
    /**
     * 判断验证结果是否为错误结果[true:错误;false:正确].
     * @return 验证结果是否为错误
     */
    public boolean isError() {
        return !ConstantsUtil.Check.DEFAULT.equals(detailCode);
    }

}
