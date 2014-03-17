package com.richClientFrame.util.tag.widget;

import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.tag.BodyTagSupportBase;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

/** 
* @ClassName: ErrMessageTag 
* @Description: ������Ϣ��ǩ
* @author king
* @since Sep 11, 2012 3:23:40 PM 
*  
*/
public class ErrMessageTag extends BodyTagSupportBase {
    
    /**
     * ���л�ID
     */
    private static final long serialVersionUID = 1L;

    private StringBuffer outerPrint;
    
    private String id;
    
    private String classStyle;
    
    private String msClassStyle;
    
    private String message;
    
    private String style;
    
    private String msStyle;

    /**
     * ���ɱ�ǩǰ����.
     * 
     * @return ���ɽ��
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        outerPrint = new StringBuffer();
        outerPrint.append("<span id=\"" + id + "_msg\" " 
                + createMsClass() + createMsStyle() + ">" + message + "</span>");
        outerPrint.append("<span id=\"" + id + "_err\"" 
                + createClass() + createStyle() + "></span>");
        return SKIP_BODY;
    }
    
    /** 
    * @Description: ���ɴ�����Ϣclass
    * @author king
    * @since Nov 3, 2012 11:45:46 PM 
    * 
    * @return ��ʾ��Ϣclass
    */
    private String createClass() {
        if (CommonUtil.isEmpty(classStyle)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return " class=\"" + classStyle + " hide\"";
    }
    
    /** 
     * @Description: ���ɴ�����Ϣclass
     * @author king
     * @since Nov 3, 2012 11:45:46 PM 
     * 
     * @return ��ʾ��Ϣclass
     */
    private String createStyle() {
        if (CommonUtil.isEmpty(style)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return " style=\"" + style + "\"";
    }
    
    /** 
     * @Description: ������ʾ��Ϣclass
     * @author king
     * @since Nov 3, 2012 11:45:46 PM 
     * 
     * @return ��ʾ��Ϣclass
     */
    private String createMsClass() {
        if (CommonUtil.isEmpty(msClassStyle)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return " class=\"" + msClassStyle + "\"";
    }
    
    /** 
     * @Description: ������ʾ��Ϣclass
     * @author king
     * @since Nov 3, 2012 11:45:46 PM 
     * 
     * @return ��ʾ��Ϣclass
     */
    private String createMsStyle() {
        if (CommonUtil.isEmpty(msStyle)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return " style=\"" + msStyle + "\"";
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

    /** 
     * @Description: ���ÿؼ�ID
     * @author king
     * @since Nov 3, 2012 11:34:33 PM 
     * 
     * @param id �ؼ�ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /** 
    * @Description: ����class
    * @author king
    * @since Nov 3, 2012 11:34:33 PM 
    * 
    * @param classStyle ��ʽ
    */
    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }
    
    /** 
    * @Description: ������ʾ��Ϣ
    * @author king
    * @since Nov 3, 2012 11:35:09 PM 
    * 
    * @param message ��ʾ��Ϣ
    */
    public void setMessage(String message) {
        this.message = message;
    }

    /** 
    * @Description: ������ʽ��
    * @author king
    * @since Nov 3, 2012 11:40:02 PM 
    * 
    * @param style ��ʽ��
    */
    public void setStyle(String style) {
        this.style = style;
    }

    /** 
    * @Description: ������ʾ��Ϣclass
    * @author king
    * @since Nov 3, 2012 11:41:56 PM 
    * 
    * @param msClass ��ʾ��Ϣclass
    */
    public void setMsClass(String msClass) {
        this.msClassStyle = msClass;
    }

    /** 
     * @Description: ������ʾ��Ϣ��ʽ��
     * @author king
     * @since Nov 3, 2012 11:40:02 PM 
     * 
     * @param msStyle ��ʾ��Ϣ��ʽ��
     */
    public void setMsStyle(String msStyle) {
        this.msStyle = msStyle;
    }
}
