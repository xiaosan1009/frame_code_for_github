package com.richClientFrame.util.tag;

import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.TagUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * list tag.
 * @author king
 * @since 2011.03.06
 */
public class CommonDimensionListTag extends BodyTagSupportBase {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * ��������
     */
    private ResponseList dataList;
    
    /**
     * �����
     */
    private int listCurrentIndex;
    
    /**
     * �����Ϣ
     */
    private StringBuffer outerPrint;
    
    /**
     * ��ǩ����Ϣ
     */
    private String htmlBodyCode;

    /**
     * ���ɱ�ǩǰ����.
     * 
     * @return ���ɽ��
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        
        outerPrint = new StringBuffer();
        listCurrentIndex = 0;
        if (pageContext.getAttribute(ConstantsUtil.Tag.DIMENSION) == null) {
            return SKIP_BODY;
        }
        dataList = (ResponseList)pageContext.getAttribute(ConstantsUtil.Tag.DIMENSION);
        if (dataList == null || listCurrentIndex >= dataList.size()) {
            return SKIP_BODY;
        }
        pageContext.setAttribute(ConstantsUtil.Tag.DIMENSION, null);
        return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public int doAfterBody() throws JspException {
        if (listCurrentIndex >= dataList.size()) {
            return SKIP_BODY;
        }
        if (listCurrentIndex == 0) {
            final BodyContent body = getBodyContent();
            htmlBodyCode = body.getString();
        }
        final ResponseLine resLine = dataList.getLine(listCurrentIndex);
        resLine.setListLineIndex(listCurrentIndex);
        outerPrint.append(TagUtil.resolvePlaceholders(htmlBodyCode, resLine));
        listCurrentIndex++;
        return EVAL_BODY_AGAIN;
    }

    /**
     * ���ɱ�ǩ�󷽷�.
     * 
     * @return ���ɽ��
     * @throws JspException JspException
     */
    public int doEndTag() throws JspException {
        ((CommonListTag)findAncestorWithClass(this, 
                CommonListTag.class)).setDimensionHtml(outerPrint.toString());
        return EVAL_PAGE;
    }
}
