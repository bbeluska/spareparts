package assemblers;

import model.User;
import sparepartsEJBClient.dtos.UserDTO;

public class UserAssembler {

	public UserDTO userToDto(User user){
		UserDTO dto = new UserDTO();
		if (user != null){	
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setPassword(user.getPassword());
			dto.setType(user.getType());
			dto.setUserId(user.getUserId());
			dto.setUserName(user.getUserName());
		}
		return dto;
	}
}
