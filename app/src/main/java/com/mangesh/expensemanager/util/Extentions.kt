package com.mangesh.expensemanager.util


import java.text.SimpleDateFormat
import java.util.Date


fun Date.dd_mm_yyyy() = SimpleDateFormat("dd MMMM yyyy").format(this)
