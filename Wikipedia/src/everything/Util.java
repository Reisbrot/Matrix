package everything;

import com.jaunt.*;

public class Util {
    public StringBuilder removeHtmlTags(StringBuilder text){
            try{
     UserAgent userAgent = new UserAgent();
     userAgent.openContent(t);
     
      Elements links = userAgent.doc.findEach("a href");       //find non-nested links
      System.out.println("Found " + links.size() + " links.");
      for(Element link : links){                              //iterate through Results
       int startpos = text.indexOf(link.toString());
       int endpos = startpos + link.toString().length();
       text.delete(startpos, endpos);                         //remove link decl. from text
      }
      textString = text.toString();
      text = new StringBuilder(textString.replaceAll("</a>", ""));  //remove link end from text
      System.out.println("removed them. " + text);
      
      Elements tables = userAgent.doc.findEach("table");       //find non-nested links
      System.out.println("Found " + tables.size() + " tables.");
      for(Element table : tables){                              //iterate through Results
       int startpos = text.indexOf(table.toString());
       int endpos = startpos + table.toString().length();
       text.delete(startpos, endpos);                        //remove link decl. from text
       textString = text.toString();
      //text = new StringBuilder(textString.replaceAll("</a>", ""));  //remove link end from text
      System.out.println("removed them. " + text);
      }
    }
    catch(ResponseException e){
      System.out.println(e);
    }
    catch(StringIndexOutOfBoundsException e){
      System.err.println(e + "\n Some shit triggered by the HTML-Code. Should be debugged. Later. Maybe. Nothing happens. See ya." );  
    }
    System.out.println("\n");
        
        
        return text;
    }
}
