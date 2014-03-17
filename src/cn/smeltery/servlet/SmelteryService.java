package cn.smeltery.servlet;

import cn.smeltery.util.ProjectConstants;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ControlMenuMap;
import com.richClientFrame.servlet.AbstractRichClientFrameServlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目主servlet.
 * @author king
 * @since 2010.07.28
 */
public class SmelteryService extends AbstractRichClientFrameServlet {

    private static final long serialVersionUID = 1L;
    
    /**
     * 资源文件路径取得.
     * @return 资源文件路径
     */
    public String getResourcePath() {
        return ProjectConstants.DIR_RESOURCE;
    }

    /**
     * 是否为用户登出操作.
     * @param param request
     * @return 判断结果
     */
    public boolean isLogout(Param param) {
        return ProjectConstants.MENU_DISP_CODE.equalsIgnoreCase(param.dispCode) 
            && ProjectConstants.LOGOUT_CMD_CODE.equalsIgnoreCase(param.cmdCode);
    }

    /**
     * servlet 初始化方法.
     * @param context 服务器对象
     * @throws RichClientWebException RichClientWebException
     */
    public void initialize(ServletContext context) 
        throws RichClientWebException {
        ControlMenuMap.setPath(
                context.getRealPath(File.separator) + context.getInitParameter("menuPath"));
        ControlMenuMap.getInstance();
    }
}
