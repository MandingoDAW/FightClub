/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectem03uf6;

import Dao.AdministradorDAO;
import Dao.DAOFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe Principal.
 * Classe on es prova el joc de proves del joc FIGH CLUB
 * @author Erik
 */
public class Principal {
    private static Connection conn;
    private static BDAccessor bd= null;
    private String nom ;//nombre del scanner
    private String pass;//password del scanner
    private static String nomU ;//nombre que recuperamos de la BD
    private static String passU;//password que recuperamos de la BD
    private static String lema ;//lema que recuperamos de la BD
    private static int log = 0 ;
    private static int admin ;
    private static ArrayList <Personatge> totalPersonatges  = new ArrayList<Personatge>();
    private static ArrayList <Equip> totalEquips  = new ArrayList<Equip>();
    
    
    
    /**
     * Main.
     * Aqui preguntem el login del joc, si l'usuari es administrador o jugador.
     */ 
    
    public static void main(String[] args) throws SQLException {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Usuari:");
        String nom = entrada.next();

        System.out.println("Password:");
        String pass = entrada.next();

         
         
         bd = new BDAccessor();
        try {
           // while(log==0){
                conn = bd.obtenirConnexio();

                String consultaSQL = "SELECT count(nom)as log,nom, password , lema,admin FROM Users WHERE nom = ? and password = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS)) {
                    pstmt.setString(1, nom);
                    pstmt.setString(2, pass);

                    ResultSet resultat = pstmt.executeQuery();

                    while (resultat.next()) {
                        if(resultat.getInt(1)==1){
                            log=resultat.getInt(1);
                            nomU=resultat.getString(2);
                            passU=resultat.getString(3);
                            lema=resultat.getString(4);
                            admin=resultat.getInt(5);
                            System.out.println(resultat.getInt(5));
                            System.out.println("Usari logejat ");
                            if(admin==0){
                                aplicacio();
                            }else{
                            //otra funcion
                                admin();
                            }
                        }else{
                            System.out.println("Error al log");
                        }
                    }
                    
                }
           // }
        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
            
         
         

    }
            //aplicacio();    //Métode que crida el joc de proves per a executar l'aplicació
            
   //------------------------------------MODO ADMIN----------------------------//
    
    /**
     * Metode admin.
     * Aquest metode ens mostrarà el menú per administrar els usuaris.
     */ 
    public static void admin() throws SQLException{
        AdministradorDAO dao = DAOFactory.crearAdministradorDAO();
        System.out.println("Informació del Admin");
        Jugador persona1 = new Jugador(nomU,passU,lema);
        System.out.println(persona1);
        int opcio;
        Scanner entrada = new Scanner(System.in);

        do {

            System.out.println("\nTrii quina operació vol realitzar:");
            System.out.println("1.Crear Usuari");
            System.out.println("2. Editar usuari");
            System.out.println("3. Eliminar usuario");          
            System.out.println("4. Sortir");

                opcio = entrada.nextInt();
                switch (opcio) {
                    case 1:
                        //
                       // crearPersonatge(persona1);
                        //llenamos info
                        //hacemos insert del objeto creado en BD
                        //añadimos al arraylist
                        dao.registrar();
                        break;
                    case 2:
                        //Falta Implementar
                       // modificarPersonatge(persona1);
                        modificarUsuario(dao);
                        break;
                    case 3:
                        //Falta Implementar
                        //eliminarPersonatge();
                        eliminarUsuario(dao);
                        break;
                    case 4:
                        System.out.println("Sortint de l'aplicació...");
                        //conn.close();
                        System.out.println("Final aplicació");
                        break;
                    default:
                        System.out.println("Opció incorrecta.\n");
                    break;
                }
        } while (opcio != 4);
        
        
    }
    
    /**
     * Metode modificarUsuario.
     * Aquest metode utilitza el Factory de AdministrarDAO per poder editar l'usuari.
     * 
     * @param dao
     */ 
    public static void modificarUsuario(AdministradorDAO dao) throws SQLException{
        System.out.println("Modificar usuario: ");
        try{
            String consultaSQL = "SELECT * FROM users";
            PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet resultat = pstmt.executeQuery();

            while (resultat.next()) {
                int id = resultat.getInt(1);
                String usuarioActual = resultat.getString(2);
                String administrador = resultat.getString(4);
                if(administrador.equals("1")){
                    System.out.println("ID: "+id+" - usuario: "+usuarioActual+" - Administrador: Sí");
                }else{
                    System.out.println("ID: "+id+" - usuario: "+usuarioActual+" - Administrador: No");
                }
            }
            resultat.close();
            conn.commit();
            int opcion;
            Scanner entrada = new Scanner(System.in);
            opcion = entrada.nextInt();
            
            dao.modificarUsuario(opcion);
        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        }
    }
    
    /**
     * Metode eliminarUsuario.
     * Aquest metode utilitza el Factory de AdministrarDAO per poder esborrar l'usuari.
     * 
     * @param dao
     */ 
    public static void eliminarUsuario(AdministradorDAO dao) throws SQLException{
        System.out.println("Eliminar usuario: ");
        try{
            String consultaSQL = "SELECT * FROM users";
            PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet resultat = pstmt.executeQuery();

            while (resultat.next()) {
                int id = resultat.getInt(1);
                String usuarioActual = resultat.getString(2);
                String administrador = resultat.getString(4);
                if(administrador.equals("1")){
                    System.out.println("id-> "+id+" - usuari-> "+usuarioActual+" - admin: Sí");
                }else{
                    System.out.println("id-> "+id+" - usuari-> "+usuarioActual+" - admin: No");
                }
            }
            resultat.close();
            conn.commit();
            int opcion;
            Scanner entrada = new Scanner(System.in);
            opcion = entrada.nextInt();
            
            dao.eliminarUsuario(opcion);
        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        }
    }
    
    
    //-----------------------------MODO USUARIO--------------------------------/
    
    
    /**
     * Metode aplicacio.
     * Aquest metode ens mostra el menú per fer les operacions CRUD de l'usuari Jugador i poder jugar al joc per lluitar.
     * 
     */ 
    
    public static void aplicacio() throws SQLException{
  
        System.out.println("Informació del Jugador");
        Jugador persona1 = new Jugador(nomU,passU,lema);
        System.out.println(persona1);
        //Aquest personatges es tindran que afegir dinamicament guardar mitjançant JPA2 i recuperar-los al iniciar la app
        PersonatgeDAO p =new PersonatgeDAO();
         EquipDAO e =new EquipDAO();
        totalPersonatges=p.llistarPersonatge(persona1.getNomUsuari());
        totalEquips=e.llistarEquip(persona1.getNomUsuari(), totalPersonatges);
        /*Personatge personatge1 = new Personatge("Madara",10,10,"Uchiha","Foc",persona1.getNomUsuari());
        Personatge personatge2 = new Personatge("Sakura",4,8,"Uchiha","Viento",persona1.getNomUsuari());
        Personatge personatge3 = new Personatge("Obito",5,9,"Uchiha","Foc",persona1.getNomUsuari());
        Personatge personatge4 = new Personatge("Jiraya",7,7,"Sabio","Viento",persona1.getNomUsuari());
        Personatge personatge5 = new Personatge("Naruto",9,9,"Uzumaki","Viento",persona1.getNomUsuari());
        Personatge personatge6 = new Personatge("Shikamaru",6,2,"Shika","Tierra",persona1.getNomUsuari());
       //Menu de la aplicacio
        totalPersonatges.add(personatge1);
        totalPersonatges.add(personatge2);
        totalPersonatges.add(personatge3);
        totalPersonatges.add(personatge4);
        totalPersonatges.add(personatge5);
        totalPersonatges.add(personatge6);*/
        int opcio;
        Scanner entrada = new Scanner(System.in);

        do {

            System.out.println("\nTrii quina operació vol realitzar:");
            System.out.println("1.Crear Personatge");
            System.out.println("2. Editar Personatge");
            System.out.println("3. Eliminar Personatge");
            System.out.println("4. Crear Equip");
            System.out.println("5. Lluita Individual");
            System.out.println("6. Lluita Equip");
            System.out.println("7. Sortir");

                opcio = entrada.nextInt();
                switch (opcio) {
                    case 1:
                        //
                        crearPersonatge(persona1);
                        //llenamos info
                        //hacemos insert del objeto creado en BD
                        //añadimos al arraylist
                        
                        break;
                    case 2:
                        //Falta Implementar
                        modificarPersonatge(persona1);
                        
                        break;
                    case 3:
                        //Falta Implementar
                        eliminarPersonatge(persona1);
                        break;
                    case 4:
                        //Falta Implementar
                        crearEquip(persona1);
                        break;
                    case 5:
                        //en aquesta opcio es tindra que realitzar una eleccio entre dos personatges P1 vs CPU
                        combatIndividual(persona1);
                        break;
                    case 6:
                        //en aquest cas li tindrem que pasar les dos arraylist de equips
                        combatEquip(persona1);
                        break;
                    case 7:
                        System.out.println("Sortint de l'aplicació...");
                        //conn.close();
                        System.out.println("Final aplicació");
                        break;
                    default:
                        System.out.println("Opció incorrecta.\n");
                    break;
                }
        } while (opcio != 7);

    }
    /**
     * Metode crearPersonatge.
     * Metode per crear el personatge amb els seus atributs corresponents.
     * 
     * @param persona1
     */ 
    public static void crearPersonatge(Jugador persona1) throws SQLException{
        PersonatgeDAO p =new PersonatgeDAO();
        Scanner entrada = new Scanner(System.in);

        System.out.println("Nom Personatge:");
        String nomPersonatge = entrada.next();

        System.out.println("Pot Atac:");
        int ptAtac = entrada.nextInt();
        
        System.out.println("Pot Def:");
        int ptDef = entrada.nextInt();
        
        System.out.println("Clan:");
        String clan = entrada.next();
        
        System.out.println("Element:");
        String element = entrada.next();
     
        Personatge personatge = new Personatge(p.MaxNum()+1,nomPersonatge,ptAtac,ptDef,clan,element,persona1.getNomUsuari());
        totalPersonatges.add(personatge);
        p.crearPersonatge(nomPersonatge,ptAtac,ptDef,clan,element,persona1.getNomUsuari());
        System.out.println(totalPersonatges.get(totalPersonatges.size()-1));
    }
    
    /**
     * Metode modificarPersonatge.
     * Aquest metode editar personatges ja creats a la BD.
     * 
     * @param persona1
     */ 
    public static void modificarPersonatge(Jugador persona1) throws SQLException{
        PersonatgeDAO p =new PersonatgeDAO();
        Scanner entrada = new Scanner(System.in);

        for(int z=0;z<totalPersonatges.size();z++){
            
            System.out.println(totalPersonatges.get(z).getIdPersonatge()+"-"+totalPersonatges.get(z));
        }
        System.out.println("Num Personatge que volem modificar:");
        int numPersonatge = entrada.nextInt();//campo por el que filtrar
       
        System.out.println("Nom Personatge:");
        String nomPersonatge = entrada.next();

        System.out.println("Pot Atac:");
        int ptAtac = entrada.nextInt();
        
        System.out.println("Pot Def:");
        int ptDef = entrada.nextInt();
        
        System.out.println("Clan:");
        String clan = entrada.next();
        
        System.out.println("Element:");
        String element = entrada.next();
        //update ... values ... where idPersonaje = numPersonatge
        //vaciar el arraylist i hacer un select y volver-lo a llenar
        totalPersonatges=p.modificarPersonatge(nomPersonatge,ptAtac,ptDef,clan,element,numPersonatge,persona1.getNomUsuari());
        //System.out.println("Falta Implementar");
    }
    
    /**
     * Metode eliminarPersonatge.
     * Aquest metode eliminar personatges ja creats a la BD.
     * 
     * @param persona1
     */ 
    public static void eliminarPersonatge(Jugador persona1) throws SQLException{
        PersonatgeDAO p =new PersonatgeDAO();
        Scanner entrada = new Scanner(System.in);
         for(int z=0;z<totalPersonatges.size();z++){
            
            System.out.println(totalPersonatges.get(z).getIdPersonatge()+"-"+totalPersonatges.get(z));
        }
        System.out.println("Num Personatge que volem modificar:");
        int numPersonatge = entrada.nextInt();//delete from persontages where idperdonatge=numPersonatge
        //vaciar el arraylist i hacer un select y volver-lo a llenar
        totalPersonatges=p.esborrarPersonatge(numPersonatge, persona1.getNomUsuari());

        System.out.println("Falta Implementar");
    }
    
    
    /**
     * Metode crearEquip.
     * Aquest metode crea l'equip quant ja han sigut seleccionats previament els personatges.
     * 
     * @param persona1
     */ 
    
     public static void crearEquip(Jugador persona1) throws SQLException{
         EquipDAO e =new EquipDAO();
         ArrayList <Personatge> jugEquip  = new ArrayList<Personatge>();
        int numPersonatge1=0;
        int numPersonatge2=0;
        int numPersonatge3=0;
        //System.out.println(e.MaxNum());
        Scanner entrada = new Scanner(System.in);
        
        for(int z=0;z<totalPersonatges.size();z++){
            
            System.out.println(totalPersonatges.get(z).getIdPersonatge()+"-"+totalPersonatges.get(z));
        }
        for(int i=0;i<3;i++){
            System.out.println("Numero:");
            int numPersonatge = entrada.nextInt();
            if(i==0){
                for(int j=0;j<totalPersonatges.size();j++){
                    if(totalPersonatges.get(j).getIdPersonatge()==numPersonatge){             
                            jugEquip.add(totalPersonatges.get(j));
                    }
                }
                numPersonatge1=numPersonatge;
                
            }else if(i==1){
                for(int j=0;j<totalPersonatges.size();j++){
                    if(totalPersonatges.get(j).getIdPersonatge()==numPersonatge){             
                            jugEquip.add(totalPersonatges.get(j));
                    }
                }
                numPersonatge2=numPersonatge;
            }else if(i==2){
                for(int j=0;j<totalPersonatges.size();j++){
                    if(totalPersonatges.get(j).getIdPersonatge()==numPersonatge){             
                            jugEquip.add(totalPersonatges.get(j));
                    }
                }
                 numPersonatge3=numPersonatge;
            }
            
        }
        
        Equip equip1 = new Equip(e.MaxNum()+1,jugEquip);
        totalEquips.add(equip1);
        e.crearEquip(numPersonatge1, numPersonatge2, numPersonatge3, persona1.getNomUsuari());
        //System.out.println("Falta Implementar");
        
    }
    
    
    
    
    /**
     * Metode combatIndividual.
     * Aquest metode implementa la lluita individual de personatges.
     * 
     * @param persona1
     */ 
    public static void combatIndividual(Jugador persona1){
        Scanner entrada = new Scanner(System.in);
        for(int z=0;z<totalPersonatges.size();z++){
            
            System.out.println(z+"-"+totalPersonatges.get(z));
        }
        System.out.println("Personatge P1:");
            int numPersonatge = entrada.nextInt();
        System.out.println("Personatge CPU:");
            int numCpu = entrada.nextInt();
        
        System.out.println("----------------------------------------------------");
        System.out.println("Informació del Personatges");
        System.out.println(totalPersonatges.get(numPersonatge));
        System.out.println("VS");
        System.out.println(totalPersonatges.get(numCpu));
        CombatIndividual combat1 = new CombatIndividual(persona1,totalPersonatges.get(numPersonatge),totalPersonatges.get(numCpu));
        System.out.println(combat1);
        combat1.Resolucio();
    
    }
    /**
     * Metode combatEquip.
     * Aquest metode implementa la lluita entre 2 equips.
     * 
     * @param persona1
     */ 
    public static void combatEquip(Jugador persona1){
       Scanner entrada = new Scanner(System.in);
        for(int z=0;z<totalEquips.size();z++){
            
            System.out.println(z+"-"+totalEquips.get(z));
        }
        System.out.println("Equip P1:");
            int numEquipPersonatge = entrada.nextInt();
        System.out.println("Equip CPU:");
            int numEquipCpu = entrada.nextInt();
        
        /*
        ArrayList <Personatge> pp  = new ArrayList<Personatge>();
       ArrayList <Personatge> pr  = new ArrayList<Personatge>();
       pp.add(personatge1);
       pp.add(personatge2);
       pp.add(personatge3);
       pr.add(personatge4);
       pr.add(personatge5);
       pr.add(personatge6);*/
       System.out.println("----------------------------------------------------");
       System.out.println("Informació del Personatges");
        System.out.println("Equip 1");
       System.out.println(totalEquips.get(numEquipPersonatge).getPersonatges());
     
       System.out.println("Equip 2");
       System.out.println(totalEquips.get(numEquipCpu).getPersonatges());
       
       
       CombatEquip combat2 = new CombatEquip(persona1,totalEquips.get(numEquipPersonatge).getPersonatges(),totalEquips.get(numEquipCpu).getPersonatges());
    
    }
}
