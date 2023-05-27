package com.mangesh.expensemanager.home.presenter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.mangesh.expensemanager.BaseUiModel
import com.mangesh.expensemanager.home.model.TransactionDataUiModel
import com.mangesh.expensemanager.home.model.TransactionDateUiModel
import com.mangesh.expensemanager.home.model.TransactionSummaryUiModel
import com.mangesh.expensemanager.home.model.TransactionUiModel

class TransactionDiff : DiffUtil.ItemCallback<BaseUiModel>() {
    override fun areItemsTheSame(oldItem: BaseUiModel, newItem: BaseUiModel): Boolean {
        return when {
            oldItem is TransactionDateUiModel && newItem is TransactionDateUiModel -> true
            oldItem is TransactionDataUiModel && newItem is TransactionDataUiModel -> true
            oldItem is TransactionUiModel && newItem is TransactionUiModel -> true
            oldItem is TransactionSummaryUiModel && newItem is TransactionSummaryUiModel -> true
            else -> oldItem == newItem
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseUiModel, newItem: BaseUiModel): Boolean {
        return when {
            oldItem is TransactionDateUiModel && newItem is TransactionDateUiModel -> oldItem == newItem
            oldItem is TransactionUiModel && newItem is TransactionUiModel -> oldItem == newItem
            oldItem is TransactionSummaryUiModel && newItem is TransactionSummaryUiModel -> oldItem == newItem
            else -> oldItem == newItem
        }
    }
}