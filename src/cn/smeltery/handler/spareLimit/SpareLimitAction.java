package cn.smeltery.handler.spareLimit;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractAction;

import java.util.Map;

/**
 * material unit manager.
 * @author king
 * @since 2012.02.09
 */
public class SpareLimitAction extends AbstractAction {
    
    @Override
    public boolean onUpdatePreExecute(Map<String, Object> whereMap, String id)
        throws RichClientWebException {
        final int listIndex = Integer.parseInt(request.get("listindex"));
        final String[] typeIds = request.getList("typeId");
        final String[] topLimits = request.getList("topLimit");
        final String[] floorLimits = request.getList("floorLimit");
        whereMap.put("typeId", typeIds[listIndex]);
        whereMap.put("topLimit", topLimits[listIndex]);
        whereMap.put("floorLimit", floorLimits[listIndex]);
        return true;
    }

}
