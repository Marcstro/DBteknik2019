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


public class Farg {

    public int getId() {
        return id;
    }

    public String getPrimarfarg() {
        return primarfarg;
    }

    public String getSekundarfarg() {
        return sekundarfarg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrimarfarg(String primarfarg) {
        this.primarfarg = primarfarg;
    }

    public void setSekundarfarg(String sekundarfarg) {
        this.sekundarfarg = sekundarfarg;
    }
    
    int id;
    String primarfarg;
    String sekundarfarg;
    Farg(int id, String primarfarg, String sekundarfarg){
    this.id=id;
    this.primarfarg=primarfarg;
    this.sekundarfarg=sekundarfarg;
    }

}
