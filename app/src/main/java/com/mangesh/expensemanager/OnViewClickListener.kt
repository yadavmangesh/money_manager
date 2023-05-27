package com.mangesh.expensemanager

interface ViewOnClickListener<in T> {
    fun onViewClicked(model: T)
}