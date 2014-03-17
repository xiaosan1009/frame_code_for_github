package com.richClientFrame.info;

import com.richClientFrame.data.menu.MenuInformation;
import com.richClientFrame.exception.RichClientWebException;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ControlMenuMap
 * 类描述 ： 系统请求的分发xml解析类.保存的内容与request.xml一致
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.07.28
 * @author king
 */
public final class ControlMenuMap {

    /**
     * 本类对象
     */
    private static ControlMenuMap sMe;
    
    /**
     * 菜单信息
     */
    private MenuInformation menuInfo;
    
    /**
     * 路径
     */
    private static String sPath;
    
    /**
     * 构造函数
     * @throws RichClientWebException RichClientWebException
     */
    private ControlMenuMap() throws RichClientWebException {
        // 读取文件
        menuInfo = MenuConfigReader.read(sPath);
        
    }
    
    /**
     * 本类对象生成.
     * @return RequestMap 本类对象
     * @throws RichClientWebException RichClientWebException
     */
    public static ControlMenuMap getInstance() throws RichClientWebException {
        
        synchronized (ControlMenuMap.class) {
            if (sMe == null) {
                sMe = new ControlMenuMap();
            }
        }
        return sMe;
    }

    /**
     * request.xml路径设置.
     * 
     * @param path request.xml路径
     */
    public static void setPath(String path) {
        sPath = path;
    }

    /**
     * 菜单信息取得.
     * 
     * @return 菜单信息
     */
    public MenuInformation getMenuInfo() {
        return menuInfo;
    }
    
}
