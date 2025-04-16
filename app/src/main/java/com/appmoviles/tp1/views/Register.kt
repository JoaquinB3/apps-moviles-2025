package com.appmoviles.tp1.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appmoviles.tp1.models.User

@Preview(showBackground = true)
@Composable
fun Register() {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repeatPassword = remember { mutableStateOf("") }

    val newUser = remember { mutableStateOf<User?>(null) }
    val passwordVisible = remember { mutableStateOf(false) }
    val repeatPasswordVisible = remember { mutableStateOf(false) }

    val errorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .padding(top = 99.dp)
    ) {
        Text(
            text = "Registrarse",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 23.dp)
        )

        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        )
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    passwordVisible.value = !passwordVisible.value
                }) {
                    Icon(
                        imageVector = image,
                        contentDescription = if (passwordVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        )
        TextField(
            value = repeatPassword.value,
            onValueChange = { repeatPassword.value = it },
            label = { Text("Repetir Contraseña") },
            visualTransformation = if (repeatPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (repeatPasswordVisible.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    repeatPasswordVisible.value = !repeatPasswordVisible.value
                }) {
                    Icon(
                        imageVector = image,
                        contentDescription = if (repeatPasswordVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(15.dp))

        if (errorMessage.value.isNotEmpty()) {
            Text(
                text = errorMessage.value,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        newUser.value?.let {
            Text("Usuario creado:")
            Text("Nombre: ${it.name}")
            Text("Email: ${it.email}")
            Text("Contraseña: ${it.password}")
        }
    }
}
