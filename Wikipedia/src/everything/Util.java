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
      List<String> items = new ArrayList<String>();
      items.add("s");
      System.out.printf("Found %d elements of type \"%s\".%n", items.size(), tag);
      for(Object item : items){                              //iterate through Results
       int startpos = text.indexOf(item.toString());
       int endpos = startpos + item.toString().length();
       text.delete(startpos, endpos);                         //remove item decl. from text
      }
      textString = text.toString();
      text = new StringBuilder(textString.replaceAll(endtag, ""));  //remove endtag from text
      if(items.size() != 0)
      System.out.println("Removed them.");
    }
    catch(StringIndexOutOfBoundsException e){
      System.err.println(e + "\n Some shit triggered by the HTML-Code. Should be debugged. Later. Maybe. Nothing happens. See ya." );  
    }
        
        
        return text;
    }
}
