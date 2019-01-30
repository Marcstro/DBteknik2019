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


public class Kategori {

    public int getId() {
        return id;
    }

    public String getNamn() {
        return namn;
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

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    int id;
    String namn;
    String created;
    String updated;
    Kategori(int id, String namn,String created,String updated ){
        this.id=id;
        this.namn=namn;
        this.created=created;
        this.updated=updated;
    }
}
