package com.mangesh.expensemanager.home.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mangesh.expensemanager.home.model.TransactionSummaryUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: TransactionEntity): Long

    @Query("DELETE  FROM `transaction` WHERE id=:transactionId")
    fun deleteTransaction(transactionId: Int): Int

    @Query("SELECT * FROM `transaction`")
    fun getTransactionList(): Flow<List<TransactionEntity>>

}