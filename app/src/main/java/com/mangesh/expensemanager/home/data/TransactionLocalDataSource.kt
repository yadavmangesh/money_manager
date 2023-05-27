package com.mangesh.expensemanager.home.data

import androidx.lifecycle.LiveData
import com.mangesh.expensemanager.home.model.TransactionSummaryUiModel
import com.mangesh.expensemanager.util.Result
import kotlinx.coroutines.flow.Flow

interface TransactionLocalDataSource {

    suspend fun insertTransaction(transaction: TransactionEntity): Result<Any>

    suspend fun deleteTransaction(transactionId: Int): Result<Any>

    val transactionList: Flow<List<TransactionEntity>>
}