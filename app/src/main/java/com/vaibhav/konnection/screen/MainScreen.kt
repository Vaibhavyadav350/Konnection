package com.vaibhav.konnection.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vaibhav.konnection.nav.Screens
import com.vaibhav.konnection.ui.theme.Teal200
import com.vaibhav.konnection.R.drawable.kiit
@Composable
fun MainScreen(
    navController: NavController,
) {




    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // get user data Button
        Text(
            text = "KIIT Talent Konnections",
            style = MaterialTheme.typography.h3,
            color = Color.Green,
            fontSize = 35.sp
        )



        Divider(thickness = 3.dp)
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .height(70.dp)
                .width(150.dp),
            shape= RectangleShape,
            onClick = {
                navController.navigate(route = Screens.GetDataScreen.route)
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.LightGray,
                contentColor = Color.Magenta)
        ) {
            Text(text = "Get Student Data")
        }

        // add user data Button
        OutlinedButton(
            modifier = Modifier
                .height(70.dp)
                .width(150.dp),
            onClick = {
                navController.navigate(route = Screens.AddDataScreen.route)
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.LightGray,
                contentColor = Color.Magenta)
        ) {
            Text(text = "Add Student Data")
        }

    }
}




