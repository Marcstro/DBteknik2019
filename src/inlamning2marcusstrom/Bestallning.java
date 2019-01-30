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


public class Bestallning {

    public int getId() {
        return id;
    }

    public int getKundId() {
        return kundId;
    }

    public boolean isExpedierad() {
        return expedierad;
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

    public void setKundId(int kundId) {
        this.kundId = kundId;
    }

    public void setExpedierad(boolean expedierad) {
        this.expedierad = expedierad;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
    public void addVara(){
        antalVaror++;
    }
    public int getVaror(){
        return antalVaror;
    }
    public Orderinfo getOI(int a){
        return OI.get(a);
    }
    public void setOI(Orderinfo oo){
        OI.add(oo);
    }
 
            int id;
            int kundId;
            boolean expedierad;
            LinkedList<Orderinfo> OI = new LinkedList<>();
            String created;
            String updated;
            String info;
            int antalVaror = 0;
           // int id, int kundId, boolean expedierad,  String created, String updated;
            Bestallning(int id, int kundId, boolean expedierad,  String created, String updated){
                this.id=id;
                this.kundId=kundId;
                this.expedierad= expedierad;
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
