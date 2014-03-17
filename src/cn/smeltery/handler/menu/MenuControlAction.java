package cn.smeltery.handler.menu;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.menu.MenuData;
import com.richClientFrame.data.menu.MenuInformation;
import com.richClientFrame.data.menu.SubMenuData;
import com.richClientFrame.data.response.TabRowMapDimensions;
import com.richClientFrame.data.response.TabRowMapList;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractAction;
import com.richClientFrame.info.ControlMenuMap;
import com.richClientFrame.info.UserInfo;
import com.richClientFrame.util.CommonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author King
 *
 */
public class MenuControlAction extends AbstractAction {
    
    @Override
    public void onListPostExecute(List<TableRowMap> arg0, String arg1)
        throws RichClientWebException {
        if ("queryParentNodes".equals(arg1)) {
            final UserInfo userInfo = session.getUserInfo();
            final MenuInformation menuInformation = ControlMenuMap.getInstance().getMenuInfo();
            final Map<String, String> parentMap = menuInformation.getParentMap();
            if (userInfo != null && "1".equals(userInfo.getLevel())) {
                final Map<String, Map<String, String>> firstMap = new HashMap<String, Map<String,String>>();
                for (int m = 0; m < arg0.size(); m++) {
                    if ("0".equals(arg0.get(m).get("LIMIT_LEVEL"))) {
                        final Map<String, String> secondMap = new HashMap<String, String>();
                        firstMap.put(CommonUtil.toString(arg0.get(m).get("LIMIT_NUMBER")), secondMap);
                    } else {
                        final String limitNumber = CommonUtil.toString(arg0.get(m).get("LIMIT_NUMBER"));
                        final String limitKey = parentMap.get(limitNumber);
                        firstMap.get(limitKey).put(limitNumber, limitNumber);
                    }
                }
                arg0.clear();
                for (int i = 0; i < menuInformation.getMenuList().size(); i++) {
                    final MenuData outData = menuInformation.getMenuList().get(i);
                    if (firstMap.containsKey(outData.getParentId())) {
                        final Map<String, String> secondMap = firstMap.get(outData.getParentId());
                        final TabRowMapList mapList = new TabRowMapList();
                        final TableRowMap tab = new TableRowMap();
                        tab.put("name", outData.getName());
                        tab.put("pId", "0");
                        tab.put("open", "false");
                        tab.put("id", "p" + CommonUtil.toString(i));
                        arg0.add(tab);
                        final List<SubMenuData> subList = outData.getSublist();
                        for (int j = 0; j < subList.size(); j++) {
                            if (secondMap.containsKey(subList.get(j).getFunctionId())) {
                                final TableRowMap tab1 = new TableRowMap();
                                tab1.put("name", subList.get(j).getSubname());
                                tab1.put("disp", subList.get(j).getDispcode());
                                tab1.put("pId", "p" + CommonUtil.toString(i));
                                tab1.put("id", "p" + CommonUtil.toString(i) + "c" + CommonUtil.toString(j));
                                arg0.add(tab1);
                            }
                        }
                    }
                }
            } else {
                arg0.clear();
                for (int i = 0; i < menuInformation.getMenuList().size(); i++) {
                    final MenuData outData = menuInformation.getMenuList().get(i);
                    final TableRowMap tab = new TableRowMap();
                    tab.put("name", outData.getName());
                    tab.put("pId", "0");
                    tab.put("open", "false");
                    tab.put("id", "p" + CommonUtil.toString(i));
                    arg0.add(tab);
                    final List<SubMenuData> subList = outData.getSublist();
                    for (int j = 0; j < subList.size(); j++) {
                        final TableRowMap tab1 = new TableRowMap();
                        tab1.put("name", subList.get(j).getSubname());
                        tab1.put("disp", subList.get(j).getDispcode());
                        tab1.put("pId", "p" + CommonUtil.toString(i));
                        tab1.put("id", "p" + CommonUtil.toString(i) + "c" + CommonUtil.toString(j));
                        arg0.add(tab1);
                    }
                }
            }
        }
    }
    
    @Override
    public void onDemensionListPostExecute(List<TableRowMap> tabs, TabRowMapDimensions dimensionList,
            String id) throws RichClientWebException {
        
        if ("showBigType".equals(id)) {
            final UserInfo userInfo = session.getUserInfo();
            final MenuInformation menuInformation = ControlMenuMap.getInstance().getMenuInfo();
            final Map<String, String> parentMap = menuInformation.getParentMap();
            if (userInfo != null && "1".equals(userInfo.getLevel())) {
                final Map<String, Map<String, String>> firstMap = new HashMap<String, Map<String,String>>();
                for (int m = 0; m < tabs.size(); m++) {
                    if ("0".equals(tabs.get(m).get("LIMIT_LEVEL"))) {
                        final Map<String, String> secondMap = new HashMap<String, String>();
                        firstMap.put(CommonUtil.toString(tabs.get(m).get("LIMIT_NUMBER")), secondMap);
                    } else {
                        final String limitNumber = CommonUtil.toString(tabs.get(m).get("LIMIT_NUMBER"));
                        final String limitKey = parentMap.get(limitNumber);
                        firstMap.get(limitKey).put(limitNumber, limitNumber);
                    }
                }
                for (int i = 0; i < menuInformation.getMenuList().size(); i++) {
                    final MenuData outData = menuInformation.getMenuList().get(i);
                    if (firstMap.containsKey(outData.getParentId())) {
                        final Map<String, String> secondMap = firstMap.get(outData.getParentId());
                        final TabRowMapList mapList = new TabRowMapList();
                        final List<SubMenuData> subList = outData.getSublist();
                        for (int j = 0; j < subList.size(); j++) {
                            if (secondMap.containsKey(subList.get(j).getFunctionId())) {
                                final TableRowMap tab = new TableRowMap();
                                tab.put("id_0011", subList.get(j).getSubname());
                                tab.put("id_0012", subList.get(j).getDispcode());
                                mapList.addLine(tab);
                            }
                        }
                        final TableRowMap tab = new TableRowMap();
                        tab.put("id_0001", outData.getName());
                        tab.put("id_0002", CommonUtil.toString((mapList.size() + 1) * 30));
                        mapList.setDimensionLine(tab);
                        dimensionList.addDimension(mapList);
                    }
                }
            } else {
                for (int i = 0; i < menuInformation.getMenuList().size(); i++) {
                    final MenuData outData = menuInformation.getMenuList().get(i);
                    final TabRowMapList mapList = new TabRowMapList();
                    final List<SubMenuData> subList = outData.getSublist();
                    for (int j = 0; j < subList.size(); j++) {
                        final TableRowMap tab = new TableRowMap();
                        tab.put("id_0011", subList.get(j).getSubname());
                        tab.put("id_0012", subList.get(j).getDispcode());
                        mapList.addLine(tab);
                    }
                    final TableRowMap tab = new TableRowMap();
                    tab.put("id_0001", outData.getName());
                    tab.put("id_0002", CommonUtil.toString((mapList.size() + 1) * 30));
                    mapList.setDimensionLine(tab);
                    dimensionList.addDimension(mapList);
                }
            }
        }
    }

}
