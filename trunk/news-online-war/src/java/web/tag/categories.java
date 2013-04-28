/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.tag;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import web.bean.CategorySBLocal;
import web.entity.Category;

/**
 *
 * @author Khatmau_sr
 */
public class categories extends SimpleTagSupport {
    @EJB
    private CategorySBLocal categorySB;
    private String cateId;

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
	    List<Category> list = categorySB.getSubCategories(cateId);
	    if(!list.isEmpty())
	    {
		int i = 0;
		for(Category item : list)
		{
		    out.println("<div style=\"border-bottom: 1px solid #B3B3B3; margin: 5px;\">");
		    out.println("<img style=\"margin-right: 5px\" src=\"images/list-arrow-bold.png\"/><a href=\"Client?action=viewCategory&cateId="+item.getCategoryId()+"\">"+item.getTitle()+"</a>");
		    out.println("</div>");
		    if(i == 4)
		    {
			out.println("<div style=\"border-bottom: 1px solid #B3B3B3; margin: 5px;\">");
			out.println("<img style=\"margin-right: 5px\" src=\"images/list-arrow-bold.png\"/><a href=\"Client?action=viewCategory&cateId="+cateId+"\">More...</a>");
			out.println("</div>");
			break;
		    }
		    i++;
		}
	    }
	    else
	    {
		out.println("<div style=\"border-bottom: 1px solid #B3B3B3; margin: 5px;\">");
		out.println("<img style=\"margin-right: 5px\" src=\"images/list-arrow-bold.png\"/>There's no sub category");
		out.println("</div>");
	    }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in categories tag", ex);
        }
    }

    public void setCateId(String cateId) {
	this.cateId = cateId;
    }

}
