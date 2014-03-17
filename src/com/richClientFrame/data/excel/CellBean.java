package com.richClientFrame.data.excel;

import com.richClientFrame.util.CommonUtil;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： CellBean
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Jul 31, 2013 3:01:57 PM
 * @author king
 */
public class CellBean {
    
    private String colspan;
    
    private String rowspan;
    
    private String site;
    
    private String value;
    
    private String background;
    
    private String align;
    
    private String valign;

    public String getColspan() {
        return colspan;
    }

    public void setColspan(String colspan) {
        this.colspan = colspan;
    }

    public String getRowspan() {
        return rowspan;
    }

    public void setRowspan(String rowspan) {
        this.rowspan = rowspan;
    }
    
    public boolean isMerged() {
        return CommonUtil.isEmpty(colspan) && CommonUtil.isEmpty(rowspan);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getValign() {
        return valign;
    }

    public void setValign(String valign) {
        this.valign = valign;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

}
