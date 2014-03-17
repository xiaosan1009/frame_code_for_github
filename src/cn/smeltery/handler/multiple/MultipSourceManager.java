package cn.smeltery.handler.multiple;

import cn.smeltery.util.ProjectConstants;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractHandler;
import com.richClientFrame.service.IDbService;

import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： MultipSourceManager
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Jan 16, 20133:52:16 PM
 * @author king
 */
public class MultipSourceManager extends AbstractHandler {
    
    private static int count = 1;
    
    public boolean testMultipSource() throws DataAccessException, RichClientWebException {
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("userId", "testUser-new" + count++);
        conditions.put("username", "testUser" + count++);
        conditions.put("password", "testUser" + count++);
        conditions.put("userType", "1");
        conditions.put("status", "2");
        getDb().insert("Multip.registUserBasic", conditions);
        conditions.put("userType", "111");
//        getDb().insert("Multip.registUserBasic", conditions);
        getDb().get(ProjectConstants.Db.DB1).insert("Multip.registUserBasic2", conditions);
//        db2.insert("Multip.registUserBasic2", conditions);
        return false;
    }

}
