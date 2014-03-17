package cn.smeltery.handler.common;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.handler.impl.FormatHandlerImpl;
import com.richClientFrame.util.CommonUtil;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FormatHandler extends FormatHandlerImpl {
    
    public String countDecimalFormat(String value, String[] params) {
        final TableRowMap tab = db.query("Decimal.getDecimalInfo");
        final int countDecimal = Integer.parseInt(CommonUtil.toString(tab.get("COUNT")));
        final String zero = CommonUtil.toString((int)Math.pow(10, countDecimal)).substring(1);
        final DecimalFormat df1 = new DecimalFormat("#0." + zero); 
        df1.setRoundingMode(RoundingMode.HALF_UP);
        return df1.format(Double.parseDouble(value));
    }
    
    public String priceDecimalFormat(String value, String[] params) {
        final TableRowMap tab = db.query("Decimal.getDecimalInfo");
        final int priceDecimal = Integer.parseInt(CommonUtil.toString(tab.get("PRICE")));
        final String zero = CommonUtil.toString((int)Math.pow(10, priceDecimal)).substring(1);
        final DecimalFormat df1 = new DecimalFormat("#0." + zero); 
        df1.setRoundingMode(RoundingMode.HALF_UP);
        return df1.format(Double.parseDouble(value));
    }

}
