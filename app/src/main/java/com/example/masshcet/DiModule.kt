package com.example.masshcet

import android.app.Application
import androidx.room.Room
import com.example.masshcet.data.MemberDatabase
import com.example.masshcet.data.MemberRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    @Singleton
    fun provideDatabase(applicationContext: Application): MemberDatabase{
        return Room.databaseBuilder(
            context = applicationContext,
            klass = MemberDatabase::class.java,
            name = "mass_database.sql"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMemberRepo(memberDB:MemberDatabase):MemberRepo{
        return MemberRepo(memberDB.memberDao())
    }
}