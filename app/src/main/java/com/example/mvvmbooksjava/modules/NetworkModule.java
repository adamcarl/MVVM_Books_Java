package com.example.mvvmbooksjava.modules;

import com.example.mvvmbooksjava.Constants;
import com.example.mvvmbooksjava.api.BookSearchService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public static BookSearchService provideBookSearchService(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BOOK_SEARCH_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(BookSearchService.class);
    }
}
