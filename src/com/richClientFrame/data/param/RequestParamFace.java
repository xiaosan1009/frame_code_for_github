
package com.richClientFrame.data.param;

import com.richClientFrame.data.param.RequestParam.Checker;
import com.richClientFrame.data.param.RequestParam.Cmb;
import com.richClientFrame.data.param.RequestParam.Condition;
import com.richClientFrame.data.param.RequestParam.DataSource;
import com.richClientFrame.data.param.RequestParam.Excel;
import com.richClientFrame.data.param.RequestParam.External;
import com.richClientFrame.data.param.RequestParam.Format;
import com.richClientFrame.data.param.RequestParam.Items;
import com.richClientFrame.data.param.RequestParam.Result;

/**
 * 项目名称 ： Web2.0开发引擎. 类名称 ： RequestParamFace 类描述 ： request.xml参数接口类. 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com QQ ： 26981791 创建时间 ： 2011.09.07
 * 
 * @author king
 */
public class RequestParamFace {

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jul 8, 2013 5:41:43 PM
     * @version V1.0
     */
    public interface IParam {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 3:59:20 PM
         * @version V1.0
         * @param param param
         */
        void addParam(com.richClientFrame.data.param.RequestParam.Param param);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 3:59:37 PM
         * @version V1.0
         * @return List<com.richClientFrame.data.param.RequestParam.Param>
         */
        java.util.List<com.richClientFrame.data.param.RequestParam.Param> getParams();
    }
    
    /**
     * @Title: RequestParamFace.java  
     * @Description: 
     * @author king
     * @since Oct 9, 2013 4:01:23 PM 
     * @version V1.0
     */
    public interface IResult {
        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 3:59:54 PM
         * @version V1.0
         * @param result result
         */
        void addResult(Result result);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:00:01 PM
         * @version V1.0
         * @return List<Result>
         */
        java.util.List<Result> getResults();

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:00:09 PM
         * @version V1.0
         * @return isResultConstant
         */
        boolean isResultConstant();

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:00:15 PM
         * @version V1.0
         * @param flg flg
         */
        void setResultConstant(boolean flg);

        /**
         * @Description: 是否强制输出<result>标签中定义的项目，如果为true，非<result>标签中的项目将不被输出
         * @author king
         * @since Jun 7, 2013 4:00:22 PM
         * @version V1.0
         * @return isResultAppoint
         */
        boolean isResultAppoint();

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:00:37 PM
         * @version V1.0
         * @param resultAppoint resultAppoint
         */
        void setResultAppoint(boolean resultAppoint);
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 3:59:03 PM
     * @version V1.0
     */
    public interface IActionStep extends IParam, IResult {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 3:59:47 PM
         * @version V1.0
         * @return Index
         */
        String getIndex();

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:00:43 PM
         * @version V1.0
         * @return Sql
         */
        String getSql();

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:00:49 PM
         * @version V1.0
         * @return Source
         */
        String getSource();
    }

    /**
     * @Title: RequestParamFace.java  
     * @Description: 
     * @author king
     * @since Jul 18, 2013 10:24:39 AM 
     * @version V1.0
     */
    public interface ISingle {
        /**
         * @Description: 
         * @author king
         * @since Jul 18, 2013 10:24:43 AM 
         * @version V1.0
         * @return isResultConstant
         */
        boolean isResultConstant();

        /**
         * @Description: 
         * @author king
         * @since Jul 18, 2013 10:24:49 AM 
         * @version V1.0
         * @return SelectedKey
         */
        String getSelectedKey();
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 4:00:58 PM
     * @version V1.0
     */
    public interface IExcel {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:01:03 PM
         * @version V1.0
         * @param excel excel
         */
        void setExcel(Excel excel);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:01:11 PM
         * @version V1.0
         * @return excel
         */
        Excel getExcel();
    }

    /**
     * @ClassName: IChecker
     * @Description: 验证用接口
     * @author king
     * @since Sep 15, 2012 7:35:32 PM
     */
    public interface IChecker {
        /**
         * @Description: 添加验证对象
         * @author king
         * @since Sep 15, 2012 7:35:28 PM
         * @param check 验证对象
         */
        void addCheck(Checker check);

        /**
         * @Description: 取得验证对象数组
         * @author king
         * @since Sep 15, 2012 7:36:09 PM
         * @return 验证对象数组
         */
        java.util.List<Checker> getChecks();

        /**
         * @Description: 取得验证标示
         * @author king
         * @since Sep 15, 2012 7:36:28 PM
         * @return 验证标示
         */
        String getIndex();
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 4:01:19 PM
     * @version V1.0
     */
    public interface IItem {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:01:23 PM
         * @version V1.0
         * @param items items
         */
        void setItems(Items items);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:01:34 PM
         * @version V1.0
         * @return items
         */
        Items getItems();
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 4:01:40 PM
     * @version V1.0
     */
    public interface IDataSource {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:01:43 PM
         * @version V1.0
         * @param dataSource dataSource
         */
        void setDataSource(DataSource dataSource);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:01:51 PM
         * @version V1.0
         * @return dataSource
         */
        DataSource getDataSource();
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 4:01:57 PM
     * @version V1.0
     */
    public interface ICmb {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:02:07 PM
         * @version V1.0
         * @param cmb cmb
         */
        void addCmb(Cmb cmb);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:02:12 PM
         * @version V1.0
         * @return List<Cmb>
         */
        java.util.List<Cmb> getCmbs();
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 4:02:21 PM
     * @version V1.0
     */
    public interface IExternal {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:02:26 PM
         * @version V1.0
         * @param external external
         */
        void addExternal(External external);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:02:32 PM
         * @version V1.0
         * @return List<External>
         */
        java.util.List<External> getExternals();
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 4:04:19 PM
     * @version V1.0
     */
    public interface IWhether {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:04:24 PM
         * @version V1.0
         * @param condition condition
         */
        void addWhethers(Condition condition);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:04:38 PM
         * @version V1.0
         * @return List<Condition>
         */
        java.util.List<Condition> getWhethers();
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 4:05:03 PM
     * @version V1.0
     */
    public interface ICondition {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:05:06 PM
         * @version V1.0
         * @param id id
         */
        void setConditionId(String id);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:05:15 PM
         * @version V1.0
         * @return id
         */
        String getConditionId();

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:05:21 PM
         * @version V1.0
         * @param condition condition
         */
        void addCondition(Condition condition);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:05:27 PM
         * @version V1.0
         * @return List<Condition>
         */
        java.util.List<Condition> getConditions();
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 4:05:38 PM
     * @version V1.0
     */
    public interface IFormat {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:05:42 PM
         * @version V1.0
         * @param format format
         */
        void addFormat(Format format);

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:05:49 PM
         * @version V1.0
         * @return List<Format>
         */
        java.util.List<Format> getFormats();
    }

    /**
     * @Title: RequestParamFace.java
     * @Description:
     * @author king
     * @since Jun 7, 2013 4:06:25 PM
     * @version V1.0
     */
    public interface IList {

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:07:26 PM
         * @version V1.0
         * @return PageSize
         */
        String getPageSize();

        /**
         * @Description:
         * @author king
         * @since Jun 7, 2013 4:07:33 PM
         * @version V1.0
         * @param pageSize PageSize
         */
        void setPageSize(String pageSize);

    }

}
