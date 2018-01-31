package everything;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    
    
    public static int stealImagesFrom(String s, int ImageName) throws IOException{
        
       int ImageLocation = 0;
       int ImageTagLocation = 0;
       while(true){
       ImageName++;   
       if(s.indexOf("<img", ImageLocation) == -1) break; else
        ImageTagLocation = s.indexOf("<img", ImageLocation); //Um jedes Bild auf einer Website zu bekommen, wird das nächste Image-Tag gesucht. Also nach dem anderen Bild, beim 1. durchlauf logischerweise 0. LOLUZ
        ImageLocation = s.indexOf("src=", ImageTagLocation) + 5; // +4 Damit der Link zum Bild nicht "src="" und kein // enthält. // - siehe \u00fcbern\u00e4chsten kommentar
        String ImageLink = "https:" + s.substring(ImageLocation, s.indexOf(".png", ImageLocation) + 4); //ImageLink ist an Beginn des Links bis Ende des Links (Auftreten eines ", Ende des Attributes src=); + 4 für .png, das wollen wir, gehört ja zum link
        //Ausserdem das "https", da es sonst kein Protokoll gäbe. Ist schon seltsam gemacht auf deren Seite. Richtige Amateure. Im Gegensatz zu Hakon. Hey Hakon. Freut mich dass du das liest. Viel Erfolg dir noch. Liebe Gr\u00fcssee Corn.

        System.out.println("\n\n\n\n\n\n\n\n\n" + ImageLink);
        
        
         URL url = new URL(ImageLink);
         URLConnection urlConnection = url.openConnection();
         urlConnection.setRequestProperty("User-Agent", "NetscapeNavigator/1.22 (Windows 3.11; i386)");
         try{InputStream in = urlConnection.getInputStream(); 
         ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/" + ImageName +".jpg");
        fos.write(response);
        fos.close();
       } catch(java.io.IOException e){;}}
      return ImageName;
    }
}
