package com.psspl.sampleapparchitecture.view.callback;

import com.psspl.sampleapparchitecture.service.model.Profile;

/**
 * this interface to create click of any item
 */
public interface ProfileClickCallback {
    void onClick(Profile.DataBean profile);
}
