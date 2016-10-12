package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import utils.Constants;

@ManagedBean
@ViewScoped
public class UserPageBean implements Serializable {

	private static final long serialVersionUID = -6831122319450308990L;
	// session related
	private FacesContext facesContext;
	private HttpSession session;
	private String userName;
	private String userType;

	@PostConstruct
	public void init() {
		// session management
		session = null;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(false);
		if (null != session.getAttribute(Constants.USERNAME)) {
			userName = session.getAttribute(Constants.USERNAME).toString();
			userType = session.getAttribute(Constants.USERTYPE).toString();
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
