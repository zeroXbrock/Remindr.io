package com.remindr.io;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by evant_000 on 2/22/14.
 */
public class NetworkStuff {
    // TODO: Don't crash! android.os.NetworkOnMainThreadException
    public void plotFromString(GoogleMap mMap) {
        // Check if we were successful in obtaining the map.

        if (mMap != null) {
            String[] locationList = {"portland", "corvallis"};
            double lat = 0, lng = 0;
            String keyword = "";
            int len = 0;
            len = locationList.length;
            for (int i = 0; i < len; i++) {
                keyword = locationList[i];
                lat = 0;
                lng = 0;
                keyword = "";
                HttpResponse response = null;
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI("https://maps.googleapis.com/maps/api/geocode/json?address=" + keyword + "&sensor=false"));
                    response = client.execute(request);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    //response.getEntity().getContent();
                    String responseString = convertStreamToString(response.getEntity().getContent());
                    JSONObject json = new JSONObject(responseString);
                    lat = json.getDouble("lat");
                    lng = json.getDouble("lng");
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // The Map is verified. It is now safe to manipulate the map.
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lng))
                        .title(keyword));
            }
        }
    }

    public static String convertStreamToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 1024);
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }
}
