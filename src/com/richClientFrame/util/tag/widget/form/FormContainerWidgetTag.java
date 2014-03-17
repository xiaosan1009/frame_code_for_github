
package com.richClientFrame.util.tag.widget.form;

import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.tag.BodyTagSupportBase;

import java.io.IOException;

import javax.servlet.jsp.JspException;

public class FormContainerWidgetTag extends BodyTagSupportBase {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private StringBuffer outerPrint;

    private String title;

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_AGAIN;
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            outerPrint = new StringBuffer();
            final String htmlContent = this.getBodyContent().getString();
            outerPrint.append("<div id=\"formMain\">");
            if (CommonUtil.isNotEmpty(title)) {
                outerPrint.append("<div class=\"formTr\">");
                outerPrint.append("<span id=\"formHeader\" class=\"formTh\">" + getResourceInfo(title) + "</span>");
                outerPrint.append("</div>");
            }
            outerPrint.append(htmlContent);
            outerPrint.append("</div>");
            this.getBodyContent().getEnclosingWriter().print(outerPrint.toString());
            this.getBodyContent().clearBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
