package com.mangesh.expensemanager.home.model

sealed class ExpenseDialogEventModel {

    data class AddBtnClicked(
        val transactionType: String,
        val description: String?,
        val amount: String?
    ) : ExpenseDialogEventModel()


}