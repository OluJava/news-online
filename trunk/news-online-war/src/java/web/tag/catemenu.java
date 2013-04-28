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
public class catemenu extends SimpleTagSupport {
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
	    if(!list.isEmpty()){
		out.print(" <i class=\"arrow-main-nav\"></i>");
	    }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in catemenu tag", ex);
        }
    }

    public void setCateId(String cateId) {
	this.cateId = cateId;
    }

}
