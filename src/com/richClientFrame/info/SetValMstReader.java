package com.richClientFrame.info;

import com.richClientFrame.data.SetValType;
import com.richClientFrame.data.access.ReadXml;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.CommonUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Hashtable;
import java.util.Map;

/**
 * 名称管理MASTER文件读取类.
 * @author King
 * @since 2010/08/07
 */
public class SetValMstReader extends ReadXml {

    /**
     * 构造函数.
     */
    public SetValMstReader() {
        super();
    }

    /**
     * 名称管理MASTER文件读取.
     * 
     * @param path 文件路径
     * @param errorMap 错误信息保存对象
     * @param headerMap 头信息保持对象
     * @throws RichClientWebException RichClientWebException
     */
    public static synchronized void read(
            String path, 
            Map<String, SetValType> errorMap, 
            Map<String, SetValType> headerMap) 
        throws RichClientWebException {
        Document doc;
        Hashtable<String, Document> hash;
        
        hash = parse(path);
        if (hash.get(DOC_KEY) == null) {
            doc = null;
        } else {
            doc = (Document)hash.get(DOC_KEY);
        }

        if (doc == null) {
            return;
        }
        
        analyze(doc, errorMap, headerMap);
    }


    /**
     * 名称管理MASTER文件读取.
     * 
     * @param doc 名称管理MASTER文件DOC对象
     * @param errorMap 错误信息保存对象
     * @param headerMap 头信息保持对象
     */
    private static void analyze(Document doc, Map<String, SetValType> errorMap, Map<String, SetValType> headerMap) {
        
        final NodeList nodeMainList = doc.getElementsByTagName("type");
        if (nodeMainList != null) {
            for (int i = 0; i < nodeMainList.getLength(); i++) {
                Element elemType = null;
                
                final Node nodeMain = nodeMainList.item(i);
                if (nodeMain instanceof Element) {
                    elemType = (Element)nodeMain;
                }
                final SetValType typeObj = reflectSettings(elemType);
                if ("header".equals(typeObj.getType())) {
                    headerMap.put(typeObj.getValue(), typeObj);
                } else {
                    errorMap.put(typeObj.getValue(), typeObj);
                }
            }
        }
    }
    
    /**
     * 名称管理MASTER文件解析.
     * 
     * @param typeElem 名称管理MASTER文件DOC对象父标签对象
     * @return 名称管理MASTER文件信息
     */
    private static SetValType reflectSettings(Element typeElem) {
        
        if (typeElem == null) {
            return null;
        }
        
        final SetValType typedata = new SetValType();
        
        String tmp = typeElem.getAttribute("value");
        if (CommonUtil.isNotEmpty(tmp)) {
            typedata.setValue(tmp);
        }
        tmp = typeElem.getAttribute("name_zh_CN");
        if (CommonUtil.isNotEmpty(tmp)) {
            typedata.setNameCN(tmp);
        }
        tmp = typeElem.getAttribute("type");
        if (CommonUtil.isNotEmpty(tmp)) {
            typedata.setType(tmp);
        }
        tmp = typeElem.getAttribute("name_eng");
        if (CommonUtil.isNotEmpty(tmp)) {
            typedata.setNameEng(tmp);
        }
        
        return typedata;
    }
}
