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
        // ��������ԴΪ·�ɿ�
        DynamicDataSource.setDb(Config.getProperty("routerDataSource"));
        try {
            userInfo = dbSwitchService.queryUserInfoByUserLogin(userLogin);
        } catch (Exception e) {
            log.fatal(ConstantsUtil.ErrInfo.SYSTEM_ERROR + e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR);
        }
        if (userInfo == null) {
            log.error("��·�ɿ���û�в鵽�û���Ϣ��userLogin=" + userLogin + "����");
            return null;
        }
        provinceId = userInfo.getString("PROVINCEID");
        if (provinceId == null) {
            log.error("��·�ɿ��в鵽�û���Ϣ��userLogin=" + userLogin + "����ʡ��idΪnull��");
            return null;
        }
        final String dbSeq = ProvinceDBMappingManager.sDbSourceSeqTable.get(provinceId);
        if (dbSeq != null) {
            log.info("ѡ�������Դ���ƣ�" + dbSeq);
            DynamicDataSource.setDb(dbSeq);
        } else {
            log.error("����Դʡ��ӳ�����û�ж�Ӧ�ļ�¼���ڡ�provinceId=" + provinceId + "��.");
        }
        return dbSeq;
    }
    
    public void setDbSwitchService(IDBSourceSwitchService dbSwitchService) {
        this.dbSwitchService = dbSwitchService;
    }
}
