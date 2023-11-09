package com.example.bt_buoi4;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchData extends AsyncTask<Void, Void, String> {
    private PhotoAdapter adapter;
    public FetchData(PhotoAdapter adapter) {
        this.adapter = adapter;
    }
    private List<Photo> photoList = new ArrayList<>();

    private final String apiUrl = "http://192.168.9.103/api_buoi4.php"; // Đặt URL API PHP ở đây



    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            Log.d("AsyncTask", "Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            reader.close();
            urlConnection.disconnect();

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String jsonData) {
        // Xử lý dữ liệu JSON ở đây
        // Gọi hàm để hiển thị dữ liệu lên RecyclerView
        if (jsonData != null) {
            try {
                // Chuyển đổi chuỗi JSON thành mảng JSON
                JSONArray jsonArray = new JSONArray(jsonData);

                // Xóa dữ liệu cũ trong danh sách
                photoList.clear();

                // Lặp qua mảng JSON để chuyển đổi thành các đối tượng Photo và thêm vào danh sách
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int albumId = jsonObject.getInt("albumId");
                    int id = jsonObject.getInt("id");
                    String title = jsonObject.getString("title");
                    String url = jsonObject.getString("url");
                    String thumbnailUrl = jsonObject.getString("thumbnailUrl");

                    Photo photo = new Photo(albumId, id, title, url, thumbnailUrl);
                    photoList.add(photo);
                }

                // Thông báo cho Adapter biết rằng dữ liệu đã thay đổi
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            // Xử lý trường hợp jsonData null hoặc không phải là định dạng JSON
            // Có thể hiển thị thông báo lỗi hoặc thực hiện xử lý phù hợp
            Log.d("AsyncTask", "onPostExecute: " + jsonData);
        }
    }
}
