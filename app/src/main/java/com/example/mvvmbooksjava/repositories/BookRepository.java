package com.example.mvvmbooksjava.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmbooksjava.Constants;
import com.example.mvvmbooksjava.api.BookSearchService;
import com.example.mvvmbooksjava.models.VolumesResponse;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {

    private BookSearchService bookSearchService;
    private MutableLiveData<VolumesResponse> volumesResponseMutableLiveData;

    public BookRepository() {
        volumesResponseMutableLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        bookSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(Constants.BOOK_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchService.class);
    }

    public void searchVolumes(String keyword, String author, String apiKey) {
        bookSearchService.searchVolumes(keyword, author, apiKey)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<VolumesResponse> call, @NotNull Response<VolumesResponse> response) {
                        if(response.body() != null) {
                            volumesResponseMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<VolumesResponse> call, @NotNull Throwable t) {
                        volumesResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseMutableLiveData;
    }
}
