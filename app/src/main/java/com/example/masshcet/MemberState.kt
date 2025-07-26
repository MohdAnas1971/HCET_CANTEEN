package com.example.masshcet

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf

data class MemberState(
    val name:MutableState<String> = mutableStateOf(""),
    val description:MutableState<String> = mutableStateOf(""),
    val phoneNo:MutableState<String> = mutableStateOf(""),
    val startDate:MutableState<Long> = mutableLongStateOf(0),
    var endDate:MutableState<Long> = mutableLongStateOf(0),
    val isAmountDone:MutableState<Boolean> = mutableStateOf(false),
    val isMonthDone:MutableState<Boolean> = mutableStateOf(false),
)
