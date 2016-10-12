package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class ManagerPageBean implements Serializable {

	private static final long serialVersionUID = -7583482883503423071L;
	// session related
	private FacesContext facesContext;
	private HttpSession session;
	private String userName;
	private String userType;

	@PostConstruct
	public void init() {
		// session management
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(false);
		if (null != session.getAttribute("userName")) {
			userName = session.getAttribute("userName").toString();
			userType = session.getAttribute("userType").toString();
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
