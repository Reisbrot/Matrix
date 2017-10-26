package everything;

import com.jaunt.*;


public class Gather {
    public String title(String urlContent){
        int titleStart = urlContent.indexOf("<title>") + 7; //+7 because <title> would be part of the output otherwise
        int titleEnd = urlContent.indexOf("</title>")  - 12; // -12 because the title always ends with " – Wikipedia"
        String title = urlContent.substring(titleStart, titleEnd);
        return title;
    }

    public String intrestingText(String urlContent) {
        Gather GatherThe = new Gather();
        String text;
        int textStart = urlContent.indexOf("class=\"mw-parser-output\"") + 25; //Thats where The Headline of the Article Ends. + 25 for the "parser-output"-Overcome-Stuff
        int textEnd = urlContent.indexOf("id=\"Einzelnachweise\""); //There where the Sources start. WHO CARES
        if(textEnd == -1)
            textEnd = urlContent.indexOf("id=\"Weblinks\""); //So if there are no Sources, Just suc in all that information until Weblinks
        if(textEnd != -1)       
            text = urlContent.substring(textStart, textEnd);
        else
            text = urlContent.substring(textStart);
        System.out.println(textStart + " - " + textEnd);
        
        GatherThe.plainText(text);
        

        
        
        return text;
    }

    private void plainText(String t) {
        int iStart = -1;
        int iEnd = -1;
        StringBuilder text = new StringBuilder(t);
        System.out.println(text);
    try{
     UserAgent userAgent = new UserAgent();
     userAgent.openContent(t);
 
      Elements tables = userAgent.doc.findEach("<a href>");       //find non-nested tables   
      System.out.println("Found " + tables.size() + " links:");
      for(Element table : tables){                               //iterate through Results
        System.out.println(table.outerHTML() + "\n" + table.innerText() + "\n----\n");      //print each element and its contents
      }   
    }
    catch(ResponseException e){
      System.out.println(e);
    }
    }
}
