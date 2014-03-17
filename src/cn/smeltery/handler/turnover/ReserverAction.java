package cn.smeltery.handler.turnover;

import java.util.List;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractAction;
import com.richClientFrame.util.CommonUtil;

public class ReserverAction extends AbstractAction {
	
	@Override
    public void onListPostExecute(List<TableRowMap> arg0, String arg1)
    		throws RichClientWebException {
    	for (int i = 0; i < arg0.size(); i++) {
    		TableRowMap tr = arg0.get(i);
    		String num = tr.getString("AMOUNT");
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
