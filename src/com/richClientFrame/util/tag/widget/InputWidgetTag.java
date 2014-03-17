package com.richClientFrame.util.tag.widget;

import com.richClientFrame.util.tag.BodyTagSupportBase;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

/**
 * 分页标签.
 * @author king
 * @since 2011.03.06
 */
public class InputWidgetTag extends BodyTagSupportBase {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private StringBuffer outerPrint;
    
    private String id;
    
    private String value;
    
    private String width;
    
    private String height;
    
    private String maxlength;
    
    private boolean focus;
    
    private boolean disabled;
    
    private boolean read;
    
    private String title;
    
    private String style;
    
    private String classStyle;

    /**
     * 生成标签前方法.
     * 
     * @return 生成结果
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        outerPrint = new StringBuffer();
        outerPrint.append("<input ");
        outerPrint.append(setIdItem(id));
        outerPrint.append(setWidthItem(width));
        outerPrint.append(setHeightItem(height));
        outerPrint.append(setMaxlengthItem(maxlength));
        outerPrint.append(setTitleItem(title));
        outerPrint.append(setClassItem(classStyle, focus));
        outerPrint.append(setStyleItem(style));
        outerPrint.append(setReadOnly(read));
        outerPrint.append(setDisable(disabled));
        outerPrint.append(" type=\"text\" />");
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

    public void setClass(String classStyle) {
        this.classStyle = classStyle;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setMaxlength(String maxlength) {
        this.maxlength = maxlength;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
