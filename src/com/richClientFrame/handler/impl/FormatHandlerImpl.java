package com.richClientFrame.handler.impl;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractFormatHandler;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.util.KeyedDigestMD5;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： FormatHandlerImpl
 * 类描述 ： 引擎参数格式化接口实现类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Oct 5, 2012 10:53:23 AM 
 * @author king
 */
public class FormatHandlerImpl extends AbstractFormatHandler {
    
    /** 
    * @Description: 请求参数md5转换
    * @author king
    * @since Oct 5, 2012 10:53:50 AM 
    * 
    * @param value 请求参数值
    * @param params 参数
    * @return md5转换后的请求参数
    * @throws RichClientWebException RichClientWebException
    */
    public String md5(String value, String[] params) throws RichClientWebException {
        final String key = ControlConfig.getInstance().getConfiguration().getMd5Key();
        final String md5 = KeyedDigestMD5.getKeyedDigest(value, key);
        return md5;
    }
    
    /**
     * 日期转换成数字.
     * @param value 要转换的数据
     * @param params 参数
     * @return 转换成数字
     */
    public String dateToNum(String value, String[] params) {
        value = value.replaceAll("-", "");
        value = value.replaceAll("/", "");
        value = value.replaceAll(":", "");
        value = value.replaceAll(" ", "");
        return value;
    }

}
