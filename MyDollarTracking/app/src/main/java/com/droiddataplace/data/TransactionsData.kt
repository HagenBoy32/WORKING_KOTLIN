package com.droiddataplace.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TransactionsData(
    @PrimaryKey var id: UUID = UUID.randomUUID(),
    var acct_name: String = "",
    var account_amount_paid: String = "",
    var account_date_Paid: Date = Date(),
    var account_category: String = "",
    var isFood: String ="",
    var isCredit: String ="",
    var isEntertainment: String ="",
    var isUtility: String ="",
    var isHealth:  String ="",
    var isPaid: String ="",
    var isRemoved: String
)