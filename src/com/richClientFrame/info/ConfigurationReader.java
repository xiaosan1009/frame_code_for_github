package com.richClientFrame.info;

import com.richClientFrame.data.access.ReadXml;
import com.richClientFrame.exception.RichClientWebException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ConfigurationReader
 * 类描述 ： 环境设定文件读取类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.03.19
 * @author king
 */
public class ConfigurationReader extends ReadXml {

    /**
     * 构造函数.
     */
    public ConfigurationReader() {
        super();
    }

    /**
     * 环境设定文件内容读取.
     * @param path 环境设定文件绝对路径
     * @return Configuration 环境设定文件对象
     * @throws RichClientWebException RichClientWebException
     */
    public static synchronized Configuration read(String path) {
        
        Configuration config = null;
        Document doc;
        Hashtable<?, ?> hash;
        
        hash = parse(path);
        if (hash.get(DOC_KEY) == null) {
            doc = null;
        } else {
            doc = (Document)hash.get(DOC_KEY);
        }

        if (doc == null) {
            return config;
        }
        
        config = analyze(doc);
        return config;
    }


    /**
     * 解析环境设定文件获得环境设定文件对象.
     * @param doc 环境设定文件对应的document对象
     * @return Configuration 环境设定文件对象
     */
    private static Configuration analyze(Document doc) {
        final Configuration config = new Configuration();
        NodeList nodeList = null;

        // 文件内容的取得
        Element settings;
        nodeList = doc.getElementsByTagName("settings");
        final Map<String, Map<String, String>> configMap = new HashMap<String, Map<String,String>>();
        if (nodeList != null && nodeList.getLength() != 0) {
            settings = (Element) nodeList.item(0);
            final NodeList nodes = settings.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                final NamedNodeMap namedNodeMap = nodes.item(i).getAttributes();
                if (namedNodeMap != null) {
                    final Map<String, String> nodeMap = new HashMap<String, String>();
                    for (int j = 0; j < namedNodeMap.getLength(); j++) {
                        final Node node = namedNodeMap.item(j);
                        node.getNodeName();
                        node.getNodeValue();
                        nodeMap.put(node.getNodeName(), node.getNodeValue());
                    }
                    configMap.put(nodes.item(i).getNodeName(), nodeMap);
                }
            }
            config.setConfigMap(configMap);
        }
        
        return config;
    }

}
