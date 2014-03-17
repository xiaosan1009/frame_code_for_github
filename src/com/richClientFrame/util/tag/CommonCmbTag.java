package com.richClientFrame.util.tag;

import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.TagUtil;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * list tag.
 * @author king
 * @since 2011.03.06
 */
public class CommonCmbTag extends BodyTagSupportBase {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 数据数组
     */
    private ResponseList dataList;
    
    /**
     * 项目ID
     */
    private String targetId;
    
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
        AbstractResponseData resList1 = null;
        if (resData == null) {
            return SKIP_BODY;
        } else {
            resList1 = AbstractResponseData.getAbstractResponseData(resData, targetId);
            if (pageContext.getSession().getAttribute(ConstantsUtil.Tag.CMB_ITEMS) != null) {
                final ResponseLine cmbLine = (ResponseLine)pageContext.getSession().getAttribute(
                        ConstantsUtil.Tag.CMB_ITEMS);
                final ResponseTab cmbTab = cmbLine.get(targetId);
                if (cmbTab == null) {
                    return SKIP_BODY;
                }
                final Map<String, AbstractResponseData> cmbData = cmbTab.getCmbList();
                if (cmbData == null) {
                    return SKIP_BODY;
                }
                if (cmbData.containsKey(targetId)) {
                    dataList = cmbData.get(targetId).getDataList();
                }
            } else {
                dataList = resList1.getDataList();
            }
            if (dataList == null) {
                return SKIP_BODY;
            }
            if (dataList != null && listCurrentIndex >= dataList.size()) {
                return SKIP_BODY;
            }
            
        }
        return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public int doAfterBody() throws JspException {
        if (listCurrentIndex >= dataList.size()) {
            return SKIP_BODY;
        }
        if (listCurrentIndex == 0) {
            final BodyContent body = getBodyContent();
            htmlBodyCode = body.getString();
        }
        final ResponseLine resLine = dataList.getLine(listCurrentIndex);
        resLine.setListLineIndex(listCurrentIndex);
        outerPrint.append(TagUtil.resolvePlaceholders(htmlBodyCode, resLine));
        listCurrentIndex++;
        return EVAL_BODY_AGAIN;
    }
    
    @Override
    public void doInitBody() throws JspException {
        super.doInitBody();
    }

    /**
     * 生成标签后方法.
     * 
     * @return 生成结果
     * @throws JspException JspException
     */
    public int doEndTag() throws JspException {
        if (findAncestorWithClass(this, 
                CommonListTag.class) != null) {
            ((CommonListTag)findAncestorWithClass(this, 
                    CommonListTag.class)).setCmbHtml(outerPrint.toString());
        } else {
            try {
                pageContext.getOut().print(outerPrint.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return EVAL_PAGE;
    }
    
    /**
     * 项目ID设定.
     * 
     * @param targetId 项目ID
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
