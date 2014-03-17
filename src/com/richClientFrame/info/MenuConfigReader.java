package com.richClientFrame.info;

import com.richClientFrame.data.access.ReadXml;
import com.richClientFrame.data.menu.MenuData;
import com.richClientFrame.data.menu.MenuInformation;
import com.richClientFrame.data.menu.SubMenuData;
import com.richClientFrame.exception.RichClientWebException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * �˵��ļ���ȡ��.
 * @author King
 * @since 2010.03.19
 */
public class MenuConfigReader extends ReadXml {

    /**
     * ���캯��.
     */
    public MenuConfigReader() {
        super();
    }

    /**
     * �˵��ļ����ݶ�ȡ.
     * @param path �����趨�ļ�����·��
     * @return Configuration �����趨�ļ�����
     * @throws RichClientWebException RichClientWebException
     */
    public static synchronized MenuInformation read(String path) 
        throws RichClientWebException {
        
        MenuInformation config = null;
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
     * �����˵��ļ���ò˵��ļ�����.
     * @param doc �˵��ļ���Ӧ��document����
     * @return Configuration �˵��ļ�����
     */
    private static MenuInformation analyze(Document doc) {
        final MenuInformation config = new MenuInformation();

        // �ļ����ݵ�ȡ��
        final NodeList mainList = doc.getElementsByTagName("main");
        final List<MenuData> menuList = new ArrayList<MenuData>();
        final Map<String, MenuData> menuMap = new HashMap<String, MenuData>();
        final Map<String, String> parentMap = new HashMap<String, String>();
        if (mainList != null && mainList.getLength() != 0) {
            for (int mainIndex = 0; mainIndex < mainList.getLength(); mainIndex++) {
                final Element menu = (Element) mainList.item(mainIndex);
                final MenuData menuData = new MenuData();
                final String parentId = menu.getAttribute("parentId");
                menuData.setName(menu.getAttribute("name"));
                menuData.setParentId(parentId);
                final NodeList subNodes = menu.getElementsByTagName("sub");
                final List<SubMenuData> sublist = new ArrayList<SubMenuData>();
                for (int subIndex = 0; subIndex < subNodes.getLength(); subIndex++) {
                    final Element subMenu = (Element) subNodes.item(subIndex);
                    final SubMenuData subMenuData = new SubMenuData();
                    subMenuData.setSubname(subMenu.getAttribute("name"));
                    subMenuData.setDispcode(subMenu.getAttribute("dispcode"));
                    subMenuData.setFunctionId(subMenu.getAttribute("functionId"));
                    parentMap.put(subMenu.getAttribute("functionId"), parentId);
                    sublist.add(subMenuData);
                }
                menuData.setSublist(sublist);
                menuList.add(menuData);
                menuMap.put(parentId, menuData);
            }
            config.setMenuList(menuList);
            config.setMenuMap(menuMap);
            config.setParentMap(parentMap);
        }
        
        return config;
    }

}
