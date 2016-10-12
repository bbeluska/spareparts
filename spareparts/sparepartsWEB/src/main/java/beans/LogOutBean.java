package beans;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@ManagedBean
@RequestScoped
public class LogOutBean implements Serializable {
	private String label = "Logout";
	private static final long serialVersionUID = -4975864716415272511L;

	final static Logger logger = Logger.getLogger(LogOutBean.class);

	public String logOut() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		try {
			session.invalidate();
		} catch (IllegalStateException e) {
			logger.error("Exception in logOut: " + e);
		}
		return "loggedOut";
	}

	public String getLabel() {
		return label;
	}

}
