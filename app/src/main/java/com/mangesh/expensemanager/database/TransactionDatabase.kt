package com.mangesh.expensemanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mangesh.expensemanager.util.DateConverter
import com.mangesh.expensemanager.home.data.TransactionDao
import com.mangesh.expensemanager.home.data.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1 , exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        const val DB_NAME = "transaction_db"
    }

}

