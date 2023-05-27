package com.mangesh.expensemanager.home.presenter

import com.mangesh.expensemanager.BaseUiModel
import com.mangesh.expensemanager.di.AppModule
import com.mangesh.expensemanager.home.data.TransactionEntity
import com.mangesh.expensemanager.home.model.TransactionDataUiModel
import com.mangesh.expensemanager.home.model.TransactionUiModel
import com.mangesh.expensemanager.util.dd_mm_yyyy
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionMapper @Inject constructor(
    @AppModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun mapEntityToUiModel(list: List<TransactionEntity>): List<BaseUiModel> {
        return withContext(ioDispatcher) {
            val uiList = mutableListOf<TransactionUiModel>()
            list.forEach { transactionEntity ->
                val uiModel = TransactionUiModel(
                    id = transactionEntity.id,
                    amount = transactionEntity.amount.toString(),
                    description = transactionEntity.description,
                    date = transactionEntity.transactionDate.dd_mm_yyyy(),
                    type = transactionEntity.type
                )
                uiList.add(uiModel)
            }

            val groupByDate = uiList.groupBy { it.date }

            val combinedList = mutableListOf<BaseUiModel>()
            for (date: String in groupByDate.keys) {
                combinedList.add(
                    TransactionDataUiModel(
                        date = date,
                        list = groupByDate[date]
                    )
                )
            }
            return@withContext combinedList
        }
    }
}