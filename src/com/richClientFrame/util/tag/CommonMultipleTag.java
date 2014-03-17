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
     * ��ĿID
     */
    private String targetId;
    
    /**
     * ��ĿID
     */
    private String loop;
    
    /**
     * ��������
     */
    private ResponseList dataList;
    
    /**
     * �����
     */
    private int listCurrentIndex;
    
    /**
     * �����Ϣ
     */
    private StringBuffer outerPrint;
    
    /**
     * ��ǩ����Ϣ
     */
    private String htmlBodyCode;
    
    private boolean isMultiple;

    /**
     * ��ĿID�趨.
     * 
     * @param targetId ��ĿID
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * ���ɱ�ǩǰ����.
     * 
     * @return ���ɽ��
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
     * ���ɱ�ǩ�󷽷�.
     * 
     * @return ���ɽ��
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
     * ���ö�ά�б�����ռλ��ʶ.
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
     * ���ö�ά�б����ݵ���������.
     * 
     * @param html ��ά�б�����
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
