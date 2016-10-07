package sparepartsEJBClient.interfaces;

import java.util.List;

import javax.ejb.Remote;

import sparepartsEJBClient.dtos.UserDTO;

@Remote
public interface UserBeanInterface {
	
	List<UserDTO> getAllUsers();
	UserDTO login(String userName, String password);

}
