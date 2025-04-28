package com.appmoviles.tp1.views

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appmoviles.tp1.R

@Composable
fun BienvenidaPage(nombre: String) {

    val selectedPlataform = remember { mutableStateOf<String?>(null) }
    var selectedPreferences by remember { mutableStateOf(setOf<String>()) }
    var otherPreferences by remember { mutableStateOf("") }
    var showOtherField by remember { mutableStateOf(false) }
    var finalPreferences by remember { mutableStateOf<List<String>>(emptyList()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1E1E2C), Color(0xFF23234C))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 64.dp, end = 24.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenido/a, $nombre!",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                Text(
                    "¿Qué plataforma utilizas?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 16.dp) // Aumenté el espaciado
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp), // Separar los botones de la imagen
                    horizontalArrangement = Arrangement.Center // Centrado de botones
                ) {
                    Button(
                        onClick = { selectedPlataform.value = "Android" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedPlataform.value == "Android") MaterialTheme.colorScheme.primary else Color.LightGray,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f) // Aseguramos que los botones tengan el mismo tamaño
                    ) {
                        Text("Android")
                    }

                    Spacer(modifier = Modifier.width(16.dp)) // Separar los botones

                    Button(
                        onClick = { selectedPlataform.value = "iOS" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedPlataform.value == "iOS") MaterialTheme.colorScheme.primary else Color.LightGray,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f) // Aseguramos que los botones tengan el mismo tamaño
                    ) {
                        Text("iOS")
                    }
                }

                selectedPlataform.value?.let { plataform ->
                    val logo = if (plataform == "Android") R.drawable.android_icon else R.drawable.ios_icon
                    Image(
                        painter = painterResource(id = logo),
                        contentDescription = "$plataform Logo",
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .size(100.dp) // Ajustar el tamaño de la imagen
                            .align(Alignment.CenterHorizontally) // Centrado de imagen
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    "Tus preferencias:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                val preferences = listOf("Programacion", "Redes", "Seguridad", "Hardware", "Otra")

                Column(modifier = Modifier.fillMaxWidth()) {
                    preferences.forEach { preference ->
                        val checked = selectedPreferences.contains(preference)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { isChecked ->
                                    selectedPreferences = if (isChecked) {
                                        selectedPreferences + preference
                                    } else {
                                        selectedPreferences - preference
                                    }
                                    showOtherField = preference == "Otra" && isChecked
                                    if (!selectedPreferences.contains("Otra")) {
                                        otherPreferences = ""
                                    }
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = MaterialTheme.colorScheme.primary
                                )
                            )
                            Text(
                                preference,
                                color = Color.White // Aquí definimos que el texto será blanco
                            )
                        }
                    }

                    if (showOtherField) {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = otherPreferences,
                            onValueChange = { newValue -> otherPreferences = newValue },
                            label = { Text("Especificar otra preferencia") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                cursorColor = MaterialTheme.colorScheme.primary,
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                    }

                    Button(
                        onClick = {
                            finalPreferences = buildList {
                                addAll(selectedPreferences.filter { it != "Otra" })
                                if (selectedPreferences.contains("Otra") && otherPreferences.isNotBlank()) {
                                    add(otherPreferences)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Guardar Preferencias", style = TextStyle(fontWeight = FontWeight.Bold))
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    if (finalPreferences.isNotEmpty()) {
                        Text(
                            "Preferencias guardadas:",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(finalPreferences.joinToString(", "), color = Color.White) // También el texto aquí en blanco
                    }
                }
            }
        }
    }
    }





@Preview(showBackground = true)
@Composable
fun BienvenidaPageStyledPreview() {
    BienvenidaPage(nombre = "Juan Torres")
}


