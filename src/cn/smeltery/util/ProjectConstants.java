package cn.smeltery.util;

import com.hisunsray.commons.res.Config;

import java.io.File;

public class ProjectConstants {
    
    public static final String DIR_RESOURCE = "WEB-INF" + File.separator 
                                                + "classes" + File.separator
                                                + "cn" + File.separator
                                                + "smeltery" + File.separator
                                                + "resource" + File.separator;
    
    public static final String LOGIN_DISP_CODE = "000001";
    
    public static final String MENU_DISP_CODE = "000002";
    
    public static final String LOGOUT_CMD_CODE = "2";
    
    public static final class Db {
        
        public static final String DB1 = Config.getProperty("db1");
        
    }
}
