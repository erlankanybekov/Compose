package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme() {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val image = viewModel.state.value.image
                    val isLoading = viewModel.state.value.isLoading

//                   my set img
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.White),
                        shape = CircleShape,
                        elevation = 2.dp
                    ) {

                    }

//                    backend
                    image?.let {
                        Image(
                            painter = rememberImagePainter(
                                data = image.imgUrl,
                                builder = { crossfade(true) }
                            ),
                            contentDescription = "Rabbit"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = image.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = image.description)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Button(onClick = viewModel::getRandomImage,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Next")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if (isLoading){
                        CircularProgressIndicator()
                    }
                }
            }

        }
    }
}