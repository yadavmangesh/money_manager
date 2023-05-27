package com.mangesh.expensemanager.home.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val type: String, // expense , income
    val amount: Int,
    val description: String,
    val transactionDate: Date
) {

}