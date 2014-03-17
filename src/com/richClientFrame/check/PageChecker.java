package com.richClientFrame.check;

import java.util.ArrayList;
import java.util.List;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.InputException;
import com.richClientFrame.util.ConstantsUtil;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： PageChecker
 * 类描述 ： 页面验证.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.10.21
 * @author king
 */
public class PageChecker extends CommonChecker {
    
    /**
     * 页面验证.
     * @param param request
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public void checkPage(Param param)
        throws RichClientWebException, InputException {
        
        final List<ResponseTab> errTabList = new ArrayList<ResponseTab>();
        int page = 0;
        int pageCnt = 0;
        String str = param.get(ConstantsUtil.Target.PAGE_NUM);
        if (str != null && str.length() > 0) {
            try {
                page = Integer.parseInt(str);
            } catch (NumberFormatException ne) {
                final ResponseTab errTab = makeErrTab(param.dispCode, ConstantsUtil.Target.PAGE_NUM, "", 
                        ConstantsUtil.Check.INPUT_ERROR);
                errTab.setDataType(ConstantsUtil.Widget.TEXTBOX);
                errTabList.add(errTab);
            }
        } else {
            final ResponseTab errTab = makeErrTab(param.dispCode, ConstantsUtil.Target.PAGE_NUM, "", 
                    ConstantsUtil.Check.NOTHING_INPUT);
            errTab.setDataType(ConstantsUtil.Widget.TEXTBOX);
            errTabList.add(errTab);
        }
        str = param.get(ConstantsUtil.Target.ALL_PAGE);
        if (str != null && str.length() > 0) {
            pageCnt = Integer.parseInt(str);
        }

        if (pageCnt > 0 && !(page > 0 && page <= pageCnt)) {
            final ResponseTab errTab = makeErrTab(param.dispCode, ConstantsUtil.Target.PAGE_NUM, "", 
                    ConstantsUtil.Check.RANGE_ERROR);
            errTab.setDataType(ConstantsUtil.Widget.TEXTBOX);
            errTabList.add(errTab);
        }

        ResponseHeader errHead = null;
        ResponseTab[] tablist = null;
        if (errTabList.size() != 0) {
            errHead = makeErrHead(param);
            tablist = errTabList.toArray(new ResponseTab[]{});

            throw new InputException(errHead, tablist);
        }
    }
    
}
