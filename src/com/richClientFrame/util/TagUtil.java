package com.richClientFrame.util;

import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.HtmlTabHandler;

import java.util.Arrays;
import java.util.List;

/** 
* @ClassName: TagUtil 
* @Description: 自定义标签工具类
* @author king
* @since Oct 12, 2012 1:57:30 PM 
*  
*/
public final class TagUtil {
    
    private static final String WIDGET_TAG_CHECK = "check";
    
    private static final String WIDGET_TAG_RADIO = "radio";
    
    private static final String[] WIDGET_TAGS = new String[] {
        WIDGET_TAG_CHECK,
        WIDGET_TAG_RADIO,
    };
    
    /**
     * 构造函数.
     */
    private TagUtil() {
    }
    
    /**
     * 解析标签内容.
     * 
     * @param text 标签内容
     * @param resLine 内容对象
     * @return 解析后的结果
     */
    public static String resolvePlaceholders(String text, ResponseLine resLine) {

        final StringBuilder buf = new StringBuilder(text);

        int startIndex = text.indexOf(ConstantsUtil.Tag.PLACE_HOLDER_PREFIX);
        while (startIndex != -1) {
            final int endIndex = buf.toString().indexOf(ConstantsUtil.Tag.PLACE_HOLDER_SUFFIX, 
                    startIndex + ConstantsUtil.Tag.PLACE_HOLDER_PREFIX.length());
            if (endIndex != -1) {
                final String placeholder = buf.substring(
                        startIndex + ConstantsUtil.Tag.PLACE_HOLDER_PREFIX.length(), endIndex);
                // 解析标签内容
                final String propVal = getHtmlContent(placeholder, resLine);
                if (propVal != null) {
                    buf.replace(startIndex, endIndex + ConstantsUtil.Tag.PLACE_HOLDER_SUFFIX.length(), propVal);
                    startIndex = buf.toString().indexOf(
                            ConstantsUtil.Tag.PLACE_HOLDER_PREFIX, startIndex + propVal.length());
                } else {
                    startIndex = buf.toString().indexOf(ConstantsUtil.Tag.PLACE_HOLDER_PREFIX, 
                            endIndex + ConstantsUtil.Tag.PLACE_HOLDER_SUFFIX.length());
                }
            } else {
                startIndex = -1;
            }
        }

        return buf.toString();
    }
    
    /**
     * 解析标签内容.
     * 
     * @param text 标签内容
     * @param resLine 内容对象
     * @return 解析后的结果
     */
    public static String getHtmlContent(String text, ResponseLine resLine) {
        try {
            if (text.indexOf(ConstantsUtil.Str.DOT) > 0) {
                final String[] texts = text.split(ConstantsUtil.Str.REGEX_DOT);
                if (texts.length > 1) {
                    final String targetId = texts[0];
                    final String property = texts[1];
                    final ResponseTab responseTab = resLine.get(targetId);
                    String outerPrint = ConstantsUtil.Str.EMPTY;
                    if ("line".equals(targetId)) {
                        if (property.indexOf(ConstantsUtil.Tag.INDEX_ATTRIBUTE) != -1) {
                            if (texts.length > 2) {
                                final int digit = getStartOperation(texts[2]);
                                final int changeNum = getEndOperation(texts[2]);
                                outerPrint = String.valueOf((int)(Math.pow(10, digit) 
                                        + (resLine.getListLineIndex() + changeNum))).substring(1);
                            } else {
                                final int changeNum = getEndOperation(property);
                                outerPrint = String.valueOf(resLine.getListLineIndex() + changeNum);
                            }
                        } else if (ConstantsUtil.Tag.CHAR_ATTRIBUTE.equals(property)) {
                            outerPrint = resLine.getLineChar();
                        } else if (ConstantsUtil.Tag.ALTERNATE_COLOR_ATTRIBUTE.equals(property)) {
                            outerPrint = HtmlUtil.getAlternateColor(resLine.getListLineIndex());
                        }
                    } else {
                        if (CommonUtil.isNotEmpty(property)) {
                            if (responseTab != null) {
                                if (ConstantsUtil.Tag.VALUE_ATTRIBUTE.equals(property)) {
                                    outerPrint = HtmlUtil.getValue(responseTab);
                                } else if (property.indexOf(ConstantsUtil.Tag.INDEX_ATTRIBUTE) != -1) {
                                    if (texts.length > 2) {
                                        final int digit = getStartOperation(texts[2]);
                                        final int changeNum = getEndOperation(texts[2]);
                                        outerPrint = String.valueOf((int)(Math.pow(10, digit) 
                                                + (resLine.getListLineIndex() + changeNum))).substring(1);
                                    } else {
                                        final int changeNum = getEndOperation(property);
                                        outerPrint = String.valueOf(
                                                resLine.getListLineIndex() + changeNum);
                                    }
                                } else if (
                                        ConstantsUtil.Tag.ALTERNATE_COLOR_ATTRIBUTE.equals(property)) {
                                    outerPrint = HtmlUtil.getAlternateColor(resLine.getListLineIndex());
                                } else if (ConstantsUtil.Tag.ID_ATTRIBUTE.equals(property)) {
                                    if (resLine.getListLineIndex() == -1) {
                                        outerPrint = HtmlUtil.getID(responseTab);
                                    } else {
                                        outerPrint = HtmlUtil.getID(
                                                responseTab, String.valueOf(resLine.getListLineIndex()));
                                    }
                                } else if (ConstantsUtil.Tag.TEXT_ATTRIBUTE.equals(property)) {
                                    outerPrint = HtmlUtil.getText(responseTab);
                                } else if (ConstantsUtil.Tag.SELECTED_ATTRIBUTE.equals(property)) {
                                    outerPrint = HtmlUtil.getSelected(responseTab);
                                } else if (ConstantsUtil.Tag.STAT_ATTRIBUTE.equals(property)) {
                                    outerPrint = HtmlUtil.getDisabled(responseTab);
                                } else if (ConstantsUtil.Tag.STYLE_ATTRIBUTE.equals(property)) {
                                    outerPrint = HtmlUtil.getHtmlTagProperties(responseTab, resLine.getListLineIndex());
                                } else if (ConstantsUtil.Tag.WIDGET_ATTRIBUTE.equals(property)) {
                                    outerPrint = HtmlUtil.createHtml(responseTab, 
                                            String.valueOf(resLine.getListLineIndex()));
                                } else if (isWidgetTag(property)) {
                                    outerPrint = analyWidgetInfo(resLine, texts, targetId, outerPrint);
                                } 
                            } else if (ConstantsUtil.Tag.ID_ATTRIBUTE.equals(property)) {
                                outerPrint = HtmlUtil.getID(
                                        targetId, String.valueOf(resLine.getListLineIndex()));
                            }
                        }
                    }
                    return outerPrint;
                }
            }
        } catch (RichClientWebException e) {
            e.printStackTrace();
        }
        return "";
    }

    /** 
    * @Description: 解析控件信息
    * @author king
    * @since Oct 12, 2012 1:58:03 PM 
    * 
    * @param resLine 行信息对象
    * @param texts 标签内容信息
    * @param targetId 标签类型
    * @param outerPrint 显示信息
    * @return 控件信息
    * @throws RichClientWebException RichClientWebException
    */
    private static String analyWidgetInfo(
            ResponseLine resLine, 
            final String[] texts,
            final String targetId, 
            String outerPrint) throws RichClientWebException {
        if (WIDGET_TAG_CHECK.equals(targetId)) {
            outerPrint = ConstantsUtil.Str.EMPTY;
        } else if (WIDGET_TAG_RADIO.equals(targetId)) {
            ResponseTab tab = null;
            if (texts.length == 3) {
                final ResponseTab valueTab = resLine.get(texts[2]);
                tab = new HtmlTabHandler().makeRadioTab(targetId,
                        valueTab.getValue(), valueTab.getValue());
            } else if (texts.length == 4) {
                final ResponseTab valueTab = resLine.get(texts[2]);
                final ResponseTab textTab = resLine.get(texts[3]);
                tab = new HtmlTabHandler().makeRadioTab(targetId,
                        valueTab.getValue(), textTab.getValue());
            }
            outerPrint = HtmlUtil.createHtmlByResponse(tab, String
                    .valueOf(resLine.getListLineIndex()));
        }
        return outerPrint;
    }

    /**
     * 解析标签内容.
     * 
     * @param property 标签头
     * @return 解析后的结果
     */
    private static int getStartOperation(String property) {
        int changeNum = 0;
        if (property.indexOf("+") != -1) {
            final String changeStr = CommonUtil.trim(property.substring(0, property.indexOf("+")));
            try {
                changeNum = Integer.parseInt(changeStr);
            } catch (NumberFormatException e) {
                changeNum = 0;
            }
        } else if (property.indexOf("-") != -1) {
            final String changeStr = CommonUtil.trim(property.substring(0, property.indexOf("-")));
            try {
                changeNum = Integer.parseInt(changeStr) * -1;
            } catch (NumberFormatException e) {
                changeNum = 0;
            }
        } else {
            try {
                changeNum = Integer.parseInt(property);
            } catch (NumberFormatException e) {
                changeNum = 0;
            }
        }
        return changeNum;
    }
    
    /**
     * 解析标签内容.
     * 
     * @param property 标签头
     * @return 解析后的结果
     */
    private static int getEndOperation(String property) {
        int changeNum = 0;
        if (property.indexOf("+") != -1) {
            final String changeStr = CommonUtil.trim(property.substring(property.indexOf("+") + 1, 
                    property.length()));
            try {
                changeNum = Integer.parseInt(changeStr);
            } catch (NumberFormatException e) {
                changeNum = 0;
            }
        } else if (property.indexOf("-") != -1) {
            final String changeStr = CommonUtil.trim(property.substring(property.indexOf("-") + 1, 
                    property.length()));
            try {
                changeNum = Integer.parseInt(changeStr) * -1;
            } catch (NumberFormatException e) {
                changeNum = 0;
            }
        }
        return changeNum;
    }
    
    /**
     * 判断是否是定制标签.
     * 
     * @param property 标签头
     * @return 判断后的结果
     */
    private static boolean isWidgetTag(String property) {
        final List<String> widgetList = Arrays.asList(WIDGET_TAGS);
        return widgetList.contains(property);
    }

}
