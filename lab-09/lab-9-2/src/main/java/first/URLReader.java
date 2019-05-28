package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLReader {

    public static void main(String[] args) {
        URL url;
        String urlName = "http://www.s13.ru";
        try {
            url = new URL(urlName);
            try (BufferedReader d = new BufferedReader (new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = d.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
