package cn.smeltery.handler.login;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractAction;
import com.richClientFrame.info.UserInfo;
import com.richClientFrame.util.CommonUtil;

public class LoginAction extends AbstractAction {
    
    @Override
    public void onItemPostExecute(TableRowMap tab, String id)
        throws RichClientWebException {
        UserInfo userInfo = new UserInfo();
        if (session.getUserInfo() != null) {
            userInfo = session.getUserInfo();
        }
        userInfo.setUserId(CommonUtil.toString(tab.get("USER_ID")));
        userInfo.setUserName(CommonUtil.toString(tab.get("USER_NAME")));
        userInfo.setPassword(CommonUtil.toString(tab.get("USER_PASSWORD")));
        userInfo.setLevel(CommonUtil.toString(tab.get("USER_LEVEL")));
        session.setUserInfo(userInfo);
    }

}
