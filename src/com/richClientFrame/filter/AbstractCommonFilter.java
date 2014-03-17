package com.richClientFrame.filter;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseData;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.data.ResponseHeaderData;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.HtmlUtil;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： AbstractCommonFilter
 * 类描述 ： 共通过滤器类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2009.07.28
 * @author king
 */
public abstract class AbstractCommonFilter implements Filter {
    
    /**
     * 画面跳转到错误页面
     * @param request 请求对象
     * @param response 相应对象
     * @param param 请求参数对象
     * @param errCode 错误code
     * @throws RichClientWebException RichClientWebException
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void forwardErrorUrl(
            ServletRequest request, ServletResponse response, Param param, EngineExceptionEnum errCode)
        throws RichClientWebException, ServletException, IOException {
        forwardErrorUrl(request, response, param, errCode, false);
    }
    
    /**
     * 画面跳转到指定页面
     * @param request 请求对象
     * @param response 相应对象
     * @param param 请求参数对象
     * @param errCode 错误code
     * @param jsp 跳转的页面地址
     * @throws RichClientWebException RichClientWebException
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void forwardUrl(
            ServletRequest request, ServletResponse response, Param param, EngineExceptionEnum errCode, String jsp)
        throws RichClientWebException, ServletException, IOException {
        forwardErrorUrl(request, response, param, errCode, false, jsp);
    }
    
    /**
     * 画面跳转到错误页面
     * @param request 请求对象
     * @param response 相应对象
     * @param param 请求参数对象
     * @param errCode 错误code
     * @param isUpload 是否为上传功能
     * @throws RichClientWebException RichClientWebException
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void forwardErrorUrl(
            ServletRequest request, ServletResponse response, 
            Param param, EngineExceptionEnum errCode, boolean isUpload)
        throws RichClientWebException, ServletException, IOException {
        final ControlRequestMap requestMap = ControlRequestMap.getInstance();
        if (requestMap.isServlet(param)) {
            forwardErrorUrl(request, response, param, isUpload, errCode);
        } else {
            forwardErrorUrl(response, param.clientType, errCode);
        }
    }
    
    /**
     * 画面跳转到错误页面
     * @param request 请求对象
     * @param response 相应对象
     * @param param 请求参数对象
     * @param errCode 错误code
     * @param isUpload 是否为上传功能
     * @param jsp 跳转的页面地址
     * @throws RichClientWebException RichClientWebException
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void forwardErrorUrl(
            ServletRequest request, ServletResponse response, Param param, 
            EngineExceptionEnum errCode, boolean isUpload, String jsp)
        throws RichClientWebException, ServletException, IOException {
        final ControlRequestMap requestMap = ControlRequestMap.getInstance();
        if (requestMap.isServlet(param)) {
            forwardErrorUrl(request, response, param, isUpload, errCode, jsp);
        } else {
            forwardErrorUrl(response, param.clientType, errCode);
        }
    }
    
    /**
     * 画面跳转到错误页面
     * @param request 请求对象
     * @param response 相应对象
     * @param errCode 错误code
     * @throws RichClientWebException RichClientWebException
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void forwardErrorUrl(ServletRequest request, ServletResponse response, EngineExceptionEnum errCode)
        throws RichClientWebException, ServletException, IOException {
        forwardErrorUrl(request, response, false, errCode);
    }
    
    /**
     * 画面跳转到错误页面
     * @param request 请求对象
     * @param response 相应对象
     * @param errCode 错误code
     * @param isUpload 是否为上传功能
     * @throws RichClientWebException RichClientWebException
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void forwardErrorUrl(
            ServletRequest request, ServletResponse response, boolean isUpload, EngineExceptionEnum errCode)
        throws RichClientWebException, ServletException, IOException {
        forwardErrorUrl(request, response, null, isUpload, errCode);
    }
    
    /**
     * 画面跳转到错误页面
     * @param request 请求对象
     * @param response 相应对象
     * @param param 请求参数对象
     * @param errCode 错误code
     * @param isUpload 是否为上传功能
     * @throws RichClientWebException RichClientWebException
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void forwardErrorUrl(ServletRequest request, 
            ServletResponse response, Param param, boolean isUpload, EngineExceptionEnum errCode)
        throws RichClientWebException, ServletException, IOException {
        forwardErrorUrl(request, response, param, isUpload, errCode, ConstantsUtil.Page.ERROR_PATH);
    }
    
    /**
     * 画面跳转到错误页面
     * @param request 请求对象
     * @param response 相应对象
     * @param param 请求参数对象
     * @param errCode 错误code
     * @param isUpload 是否为上传功能
     * @param jsp 跳转的页面地址
     * @throws RichClientWebException RichClientWebException
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void forwardErrorUrl(ServletRequest request, 
            ServletResponse response, Param param, boolean isUpload, EngineExceptionEnum errCode, String jsp)
        throws RichClientWebException, ServletException, IOException {
        String clientType = ConstantsUtil.Client.AJAX_TYPE;
        ResponseHeader header = null;
        final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
        responseHeaderData.setResCode(errCode.getCode());
        if (param != null) {
            responseHeaderData.setParam(param);
            header = new ResponseHeader(responseHeaderData);
            clientType = param.clientType;
        } else {
            responseHeaderData.setDataKind(ConstantsUtil.DataType.SINGLE);
            header = new ResponseHeader(responseHeaderData);
        }
        header.setError(ResponseHeader.ERROR_HEADER_CODE);
        final ResponseData reData = new ResponseData(header);
        reData.setClientType(clientType);
        reData.setUpload(isUpload);
        request.setAttribute(AbstractResponseData.KEY_RESPONSE_DATA, reData);
        final RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
    
    /**
     * 相应错误信息（ajax功能）
     * 
     * @param response 相应对象
     * @param clientType 客户端类型
     * @param errCode 错误code
     * @throws RichClientWebException RichClientWebException
     * @throws IOException IOException
     */
    protected void forwardErrorUrl(ServletResponse response, String clientType, EngineExceptionEnum errCode)
        throws RichClientWebException, IOException {
        final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
        responseHeaderData.setResCode(errCode.getCode());
        responseHeaderData.setDataKind(ConstantsUtil.DataType.SINGLE);
        final ResponseHeader header = new ResponseHeader(responseHeaderData);
        header.setError(ResponseHeader.ERROR_HEADER_CODE);
        final ResponseData resData = new ResponseData(header);
        resData.setClientType(clientType);
        final BufferedWriter fout = new BufferedWriter(response.getWriter());
        fout.write(HtmlUtil.createResponseHeaderForFilter(resData));
        fout.close();
    }

}
