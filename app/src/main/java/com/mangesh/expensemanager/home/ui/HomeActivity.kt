package com.mangesh.expensemanager.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mangesh.expensemanager.BaseUiModel
import com.mangesh.expensemanager.ViewOnClickListener
import com.mangesh.expensemanager.databinding.ActivityHomeBinding
import com.mangesh.expensemanager.home.model.HomeEventModel
import com.mangesh.expensemanager.home.model.HomeReactionModel
import com.mangesh.expensemanager.home.presenter.HomeViewModel
import com.mangesh.expensemanager.home.presenter.TransactionAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(),ViewOnClickListener<BaseUiModel> {

    private val binding by lazy { ActivityHomeBinding.inflate(LayoutInflater.from(this)) }

    private val viewModel: HomeViewModel by viewModels()

    private val adapter: TransactionAdapter by lazy { TransactionAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.reactionLiveData.observe(this, this::onReaction)
        viewModel.transactionList.observe(this) {
            viewModel.mapListDataForAdapter(it)
        }
        initViews()
    }

    private fun initViews() {

        binding.apply {
            fabAdd.setOnClickListener {
                viewModel.onViewEvent(HomeEventModel.FabBtnClick)
            }
            rvExpense.adapter = adapter
        }
    }

    private fun onReaction(reaction: HomeReactionModel) {

        when (reaction) {

            is HomeReactionModel.ShowTransactionDialog -> {
                ExpenseDialogFragment().show(supportFragmentManager, ExpenseDialogFragment.TAG)
            }
            is HomeReactionModel.UpdateList -> {
                adapter.submitList(reaction.list)
            }
        }
    }

    override fun onViewClicked(model: BaseUiModel) {
        viewModel.onViewClicked(model)
    }
}