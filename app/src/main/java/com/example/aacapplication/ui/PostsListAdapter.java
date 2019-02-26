package com.example.aacapplication.ui;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aacapplication.R;
import com.example.aacapplication.data.entity.Post;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PostsListAdapter extends PagedListAdapter<Post, PostsListAdapter.PostViewHolder> {

    private static DiffUtil.ItemCallback<Post> sItemCallback = new DiffUtil.ItemCallback<Post>() {
        @Override
        public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.id == newItem.id
                    && Objects.equals(oldItem.title, newItem.title)
                    && Objects.equals(oldItem.content, newItem.content);
        }
    };
    //    private List<Post> mPostList;
    private OnItemClickListener mOnItemClickListener;

    PostsListAdapter() {
        super(sItemCallback);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, final int position) {
        final Post post = getItem(position);
        if (post == null) {
            return;
        }
        holder.mPostTitle.setText(post.title);
        holder.mReplyNum.setText(String.valueOf(post.replies));
        holder.mNodeName.setText(post.node.name);
        holder.mPostUserName.setText(post.member.username);
        Glide.with(holder.itemView.getContext())
                .load(Uri.parse("https:" + post.member.avatarNormal))
                .into(holder.mPostUserAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, post);
                }
            }
        });
    }

//    @Override
//    public int getItemCount() {
//        return mPostList == null ? 0 : mPostList.size();
//    }

//    void setPostList(final List<Post> postList) {
//        if (mPostList == null) {
//            mPostList = postList;
//            notifyItemRangeInserted(0, postList.size());
//        } else {
//            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
//                @Override
//                public int getOldListSize() {
//                    return mPostList.size();
//                }
//
//                @Override
//                public int getNewListSize() {
//                    return postList.size();
//                }
//
//                @Override
//                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//                    Post old = mPostList.get(oldItemPosition);
//                    Post post = postList.get(newItemPosition);
//                    return old.id == post.id;
//                }
//
//                @Override
//                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//                    Post old = mPostList.get(oldItemPosition);
//                    Post post = postList.get(newItemPosition);
//                    return old.id == post.id
//                            && Objects.equals(old.title, post.title)
//                            && Objects.equals(old.content, post.content);
//                }
//            });
//            mPostList = postList;
//            diffResult.dispatchUpdatesTo(this);
//        }
//    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(int position, Post post);
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView mPostTitle;
        private TextView mReplyNum;
        private TextView mNodeName;
        private TextView mPostUserName;
        private ImageView mPostUserAvatar;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            mPostTitle = itemView.findViewById(R.id.tv_post_title);
            mReplyNum = itemView.findViewById(R.id.tv_reply_num);
            mPostUserAvatar = itemView.findViewById(R.id.iv_post_user_avatar);
            mPostUserName = itemView.findViewById(R.id.tv_post_user_name);
            mNodeName = itemView.findViewById(R.id.tv_node_name);
        }
    }
}
