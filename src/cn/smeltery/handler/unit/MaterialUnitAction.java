package cn.smeltery.handler.unit;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractAction;

import java.util.Map;

/**
 * material unit manager.
 * @author king
 * @since 2012.02.09
 */
public class MaterialUnitAction extends AbstractAction {
    
    @Override
    public boolean onUpdatePreExecute(Map<String, Object> whereMap, String id)
        throws RichClientWebException {
        final int listIndex = Integer.parseInt(request.get("listindex"));
        final String[] typeIds = request.getList("typeId");
        final String[] units = request.getList("unit");
        whereMap.put("typeId", typeIds[listIndex]);
        whereMap.put("unit", units[listIndex]);
        return true;
    }

}
