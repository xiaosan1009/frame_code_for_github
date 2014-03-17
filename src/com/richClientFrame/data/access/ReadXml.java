package com.richClientFrame.data.access;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.richClientFrame.exception.RichClientWebException;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� ReadXml
 * ������ �� ��ȡXML�ļ�.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class ReadXml {
    
    protected static final String DOC_KEY = "doc";
    
    /**
     * ���캯��.
     */
    public ReadXml() {
        super();
    }
    
    /**
     * ����XML�ļ�
     * @param path XML�ľ���·��
     * @return xml��Ϣ
     */
    protected static Hashtable<String, Document> parse(String path) {
        Document doc = null;
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            if (path != null) {
                final DocumentBuilder builder = factory.newDocumentBuilder();
                final File file = new File(path);
                doc = builder.parse(file);
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

        final Hashtable<String, Document> hash = new Hashtable<String, Document>();
        if (doc != null) {
            hash.put(DOC_KEY, doc);
        }
        
        return hash;

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
