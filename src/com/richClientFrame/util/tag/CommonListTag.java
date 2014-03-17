package com.richClientFrame.util.tag;

import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseDimensions;
import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.TagUtil;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * list tag.
 * @author king
 * @since 2011.03.06
 */
public class CommonListTag extends BodyTagSupportBase {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 项目ID
     */
    private String targetId;
    
    /**
     * 数据数组
     */
    private ResponseList dataList;
    
    /**
     * 数据数组
     */
    private ResponseDimensions dimensionList;
    
    /**
     * 下拉框信息对象
     */
    private ResponseLine cmbLine;
    
    /**
     * 行序号
     */
    private int listCurrentIndex;
    
    /**
     * 输出信息
     */
    private StringBuffer outerPrint;
    
    /**
     * 标签体信息
     */
    private String htmlBodyCode;

    /**
     * 项目ID设定.
     * 
     * @param targetId 项目ID
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * 生成标签前方法.
     * 
     * @return 生成结果
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        
        outerPrint = new StringBuffer();
        listCurrentIndex = 0;
        final AbstractResponseData resData = (AbstractResponseData)pageContext.getRequest()
            .getAttribute(AbstractResponseData.KEY_RESPONSE_DATA);
        AbstractResponseData resList1 = null;
        if (resData == null) {
            return SKIP_BODY;
        } else {
            resList1 = AbstractResponseData.getAbstractResponseData(resData, targetId);
            if (resList1 == null) {
                return SKIP_BODY;
            }
            dimensionList = resList1.getDimensionList();
            if (dimensionList == null) {
                dataList = resList1.getDataList();
            }
            if (dimensionList == null && dataList == null) {
                return SKIP_BODY;
            }
            if ((dimensionList != null && listCurrentIndex >= dimensionList.size()) 
                    || (dataList != null && listCurrentIndex >= dataList.size())) {
                return SKIP_BODY;
            }
            
        }
        return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public int doAfterBody() throws JspException {
        if ((dimensionList != null && listCurrentIndex >= dimensionList.size()) 
                || (dataList != null && listCurrentIndex >= dataList.size())) {
            return SKIP_BODY;
        }
        if (listCurrentIndex == 0) {
            final BodyContent body = getBodyContent();
            htmlBodyCode = body.getString();
            if (dimensionList != null) {
                setDimensionSign();
            } else if (dataList.isHasCmbs()) {
                setCmbSign();
            }
        }
        ResponseLine resLine = null;
        if (dataList != null) {
            resLine = dataList.getLine(listCurrentIndex);
            if (dataList.isHasCmbs()) {
                pageContext.getSession().setAttribute(ConstantsUtil.Tag.CMB_ITEMS, dataList.getLine(listCurrentIndex));
            }
        } else {
            pageContext.setAttribute(ConstantsUtil.Tag.DIMENSION, dimensionList.getDimension(listCurrentIndex));
            resLine = dimensionList.getDimension(listCurrentIndex).getDimensionLine();
        }
        resLine.setListLineIndex(listCurrentIndex);
        outerPrint.append(TagUtil.resolvePlaceholders(htmlBodyCode, resLine));
        listCurrentIndex++;
        return EVAL_BODY_AGAIN;
    }
    
    /**
     * 生成标签后方法.
     * 
     * @return 生成结果
     * @throws JspException JspException
     */
    public int doEndTag() throws JspException {
        try {
            pageContext.getSession().removeAttribute(ConstantsUtil.Tag.CMB_ITEMS);
            pageContext.getOut().print(outerPrint.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
    /**
     * 设置二维列表数据占位标识.
     * 
     */
    private void setDimensionSign() {
        char b = 0;
        final StringBuffer buff = new StringBuffer();
        for (int i = 0; i < htmlBodyCode.length(); i++) {
            if (i != 0) {
                b = htmlBodyCode.charAt(i - 1);
            }
            final char a = htmlBodyCode.charAt(i);
            if (a == 13 && b == 9) {
                buff.append("#{code}");
            }
            buff.append(a);
        }
        htmlBodyCode = buff.toString();
    }
    
    /**
     * 设置二维列表数据到画面数据.
     * 
     * @param html 二维列表数据
     * 
     */
    public void setDimensionHtml(String html) {
        final StringBuffer buff = new StringBuffer(outerPrint.toString().replace("#{code}", html));
        outerPrint = buff;
    }
    
    /**
     * 设置下拉框数据占位标识.
     * 
     */
    private void setCmbSign() {
        char b = 0;
        final StringBuffer buff = new StringBuffer();
        for (int i = 0; i < htmlBodyCode.length(); i++) {
            if (i != 0) {
                b = htmlBodyCode.charAt(i - 1);
            }
            final char a = htmlBodyCode.charAt(i);
            if (a == 13 && b == 9) {
                buff.append("#{cmb}");
            }
            buff.append(a);
        }
        htmlBodyCode = buff.toString();
    }
    
    /**
     * 设置下拉框数据到画面数据.
     * 
     * @param html 二维列表数据
     * 
     */
    public void setCmbHtml(String html) {
        if (outerPrint.toString().indexOf("#{cmb}") != -1) {
            final StringBuffer buff = new StringBuffer(outerPrint.toString().replaceFirst("#\\{cmb\\}", html));
            outerPrint = buff;
        }
    }
    
//    public static void main(String[] args) {
//        String a = "aaaaaaaaaaaaaaaaaaaa#{cmb}bbbbbbbbbbbbbbbbbbbbbbbb#{cmb}cccccccccccccccc";
//        System.out.println(a.replaceFirst("#\\{cmb\\}", "****"));
//    }
}
