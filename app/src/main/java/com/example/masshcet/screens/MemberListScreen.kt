package com.example.masshcet.screens


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.masshcet.R
import com.example.masshcet.data.MemberEntity
import com.example.masshcet.navigation.NavRouts
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    memberLSViewModel: MemberLSViewModel,
    memberList: State<List<MemberEntity>>,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Hitkarini Mass") },
                modifier=Modifier,
                actions = {
                    Icon(painter = painterResource(R.drawable.baseline_calculate_24),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp).clickable { navController.navigate(NavRouts.DayCalculator)})
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal)
                ),

            ) {
                // Your bottom bar content
                Row(horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(onClick = {navController.navigate(route = NavRouts.AddEditScreen)},
                        shape = CircleShape,
                        modifier = Modifier.padding(end = 20.dp)) {
                        Text(text = "ADD")
                    }
                }
            }
        },
        content = { padding ->

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {

                MemberListScreen(memberLSViewModel = memberLSViewModel, memberList = memberList,navController)
            }
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MemberListScreen(
    memberLSViewModel: MemberLSViewModel,
    memberList: State<List<MemberEntity>>,
    navController: NavHostController,
    ) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(memberList.value) { member ->
                ListItem(member =member,navController)
            }
        }
    }


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListItem(
    member: MemberEntity,
    navController: NavHostController,
   viewModel: MemberLSViewModel= hiltViewModel()
) {


val remainingDay=(member.endDate - System.currentTimeMillis())/(1000*60*60*24)

    val context = LocalContext.current
    val paddingVal: Dp = 6.dp

    val bgColor:Color = if (member.isAmountDone){
        MaterialTheme.colorScheme.tertiary
    }else{
        MaterialTheme.colorScheme.error
    }

    Card(
        modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingVal)
                .border(2.dp, color = bgColor, shape = RoundedCornerShape(8))
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {

                //Member Name
                Row(verticalAlignment = Alignment.CenterVertically,
                   modifier = Modifier .clickable {
                    viewModel.updateMemberState(member)
                    navController.navigate(NavRouts.MemberDetailedScreen(member.name))
                }) {
                    Icon(painter = painterResource(R.drawable.student_icon), contentDescription = null,Modifier.size(20.dp).padding(end = 5.dp))


                    Box(
                        modifier = Modifier
                            .width(180.dp)
                           // .height(50.dp) // Adjust height
                            .horizontalScroll(rememberScrollState()) // Enable scrolling
                    ) {

                        Text(
                            text =member.name,
                            fontSize = 24.sp,
                            maxLines = 1,
                           // softWrap = true,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight(500),
                           // modifier = Modifier.width(200.dp),
                        )
                    }


                }
                //Phone Number
                Row(modifier = Modifier .clickable {
                    viewModel.openDefaultDialer("+91${member.phoneNo}",context)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.bg_image),
                        contentDescription = null,
                        Modifier
                            .size(20.dp)

                    )
                    Text(
                        text = "+91 ",
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = member.phoneNo,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }

            //Date Information
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                DateColumn(
                    first = toDate(member.startDate).toString(),
                    second = toDate(member.endDate).toString(),
                    remainingDay=remainingDay
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
}




@Composable
fun DateColumn(first: String, second: String,remainingDay:Long) {

    var dateColor:Color= MaterialTheme.colorScheme.tertiary
    if (remainingDay<=2){
        dateColor=MaterialTheme.colorScheme.error
    }


    Column(verticalArrangement =Arrangement.SpaceBetween
        , modifier = Modifier.fillMaxHeight()
    ) {
        Text(text = first,
            fontWeight = FontWeight(600),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = second,
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            style = MaterialTheme.typography.bodyLarge,
            color = dateColor
        )
    }
 }



// Convert Long to formatted Date String (when retrieving from the database)
fun toDate(timestamp: Long?): String? {
    return timestamp?.let {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.format(Date(it))
    }
}



