package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sparepartsEJBClient.dtos.UserDTO;
import sparepartsEJBClient.interfaces.UserBeanInterface;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -6681290729688169925L;
	private UserDTO currentUser;
	private String userName;
	private String password;

	@EJB
	private UserBeanInterface ubi;

	@PostConstruct
	public void init() {
		currentUser = new UserDTO();
		currentUser.setUserName("");
	}

	public String login() {
		UserDTO user = ubi.login(userName,password);
		if (null == user){
			return "failed";
		}else{
			return user.getType();
		}
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
