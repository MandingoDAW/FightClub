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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author usuario
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
    private static ArrayList <Personatge> totalPersonatges  = new ArrayList<Personatge>();
    private static ArrayList <Equip> totalEquips  = new ArrayList<Equip>();
    
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

                String consultaSQL = "SELECT count(nom)as log,nom, password , lema FROM Users WHERE nom = ? and password = ?";
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
                            System.out.println(resultat.getInt(1));
                            System.out.println("Usari logejat ");
                            aplicacio();
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
            
            
       
        
        //combat2.Resolucio();
        
    public static void aplicacio(){
  
        System.out.println("Informació del Jugador");
        Jugador persona1 = new Jugador(nomU,passU,lema);
        System.out.println(persona1);
        //Aquest personatges es tindran que afegir dinamicament guardar mitjançant JPA2 i recuperar-los al iniciar la app
        
        Personatge personatge1 = new Personatge("Madara",10,10,"Uchiha","Foc",persona1);
        Personatge personatge2 = new Personatge("Sakura",4,8,"Uchiha","Viento",persona1);
        Personatge personatge3 = new Personatge("Obito",5,9,"Uchiha","Foc",persona1);
        Personatge personatge4 = new Personatge("Jiraya",7,7,"Sabio","Viento",persona1);
        Personatge personatge5 = new Personatge("Naruto",9,9,"Uzumaki","Viento",persona1);
        Personatge personatge6 = new Personatge("Shikamaru",6,2,"Shika","Tierra",persona1);
       //Menu de la aplicacio
        totalPersonatges.add(personatge1);
        totalPersonatges.add(personatge2);
        totalPersonatges.add(personatge3);
        totalPersonatges.add(personatge4);
        totalPersonatges.add(personatge5);
        totalPersonatges.add(personatge6);
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
                        modificarPersonatge();
                        
                        break;
                    case 3:
                        //Falta Implementar
                        eliminarPersonatge();
                        break;
                    case 4:
                        //Falta Implementar
                        crearEquip();
                        break;
                    case 5:
                        //en aquesta opcio es tindra que realitzar una eleccio entre dos personatges P1 vs CPU
                        combatIndividual(persona1);
                        break;
                    case 6:
                        //en aquest cas li tindrem que pasar les dos arraylist de equips
                        combatEquip(persona1,personatge1,personatge2,personatge3,personatge4,personatge5,personatge6);
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
    
    public static void crearPersonatge(Jugador persona1){
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
     
        Personatge personatge = new Personatge(nomPersonatge,ptAtac,ptDef,clan,element,persona1);
        totalPersonatges.add(personatge);
        System.out.println(totalPersonatges.get(totalPersonatges.size()-1));
    }
    
    public static void modificarPersonatge(){
        Scanner entrada = new Scanner(System.in);

        for(int z=0;z<totalPersonatges.size();z++){
            
            System.out.println(totalPersonatges.get(z).getIdPersonatge()+"-"+totalPersonatges.get(z));
        }
        System.out.println("NUm Personatge que volem modificar:");
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
        System.out.println("Falta Implementar");
    }
    
    public static void eliminarPersonatge(){
        Scanner entrada = new Scanner(System.in);
         for(int z=0;z<totalPersonatges.size();z++){
            
            System.out.println(totalPersonatges.get(z).getIdPersonatge()+"-"+totalPersonatges.get(z));
        }
        System.out.println("Num Personatge que volem modificar:");
        int numPersonatge = entrada.nextInt();//delete from persontages where idperdonatge=numPersonatge
        //vaciar el arraylist i hacer un select y volver-lo a llenar

        System.out.println("Falta Implementar");
    }
    
     public static void crearEquip(){
         ArrayList <Personatge> jugEquip  = new ArrayList<Personatge>();
        Scanner entrada = new Scanner(System.in);
        
        for(int z=0;z<totalPersonatges.size();z++){
            
            System.out.println(z+"-"+totalPersonatges.get(z));
        }
        for(int i=0;i<3;i++){
            System.out.println("Numero:");
            int numPersonatge = entrada.nextInt();
            jugEquip.add(totalPersonatges.get(numPersonatge));
        }
        Equip equip1 = new Equip(jugEquip);
        totalEquips.add(equip1);
        //System.out.println("Falta Implementar");
        
    }
    
    
    
    
    
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
    
    public static void combatEquip(Jugador persona1,Personatge personatge1,Personatge personatge2,Personatge personatge3,Personatge personatge4,Personatge personatge5,Personatge personatge6){
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
