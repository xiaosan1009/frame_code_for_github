package com.richClientFrame.data.response;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： TabRowGroup
 * 类描述 ： 列表全体数据管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class TabRowGroup {
    
    private String widgetType1;
    private String widgetType2;
    private String value1;
    private String value2;
    
    public TabRowGroup(int type1) {
        this.widgetType1 = String.valueOf(type1);
    }
    
    public TabRowGroup(int type1, String value1) {
        this.widgetType1 = String.valueOf(type1);
        this.value1 = value1;
    }
    
    public TabRowGroup(int type1, int type2) {
        this.widgetType1 = String.valueOf(type1);
        this.widgetType2 = String.valueOf(type2);
    }
    
    public TabRowGroup(int type1, int type2, String value1, String value2) {
        this.widgetType1 = String.valueOf(type1);
        this.widgetType2 = String.valueOf(type2);
        this.value1 = value1;
        this.value2 = value2;
        
    }
    public String getWidgetType1() {
        return widgetType1;
    }
    public int getType1() {
        return Integer.parseInt(widgetType1);
    }
    public void setWidgetType1(String widgetType1) {
        this.widgetType1 = widgetType1;
    }
    public int getType2() {
        return Integer.parseInt(widgetType2);
    }
    public String getWidgetType2() {
        return widgetType2;
    }
    public void setWidgetType2(String widgetType2) {
        this.widgetType2 = widgetType2;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

}
