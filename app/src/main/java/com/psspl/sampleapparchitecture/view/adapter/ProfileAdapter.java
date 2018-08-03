package com.psspl.sampleapparchitecture.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.psspl.sampleapparchitecture.R;
import com.psspl.sampleapparchitecture.databinding.ProfileListItemBinding;
import com.psspl.sampleapparchitecture.service.model.Profile;
import com.psspl.sampleapparchitecture.view.callback.ProfileClickCallback;

import java.util.List;
import java.util.Objects;

/**
 * This is Adapter class to bind list item
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    List<Profile.DataBean> profileList;

    @Nullable
    private final ProfileClickCallback profileClickCallback;

    public ProfileAdapter(@Nullable ProfileClickCallback profileClickCallback) {
        this.profileClickCallback = profileClickCallback;
    }

    public void setProjectList(final List<Profile.DataBean> projectList) {
        if (this.profileList == null) {
            this.profileList = projectList;
            notifyItemRangeInserted(0, projectList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProfileAdapter.this.profileList.size();
                }

                @Override
                public int getNewListSize() {
                    return projectList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProfileAdapter.this.profileList.get(oldItemPosition).getId() ==
                            projectList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Profile.DataBean profile = projectList.get(newItemPosition);
                    Profile.DataBean old = projectList.get(oldItemPosition);
                    return profile.getId() == old.getId()
                            && Objects.equals(profile.getAvatar(), old.getAvatar());
                }
            });
            this.profileList.addAll(projectList);
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProfileListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.profile_list_item,
                        parent, false);

        binding.setCallback(profileClickCallback);

        return new ProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        holder.binding.setProfile(profileList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return profileList == null ? 0 : profileList.size();
    }

    static class ProfileViewHolder extends RecyclerView.ViewHolder {

        final ProfileListItemBinding binding;

        public ProfileViewHolder(ProfileListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
