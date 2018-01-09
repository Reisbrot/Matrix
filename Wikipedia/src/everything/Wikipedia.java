package everything;
import java.net.*;
import java.io.*;

public class Wikipedia {

   public static void main(String [] args) {
      while(true){
      Gather GatherThe = new Gather();
      String urlString = "";
      try {
         URL url = new URL("https://de.wikipedia.org/wiki/Spezial:Zuf%C3%A4llige_Seite");
         URLConnection urlConnection = url.openConnection();
         HttpURLConnection connection;
         connection = (HttpURLConnection) urlConnection;
         
         BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
         String current;
         
         while((current = in.readLine()) != null) {
            urlString += current;
         }
      }catch(IOException e) {
         e.printStackTrace();
      }
      System.out.println(GatherThe.title(urlString));
      String plainText = GatherThe.plainText(urlString).toString();
      //String intrestingText = GatherThe.intrestingText(urlString); Den wichtigen Text hab ich vorher per HTML Tags ausfindig gemacht (glaube ich, zu faul nachzusehen) Da dabei ungeschlossene Tags übrigbleiben hab ich mich dazu entschlossen, erst den Text zu säubern. Vielleicht die signifikanten Tags später entfernen?

      System.out.println("\n\nAs removed:    " + plainText + "\n");
      
      
      }
   }
}