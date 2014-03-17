package cn.smeltery.handler.client;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractAction;

public class ClientAction extends AbstractAction {
    
    @Override
    public void onItemPostExecute(TableRowMap tab, String id) throws RichClientWebException {
        System.out.println(request.get("clientId"));
        tab.put("clientName", "value", "1");
    }
    
}
