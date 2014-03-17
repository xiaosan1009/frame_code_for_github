package cn.smeltery.handler.sample;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractHandler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServletHandler extends AbstractHandler {
    
    public boolean testServlet(HttpServletRequest request, 
            HttpServletResponse response, SessionData session) throws RichClientWebException {
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("userName", session.getUserInfo().getUserName());
        conditions.put("passWord", session.getUserInfo().getLoginPass());
        TableRowMap tab = getDb().query("Login.checkUserAvailable", conditions);
        request.setAttribute("userName", tab.get("CREATE_DATE"));
        return false;
    }

}
