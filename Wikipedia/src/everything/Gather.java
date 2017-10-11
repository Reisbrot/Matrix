package everything;


public class Gather {
    public String title(String urlContent){
        int titleStart = urlContent.indexOf("<title>") + 7; //+7 because <title> would be part of the output otherwise
        int titleEnd = urlContent.indexOf("</title>")  - 12; // -12 because the title always ends with " – Wikipedia"
        String title = urlContent.substring(titleStart, titleEnd);
        return title;
    }

    public String intrestingText(String urlContent) {
        int textStart = urlContent.indexOf("<h2>"); //h2 is the Standard-Headline for Wikipedia - Subchapters. So the Stuff that comes with the First Subchapter.
        int textEnd = urlContent.indexOf("id=\"Einzelnachweise\""); //There where the Sources start. WHO CARES
        if(textEnd == -1)
            textEnd = urlContent.indexOf("id=\"Weblinks\""); //So if there are no Sources, Just suc in all that information until Weblinks
        System.out.println(textStart + "ss" + textEnd);
        String text = urlContent.substring(textStart, textEnd);
        return text;
    }
}
