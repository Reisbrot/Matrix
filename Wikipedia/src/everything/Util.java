package everything;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Util {

   public StringBuilder removeHtmlTags(String t, String tag, String endtag, boolean between){ //boolean between -> Soll das Zeug zwischen den Tags auch entfernt werden?
     StringBuilder text = new StringBuilder(t);
     String textString = "";
     int tagLength = tag.length();
     
     boolean hasAnEnd = true;
     if(endtag == null || endtag.equals(""))
         hasAnEnd = false;
          System.out.println(hasAnEnd);

     if(!between)
     try{
         textString = t.replace(tag, "");
         int lengthDifference = t.length() - textString.length();
         int replacedTags = lengthDifference / tagLength;
      System.out.printf("Found %d elements of type \"%s\".%n", replacedTags, tag);
      
      text = new StringBuilder(textString.replaceAll(endtag, ""));  //remove endtag from text
      System.out.println("Removed them.");
    }
    catch(StringIndexOutOfBoundsException e){
      //System.err.println(e + "\n Some shit triggered by the HTML-Code. Should be debugged. Later. Maybe. Nothing happens. See ya." );  
    }
    else
    try{
         int shift = 0;
         while(true){
             if(text.indexOf(tag, shift) == -1) break;
             shift = text.indexOf(tag, shift);
             if(hasAnEnd)                                         //Wenn es ein Endtag gibt. z.B <p> Du stinkst </p>
             text.delete(shift, endtag.length());
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
