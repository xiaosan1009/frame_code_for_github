package com.richClientFrame.util.tag;

import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.util.HtmlUtil;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

/**
 * 分页标签.
 * @author king
 * @since 2011.03.06
 */
public class CommonHeaderDisplayTag extends BodyTagSupportBase {
    
    private static final long serialVersionUID = 1L;
    
    private StringBuffer outerPrint;

    /**
     * 生成标签前方法.
     * 
     * @return 生成结果
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        final AbstractResponseData resData = 
            (AbstractResponseData)pageContext.getRequest().getAttribute(AbstractResponseData.KEY_RESPONSE_DATA);
        outerPrint = new StringBuffer();
        if (resData != null) {
            outerPrint.append(HtmlUtil.createResponseHeader(resData));
        }
        return SKIP_BODY;
    }

    /**
     * 生成标签后方法.
     * 
     * @return 生成结果
     * @throws JspException JspException
     */
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().print(outerPrint.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
