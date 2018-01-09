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
        //System.out.println(textStart + " - " + textEnd);  
        return text;
    }

    public StringBuilder plainText(String t) {
        boolean between = false; //plainText() -> Wir wollen das Zeug zwischen den Links nicht!
        System.out.println("Raw: " + t);
        Util util = new Util();
        StringBuilder text = new StringBuilder(t);
        String tag = "<a"; String endtag = "</a>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<table"; endtag = "</table";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<div"; endtag = "</div>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<li"; endtag = "</li>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<span"; endtag = "</span>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<td"; endtag = "</td>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<tr"; endtag = "</tr>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<dd"; endtag = "</dd>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<img"; endtag = "</img>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<i"; endtag = "</i>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<ul"; endtag = "</ul>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<b"; endtag = "</b>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<p"; endtag = "</p>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        tag = "<ol"; endtag = "</ol>";
        text = util.removeHtmlTags(text, tag, endtag, between);
        
        String Zweckstring = text.toString();
        //TODO: REMOVE EDIT SHIT text = new StringBuilder(Zweckstring.replaceAll("[Bearbeiten | Quelltext bearbeiten]", "Penis"));
        
        return text;
    }
}
