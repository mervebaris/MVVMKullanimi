package com.example.mvvmkullanimi

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Visibility
import cafe.adriel.voyager.core.model.rememberScreenModel
import com.example.mvvmkullanimi.ui.theme.MVVMKullanimiTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class AnaSayfa: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        var isError1 by rememberSaveable { mutableStateOf(false) }
        var isError2 by rememberSaveable { mutableStateOf(false) }
        val viewModel = rememberScreenModel { SayfaViewModel() }
        val sonuc = viewModel.sonuc.observeAsState("0")
        val tfSayi1 = remember { viewModel.tfSayi1 }
        val tfSayi2 = remember { viewModel.tfSayi2 }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = sonuc.value, fontSize = 50.sp)

            TextField(
                value = tfSayi1.value,
                onValueChange = {tfSayi1.value = it},
                label = { Text(text = "Sayı 1") },
                isError = isError1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
                maxLines = 1,
                trailingIcon = {
                    if (isError1){
                        Icon(Icons.Filled.Error,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.error)
                    }
                }
            )

            TextField(
                value = tfSayi2.value,
                onValueChange = {tfSayi2.value = it },
                label = { Text(text = "Sayı 2") },
                isError = isError2,
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                trailingIcon = {
                    if (isError2){
                        Icon(Icons.Filled.Error,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.error)
                    }

                }
            )

            Button(onClick = {
                val sayi1 = try{
                    tfSayi1.value.toInt()
                }catch (e:Throwable){
                    e.printStackTrace()
                    null
                }
                if (sayi1 == null) {
                    isError1 = true
                    return@Button
                }

                val sayi2 = try{
                    tfSayi2.value.toInt()
                }catch (e:Throwable){
                    e.printStackTrace()
                    null
                }

                if (sayi2 == null) {
                    isError2 = true
                    return@Button
                }
                viewModel.toplamaYap(sayi1, sayi2)
            }) {
                Text(text = "Toplama")
            }


            Button(onClick = {
                viewModel.carpmaYap(tfSayi1.value, tfSayi2.value)
            }) {
                Text(text = "Çarpma")
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVMKullanimiTheme {
        AnaSayfa().Content()
    }
}
