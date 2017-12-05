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
        return text;
    }

    public void plainText(String t) {
        boolean between = false; //plainText() -> Wir wollen das Zeug zwischen den Links nicht!
        System.out.println("Raw: " + t);
        Util util = new Util();
        String tag = new java.util.Scanner(System.in).nextLine();
        String endtag = new java.util.Scanner(System.in).nextLine();
        StringBuilder text = new StringBuilder(t);
        text = util.removeHtmlTags(t, tag, endtag, between);
        System.out.println("As removed: " + text + "\n");
    }
}
