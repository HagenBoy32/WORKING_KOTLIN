package com.droiddataplace.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "transactionsdata")
data class TransactionsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var acct_name: String = "",
    var account_amount_paid: String="",
    var account_date_Paid: Date = Date(),
    var account_category: String="",
    var isFood: String="",
    var isCredit: String="",
    var isEntertainment: String="",
    var isUtility: String="",
    var isHealth: String=""

)