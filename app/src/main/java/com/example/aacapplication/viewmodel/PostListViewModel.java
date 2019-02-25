package com.example.aacapplication.viewmodel;


import android.app.Application;

import com.example.aacapplication.data.entity.Post;
import com.example.aacapplication.data.repository.PostRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class PostListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Post>> mPostList;
    private PostRepository mRepository;

    public PostListViewModel(@NonNull Application application) {
        super(application);
        mRepository = PostRepository.getInstant();
        mPostList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Post>> getPostList() {
        mRepository.getPostList(mPostList);
        return mPostList;
    }
}