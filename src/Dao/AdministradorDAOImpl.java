
package Dao;

import com.mysql.jdbc.MysqlDataTruncation;
import projectem03uf6.BDAccessor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import projectem03uf6.Principal;


/**
 * Classe que implementa els metodes necessaris per administrar el joc(crear,eliminar editar) d'usuaris.
 * @author Erik
 */


public class AdministradorDAOImpl implements AdministradorDAO{
    private static Connection conn;
    private static BDAccessor bd= null;
    private static String consultaSQL;
    private int idNextUsuario;

/**
 * Metode que registrar un usuari nou a la BD
 * @author Erik
 */
    
    @Override
    public void registrar(){
        nuevaIdUsuario();
        bd = new BDAccessor();
        try {
            conn = bd.obtenirConnexio();
            Statement stat = conn.createStatement();;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println("Fight Club - Registrar: ");
        Scanner entrada = new Scanner(System.in);
        String nom;
        String password;
        int admin;
        String lema;

        System.out.println("Usuario: ");
        nom = entrada.next();
        System.out.println("Contraseña: ");
        password = entrada.next();
        System.out.println("Lema: ");
        lema = entrada.next();
        System.out.println("Administrador: (1 si, 0 no)");
        admin = entrada.nextInt();

        try{
            consultaSQL = "INSERT INTO users (idUsers,nom,password,lema,admin) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
            idNextUsuario+=1;
            pstmt.setInt(1, idNextUsuario);
            pstmt.setString(2, nom);
            pstmt.setString(3, password);
            pstmt.setString(4, lema);
            pstmt.setInt(5, admin);
            
            pstmt.executeUpdate();
            conn.commit();

        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
/**
 * Metode amb el cual modifiquem un usuari a la BD
 * @author Erik
 */
    @Override
    public void modificarUsuario(int id){
        bd = new BDAccessor();
        try {
            conn = bd.obtenirConnexio();
            Statement stat = conn.createStatement();;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        Scanner entrada = new Scanner(System.in);
        String nom;
        String password;
        int admin;
        String lema;

        System.out.println("Nuevo usuario: ");
        nom = entrada.next();
        System.out.println("Nueva contraseña: ");
        password = entrada.next();
        System.out.println("Lema: ");
        lema = entrada.next();
        System.out.println("Administrador: (1 si, 0 no)");
        admin = entrada.nextInt();
        if(admin == 0 || admin == 1){
            actualizarUsuarioSQL(id, "nom", nom, false);
            actualizarUsuarioSQL(id, "password", password, false);
            actualizarUsuarioSQL(id, "lema", lema, false);
            actualizarUsuarioSQL(id, "admin", String.valueOf(admin), true);
        }else{
            System.out.println("Opción incorrecta.");
        }

    }

/**
 * Metode que elimina un usuari a la BD
 * @author Jordi
 */
    @Override
    public void eliminarUsuario(int id){
        bd = new BDAccessor();
        try {
            conn = bd.obtenirConnexio();
            Statement stat = conn.createStatement();;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        BDAccessor bd = new BDAccessor();
        try {
            Connection conn = bd.obtenirConnexio();
            Statement stat = conn.createStatement();
            try{
                String consultaSQL = "DELETE FROM users WHERE idUsers=?";
                PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);

                pstmt.setInt(1, id);

                pstmt.executeUpdate();
                conn.commit();
            } catch (InputMismatchException ex) {
                System.out.println("Dades introduides incorrectament");
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
/**
 * Metode que quant crear un usuari nou automaticament ho farà a la seguent ID d'usuari del últim usuari insertat
 * @author Erik
 */
    @Override
    public void nuevaIdUsuario(){
        bd = new BDAccessor();
        try {
            conn = bd.obtenirConnexio();
            Statement stat = conn.createStatement();;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            Connection conn = bd.obtenirConnexio();
            Statement stat = conn.createStatement();
            try{
                String consultaSQL = "SELECT count(idUsers) FROM users";
                PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
                ResultSet resultat = pstmt.executeQuery();

                while (resultat.next()) {
                    idNextUsuario=resultat.getInt(1);
                }
                resultat.close();
                conn.commit();
            } catch (InputMismatchException ex) {
                System.out.println("Dades introduides incorrectament");
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException ex) {
            Logger.getLogger(AdministradorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministradorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
/**
 * Metode que actualitzar les dades del usuari a la BD
 * @author Erik
 */
    public void actualizarUsuarioSQL(int id, String campo, String NuevoValor, boolean isAdmin){
        BDAccessor bd = new BDAccessor();
        int NuevoValor2 = 0;
        if(isAdmin){
            NuevoValor2 = Integer.parseInt(NuevoValor);
        }
        try {
            
            Connection conn = bd.obtenirConnexio();
            Statement stat = conn.createStatement();
            try{
                String consultaSQL = "UPDATE users SET "+campo+"=? WHERE idUsers=?";
                PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
                
                if(isAdmin){
                    //NuevoValor2 = Integer.parseInt(NuevoValor);
                    pstmt.setInt(1, NuevoValor2);
                }else{
                    pstmt.setString(1, NuevoValor);
                }
                
                pstmt.setInt(2, id);

                pstmt.executeUpdate();
                conn.commit();
            } catch (InputMismatchException ex) {
                System.out.println("Dades introduides incorrectament");
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}






  
