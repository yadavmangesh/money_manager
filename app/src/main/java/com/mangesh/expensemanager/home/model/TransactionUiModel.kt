package com.mangesh.expensemanager.home.model

import com.mangesh.expensemanager.BaseUiModel

data class TransactionDateUiModel(val date: String) : BaseUiModel

data class TransactionUiModel(
    val id: Int?,
    val description: String,
    val date: String,
    val type: String,
    val amount: String
) : BaseUiModel

data class TransactionDataUiModel(
    val date: String,
    val list: List<TransactionUiModel>?
) : BaseUiModel

data class TransactionSummaryUiModel(
    val totalExpense: Int,
    val totalIncome: Int,
) : BaseUiModel