import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class main {

    public static void main(String[] args) {
        boolean enable = false;
        URL url = null;
        try {
            url = new URL("https://raw.githubusercontent.com/PvPNiK/Test/main/ttt.txt");
           // URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            AtomicReference<String> line = new AtomicReference<>("emb");

            in.lines().forEach(string -> {
                System.out.println("1: " + string);
                if (string.startsWith(line.get())) {
                    line.set(string);
                    return;
                }
            });

            System.out.println(line);

            enable = Boolean.valueOf(line.get().split(": ")[1]);

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("enable: " + enable);
    }


}
