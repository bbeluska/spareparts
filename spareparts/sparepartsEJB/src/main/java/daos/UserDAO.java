package daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.User;

@Stateless(name = "UserDAO", mappedName = "ejb.UserDAO")
public class UserDAO {
	
	@PersistenceContext(unitName = "spareparts")
	private EntityManager entityManager;
	
	public List<User> getAllUsers(){
		try{
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);
			CriteriaQuery<User> allEntities = cq.select(root);
			TypedQuery<User> tq = entityManager.createQuery(allEntities);
			return tq.getResultList();
			
		} catch(PersistenceException e){
			// TODO log this exception
			return null;
		}
	}
	
	public User login(String userName, String password){
		try{
			TypedQuery<User> query = entityManager.createQuery(
					"SELECT u from User u WHERE u.userName = :userName AND u.password = :password", User.class);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			
			User user = query.getSingleResult();
			return user;
		} catch (PersistenceException e){
			// TODO log this exception
			return null;
		}
	}
	
}
