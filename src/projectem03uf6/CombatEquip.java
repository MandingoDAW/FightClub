/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectem03uf6;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class CombatEquip extends Combat {
    private Jugador P1;
    private ArrayList <Personatge> Participant ;
    private ArrayList <Personatge> CPU ;
    private final String[] myArray = {"Aquatic","Terra","Vent","Foc"};
    int valorEntero;
    private String Terreny; 
    private int potencial;
    private int RecompteG;
    private int RecompteP;

    public CombatEquip(Jugador P1, ArrayList<Personatge> Participant, ArrayList<Personatge> CPU) {
        this.P1 = P1;
        this.Participant = Participant;
        this.CPU = CPU;
        int pR= myArray.length-1;
        this.valorEntero = (int) Math.floor(Math.random()*(pR-0+1)+0);
        this.Terreny=myArray[valorEntero]; 
        terreny();
        Resolucio();
    }

    
    
    public Jugador getP1() {
        return P1;
    }

    public void setP1(Jugador P1) {
        this.P1 = P1;
    }

    public ArrayList<Personatge> getParticipant() {
        return Participant;
    }

    public void setParticipant(ArrayList<Personatge> Participant) {
        this.Participant = Participant;
    }

    public ArrayList<Personatge> getCPU() {
        return CPU;
    }

    public void setCPU(ArrayList<Personatge> CPU) {
        this.CPU = CPU;
    }

    public String getTerreny() {
        return Terreny;
    }

    public void setTerreny(String Terreny) {
        this.Terreny = Terreny;
    }
    
   
    
    @Override
    public void terreny() {
        for(int i=0;i<Participant.size();i++){
            if(Participant.get(i).getMedi().equals(this.Terreny)){
               Participant.get(i).setPotAtac(10);
               Participant.get(i).setPotDef(10);
            }
        
        }
    }

    @Override
    public void Resolucio() {
        System.out.println("***Combat En Equip***");
        for(int i=0;i<Participant.size();i++){
            
            if(Participant.get(i).getPotAtac()>CPU.get(i).getPotDef()){
            //1
            //d1=1;
            if(Participant.get(i).getPotDef()>CPU.get(i).getPotAtac()){
            //Guanya el participant 1
                //d2=1;
                RecompteG++;
                System.out.println("Guanya el Jugador: "+Participant.get(i).getNom());
            }else if(Participant.get(i).getPotDef()<CPU.get(i).getPotAtac()){
            //Empat 0
                //d2=0;
                System.out.println("Empat entre:"+CPU.get(i).getNom()+" i "+Participant.get(i).getNom());
            }else{
            //Guanya particiapnt
                //d2=1;
                RecompteG++;
                System.out.println("Guanya el Jugador: "+Participant.get(i).getNom());
            }
        }
        else if(Participant.get(i).getPotAtac()<CPU.get(i).getPotDef()){
            //empat
            //d1=0;
            if(Participant.get(i).getPotDef()<CPU.get(i).getPotAtac()){
                //Guanya CPU -1
                RecompteP++;
                System.out.println("Guanya el personatge: "+CPU.get(i).getNom());
            }else if(Participant.get(i).getPotDef()>CPU.get(i).getPotAtac()){
                //empat 0
                System.out.println("Empat entre:"+CPU.get(i).getNom()+" i "+Participant.get(i).getNom());
            }else{
                //Guanya CPU -1
                RecompteP++;
                System.out.println("Guanya el Jugador: "+CPU.get(i).getNom());
            }
        }
            
            
        
        }
        System.out.println("----------------------------------------------------------------");
        if(RecompteG>=2){
           System.out.println("Guanya el P1");  
        }else{
            System.out.println("Guanya el CPU");    
        }
    }
    
    
}
