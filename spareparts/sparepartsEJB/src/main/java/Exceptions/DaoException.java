package Exceptions;

public class DaoException extends RuntimeException{

	private static final long serialVersionUID = -366002265101832759L;
	
	public DaoException(){
		super();
	}
	
	public DaoException(String message, Throwable cause){
		super(message, cause);
	}
	
	public DaoException(String message){
		super(message);
	}
	
	public DaoException(Throwable cause){
		super(cause);
	}

}
