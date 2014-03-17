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
public class FormSubmitWidgetTag extends BodyTagSupportBase {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private StringBuffer outerPrint;
    
    private String label;
    
    private String id;
    
    private boolean focus;
    
    private String title;
    
    private String style;

    /**
     * ���ɱ�ǩǰ����.
     * 
     * @return ���ɽ��
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        outerPrint = new StringBuffer();
        outerPrint.append("<div class=\"formTr\" style=\"" + CommonUtil.toString(style) + "\">");
        outerPrint.append("    <a title=\"" + getResourceInfo(title) + "\" id=\"" 
                + id + "\" class=\"btn\" value=\"" 
                + getResourceInfo(label) + "\"/>");
        outerPrint.append("</div>");
        return SKIP_BODY;
    }

    /**
     * ���ɱ�ǩ�󷽷�.
     * 
     * @return ���ɽ��
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

    public void setStyle(String style) {
        this.style = style;
    }
}
