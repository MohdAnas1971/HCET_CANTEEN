package com.example.masshcet.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masshcet.MemberState
import com.example.masshcet.data.MemberEntity
import com.example.masshcet.data.MemberRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MemberLSViewModel @Inject constructor(
    private val memberRepo: MemberRepo
):ViewModel() {

     private var _state = MutableStateFlow(MemberState())
         private set

    private val _allMember = memberRepo.getAllMember().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    var memberState = _state
    val memberList=_allMember

    fun updateMemberState(member: MemberEntity) {
        _state.value = MemberState(
            name = mutableStateOf(member.name),
            description = mutableStateOf(member.description),
            phoneNo = mutableStateOf(member.phoneNo),
            startDate = mutableLongStateOf(member.startDate),
            endDate = mutableLongStateOf(member.endDate),
            isAmountDone = mutableStateOf(member.isAmountDone)
        )
    }


    fun openDefaultDialer(phoneNumber: String, context: Context) {
        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(dialIntent)
    }
}

