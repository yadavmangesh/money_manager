package com.mangesh.expensemanager.home.ui.viewholder


import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.mangesh.expensemanager.BaseUiModel
import com.mangesh.expensemanager.ViewOnClickListener
import com.mangesh.expensemanager.databinding.LayoutExpenseIncomeDateItemBinding
import com.mangesh.expensemanager.home.model.TransactionDataUiModel
import com.mangesh.expensemanager.home.model.TransactionDateUiModel
import com.mangesh.expensemanager.home.presenter.TransactionItemAdapter

class TransactionDataViewHolder(
    private val listener: ViewOnClickListener<BaseUiModel>,
    private val binding: LayoutExpenseIncomeDateItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(model: TransactionDataUiModel) {
        Log.d("", "bindData date: $model")
        binding.tvDate.text = model.date
        binding.rvTransaction.adapter = TransactionItemAdapter(listener,model.list)
    }

}

