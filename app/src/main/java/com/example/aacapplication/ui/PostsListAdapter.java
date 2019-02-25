package com.example.aacapplication.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aacapplication.data.entity.Post;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostViewHolder> {

    private List<Post> mPostList;

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(mPostList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mPostList == null ? 0 : mPostList.size();
    }

    public void setPostList(final List<Post> postList) {
        if (mPostList == null) {
            mPostList = postList;
            notifyItemRangeInserted(0, postList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mPostList.size();
                }

                @Override
                public int getNewListSize() {
                    return postList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    Post old = mPostList.get(oldItemPosition);
                    Post post = postList.get(newItemPosition);
                    return old.id == post.id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Post old = mPostList.get(oldItemPosition);
                    Post post = postList.get(newItemPosition);
                    return old.id == post.id
                            && Objects.equals(old.title, post.title)
                            && Objects.equals(old.content, post.content);
                }
            });
            mPostList = postList;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
