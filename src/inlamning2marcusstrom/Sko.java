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


public class Sko {

    public int getId() {
        return id;
    }

    public int getStorlek() {
        return storlek;
    }

    public int getFargId() {
        return fargId;
    }

    public int getMarkeId() {
        return markeId;
    }

    public int getPris() {
        return pris;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStorlek(int storlek) {
        this.storlek = storlek;
    }

    public void setFargId(int fargId) {
        this.fargId = fargId;
    }

    public void setMarkeId(int markeId) {
        this.markeId = markeId;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
    public int getAntal(){
        return antal;
    }
    public void setAntal(int a){
        antal=a;
    }
    int id;
    int antal;
    int storlek;
    int fargId;
    int markeId;
    int pris;
    int kategoriId;
    String created;
    String updated;
    String info;
    Marke marke;
    Farg farg;
    Kategori kategori;

    public Marke getMarke() {
        return marke;
    }

    public Farg getFarg() {
        return farg;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setMarke(Marke marke) {
        this.marke = marke;
    }

    public void setFarg(Farg farg) {
        this.farg = farg;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
    
    
    Sko(int id, int antal, int storlek, int fargId,  int markeId, int pris, int kategoriId, String created, String updated){
       this.id=id;
       this.antal=antal;
       this.storlek=storlek;
       this.fargId=fargId;
       this.markeId=markeId;
       this.pris=pris;
       this.kategoriId=kategoriId;
       this.created=created;
       this.updated=updated;
    }
    
    public String getInfo(){
        return info;
    }
    public void setInfo(String a){
        info=a;
    }
    
}
