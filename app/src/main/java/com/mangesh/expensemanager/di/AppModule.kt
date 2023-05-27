package com.mangesh.expensemanager.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mangesh.expensemanager.database.TransactionDatabase
import com.mangesh.expensemanager.home.data.TransactionDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    companion object {

        @Provides
        fun provideTransactionDao(database: TransactionDatabase): TransactionDao {
            return database.transactionDao()
        }

        @IoDispatcher
        @Provides
        fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @Singleton
        fun provideAppDatabase(
            appContext: Application
        ): TransactionDatabase {
            return Room.databaseBuilder(
                appContext,
                TransactionDatabase::class.java,
                TransactionDatabase.DB_NAME
            ).fallbackToDestructiveMigration()
                .build()
        }
    }

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class IoDispatcher
}