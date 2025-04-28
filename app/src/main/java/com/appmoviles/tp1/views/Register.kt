package com.appmoviles.tp1.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.appmoviles.tp1.R
import com.appmoviles.tp1.models.User

@Preview(showBackground = true)
@Composable
fun Register(navController: NavController = rememberNavController()) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repeatPassword = remember { mutableStateOf("") }

    val newUser = remember { mutableStateOf<User?>(null) }
    val passwordVisible = remember { mutableStateOf(false) }
    val repeatPasswordVisible = remember { mutableStateOf(false) }

    val errorMessage = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1E1E2C), Color(0xFF23234C))
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_app),
                contentDescription = "Logo de la aplicación",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Registrarse",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontSize = 36.sp,
                lineHeight = 42.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                placeholder = { Text("Nombre", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2C2C54), shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2C2C54),
                    unfocusedContainerColor = Color(0xFF2C2C54),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                placeholder = { Text("Correo electrónico", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2C2C54), shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2C2C54),
                    unfocusedContainerColor = Color(0xFF2C2C54),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                placeholder = { Text("Contraseña", color = Color.Gray) },
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(
                            imageVector = image,
                            contentDescription = if (passwordVisible.value) "Ocultar contraseña" else "Mostrar contraseña",
                            tint = Color.Gray
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2C2C54), shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2C2C54),
                    unfocusedContainerColor = Color(0xFF2C2C54),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = repeatPassword.value,
                onValueChange = { repeatPassword.value = it },
                placeholder = { Text("Repetir Contraseña", color = Color.Gray) },
                visualTransformation = if (repeatPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (repeatPasswordVisible.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = { repeatPasswordVisible.value = !repeatPasswordVisible.value }) {
                        Icon(
                            imageVector = image,
                            contentDescription = if (repeatPasswordVisible.value) "Ocultar contraseña" else "Mostrar contraseña",
                            tint = Color.Gray
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2C2C54), shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2C2C54),
                    unfocusedContainerColor = Color(0xFF2C2C54),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (password.value == repeatPassword.value) {
                        newUser.value = User(name.value, email.value, password.value)
                        errorMessage.value = ""
                    } else {
                        newUser.value = null
                        errorMessage.value = "Las contraseñas no coinciden"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C63FF)
                )
            ) {
                Text("Registrar", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.value.isNotEmpty()) {
                Text(
                    text = errorMessage.value,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }

            newUser.value?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .background(
                            color = Color(0xFF2C2C54),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        "Usuario creado:",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Nombre: ${it.name}", color = Color.White)
                    Text("Email: ${it.email}", color = Color.White)
                    Text("Contraseña: ${it.password}", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "¿Ya tienes una cuenta?", color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Iniciar sesión",
                    color = Color(0xFF6C63FF),
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        navController.navigate("Login")
                    }
                )
            }
        }
    }
}
