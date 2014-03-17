package com.richClientFrame.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

/**
 * 分页标签.
 * @author king
 * @since 2011.03.06
 */
public class CommonPagingDisplayTag extends BodyTagSupportBase {
    
    private static final long serialVersionUID = 1L;
    
    private StringBuffer outerPrint;

    /**
     * 生成标签前方法.
     * 
     * @return 生成结果
     * @throws JspTagException JspTagException
     */
    public int doStartTag() throws JspTagException {
        outerPrint = new StringBuffer();
        outerPrint.append(" <div class=\"pagingTag\" style=\"width:300px;margin:0 auto;margin-top: 20px;\">");
        outerPrint.append("     <table width=\"300px\" border=\"0\" ");
        outerPrint.append("         cellspacing=\"0\" cellpadding=\"0\" class=\"font12\">");
        outerPrint.append("         <tr>");
        outerPrint.append("             <td align=\"center\">");
        outerPrint.append("                 <input onclick=\"JK.pagePrev()\" ");
        outerPrint.append("                     name=\"page_prev1\" id=\"page_prev1\" ");
        outerPrint.append("                     class=\"commonBtnMinimum\" type=\"button\" value=\"" 
                + getResourceInfo("commonPageList", "page_prev1") + "\" />");
        outerPrint.append("             </td>");
        outerPrint.append("             <td align=\"center\">");
        outerPrint.append("                 <input type=\"hidden\" name=\"currentpage\" ");
        outerPrint.append("                     id=\"currentpage\" value=\"1\">");
        outerPrint.append("                 <span id=\"now_page_num1\" style=\"width: 30px\" ");
        outerPrint.append("                     class=\"font12\">1</span>");
        outerPrint.append("                 <span id=\"page_separator1\">/</span>");
        outerPrint.append("                 <input type=\"hidden\" name=\"totalPages\" id=\"totalPages\">");
        outerPrint.append("                 <span id=\"all_page_num1\" style=\"width: 30px\" class=\"font12\"></span>");
        outerPrint.append("             </td>");
        outerPrint.append("             <td width=\"40\" align=\"center\">");
        outerPrint.append("                 <input onclick=\"JK.pageNext()\" id=\"page_next1\" name=\"page_next1\" ");
        outerPrint.append("                     type=\"button\" value=\"" 
                + getResourceInfo("commonPageList", "page_next1") + "\" class=\"commonBtnMinimum \" />");
        outerPrint.append("             </td>");
        outerPrint.append("             <td align=\"center\">");
        outerPrint.append("                 <input type=\"text\" name=\"page_num1\" ");
        outerPrint.append("                     id=\"page_num1\" class=\"pageNobox\" />");
        outerPrint.append("             </td>");
        outerPrint.append("             <td width=\"70\" align=\"right\">");
        outerPrint.append("                 <input id=\"page_move1\" onclick=\"JK.pageGoto()\" ");
        outerPrint.append("                     type=\"button\" name=\"page_move1\" value=\"" 
                + getResourceInfo("commonPageList", "page_move1") + "\" class=\"pageMoveBtn\" />");
        outerPrint.append("             </td>");
        outerPrint.append("         </tr>");
        outerPrint.append("     </table>");
        outerPrint.append(" </div>");
        
        return SKIP_BODY;
    }

    /**
     * 生成标签后方法.
     * 
     * @return 生成结果
     * @throws JspException JspException
     */
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().print(outerPrint.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
