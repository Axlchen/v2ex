package com.example.aacapplication.data.repository;

import android.util.Log;

import com.example.aacapplication.api.PostService;
import com.example.aacapplication.data.Constants;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    private static final String TAG = "PostRepository";

    public static PostRepository getInstant() {
        return InstantHolder.sRepository;
    }

    public PostService getPostService() {
        return InstantHolder.sRetrofit.create(PostService.class);
    }

    static class InstantHolder {
        static final PostRepository sRepository = new PostRepository();
        static OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(getSSLSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        Log.d(TAG, hostname);
                        return true;
                    }
                })
                .build();
        static final Retrofit sRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.API_BASE_URL)
                .client(okHttpClient)
                .build();

        static SSLSocketFactory getSSLSocketFactory() {
            SSLSocketFactory sslFactory = null;
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{new CustomTrustManager()}, new SecureRandom());
                sslFactory = sslContext.getSocketFactory();
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
            return sslFactory;
        }
    }

    static class CustomTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}