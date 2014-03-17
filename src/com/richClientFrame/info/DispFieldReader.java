package com.richClientFrame.info;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� DispFieldReader
 * ������ �� �����ʾ��Ŀ��ȡ��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.05.30
 * @author king
 */
public final class DispFieldReader {

    /**
     * ���캯��.
     */
    private DispFieldReader() {
        super();
    }
    
    /**
     * �ļ����Զ�ȡ.
     * 
     * @param fileName �ļ���
     * @param enc �ļ���׺
     * @return �ļ���Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    public static Map<String, String> getAllLine(String fileName, String enc) 
        throws RichClientWebException {
        
        final Map<String, String> map = new HashMap<String, String>();
        
        BufferedReader in = null;
        
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), enc));
            
            while (in.ready()) {
                final String str = in.readLine();
                if (str.startsWith(ConstantsUtil.Str.POUND) || str.length() == 0) {
                    continue;
                }
                final String[] splitData = str.split(ConstantsUtil.Str.REGEX_EQUAL);
                
                String[] temp = null;
                if (splitData != null && splitData.length >= 2) {
                    temp = new String[2];
                    temp[0] = CommonUtil.nvl(splitData[0]);
                    temp[1] = CommonUtil.nvl(splitData[1]);
                }
                
                if (temp != null) {
                    map.put(temp[0], temp[1]);
                }
                
            }
            
        } catch (UnsupportedEncodingException ue) {
            ue.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_UNSUPPORTED_ENCODING_ERROR, ue);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_FILE_NOT_FOUND, fnfe);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, ex);
                }
            }
        }
        
        return map;
    }

}
