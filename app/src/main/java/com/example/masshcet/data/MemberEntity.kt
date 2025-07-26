package com.example.masshcet.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Member")
data class MemberEntity(
    @PrimaryKey
    val name: String,
    val description: String="",
    val phoneNo: String="",
    val startDate: Long,// Store the date as a Long
    val endDate: Long,// Store the date as a Long
    val isAmountDone:Boolean=false,
    )
