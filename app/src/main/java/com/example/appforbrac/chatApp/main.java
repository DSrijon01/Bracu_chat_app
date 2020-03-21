package com.example.appforbrac.chatApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.appforbrac.LoginActivity;
import com.example.appforbrac.R;
import com.example.appforbrac.chatApp.fragments.SettingsActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static com.example.appforbrac.R.menu.bottom_navigation;

public class main extends AppCompatActivity {


    private Toolbar mToolbar;
    private ViewPager myViewpager;
    private TabLayout myTabLayout;
    private TabsAcessorAdapter myTabsAccessorAdapter;
    FirebaseAuth auth;
    private DatabaseReference Rootref= FirebaseDatabase.getInstance().getReference();
    String myStr;


    private void test() {

        Intent i = getIntent();
        myStr = i.getStringExtra("name");

        HashMap<String, Object> profileMap = new HashMap<>();
        //profileMap.put("name", setUserName);
        profileMap.put("status", myStr);
        Rootref.child("intent").updateChildren(profileMap);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);


        //  mToolbar=(Toolbar) findViewById(R.id.main_page_toolbar);
//        setSupportActionBar(mToolbar);
        // getSupportActionBar().setTitle("BRACU");

        auth = FirebaseAuth.getInstance();

        test();

        myViewpager = findViewById(R.id.main_tabs_pager);
        myTabsAccessorAdapter = new TabsAcessorAdapter(getSupportFragmentManager(),myStr);
        myViewpager.setAdapter(myTabsAccessorAdapter);
        myTabLayout = findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewpager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate (R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);

         if (item.getItemId()==R.id.main_logout_option){

             auth.signOut();
             SendUserToLoginActivity();

         }

        if (item.getItemId()==R.id.main_Settings_option) {

             SendUserToSettingsActivity();
        }

        if (item.getItemId()==R.id.main_find_People) {


        }
        return true;

    }

    private void SendUserToLoginActivity()
    {
        Intent loginIntent = new Intent(main.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);

    }
    private void SendUserToSettingsActivity() {
        Intent SettingIntent = new Intent(main.this, SettingsActivity.class);
        SettingIntent.putExtra("name",myStr);
        startActivity(SettingIntent);
    }
}
