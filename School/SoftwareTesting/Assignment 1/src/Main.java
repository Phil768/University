import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL(" https://api.marketalertum.com/Alert");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        byte[] out = "{\"alertType\":\"6\",\"heading\":\"Jumper Windows 11 Laptop\",\"description\":\"Test\",\"url\":\"https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth\", \"imageurl\":\"https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg\", \"postedBy\":\"01150cc0-eff8-4df5-a549-eb18cf7c6184\", \"priceInCents\":\"24999\"}" .getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }


    }
}