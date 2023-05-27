package com.mangesh.expensemanager.home.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mangesh.expensemanager.BaseUiModel
import com.mangesh.expensemanager.ViewOnClickListener
import com.mangesh.expensemanager.databinding.LayoutExpenseIncomeItemBinding
import com.mangesh.expensemanager.home.model.TransactionUiModel
import com.mangesh.expensemanager.home.ui.viewholder.TransactionItemViewHolder

class TransactionItemAdapter(
    private val listener: ViewOnClickListener<BaseUiModel>,
    val list: List<TransactionUiModel>?
) :
    RecyclerView.Adapter<TransactionItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionItemViewHolder {
        return TransactionItemViewHolder(
            listener,
            LayoutExpenseIncomeItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: TransactionItemViewHolder, position: Int) {
        holder.bindData(list?.get(position))
    }
}