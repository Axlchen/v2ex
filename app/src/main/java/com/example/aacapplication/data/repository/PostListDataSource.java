package com.example.aacapplication.data.repository;

import android.util.Log;

import com.example.aacapplication.data.entity.Post;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListDataSource extends PageKeyedDataSource<Integer, Post> {

    private static final String TAG = "PostListDataSource";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Post> callback) {
        Log.d(TAG, "loadInitial " + params.placeholdersEnabled + " " + params.requestedLoadSize);
        PostRepository.getInstant().getPostService().getPostList().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResult(response.body(), 0, 2);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Post> callback) {
        Log.d(TAG, "loadBefore " + params.key + " " + params.requestedLoadSize);

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Post> callback) {
        Log.d(TAG, "loadAfter " + params.key + " " + params.requestedLoadSize);
    }

    public static class Factory extends PageKeyedDataSource.Factory<Integer, Post> {
        @NonNull
        @Override
        public DataSource<Integer, Post> create() {
            return new PostListDataSource();
        }
    }
}