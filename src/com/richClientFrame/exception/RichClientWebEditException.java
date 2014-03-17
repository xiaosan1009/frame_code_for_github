/**
 * 
 */

package com.richClientFrame.exception;

import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.util.ConstantsUtil;

/**
 * 项目名称 ： Web2.0开发引擎. 类名称 ： RichClientWebEditException 类描述 ： 设定画面异常处理;设定按钮无效处理
 * 创建人 ： 金雷 联系方式 ： xiaosan9528@163.com QQ ： 26981791 创建时间 ： 2010.07.03
 * 
 * @author king
 */
public class RichClientWebEditException extends RichClientWebException {

    private static final long serialVersionUID = 5122578174003010944L;

    public RichClientWebEditException() {
        super();
    }

    public RichClientWebEditException(EngineExceptionEnum exceptionEnum, ResponseTab[] tabs) {
        super(exceptionEnum, tabs);
        makeErrorTabs();
    }

    public RichClientWebEditException(EngineExceptionEnum exceptionEnum) {
        super(exceptionEnum);
        makeErrorTabs();
    }

    public RichClientWebEditException(EngineExceptionEnum exceptionEnum, Throwable arg1) {
        super(exceptionEnum, arg1);
        makeErrorTabs();
    }

    public RichClientWebEditException(Throwable arg0) {
        super(arg0);
        makeErrorTabs();
    }

    private void makeErrorTabs() {
        setErrTab(makeDisabledButon(new String[] {
            ConstantsUtil.Target.SET + 1
        }));
    }

    public ResponseTab[] makeDisabledButon(String[] targetId) {

        final ResponseTab[] tabs = new ResponseTab[targetId.length];

        for (int nCnt = 0; nCnt < targetId.length; nCnt++) {
            final ResponseTab tab = new ResponseTab();
            tab.setTargetId(targetId[nCnt]);
            tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
            tab.setDataType(ConstantsUtil.Widget.BTN);
            tab.setDispStat(ConstantsUtil.Status.READ);
            tabs[nCnt] = tab;
        }

        return tabs;
    }

    public ResponseTab[] makeHiddenImg(String[] targetId) {

        final ResponseTab[] tabs = new ResponseTab[targetId.length];

        for (int nCnt = 0; nCnt < targetId.length; nCnt++) {
            final ResponseTab tab = new ResponseTab();
            tab.setTargetId(targetId[nCnt]);
            tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
            tab.setDataType(ConstantsUtil.Widget.IMG);
            tab.setDispStat(ConstantsUtil.Status.HIDE);
            tabs[nCnt] = tab;
        }

        return tabs;
    }
}
