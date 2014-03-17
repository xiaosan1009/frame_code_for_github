package cn.smeltery.handler.common;

import cn.smeltery.data.ProvinceDBMappingManager;
import cn.smeltery.service.IDBSourceSwitchService;

import com.hisunsray.commons.res.Config;
import com.richClientFrame.dao.DynamicDataSource;
import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.handler.AbstractDataSourceHandler;
import com.richClientFrame.util.ConstantsUtil;

public class DataSourceHandler extends AbstractDataSourceHandler {
    
    private IDBSourceSwitchService dbSwitchService;
    
    public String changeDataSourceByUser() throws RichClientWebException {
        TableRowMap userInfo = null;
        String provinceId = null;
        final String userLogin = request.get("userLogin");
        // 设置数据源为路由库
        DynamicDataSource.setDb(Config.getProperty("routerDataSource"));
        try {
            userInfo = dbSwitchService.queryUserInfoByUserLogin(userLogin);
        } catch (Exception e) {
            log.fatal(ConstantsUtil.ErrInfo.SYSTEM_ERROR + e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR);
        }
        if (userInfo == null) {
            log.error("在路由库中没有查到用户信息【userLogin=" + userLogin + "】！");
            return null;
        }
        provinceId = userInfo.getString("PROVINCEID");
        if (provinceId == null) {
            log.error("在路由库中查到用户信息【userLogin=" + userLogin + "】的省份id为null！");
            return null;
        }
        final String dbSeq = ProvinceDBMappingManager.sDbSourceSeqTable.get(provinceId);
        if (dbSeq != null) {
            log.info("选择的数据源名称：" + dbSeq);
            DynamicDataSource.setDb(dbSeq);
        } else {
            log.error("数据源省份映射表中没有对应的记录存在【provinceId=" + provinceId + "】.");
        }
        return dbSeq;
    }
    
    public void setDbSwitchService(IDBSourceSwitchService dbSwitchService) {
        this.dbSwitchService = dbSwitchService;
    }
}
