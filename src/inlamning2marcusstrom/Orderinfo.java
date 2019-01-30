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


public class Orderinfo {

    public int getId() {
        return id;
    }

    public String getKommentar() {
        return kommentar;
    }

    public int getBetyg() {
        return betyg;
    }

    public int getSkoId() {
        return skoId;
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

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public void setBetyg(int betyg) {
        this.betyg = betyg;
    }

    public void setSkoId(int skoId) {
        this.skoId = skoId;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
    
        int id;
        String kommentar;
        int betyg;
        int skoId;
        String created;
        String updated;
        
        Orderinfo(int id,String kommentar,int betyg,int skoId, String created, String updated){
            
            this. id = id;
            this.  kommentar = kommentar;
            this.  betyg = betyg;
            this.  skoId = skoId ;
            this.  created =  created;
            this.  updated = updated;
        } 
}
