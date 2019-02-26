package com.example.aacapplication.viewmodel;


import android.app.Application;

import com.example.aacapplication.data.entity.Post;
import com.example.aacapplication.data.repository.PostListDataSource;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class PostListViewModel extends AndroidViewModel {

    public PostListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<PagedList<Post>> getPostList() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .build();
        return new LivePagedListBuilder<>(new PostListDataSource.Factory(), config)
                .build();
    }
}