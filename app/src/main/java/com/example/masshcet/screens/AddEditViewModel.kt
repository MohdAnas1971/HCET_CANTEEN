package com.example.masshcet.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masshcet.MemberState
import com.example.masshcet.data.MemberEntity
import com.example.masshcet.data.MemberRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val memberRepo: MemberRepo
) : ViewModel() {

    private var _state = MutableStateFlow(MemberState())
    var state = _state

    fun addMember() {
        val memberEntity = MemberEntity(
            name = state.value.name.value,
            description = state.value.name.value,
            phoneNo = state.value.phoneNo.value,
            startDate = state.value.startDate.value,
            endDate =state.value.endDate.value ,
            isAmountDone = state.value.isAmountDone.value,
            )
viewModelScope.launch {
    memberRepo.addMember(memberEntity)
}
        state.value = MemberState()
    }
}


