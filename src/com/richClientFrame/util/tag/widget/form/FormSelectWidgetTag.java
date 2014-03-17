package com.richClientFrame.util.tag.widget.form;

import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.tag.BodyTagSupportBase;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

/**
 * ��ҳ��ǩ.
 * @author king
 * @since 2011.03.06
 */
public class FormSelectWidgetTag extends BodyTagSupportBase {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private StringBuffer outerPrint;
    
    private String label;
    
    private String id;
    
    private boolean focus;
    
    private String title;
    
    private String message;
    
    private String style;

    /**
     * ���ɱ�ǩǰ����.
     * 
     * @return ���ɽ��
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        return EVAL_BODY_AGAIN;
    }
    
    @Override
    public int doAfterBody() throws JspException {
        try {
            outerPrint = new StringBuffer();
            final String htmlContent = this.getBodyContent().getString();
            outerPrint = new StringBuffer();
            outerPrint.append("<div class=\"formTr formMesTr\" style=\"" + CommonUtil.toString(style) + "\">");
            outerPrint.append("    <div class=\"formMesLeft\">");
            outerPrint.append("        <span class=\"formTh\">" + getResourceInfo(label) + "</span>");
            outerPrint.append("        <select class=\"formTd " + addFocusStyle() + "\" title=\"" 
                    + getResourceInfo(title) + "\" id=\"" + id + "\" name=\"" + id + "\">");
            outerPrint.append(htmlContent);
            outerPrint.append("        </select>");
            outerPrint.append("        <span id=\"" + id + "_msg\" class=\"formMesMst\">" + message + "</span>");
            outerPrint.append("        <span id=\"" + id + "_err\" class=\"formMesErr hide\"></span>");
            outerPrint.append("    </div>");
            outerPrint.append("</div>");
            this.getBodyContent().getEnclosingWriter().print(outerPrint.toString());
            this.getBodyContent().clearBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    private String addFocusStyle() {
        if (focus) {
            return "focusItem";
        }
        return ConstantsUtil.Str.EMPTY;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
