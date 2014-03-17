package com.richClientFrame.data.access;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.LogUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� ReadXmlFolder
 * ������ �� ��ȡXML�ļ�.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class ReadXmlFolder {
    
    private static LogUtil sLog = new LogUtil(ReadXmlFolder.class, true);
    
    protected static final String DOC_KEY = "doc";
    
    /**
     * ���캯��.
     */
    public ReadXmlFolder() {
        super();
    }
    
    /**
     * ����XML�ļ�
     * @param path XML�ľ���·��
     * @return xml��Ϣ
     */
    protected static List<Document> parse(String path) {
        final List<Document> list = new ArrayList<Document>();

        try {
            if (path != null) {
                final File dir = new File(path);
                if (!dir.isDirectory()) {
                    return null;
                }
                parse(list, dir);
            }
        } catch (SAXException sxe) {
            sxe.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;

    }
    
    /**
     * �����ļ����ݽ���
     * @param list �����ļ������б�
     * @param file �ļ�����
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    private static void parse(List<Document> list, File file) 
        throws ParserConfigurationException, SAXException, IOException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        sLog.debug("parse", "start", "fileName = " + file.getName());
        if (file.isDirectory()) {
            final File[] childFiles = file.listFiles();
            for (int j = 0; j < childFiles.length; j++) {
                parse(list, childFiles[j]);
            }
        } else {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
            list.add(doc);
        }
        sLog.debug("parse", "end");
    }
    
    /**
     * ָ��������Ӧ��ֵ��ȡ��
     * @param element doc����
     * @param elementName �ڵ���
     * @param attrName ������
     * @return String ָ��������Ӧ��ֵ
     */
    protected static String extractAttribute(
        Element element,
        String elementName,
        String attrName) {

        NodeList list;
        list = element.getElementsByTagName(elementName);
        if (list == null || list.getLength() == 0) {
            return null;
        }
        String path;
        Element item;

        item = (Element) list.item(0);
        path = item.getAttribute(attrName);
        return path;
    }
}
