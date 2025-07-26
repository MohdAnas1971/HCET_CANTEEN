package com.example.masshcet.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.masshcet.MemberState
import com.example.masshcet.data.MemberEntity
import com.example.masshcet.data.MemberRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MemberDetailedScreenViewModel @Inject constructor(
    private val memberRepo: MemberRepo,

):ViewModel(){
    private var _state = MutableStateFlow(MemberState())
    val state=_state

    //For delete Function
    private val memberToDelete= mutableStateOf( MemberEntity(
        name ="" ,
        endDate =0 , isAmountDone =false ,
        description ="" , phoneNo ="" ,
        startDate =0 , )
    )

    
    fun getOneMember(name: String){
      viewModelScope.launch(Dispatchers.IO) {
          val data= memberRepo.getOneMember(name = name)
          memberToDelete.value=data //For delete Function
           memberToState(data)
      }

    }


    fun deleteMember(){
        viewModelScope.launch() {
            memberRepo.deleteMember(memberToDelete.value)
        }
    }

   private fun memberToState(data:MemberEntity){
       memberToDelete.value=data
       _state.value.name.value=data.name
       _state.value.phoneNo.value=data.phoneNo

       _state.value.endDate.value=data.endDate
       _state.value.startDate.value=data.startDate
       _state.value.description.value= data.description
       _state.value.isAmountDone.value=data.isAmountDone

    }
}