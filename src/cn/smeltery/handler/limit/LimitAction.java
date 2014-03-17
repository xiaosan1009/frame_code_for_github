package cn.smeltery.handler.limit;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.menu.MenuData;
import com.richClientFrame.data.menu.MenuInformation;
import com.richClientFrame.data.menu.SubMenuData;
import com.richClientFrame.data.response.TabRowMapDimensions;
import com.richClientFrame.data.response.TabRowMapList;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractAction;
import com.richClientFrame.info.ControlMenuMap;

import java.util.List;

/**
 * 
 * @author King
 *
 */
public class LimitAction extends AbstractAction {
    
    @Override
    public void onDemensionListPostExecute(List<TableRowMap> tabs, TabRowMapDimensions dimensionList,
            String id) throws RichClientWebException {
        
        final MenuInformation menuInformation = ControlMenuMap.getInstance().getMenuInfo();
        for (int i = 0; i < menuInformation.getMenuList().size(); i++) {
            final TabRowMapList mapList = new TabRowMapList();
            final MenuData firstData = menuInformation.getMenuList().get(i);
            final List<SubMenuData> subList = firstData.getSublist();
            for (int j = 0; j < subList.size(); j++) {
                final TableRowMap tab = new TableRowMap();
                tab.put("id_0011", subList.get(j).getSubname());
                tab.put("id_0012", subList.get(j).getFunctionId());
                mapList.addLine(tab);
            }
            final TableRowMap tab = new TableRowMap();
            tab.put("id_0001", firstData.getName());
            tab.put("id_0002", firstData.getParentId());
            mapList.setDimensionLine(tab);
            dimensionList.addDimension(mapList);
        }
    }

}
