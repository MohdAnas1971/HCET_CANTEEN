package com.example.masshcet.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.masshcet.MemberState
import com.example.masshcet.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun MemberDetailedScreen(
    navController: NavHostController,
    name:String,
    memberDetailedScreenViewModel: MemberDetailedScreenViewModel= hiltViewModel(),
    memberLSViewModel : MemberLSViewModel = hiltViewModel(),
) {
     val memberToDelete=memberDetailedScreenViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        memberDetailedScreenViewModel.getOneMember(name)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(300.dp)
                            // .height(50.dp) // Adjust height
                            .horizontalScroll(rememberScrollState()) // Enable scrolling
                    ) {

                        Text(
                            text = memberToDelete.value.name.value,
                            fontSize = 24.sp,
                            maxLines = 1,
                            // softWrap = true,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight(500),
                            // modifier = Modifier.width(200.dp),
                        )
                    }
                     },
                navigationIcon = {
                    IconButton(onClick ={navController.navigateUp()} ){
                    Icon(painter = painterResource(R.drawable.baseline_arrow_back_24), contentDescription = null)
                } },
                actions = {
                    IconButton(onClick ={
                        memberDetailedScreenViewModel.deleteMember()
                        navController.navigateUp()
                    } ){
                        Icon(painter = painterResource(R.drawable.baseline_delete_outline_24), contentDescription = null)
                    }
                }
            )
        },
    ) { innerPadding->
        Column(modifier = Modifier.padding(innerPadding)) {

            Content(memberToDelete.value)
        }
    }
}


@Composable
fun Content(value: MemberState) {

    var isAmountDoneIcon= R.drawable.baseline_disabled_by_default_24
    if (value.isAmountDone.value){
        isAmountDoneIcon= R.drawable.baseline_done_24
    }

    Column(modifier = Modifier.padding(8.dp)) {
        toDate(value.startDate.value)?.let { ContentField("Start Date :", it,) }
        toDate(value.endDate.value)?.let { ContentField("End Date :", it) }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Payment Don: ",Modifier.width(150.dp),style = MaterialTheme.typography.labelLarge)
            Icon(painter = painterResource(isAmountDoneIcon), contentDescription = null)
        }


        ContentField("Description :", value.description.value,)

    }
}
@Composable
fun ContentField(fieldName: String, value: String) {
    Row(modifier=Modifier.fillMaxWidth()) {
        Text(text = fieldName,Modifier.width(150.dp) ,style = MaterialTheme.typography.labelLarge )
        Text(text =value )

    }
    Spacer(Modifier.height(5.dp))
}

