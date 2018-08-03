package com.psspl.sampleapparchitecture.view.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psspl.sampleapparchitecture.R;
import com.psspl.sampleapparchitecture.databinding.FragmentProfileBinding;
import com.psspl.sampleapparchitecture.service.model.Profile;
import com.psspl.sampleapparchitecture.viewmodel.ProfileListViewModel;
import com.psspl.sampleapparchitecture.viewmodel.ProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private static final String KEY_PROJFILE_ID = "profile_id";
    private String firstName;
    private String lastName;
    private String profileImagePath;
    private ProfileViewModel viewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    private static final String ARG_FIRSTNAME = "firstname";
    private static final String ARG_LASTNAME = "lastname";
    private static final String ARG_PROFILE = "profile";
    FragmentProfileBinding binding;

    public static android.support.v4.app.Fragment newInstance(String firstname, String lastname, String imagepath) {
        android.support.v4.app.Fragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FIRSTNAME, firstname);
        args.putString(ARG_LASTNAME, lastname);
        args.putString(ARG_PROFILE, imagepath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firstName = getArguments().getString(ARG_FIRSTNAME);
            lastName = getArguments().getString(ARG_LASTNAME);
            profileImagePath = getArguments().getString(ARG_PROFILE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        binding.setProfile(getUserData());
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        viewModel.setImageUrl(profileImagePath);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private Profile.DataBean getUserData() {
        Profile.DataBean dataBean = new Profile.DataBean();
        dataBean.setFirst_name(firstName);
        dataBean.setLast_name(lastName);
        dataBean.setAvatar(profileImagePath);

        return dataBean;
    }

    public static ProfileFragment forProject(String profileID) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(KEY_PROJFILE_ID, profileID);
        fragment.setArguments(args);
        return fragment;
    }
}
