package com.vaibhav.konnection.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.vaibhav.konnection.util.SharedViewModel
import com.vaibhav.konnection.util.UserData

@Composable
fun GetDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var userID: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var profession: String by remember { mutableStateOf("") }
    var age: String by remember { mutableStateOf("") }
    var ageInt: Int by remember { mutableStateOf(0) }
    var insta: String by remember { mutableStateOf("") }
    var contact: String by remember { mutableStateOf("") }
    var contactInt: Int by remember { mutableStateOf(0) }
    val context = LocalContext.current

    // main Layout
    Column(modifier = Modifier.fillMaxSize()) {
        // back button
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }
        // get data Layout
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // userID
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    value = userID,
                    onValueChange = {
                        userID = it
                    },
                    label = {
                        Text(text = "StudentID")
                    }
                )
                // get user data Button
                Button(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp),
                    onClick = {
                        sharedViewModel.retrieveData(
                            userID = userID,
                            context = context
                        ) { data ->
                            name = data.name
                            profession = data.profession
                            age = data.age.toString()
                            ageInt = age.toInt()
                            insta = data.insta
                            contact=data.contact.toString()
                            contactInt=contact.toInt()
                        }
                    }
                ) {
                    Text(text = "Get  Data")
                }
            }
            // Name
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = "Name")
                }
            )
            // Profession
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = profession,
                onValueChange = {
                    profession = it
                },
                label = {
                    Text(text = "Talent")
                }
            )
            // Age
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = age,
                onValueChange = {
                    age = it
                    if (age.isNotEmpty()) {
                        ageInt = age.toInt()
                    }
                },
                label = {
                    Text(text = "College Year")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Insta
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = insta,
                onValueChange = {
                    insta = it
                },
                label = {
                    Text(text = "Insta Id")
                }
            )
            // creating a variable of value 5
            val mMaxLength = 10

            // fetching local context
            val mContext = LocalContext.current
            // Contact
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = contact,
                onValueChange = {
                    if (it.toString().length <= mMaxLength)
                    contact = it
                    else Toast.makeText(mContext, "Cannot be more than 10 Number", Toast.LENGTH_SHORT).show()

                },
                label = {
                    Text(text = "Roll No.")
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            // save Button
            //          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val userData = UserData(
                        userID = userID,
                        name = name,
                        profession = profession,
                        age = ageInt,
                        insta = insta,
                        contact=contact,
                    )

                    sharedViewModel.saveData(userData = userData, context = context)
                }
            ) {
                Text(text = "Save")
            }
            // delete Button
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onClick = {
                    sharedViewModel.deleteData(
                        userID = userID,
                        context = context,
                        navController = navController
                    )
                }
            ) {
                Text(text = "Delete")
            }
        }
    }
}