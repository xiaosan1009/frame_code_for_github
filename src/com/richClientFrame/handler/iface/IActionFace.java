/**
 * 
 */
package com.richClientFrame.handler.iface;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.param.UploadParam;
import com.richClientFrame.data.response.TabRowMapDimensions;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： IActionFace
 * 类描述 ： 引擎事件控制器接口
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.05.30
 * @author king
 */
public interface IActionFace {
    
    /**
     * 设置request对象.
     * 
     * @param param request
     */
    void setParam(Param param);
    
    /**
     * 设置session对象.
     * 
     * @param seData session
     */
    void setSeData(SessionData seData);
    
    /**
     * 设置缓存服务器对象.
     * 
     * @param memcached 缓存服务器对象
     */
    void setMemcached(ContorlMemcached memcached);
    
    /**
     * 设置外部处理对象.
     * 
     * @param external 外部处理对象
     */
    void setExternal(IExternalFace external);
    
    /**
     * 设置格式化对象.
     * 
     * @param format 式化对象
     */
    void setFormat(IFormatFace format);
    
    /**
     * 设置数据源切换对象.
     * 
     * @param dataSource 数据源切换对象
     */
    void setDataSource(IDataSourceFace dataSource);
    
    /**
     * 设置数据库操作对象.
     * 
     * @param db 数据库操作对象
     */
    void setDb(IDbService db);
    
    /**
     * 改变sql条件数据.
     * 
     * @param whereMap 条件
     * @param id sql执行标识
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onItemPreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;
    
    /**
     * 解析单项目数据.
     * 
     * @param tab 数据
     * @param id sql执行标识
     * @throws RichClientWebException RichClientWebException
     */
    void onItemPostExecute(TableRowMap tab, String id) throws RichClientWebException;
    
    /**
     * 解析单项目数据.
     * 
     * @param tabs 数据
     * @param id sql执行标识
     * @return 对应画面控件ID
     * @throws RichClientWebException RichClientWebException
     */
    String onItemPostExecute(List<TableRowMap> tabs, String id) throws RichClientWebException;
    
    /**
     * 解析单项目数据.
     * 
     * @param upload upload信息
     * @param tab 数据
     * @param id sql执行标识
     * @throws RichClientWebException RichClientWebException
     */
    void onUploadPostExecute(UploadParam upload, TableRowMap tab, String id) 
        throws RichClientWebException;
    
    /**
     * 解析单项目数据.
     * 
     * @param upload upload信息
     * @param tabs 数据
     * @param id sql执行标识
     * @throws RichClientWebException RichClientWebException
     */
    void onUploadPostExecute(UploadParam upload, List<TableRowMap> tabs, String id) 
        throws RichClientWebException;
    
    /**
     * 改变sql条件数据.
     * 
     * @param whereMap 条件
     * @param id sql执行标识
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onListPreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;
    
    /**
     * 解析多项目数据.
     * 
     * @param list 数据
     * @param id sql执行标识
     * @throws RichClientWebException RichClientWebException
     */
    void onListPostExecute(List<TableRowMap> list, String id) throws RichClientWebException;
    
    /**
     * 外部方法调用前对数据列表的设置.
     * 
     * @param list 数据
     * @param id sql执行标识
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onExternalPreExecute(List<TableRowMap> list, String id) throws RichClientWebException;
    
    /**
     * 外部方法调用后对数据列表的设置.
     * 
     * @param list 数据
     * @param id sql执行标识
     * @throws RichClientWebException RichClientWebException
     */
    void onExternalPostExecute(List<TableRowMap> list, String id) throws RichClientWebException;
    
    /**
     * 外部方法调用前对数据的设置.
     * 
     * @param tab 数据
     * @param id sql执行标识
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onExternalPreExecute(TableRowMap tab, String id) throws RichClientWebException;
    
    /**
     * 外部方法调用后对数据的设置.
     * 
     * @param tab 数据
     * @param id sql执行标识
     * @throws RichClientWebException RichClientWebException
     */
    void onExternalPostExecute(TableRowMap tab, String id) throws RichClientWebException;
    
    /**
     * 改变sql条件数据.
     * 
     * @param whereMap 条件
     * @param id sql执行标识
     * @return 是否执行数据库操作【false：不执行；true：执行】
     * @throws RichClientWebException RichClientWebException
     */
    boolean onUpdatePreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;

    /**
     * 解析更新数据.
     * 
     * @param tab 数据
     * @param flag 更新结果
     * @param id sql执行标识
     * @throws RichClientWebException RichClientWebException
     */
    void onUpdatePostExecute(TableRowMap tab, boolean flag, String id) throws RichClientWebException;
    
    /**
     * 在上传之前的操作.
     * 
     * @param upload upload信息
     * @param whereMap 条件
     * @param id sql执行标识
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onUploadPreExecute(UploadParam upload, Map<String, Object> whereMap, String id) 
        throws RichClientWebException;
    
    /**
     * 多项目二维数据执行后方法.
     * 
     * @param tabs sql返回数据，此数据与画面项目绑定
     * @param dimensionList sql返回数据，此数据与画面项目绑定
     * @param id 多项目ID，用来判断是哪个项目模块
     * @throws RichClientWebException RichClientWebException
     */
    void onDemensionListPostExecute(List<TableRowMap> tabs, TabRowMapDimensions dimensionList, String id) 
        throws RichClientWebException;
    
    /**
     * 验证执行前方法.
     * 
     * @param whereMap 条件
     * @param id sql执行标识
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onCheckPreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;
    
    /**
     * 条件判断执行前方法.
     * @param whereMap 条件
     * @param id sql执行标识
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onConditionPreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;
    
    /**
     * 验证执行后方法.
     * 
     * @param tab 数据
     * @param id sql执行标识
     * @return 验证结果【true：验证通过；false：验证失败】
     * @throws RichClientWebException RichClientWebException
     */
    Map<String, List<String>> onCheckPostExecute(TableRowMap tab, String id) throws RichClientWebException;
    
    /** 
    * @Description: 设置应用对象
    * @author king
    * @since Nov 3, 2012 7:32:24 PM 
    * 
    * @param context 应用对象
    */
    void setApplication(ServletContext context);

}
