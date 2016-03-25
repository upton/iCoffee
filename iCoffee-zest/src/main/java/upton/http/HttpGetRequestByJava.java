package upton.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetRequestByJava {

    public static void main(String[] args) throws IOException {
        HttpURLConnection conn = null;
        InputStream is = null;

        try {
            URL mURL = new URL("http://xiaolai.github.io/alpha/on-learning/#section-6");
            conn = (HttpURLConnection) mURL.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {

                is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer sb = new StringBuffer();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String html = sb.toString();
                
                System.out.println(html);
            } else {
                System.out.println("error responseCode: " + responseCode);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

}
