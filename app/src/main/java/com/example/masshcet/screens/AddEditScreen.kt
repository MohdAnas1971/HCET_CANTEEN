package com.example.masshcet.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.masshcet.MemberState
import com.example.masshcet.R
import com.example.masshcet.navigation.NavRouts
import com.example.masshcet.ui.theme.LightBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCustomerPage(
    navController: NavHostController,
    viewModel: AddEditViewModel,
    memberState: MemberState,
) {

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("ADD MEMBER") },
            navigationIcon = {
                Icon(painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = null,
                    modifier = Modifier.clickable { navController.navigate(NavRouts.HomeScreen) })
            },
        )
    },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = padding)
            ) {
                OutlinedCard(
                    colors = CardColors(
                        contentColor = Color.Unspecified,
                        containerColor = LightBlue,
                        disabledContentColor = Color.Unspecified,
                        disabledContainerColor = Color.Unspecified
                    ),
                    modifier = Modifier
                        .height(500.dp)
                        .padding(8.dp),

                    ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        // Name text field
                        CustomTextFieldSpacer(
                            fieldName = "Name",
                            name = memberState.name.value,
                            icon = R.drawable.baseline_account_circle_24,
                        ) {
                            memberState.name.value = it
                        }
                        //description text field
                        CustomTextFieldSpacer(
                            fieldName = "Description",
                            name = memberState.description.value,
                            icon = R.drawable.baseline_description_24,
                        ) {
                            memberState.description.value = it
                        }

                        //Start Date text field
                        DatePickerExample(memberState.startDate,"Start Date")
                        //End Date text field
                        DatePickerExample(memberState.endDate,"End Date")

                        CustomTextFieldSpacer(
                            fieldName = "Phone No.",
                            name = memberState.phoneNo.value,
                            icon = R.drawable.bg_image,
                        ) {
                            memberState.phoneNo.value = it
                        }

                        CustomCheckBox("Is Amount Done :", memberState.isAmountDone.value,
                            onCheckedChange = {
                                memberState.isAmountDone.value = !memberState.isAmountDone.value
                            })
                        CustomCheckBox("Is Month Done :  ", memberState.isMonthDone.value,
                            onCheckedChange = {
                                memberState.isMonthDone.value = !memberState.isMonthDone.value
                            })


                    }
                }//end card
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            viewModel.addMember()
                            navController.navigate(NavRouts.HomeScreen)
                        }, Modifier.size(height = 50.dp, width = 150.dp)
                    ) {
                        Text(text = "ADD")
                    }
                }
            }
        }
    )
}

@Composable
fun CustomCheckBox(fieldName: String, value: Boolean, onCheckedChange: () -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = fieldName)
        Spacer(modifier = Modifier.width(8.dp))
        Checkbox(checked = value, onCheckedChange = { onCheckedChange() })
    }
}

@Composable
fun CustomTextFieldSpacer(
    name: String,
    fieldName: String,
    icon: Int,
    onNameChange: (String) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = name,
            label = { Text(text = fieldName) },
            placeholder = { Text(text = "Enter Here") },
            leadingIcon = { Icon(painter = painterResource(icon), contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { onNameChange(it) }
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
}


//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TestComponent(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = " name student",
            label = { Text("name ") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_account_circle_24),
                    contentDescription = null
                )
            },

            onValueChange = { }
        )
    }
}