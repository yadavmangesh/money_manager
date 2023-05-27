package com.mangesh.expensemanager.home.model

import androidx.annotation.StringRes

sealed class ExpenseDialogReactionModel {

    object TransactionSuccessful : ExpenseDialogReactionModel()

    data class ValidationError(@StringRes val messageRes: Int) : ExpenseDialogReactionModel()
}
