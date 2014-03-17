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
     * 数据数组
     */
    private ResponseList dataList;
    
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
     * 生成标签前方法.
     * 
     * @return 生成结果
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
     * 生成标签后方法.
     * 
     * @return 生成结果
     * @throws JspException JspException
     */
    public int doEndTag() throws JspException {
        ((CommonListTag)findAncestorWithClass(this, 
                CommonListTag.class)).setDimensionHtml(outerPrint.toString());
        return EVAL_PAGE;
    }
}
