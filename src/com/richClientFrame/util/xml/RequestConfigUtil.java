package com.richClientFrame.util.xml;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlResourceMap;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.CreateSequencesUtil;
import com.richClientFrame.util.DateUtils;
import com.richClientFrame.util.HtmlTabUtil;
import com.richClientFrame.util.LogUtil;
import com.richClientFrame.util.ConstantsUtil.Client;
import com.richClientFrame.util.ConstantsUtil.Result;
import com.richClientFrame.util.ConstantsUtil.Str;
import com.richClientFrame.util.ConstantsUtil.Widget;
import com.richClientFrame.util.ConstantsUtil.Xsd;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/** 
* @ClassName: RequestConfigUtil 
* @Description: request.xml����Ϣ������
* @author king
* @since Oct 8, 2012 10:00:51 AM 
*  
*/
public final class RequestConfigUtil {
    
    private static LogUtil sLog = new LogUtil(RequestConfigUtil.class, true);
    
    /**
     * ���캯��
     */
    private RequestConfigUtil() {
    }

    /**
     * ���ݱ�ǩ�еķ��ŷ�����Ӧ��ֵ.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param value ת��ǰ��ֵ
     * @throws RichClientWebException RichClientWebException
     * @return ת�����ֵ
     */
    public static String getRequestValue(
            Param param, 
            SessionData seData,
            String value) throws RichClientWebException {
        return getRequestValue(null, param, seData, value);
    }
    
    /**
     * ���ݱ�ǩ�еķ��ŷ�����Ӧ��ֵ.
     * 
     * @param tab ���� <����Ϊ��>
     * @param param �û��������
     * @param seData �û��Ự����
     * @param value ת��ǰ��ֵ
     * @throws RichClientWebException RichClientWebException
     * @return ת�����ֵ
     */
    public static String getRequestValue(
            TableRowMap tab, 
            Param param, 
            SessionData seData,
            String value) throws RichClientWebException {
        return getRequestValue(tab, param, seData, value, null);
    }
    
    /**
     * ���ݱ�ǩ�еķ��ŷ�����Ӧ��ֵ.
     * 
     * @param tab ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param value ת��ǰ��ֵ
     * @param index �ؼ������к�
     * @throws RichClientWebException RichClientWebException
     * @return ת�����ֵ
     */
    public static String getRequestValue(
            TableRowMap tab, 
            Param param, 
            SessionData seData,
            String value, String index) throws RichClientWebException {
        return getRequestValue(tab, param, seData, value, index, null);
    }
    
    /**
     * ���ݱ�ǩ�еķ��ŷ�����Ӧ��ֵ.
     * 
     * @param tab ֮ǰ���������ݲ�ѯ�Ľ����Ϣ
     * @param param �û��������
     * @param seData �û��Ự����
     * @param value ת��ǰ��ֵ
     * @param index �ؼ������к�
     * @param targetId ����ؼ�ID
     * @throws RichClientWebException RichClientWebException
     * @return ת�����ֵ
     */
    public static String getRequestValue(
            TableRowMap tab, 
            Param param, 
            SessionData seData,
            String value, String index, String targetId) throws RichClientWebException {
        sLog.debug("getRequestValue", "start", "value = " + value, "targetId = " + targetId, "tab = " + tab);
        if (CommonUtil.isEmpty(value)) {
            return value;
        }
        if (ConstantsUtil.Xsd.RESULT.equals(value)) {
            return value;
        }
        if (value.startsWith(ConstantsUtil.Xsd.DB_SIGN)) {
            if (tab != null) {
                final String key = value.substring(1);
                if (tab.containsKey(targetId, key)) {
                    value = tab.getString(targetId, key);
                } else {
                    sLog.debug("getRequestValue", null, "key = " + key, "contains = " + tab.containsKey(key));
                    if (tab.containsKey(key)) {
                        value = tab.getString(key);
                    } else if (tab.containsKey(key.toUpperCase())) {
                        value = tab.getString(key.toUpperCase());
                    } else {
                        value = ConstantsUtil.Str.EMPTY;
                    }
                }
            } else {
                value = ConstantsUtil.Str.EMPTY;
            }
        } else if (value.startsWith(ConstantsUtil.Xsd.REQUEST_SIGN)) {
            if (CommonUtil.isEmpty(index)) {
                value = CommonUtil.toString(param.get(value.substring(ConstantsUtil.Xsd.REQUEST_SIGN.length())));
            } else {
//                if (param.get(value.substring(1)).length() <= 1) {
//                    value = CommonUtil.toString(param.get(value.substring(1)));
//                } else {
//                }
                value = CommonUtil.toString(param.get(value.substring(ConstantsUtil.Xsd.REQUEST_SIGN.length()), 
                        Integer.parseInt(index)));
            }
        } else if (value.startsWith(ConstantsUtil.Xsd.FMT)) {
            final String fmt = value.substring(ConstantsUtil.Xsd.FMT.length());
            value = ControlResourceMap.getInstance().getItemName(param.dispCode, fmt);
        } else if (value.startsWith(ConstantsUtil.Xsd.USER)) {
            final String userKey = value.substring(ConstantsUtil.Xsd.USER.length());
            if (seData == null || seData.getUserInfo() == null) {
                final boolean sessionAllowNull = CommonUtil.toBoolean(
                        param.getValue(ConstantsUtil.Xsd.PARAM_SESSION_ALLOW_NULL));
                if (sessionAllowNull) {
                    return ConstantsUtil.Str.EMPTY;
                } else {
                    final RichClientWebException e = new RichClientWebException(
                            EngineExceptionEnum.UR_COM_SESSION_TIMEOUT);
                    e.setInformation(true);
                    throw e;
                }
            }
            value = seData.getUserInfo().getUserInfo(userKey);
        } else if (value.startsWith(ConstantsUtil.Xsd.SYSDATE)) {
            value = CommonUtil.toString(getSysdate(value));
        } else if (value.startsWith(ConstantsUtil.Xsd.SEQUENCE)) {
            value = CommonUtil.toString(getSequence(seData, value));
        } else if (value.startsWith(ConstantsUtil.Xsd.SESSION_ID)) {
            value = seData.getSessionId();
        } else if (value.startsWith(ConstantsUtil.Xsd.SESSION_SIGN)) {
            value = seData.getString(value.substring(ConstantsUtil.Xsd.SESSION_SIGN.length()));
        }
        return value;
    }
    
    /**
     * ת����long����.
     * 
     * @param value ����
     * @return ת�����ֵ
     */
    public static BigDecimal toNumber(String value) {
        value = toNumber(value, ConstantsUtil.Str.SLASH);
        value = toNumber(value, ConstantsUtil.Str.COLON);
        value = toNumber(value, ConstantsUtil.Str.LINE);
        if (value.indexOf(ConstantsUtil.Str.BLANK) != -1) {
            value = value.replaceAll(ConstantsUtil.Str.BLANK, "");
        }
        return new BigDecimal(value);
    }
    
    /** 
    * @Description: ���ַ���ת������ֵ���ַ���
    * @author king
    * @since Oct 8, 2012 10:50:00 AM 
    * 
    * @param value ת����ֵ
    * @param str �ָ���
    * @return ת�����ֵ
    */
    private static String toNumber(String value, String str) {
        if (value.indexOf(str) != -1) {
            final String[] values = value.split(str);
            final StringBuffer valueBuff = new StringBuffer();
            for (int i = 0; i < values.length; i++) {
                valueBuff.append(completionValue(values[i]));
            }
            value = valueBuff.toString();
        }
        return value;
    }
    
    /** 
    * @Description: ���ݲ���0
    * @author king
    * @since Oct 8, 2012 11:00:30 AM 
    * 
    * @param value �����ֵ
    * @return ������ֵ
    */
    private static String completionValue(String value) {
        if (value.length() == 1) {
            value = "0" + value;
        }
        return value;
    }
    
    /**
     * ������Ŀ��������.
     * 
     * @param param �û��������
     * @param type �ؼ�����
     * @param targetId �ؼ�ID
     * @param value �ؼ�ֵ
     * @return ������Ŀ
     * @throws RichClientWebException RichClientWebException
     */
    public static ResponseTab createItem(Param param, int type, String targetId, String value) 
        throws RichClientWebException {
        return createItem(param, type, targetId, value, false);
    }
    
    /**
     * ������Ŀ��������.
     * 
     * @param param �û��������
     * @param type �ؼ�����
     * @param targetId �ؼ�ID
     * @param value �ؼ�ֵ
     * @param isList �Ƿ�Ϊ����Ŀ����[Ϊ���ж��Ƿ������תΪ��ȫ��ʽ]
     * @return ������Ŀ
     * @throws RichClientWebException RichClientWebException
     */
    public static ResponseTab createItem(Param param, int type, String targetId, String value, boolean isList) 
        throws RichClientWebException {
        return createItem(param, type, targetId, value, null, isList, null);
    }
    
    /**
     * ������Ŀ��������.
     * 
     * @param param �û��������
     * @param type �ؼ�����
     * @param targetId �ؼ�ID
     * @param value �ؼ�ֵ
     * @param text ��ʾֵ
     * @param isList �Ƿ�Ϊ����Ŀ����[Ϊ���ж��Ƿ������תΪ��ȫ��ʽ]
     * @return ������Ŀ
     * @throws RichClientWebException RichClientWebException
     */
    public static ResponseTab createItem(
            Param param, int type, String targetId, String value, String text, boolean isList) 
        throws RichClientWebException {
        return createItem(param, type, targetId, value, text, isList, null);
    }
    
    /**
     * ������Ŀ��������.
     * 
     * @param param �û��������
     * @param type �ؼ�����
     * @param targetId �ؼ�ID
     * @param value �ؼ�ֵ
     * @param text ��ʾֵ
     * @param isList �Ƿ�Ϊ����Ŀ����[Ϊ���ж��Ƿ������תΪ��ȫ��ʽ]
     * @param selectVal ѡ�е�ֵ
     * @return ������Ŀ
     * @throws RichClientWebException RichClientWebException
     */
    public static ResponseTab createItem(
            Param param, int type, String targetId, String value, String text, boolean isList, String selectVal) 
        throws RichClientWebException {
        return createItem(param, type, targetId, value, text, null, isList, selectVal);
    }
    
    /**
     * ������Ŀ��������.
     * 
     * @param param �û��������
     * @param type �ؼ�����
     * @param targetId �ؼ�ID
     * @param value �ؼ�ֵ
     * @param text ��ʾֵ
     * @param cmbMaps ��������Ϣ
     * @param isList �Ƿ�Ϊ����Ŀ����[Ϊ���ж��Ƿ������תΪ��ȫ��ʽ]
     * @param selectVal selectVal
     * @return ������Ŀ
     * @throws RichClientWebException RichClientWebException
     */
    public static ResponseTab createItem(
            Param param, 
            int type, 
            String targetId, 
            String value, 
            String text, 
            Map<String, TableRowMap[]> cmbMaps, 
            boolean isList, 
            String selectVal) 
        throws RichClientWebException {
        ResponseTab tab = null;
        if (!isList) {
            // HTML��������ת��(��ֹxss©��)
            value = escapeHtml(param, value);
        }
        switch (type) {
            case ConstantsUtil.Widget.TEXT:
                if (CommonUtil.isEmpty(value)) {
                    tab = HtmlTabUtil.initializeTextTab(targetId);
                } else {
                    tab = HtmlTabUtil.makeTextTab(targetId, value);
                }
                break;
            case ConstantsUtil.Widget.TEXTBOX:
                if (CommonUtil.isEmpty(value)) {
                    tab = HtmlTabUtil.initializeTextBoxTab(targetId);
                } else {
                    tab = HtmlTabUtil.initializeTextBoxTab(targetId, value);
                }
                break;
            case ConstantsUtil.Widget.CHK:
                tab = HtmlTabUtil.makeChkTab(targetId, value);
                break;
            case ConstantsUtil.Widget.CMB:
                tab = HtmlTabUtil.makeCmbTab(targetId, value);
                break;
            case ConstantsUtil.Widget.RADIO:
                tab = HtmlTabUtil.makeRadioTab(targetId, value);
                break;
            case ConstantsUtil.Widget.BTN:
                tab = HtmlTabUtil.makeBtnTab(targetId, value);
                break;
            case ConstantsUtil.Widget.IMG:
                tab = HtmlTabUtil.makeImgTab(targetId, value);
                break;
            case ConstantsUtil.Widget.OPTION:
                if (CommonUtil.isEmpty(text)) {
                    text = value;
                }
                tab = HtmlTabUtil.makeOptionTab(targetId, value, text, selectVal);
                break;
            case ConstantsUtil.Widget.HIDDEN:
                tab = HtmlTabUtil.makeHiddenTab(targetId, value);
                break;
            default:
                break;
        }
        if (cmbMaps != null && cmbMaps.containsKey(targetId)) {
            final AbstractResponseData cmbs = HtmlTabUtil.showCmbs(targetId, cmbMaps.get(targetId), value);
            if (tab.getCmbList() == null) {
                final Map<String, AbstractResponseData> cmbMap = new HashMap<String, AbstractResponseData>();
                tab.setCmbList(cmbMap);
            }
            tab.getCmbList().put(targetId, cmbs);
        }
        return tab;
    }
    
    /** 
     * @Description: HTML��������ת��(��ֹxss©��)
     * @author king
     * @since Oct 8, 2012 9:49:10 AM 
     * 
     * @param param �ͻ����������
     * @param str ת����ֵ
     * @return ת�����ֵ
     */
    private static String escapeHtml(Param param, String str) {
        if (ConstantsUtil.Client.AJAX_TYPE.equals(param.clientType)) {
            return CommonUtil.escapeHtml(str);
        }
        return str;
    }
    
    /** 
     * @Description: ȡ������
     * @author king
     * @since Oct 8, 2012 9:56:46 AM 
     * 
     * @param seData session��Ϣ
     * @param valueStr ��<request.xml>�����õĲ���
     * @return ���ɵ�����ֵ
     */
    public static Object getSequence(SessionData seData, final String valueStr) {
        Object value;
        final String[] sequenceDatas = valueStr.split(ConstantsUtil.Str.BLANK);
        if (sequenceDatas.length > 1) {
            value = CreateSequencesUtil.createID(sequenceDatas[1], seData);
        } else {
            value = CreateSequencesUtil.createID(seData);
        }
        return value;
    }

     /** 
     * @Description: ȡ��ϵͳ����
     * @author king
     * @since Oct 8, 2012 9:58:06 AM 
     * 
     * @param valueStr ��<request.xml>�����õĲ���
     * @return ϵͳ����
     */
    public static Object getSysdate(final String valueStr) {
        Object value;
        final String[] sysdateDatas = valueStr.split(ConstantsUtil.Str.BLANK);
        if (sysdateDatas.length > 1) {
            value = DateUtils.getNowTime(sysdateDatas[1]);
        } else {
            value = DateUtils.getNowTime(DateUtils.FORMAT_YMD2);
        }
        return value;
    }

}
