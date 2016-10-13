package daos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import model.User;

/**
 * @author Balint Bela
 * This is a Data Transfer Object for User entity
 */
@Stateless(name = "UserDAO", mappedName = "ejb.UserDAO")
public class UserDAO {

	static final Logger logger = Logger.getLogger(UserDAO.class);

	@PersistenceContext(unitName = "spareparts")
	private EntityManager entityManager;

	/**
	 * Gets all the records from the 'user' table.
	 * 
	 * @return A list of 'User' type models or an empty list if no records found
	 */
	public List<User> getAllUsers() {
		List<User> result = new ArrayList<>();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);
			CriteriaQuery<User> allEntities = cq.select(root);
			TypedQuery<User> tq = entityManager.createQuery(allEntities);
			result = tq.getResultList();
			return result;

		} catch (PersistenceException e) {
			logger.error("Exception in getAllUsers: " + e);
			return result;
		}

	}

	/**
	 * This method verifies if the user name exists with the corresponding
	 * password
	 * 
	 * @param userName
	 *            The user name to be verified.
	 * @param password
	 *            The password for the given user.
	 * @return User type model if the login data is correct or 'null' in case of
	 *         invalid password or inexistent user
	 */
	public User login(String userName, String password) {
		try {
			TypedQuery<User> query = entityManager.createQuery(
					"SELECT u from User u WHERE u.userName = :userName AND u.password = :password", User.class);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			return query.getSingleResult();
		} catch (PersistenceException e) {
			logger.error("Exception in login: " + e);
			return null;
		}
	}

}
