package com.psspl.sampleapparchitecture;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.psspl.sampleapparchitecture.service.model.Profile;
import com.psspl.sampleapparchitecture.view.ui.ProfileFragment;
import com.psspl.sampleapparchitecture.view.ui.ProfileListFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ProfileListFragment fragment = new ProfileListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ProfileListFragment.TAG).commit();
        }

    }

    /**
     * Shows the project detail fragment
     */
    public void show(Profile.DataBean profile) {
        Fragment profileFragment = ProfileFragment.newInstance(profile.getFirst_name(), profile.getLast_name(), profile.getAvatar());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("profile")
                .add(R.id.fragment_container,
                        profileFragment, null).commit();
    }
}
