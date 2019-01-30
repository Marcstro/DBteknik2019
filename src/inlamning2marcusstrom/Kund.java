/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcu
 * aer awesome
 */

package inlamning2marcusstrom;

import java.util.LinkedList;


public class Kund {

    public int getId() {
        return id;
    }

    public String getNamn() {
        return namn;
    }

    public String getAdress() {
        return adress;
    }

    public String getOrt() {
        return ort;
    }

    public String getLosen() {
        return losen;
    }

    public String getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setLosen(String losen) {
        this.losen = losen;
    }

    public void setCreated(String created) {
        this.created = created;
    }
    public void addBestallning(Bestallning b){
        bestallningar.add(b);
    }

    int id;
    String namn;
    String adress;
    String ort;
    String losen;
    String created;
    LinkedList<Bestallning> bestallningar=new LinkedList<>();
    
    Kund(int id, String namn, String adress, String ort, String losen, String created){
     this.id=id;  
     this.namn=namn;
     this.adress=adress;
     this.ort=ort;
     this.losen=losen;
     this.created=created; 
    }
    
}
