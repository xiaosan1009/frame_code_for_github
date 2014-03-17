/**
 * 
 */
package com.richClientFrame.data.response.common;

import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.exception.RichClientWebException;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： CommonPageListInfo
 * 类描述 ： 分页请求数据.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.11.03
 * @author king
 */
public class CommonPageListInfo extends AbstractResponseData {
    
    public static final int LIST_SIZE = 1;
    
    private ResponseList list1;
    
    /**
     * 构造函数.
     * @throws RichClientWebException RichClientWebException
     */
    public CommonPageListInfo() throws RichClientWebException {
    }

    /**
     * 构造函数.
     * 
     * @param header 头文件
     * @throws RichClientWebException RichClientWebException
     */
    public CommonPageListInfo(ResponseHeader header) throws RichClientWebException {
        setResHeader(header);
    }

    /**
     * 数据取得.
     * 
     * @return 数据
     */
    public ResponseList getDataList() {
        return list1;
    }

    /**
     * 数据设置.
     * 
     * @param list1 数据
     */
    public void setDataList(ResponseList list1) {
        this.list1 = list1;
    }    
}
