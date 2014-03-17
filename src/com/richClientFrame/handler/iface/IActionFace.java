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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� IActionFace
 * ������ �� �����¼��������ӿ�
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.05.30
 * @author king
 */
public interface IActionFace {
    
    /**
     * ����request����.
     * 
     * @param param request
     */
    void setParam(Param param);
    
    /**
     * ����session����.
     * 
     * @param seData session
     */
    void setSeData(SessionData seData);
    
    /**
     * ���û������������.
     * 
     * @param memcached �������������
     */
    void setMemcached(ContorlMemcached memcached);
    
    /**
     * �����ⲿ�������.
     * 
     * @param external �ⲿ�������
     */
    void setExternal(IExternalFace external);
    
    /**
     * ���ø�ʽ������.
     * 
     * @param format ʽ������
     */
    void setFormat(IFormatFace format);
    
    /**
     * ��������Դ�л�����.
     * 
     * @param dataSource ����Դ�л�����
     */
    void setDataSource(IDataSourceFace dataSource);
    
    /**
     * �������ݿ��������.
     * 
     * @param db ���ݿ��������
     */
    void setDb(IDbService db);
    
    /**
     * �ı�sql��������.
     * 
     * @param whereMap ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ��sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onItemPreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;
    
    /**
     * ��������Ŀ����.
     * 
     * @param tab ����
     * @param id sqlִ�б�ʶ
     * @throws RichClientWebException RichClientWebException
     */
    void onItemPostExecute(TableRowMap tab, String id) throws RichClientWebException;
    
    /**
     * ��������Ŀ����.
     * 
     * @param tabs ����
     * @param id sqlִ�б�ʶ
     * @return ��Ӧ����ؼ�ID
     * @throws RichClientWebException RichClientWebException
     */
    String onItemPostExecute(List<TableRowMap> tabs, String id) throws RichClientWebException;
    
    /**
     * ��������Ŀ����.
     * 
     * @param upload upload��Ϣ
     * @param tab ����
     * @param id sqlִ�б�ʶ
     * @throws RichClientWebException RichClientWebException
     */
    void onUploadPostExecute(UploadParam upload, TableRowMap tab, String id) 
        throws RichClientWebException;
    
    /**
     * ��������Ŀ����.
     * 
     * @param upload upload��Ϣ
     * @param tabs ����
     * @param id sqlִ�б�ʶ
     * @throws RichClientWebException RichClientWebException
     */
    void onUploadPostExecute(UploadParam upload, List<TableRowMap> tabs, String id) 
        throws RichClientWebException;
    
    /**
     * �ı�sql��������.
     * 
     * @param whereMap ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ��sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onListPreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;
    
    /**
     * ��������Ŀ����.
     * 
     * @param list ����
     * @param id sqlִ�б�ʶ
     * @throws RichClientWebException RichClientWebException
     */
    void onListPostExecute(List<TableRowMap> list, String id) throws RichClientWebException;
    
    /**
     * �ⲿ��������ǰ�������б������.
     * 
     * @param list ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ��sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onExternalPreExecute(List<TableRowMap> list, String id) throws RichClientWebException;
    
    /**
     * �ⲿ�������ú�������б������.
     * 
     * @param list ����
     * @param id sqlִ�б�ʶ
     * @throws RichClientWebException RichClientWebException
     */
    void onExternalPostExecute(List<TableRowMap> list, String id) throws RichClientWebException;
    
    /**
     * �ⲿ��������ǰ�����ݵ�����.
     * 
     * @param tab ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ��sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onExternalPreExecute(TableRowMap tab, String id) throws RichClientWebException;
    
    /**
     * �ⲿ�������ú�����ݵ�����.
     * 
     * @param tab ����
     * @param id sqlִ�б�ʶ
     * @throws RichClientWebException RichClientWebException
     */
    void onExternalPostExecute(TableRowMap tab, String id) throws RichClientWebException;
    
    /**
     * �ı�sql��������.
     * 
     * @param whereMap ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ�����ݿ������false����ִ�У�true��ִ�С�
     * @throws RichClientWebException RichClientWebException
     */
    boolean onUpdatePreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;

    /**
     * ������������.
     * 
     * @param tab ����
     * @param flag ���½��
     * @param id sqlִ�б�ʶ
     * @throws RichClientWebException RichClientWebException
     */
    void onUpdatePostExecute(TableRowMap tab, boolean flag, String id) throws RichClientWebException;
    
    /**
     * ���ϴ�֮ǰ�Ĳ���.
     * 
     * @param upload upload��Ϣ
     * @param whereMap ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ��sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onUploadPreExecute(UploadParam upload, Map<String, Object> whereMap, String id) 
        throws RichClientWebException;
    
    /**
     * ����Ŀ��ά����ִ�к󷽷�.
     * 
     * @param tabs sql�������ݣ��������뻭����Ŀ��
     * @param dimensionList sql�������ݣ��������뻭����Ŀ��
     * @param id ����ĿID�������ж����ĸ���Ŀģ��
     * @throws RichClientWebException RichClientWebException
     */
    void onDemensionListPostExecute(List<TableRowMap> tabs, TabRowMapDimensions dimensionList, String id) 
        throws RichClientWebException;
    
    /**
     * ��ִ֤��ǰ����.
     * 
     * @param whereMap ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ��sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onCheckPreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;
    
    /**
     * �����ж�ִ��ǰ����.
     * @param whereMap ����
     * @param id sqlִ�б�ʶ
     * @return �Ƿ�ִ��sql
     * @throws RichClientWebException RichClientWebException
     */
    boolean onConditionPreExecute(Map<String, Object> whereMap, String id) throws RichClientWebException;
    
    /**
     * ��ִ֤�к󷽷�.
     * 
     * @param tab ����
     * @param id sqlִ�б�ʶ
     * @return ��֤�����true����֤ͨ����false����֤ʧ�ܡ�
     * @throws RichClientWebException RichClientWebException
     */
    Map<String, List<String>> onCheckPostExecute(TableRowMap tab, String id) throws RichClientWebException;
    
    /** 
    * @Description: ����Ӧ�ö���
    * @author king
    * @since Nov 3, 2012 7:32:24 PM 
    * 
    * @param context Ӧ�ö���
    */
    void setApplication(ServletContext context);

}
