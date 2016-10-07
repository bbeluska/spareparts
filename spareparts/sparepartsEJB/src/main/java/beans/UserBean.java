package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import Exceptions.DaoException;
import assemblers.UserAssembler;
import daos.UserDAO;
import model.User;
import sparepartsEJBClient.dtos.UserDTO;
import sparepartsEJBClient.interfaces.UserBeanInterface;

@Stateless
@LocalBean
public class UserBean implements Serializable, UserBeanInterface {

	private static final long serialVersionUID = 5022669426687458041L;

	@EJB
	private UserDAO userDAO;

	private UserAssembler userAssembler = new UserAssembler();

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> userDTOList = new ArrayList<>();
		try {
			List<User> userList = userDAO.getAllUsers();
			if (null != userList) {
				for (User user : userList) {
					userDTOList.add(userAssembler.userToDto(user));
				}
			}
			return userDTOList;
		} catch (DaoException e) {
			// TODO Log this exception
			return null;
		}
	}

	@Override
	public UserDTO login(String userName, String password) {
		try {
			User user = userDAO.login(userName, password);
			if (null != user) {
				UserDTO userDTO = userAssembler.userToDto(user);
				return userDTO;
			} else {
				return null;
			}

		} catch (DaoException e) {
			// TODO Log this exception
		}
		return null;
	}

}
