package com.example.daily.room
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity (tableName="logs")
data class Logs(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="log_id")
    var id: Int,

    @ColumnInfo(name = "selected_date")
    var selected_Date: String,

    @ColumnInfo(name= "age")
    var age: String,

    @ColumnInfo(name= "weight")
    var weight: String,

    @ColumnInfo(name= "height")
    var height: String,

    @ColumnInfo(name= "exercise")
    var exercise: String,

    @ColumnInfo(name= "cardio")
    var cardio: String?,

    @ColumnInfo(name= "steps")
    var steps: String?,


    );
