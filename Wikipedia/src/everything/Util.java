package everything;

import com.jaunt.*;

public class Util {

   public StringBuilder removeHtmlTags(String t, String tag, String endtag){
     StringBuilder text = new StringBuilder(t);
     String textString = "";
     
     boolean hasAnEnd = true;
     if(endtag == null || endtag.equals(""))
         hasAnEnd = false;
     
     try{
      UserAgent userAgent = new UserAgent();
      userAgent.openContent(t);
      
      Elements items = userAgent.doc.findEvery(tag);       //find all (also non-nested) items
      System.out.printf("Found %d elements of type \"%s\".%n", items.size(), tag);
      for(Element item : items){                              //iterate through Results
       int startpos = text.indexOf(item.toString());
       int endpos = startpos + item.toString().length();
       text.delete(startpos, endpos);                         //remove item decl. from text
      }
      textString = text.toString();
      text = new StringBuilder(textString.replaceAll(endtag, ""));  //remove endtag from text
      if(items.size() != 0)
      System.out.println("Removed them.");
    }
    catch(ResponseException e){
      System.out.println(e);
    }
    catch(StringIndexOutOfBoundsException e){
      System.err.println(e + "\n Some shit triggered by the HTML-Code. Should be debugged. Later. Maybe. Nothing happens. See ya." );  
    }
        
        
        return text;
    }
}
