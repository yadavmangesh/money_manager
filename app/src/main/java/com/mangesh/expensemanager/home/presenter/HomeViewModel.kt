package com.mangesh.expensemanager.home.presenter

import android.util.Log
import androidx.lifecycle.*
import com.mangesh.expensemanager.BaseUiModel
import com.mangesh.expensemanager.di.AppModule
import com.mangesh.expensemanager.home.data.TransactionEntity
import com.mangesh.expensemanager.home.data.TransactionRepository
import com.mangesh.expensemanager.home.model.HomeEventModel
import com.mangesh.expensemanager.home.model.HomeReactionModel
import com.mangesh.expensemanager.home.model.TransactionSummaryUiModel
import com.mangesh.expensemanager.home.model.TransactionType
import com.mangesh.expensemanager.home.ui.viewholder.TransactionItemViewHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transactionMapper: TransactionMapper,
    private val transactionRepository: TransactionRepository,
) : ViewModel() {

    private var _reactionMutableLiveData = MutableLiveData<HomeReactionModel>()
    val reactionLiveData: LiveData<HomeReactionModel> = _reactionMutableLiveData

    val transactionList = transactionRepository.transactionList.asLiveData()

    fun onViewEvent(event: HomeEventModel) {
        when (event) {
            is HomeEventModel.FabBtnClick -> {
                _reactionMutableLiveData.postValue(HomeReactionModel.ShowTransactionDialog)
            }
        }
    }

    fun onViewClicked(model:BaseUiModel){

        viewModelScope.launch {
            when (model) {

                is TransactionItemViewHolder.DeleteBtnClick -> {
                    transactionRepository.deleteTransaction(model.id)
                }
            }
        }
    }

    fun mapListDataForAdapter(dbList: List<TransactionEntity>?) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = dbList?.let { it1 -> transactionMapper.mapEntityToUiModel(it1) }
            val summaryUiModel = TransactionSummaryUiModel(
                totalExpense = dbList?.sumBy { if (it.type == TransactionType.TRANSACTION_EXPENSE.value) it.amount else 0 }
                    ?: 0,
                totalIncome = dbList?.sumBy { if (it.type == TransactionType.TRANSACTION_INCOME.value) it.amount else 0 }
                    ?: 0,
            )
            val uiList = mutableListOf<BaseUiModel>()
            uiList.add(0, summaryUiModel)
            list?.let { uiList.addAll(it) }
            _reactionMutableLiveData.postValue(HomeReactionModel.UpdateList(uiList))

        }
    }
}