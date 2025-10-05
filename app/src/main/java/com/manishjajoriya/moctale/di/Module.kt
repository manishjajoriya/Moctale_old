package com.manishjajoriya.moctale.di

import com.manishjajoriya.moctale.BuildConfig
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.data.remote.MoctaleApi
import com.manishjajoriya.moctale.domain.usecase.ContentUseCase
import com.manishjajoriya.moctale.domain.usecase.ExploreUseCase
import com.manishjajoriya.moctale.domain.usecase.MoctaleApiUseCase
import com.manishjajoriya.moctale.domain.usecase.ReviewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

  @Provides
  @Singleton
  fun provideClient() =
    OkHttpClient.Builder()
      .addInterceptor { chain ->
        val request =
          chain
            .request()
            .newBuilder()
            .addHeader("Cookie", "auth_token=${BuildConfig.AUTH_TOKEN}")
            .build()
        chain.proceed(request)
      }
      .build()

  @Provides
  @Singleton
  fun provideApi(client: OkHttpClient): MoctaleApi =
      Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
        .client(client)
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
  fun provideReviewsUseCase(moctaleApi: MoctaleApi) = ReviewsUseCase(moctaleApi)

  @Provides
  @Singleton
  fun provideMoctaleApiUseCase(
    exploreUseCase: ExploreUseCase,
    contentUseCase: ContentUseCase,
    reviewsUseCase: ReviewsUseCase,
  ) = MoctaleApiUseCase(exploreUseCase, contentUseCase, reviewsUseCase)
}
