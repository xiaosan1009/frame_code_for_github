package com.richClientFrame.util.tag;

import com.richClientFrame.info.ControlResourceMap;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;

import javax.servlet.jsp.tagext.BodyTagSupport;

public class BodyTagSupportBase extends BodyTagSupport {

    /**
     * serial
     */
    private static final long serialVersionUID = 1L;

    protected String getResourceInfo(String code) {
        final String dispCode = pageContext.getRequest().getParameter("dispcode");
        return getResourceInfo(dispCode, code);
    }
    
    protected String getResourceInfo(String disp, String code) {
        final String itemName = ControlResourceMap.getInstance().getItemName(disp, code);
        if (CommonUtil.isNotEmpty(itemName)) {
            code = itemName;
        }
        return CommonUtil.toString(code);
    }
    
    protected String setValueItem(String value) {
        String valueItem = ConstantsUtil.Str.EMPTY;
        if (CommonUtil.isNotEmpty(value)) {
            valueItem = " value=\"" + value + "\"";
        }
        return valueItem;
    }
    
    protected String setWidthItem(String width) {
        String widthItem = ConstantsUtil.Str.EMPTY;
        if (CommonUtil.isNotEmpty(width)) {
            widthItem = " width=\"" + width + "\"";
        }
        return widthItem;
    }
    
    protected String setHeightItem(String height) {
        String heightItem = ConstantsUtil.Str.EMPTY;
        if (CommonUtil.isNotEmpty(height)) {
            heightItem = " height=\"" + height + "\"";
        }
        return heightItem;
    }
    
    protected String setMaxlengthItem(String maxlength) {
        String maxlengthItem = ConstantsUtil.Str.EMPTY;
        if (CommonUtil.isNotEmpty(maxlength)) {
            maxlengthItem = " maxlength=\"" + maxlength + "\"";
        }
        return maxlengthItem;
    }
    
    protected String setStyleItem(String style) {
        String styleItem = ConstantsUtil.Str.EMPTY;
        if (CommonUtil.isNotEmpty(style)) {
            styleItem = " style=\"" + style + "\"";
        }
        return styleItem;
    }
    
    protected String setIdItem(String id) {
        return setIdItem(id, true);
    }
    
    protected String setIdItem(String id, boolean createName) {
        String idItem = ConstantsUtil.Str.EMPTY;
        if (CommonUtil.isNotEmpty(id)) {
            idItem = " id=\"" + id + "\"";
            if (createName) {
                idItem += " name=\"" + id + "\"";
            }
        }
        return idItem;
    }
    
    protected String setTitleItem(String title) {
        String titleItem = ConstantsUtil.Str.EMPTY;
        if (CommonUtil.isNotEmpty(title)) {
            titleItem = " title=\"" + getResourceInfo(title) + "\"";
        }
        return titleItem;
    }
    
    protected String setClassItem(String classStyle, boolean focus) {
        if (CommonUtil.isEmpty(classStyle) && !focus) {
            return ConstantsUtil.Str.EMPTY;
        }
        String classItem = " class=\"";
        if (CommonUtil.isNotEmpty(classStyle)) {
            classItem += classStyle + " ";
        }
        if (focus) {
            classItem += setFocusStyle(focus);
        }
        classItem += "\"";
        return classItem;
    }
    
    protected String setFocusStyle(boolean focus) {
        if (focus) {
            return "focusItem";
        }
        return ConstantsUtil.Str.EMPTY;
    }
    
    protected String setDisable(boolean disabled) {
        if (disabled) {
            return " disabled=\"disabled\"";
        }
        return ConstantsUtil.Str.EMPTY;
    }
    
    protected String setReadOnly(boolean read) {
        if (read) {
            return " readonly=\"readonly\"";
        }
        return ConstantsUtil.Str.EMPTY;
    }
}
