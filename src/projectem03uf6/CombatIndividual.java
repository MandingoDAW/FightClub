/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectem03uf6;

import java.util.ArrayList;

/**
 * Classe CombatIndividual que hereta de Combat
 * @author Eric
 */
public class CombatIndividual extends Combat {
    
    private String Tamany;
    //private ArrayList <Jugador> Lluitadors = new ArrayList<Jugador>();
    private Jugador P1;
    private Personatge CPU;
    private Personatge Participant;
    private Personatge Morts;
    private boolean Grup;
    private final String[] myArray = {"Aquatic","Terra","Vent","Foc"};
    private String Terreny;
    private int valorEntero;

    /**
     * Constructor amb tots els paràmetres.
     *
     * @param P1
     * @param Participant
     * @param CPU
     */
    
    
    public CombatIndividual(Jugador P1, Personatge Participant, Personatge CPU) {
        this.P1 = P1;
        this.CPU = CPU;
        this.Participant = Participant;
        int pR= myArray.length-1;
        this.valorEntero = (int) Math.floor(Math.random()*(pR-0+1)+0);
        this.Terreny=myArray[valorEntero]; 
        terreny();
    }

    
    
    
    
    public String getTerreny() {
        return Terreny;
    }

    public void setTerreny(String Terreny) {
        this.Terreny = Terreny;
    }
   

    public String getTamany() {
        return Tamany;
    }

    public void setTamany(String Tamany) {
        this.Tamany = Tamany;
    }

    public Personatge getMorts() {
        return Morts;
    }

    public void setMorts(Personatge Morts) {
        this.Morts = Morts;
    }

    public boolean isGrup() {
        return Grup;
    }

    public void setGrup(boolean Grup) {
        this.Grup = Grup;
    }

    @Override
    public String toString() {
        return "Combat{" + "P1=" + P1 + ", CPU=" + CPU + ", Participant=" + Participant + ", Terreny=" + Terreny + '}';
    }
    
    /**
     * Metode terreny.
     * Aquest metode atribueix els beneficis del terreny al personatge
     * 
     */
    
    public void terreny(){
        switch(this.Participant.getHabEspecial()){
            case "Susano Perfecto":
                    this.Participant.setPotDef(10);
                    break;
            case "Kamui":
                    //Cambiar el terreno a favor del personaje
                    this.Participant.setMedi(this.Terreny);
                    break;
            case "Mode Sennin":
                    //Adapatar al personaje al tipo de terreno
                    Participant.setPotAtac(10);
                    Participant.setPotDef(10);
                    break;
            case "Edo Tensei":
                    //Sume 5 pts Ataque y sume 5 pts defensa
                    Participant.setPotAtac(5);
                    Participant.setPotDef(5);
                    break;
            case "Amaterasu":
                    //Doblar el ataque x2
                    Participant.setPotAtac(15);
                    break;
            case "8 portes":
                    //multiplicar x 3 
                    Participant.setPotAtac(30);
                    break;
        
        }
        
        switch(this.CPU.getHabEspecial()){
            case "Susano Perfecto":
                    this.CPU.setPotDef(10);
                    break;
            case "Kamui":
                    //Cambiar el terreno a favor del personaje
                    this.CPU.setMedi(this.Terreny);
                    break;
            case "Mode Sennin":
                    //Adapatar al personaje al tipo de terreno
                    CPU.setPotAtac(10);
                    CPU.setPotDef(10);
                    break;
            case "Edo Tensei":
                    //Sume 5 pts Ataque y sume 5 pts defensa
                    CPU.setPotAtac(5);
                    CPU.setPotDef(5);
                    break;
            case "Amaterasu":
                    //Doblar el ataque x2
                    CPU.setPotAtac(15);
                    break;
            case "8 portes":
                    //multiplicar x 3 
                    CPU.setPotAtac(30);
                    break;
        
        }
        if(this.Participant.getMedi().equals(this.Terreny)){
            Participant.setPotAtac(10);
            Participant.setPotDef(10);
        }else if(this.CPU.getMedi().equals(this.Terreny)){
            CPU.setPotAtac(10);
            CPU.setPotDef(10);
        }

    }
    
    /**
     * Metode Resolucio.
     * Mostra el resultat del combat individual segons la logica de potAtac i potDef
     */
    public void Resolucio(){
        //int d1;
        //int d2;
        System.out.println("----------------------------------------------------------------");
        System.out.println("***Combat Individual***");
        if(Participant.getPotAtac()>CPU.getPotDef()){
            //1
            //d1=1;
            if(Participant.getPotDef()>CPU.getPotAtac()){
            //Guanya el participant 1
                //d2=1;
                System.out.println("Guanya el Jugador: "+Participant.getNom());
            }else if(Participant.getPotDef()<CPU.getPotAtac()){
            //Empat 0
                //d2=0;
                System.out.println("Empat entre:"+CPU.getNom()+" i "+Participant.getNom());
            }else{
            //Guanya particiapnt
                //d2=1;
                System.out.println("Guanya el Jugador: "+Participant.getNom());
            }
        }
        else if(Participant.getPotAtac()<CPU.getPotDef()){
            //empat
            //d1=0;
            if(Participant.getPotDef()<CPU.getPotAtac()){
                //Guanya CPU -1
                
                System.out.println("Guanya el personatge: "+CPU.getNom());
            }else if(Participant.getPotDef()>CPU.getPotAtac()){
                //empat 0
                
                System.out.println("Empat entre:"+CPU.getNom()+" i "+Participant.getNom());
            }else{
                //Guanya CPU -1
                System.out.println("Guanya el Jugador: "+CPU.getNom());
            }
           
        }
        
        /*
        Participant.getPotAtac(); 
        CPU.getPotDef();
        Participant.getPotDef();
        CPU.getPotAtac();
        */
        
        /////
        /*switch(resultado1){
            case 1: 
                if(resultado2==1){
                    System.out.println("Mueren los 2");
                }else{
                    System.out.println("Gana "+c1.getNom());
                }
                break;
            case -1: 
                if(resultado2==1 || resultado2==0){
                    System.out.println("Gana "+c2.getNom());
                }else{
                    System.out.println("Mueren los 2");
                }
                break;
            case 0:
                if(resultado2==1){
                    System.out.println("Gana criatura "+c2.getNom());
                }else if(resultado2==-1){
                    System.out.println("Gana criatura "+c1.getNom());
                }else{
                    System.out.println("Empate");
                }
                break;
        }*/
        System.out.println("----------------------------------------------------------------");
    }
    
    
}
