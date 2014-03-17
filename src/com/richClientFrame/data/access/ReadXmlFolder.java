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
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： ReadXmlFolder
 * 类描述 ： 读取XML文件.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class ReadXmlFolder {
    
    private static LogUtil sLog = new LogUtil(ReadXmlFolder.class, true);
    
    protected static final String DOC_KEY = "doc";
    
    /**
     * 构造函数.
     */
    public ReadXmlFolder() {
        super();
    }
    
    /**
     * 处理XML文件
     * @param path XML的绝对路径
     * @return xml信息
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
     * 配置文件数据解析
     * @param list 配置文件对象列表
     * @param file 文件对象
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
     * 指定属性相应的值的取得
     * @param element doc对象
     * @param elementName 节点名
     * @param attrName 属性名
     * @return String 指定属性相应的值
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
