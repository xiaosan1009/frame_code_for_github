package com.richClientFrame.util.tag;

import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.data.response.common.CommonPageListInfo;
import com.richClientFrame.util.TagUtil;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * 分页标签.
 * @author king
 * @since 2011.06.06
 */
public class CommonPagingTag extends BodyTagSupportBase {
    
    private static final long serialVersionUID = 1L;
    
    private ResponseList dataList;
    
    private int listCurrentIndex;
    
    private StringBuffer outerPrint;
    
    private String htmlBodyCode;

    /**
     * 生成标签前方法.
     * 
     * @return 生成结果
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        
        outerPrint = new StringBuffer();
        listCurrentIndex = 0;
        final AbstractResponseData resData = (AbstractResponseData)pageContext.getRequest()
            .getAttribute(AbstractResponseData.KEY_RESPONSE_DATA);
        
        CommonPageListInfo resList1 = null;
        if (resData != null) {
            final AbstractResponseData responseData = AbstractResponseData.getAbstractResponseData(
                    resData, "pageList");
            if (responseData != null && responseData instanceof CommonPageListInfo) {
                resList1 = (CommonPageListInfo)responseData;
            } else {
                return SKIP_BODY;
            }
            if (resList1 != null) {
                dataList = resList1.getDataList();
                if (dataList == null) {
                    return SKIP_BODY;
                }
            } else {
                return SKIP_BODY;
            }
        } else {
            return SKIP_BODY;
        }
        
        return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public int doAfterBody() throws JspException {
        final BodyContent body = getBodyContent();
        htmlBodyCode = body.getString();
        final ResponseLine resLine = dataList.getLine(listCurrentIndex);
        resLine.setListLineIndex(-1);
        outerPrint.append(TagUtil.resolvePlaceholders(htmlBodyCode, resLine));
        listCurrentIndex++;
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
}
