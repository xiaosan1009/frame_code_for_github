package cn.smeltery.handler.turnover;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractAction;
import com.richClientFrame.util.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpareTurnoverAction extends AbstractAction {

    @Override
    public boolean onUpdatePreExecute(Map<String, Object> whereMap, String id)
        throws RichClientWebException {
        final String[] indexs = request.getList("indexs");
        final String[] batchs = request.getList("batch");
        final String[] outNumbers = request.getList("outNumber");
        final String[] numbers = request.getList("number");
        final String[] parentTypeIds = request.getList("parentTypeId");
        final String[] typeIds = request.getList("typeId");
        final String[] prices = request.getList("price");
        final String[] effectiveDates = request.getList("effectiveDate");
        final String[] orderNos = request.getList("orderNo");
        final String[] supplierValue = request.getList("supplierValue");
        final List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < indexs.length; i++) {
            final int no = Integer.parseInt(indexs[i]);
            final Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("parentTypeId", parentTypeIds[no]);
            dataMap.put("typeId", typeIds[no]);
            dataMap.put("batch", batchs[no]);
            dataMap.put("price", format.execute(prices[no], "priceDecimalFormat", null));
            dataMap.put("effectiveDate", effectiveDates[no]);
            dataMap.put("orderNo", orderNos[no]);
            dataMap.put("supplierValue", supplierValue[no]);
            String outNumber = outNumbers[no];
            if (CommonUtil.isEmpty(outNumber)) {
                outNumber = numbers[no];
            }
            dataMap.put("outNumber", format.execute(outNumber, "countDecimalFormat", null));
            dataList.add(dataMap);
        }
        whereMap.put("datas", dataList);
        return true;
    }
    
    @Override
    public void onListPostExecute(List<TableRowMap> arg0, String arg1)
    		throws RichClientWebException {
    	for (int i = 0; i < arg0.size(); i++) {
    		TableRowMap tr = arg0.get(i);
    		String num = tr.getString("NUM");
    		String turnover_price = tr.getString("TURNOVER_PRICE");
    		String sum = "0";
    		if (CommonUtil.isNotEmpty(num) && CommonUtil.isNotEmpty(turnover_price)) {
    			sum = CommonUtil.toString(Float.parseFloat(num) * Float.parseFloat(turnover_price));
    			sum = format.execute(sum, "priceDecimalFormat", null);
    		}
    		tr.put("sum", sum);
		}
    }
}
