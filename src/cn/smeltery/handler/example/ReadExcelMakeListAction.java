package cn.smeltery.handler.example;

import cn.smeltery.handler.CommonAction;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.excel.CellBean;
import com.richClientFrame.data.excel.RowBean;
import com.richClientFrame.data.excel.SheetBean;
import com.richClientFrame.data.param.UploadParam;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.ExcelUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ReadExcelMakeListAction
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Jul 30, 2013 11:26:06 AM
 * @author king
 */
public class ReadExcelMakeListAction extends CommonAction {
    
    private static Map<String, List<SheetBean>> threadLocal;
    
    private static Map<String, List<SheetBean>> dynamichreadLocal;
    
    @Override
    public void onUploadPostExecute(UploadParam upload, List<TableRowMap> tabs, String id)
        throws RichClientWebException {
        if ("READ_EXCEL_MAKE_LIST_INFO".equals(id)) {
            setThreadLocal(tabs);
        } else if ("READ_DYNAMIC_EXCEL_MAKE_LIST_INFO".equals(id)) {
            setDynamicThreadLocal(tabs);
        }
    }
    
    @Override
    public void onListPostExecute(List<TableRowMap> list, String id) throws RichClientWebException {
        if ("CREATE_SHEET_NAME_MENU".equals(id)) {
            final List<SheetBean> tabs = getThreadLocal();
            if (tabs == null) {
                return;
            }
            for (int i = 0; i < tabs.size(); i++) {
                final TableRowMap tab = new TableRowMap();
                tab.put("sheetName", tabs.get(i).getSheetName());
                list.add(tab);
            }
        } else if ("CREATE_SHEET_DATAS".equals(id)) {
            final List<SheetBean> tabs = getThreadLocal();
            if (tabs == null) {
                return;
            }
            final String sheetName = request.get("sheetName");
            SheetBean sheetBean = null;
            if (CommonUtil.isEmpty(sheetName)) {
                sheetBean = tabs.get(0);
            } else {
                for (int i = 0; i < tabs.size(); i++) {
                    if (sheetName.equals(tabs.get(i).getSheetName())) {
                        sheetBean = tabs.get(i);
                        break;
                    }
                }
            }
            list.addAll(sheetBean.getRowTabs());
        } else if ("CREATE_DYNAMIC_SHEET_NAME_MENU".equals(id)) {
            final List<SheetBean> tabs = getDynamicThreadLocal();
            if (tabs == null) {
                return;
            }
            for (int i = 0; i < tabs.size(); i++) {
                final TableRowMap tab = new TableRowMap();
                tab.put("sheetName", tabs.get(i).getSheetName());
                list.add(tab);
            }
        }
    }
    
    @Override
    public String onItemPostExecute(List<TableRowMap> list, String id)
        throws RichClientWebException {
        if ("CREATE_DYNAMIC_SHEET_DATAS".equals(id)) {
            final List<SheetBean> tabs = getDynamicThreadLocal();
            if (tabs == null) {
                return "";
            }
            final String sheetName = request.get("sheetName");
            SheetBean sheetBean = null;
            if (CommonUtil.isEmpty(sheetName)) {
                sheetBean = tabs.get(0);
            } else {
                for (int i = 0; i < tabs.size(); i++) {
                    if (sheetName.equals(tabs.get(i).getSheetName())) {
                        sheetBean = tabs.get(i);
                        break;
                    }
                }
            }
            List<RowBean> rowList = sheetBean.getRowList();
            for (int i = 0; i < rowList.size(); i++) {
                RowBean rowBean = rowList.get(i);
                List<CellBean> celList = rowBean.getCellList();
                for (int j = 0; j < celList.size(); j++) {
                    TableRowMap data = new TableRowMap();
                    CellBean cellBean = celList.get(j);
                    data.put("row", rowBean.getSite());
                    data.put("cell", cellBean.getSite());
                    data.put("value", cellBean.getValue());
                    data.put("background", cellBean.getBackground());
                    data.put("rowspan", cellBean.getRowspan());
                    data.put("colspan", cellBean.getColspan());
                    data.put("align", cellBean.getAlign());
                    data.put("valign", cellBean.getValign());
                    list.add(data);
                }
            }
            
        }
        return "";
    }
    
    private String getKey(int index) {
        switch (index) {
            case 0:
                return "unit";
            case 1:
                return "inner";
            case 2:
                return "batteryNo";
            case 3:
                return "createDate";
            case 4:
                return "checkDate";
            case 5:
                return "volume";
            case 6:
                return "weight";
            case 7:
                return "wallThickness";
            case 8:
                return "medium";
            default:
                break;
        }
        return null;
    }
    
    /**
     * @Description: 
     * @author king
     * @since Jul 30, 2013 11:39:39 AM 
     * @version V1.0
     * @param tabs tabs
     */
    private void setThreadLocal(List<TableRowMap> tabs) {
        if (this.threadLocal == null) {
            threadLocal = new HashMap<String, List<SheetBean>>();
        }
        threadLocal.put(session.getUserInfo().getUserId(), ExcelUtil.createExcelInfo(tabs));
    }
    
    /**
     * @Description: 
     * @author king
     * @since Jul 30, 2013 11:39:39 AM 
     * @version V1.0
     * @param tabs tabs
     */
    private void setDynamicThreadLocal(List<TableRowMap> tabs) {
        if (this.dynamichreadLocal == null) {
            dynamichreadLocal = new HashMap<String, List<SheetBean>>();
        }
        dynamichreadLocal.put(session.getUserInfo().getUserId(), ExcelUtil.createExcelInfo(tabs));
    }
    
    /**
     * @Description: 
     * @author king
     * @since Jul 30, 2013 1:38:22 PM 
     * @version V1.0
     * @return ThreadLocal
     */
    private List<SheetBean> getThreadLocal() {
        if (threadLocal == null) {
            return null;
        }
        return threadLocal.get(session.getUserInfo().getUserId());
    }
    
    /**
     * @Description: 
     * @author king
     * @since Jul 30, 2013 1:38:22 PM 
     * @version V1.0
     * @return ThreadLocal
     */
    private List<SheetBean> getDynamicThreadLocal() {
        if (dynamichreadLocal == null) {
            return null;
        }
        return dynamichreadLocal.get(session.getUserInfo().getUserId());
    }

}
