package com.richClientFrame.handler;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.param.UploadParam;
import com.richClientFrame.data.response.TabRowMapDimensions;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.iface.IActionFace;
import com.richClientFrame.handler.iface.IDataSourceFace;
import com.richClientFrame.handler.iface.IExternalFace;
import com.richClientFrame.handler.iface.IFormatFace;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.LogUtil;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： AbstractAction
 * 类描述 ： 引擎事件控制器接口实现类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2011.10.01
 * @author king
 */
public class AbstractAction implements IActionFace {
    
    /**
     * 缓存服务器对象.
     */
    protected ContorlMemcached memcached;
    
    /**
     * 外部处理对象.
     */
    protected IExternalFace external;
    
    /**
     * 格式化对象.
     */
    protected IFormatFace format;
    
    /**
     * 数据源切换对象.
     */
    protected IDataSourceFace dataSource;
    
    /**
     * 客户端请求对象.
     */
    protected Param request;
    
    /**
     * 客户端session对象.
     */
    protected SessionData session;
    
    /**
     * 数据库操作对象.
     */
    protected IDbService db;
    
    /**
     * 应用对象.
     */
    protected ServletContext app;
    
    /**
     * log对象.
     */
    protected LogUtil log = new LogUtil(this.getClass());
    
    /**
     * 单项目执行前方法.
     * 
     * @param whereMap sql条件
     * @param id 单项目ID，用来判断是哪个项目模块
     * @return 是否执行数据库操作【false：不执行；true：执行】
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onItemPreExecute(Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /**
     * 单项目执行后方法.
     * 
     * @param tab sql返回数据，此数据与画面项目绑定
     * @param id 单项目ID，用来判断是哪个项目模块
     * @throws RichClientWebException RichClientWebException
     */
    public void onItemPostExecute(TableRowMap tab, String id) 
        throws RichClientWebException {
    }
    
    /**
     * 单项目执行后方法.
     * 
     * @param tabs sql返回数据，此数据与画面项目绑定
     * @param id 单项目ID，用来判断是哪个项目模块
     * @return 对应画面控件ID
     * @throws RichClientWebException RichClientWebException
     */
    public String onItemPostExecute(List<TableRowMap> tabs, String id)
        throws RichClientWebException {
        return null;
    }
    
    /**
     * 多项目执行前方法.
     * 
     * @param whereMap sql条件
     * @param id 多项目ID，用来判断是哪个项目模块
     * @return 是否执行数据库操作【false：不执行；true：执行】
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onListPreExecute(Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /**
     * 多项目执行后方法.
     * 
     * @param list sql返回数据，此数据与画面项目绑定
     * @param id 多项目ID，用来判断是哪个项目模块
     * @throws RichClientWebException RichClientWebException
     */
    public void onListPostExecute(List<TableRowMap> list, String id) 
        throws RichClientWebException {
    }
    
    /**
     * 多项目二维数据执行后方法.
     * 
     * @param tabs sql返回数据，此数据与画面项目绑定
     * @param dimensionList sql返回数据，此数据与画面项目绑定
     * @param id 多项目ID，用来判断是哪个项目模块
     * @throws RichClientWebException RichClientWebException
     */
    public void onDemensionListPostExecute(List<TableRowMap> tabs, TabRowMapDimensions dimensionList, String id) 
        throws RichClientWebException {
    }
    
    /**
     * 增删改执行前方法.
     * 
     * @param whereMap sql条件
     * @param id 增删改项目ID，用来判断是哪个项目模块
     * @return 是否执行数据库操作【false：不执行；true：执行】
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onUpdatePreExecute(Map<String, Object> whereMap, String id)
        throws RichClientWebException {
        return true;
    }

    /**
     * 增删改执行后方法.
     * 
     * @param tab sql返回数据，此数据与画面项目绑定
     * @param flag 增删改数据库操作结果
     * @param id 单项目ID，用来判断是哪个项目模块
     * @throws RichClientWebException RichClientWebException
     */
    public void onUpdatePostExecute(TableRowMap tab, boolean flag, String id) 
        throws RichClientWebException {
    }
    
    /**
     * 上传执行前方法.
     * 
     * @param upload 上传信息对象
     * @param whereMap sql条件
     * @param id 增删改项目ID，用来判断是哪个项目模块
     * @return 是否执行数据库操作【false：不执行；true：执行】
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onUploadPreExecute(UploadParam upload, Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /**
     * 上传执行后方法.
     * 
     * @param upload 上传信息对象
     * @param tab sql返回数据，此数据与画面项目绑定
     * @param id 上传项目ID，用来判断是哪个项目模块
     * @throws RichClientWebException RichClientWebException
     */
    public void onUploadPostExecute(UploadParam upload, TableRowMap tab, String id)
        throws RichClientWebException {
    }
    
    /**
     * 上传执行后方法.
     * 
     * @param upload 上传信息对象
     * @param tabs sql返回数据或者上传文件中数据，此数据与画面项目绑定
     * @param id 上传项目ID，用来判断是哪个项目模块
     * @throws RichClientWebException RichClientWebException
     */
    public void onUploadPostExecute(UploadParam upload, List<TableRowMap> tabs, String id)
        throws RichClientWebException {
    }
    
    /**
     * 外部方法调用前对数据列表的设置.
     * 
     * @param list 数据
     * @param id sql执行标识
     * @return 是否执行数据库操作【false：不执行；true：执行】
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onExternalPreExecute(List<TableRowMap> list, String id)
        throws RichClientWebException {
        return true;
    }
    
    /**
     * 外部方法调用后对数据列表的设置.
     * 
     * @param list 数据
     * @param id sql执行标识
     * @throws RichClientWebException RichClientWebException
     */
    public void onExternalPostExecute(List<TableRowMap> list, String id)
        throws RichClientWebException {
    }

    /**
     * 外部方法调用前对数据的设置.
     * 
     * @param tab 数据
     * @param id sql执行标识
     * @return 是否执行数据库操作【false：不执行；true：执行】
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onExternalPreExecute(TableRowMap tab, String id) throws RichClientWebException {
        return true;
    }
    
    /**
     * 外部方法调用后对数据的设置.
     * 
     * @param tab 数据
     * @param id sql执行标识
     * @throws RichClientWebException RichClientWebException
     */
    public void onExternalPostExecute(TableRowMap tab, String id) throws RichClientWebException {
    }

    /**
     * 缓存服务器对象设置.
     * @param memcach 缓存服务器对象
     */
    public void setMemcached(ContorlMemcached memcach) {
        this.memcached = memcach;
    }

    /**
     * 客户端请求对象设置.
     * @param param 客户端请求对象
     */
    public void setParam(Param param) {
        this.request = param;
    }

    /**
     * 客户端session对象设置.
     * @param seData 客户端session对象
     */
    public void setSeData(SessionData seData) {
        this.session = seData;
    }

    /**
     * 外部处理对象设置.
     * @param external 外部处理对象
     */
    public void setExternal(IExternalFace external) {
        this.external = external;
    }

    /**
     * 数据库操作对象设置.
     * 
     * @param db 数据库操作对象
     */
    public void setDb(IDbService db) {
        this.db = db;
    }

    /**
     * 改变sql条件数据.
     * 
     * @param whereMap 条件
     * @param id sql执行标识
     * @return 是否执行数据库操作【false：不执行；true：执行】
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onCheckPreExecute(Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /**
     * 进行验证.
     * 
     * @param tab 数据
     * @param id sql执行标识
     * @return 验证结果【true：验证通过；false：验证失败】
     * @throws RichClientWebException RichClientWebException
     */
    public Map<String, List<String>> onCheckPostExecute(TableRowMap tab, String id) 
        throws RichClientWebException {
        return null;
    }

    /**
     * 格式化对象设置.
     * 
     * @param format 格式化对象
     */
    public void setFormat(IFormatFace format) {
        this.format = format;
    }

    /**
     * 数据源切换对象设置.
     * 
     * @param dataSourceFace 数据源切换对象
     */
    public void setDataSource(IDataSourceFace dataSourceFace) {
        this.dataSource = dataSourceFace;
    }

    /**
     * 条件判断执行前方法.
     * @param whereMap 条件
     * @param id sql执行标识
     * @return 是否执行数据库操作【false：不执行；true：执行】
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onConditionPreExecute(Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /** 
     * @Description: 设置应用对象
     * @author king
     * @since Nov 3, 2012 7:32:24 PM 
     * 
     * @param context 应用对象
     */
    public void setApplication(ServletContext context) {
        this.app = context;
    }

}
