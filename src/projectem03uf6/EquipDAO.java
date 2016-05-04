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
 * Classe EquipDAO 
 * adminitra la persistencia de dades del joc de lluita en equip
 * @author usuario
 */
public class EquipDAO {
    private BDAccessor bd = new BDAccessor();
        
    /**
     * Metode MaxNum, amb el cual es determina el nombre de personatges per equip
     *
     */
        public int MaxNum() throws SQLException{
        int MaxNum=0;
         PreparedStatement pstmt = null;
        ArrayList<Personatge> llista = new ArrayList<>();
        try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "SELECT max(idEquip)as MaxNum FROM Equip ";
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
     * Metode crearEquip.
     * Aquest metode s'encarrega de crear l'equip una vegada están seleccionats el personatges
     * 
     * @param pt1
     * @param pt2
     * @param pt3
     * @param nomUsuari
     */  
      public void crearEquip(int pt1,int pt2,int pt3,String nomUsuari) throws SQLException{
       int id = 0;
       PreparedStatement pstmt = null;
       try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "Select max(idEquip)as NumMax from Equip";
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
            String cadenaSQL = "Insert into Equip VALUES (?,?,?,?,?) ";
            pstmt1 = connexio.prepareStatement(cadenaSQL);
            
            pstmt1.setInt(1, id);
            pstmt1.setInt(2, pt1);
            pstmt1.setInt(3, pt2);
            pstmt1.setInt(4, pt3);
            pstmt1.setString(5, nomUsuari);

            
            pstmt1.executeUpdate();
            connexio.commit();
           
            
        } catch (IOException ex) {
            //Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           // Logger.getLogger(PersonatgeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
   
   }
      
      /**
     * Metode llistarEquip.
     * Aquest metode s'encarrega de llistar els equips que hi han disponibles
     * 
     * @param Nom
     * @param Personatges
     */ 
      public ArrayList<Equip> llistarEquip(String Nom,ArrayList<Personatge> Personatges) throws SQLException {
       PreparedStatement pstmt = null;
        ArrayList<Equip> llista = new ArrayList<>();
        //ArrayList<Personatge> afegir = new ArrayList<>();
        try (Connection connexio = bd.obtenirConnexio()) {
            String cadenaSQL = "SELECT * FROM Equip where Propietari = ?";
            pstmt = connexio.prepareStatement(cadenaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, Nom);
            
            
            try (ResultSet resultat = pstmt.executeQuery()) {
                while (resultat.next()) {
                            ArrayList<Personatge> afegir = new ArrayList<>();
                            int Equipid=resultat.getInt(1);
                            int Idpersonatge1=resultat.getInt(2);
                            int Idpersonatge2=resultat.getInt(3);
                            int Idpersonatge3=resultat.getInt(4);
                            //System.out.println(Personatges.size());
                            //System.out.println(Idpersonatge1+" "+Idpersonatge2+" "+Idpersonatge3);
                            for(int i=0;i<Personatges.size();i++){
                                //System.out.println(Personatges.get(i));
                                //afegir.clear();
                               if(Personatges.get(i).getIdPersonatge()==Idpersonatge1){
                                   //Personatge p1=;
                                   
                                   afegir.add(Personatges.get(i));
                               }else if(Personatges.get(i).getIdPersonatge()==Idpersonatge2){
                                   
                                   afegir.add(Personatges.get(i));
                               }else if(Personatges.get(i).getIdPersonatge()==Idpersonatge3){
                                   
                                   afegir.add(Personatges.get(i));
                               }
                               
                               
                               
                            }
                            //System.out.println(afegir.get(0));
                            //System.out.println(afegir.get(1));
                            //System.out.println(afegir.get(2));
                            Equip e=new Equip(Equipid,afegir);
                            llista.add(e);
                            
                            //ArrayList<Personatge> afegir = new ArrayList<>();
                            //System.out.println(e);
                            
                            
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
