package everything;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Util {

   public StringBuilder removeHtmlTags(StringBuilder text, String tag, String endtag, boolean between){ //boolean between -> Soll das Zeug zwischen den Tags auch entfernt werden?
     String textString = "";
     int tagLength = tag.length();
     
     boolean hasAnEnd = true;
     if(endtag == null || endtag.equals(""))
         hasAnEnd = false;
          System.out.println(hasAnEnd);

     if(!between)
     try{
         int i = 1;
         int shift = 0;
         int shiftFirstHalfOfMirroredHeart = 0;
         int shiftEndtag = 0;
         while(true){
             if(text.indexOf(tag, shift) == -1) break;
             if(text.indexOf(endtag, shiftEndtag) == -1) hasAnEnd = false;
             if(hasAnEnd) text.delete(shiftEndtag, shiftEndtag + endtag.length()); //Alles vom 1. Auftreten d. Endtags bis zum Ende des Endtags (1. Auftreten + Die Länge = ">" haha lol)
             shift = text.indexOf(tag, shift);
             shiftFirstHalfOfMirroredHeart = text.indexOf(">", shift);
             text.delete(shift, shiftFirstHalfOfMirroredHeart + 1);  // Vom Vorkommen des zu entfernenden Tags bis zum nächsten ">" wird alles gefickt
             
             System.out.println("Endtag: " + endtag +  "; Shift: " + shift);            //Weird debug Output
             shiftEndtag = text.indexOf(endtag, shift);
             System.out.println("Wird ein: " + shiftEndtag + "  :)");                   //Weird debug Output
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
        
        
        return text;
    }
}
