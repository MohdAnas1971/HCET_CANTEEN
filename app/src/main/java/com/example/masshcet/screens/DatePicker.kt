package com.example.masshcet.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.masshcet.R
import java.util.Calendar
import java.util.concurrent.TimeUnit


@Composable
fun DatePickerExample(startDate: MutableState<Long>, fieldName: String) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // val dateInMillis = remember { mutableStateOf<Long?>(null) }
    val formattedDate = remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            // Set the calendar to the selected date
            calendar.set(selectedYear, selectedMonth, selectedDay)

            // Convert date to milliseconds (long)
            startDate.value = calendar.timeInMillis

            // Optionally format the date for display
            formattedDate.value = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    )

    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() }
    ) {
        OutlinedTextField(
            value = formattedDate.value,
            label = { Text(text = fieldName) },
            placeholder = {
                Text(
                    text = "Select Date",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { datePickerDialog.show() })
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_date_range_24),
                    contentDescription = null,
                    modifier = Modifier.clickable { datePickerDialog.show() })
            },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            onValueChange = {}
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
}


@Composable
fun DayCalculator(navController: NavHostController) {
    val startDate: MutableState<Long> = remember { mutableLongStateOf(0) }
    val endDate: MutableState<Long> = remember { mutableLongStateOf(0) }
    val extraDays: MutableState<Long> = remember { mutableLongStateOf(0) }

    extraDays.value = TimeUnit.MILLISECONDS.toDays(endDate.value - startDate.value)
    Scaffold(content = { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            OutlinedCard(
                modifier = Modifier
                    .height(300.dp)
                    .padding(10.dp)
            ) {

                Column(modifier = Modifier.padding(8.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${extraDays.value}  Days",
                            fontSize = 30.sp,
                            fontWeight = FontWeight(600)
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    //Start Date text field
                    DatePickerExample(startDate, "Stop Meal")
                    //End Date text field
                    DatePickerExample(endDate, "Start Meal")
                    Text("it not add")
                }
            }
            TextButton(onClick = { navController.navigateUp() }) {
                Text(text = "Cansel ")
                Icon(
                    painter = painterResource(R.drawable.baseline_disabled_by_default_24),
                    contentDescription = null
                )
            }
        }
    })

}
