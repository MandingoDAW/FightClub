
package Dao;

/**
 * Classe factoria d'objectes DAO.
 * Podem invocar a traves d'un mètode estàtic la instanciació
 * d'un objecte que correspon a un objecte EmpleatDAO.
 * Aquest patró ens proporciona un contracte per crear objectes sense
 * especificar la  classe concreta a la que corresponen.
*/

public class DAOFactory {

	public static AdministradorDAOImpl crearAdministradorDAO(){
		return new AdministradorDAOImpl();
	}

}
