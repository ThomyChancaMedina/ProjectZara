package com.example.projeczara.di

import android.app.Application
import androidx.room.Room
import com.example.projeczara.data.database.GetCharacterRoomData
import com.example.projeczara.data.database.ProjectDatabase
import com.example.projeczara.data.server.GetCharacterRemote
import com.example.projeczara.data.server.RemoteService
import com.example.projeczara.datasource.GetCharacterRemoteSource
import com.example.projeczara.datasource.GetCharacterRoomDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        ProjectDatabase::class.java,
        "rickAndMorty"
    ).build()

    @Provides
    @Singleton
    fun provideCharacterDao(db: ProjectDatabase) = db.characterDao()

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): RemoteService {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModules {

    @Binds
    abstract fun bindRoomDataSource(characterRoomDataSource: GetCharacterRoomData): GetCharacterRoomDataSource

    @Binds
    abstract fun bindRemoteDataSource(getCharacterRemoteSource: GetCharacterRemote): GetCharacterRemoteSource
}