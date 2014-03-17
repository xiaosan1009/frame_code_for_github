package cn.smeltery.handler.err;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.handler.AbstractAction;

public class ErrAction extends AbstractAction {

    @Override
    public void onItemPostExecute(TableRowMap tab, String id) 
        throws RichClientWebException {
        final String resCode = request.get("resCode");
        final RichClientWebException ex = new RichClientWebException(resCode);
        ex.setInformation(true);
        throw ex;
    }
}
