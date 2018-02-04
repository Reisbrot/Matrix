package everything;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Wikipedia {
    
      static int ImageName = 0;
      
   public static void main(String [] args) throws IOException {
      // wiki();
      Util.writeTo("usedAdresses", "Test", true);
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
   
   public static void msopdl() throws IOException{
     System.out.println("64K Ram System 38911 Basic Bytes Free\nReady.\n1.) Download from Lightshot\n2.) Download from i.otut.pw\n3.) Download from Custom URL");
     //Defaults:
     String adress = "https://prnt.sc/";
     int minChars = 1;
     int maxChars = 6;
     boolean includesCaps = false;
     
     //Natürlich könnte ich hier schauen ob der richtige Input eingeputtet wurde... Aber zu faul. Wirklich. Ich bin der einzige der das Tool bedienen wird. Denke ich.
     int choice = new Scanner(System.in).nextInt();
          switch (choice) {
              case 1:
                  adress = "https://prnt.sc/";
                  minChars = 3;
                  maxChars = 5;
                  includesCaps = true; //Nur zur Übersichtlichkeit da.
                  break;
              case 2:
                  adress = "https://i.otut.pw/";
                  minChars = 3;
                  maxChars = 3;
                  includesCaps = true;
                  break;
              case 3:
                  System.out.println("Adress(Protocol|Host|/); ENTER; minimal Bruteforce characters; ENTER; max. Bruteforce chars.; ENTER; Including CAPS (true|false); also ENTER.");
                  adress = new Scanner(System.in).nextLine();
                  minChars = new Scanner(System.in).nextInt();
                  maxChars = new Scanner(System.in).nextInt();
                  includesCaps = new Scanner(System.in).nextBoolean();
                  break;
              default:
                  System.out.println("Fuck off"); System.exit(42);
          }
     
     while(true){
      URL url = BruteforceDownloading.generateURL(new StringBuilder(adress), minChars, maxChars, includesCaps);
      if(Util.checkIfIn("usedAdresses", url.toString()))
          return; //Die aktuelle Methodeninstanz wird beendet und neu ausgeführt, so dass ein neuer Link generiert wird.
      
      String urlString = Util.getWebsiteContentFromURL(url.toString());
      System.out.println(urlString);
      ImageName = BruteforceDownloading.stealImagesFrom(urlString, ImageName, false, url.toString()); //false, damit wir nur das 1. Bild bekommen, welches der Screenshot ist. Das 2. ist der lightshot-Logo.
    }
   }
}