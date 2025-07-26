package com.example.masshcet.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.masshcet.screens.AddCustomerPage
import com.example.masshcet.screens.AddEditViewModel
import com.example.masshcet.screens.DayCalculator
import com.example.masshcet.screens.HomeScreen
import com.example.masshcet.screens.MemberDetailedScreen
import com.example.masshcet.screens.MemberDetailedScreenViewModel
import com.example.masshcet.screens.MemberLSViewModel



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNav(
    addEditViewModel: AddEditViewModel = hiltViewModel(),
    memberLSViewModel: MemberLSViewModel= hiltViewModel(),
    memberDetailedScreenViewModel: MemberDetailedScreenViewModel= hiltViewModel()
) {

    val navController=rememberNavController()
    NavHost(navController =navController,
        startDestination = NavRouts.HomeScreen){

        composable<NavRouts.HomeScreen> {
            HomeScreen(memberLSViewModel,memberLSViewModel.memberList.collectAsState(),navController)
        }
        composable<NavRouts.AddEditScreen> {

            AddCustomerPage(navController=navController,viewModel=addEditViewModel, memberState = addEditViewModel.state.value)
        }
        composable<NavRouts.DayCalculator> {
            DayCalculator(navController)
        }
        composable<NavRouts.MemberDetailedScreen> {navBackEntry->
            val name=navBackEntry.arguments?.getString("name")?:""
            MemberDetailedScreen(navController, name)
        }
    }
}


