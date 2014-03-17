package com.richClientFrame.util;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseData;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.RichClientWebEditException;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 画面共通处理类.
 * 
 * @author King
 * @since 2010.03.24
 */
public class HtmlTabUtil {
    
    private static LogUtil sLog = new LogUtil(HtmlTabUtil.class);;

    private HtmlTabUtil() {
    }
    
    public static void setTarget(ResponseTab[] tablist, String targetId, String targetIndex) {
        for (int cnt = 0; cnt < tablist.length; cnt++) {
            tablist[cnt].setTargetId(targetId);
            tablist[cnt].setTargetIndex(targetIndex);
        }
    }
    
    public static ResponseTab makeBgColorTab(String targetId, int datatype) throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
        tab.setDataType(datatype);
        if (datatype == ConstantsUtil.Widget.CHK || datatype == ConstantsUtil.Widget.RADIO) {
            tab.setBgColor(ControlConfig.getInstance().getConfiguration().getBodyBackColor());
        } else {
            tab.setBgColor(ControlConfig.getInstance().getConfiguration().getItemBackColor());
        }        
        return tab;
    }
    
    public static ResponseTab makeLineBgColorTab(String targetId, int line, int datatype)
        throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setTargetIndex(String.valueOf(line));
        tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
        tab.setDataType(datatype);
        if (datatype == ConstantsUtil.Widget.CHK || datatype == ConstantsUtil.Widget.RADIO) {
            tab.setBgColor(getAlternateColor(line));
        } else {
            tab.setBgColor(ControlConfig.getInstance().getConfiguration().getItemBackColor());            
        }
        return tab;
        
    }
    
    public static ResponseTab makeBgColorTab(String targetId, String targetIndex, int datatype, String bgColor) 
        throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setTargetIndex(targetIndex);
        tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
        tab.setDataType(datatype);
        tab.setBgColor(bgColor);
        return tab;
        
    }

    public static ResponseTab makeGroupTab(String targetId, int type1, int type2) 
        throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setDataType(type1 * 100 + type2);
        tab.setDispStat(ConstantsUtil.Status.NO_CHANGE);

        return tab;
    }
    
    public static ResponseTab makeHiddenTab(String targetId, String value) 
        throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setValue(value);
        tab.setDataType(ConstantsUtil.Widget.HIDDEN);
        tab.setDispStat(ConstantsUtil.Status.NO_CHANGE);
        
        return tab;
    }

    public static ResponseTab makeHiddenTab(String targetId, String targetIndex, String value) 
        throws RichClientWebException {
        final ResponseTab tab = makeHiddenTab(targetId, value);
        tab.setTargetIndex(targetIndex);

        return tab;
    }

    public static ResponseTab makeTextTab(String targetId, String value) throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setValue(value);
        tab.setDataType(ConstantsUtil.Widget.TEXT);
        tab.setDispStat(ConstantsUtil.Status.NO_CHANGE);

        return tab;
    }

    public static ResponseTab makeTextTab(String targetId, String targetIndex, String value) 
        throws RichClientWebException {
        final ResponseTab tab = makeTextTab(targetId, value);
        tab.setTargetIndex(targetIndex);

        return tab;
    }
    
    public static ResponseTab initializeTextTab(String targetId) 
        throws RichClientWebException {
        final ResponseTab tab = makeTextTab(targetId, ConstantsUtil.Str.EMPTY);
        tab.setBgColor(ControlConfig.getInstance().getConfiguration().getItemBackColor());
        
        return tab;
    }
    
    public static ResponseTab makeTextBoxTab(String targetId) throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setEffect(ConstantsUtil.Status.EFFECT_YES);
        tab.setDataType(ConstantsUtil.Widget.TEXTBOX);
        tab.setDispStat(ConstantsUtil.Status.NO_CHANGE);

        return tab;
    }
    
    public static ResponseTab makeTextBoxTab(String targetId, String value) throws RichClientWebException {
        final ResponseTab tab = makeTextBoxTab(targetId);
        tab.setValue(value);
        
        return tab;
    }

    public static ResponseTab initializeTextBoxTab(String targetId) 
        throws RichClientWebException {
        final ResponseTab tab = makeTextBoxTab(targetId, ConstantsUtil.Str.EMPTY);
        tab.setBgColor(ControlConfig.getInstance().getConfiguration().getItemBackColor());
        
        return tab;
    }
    
    public static ResponseTab initializeTextBoxTab(String targetId, String value) 
        throws RichClientWebException {
        final ResponseTab tab = makeTextBoxTab(targetId, value);
        tab.setBgColor(ControlConfig.getInstance().getConfiguration().getItemBackColor());
        
        return tab;
    }

    public static ResponseTab makeCmbTab(String targetId) throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setDataType(ConstantsUtil.Widget.CMB);
        tab.setDispStat(ConstantsUtil.Status.NO_CHANGE);

        return tab;

    }

    public static ResponseTab makeCmbTab(String targetId, String value)  throws RichClientWebException {
        final ResponseTab tab = makeCmbTab(targetId);
        tab.setValue(value);

        return tab;

    }
    
    public static ResponseTab makeOptionTab(String targetId) throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setDataType(ConstantsUtil.Widget.OPTION);
        
        return tab;
        
    }
    
    public static ResponseTab makeOptionTab(String targetId, String value) throws RichClientWebException {
        return makeOptionTab(targetId, value, value);
    }
    
    public static ResponseTab makeOptionTab(String targetId, String value, String text) throws RichClientWebException {
        final ResponseTab tab = makeOptionTab(targetId);
        tab.setValue(value);
        tab.setText(text);
        
        return tab;
    }
    
    public static ResponseTab makeOptionTab(String targetId, String value, String text, String selectVal) 
        throws RichClientWebException {
        final ResponseTab tab = makeOptionTab(targetId, value, text);
        if (value != null && selectVal != null && value.equals(selectVal)) {
            tab.setSelected(true);
        }
        
        return tab;
    }

    public static ResponseTab makeBtnTab(String targetId) throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
        tab.setDataType(ConstantsUtil.Widget.BTN);
        tab.setDispStat(ConstantsUtil.Status.NO_CHANGE);

        return tab;

    }

    public static ResponseTab makeBtnTab(String targetId, String value) throws RichClientWebException {
        final ResponseTab tab = makeBtnTab(targetId);
        tab.setValue(value);

        return tab;

    }

    public static ResponseTab[] makeDisabledButtonTabs(String[] targetIds) throws RichClientWebException {
        final ResponseTab[] tabs = new ResponseTab[targetIds.length];

        for (int i = 0; i < targetIds.length; i++) {
            tabs[i] = makeBtnTab(targetIds[i], ConstantsUtil.Status.READ);
        }

        return tabs;
    }

    public static ResponseTab makeBtnTab(String targetId, int stat) throws RichClientWebException {
        final ResponseTab tab = makeBtnTab(targetId);
        tab.setDispStat(stat);

        return tab;
    }

    public static ResponseTab makeImgTab(String targetId, int stat) {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
        tab.setDataType(ConstantsUtil.Widget.IMG);
        tab.setDispStat(stat);

        return tab;
    }
    
    public static ResponseTab makeImgTab(String targetId, String value) {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setValue(value);
        tab.setDataType(ConstantsUtil.Widget.IMG);

        return tab;
        
    }
    
    public static ResponseTab makeChkTab(String targetId, int stat) throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setDataType(ConstantsUtil.Widget.CHK);
        tab.setDispStat(stat);
//        tab.setBgColor(ControlConfig.getInstance().getConfiguration().getBodyBackColor());

        return tab;        
    }
    
    public static ResponseTab makeChkTab(String targetId, String targetIndex, String value) 
        throws RichClientWebException {
        final ResponseTab tab = makeChkTab(targetId, ConstantsUtil.Status.NO_CHANGE);
        tab.setTargetIndex(targetIndex);
        tab.setValue(value);

        return tab;
    }
    
    public static ResponseTab makeChkTab(String targetId, String value) throws RichClientWebException {
        final ResponseTab tab = makeChkTab(targetId, ConstantsUtil.Status.NO_CHANGE);
        tab.setValue(value);
        
        return tab;
    }
    
    public static ResponseTab makeRadioTab(String targetId) throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        tab.setTargetId(targetId);
        tab.setDataType(ConstantsUtil.Widget.RADIO);
        tab.setDispStat(ConstantsUtil.Status.NO_CHANGE);
        
//        tab.setBgColor(ControlConfig.getInstance().getConfiguration().getBodyBackColor());
        
        return tab;
        
    }
    
    public static ResponseTab makeRadioTab(String targetId, String value, String text) throws RichClientWebException {
        final ResponseTab tab = makeRadioTab(targetId);
        tab.setValue(value);
        tab.setText(value);
        
//        tab.setBgColor(ControlConfig.getInstance().getConfiguration().getBodyBackColor());
        
        return tab;    
        
    }
    
    public static ResponseTab makeRadioTab(String targetId, int stat) throws RichClientWebException {
        final ResponseTab tab = makeRadioTab(targetId);
        tab.setDispStat(stat);
        
//        tab.setBgColor(ControlConfig.getInstance().getConfiguration().getBodyBackColor());
        
        return tab;    
        
    }
    
    public static ResponseTab makeRadioTab(String targetId, String value) 
        throws RichClientWebException {
        final ResponseTab tab = makeRadioTab(targetId);
        tab.setValue(value);
        tab.setText(value);

        return tab;
    }

    public static List<ResponseTab> setDefaultTextColor(String[] targetIds, int targetIndex) {
        final List<ResponseTab> retList = new ArrayList<ResponseTab>();

        for (int i = 0; i < targetIds.length; i++) {
            final ResponseTab tab = new ResponseTab();
            tab.setTargetId(targetIds[i]);
            tab.setTargetIndex(String.valueOf(targetIndex));
            tab.setDataType(ConstantsUtil.Widget.TEXT);
            tab.setCharColor("#000000");
            tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
            retList.add(tab);
        }

        return retList;
    }

    public static List<ResponseTab> setDefaultBgColor(String[] targetIds, int targetIndex) 
        throws RichClientWebException {
        final List<ResponseTab> retList = new ArrayList<ResponseTab>();

        for (int i = 0; i < targetIds.length; i++) {
            final ResponseTab tab = new ResponseTab();
            tab.setTargetId(targetIds[i]);
            tab.setTargetIndex(String.valueOf(targetIndex));
            tab.setDataType(ConstantsUtil.Widget.TEXT);
            tab.setBgColor(getAlternateColor(targetIndex));
            tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
            retList.add(tab);
        }

        return retList;
    }

    public static List<ResponseTab> setDefaultBgColorForCmb(String[] targetIds, int targetIndex) 
        throws RichClientWebException {
        final List<ResponseTab> retList = new ArrayList<ResponseTab>();

        for (int i = 0; i < targetIds.length; i++) {
            final ResponseTab tab = new ResponseTab();
            tab.setTargetId(targetIds[i]);
            tab.setTargetIndex(String.valueOf(targetIndex));
            tab.setDataType(ConstantsUtil.Widget.CMB);
            tab.setBgColor(getAlternateColor(targetIndex));
            tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
            retList.add(tab);
        }

        return retList;
    }

    public static String getAlternateColor(int line) throws RichClientWebException {
        final String[] colors = new String[] {
                ControlConfig.getInstance().getConfiguration().getColumnBackColor(), 
                ControlConfig.getInstance().getConfiguration().getColumnBackColorAlt(),};
        if (line == 0 || line % 2 == 0) {
            return colors[0];
        } else {
            return colors[1];
        }

    }

    public static ResponseTab[] makeHiddenImg(String[] targetId) {

        final ResponseTab[] tabs = new ResponseTab[targetId.length];

        for (int nCnt = 0; nCnt < targetId.length; nCnt++) {
            final ResponseTab tab = new ResponseTab();
            tab.setTargetId(targetId[nCnt]);
            tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
            tab.setDataType(ConstantsUtil.Widget.IMG);
            tab.setDispStat(ConstantsUtil.Status.HIDE);
            tabs[nCnt] = tab;
        }

        return tabs;
    }

    public static String judgeCheck(Object dtlPno) {

        String val = ConstantsUtil.Status.CHECK_ON;

        if (dtlPno == null || (dtlPno != null && "".equals(dtlPno))) {
            val = ConstantsUtil.Status.CHECK_OFF;
        }

        return val;
    }
    
    public static void getResTab(List<ResponseTab> tabList, String targetId, int type) 
        throws RichClientWebEditException {
        getResTab(tabList, targetId, ConstantsUtil.Str.EMPTY, type, true);
    }
    
    public static void getResTab(List<ResponseTab> tabList, String targetId, String data, int type) 
        throws RichClientWebEditException {
        getResTab(tabList, targetId, data, type, false);
    }
    
    public static void getResTab(List<ResponseTab> tabList, String targetId, 
            String data, int type, boolean isCheck) throws RichClientWebEditException {
        
        ResponseTab resTab = new ResponseTab();
        
        try {
            switch (type) {
                case ConstantsUtil.Widget.TEXT:
                    resTab = makeTextTab(targetId, data);
                    break;
                case ConstantsUtil.Widget.TEXTBOX:
                    if (isCheck) {
                        resTab = makeTextBoxTab(targetId);
                    } else {
                        resTab = makeTextBoxTab(targetId, data);
                    }
                    break;
                case ConstantsUtil.Widget.CMB:
                    resTab = makeCmbTab(targetId, data);
                    break;
                case ConstantsUtil.Widget.CHK:
                    resTab = makeChkTab(targetId, data);
                    break;
                case ConstantsUtil.Widget.BTN:
                    resTab = makeBtnTab(targetId);
                    break;
                case ConstantsUtil.Widget.HIDDEN:
                    resTab = makeHiddenTab(targetId + "_h", data);
                    break;
                default:
                    break;
            }
        } catch (RichClientWebEditException e) {
            e.printStackTrace();
            sLog.fatal(e.getMessage());
            throw new RichClientWebEditException(EngineExceptionEnum.UR_COM_READ_ERROR, e);
        } catch (Exception e) {
            e.printStackTrace();
            sLog.fatal(EngineExceptionEnum.FM_COM_SYSTEM_ERROR.getErrinfo() + e.toString());
            throw new RichClientWebEditException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } 
        
        tabList.add(resTab);
    }
    
    public static AbstractResponseData showCmbs(String targetId, TableRowMap[] datas, String cmbValue)
        throws RichClientWebException {

        sLog.debug("showCmbs", "start");

        AbstractResponseData resData = null;
        
        final ResponseList dataList = new ResponseList();

        ResponseHeader header = null;

        try {
            for (int i = 0; i < datas.length; i++) {
                
                final ResponseLine dataLine = new ResponseLine();

                ResponseTab cmb = null;
                if (datas[i].containsKey(ConstantsUtil.Cmb.VALUE)) {
                    cmb = makeOptionTab(targetId, 
                            CommonUtil.toString(datas[i].get(ConstantsUtil.Cmb.VALUE)), 
                            CommonUtil.toString(datas[i].get(ConstantsUtil.Cmb.LABEL)), cmbValue);
                } else if (datas[i].containsKey(ConstantsUtil.Cmb.VALUE.toUpperCase())) {
                    cmb = makeOptionTab(targetId, 
                            CommonUtil.toString(datas[i].get(ConstantsUtil.Cmb.VALUE.toUpperCase())), 
                            CommonUtil.toString(datas[i].get(ConstantsUtil.Cmb.LABEL.toUpperCase())), cmbValue);
                }
                if (cmb != null) {
                    dataLine.put(targetId, cmb);
                    dataList.addLine(dataLine);
                }
            }

            header = new ResponseHeader();
        } catch (Exception e) {
            e.printStackTrace();
            sLog.fatal(EngineExceptionEnum.FM_COM_SYSTEM_ERROR.getErrinfo() + e.toString());
            throw new RichClientWebEditException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } finally {
            resData = new ResponseData(header, ConstantsUtil.Widget.CMB);
            resData.setDataList(dataList);
        }
        
        sLog.debug("showCmbs", "end");

        return resData;
    }

}
