package everything;


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
        //while(true){
            iStart = text.indexOf("<a href"); //Link Decleration
            if(iStart != -1)                  //if there is not not a Link
             iEnd = text.indexOf(">", iStart);//How Link Declerations normally End
            else
            ;// break;
            
            System.out.println(iStart + " # " + iEnd);
        //}
    }
}
