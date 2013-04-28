/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.tag;

import javax.ejb.EJB;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import web.bean.CategorySBLocal;

/**
 *
 * @author Khatmau_sr
 */
public class category extends SimpleTagSupport {
    @EJB
    private CategorySBLocal categorySB;
    private int categoryId;

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            out.print(categorySB.getCategoryById(categoryId).getTitle());

        } catch (java.io.IOException ex) {
            throw new JspException("Error in category tag", ex);
        }
    }

    public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
    }

}
