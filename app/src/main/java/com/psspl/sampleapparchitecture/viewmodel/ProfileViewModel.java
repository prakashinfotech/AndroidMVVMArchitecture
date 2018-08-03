package com.psspl.sampleapparchitecture.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.psspl.sampleapparchitecture.R;
import com.psspl.sampleapparchitecture.service.api.ApiClient;
import com.psspl.sampleapparchitecture.service.model.Profile;
import com.squareup.picasso.Picasso;

public class ProfileViewModel extends ViewModel {
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imaagUrl) {
        this.imageUrl = imaagUrl;
    }

    public ProfileViewModel() {
    }


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view);
    }

}
