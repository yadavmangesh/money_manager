package com.mangesh.expensemanager.home.presenter

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangesh.expensemanager.R
import com.mangesh.expensemanager.home.data.TransactionEntity
import com.mangesh.expensemanager.home.data.TransactionRepository
import com.mangesh.expensemanager.home.model.ExpenseDialogEventModel
import com.mangesh.expensemanager.home.model.ExpenseDialogReactionModel
import com.mangesh.expensemanager.home.model.TransactionType
import com.mangesh.expensemanager.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(private val transactionRepository: TransactionRepository) :
    ViewModel() {

    private var _reactionMutableLiveData = MutableLiveData<ExpenseDialogReactionModel>()
    val reactionLiveData: LiveData<ExpenseDialogReactionModel> = _reactionMutableLiveData


    fun onViewEvent(event: ExpenseDialogEventModel) {

        viewModelScope.launch {

            when (event) {

                is ExpenseDialogEventModel.AddBtnClicked -> {

                    when {

                        (event.transactionType != TransactionType.TRANSACTION_INCOME.value &&
                                event.transactionType != TransactionType.TRANSACTION_EXPENSE.value) -> {
                            postValidationError(R.string.select_transaction_type_error)
                        }
                        event.description.isNullOrBlank() -> {
                            postValidationError(R.string.empty_description_error)
                        }
                        event.amount.isNullOrBlank() -> {
                            postValidationError(R.string.empty_trasanction_amount_error)
                        }
                        else -> {
                           val result =  transactionRepository.addTransaction(
                                TransactionEntity(
                                    type = event.transactionType,
                                    amount = event.amount.toInt(),
                                    description = event.description,
                                    transactionDate = Date(),
                                )
                            )
                            if(result is Result.Success) {
                                _reactionMutableLiveData.postValue(ExpenseDialogReactionModel.TransactionSuccessful)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun postValidationError(@StringRes messageRes: Int) {
        _reactionMutableLiveData.postValue(
            ExpenseDialogReactionModel.ValidationError(messageRes)
        )
    }

    companion object {
        const val TAG = "ExpenseViewModel"
    }
}