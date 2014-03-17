package cn.com.junit.action;

import com.hisunsray.commons.res.Config;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.exception.BreakProgressException;
import com.richClientFrame.exception.EngineCommonException;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.CommonActionHandler;
import com.richClientFrame.handler.iface.CustomContentLoaderListener;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.info.ControlDispField;
import com.richClientFrame.info.ControlErrorMap;
import com.richClientFrame.info.ControlMasters;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.info.ControlResourceMap;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.HtmlUtil;

import net.sf.json.JSONObject;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� TestActionCase
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Apr 20, 201311:20:53 AM
 * @author king
 */
public class TestActionCase {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        CustomContentLoaderListener.setApplicationContext(
                new ClassPathXmlApplicationContext("classpath:**/spring/**/*.xml"));
        String realPath = "C:\\Users\\king\\work\\workspace\\frame_workspace\\web\\smeltery\\WebRoot\\";
        final String log4jPath = realPath + "WEB-INF\\xml\\log4j.properties";
        PropertyConfigurator.configure(log4jPath);
        final String configPath = realPath + "\\WEB-INF\\xml\\config.xml";
        final String errorMenuPath = "WEB-INF\\jsp\\frame\\err.jsp";
        final String masterPath = realPath + "WEB-INF\\xml\\errmsg";
        final String configFilePath = "WEB-INF\\xml\\config.properties";
        
        final String sesPath = realPath + ControlConfig.DEF_SESDIR;
        
        ControlConfig.setSesfPath(sesPath);
        ControlConfig.setErrorMenuPath(errorMenuPath);
        if (CommonUtil.isNotEmpty(configFilePath)) {
            Config.setConfigResource(realPath + configFilePath);
        }
        
        // Config.xml
        ControlConfig.setConfPath(configPath);
        ControlConfig.setRealPath(realPath);
        ControlConfig.setHostUrl(CommonUtil.getAppUrlPath());
        ControlConfig.getInstance();
        
        ControlResourceMap.setFramePath(realPath + ConstantsUtil.Frame.DIR_RESOURCE);
        ControlResourceMap.getInstance();
        
        // ���Ʋ�����������Ϣ��ȡ(WEB-INF/xml/request)
        ControlRequestMap.setPath(realPath);
        ControlRequestMap.getInstance();
        
        // ��֤�ļ���ȡ(WEB-INF/err)
        ControlErrorMap.setPath(realPath);
        ControlErrorMap.getInstance();
        
        // ������Ŀ�ļ���ȡ(WEB-INF/disp/field & resource)
        ControlDispField.setPath(realPath);
        ControlDispField.getInstance();
        
        // �����ʾ��Ϣ��ȡ(WEB-INF/xml/errmsg)
        ControlMasters.setPath(masterPath);
        ControlMasters.getInstance();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testAction() {
        try {
            Param param = new Param();
            param.dispCode = "020900";
            param.cmdCode = "1";
            param.clientType = ConstantsUtil.Client.FLEX_TYPE;
            AbstractResponseData resData = CommonActionHandler.doAction(param);
            JSONObject header = HtmlUtil.createJsonResponseHeader(resData);
//            System.out.println(header.toString());
            resData.setClientType(ConstantsUtil.Client.ANDROID_TYPE);
            List<Map<String, String>> responseData = HtmlUtil.getDataList(resData);
            for (int i = 0; i < responseData.size(); i++) {
                Map<String, String> data = responseData.get(i);
                StringBuilder sb = new StringBuilder();
                for (String key : data.keySet()) {
                    sb.append(key + ":" + data.get(key) + ";");
                }
                System.out.println(sb.toString());
            }
//            ResponseList list = resData.getDataList();
//            for (int i = 0; i < list.size(); i++) {
//                ResponseLine resLine = list.getLine(i);
//            }
            
        } catch (BreakProgressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RichClientWebException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (EngineCommonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

}
