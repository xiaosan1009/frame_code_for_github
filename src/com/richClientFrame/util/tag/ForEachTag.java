
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

    @Override
    public int doStartTag() throws JspException {
        // items��Ϊ��ʱ,var�Ǳ���,������ʽ,��Ϊitems��Object����ȡ������
        listCurrentIndex = 0;// ����0�ټӷ����һֱ��
        int looper = 0;
        if (CommonUtil.isNotEmpty(loop)) {
            looper = Integer.parseInt(loop);
        }
        if (items != null || (items == null && looper != 0)) {
            if (items == null) {
                return SKIP_BODY;
            }
            // ����Ǽ���
            if (items instanceof ResponseList) {
                dataList = (ResponseList)this.items;
            } else {
                return SKIP_BODY;
            }
            
            // itemsΪ��ʱ
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
        
        // ����������Ƿ�����һ��ֵ
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
        // ����������Ƿ�����һ��ֵ
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
