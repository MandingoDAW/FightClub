/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectem03uf6;

import java.util.ArrayList;

/**
 * Classe Equip
 * @author Eric
 */
public class Equip {
    private static int contador=0;
    private int idEquip;
    private  ArrayList <Personatge> Personatges;
    private int Potencial;
    private String caras;

    
    /**
     * Constructor amb tots els paràmetres.
     *
     * @param idEquip
     * @param Personatges
     */
    
    public Equip(int idEquip,ArrayList<Personatge> Personatges) {
        this.Personatges = Personatges;
        this.contador++;
        this.idEquip=idEquip;
        setPotencial();
    }

    
    
    public ArrayList<Personatge> getPersonatges() {
        return Personatges;
    }

    public void setPersonatges(ArrayList<Personatge> Personatges) {
        this.Personatges = Personatges;
    }
    

    public int getPotencial() {
        return Potencial;
    }

    public void setPotencial() {
        
        for(int i=0;i<this.Personatges.size();i++){
            this.Potencial+=this.Personatges.get(i).getPotAtac();
            this.Potencial+=this.Personatges.get(i).getPotDef();
        }
        //this.Potencial = Potencial;
        //hacer un for para recorrer el array list de cada equipo y en cada iteracion del for calcular la suma de los puntos de ataque y defensa
        setCaras(this.Potencial);
    }

    public String getCaras() {
        return caras;
    }

    public void setCaras(int Potencial) {
        if(Potencial<=10){
            this.caras = "☺";
        }else if(Potencial>10 && Potencial<=40){
            this.caras = "☺☺";
        }else if(Potencial>40){
            this.caras = "☺☺☺";
        }
        
    }

    @Override
    public String toString() {
        return "Equip{" + "idEquip=" + idEquip + ", Personatges=" + Personatges + ", Potencial=" + Potencial +", Caras=" + caras + '}';
    }
    
   
    
    
    
    
}
