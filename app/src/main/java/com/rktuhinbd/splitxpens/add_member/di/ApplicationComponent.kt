package com.rktuhinbd.splitxpens.add_member.di

import android.content.Context
import com.rktuhinbd.splitxpens.db.AppDB
import com.rktuhinbd.splitxpens.db.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationComponent {
//    @Singleton
//    @Provides
//    fun providesMyRepo(@ApplicationContext context: Context, service: ApiService): MyRepo {
//        return MyRepo(context, service)
//    }

//    @Singleton
//    @Provides
//    fun providesApiService(@RetrofitForVideo retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }

    @Singleton
    @Provides
    fun providesRoomDBService(@ApplicationContext context: Context): RoomDao {
        return AppDB.getDatabase(context).roomDao()
    }
}