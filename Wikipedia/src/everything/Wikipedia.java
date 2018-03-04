package everything;
import java.awt.Desktop;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Wikipedia {
    
      static int ImageName = 99;
      
   public static void main(String [] args) throws IOException {
      // wiki();
      Util.writeTo("usedAdresses", "New Try", true);
      //ImageName = 6774;
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
     System.out.println("64K Ram System 38911 Basic Bytes Free\nReady.\n1.) Download from Lightshot\n2.) Download from i.otut.pw\n3.) Download from i.imgur.com\n4.) Download from Custom URL");
     //Defaults:
     String adress = "https://prnt.sc/";
     int minChars = 1;
     int maxChars = 6;
     boolean includesCaps = false;
     String usedAdresses = "default";
     boolean images = true;
     
     //Natürlich könnte ich hier schauen ob der richtige Input eingegeben wurde... Aber zu faul. Wirklich. Ich bin der einzige der das Tool bedienen wird. Denke ich.
     int choice = 3;//new Scanner(System.in).nextInt();
          switch (choice) {
              case 1:
                  adress = "https://prnt.sc/";
                  minChars = 3;
                  maxChars = 6;
                  includesCaps = false; //Nur zur Übersichtlichkeit da.
                  images = true; //Same
                  usedAdresses = "Lightshot";
                  break;
              case 2:
                  adress = "https://i.otut.pw/";
                  minChars = 3;
                  maxChars = 3;
                  includesCaps = true;
                  usedAdresses = "Otut";
                  break;
              case 3:
                  adress = "https://i.imgur.com/";
                  minChars = 4;
                  maxChars = 8;
                  includesCaps = true;
                  usedAdresses = "Imgur";
                  break;
              case 4:
                  System.out.println("Adress(Protocol|Host|/); ENTER; minimal Bruteforce characters; ENTER; max. Bruteforce chars.; ENTER; Including CAPS (true|false); ENTER; used-Adress-File name; ENTER; images runterladen (true|false) also ENTER.");
                  adress = new Scanner(System.in).nextLine();
                  minChars = new Scanner(System.in).nextInt();
                  maxChars = new Scanner(System.in).nextInt();
                  includesCaps = new Scanner(System.in).nextBoolean();
                  usedAdresses = new Scanner(System.in).nextLine();
                  break;
              default:
                  System.out.println("Fuck off"); System.exit(42);
          }
     
     while(true){
      URL url = BruteforceDownloading.generateURL(new StringBuilder(adress), minChars, maxChars, includesCaps);
      if(Util.checkIfIn("usedAdresses" + usedAdresses, url.toString()))
          return; //Die aktuelle Methodeninstanz wird beendet und neu ausgeführt, so dass ein neuer Link generiert wird.
      
      String websiteContent = Util.getWebsiteContentFromURL(url.toString());
      if(images)
          ImageName = BruteforceDownloading.stealImagesFrom(websiteContent, ImageName, false, url.toString(), usedAdresses); //false, damit wir nur das 1. Bild bekommen, welches der Screenshot ist. Das 2. ist der lightshot-Logo.
      else
          Desktop.getDesktop().browse(new File(url.toString()).toURI());
    }
   }
}