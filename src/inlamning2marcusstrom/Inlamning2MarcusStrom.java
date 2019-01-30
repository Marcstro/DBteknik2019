/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcu
aer awesome
 */


package inlamning2marcusstrom;

import javax.swing.JOptionPane;


public class Inlamning2MarcusStrom {

    
    public static void main(String[] args) {
        
        
        
            Repository r = new Repository();
//            r.metodgorasaker();
//           System.out.println(r.kunder.size());
//           
//           r.visa();
//           System.out.println(r.nylista.size());
           
           while(true){
               
           String anv="";
           anv = JOptionPane.showInputDialog("Vilken kund aer du? Foljande kunder finns: \n"
                    + r.getKundersNamn() + "\n Avsluta med \"q\"");
                   
           if(anv.equalsIgnoreCase("q")){
               break;
           }
           String lose = JOptionPane.showInputDialog("Och vad ar ditt losenord? " +
                   "OBS ALFA VERSION ALLAS LOSENORD AR \"l\".");
           if(r.loggaIn(anv, lose)){
               System.out.println("Inloggad som " + anv);
               
               break;
           }
           else{
               System.out.println("OBS FEL ANV ELLER LOSENORD");
           }
           }
           while(true){
             //  System.out.println(r.getVaror());
             //  System.out.println(r.getSkorsInfo());
              // System.out.println(r.getSkorsInfo());
               String kopa = JOptionPane.showInputDialog("Foljande produkter finns:"+
                       "\n valj produkt genom att ange siffran som star fore produkten.\n"
                       +r.getSkorsInfo() + "\n Avsluta med q");
               if(kopa.equalsIgnoreCase("q"))
                   break;
               else if (!kopa.matches("[0-9]+")){
                   JOptionPane.showMessageDialog(null, "OBS enbart giltiga siffror!");
                   break;
               }
               int kop = Integer.parseInt(kopa);
               if(r.finnsSko(kop)){
                   r.createMinaBestallningar();
                 //  System.out.println(r.geSko(kop).getInfo());
                 //  break;
               }
               else{
                   System.out.println("Obs det finns ingen sko for den siffran, vanliga valj igen.");
               }
              
              String vilkenBestallning = JOptionPane.showInputDialog(
                      "Och nu ska vi valja vilken bestallning vi villl laga in varan i. \n"
                              +"Skriv \"n\" for en ny bestallning. \n"
                              + "Foljande bestallningar ar aktuella for ditt konto: \n"+
                              r.getAktuellaBestallningarInfo());
              int bestallning = 0;
              if(vilkenBestallning.equalsIgnoreCase("n")){
                  //r.skapaNyBestallning();
                   bestallning = 0;
              }
              
              else  if (!vilkenBestallning.matches("[0-9]+")){
                   JOptionPane.showMessageDialog(null, "OBS enbart giltiga siffror!2");
                   break;
               }
              if(vilkenBestallning.matches("[0-9]+"))
                 bestallning = Integer.parseInt(vilkenBestallning);
              
              if (!r.finnsBestallning(bestallning)){
                  System.out.println("Obs det finns ingen bestallning for den siffran. Skapar Ny bestallning");
                  r.skapaNyBestallning();
              }
               System.out.println(r.kopVara(kop,bestallning));
              
               System.out.println("FranHuvudProgram: genomfordes korrekt");
               break;
           }
           
           
    }
//agerIDList.stream().forEach(s->getStockRow((s)));
}
