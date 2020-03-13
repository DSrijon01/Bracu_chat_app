package com.example.appforbrac;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAcessorAdapter extends FragmentPagerAdapter {


    public TabsAcessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                Chats_Fragments chatsFragments = new Chats_Fragments();
                return chatsFragments;

            case 1:

                Groups_Fragments groupsFragments = new Groups_Fragments();
                return groupsFragments;

            case 2:

                People_Fragments peopleFragments = new People_Fragments();
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
