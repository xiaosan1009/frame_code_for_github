package com.richClientFrame.util;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.HtmlTabHandler;

/**
 * ��ҳ������.
 * @author King
 * @since 2010.11.07
 */
public class PageUtil extends HtmlTabHandler {
    
    /**
     * ��ҳ��Ϣ����.
     * @param list1 ������Ŀ��Ϣ
     * @param param request
     * @throws RichClientWebException RichClientWebException
     */
    public void pageShow(ResponseList list1, Param param) throws RichClientWebException {
        
        String targetId;
        ResponseTab resTab;
        String txt = ConstantsUtil.Str.EMPTY;
        final ResponseLine resLine1 = new ResponseLine();
        
        // ��ǰҳ��
        int page = 1;
        final String currentpage = param.get(ConstantsUtil.Target.CURRENT_PAGE);
        if (CommonUtil.isNotEmpty(currentpage)) {
            page = Integer.parseInt(currentpage);
        }
        if (page < 1) {
            page = 1;
        }
        
        final String totalRowsStr = param.get("pagingTotalRows");
        final String pageSizeStr = param.get("pagingPageSize");
        
        int totalRows = 0;
        if (CommonUtil.isNotEmpty(totalRowsStr)) {
            totalRows = Integer.parseInt(totalRowsStr);
        }
        
        int pageSize = 0;
        if (CommonUtil.isNotEmpty(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        
        // �л�ҳ����Ϣ
        final PageCreate pageInfo = new PageCreate(totalRows, pageSize, page);
        
        // ǰһҳ
        targetId = ConstantsUtil.Target.PREV_PAGE;
        resTab = makeBtnTab(targetId);
        int prevFlg = ConstantsUtil.Status.NO_CHANGE;
        if (!pageInfo.isHasPreviousPage()) {
            prevFlg = ConstantsUtil.Status.READ;
        }
        resTab.setDispStat(prevFlg);
        resLine1.put(targetId, resTab);
        
        // ��һҳ
        targetId = ConstantsUtil.Target.NEXT_PAGE;
        resTab = makeBtnTab(targetId);
        int nextFlg = ConstantsUtil.Status.NO_CHANGE;
        if (!pageInfo.isHasNextPage()) {
            nextFlg = ConstantsUtil.Status.READ;
        }
        resTab.setDispStat(nextFlg);
        resLine1.put(targetId, resTab);
        
        // ��ǰҳ��
        targetId = ConstantsUtil.Target.NOW_PAGE;
        txt = String.valueOf(pageInfo.getCurrentPage());
        resTab = makeTextTab(targetId, txt);
        resLine1.put(targetId, resTab);
        
        // ��ǰҳ��
        targetId = ConstantsUtil.Target.CURRENT_PAGE;
        txt = String.valueOf(pageInfo.getCurrentPage());
        resTab = makeHiddenTab(targetId, txt);
        resLine1.put(targetId, resTab);
        
        // ��ҳ��
        targetId = ConstantsUtil.Target.ALL_PAGE;
        txt = String.valueOf(pageInfo.getTotalPages());
        resTab = makeTextTab(targetId, txt);
        resLine1.put(targetId, resTab);
        
        // ��ҳ��
        targetId = ConstantsUtil.Target.TOTAL_PAGES;
        txt = String.valueOf(pageInfo.getTotalPages());
        resTab = makeHiddenTab(targetId, txt);
        resLine1.put(targetId, resTab);
        
        list1.addLine(resLine1);
    }
}
