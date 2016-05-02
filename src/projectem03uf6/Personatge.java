/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectem03uf6;

/**
 *
 * @author Eric
 */
public class Personatge {
    
    private String Nom;
    private int potAtac;
    private int potDef;
    private String Raça;
    private String Medi;
    private String habEspecial;
    private static String[] habilitats = {"Susano Perfecto","Kamui","Mode Sennin","Edo Tensei","Amaterasu","8 portes"};
    //private  Imatge;
     private int valorEntero;

    public Personatge(String Nom, int potAtac, int potDef, String Raça, String Medi) {
        this.Nom = Nom;
        this.potAtac = potAtac;
        this.potDef = potDef;
        this.Raça = Raça;
        this.Medi = Medi;
        //this.habEspecial = habEspecial;
        int pR= habilitats.length-1;
        this.valorEntero = (int) Math.floor(Math.random()*(pR-0+1)+0);
        this.habEspecial=habilitats[valorEntero]; 
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getPotAtac() {
        return potAtac;
    }

    public void setPotAtac(int potAtac) {
        this.potAtac += potAtac;
    }

    public int getPotDef() {
        return potDef;
    }

    public void setPotDef(int potDef) {
        this.potDef += potDef;
    }

    public String getRaça() {
        return Raça;
    }

    public void setRaça(String Raça) {
        this.Raça = Raça;
    }

    public String getMedi() {
        return Medi;
    }

    public void setMedi(String Medi) {
        this.Medi = Medi;
    }

    public String getHabEspecial() {
        return habEspecial;
    }

    public void setHabEspecial(String habEspecial) {
        this.habEspecial = habEspecial;
    }

    @Override
    public String toString() {
        return "Personatge{" + "Nom=" + Nom + ", potAtac=" + potAtac + ", potDef=" + potDef + ", Ra\u00e7a=" + Raça + ", Medi=" + Medi + ", habEspecial=" + habEspecial + '}';
    }
    
    
}
