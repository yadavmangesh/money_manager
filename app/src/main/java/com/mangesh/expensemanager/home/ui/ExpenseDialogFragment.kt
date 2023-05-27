package com.mangesh.expensemanager.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mangesh.expensemanager.databinding.FragmentExpenseDialogBinding
import com.mangesh.expensemanager.home.model.ExpenseDialogEventModel
import com.mangesh.expensemanager.home.model.ExpenseDialogReactionModel
import com.mangesh.expensemanager.home.presenter.ExpenseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExpenseDialogFragment : DialogFragment() {

    private var _binding: FragmentExpenseDialogBinding? = null
    private val binding: FragmentExpenseDialogBinding get() = _binding!!

    private val viewModel: ExpenseViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExpenseDialogBinding.inflate(inflater)
        viewModel.reactionLiveData.observe(this, this::onReaction)
        return binding.root
    }

    private fun getTransactionType() = binding.typeSpinner.selectedItem.toString()
    private fun getDescription() = binding.etDescription.text?.trim().toString()
    private fun getAmount() = binding.etAmount.text?.toString()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            btnAdd.setOnClickListener {
                viewModel.onViewEvent(
                    ExpenseDialogEventModel.AddBtnClicked(
                        getTransactionType(), getDescription(), getAmount()
                    )
                )
            }
        }
    }

    private fun onReaction(reaction: ExpenseDialogReactionModel) {

        when (reaction) {

            is ExpenseDialogReactionModel.TransactionSuccessful -> {
                Toast.makeText(requireContext(), "Transaction Successful", Toast.LENGTH_SHORT)
                    .show()
                dismissAllowingStateLoss()
            }
            is ExpenseDialogReactionModel.ValidationError -> {
                Toast.makeText(requireContext(), getString(reaction.messageRes), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    companion object {
        const val TAG = "ExpenseDialogFragment"
    }
}