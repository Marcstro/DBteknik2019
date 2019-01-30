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


public class Marke {

    public int getId() {
        return id;
    }

    public String getNamn() {
        return namn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }
    
    int id;
    String namn;

    
    Marke(int id, String namn){
        this.id=id;
        this.namn=namn;
    }
}
