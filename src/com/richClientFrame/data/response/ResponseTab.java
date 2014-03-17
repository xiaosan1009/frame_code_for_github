package com.richClientFrame.data.response;

import com.richClientFrame.util.ConstantsUtil;

import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ResponseTab
 * ������ �� Response���ݵĹ�����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.07.28
 * @author king
 */
public class ResponseTab {
    
    /**
     * ����������
     */
    private Map<String, AbstractResponseData> cmbMap;
    
    /**
     * �ؼ�ID
     */
    private String targetId = "";
    
    /**
     * ����Ŀ�ؼ����
     */
    private String targetIndex = "";
    
    /**
     * ������Ч/��Ч
     */
    private int effect = ConstantsUtil.Status.EFFECT_YES;
    
    /**
     * ����
     */
    private String value = "";
    
    /**
     * ��ʾ��ֵ
     */
    private String text = "";
    
    /**
     * ����
     */
    private int dataType = ConstantsUtil.Widget.DEFAULT;
    
    /**
     * ������ɫ
     */
    private String bgColor = ConstantsUtil.Status.BG_COLOR_DEFAULT;
    
    /**
     * ������ɫ
     */
    private String charColor = ConstantsUtil.Status.CHAR_COLOR_DEFAULT;
    
    /**
     * ������Ϣ������ɫ
     */
    private String errMsgColor = ConstantsUtil.Status.CHAR_COLOR_DEFAULT;
    
    /**
     * ��ʾ״̬
     */
    private int dispStat = ConstantsUtil.Status.NO_CHANGE;
    
    /**
     * ������֤���� ��ϸ�����
     */
    private String detailCode = ConstantsUtil.Check.DEFAULT;
    
    /**
     * ������֤���� ������Ŀ����
     */
    private String itemName = "";
    
    private boolean selected;

    /**
     * ���볤��
     */
    private int maxLen;
    
    /**
     * ��ϸ������Ϣ
     */
    private String detailMsg = "";
    
    /**
     * ������ʽ
     */
    private String charWeight = ConstantsUtil.Status.CHAR_WEIGHT_DEFAULT;
    
    private Map<String, Object> dataMap;
    
    /**
     * ���캯��.
     */
    public ResponseTab() {
        super();
    }

    /**
     * ������ɫȡ��.
     * 
     * @return bgColor ������ɫ
     */
    public String getBgColor() {
        return bgColor;
    }

    /**
     * ������ɫ�趨.
     * 
     * @param bgColor ������ɫ
     */
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * ������ɫȡ��.
     * 
     * @return charColor ������ɫ
     */
    public String getCharColor() {
        return charColor;
    }

    /**
     * ������ɫ���趨.
     * 
     * @param charColor ������ɫ
     */
    public void setCharColor(String charColor) {
        this.charColor = charColor;
    }

    /**
     * ����ȡ��.
     * 
     * @return dataType ����
     */
    public int getDataType() {
        return dataType;
    }

    /**
     * ���͵��趨.
     * 
     * @param dataType ����
     */
    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    /**
     * ��ʾ״̬ȡ��.
     * 
     * @return dispStat ��ʾ״̬
     */
    public int getDispStat() {
        return dispStat;
    }

    /**
     * ��ʾ״̬���趨.
     * 
     * @param dispStat ��ʾ״̬
     */
    public void setDispStat(int dispStat) {
        this.dispStat = dispStat;
    }

    /**
     * ������Ч/��Чȡ��.
     * 
     * @return effect ������Ч/��Ч
     */
    public int getEffect() {
        return effect;
    }

    /**
     * ������Ч/��Ч���趨.
     * 
     * @param effect ������Ч/��Ч
     */
    public void setEffect(int effect) {
        this.effect = effect;
    }
    
    /**
     * �ؼ�IDȡ��.
     * 
     * @return targetId �ؼ�ID
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * �ؼ�ID���趨.
     * 
     * @param targetId �ؼ�ID
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * ����Ŀ�ؼ����ȡ��.
     * 
     * @return targetIndex ����Ŀ�ؼ����
     */
    public String getTargetIndex() {
        return targetIndex;
    }

    /**
     * ����Ŀ�ؼ���ŵ��趨.
     * 
     * @param targetIndex ����Ŀ�ؼ����
     */
    public void setTargetIndex(String targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * ����ȡ��.
     * 
     * @return value ����
     */
    public String getValue() {
        return value;
    }

    /**
     * ���ݵ��趨.
     * 
     * @param value ����
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * ��ʾ��ֵȡ��.
     * 
     * @return text ��ʾ��ֵ
     */
    public String getText() {
        return text;
    }

    /**
     * ��ʾ��ֵ���趨.
     * 
     * @param text ��ʾ��ֵ
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * ���볤��ȡ��.
     * 
     * @return maxLen ���볤��
     */
    public int getMaxLen() {
        return maxLen;
    }

    /**
     * ���볤�ȵ��趨.
     * 
     * @param maxLen ���볤��
     */
    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    /**
     * ��ϸ�����ȡ��.
     * 
     * @return ��ϸ�����
     */
    public String getDetailCode() {
        return detailCode;
    }

    /**
     * ��ϸ����ŵ��趨.
     * 
     * @param detailCode ��ϸ�����
     */
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    /**
     * ������Ŀ����ȡ��.
     * 
     * @return ������Ŀ����
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * ������Ŀ���Ƶ��趨.
     * 
     * @param itemName ������Ŀ����
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * ��ϸ������Ϣȡ��.
     * 
     * @return detailMsg ��ϸ������Ϣ
     */
    public String getDetailMsg() {
        return detailMsg;
    }

    /**
     * ��ϸ������Ϣ���趨.
     * 
     * @param detailMsg ��ϸ������Ϣ
     */
    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }

    /**
     * ��ϸ������Ϣ��ɫȡ��.
     * 
     * @return detailMsg ��ϸ������ɫ��Ϣ
     */
    public String getErrMsgColor() {
        return errMsgColor;
    }

    /**
     * ��ϸ������Ϣ��ɫ���趨.
     * 
     * @param errMsgColor ��ϸ������ɫ��Ϣ
     */
    public void setErrMsgColor(String errMsgColor) {
        this.errMsgColor = errMsgColor;
    }

    /**
     * �����Сȡ��.
     * 
     * @return charWeight �����С
     */
    public String getCharWeight() {
        return charWeight;
    }

    /**
     * �����С���趨.
     * 
     * @param charWeight �����С
     */
    public void setCharWeight(String charWeight) {
        this.charWeight = charWeight;
    }
    
    /**
     * ����������ȡ��.
     * 
     * @return ����������
     */
    public Map<String, AbstractResponseData> getCmbList() {
        return cmbMap;
    }

    /**
     * ���������ݵ��趨.
     * 
     * @param cmbList ����������
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
