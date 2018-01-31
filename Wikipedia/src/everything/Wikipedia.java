package everything;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Wikipedia {

   public static void main(String [] args) {
      // wiki();
      msopdl();
   }
   
   public static void wiki(){
           while(true){
      Gather GatherThe = new Gather();
      String urlString = "";
      urlString = Util.getWebsiteContentFromURL("https://de.wikipedia.org/wiki/Spezial:Zuf%C3%A4llige_Seite");
      System.out.println(GatherThe.title(urlString));
      String plainText = GatherThe.plainText(urlString).toString();
      //String intrestingText = GatherThe.intrestingText(urlString); Den wichtigen Text hab ich vorher per HTML Tags ausfindig gemacht (glaube ich, zu faul nachzusehen) Da dabei ungeschlossene Tags übrigbleiben hab ich mich dazu entschlossen, erst den Text zu säubern. Vielleicht die signifikanten Tags später entfernen?

      System.out.println("\n\nAs removed:    " + plainText + "\n");
      System.err.println(plainText.indexOf('<'));
      
      }  
   }
   
   public static void msopdl(){
      URL url = BruteforceDownloading.generateURL(new StringBuilder("https://prnt.sc/")); 
      String urlString = Util.getWebsiteContentFromURL(url.toString());
      System.out.println(urlString);

   }
}