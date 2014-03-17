package com.richClientFrame.util.tag.widget;

import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.tag.BodyTagSupportBase;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

/** 
* @ClassName: ErrMessageTag 
* @Description: 错误信息标签
* @author king
* @since Sep 11, 2012 3:23:40 PM 
*  
*/
public class ErrMessageTag extends BodyTagSupportBase {
    
    /**
     * 序列化ID
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
     * 生成标签前方法.
     * 
     * @return 生成结果
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
    * @Description: 生成错误信息class
    * @author king
    * @since Nov 3, 2012 11:45:46 PM 
    * 
    * @return 提示信息class
    */
    private String createClass() {
        if (CommonUtil.isEmpty(classStyle)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return " class=\"" + classStyle + " hide\"";
    }
    
    /** 
     * @Description: 生成错误信息class
     * @author king
     * @since Nov 3, 2012 11:45:46 PM 
     * 
     * @return 提示信息class
     */
    private String createStyle() {
        if (CommonUtil.isEmpty(style)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return " style=\"" + style + "\"";
    }
    
    /** 
     * @Description: 生成提示信息class
     * @author king
     * @since Nov 3, 2012 11:45:46 PM 
     * 
     * @return 提示信息class
     */
    private String createMsClass() {
        if (CommonUtil.isEmpty(msClassStyle)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return " class=\"" + msClassStyle + "\"";
    }
    
    /** 
     * @Description: 生成提示信息class
     * @author king
     * @since Nov 3, 2012 11:45:46 PM 
     * 
     * @return 提示信息class
     */
    private String createMsStyle() {
        if (CommonUtil.isEmpty(msStyle)) {
            return ConstantsUtil.Str.EMPTY;
        }
        return " style=\"" + msStyle + "\"";
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

    /** 
     * @Description: 设置控件ID
     * @author king
     * @since Nov 3, 2012 11:34:33 PM 
     * 
     * @param id 控件ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /** 
    * @Description: 设置class
    * @author king
    * @since Nov 3, 2012 11:34:33 PM 
    * 
    * @param classStyle 样式
    */
    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }
    
    /** 
    * @Description: 设置提示信息
    * @author king
    * @since Nov 3, 2012 11:35:09 PM 
    * 
    * @param message 提示信息
    */
    public void setMessage(String message) {
        this.message = message;
    }

    /** 
    * @Description: 设置样式表
    * @author king
    * @since Nov 3, 2012 11:40:02 PM 
    * 
    * @param style 样式表
    */
    public void setStyle(String style) {
        this.style = style;
    }

    /** 
    * @Description: 设置提示信息class
    * @author king
    * @since Nov 3, 2012 11:41:56 PM 
    * 
    * @param msClass 提示信息class
    */
    public void setMsClass(String msClass) {
        this.msClassStyle = msClass;
    }

    /** 
     * @Description: 设置提示信息样式表
     * @author king
     * @since Nov 3, 2012 11:40:02 PM 
     * 
     * @param msStyle 提示信息样式表
     */
    public void setMsStyle(String msStyle) {
        this.msStyle = msStyle;
    }
}
