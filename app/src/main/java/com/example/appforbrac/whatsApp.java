package com.example.appforbrac;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.appforbrac.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class whatsApp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavController navController = Navigation.findNavController(this, R.id.nav_chat_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        NavigationUI.setupActionBarWithNavController(this, navController);


        // mToolbar=(Toolbar) findViewById(R.id.main_page_toolbar);
        // setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("BRACU");
}



    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}
