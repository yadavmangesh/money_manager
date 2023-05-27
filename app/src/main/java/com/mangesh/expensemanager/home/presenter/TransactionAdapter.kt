package com.mangesh.expensemanager.home.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mangesh.expensemanager.BaseUiModel
import com.mangesh.expensemanager.ViewOnClickListener
import com.mangesh.expensemanager.databinding.LayoutExpenseIncomeDateItemBinding
import com.mangesh.expensemanager.databinding.LayoutTransactionSummaryItemBinding
import com.mangesh.expensemanager.home.model.TransactionDataUiModel
import com.mangesh.expensemanager.home.model.TransactionSummaryUiModel
import com.mangesh.expensemanager.home.ui.viewholder.TransactionDataViewHolder
import com.mangesh.expensemanager.home.ui.viewholder.TransactionSummaryViewHolder
import javax.inject.Inject

class TransactionAdapter @Inject constructor(
    val listener: ViewOnClickListener<BaseUiModel>
) :
    ListAdapter<BaseUiModel, RecyclerView.ViewHolder>(TransactionDiff()) {

    companion object {
        const val TRANSACTION_SUMMARY = 1
        const val TRANSACTION_ITEM = 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {

            TRANSACTION_SUMMARY -> TransactionSummaryViewHolder(
                LayoutTransactionSummaryItemBinding.inflate(layoutInflater, parent, false)
            )
            TRANSACTION_ITEM -> TransactionDataViewHolder(
                listener,
                LayoutExpenseIncomeDateItemBinding.inflate(layoutInflater, parent, false)
            )
            else -> throw IllegalStateException("No view type found")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            TRANSACTION_SUMMARY -> (holder as TransactionSummaryViewHolder).bindData(currentList[position] as TransactionSummaryUiModel)
            TRANSACTION_ITEM -> (holder as TransactionDataViewHolder).bindData(currentList[position] as TransactionDataUiModel)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is TransactionSummaryUiModel -> TRANSACTION_SUMMARY
            is TransactionDataUiModel -> TRANSACTION_ITEM
            else -> throw IllegalStateException("No view type found")
        }
    }
}