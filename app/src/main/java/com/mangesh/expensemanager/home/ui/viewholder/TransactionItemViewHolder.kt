package com.mangesh.expensemanager.home.ui.viewholder

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mangesh.expensemanager.BaseUiModel
import com.mangesh.expensemanager.R
import com.mangesh.expensemanager.ViewOnClickListener
import com.mangesh.expensemanager.databinding.LayoutExpenseIncomeItemBinding
import com.mangesh.expensemanager.home.model.TransactionType
import com.mangesh.expensemanager.home.model.TransactionUiModel

class TransactionItemViewHolder(
    private val listener: ViewOnClickListener<BaseUiModel>,
    private val binding: LayoutExpenseIncomeItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(model: TransactionUiModel?) {
        binding.apply {
            Log.d("TAG", "bindData: $model")
            tvAmount.text = "$ ${model?.amount}"
            tvAmount.setTextColor(
                ContextCompat.getColor(
                    tvAmount.context,
                    if (model?.type == TransactionType.TRANSACTION_EXPENSE.value) R.color.red else R.color.green
                )
            )
            tvTitle.text = model?.description

            ivDelete.setOnClickListener {
                if (model != null) listener.onViewClicked(DeleteBtnClick(model.id!!))
            }
        }
    }

    data class DeleteBtnClick(val id: Int) : BaseUiModel
}