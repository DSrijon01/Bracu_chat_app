package com.example.appforbrac.chatApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.appforbrac.R;
import com.google.android.material.tabs.TabLayout;

public class main extends AppCompatActivity {


    private Toolbar mToolbar;
    private ViewPager myViewpager;
    private TabLayout myTabLayout;
    private TabsAcessorAdapter myTabsAccessorAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);


      //  mToolbar=(Toolbar) findViewById(R.id.main_page_toolbar);
//        setSupportActionBar(mToolbar);
       // getSupportActionBar().setTitle("BRACU");


        myViewpager = (ViewPager) findViewById(R.id.main_tabs_pager);
        myTabsAccessorAdapter = new TabsAcessorAdapter(getSupportFragmentManager());
        myViewpager.setAdapter(myTabsAccessorAdapter);
        myTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewpager);




    }

}
