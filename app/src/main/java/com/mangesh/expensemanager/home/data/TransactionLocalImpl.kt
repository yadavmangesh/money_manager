package com.mangesh.expensemanager.home.data

import android.util.Log
import com.mangesh.expensemanager.BaseUiModel
import com.mangesh.expensemanager.home.model.TransactionSummaryUiModel
import javax.inject.Inject
import com.mangesh.expensemanager.util.Result
import com.mangesh.expensemanager.util.genericErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class TransactionLocalImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionLocalDataSource {

    override suspend fun insertTransaction(transaction: TransactionEntity): Result<Any> {
        return try {
            val result = transactionDao.insertTransaction(transaction)

            if (result > 0) Result.Success(Unit)
            else Result.Error(Exception("Failed adding transaction"))

        } catch (e: Exception) {
            genericErrorResponse()
        }
    }

    override suspend fun deleteTransaction(transactionId: Int): Result<Any> {
        return try {
            val result = transactionDao.deleteTransaction(transactionId)

            if (result > 0) Result.Success(Unit)
            else Result.Error(Exception("Failed removing transaction"))

        } catch (e: Exception) {
            genericErrorResponse()
        }
    }

    override val transactionList: Flow<List<TransactionEntity>>
        get() = transactionDao.getTransactionList()



}