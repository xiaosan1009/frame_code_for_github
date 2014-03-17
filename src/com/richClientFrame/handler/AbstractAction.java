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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� AbstractAction
 * ������ �� �����¼��������ӿ�ʵ����
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2011.10.01
 * @author king
 */
public class AbstractAction implements IActionFace {
    
    /**
     * �������������.
     */
    protected ContorlMemcached memcached;
    
    /**
     * �ⲿ�������.
     */
    protected IExternalFace external;
    
    /**
     * ��ʽ������.
     */
    protected IFormatFace format;
    
    /**
     * ����Դ�л�����.
     */
    protected IDataSourceFace dataSource;
    
    /**
     * �ͻ����������.
     */
    protected Param request;
    
    /**
     * �ͻ���session����.
     */
    protected SessionData session;
    
    /**
     * ���ݿ��������.
     */
    protected IDbService db;
    
    /**
     * Ӧ�ö���.
     */
    protected ServletContext app;
    
    /**
     * log����.
     */
    protected LogUtil log = new LogUtil(this.getClass());
    
    /**
     * ����Ŀִ��ǰ����.
     * 
     * @param whereMap sql����
     * @param id ����ĿID�������ж����ĸ���Ŀģ��
     * @return �Ƿ�ִ�����ݿ������false����ִ�У�true��ִ�С�
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onItemPreExecute(Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /**
     * ����Ŀִ�к󷽷�.
     * 
     * @param tab sql�������ݣ��������뻭����Ŀ��
     * @param id ����ĿID�������ж����ĸ���Ŀģ��
     * @throws RichClientWebException RichClientWebException
     */
    public void onItemPostExecute(TableRowMap tab, String id) 
        throws RichClientWebException {
    }
    
    /**
     * ����Ŀִ�к󷽷�.
     * 
     * @param tabs sql�������ݣ��������뻭����Ŀ��
     * @param id ����ĿID�������ж����ĸ���Ŀģ��
     * @return ��Ӧ����ؼ�ID
     * @throws RichClientWebException RichClientWebException
     */
    public String onItemPostExecute(List<TableRowMap> tabs, String id)
        throws RichClientWebException {
        return null;
    }
    
    /**
     * ����Ŀִ��ǰ����.
     * 
     * @param whereMap sql����
     * @param id ����ĿID�������ж����ĸ���Ŀģ��
     * @return �Ƿ�ִ�����ݿ������false����ִ�У�true��ִ�С�
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onListPreExecute(Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /**
     * ����Ŀִ�к󷽷�.
     * 
     * @param list sql�������ݣ��������뻭����Ŀ��
     * @param id ����ĿID�������ж����ĸ���Ŀģ��
     * @throws RichClientWebException RichClientWebException
     */
    public void onListPostExecute(List<TableRowMap> list, String id) 
        throws RichClientWebException {
    }
    
    /**
     * ����Ŀ��ά����ִ�к󷽷�.
     * 
     * @param tabs sql�������ݣ��������뻭����Ŀ��
     * @param dimensionList sql�������ݣ��������뻭����Ŀ��
     * @param id ����ĿID�������ж����ĸ���Ŀģ��
     * @throws RichClientWebException RichClientWebException
     */
    public void onDemensionListPostExecute(List<TableRowMap> tabs, TabRowMapDimensions dimensionList, String id) 
        throws RichClientWebException {
    }
    
    /**
     * ��ɾ��ִ��ǰ����.
     * 
     * @param whereMap sql����
     * @param id ��ɾ����ĿID�������ж����ĸ���Ŀģ��
     * @return �Ƿ�ִ�����ݿ������false����ִ�У�true��ִ�С�
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onUpdatePreExecute(Map<String, Object> whereMap, String id)
        throws RichClientWebException {
        return true;
    }

    /**
     * ��ɾ��ִ�к󷽷�.
     * 
     * @param tab sql�������ݣ��������뻭����Ŀ��
     * @param flag ��ɾ�����ݿ�������
     * @param id ����ĿID�������ж����ĸ���Ŀģ��
     * @throws RichClientWebException RichClientWebException
     */
    public void onUpdatePostExecute(TableRowMap tab, boolean flag, String id) 
        throws RichClientWebException {
    }
    
    /**
     * �ϴ�ִ��ǰ����.
     * 
     * @param upload �ϴ���Ϣ����
     * @param whereMap sql����
     * @param id ��ɾ����ĿID�������ж����ĸ���Ŀģ��
     * @return �Ƿ�ִ�����ݿ������false����ִ�У�true��ִ�С�
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onUploadPreExecute(UploadParam upload, Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /**
     * �ϴ�ִ�к󷽷�.
     * 
     * @param upload �ϴ���Ϣ����
     * @param tab sql�������ݣ��������뻭����Ŀ��
     * @param id �ϴ���ĿID�������ж����ĸ���Ŀģ��
     * @throws RichClientWebException RichClientWebException
     */
    public void onUploadPostExecute(UploadParam upload, TableRowMap tab, String id)
        throws RichClientWebException {
    }
    
    /**
     * �ϴ�ִ�к󷽷�.
     * 
     * @param upload �ϴ���Ϣ����
     * @param tabs sql�������ݻ����ϴ��ļ������ݣ��������뻭����Ŀ��
     * @param id �ϴ���ĿID�������ж����ĸ���Ŀģ��
     * @throws RichClientWebException RichClientWebException
     */
    public void onUploadPostExecute(UploadParam upload, List<TableRowMap> tabs, String id)
        throws RichClientWebException {
    }
    
    /**
     * �ⲿ��������ǰ�������б������.
     * 
     * @param list ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ�����ݿ������false����ִ�У�true��ִ�С�
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onExternalPreExecute(List<TableRowMap> list, String id)
        throws RichClientWebException {
        return true;
    }
    
    /**
     * �ⲿ�������ú�������б������.
     * 
     * @param list ����
     * @param id sqlִ�б�ʶ
     * @throws RichClientWebException RichClientWebException
     */
    public void onExternalPostExecute(List<TableRowMap> list, String id)
        throws RichClientWebException {
    }

    /**
     * �ⲿ��������ǰ�����ݵ�����.
     * 
     * @param tab ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ�����ݿ������false����ִ�У�true��ִ�С�
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onExternalPreExecute(TableRowMap tab, String id) throws RichClientWebException {
        return true;
    }
    
    /**
     * �ⲿ�������ú�����ݵ�����.
     * 
     * @param tab ����
     * @param id sqlִ�б�ʶ
     * @throws RichClientWebException RichClientWebException
     */
    public void onExternalPostExecute(TableRowMap tab, String id) throws RichClientWebException {
    }

    /**
     * �����������������.
     * @param memcach �������������
     */
    public void setMemcached(ContorlMemcached memcach) {
        this.memcached = memcach;
    }

    /**
     * �ͻ��������������.
     * @param param �ͻ����������
     */
    public void setParam(Param param) {
        this.request = param;
    }

    /**
     * �ͻ���session��������.
     * @param seData �ͻ���session����
     */
    public void setSeData(SessionData seData) {
        this.session = seData;
    }

    /**
     * �ⲿ�����������.
     * @param external �ⲿ�������
     */
    public void setExternal(IExternalFace external) {
        this.external = external;
    }

    /**
     * ���ݿ������������.
     * 
     * @param db ���ݿ��������
     */
    public void setDb(IDbService db) {
        this.db = db;
    }

    /**
     * �ı�sql��������.
     * 
     * @param whereMap ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ�����ݿ������false����ִ�У�true��ִ�С�
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onCheckPreExecute(Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /**
     * ������֤.
     * 
     * @param tab ����
     * @param id sqlִ�б�ʶ
     * @return ��֤�����true����֤ͨ����false����֤ʧ�ܡ�
     * @throws RichClientWebException RichClientWebException
     */
    public Map<String, List<String>> onCheckPostExecute(TableRowMap tab, String id) 
        throws RichClientWebException {
        return null;
    }

    /**
     * ��ʽ����������.
     * 
     * @param format ��ʽ������
     */
    public void setFormat(IFormatFace format) {
        this.format = format;
    }

    /**
     * ����Դ�л���������.
     * 
     * @param dataSourceFace ����Դ�л�����
     */
    public void setDataSource(IDataSourceFace dataSourceFace) {
        this.dataSource = dataSourceFace;
    }

    /**
     * �����ж�ִ��ǰ����.
     * @param whereMap ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ�����ݿ������false����ִ�У�true��ִ�С�
     * @throws RichClientWebException RichClientWebException
     */
    public boolean onConditionPreExecute(Map<String, Object> whereMap, String id) 
        throws RichClientWebException {
        return true;
    }

    /** 
     * @Description: ����Ӧ�ö���
     * @author king
     * @since Nov 3, 2012 7:32:24 PM 
     * 
     * @param context Ӧ�ö���
     */
    public void setApplication(ServletContext context) {
        this.app = context;
    }

}
