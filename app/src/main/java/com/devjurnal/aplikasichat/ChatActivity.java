package com.devjurnal.aplikasichat;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity {

    SharedPreferences myPreference;
    EditText edtNamaChat,edtIsiChat;

    SharedPreferences.Editor spEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initView();
        myPreference= getSharedPreferences(MainActivity.ADD_PREF,MODE_PRIVATE);
        spEdit = myPreference.edit();
    }

    private void initView() {
        edtIsiChat = findViewById(R.id.edtIsiChat);
        edtNamaChat = findViewById(R.id.edtNamaChat);

    }

    public void tambahpesan(View view) {
        JSONObject jsonObject = new JSONObject();


        // Get Data from Input
        String nama = edtNamaChat.getText().toString();
        String pesan = edtIsiChat.getText().toString();
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
        String waktu = sdf.format(c1.getTime());

        try{
            jsonObject.put("Nama", nama);
            jsonObject.put("Pesan", pesan);
            jsonObject.put("Waktu", waktu);
            // TODO NO IMAGE
        }catch (JSONException e) {
            e.printStackTrace();
        }

        if (myPreference.contains("data"))
        {
            String prefString = myPreference.getString("data","No_Content");

            try
            {
                // TODO Put jsonObject to array
                JSONArray jsonArray = new JSONArray(prefString);
                jsonArray.put(jsonObject);
                spEdit.putString("data", jsonArray.toString());
                spEdit.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
        {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            spEdit.putString("data", jsonArray.toString());
            spEdit.apply();
        }
        finish();
    }
}
