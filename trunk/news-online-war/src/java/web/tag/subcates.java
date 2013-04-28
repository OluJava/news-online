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
public class subcates extends SimpleTagSupport {
    @EJB
    private CategorySBLocal categorySB;
    private int cateId;

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
	    List<Category> list = categorySB.getSubCategories(cateId + "");
	    if(!list.isEmpty()){
		out.println("<ul>");
		for(Category cate : list)
		{
		    if(!cate.getNewsCollection().isEmpty())
			out.println("<li><a href=\"Client?action=viewCategory&cateId="+cate.getCategoryId()+"\">"+cate.getTitle()+"</a></li>");
		}
		out.println("</ul>");
	    }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in subcates tag", ex);
        }
    }

    public void setCateId(int cateId) {
	this.cateId = cateId;
    }

}
