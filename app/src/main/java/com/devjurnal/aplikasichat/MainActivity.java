package com.devjurnal.aplikasichat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    public static String ADD_PREF = "add_pref";
    ChatAdapter adapter;
    RecyclerView recyclerView;
    SharedPreferences myPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        myPreference = getSharedPreferences(ADD_PREF,MODE_PRIVATE);
        String prefString = myPreference.getString("data","No_Content");

        try{
            JSONArray jsonArray = new JSONArray(prefString);
            adapter = new ChatAdapter(jsonArray);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        recyclerView=  findViewById(R.id.mRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    public void chatActivity(View view) {
        startActivity(new Intent(MainActivity.this, ChatActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId())
        {
            case  R.id.menuClear:
                SharedPreferences sharedPreferences = getSharedPreferences(ADD_PREF,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                onRestart();
                break;

        }

        return true;
    }
}
