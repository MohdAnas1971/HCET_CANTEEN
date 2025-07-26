package com.example.masshcet.navigation

import kotlinx.serialization.Serializable



@Serializable
sealed class NavRouts {

    @Serializable
    data object HomeScreen : NavRouts()

    @Serializable
    data object AddEditScreen : NavRouts()

    @Serializable
    data object DayCalculator : NavRouts()

    @Serializable
    data class MemberDetailedScreen(val name:String) : NavRouts()
}