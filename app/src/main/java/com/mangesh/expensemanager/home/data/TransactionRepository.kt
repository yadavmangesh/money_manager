package com.mangesh.expensemanager.home.data

import com.mangesh.expensemanager.di.AppModule
import javax.inject.Inject
import com.mangesh.expensemanager.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class TransactionRepository @Inject constructor(
    private val localImpl: TransactionLocalImpl,
    @AppModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun addTransaction(transaction: TransactionEntity): Result<Any> {
        return withContext(ioDispatcher) {
            localImpl.insertTransaction(transaction)
        }
    }

    suspend fun deleteTransaction(transactionId: Int): Result<Any> {
        return withContext(ioDispatcher) {
            localImpl.deleteTransaction(transactionId)
        }
    }

    val transactionList get() = localImpl.transactionList


}