/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectem03uf6;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe PersonatgeDAO.
 * Administra els metodes per fer les operacions CRUD del personatge
 * @author usuario
 */
public class PersonatgeDAO {
    private BDAccessor bd = new BDAccessor();
    
    /**
     * Metode crearPersonatge.
     * Aquest metode s'encarrega de crear el personatge.
     * 
     * @param nomPersonatge
     * @param ptAtac
     * @param ptDef
     * @param clan
     * @param element
     * @param nomUsuari
     */ 
    
   public void crearPersonatge(String nomPersonatge,int ptAtac,int ptDef,String clan,String element,String nomUsuari) throws SQLException{
       int id = 0;
       PreparedStatement pstmt = null;
       try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "Select max(idPersonatge)as NumMax from Personatges";
            pstmt = connexio.prepareStatement(cadenaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
           
            
            
            try (ResultSet resultat = pstmt.executeQuery()) {
                while (resultat.next()) {
            
                            id=resultat.getInt(1);
                           
                           
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           // Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println(id);
       id++;
       PreparedStatement pstmt1 = null;
        try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "Insert into personatges VALUES (?,?,?,?,?,?,?,?) ";
            pstmt1 = connexio.prepareStatement(cadenaSQL);
            
            pstmt1.setInt(1, id);
            pstmt1.setString(2, nomPersonatge);
            pstmt1.setInt(3, ptAtac);
            pstmt1.setInt(4, ptDef);
            pstmt1.setString(5, clan);
            pstmt1.setString(6, element);
            
            pstmt1.setString(7, null);
            pstmt1.setString(8, nomUsuari);
            
            pstmt1.executeUpdate();
            connexio.commit();
           
            
        } catch (IOException ex) {
            //Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           // Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
   
   }
   
   
   /**
     * Metode llistarPersonatge.
     * Aquest metode s'encarrega de llistar el personatge.
     * 
     * @param Nom
     */ 
    public ArrayList<Personatge> llistarPersonatge(String Nom) throws SQLException {
       PreparedStatement pstmt = null;
        ArrayList<Personatge> llista = new ArrayList<>();
        try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "SELECT * FROM PERSONATGES where Propietari = ?";
            pstmt = connexio.prepareStatement(cadenaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, Nom);
            
            
            try (ResultSet resultat = pstmt.executeQuery()) {
                while (resultat.next()) {
                    
                  
                    llista.add(new Personatge(
                            resultat.getInt(1),
                            resultat.getString(2),
                            resultat.getInt(3),
                            resultat.getInt(4),
                            resultat.getString(5),
                            resultat.getString(6),
                            resultat.getString(7)));
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           // Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return llista;
    }
    
    /**
     * Metode MaxNum.
     * Aquest metode s'utilitza per manegar la maximitat de personatges 
     *
     */ 
    public int MaxNum() throws SQLException{
        int MaxNum=0;
         PreparedStatement pstmt = null;
        ArrayList<Personatge> llista = new ArrayList<>();
        try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "SELECT max(idPersonatge)as MaxNum FROM PERSONATGES ";
            pstmt = connexio.prepareStatement(cadenaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            
            
            
            
            try (ResultSet resultat = pstmt.executeQuery()) {
                while (resultat.next()) {
                    
                   MaxNum=resultat.getInt(1);
                   
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           // Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return MaxNum;
    
    }
    /**
     * Metode esborrarPersonatge.
     * Aquest metode s'encarrega de esborrar el personatge.
     * 
     * @param codi
     * @param Nom
     */ 
    public ArrayList<Personatge> esborrarPersonatge(int codi, String Nom) throws SQLException {


        PreparedStatement pstmt = null;
        try (Connection connexio = bd.obtenirConnexio()) {

            pstmt = connexio.prepareStatement(
                    "DELETE FROM Personatges WHERE idPersonatge= ?");
            pstmt.setInt(1, codi);
            pstmt.executeUpdate();
            connexio.commit();
        } catch (Exception e) {
            throw new SQLException();
        }
        
        
        PreparedStatement pstmt1 = null;
        ArrayList<Personatge> llista = new ArrayList<>();
        try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "SELECT * FROM PERSONATGES where Propietari = ?";
            pstmt1 = connexio.prepareStatement(cadenaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pstmt1.setString(1, Nom);
            
            
            try (ResultSet resultat = pstmt1.executeQuery()) {
                while (resultat.next()) {
                    
                  
                    llista.add(new Personatge(
                            resultat.getInt(1),
                            resultat.getString(2),
                            resultat.getInt(3),
                            resultat.getInt(4),
                            resultat.getString(5),
                            resultat.getString(6),
                            resultat.getString(7)));
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           // Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return llista;
    }
    
    
    /**
     * Metode modificarPersonatge.
     * Aquest metode s'encarrega de modificar el personatge.
     * 
     * @param nomPersonatge
     * @param ptAtac
     * @param ptDef
     * @param clan
     * @param element
     * @param nomJugador
     * @param numPersonatge
     */ 
     public ArrayList<Personatge> modificarPersonatge(String nomPersonatge,int ptAtac,int ptDef,String clan,String element,int numPersonatge,String nomJugador) throws SQLException {

        PreparedStatement pstmt = null;
        try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "Update personatges set nom=?,"
                            + "potAtac=?,"
                            + "potDef=?,"
                            + "Raça=?,"
                            + "Medi=?"
                            + " WHERE idPersonatge= ?";
            pstmt = connexio.prepareStatement(cadenaSQL);
            //pstmt = connexio.prepareStatement();
            pstmt.setString(1, nomPersonatge);
            pstmt.setInt(2, ptAtac);
            pstmt.setInt(3, ptDef);
            pstmt.setString(4, clan);
            pstmt.setString(5, element);
            pstmt.setInt(6, numPersonatge);
            
            
            pstmt.executeUpdate();
            connexio.commit();
        } catch (Exception e) {
            throw new SQLException();
        }
        
        
        PreparedStatement pstmt1 = null;
        ArrayList<Personatge> llista = new ArrayList<>();
        try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "SELECT * FROM PERSONATGES where Propietari = ?";
            pstmt1 = connexio.prepareStatement(cadenaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pstmt1.setString(1, nomJugador);
            
            
            try (ResultSet resultat = pstmt1.executeQuery()) {
                while (resultat.next()) {
                    
                  
                    llista.add(new Personatge(
                            resultat.getInt(1),
                            resultat.getString(2),
                            resultat.getInt(3),
                            resultat.getInt(4),
                            resultat.getString(5),
                            resultat.getString(6),
                            resultat.getString(7)));
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           // Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return llista;
    }
}
