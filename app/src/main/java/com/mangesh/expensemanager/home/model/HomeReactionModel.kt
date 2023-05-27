package com.mangesh.expensemanager.home.model

import com.mangesh.expensemanager.BaseUiModel

sealed class HomeReactionModel {

    object ShowTransactionDialog : HomeReactionModel()

    data class UpdateList(val list: List<BaseUiModel>) : HomeReactionModel()
}