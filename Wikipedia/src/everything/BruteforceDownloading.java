package everything;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BruteforceDownloading {
    
    public static URL generateURL(StringBuilder urlString){
        URL url = null;
        String URLpath = PasswordGenerator.generateURLPath(ThreadLocalRandom.current().nextInt(3,6), PasswordGenerator.ALPHA + PasswordGenerator.NUMERIC);
        try {
            url = new URL(urlString.append(URLpath).toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(BruteforceDownloading.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Given URL Malformed you <good ol' boy>");
            ex.printStackTrace();
        }
        System.out.println(url);
        return(url);
    }
    
    
    public void stealImagesFrom(URL url){

        //return true;
    }
}
