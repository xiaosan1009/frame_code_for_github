package com.richClientFrame.data.response;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.ConstantsUtil;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseData
 * 类描述 ： 画面项目数据管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.24
 * @author king
 */
public class ResponseData extends AbstractResponseData {
    
    /**
     * 画面项目对象容器.
     */
    private ResponseTab[] resTab;
    
    /**
     * 构造函数.
     * 
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseData() throws RichClientWebException {
        super(new ResponseHeader());
    }
    
    /**
     * 构造函数.
     * 
     * @param header 头文件对象
     */
    public ResponseData(ResponseHeader header) {
        super(header);
    }
    
    /**
     * 构造函数.
     * 
     * @param header 头文件对象
     * @param dateType 控件类型
     */
    public ResponseData(ResponseHeader header, int dateType) {
        super(header, dateType);
    }

    /**
     * 获得画面项目对象.
     * @return resTab 画面项目对象
     */
    public ResponseTab[] getResTab() {
        return resTab;
    }

    /**
     * 设置画面项目对象.
     * @param resTab 画面项目对象
     */
    public void setResTab(ResponseTab[] resTab) {
        this.resTab = resTab;
    }
    
}
