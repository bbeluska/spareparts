package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import sparepartsEJBClient.dtos.UserDTO;
import sparepartsEJBClient.interfaces.UserBeanInterface;
import utils.Constants;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -6681290729688169925L;
	
	final static Logger logger = Logger.getLogger(LoginBean.class);
	
	private UserDTO currentUser;
	private String userName;
	private String password;
	private UserDTO user;

	@EJB
	private UserBeanInterface ubi;

	@PostConstruct
	public void init() {
		currentUser = new UserDTO();
		currentUser.setUserName("");
	}

	// handle login
	public String login() {
		user = ubi.login(userName, password);
		if (null != user) {
			return loginSuccess();
		} else {
			return loginFailed();
		}
	}

	// create session and return the user type
	// user type is used in the faces-config.xml at the Navigation rules
	private String loginSuccess(){
		if (!user.getType().isEmpty()) {
			HttpSession session = getSession();
			session.setAttribute(Constants.USERTYPE, user.getType());
			session.setAttribute(Constants.USERNAME, user.getUserName());
			session.setAttribute(Constants.MENUFILE, user.getType().toLowerCase() + "menu.xhtml");
			return user.getType();
		} else {
			return Constants.FAILED;
		}
	}
	
	private HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	private String loginFailed() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", "Error"));
		userName = "";
		return Constants.FAILED;
	}

	public UserDTO getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDTO userDto) {
		this.currentUser = userDto;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
