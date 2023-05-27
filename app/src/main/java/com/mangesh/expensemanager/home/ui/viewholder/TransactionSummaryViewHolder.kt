package com.mangesh.expensemanager.home.ui.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.mangesh.expensemanager.databinding.LayoutTransactionSummaryItemBinding
import com.mangesh.expensemanager.home.model.TransactionSummaryUiModel

class TransactionSummaryViewHolder(private val binding: LayoutTransactionSummaryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(model: TransactionSummaryUiModel) {

        binding.apply {
            tvExpense.text = "$ ${model.totalExpense}"
            tvIncome.text = "$ ${model.totalIncome}"
            tvTotal.text = "$ " + (model.totalIncome - model.totalExpense)
            progressHorizontal.max = 100
            if (model.totalIncome == 0 && model.totalExpense == 0 || (model.totalIncome - model.totalExpense) < 0) {
                progressHorizontal.progress = 0
            } else {
                if (model.totalIncome != 0) {
                    val progress = model.totalExpense * 100 / model.totalIncome
                    progressHorizontal.progress = progress
                }
            }

        }
    }
}