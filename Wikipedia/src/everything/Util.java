package everything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

   public static String getWebsiteContentFromURL(Object urlOrStringThatHasToBeConvertedToURL){
       String StringThatHasToBeConvertedToURL=null;
       if(urlOrStringThatHasToBeConvertedToURL instanceof String) StringThatHasToBeConvertedToURL=(String)urlOrStringThatHasToBeConvertedToURL;
       if(urlOrStringThatHasToBeConvertedToURL instanceof URL) StringThatHasToBeConvertedToURL=((URL)urlOrStringThatHasToBeConvertedToURL).toString();
       URL url = null;
       try {
           url = new URL(StringThatHasToBeConvertedToURL);
       } catch (MalformedURLException ex) {
           Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
           System.err.println("Given URL Malformed you <good ol' boy>");
           ex.printStackTrace();
       }
       String urlString = "";
      try {
         URLConnection urlConnection = url.openConnection();
         HttpURLConnection connection;
         connection = (HttpURLConnection) urlConnection;
         connection.setRequestProperty("User-Agent", "NetscapeNavigator/1.22 (Windows 3.11; i386)");
         
         BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
         String current;
         
         while((current = in.readLine()) != null) {
            urlString += current;
         }
      }catch(IOException e) {
         e.printStackTrace();
      }
      return urlString; 
   } 
    
   public StringBuilder removeHtmlTags(StringBuilder text, String tag, String endtag, boolean between){ //boolean between -> Soll das Zeug zwischen den Tags auch entfernt werden?
     String textString = "";
     int tagLength = tag.length();
     
     boolean hasAnEnd = true;
     if(endtag == null || endtag.equals(""))
         hasAnEnd = false;
          //System.out.println(hasAnEnd);

     if(!between)
     try{
         int i = 1;
         int shift = 0;
         int shiftFirstHalfOfMirroredHeart = 0; //An ordinary ">"
         int shiftEndtag = 0;
         while(true){
             if(text.indexOf(tag, shift) == -1) break;
             if(text.indexOf(endtag, shiftEndtag) == -1) hasAnEnd = false;
             if(hasAnEnd) text.delete(shiftEndtag, shiftEndtag + endtag.length()); //Alles vom 1. Auftreten d. Endtags bis zum Ende des Endtags (1. Auftreten + Die Länge = ">" haha lol)
             shift = text.indexOf(tag, shift);
             shiftFirstHalfOfMirroredHeart = text.indexOf(">", shift);
             text.delete(shift, shiftFirstHalfOfMirroredHeart + 1);  // Vom Vorkommen des zu entfernenden Tags bis zum nächsten ">" wird alles gefickt
             
             //System.out.println("Endtag: " + endtag +  "; Shift: " + shift);            //Weird debug Output
             shiftEndtag = text.indexOf(endtag, shift);
             //System.out.println("Wird ein: " + shiftEndtag + "  :)");                   //Weird debug Output
         }
    }
    catch(StringIndexOutOfBoundsException e){
      System.err.println(e + "\n Some shit triggered by the HTML-Code. Should be debugged. Later. Maybe. Nothing happens. See ya." );  
    }
    else
    try{
         int shift = 0;
         int shiftEndtag = 0;
         while(true){
             if(text.indexOf(tag, shift) == -1) break;
             if(text.indexOf(endtag, shiftEndtag) == -1) hasAnEnd = false;                  //Es gib kein Endtag und somit auch kein Ende mehr -> Dirty Dirty Dirty!
             shiftEndtag = text.indexOf(endtag, shift);
             shift = text.indexOf(tag, shift);
             if(hasAnEnd)                                         //Wenn es ein Endtag gibt. z.B <p> Du stinkst </p>
             text.delete(shift, shiftEndtag + endtag.length());                     //Alles zwischen den beiden Tags wird entfernt. + Die Endtag length, daa dieser auch noch mit weg sollte ;-; GIBT SINN NHNHNH
             else
             text.delete(shift, shift + tagLength);              //Kein Endtag. (z.B <br>)
         }
         
    }
    catch(StringIndexOutOfBoundsException e){
      //System.err.println(e + "\n Some shit triggered by the HTML-Code. Should be debugged. Later. Maybe. Nothing happens. See ya." );  
    }
    
    if(text.indexOf(">") < text.indexOf("<") || text.indexOf(">")!=-1)     // Wenn indexOf KlammerZu kleiner ist als indexOf KlammerOffen und KlammerZu vorhanden ist
      text.replace(0, text.indexOf(">") + 1, "");                             //  Alles von Anfang bis z. ersten auftreten von KlammerZu wird ELIMINIERT
              
        return text;
    }
}
