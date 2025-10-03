package com.manishjajoriya.moctale.di

import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.data.remote.MoctaleApi
import com.manishjajoriya.moctale.domain.usecase.ContentUseCase
import com.manishjajoriya.moctale.domain.usecase.ExploreUseCase
import com.manishjajoriya.moctale.domain.usecase.MoctaleApiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

  @Provides
  @Singleton
  fun provideApi(): MoctaleApi =
      Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
          .create(MoctaleApi::class.java)

  @Provides
  @Singleton
  fun provideExploreUseCase(moctaleApi: MoctaleApi) = ExploreUseCase(moctaleApi)

  @Provides
  @Singleton
  fun provideContentUseCase(moctaleApi: MoctaleApi) = ContentUseCase(moctaleApi)

  @Provides
  @Singleton
  fun provideMoctaleApiUseCase(exploreUseCase: ExploreUseCase, contentUseCase: ContentUseCase) =
      MoctaleApiUseCase(exploreUseCase, contentUseCase)
}
