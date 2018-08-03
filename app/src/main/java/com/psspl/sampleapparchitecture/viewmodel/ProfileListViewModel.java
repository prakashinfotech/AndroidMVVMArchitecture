package com.psspl.sampleapparchitecture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.psspl.sampleapparchitecture.service.api.ApiClient;
import com.psspl.sampleapparchitecture.service.model.Profile;

import java.util.List;

public class ProfileListViewModel extends ViewModel {
    private MutableLiveData<Profile> projectListObservable;

    public ProfileListViewModel() {
        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = new MutableLiveData<>();
        ApiClient.getInstance().getProfileList(projectListObservable, 0);
    }

    public void getProfileList(int i) {
        ApiClient.getInstance().getProfileList(projectListObservable, i);
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<Profile> getProjectListObservable() {
        return projectListObservable;
    }
}
