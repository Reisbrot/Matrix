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
      String intrestingText = GatherThe.intrestingText(urlString);
      String plainText = GatherThe.plainText(intrestingText).toString();
      System.out.println("As removed: " + plainText + "\n");
      
      
      }
   }
}