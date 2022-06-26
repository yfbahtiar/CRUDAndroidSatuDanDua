package com.example.androidcrud.modul;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    public String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()){
            if(first)
                first = false;
            else
                sb.append("&");

            sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return sb.toString();
    }

    public String sendPostRequest(String requestURL, HashMap<String, String> dataParams){
        //membuat url
        URL url;

        //objek stringbuilder untuk menyimpan pesan yang diambil di server
        StringBuilder sb = new StringBuilder();

        try {
            //inisialisasi url
            url = new URL(requestURL);

            //membuat koneksi
            HttpURLConnection koneksi = (HttpURLConnection)url.openConnection();

            //konfigurasi koneksi
            koneksi.setReadTimeout(15000);
            koneksi.setConnectTimeout(15000);
            koneksi.setDoInput(true);
            koneksi.setDoOutput(true);

            //membuat keluaran stream
            OutputStream os = koneksi.getOutputStream();

            //menulis parameter untuk permintaan ke server
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getPostDataString(dataParams));
            writer.flush();
            writer.close();

            os.close();

            //kelola response
            int responseCode = koneksi.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(koneksi.getInputStream()));
                sb = new StringBuilder();

                String response;
                //baca respon dari server
                while((response = bufferedReader.readLine()) != null){
                    sb.append(response);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public String sendGetRequest(String requestURL, String typeRequest){
        StringBuilder sb = new StringBuilder();

        try {
            //inisialisasi url
            URL url = new URL(requestURL+typeRequest);

            //buat koneksi
            HttpURLConnection koneksi = (HttpURLConnection)url.openConnection();

            //kelola respon
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(koneksi.getInputStream()));

            String response;
            //baca response dari server
            while ((response = bufferedReader.readLine()) != null){
                sb.append(response+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public String sendGetRequestParam(String requestURL, String id){
        StringBuilder sb = new StringBuilder();
        try{
            //inisialisasi url
            URL url = new URL(requestURL+id);

            //membuat koneksi
            HttpURLConnection koneksi = (HttpURLConnection)url.openConnection();

            //kelola respon
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(koneksi.getInputStream()));

            String response;
            //baca respon dari server
            while ((response = bufferedReader.readLine()) != null){
                sb.append(response+"\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }
}
