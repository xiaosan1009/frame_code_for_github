package com.richClientFrame.util.tag;

import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.TagUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * multiple tag.
 * @author king
 * @since 2011.03.06
 */
public class CommonMultipleTag extends BodyTagSupportBase {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 项目ID
     */
    private String targetId;
    
    /**
     * 项目ID
     */
    private String loop;
    
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
    
    private boolean isMultiple;

    /**
     * 项目ID设定.
     * 
     * @param targetId 项目ID
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * 生成标签前方法.
     * 
     * @return 生成结果
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        
        outerPrint = new StringBuffer();
        listCurrentIndex = 0;
//        dataList = (ResponseList)pageContext.getAttribute(ConstantsUtil.Tag.MULTIPLE);
//        
//        if (dataList == null) {
//            final AbstractResponseData resData = (AbstractResponseData)pageContext.getRequest()
//                .getAttribute(AbstractResponseData.KEY_RESPONSE_DATA);
//            AbstractResponseData resList1 = null;
//            if (resData == null) {
//                return SKIP_BODY;
//            } else {
//                resList1 = AbstractResponseData.getAbstractResponseData(resData, targetId);
//                if (resList1 == null) {
//                    return SKIP_BODY;
//                }
//                dataList = resList1.getDataList();
//                if (dataList == null) {
//                    return SKIP_BODY;
//                }
//                if (dataList != null && listCurrentIndex >= dataList.size()) {
//                    return SKIP_BODY;
//                }
//                
//            }
//        } else {
//            isMultiple = true;
//        }
        
        dataList = (ResponseList)pageContext.getAttribute(ConstantsUtil.Tag.MULTIPLE + CommonUtil.toString(loop));
        if (dataList == null) {
            final AbstractResponseData resData = (AbstractResponseData)pageContext.getRequest()
                    .getAttribute(AbstractResponseData.KEY_RESPONSE_DATA);
            AbstractResponseData resList1 = null;
            if (resData == null) {
                return SKIP_BODY;
            } else {
                resList1 = AbstractResponseData.getAbstractResponseData(resData, targetId);
                if (resList1 == null) {
                    return SKIP_BODY;
                }
                dataList = resList1.getDataList();
                if (dataList == null) {
                    return SKIP_BODY;
                }
//                int looper = 0;
//                if (CommonUtil.isNotEmpty(loop)) {
//                    looper = Integer.parseInt(loop);
//                }
                if (dataList.getLine(listCurrentIndex) != null) {
                    pageContext.setAttribute(ConstantsUtil.Tag.MULTIPLE + 1, dataList
                            .getLine(listCurrentIndex).getList());
                }
//                for (int i = 0; i < looper; i++) {
//                }
                if (dataList != null && listCurrentIndex >= dataList.size()) {
                    return SKIP_BODY;
                }

            }
        } else {
            if (dataList.getLine(listCurrentIndex) != null) {
                int looper = 0;
                if (CommonUtil.isNotEmpty(loop)) {
                    looper = Integer.parseInt(loop);
                }
                pageContext.setAttribute(ConstantsUtil.Tag.MULTIPLE + (looper + 1), dataList
                        .getLine(listCurrentIndex).getList());
            }
        }
        return EVAL_BODY_AGAIN;
    }
    
    @Override
    public int doAfterBody() throws JspException {
        if (dataList != null && listCurrentIndex >= dataList.size()) {
            return SKIP_BODY;
        }
        if (listCurrentIndex == 0) {
            final BodyContent body = getBodyContent();
            htmlBodyCode = body.getString();
//            this.getBodyContent().clearBody();  
//            setMultipleSign();
        }
        
//        try {  
//            this.getBodyContent().getEnclosingWriter().print(this.getBodyContent().getString());  
//           } catch (IOException e) {  
//            e.printStackTrace();  
//           } 
        
        ResponseLine resLine = null;
        resLine = dataList.getLine(listCurrentIndex);
        final ResponseList multipleList = resLine.getList();
        if (multipleList != null) {
            int looper = 0;
            if (CommonUtil.isNotEmpty(loop)) {
                looper = Integer.parseInt(loop);
            }
            pageContext.setAttribute(ConstantsUtil.Tag.MULTIPLE + (looper + 1), multipleList);
        }
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
//        try {
//            if (isMultiple) {
//                ((CommonMultipleTag)findAncestorWithClass(this, 
//                        CommonMultipleTag.class)).setMultipleHtml(outerPrint.toString());
//            } else {
//            }
//            this.getBodyContent().getEnclosingWriter().print(outerPrint.toString());
//            this.getBodyContent().clearBody();
//            pageContext.getOut().print(outerPrint.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return EVAL_PAGE;
    }
    
    /**
     * 设置二维列表数据占位标识.
     * 
     */
    private void setMultipleSign() {
        char b = 0;
        final StringBuffer buff = new StringBuffer();
        for (int i = 0; i < htmlBodyCode.length(); i++) {
            if (i != 0) {
                b = htmlBodyCode.charAt(i - 1);
            }
            final char a = htmlBodyCode.charAt(i);
            if (a == 13 && b == 9) {
                buff.append("#{code}");
            }
            buff.append(a);
        }
        htmlBodyCode = buff.toString();
    }
    
    /**
     * 设置二维列表数据到画面数据.
     * 
     * @param html 二维列表数据
     * 
     */
    public void setMultipleHtml(String html) {
        final StringBuffer buff = new StringBuffer(outerPrint.toString().replaceFirst("#\\{code\\}", html));
        outerPrint = buff;
    }

    public String getLoop() {
        return loop;
    }

    public void setLoop(String loop) {
        this.loop = loop;
    }
}
