package everything;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Util {

   public StringBuilder removeHtmlTags(String t, String tag, String endtag){
     StringBuilder text = new StringBuilder(t);
     String textString = "";
     
     boolean hasAnEnd = true;
     if(endtag == null || endtag.equals(""))
         hasAnEnd = false;
     
     try{
         textString = t.replace(tag, "");
         int lengthDifference = t.length() - textString.length();
         int tagLength = tag.length();
         int replacedTags = lengthDifference / tagLength;
      System.out.printf("Found %d elements of type \"%s\".%n", replacedTags, tag);
      
      text = new StringBuilder(textString.replaceAll(endtag, ""));  //remove endtag from text
      System.out.println("Removed them.");
    }
    catch(StringIndexOutOfBoundsException e){
      System.err.println(e + "\n Some shit triggered by the HTML-Code. Should be debugged. Later. Maybe. Nothing happens. See ya." );  
    }
        
        
        return text;
    }
}
