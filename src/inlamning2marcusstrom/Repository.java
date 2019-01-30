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

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;



public class Repository {

    Properties p = new Properties();
    String connectionString, name, pw;
    
    /**
     * OBS OBS OBS!
     * jAG HAR INTE LADDAT UPP MIN .PROPERTIES-FIL
     * dESS INNEHÅLL SER UT PÅ FÖLJANDE SETT:
        * name=******
        *ppassword=*****
        *connectionString=jdbc:mysql://localhost:3306/skoshop2
     */
    Connection con;
    boolean inloggad = false;
    Kund inloggatKonto = null;
    LinkedList<Kund> kunder = new LinkedList<>();
    LinkedList<Sko> skor = new LinkedList<>();
    Map<Integer, Sko> varor = new HashMap<>();
    Map<Integer, Bestallning> aktuellaBestallningar = new HashMap<>();
    LinkedList<Sko> varorKop = new LinkedList<>();
    LinkedList<Bestallning> bestallningar = new LinkedList<>();
    LinkedList<Bestallning> minaBestallningar = new LinkedList<>();
    LinkedList<Orderinfo> orderInfos = new LinkedList<>();
    
    //88
    LinkedList<Marke> marken = new LinkedList<>();
    LinkedList<Farg> farger = new LinkedList<>();
    LinkedList<Kategori> kategorier = new LinkedList<>();
    
    LinkedList<Kund> nylista=new LinkedList<>();
    
    LinkedList<Sko> inkopsLista = new LinkedList<>();
    
    

    public Repository (){
        
        try{
            p.load(new FileInputStream("src/prop.properties"));
            Class.forName("com.mysql.jdbc.Driver");
            connectionString= p.getProperty("connectionString");
            name = p.getProperty("name");
            pw = p.getProperty("ppassword");
            con = DriverManager.getConnection(connectionString, name, pw);
         
        }
        catch(Exception e){
            e.printStackTrace();
        }
        createKunder();
        createSkor();
        skapaVaroLista();
        createBestallningar();
        createOrderinfo();
        
        createSkoUppgifter();
//        int raknare = 1; 
//                for(Bestallning g: bestallningar){
//            System.out.println(raknare + ": " + g.toString());// + ": " + g.getInfo());
//            raknare++;
//        }
//        System.out.println("marcus det funkar");
//        int raknare = 1; 
//        System.out.println("test1");
//        for(Sko g: skor){
//            System.out.println(raknare + ": " + g.getInfo());
//            raknare++;
//        }
      
       //getSkorsInfo();
       
    }
    
    public void createSkor(){
        try{
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from sko");
            while(rs.next()){
                int id = rs.getInt("id");
                int antal = rs.getInt("lagerantal");
                int storlek = rs.getInt("storlek");
                int fargId = rs.getInt("fargId");
                int markeId = rs.getInt("markeId");
                int pris = rs.getInt("pris");
                int kategoriId = rs.getInt("kategoriId");
                String created = rs.getString("created");
                String updated = rs.getString("updated");
                
                Sko nySko = new Sko(id, antal, storlek, fargId, markeId, pris, kategoriId, created, updated);
                skor.add(nySko);
                String info="";
               // System.out.println("fel1");
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("select farg.primarfarg from sko inner join farg on farg.id = sko.fargId where sko.id = "+ 
                        id + ";");
                while(rs2.next()){
                    info+="Farg: " + rs2.getString("farg.primarfarg");
                }
               // System.out.println("fel2");
                Statement stmt3 = con.createStatement();
                ResultSet rs3 = stmt3.executeQuery("select marke.namn from sko inner join marke on marke.id = sko.markeId where sko.id = "
                        + id + ";");
                while(rs3.next()){
                    info+=", Marke: " + rs3.getString("namn");
                }
               // System.out.println("fel3");
                Statement stmt4 = con.createStatement();
                info+=", Pris: " + pris;
                ResultSet rs4 = stmt4.executeQuery("select kategori.namn from sko inner join kategori on kategori.id = sko.kategoriId where sko.id = "
                        + id + ";");
              //  System.out.println("fel4");
                while(rs4.next()){
                    info+=", Kategori: " + rs4.getString("namn");
                }
              //  System.out.println("fel5");
                nySko.setInfo(info);
            //    System.out.println("fel6");
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createOrderinfo(){
        try{
             Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from orderinfo");
            //id | kommentar       | betyg | skoId | bestallningId | created             | updated
            int bestallningId=0;
            while(rs.next()){
                int id = rs.getInt("id");
                String kommentar = rs.getString("kommentar");
                int betyg = rs.getInt("betyg");
                int skoId = rs.getInt("skoId");
                 bestallningId = rs.getInt("bestallningId");
                String created = rs.getString("created");
                String updated = rs.getString("updated");
                Orderinfo nyorder = new Orderinfo(id, kommentar, betyg, skoId,created,updated);
                orderInfos.add(nyorder);
            }
            for(Orderinfo o: orderInfos){
                for(Sko s: skor){
                    if(s.getId()==o.getSkoId()){
                        o.setSkoId(s.getId());
                    }
                }
                for(Bestallning b: bestallningar){
                    if(bestallningId == b.getId()){
                        b.setOI(o);
                        b.addVara();
                    }
                }
            }
        }
            catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createKunder(){
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from kund");
            
            while(rs.next()){
               // int id = Integer.parseInt((rs.getString("id")));
                int id = rs.getInt("id");
                String namn = rs.getString("namn");
                String adress = rs.getString("adress");
                String ort = rs.getString("ort");
                String losen = rs.getString("losen");
                String created = rs.getString("created");
                Kund nykund = new Kund(id, namn, adress, ort, losen, created);
                kunder.add(nykund);
            }
            Kund testKund = new Kund(0, "t", "a","o","t","");
            kunder.add(testKund);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createBestallningar(){
        try{
           Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from bestallning");
            
            while(rs.next()){
               // int id = Integer.parseInt((rs.getString("id")));
                int id = rs.getInt("id");
                int kundId = rs.getInt("kundId");
                boolean expedierad = rs.getBoolean("expedierad");
                String created = rs.getString("created");
                String updated = rs.getString("updated");
                
                Bestallning nyBestallning = new Bestallning(id, kundId, expedierad, created, updated);
                bestallningar.add(nyBestallning);
            }
            for(Bestallning b: bestallningar){
                for(Kund k: kunder){
                    if(b.getKundId()==k.getId()){
                        k.addBestallning(b);
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createSkoUppgifter(){//OBS OBS INTE GJORD KLAR FUNKAR EJ GOR HAR!
        try{//77
           Statement stmt1 = con.createStatement();
            ResultSet rs = stmt1.executeQuery("Select * from marke");
            
            while(rs.next()){
               // int id = Integer.parseInt((rs.getString("id")));
               int id = rs.getInt("id");
               String namn = rs.getString("namn");
               Marke nym = new Marke(id,namn);
               marken.add(nym);
            }
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("Select * from farg");
            while(rs2.next()){
               // int id = Integer.parseInt((rs.getString("id")));
               int id = rs2.getInt("id");
               String f1 = rs2.getString("primarfarg");
               String f2 = rs2.getString("sekundarfarg");
               Farg f = new Farg(id,f1,f2);
               farger.add(f);
            }
            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("Select * from kategori");
            while(rs3.next()){
               // int id = Integer.parseInt((rs.getString("id")));
               int id = rs3.getInt("id");
               String namn = rs3.getString("namn");
               String created = rs3.getString("created");
               String updated = rs3.getString("updated");
               
               Kategori k = new Kategori(id, namn, created,updated);
               kategorier.add(k);
            }
            
            for(Sko s: skor){
                for(Marke m: marken){
                    if(s.getId()==m.getId()){
                        s.setMarke(m);
                    }
                }
                for(Farg f: farger){
                    if(s.getId()==f.getId()){
                        s.setFarg(f);
                    }
                }
                for(Kategori k: kategorier){
                    if(s.getId()==k.getId()){
                        s.setKategori(k);
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createMinaBestallningar(){
        try{
           for(Bestallning g: bestallningar){
               if(g.getKundId() == inloggatKonto.getId()){
                   minaBestallningar.add(g);
               }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String getKundersNamn(){
        int raknare = 1;
        String a = "";
        for(Kund g: kunder){
            a+=raknare + ": " + g.getNamn() + "\n";
            raknare++;
        }
        a.substring(0, a.length()-2);
        return a;
    }
    public void skapaVaroLista(){
        int raknare = 1;
         varor.clear();
         for(Sko s: skor){
         //varor.put( raknare, s.getInfo());
            // System.out.println(s.getAntal());
         if(s.getAntal()>0){
         varor.put(raknare, s);
         raknare++;
         }
         }
    }
    public Map getVaror(){
        return varor;
    }
     public String getAktuellaBestallningarInfo(){
         //LAMBDA har finns lambda
         List<Bestallning> aktuella = minaBestallningar.stream().filter(B -> !B.expedierad).collect(Collectors.toList());
         
         int raknare = 0;
         aktuellaBestallningar.clear();//fel66
         
         for(Bestallning b: aktuella){
            raknare++;
            aktuellaBestallningar.put(raknare, b);
             
            
         }
          String returr="";         
                for(Integer key: aktuellaBestallningar.keySet()){
                    returr+=key.toString() + ": Bestallning skapades: " +
                            aktuellaBestallningar.get(key).getCreated() +
                            ", bestallninge innehaller: " +
                            aktuellaBestallningar.get(key).getVaror() + 
                            ", Utskickad?: " + aktuellaBestallningar.get(key).isExpedierad()
                            + "\n";
                }
       return returr;
    }
     public boolean finnsBestallning(int num){
         if(aktuellaBestallningar.containsKey(num)){
            return true;
        }
        return false;
     }
    public String getSkorsInfo(){
        String returr="";
              //  int mangd = varor.size();
                //HERE OBS OBS 260 OBS OBS
              //  varor.forEach((k,v)->System.out.println("Fruit: " + k + ", Price: " + v));
               // varor.forEach((k,v)->returr+=key.toString() + ", " + varor.get(key).getInfo() + "\n");
                
                for(Integer key: varor.keySet()){
                    returr+=key.toString() + ": " + varor.get(key).getInfo() + "\n";
                }
                
                
                return returr;
    }
   
    
    public boolean finnsSko(int num){
        if(varor.containsKey(num)){
            return true;
        }
        return false;
    }
    public Sko geSko(int num){
        return varor.get(num);
    }
    public String kopVara(int skoId, int bestallningId){
        
    
     //OBS HÄR SKA DET FINNAS ROLLBACK OCH SÅ VIA FELHANTERING
     //AddToCart(in kundId int, in bestallningId int, in skoId int)
     try{
     CallableStatement cstmt = con.prepareCall("Call AddToCart(?,?,?)");
     cstmt.setInt(1,inloggatKonto.getId());
     cstmt.setInt(2,bestallningId);
     cstmt.setInt(3,skoId);
     //ResultSet info  = 
      cstmt.executeQuery();
       
//     String infon = "";
//     while(info.next()){
//      infon += info.getString("error");
//     }
     bestallningar.get(bestallningId).addVara();
     //skapaVaroLista();
     JOptionPane.showMessageDialog(null, "Varan har blivit kopt, lagerantal har minskats.");
     return "Success! Fran rep";
     }
     catch(Exception e){
         JOptionPane.showMessageDialog(null, "OBS nagot gick fel, kopet gick ej igenom!");
         e.printStackTrace();
         String arf = e.toString() + ", Nagot gick fel, kopet genomfordes EJ!";
         return arf;
     }
    }
    public void skapaNyBestallning(){
        try{
        PreparedStatement stmt = con.prepareStatement("insert into bestallning ( kundId, expedierad)  values (?,?)");
            stmt.setInt(1, inloggatKonto.getId());
            stmt.setBoolean(2, false);
            stmt.execute();
                Statement stmtt = con.createStatement();
                ResultSet rs = stmtt.executeQuery("select * from bestallning where id = (select max(id) from bestallning);");
                boolean gjort = false;
                while(rs.next()){
               // int id = Integer.parseInt((rs.getString("id")));
                int id = rs.getInt("id");
                int kundId = rs.getInt("kundId");
                boolean expedierad = rs.getBoolean("expedierad");
                String created = rs.getString("created");
                String updated = rs.getString("updated");
                
                Bestallning nyBestallning = new Bestallning(id, kundId, expedierad, created, updated);
                bestallningar.add(nyBestallning);
              //  aktuellaBestallningar.add(nyBestallning);
              if(!gjort){
                aktuellaBestallningar.put(aktuellaBestallningar.size()+1, nyBestallning);
                minaBestallningar.add(nyBestallning);
                gjort=true;
              }
              
                }//fel99
                
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean loggaIn(String an, String lo){
        for(Kund g: kunder){
            if(g.getNamn().contains(an)){
                if(g.getLosen().equalsIgnoreCase(lo)){
                    inloggatKonto=g;
                    inloggad=true;
                    return true;
                }
            }
        }
        return false;
    }
    public void metodgorasaker(){
        

    }
}
