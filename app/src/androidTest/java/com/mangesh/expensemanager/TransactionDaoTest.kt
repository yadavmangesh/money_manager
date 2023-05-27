package com.mangesh.expensemanager

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mangesh.expensemanager.database.TransactionDatabase
import com.mangesh.expensemanager.home.data.TransactionDao
import com.mangesh.expensemanager.home.data.TransactionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@SmallTest
class TransactionDaoTest {
    private lateinit var database: TransactionDatabase
    private lateinit var transactionDao: TransactionDao

    @Before
    fun setUpDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), TransactionDatabase::class.java
        ).allowMainThreadQueries().build()

        transactionDao = database.transactionDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insert_transaction() = runBlocking {
        val transaction = TransactionEntity(
            description = "transaction description",
            id = 1,
            amount = 20,
            type = "Expense",
            transactionDate = Date()
        )
        transactionDao.insertTransaction(transaction)
        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            transactionDao.getTransactionList().collect {
                assertThat(it).contains(transaction)
                latch.countDown()

            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun delete_transaction() = runBlocking {
        val transactionOne = TransactionEntity(
            description = "transaction description 1",
            id = 3,
            amount = 10,
            type = "Expense",
            transactionDate = Date()
        )
        val transactionTwo = TransactionEntity(
            description = "transaction description 2",
            id = 5,
            amount = 30,
            type = "Income",
            transactionDate = Date()
        )

        transactionDao.insertTransaction(transactionOne)
        transactionDao.insertTransaction(transactionTwo)

        transactionDao.deleteTransaction(transactionTwo.id)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            transactionDao.getTransactionList().collect {
                assertThat(it).doesNotContain(transactionTwo)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()

    }
}