package com.psspl.sampleapparchitecture.view.ui;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psspl.sampleapparchitecture.MainActivity;
import com.psspl.sampleapparchitecture.R;
import com.psspl.sampleapparchitecture.databinding.FragmentProfileListBinding;
import com.psspl.sampleapparchitecture.service.model.Profile;
import com.psspl.sampleapparchitecture.view.adapter.ProfileAdapter;
import com.psspl.sampleapparchitecture.view.callback.ProfileClickCallback;
import com.psspl.sampleapparchitecture.viewmodel.ProfileListViewModel;

public class ProfileListFragment extends Fragment {
    public static final String TAG = "ProjectListFragment";
    private ProfileAdapter projectAdapter;
    private FragmentProfileListBinding binding;
    Activity activity;
    ProfileListViewModel viewModel;
    private int page_index = 2;
    private boolean isDataAvailable = true;
    private boolean mIsLoading = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_list, container, false);

        viewModel =ViewModelProviders.of(this).get(ProfileListViewModel.class);
        /**
         * Bind Recycler view with data
         */
        projectAdapter = new ProfileAdapter(profileClickCallback);
        binding.projectList.setAdapter(projectAdapter);
        binding.setIsLoading(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        observeViewModel(viewModel);
    }

    /**
     * This method update observe data and update view model
     * @param viewModel
     */
    private void observeViewModel(final ProfileListViewModel viewModel) {
        // Update the list when the data changes
        binding.projectList.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (mIsLoading)
                    return;
                int visibleItemCount = binding.projectList.getLayoutManager().getChildCount();
                int totalItemCount = binding.projectList.getLayoutManager().getItemCount();
                int pastVisibleItems = ((LinearLayoutManager) binding.projectList.getLayoutManager()).findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    if (isDataAvailable) {
                        viewModel.getProfileList(page_index);
                        page_index++;
                    }
                }
            }
        });

        viewModel.getProjectListObservable().observe(this, new Observer<Profile>() {
            @Override
            public void onChanged(@Nullable Profile profile) {
                if (profile != null) {
                    binding.setIsLoading(false);
                    projectAdapter.setProjectList(profile.getData());
                    if (profile.getTotal_pages() < page_index) {
                        isDataAvailable = false;
                    }
                }
            }
        });
    }

    /**
     * This method handle call back when click any items
     */
    private final ProfileClickCallback profileClickCallback = new ProfileClickCallback() {
        @Override
        public void onClick(Profile.DataBean profile) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(profile);

            }
        }
    };
}
