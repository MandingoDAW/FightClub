/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectem03uf6;

import java.util.ArrayList;

/**
 * Classe Jugador que hereta de Usuari
 * @author Erik
 */
public class Jugador extends Usuari {
 
    private String NomUsuari;
    private String PasswordUsuari;
    //private Personatge Personatges;
    ArrayList <Personatge> nombreArrayList = new ArrayList<Personatge>();
    private String Lema;

    /**
     * Constructor amb tots els paràmetres.
     *
     * @param NomUsuari
     * @param PasswordUsuari
     * @param lema
     */
    
    public Jugador(String NomUsuari, String PasswordUsuari, String Lema) {
        this.NomUsuari = NomUsuari;
        this.PasswordUsuari = PasswordUsuari;
        this.Lema = Lema;
    }

    
    
    public String getNomUsuari() {
        return NomUsuari;
    }

    public void setNomUsuari(String NomUsuari) {
        this.NomUsuari = NomUsuari;
    }

    public String getPasswordUsuari() {
        return PasswordUsuari;
    }

    public void setPasswordUsuari(String PasswordUsuari) {
        this.PasswordUsuari = PasswordUsuari;
    }

    public ArrayList<Personatge> getNombreArrayList() {
        return nombreArrayList;
    }

    public void setNombreArrayList(ArrayList<Personatge> nombreArrayList) {
        this.nombreArrayList = nombreArrayList;
    }

    public String getLema() {
        return Lema;
    }

    public void setLema(String Lema) {
        this.Lema = Lema;
    }

    @Override
    public String toString() {
        return "Jugador{" + "NomUsuari=" + NomUsuari + ", PasswordUsuari=" + PasswordUsuari + ", nombreArrayList=" + nombreArrayList + ", Lema=" + Lema + '}';
    }
    
    
    
    
}
