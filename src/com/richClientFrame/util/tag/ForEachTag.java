
package com.richClientFrame.util.tag;

import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.TagUtil;

import java.io.IOException;

import javax.servlet.jsp.JspException;

public class ForEachTag extends BodyTagSupportBase {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String var;

    private Object items;
    
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

    @Override
    public int doStartTag() throws JspException {
        // items不为空时,var是必须,迭代方式,因为items是Object不用取作用域
        listCurrentIndex = 0;// 先置0再加否则会一直加
        int looper = 0;
        if (CommonUtil.isNotEmpty(loop)) {
            looper = Integer.parseInt(loop);
        }
        if (items != null || (items == null && looper != 0)) {
            if (items == null) {
                return SKIP_BODY;
            }
            // 如果是集合
            if (items instanceof ResponseList) {
                dataList = (ResponseList)this.items;
            } else {
                return SKIP_BODY;
            }
            
            // items为空时
        } else {
            final AbstractResponseData resData = (AbstractResponseData)pageContext.getRequest()
                .getAttribute(AbstractResponseData.KEY_RESPONSE_DATA);
            AbstractResponseData resList1 = null;
            if (resData == null) {
                return SKIP_BODY;
            } else {
                resList1 = AbstractResponseData.getAbstractResponseData(resData, null);
                if (resList1 == null) {
                    return SKIP_BODY;
                }
                dataList = resList1.getDataList();
                if (dataList == null) {
                    return SKIP_BODY;
                }
                if (dataList != null && listCurrentIndex >= dataList.size()) {
                    return SKIP_BODY;
                }
            }
        }
        
        // 检验迭代器是否有下一个值
        if (dataList != null && dataList.size() > 0 && listCurrentIndex < dataList.size()) {
            final ResponseLine resLine = dataList.getLine(listCurrentIndex);
            resLine.setListLineIndex(listCurrentIndex);
            if (CommonUtil.isNotEmpty(var)) {
                this.pageContext.setAttribute(var, resLine.getList());
            }
            this.pageContext.setAttribute(ConstantsUtil.Tag.MULTIPLE + looper, resLine);
            listCurrentIndex++;
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }

    }

    @Override
    public int doAfterBody() throws JspException {
        int looper = 0;
        if (CommonUtil.isNotEmpty(loop)) {
            looper = Integer.parseInt(loop);
        }
        try {
            final ResponseLine resLine = (ResponseLine)this.pageContext.getAttribute(
                    ConstantsUtil.Tag.MULTIPLE + looper);
            final String htmlContent = TagUtil.resolvePlaceholders(this.getBodyContent().getString(), resLine);
            this.getBodyContent().getEnclosingWriter().print(htmlContent);
            this.getBodyContent().clearBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 检验迭代器是否有下一个值
        if (dataList != null && dataList.size() > 0 && listCurrentIndex < dataList.size()) {
            final ResponseLine resLine = dataList.getLine(listCurrentIndex);
            resLine.setListLineIndex(listCurrentIndex);
            if (CommonUtil.isNotEmpty(var)) {
                this.pageContext.setAttribute(var, resLine.getList());
            }
            this.pageContext.setAttribute(ConstantsUtil.Tag.MULTIPLE + looper, resLine);
            listCurrentIndex++;
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }

    }
    
    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getLoop() {
        return loop;
    }

    public void setLoop(String loop) {
        this.loop = loop;
    }

}
