package com.hari.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hari.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MyApp(it)
               }

            }
        }
    }
}

@Composable
fun MyApp(innerPadding: PaddingValues = PaddingValues(0.dp)) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "firstscreen") {
        composable("firstscreen") {
            FirstScreen(innerPadding) { name ->
                navController.navigate("secondscreen/$name")
            }
        }

        composable(route= "secondscreen/{name}") {
            val name = it.arguments?.getString("name") ?: "No name"
            SecondScreen(innerPadding, nameFrom1 = name) { name, age ->
                navController.navigate("thirdscreen/$name/$age")
            }
        }

        composable(route = "thirdscreen/{name}/{age}")  {
            val name = it.arguments?.getString("name") ?: "No name"
            val age = it.arguments?.getString("age") ?: "No age"
            ThirdScreen(innerPadding, name = name, age = age) {
                navController.navigate("firstscreen")
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun FirstPerview() {
}