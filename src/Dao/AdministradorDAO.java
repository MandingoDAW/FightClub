
package Dao;

/**
 * Interficie que que instancia els metodes de la classe AdministradorDAOImpl
 * @author Erik
 */
public interface AdministradorDAO {
    public void registrar();
    public void modificarUsuario(int id);
    public void eliminarUsuario(int id);
    public void nuevaIdUsuario();
    public void actualizarUsuarioSQL(int id, String campo, String NuevoValor, boolean isAdmin);
}
