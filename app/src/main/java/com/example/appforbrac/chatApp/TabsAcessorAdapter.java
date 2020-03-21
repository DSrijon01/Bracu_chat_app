package com.example.appforbrac.chatApp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appforbrac.chatApp.fragments.Chats_Fragments;
import com.example.appforbrac.chatApp.fragments.Groups_Fragments;
import com.example.appforbrac.chatApp.fragments.People_Fragments;

public class TabsAcessorAdapter extends FragmentPagerAdapter {

    String myStr;
    public Bundle bundle = new Bundle();


    public TabsAcessorAdapter(FragmentManager fm, String myStr) {
        super(fm);
        this.myStr=myStr;
    }

    @Override
    public Fragment getItem(int position) {
        bundle.putString("name", myStr);
        switch (position) {
            case 0:

                Chats_Fragments chatsFragments = new Chats_Fragments();
                chatsFragments.setArguments(bundle);
                return chatsFragments;

            case 1:

                Groups_Fragments groupsFragments = new Groups_Fragments();
                groupsFragments.setArguments(bundle);
                return groupsFragments;

            case 2:


                People_Fragments peopleFragments = new People_Fragments();
                peopleFragments.setArguments(bundle);
                return peopleFragments;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:


                return "Chats";

            case 1:


                return "Groups";

            case 2:


                return "People";

            default:
                return null;

        }
    }

}
