
package com.richClientFrame.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： EncodingFilter
 * 类描述 ： 应用编码格式过滤器
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.06.01
 * @author king
 */
public class EncodingFilter extends AbstractCommonFilter {

    // ----------------------------------------------------- Instance Variables

    /**
     * The default character encoding to set for requests that pass through this
     * filter.
     */
    private String encoding;

    /**
     * The filter configuration object we are associated with. If this value is
     * null, this filter instance is not currently configured.
     */
    private FilterConfig filterConfig;

    /**
     * Should a character encoding specified by the client be ignored?
     */
    private boolean ignore = true;

    // --------------------------------------------------------- Public Methods

    /**
     * Take this filter out of service.
     */
    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

    /**
     * Select and set (if specified) the character encoding to be used to
     * interpret request parameters for this request.
     * 
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        // Conditionally select and set the character encoding to be used
        if (ignore || (request.getCharacterEncoding() == null)) {
            final String encoding = selectEncoding(request);
            if (encoding != null) {
                request.setCharacterEncoding(encoding);
            }
            response.setCharacterEncoding(encoding);
            response.setContentType("text/html; charset=" + encoding);
        }

        // Pass control on to the next filter
        chain.doFilter(request, response);

    }

    /**
     * Place this filter into service.
     * 
     * @param filterConfig The filter configuration object
     */
    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        final String value = filterConfig.getInitParameter("ignore");
        if (value == null) {
            this.ignore = true;
        } else if (value.equalsIgnoreCase("true")) {
            this.ignore = true;
        } else if (value.equalsIgnoreCase("yes")) {
            this.ignore = true;
        } else {
            this.ignore = false;
        }

    }

    // ------------------------------------------------------ Protected Methods

    /**
     * Select an appropriate character encoding to be used, based on the
     * characteristics of the current request and/or filter initialization
     * parameters. If no character encoding should be set, return
     * <code>null</code>.
     * <p>
     * The default implementation unconditionally returns the value configured
     * by the <strong>encoding</strong> initialization parameter for this
     * filter.
     * 
     * @param request The servlet request we are processing
     */
    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }

}
