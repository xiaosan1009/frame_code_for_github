package com.richClientFrame.data.response;

import com.richClientFrame.util.ConstantsUtil;

import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseTab
 * 类描述 ： Response数据的管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.07.28
 * @author king
 */
public class ResponseTab {
    
    /**
     * 下拉框内容
     */
    private Map<String, AbstractResponseData> cmbMap;
    
    /**
     * 控件ID
     */
    private String targetId = "";
    
    /**
     * 多项目控件序号
     */
    private String targetIndex = "";
    
    /**
     * 数据有效/无效
     */
    private int effect = ConstantsUtil.Status.EFFECT_YES;
    
    /**
     * 数据
     */
    private String value = "";
    
    /**
     * 显示的值
     */
    private String text = "";
    
    /**
     * 类型
     */
    private int dataType = ConstantsUtil.Widget.DEFAULT;
    
    /**
     * 背景颜色
     */
    private String bgColor = ConstantsUtil.Status.BG_COLOR_DEFAULT;
    
    /**
     * 文字颜色
     */
    private String charColor = ConstantsUtil.Status.CHAR_COLOR_DEFAULT;
    
    /**
     * 错误信息文字颜色
     */
    private String errMsgColor = ConstantsUtil.Status.CHAR_COLOR_DEFAULT;
    
    /**
     * 表示状态
     */
    private int dispStat = ConstantsUtil.Status.NO_CHANGE;
    
    /**
     * 输入验证处理 详细错误号
     */
    private String detailCode = ConstantsUtil.Check.DEFAULT;
    
    /**
     * 输入验证处理 错误项目名称
     */
    private String itemName = "";
    
    private boolean selected;

    /**
     * 输入长度
     */
    private int maxLen;
    
    /**
     * 详细错误信息
     */
    private String detailMsg = "";
    
    /**
     * 字体样式
     */
    private String charWeight = ConstantsUtil.Status.CHAR_WEIGHT_DEFAULT;
    
    private Map<String, Object> dataMap;
    
    /**
     * 构造函数.
     */
    public ResponseTab() {
        super();
    }

    /**
     * 背景颜色取得.
     * 
     * @return bgColor 背景颜色
     */
    public String getBgColor() {
        return bgColor;
    }

    /**
     * 背景颜色设定.
     * 
     * @param bgColor 背景颜色
     */
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * 文字颜色取得.
     * 
     * @return charColor 文字颜色
     */
    public String getCharColor() {
        return charColor;
    }

    /**
     * 文字颜色的设定.
     * 
     * @param charColor 文字颜色
     */
    public void setCharColor(String charColor) {
        this.charColor = charColor;
    }

    /**
     * 类型取得.
     * 
     * @return dataType 类型
     */
    public int getDataType() {
        return dataType;
    }

    /**
     * 类型的设定.
     * 
     * @param dataType 类型
     */
    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    /**
     * 表示状态取得.
     * 
     * @return dispStat 表示状态
     */
    public int getDispStat() {
        return dispStat;
    }

    /**
     * 表示状态的设定.
     * 
     * @param dispStat 表示状态
     */
    public void setDispStat(int dispStat) {
        this.dispStat = dispStat;
    }

    /**
     * 数据有效/无效取得.
     * 
     * @return effect 数据有效/无效
     */
    public int getEffect() {
        return effect;
    }

    /**
     * 数据有效/无效的设定.
     * 
     * @param effect 数据有效/无效
     */
    public void setEffect(int effect) {
        this.effect = effect;
    }
    
    /**
     * 控件ID取得.
     * 
     * @return targetId 控件ID
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * 控件ID的设定.
     * 
     * @param targetId 控件ID
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * 多项目控件序号取得.
     * 
     * @return targetIndex 多项目控件序号
     */
    public String getTargetIndex() {
        return targetIndex;
    }

    /**
     * 多项目控件序号的设定.
     * 
     * @param targetIndex 多项目控件序号
     */
    public void setTargetIndex(String targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * 数据取得.
     * 
     * @return value 数据
     */
    public String getValue() {
        return value;
    }

    /**
     * 数据的设定.
     * 
     * @param value 数据
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 显示的值取得.
     * 
     * @return text 显示的值
     */
    public String getText() {
        return text;
    }

    /**
     * 显示的值的设定.
     * 
     * @param text 显示的值
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 输入长度取得.
     * 
     * @return maxLen 输入长度
     */
    public int getMaxLen() {
        return maxLen;
    }

    /**
     * 输入长度的设定.
     * 
     * @param maxLen 输入长度
     */
    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    /**
     * 详细错误号取得.
     * 
     * @return 详细错误号
     */
    public String getDetailCode() {
        return detailCode;
    }

    /**
     * 详细错误号的设定.
     * 
     * @param detailCode 详细错误号
     */
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    /**
     * 错误项目名称取得.
     * 
     * @return 错误项目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 错误项目名称的设定.
     * 
     * @param itemName 错误项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 详细错误信息取得.
     * 
     * @return detailMsg 详细错误信息
     */
    public String getDetailMsg() {
        return detailMsg;
    }

    /**
     * 详细错误信息的设定.
     * 
     * @param detailMsg 详细错误信息
     */
    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }

    /**
     * 详细错误信息颜色取得.
     * 
     * @return detailMsg 详细错误颜色信息
     */
    public String getErrMsgColor() {
        return errMsgColor;
    }

    /**
     * 详细错误信息颜色的设定.
     * 
     * @param errMsgColor 详细错误颜色信息
     */
    public void setErrMsgColor(String errMsgColor) {
        this.errMsgColor = errMsgColor;
    }

    /**
     * 字体大小取得.
     * 
     * @return charWeight 字体大小
     */
    public String getCharWeight() {
        return charWeight;
    }

    /**
     * 字体大小的设定.
     * 
     * @param charWeight 字体大小
     */
    public void setCharWeight(String charWeight) {
        this.charWeight = charWeight;
    }
    
    /**
     * 下拉框内容取得.
     * 
     * @return 下拉框内容
     */
    public Map<String, AbstractResponseData> getCmbList() {
        return cmbMap;
    }

    /**
     * 下拉框内容的设定.
     * 
     * @param cmbList 下拉框内容
     */
    public void setCmbList(Map<String, AbstractResponseData> cmbList) {
        this.cmbMap = cmbList;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

}
