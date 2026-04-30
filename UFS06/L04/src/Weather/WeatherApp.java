package Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;

public class WeatherApp {
    private double lat, lon;

    public WeatherApp(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public static void main(String[] args) {
        WeatherApp milano = new WeatherApp(45.464664, 9.188540);
        WeatherApp roma = new WeatherApp(41.902782, 12.496366);
        WeatherApp londra = new WeatherApp(51.509865, -0.118092);

        milano.writeInfo();
        System.out.println();
        roma.writeInfo();
        System.out.println();
        londra.writeInfo();
    }

    private Map<String, Object> getInfo(){
        Map<String, Object> info = new LinkedHashMap<>();
        URI uri;

        try {
            URIBuilder uriBuilder = new URIBuilder("https://api.openweathermap.org/data/2.5/weather");
            uriBuilder.addParameter("lat", String.valueOf(lat));
            uriBuilder.addParameter("lon", String.valueOf(lon));
            uriBuilder.addParameter("appid", "fcbaf03c621d2bff790e61e606b9bd95");
            uriBuilder.addParameter("units", "metric");
            uriBuilder.addParameter("lang", "it"); 

            uri = uriBuilder.build();
        } catch (Exception e) {
            return null;
        }

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri);

        try(CloseableHttpResponse response = client.execute(request)){
            if(response.getStatusLine().getStatusCode() == 200){
                InputStream is = response.getEntity().getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer sb = new StringBuffer();
                String s;

                while((s=br.readLine()) != null)
                    sb.append(s);

                JSONObject allInfo = new JSONObject(sb.toString());

                info.put("Città", allInfo.getString("name"));
                info.put("Temperatura", allInfo.getJSONObject("main").getDouble("temp"));
                info.put("Umidità", allInfo.getJSONObject("main").getDouble("humidity"));
                info.put("Meteo", allInfo.getJSONArray("weather").getJSONObject(0).getString("description"));

                return info;
            } else {
                throw new Exception("Codice: " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void writeInfo() {
        Map<String, Object> info = getInfo();

        if(info != null){
            info.forEach((k, v) -> {
                System.out.println(k + ": " + v);
            });
        } else {
            System.err.println("Errore");
        }
    }
}
