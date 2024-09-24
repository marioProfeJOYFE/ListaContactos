package com.mrh.listacontactos

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrh.listacontactos.ui.theme.ListaContactosTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaContactosTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.secondary

                            )
                            ,title = {
                                Text("Lista Contactos")
                            }
                        )
                    },
                    floatingActionButton = {
                        FilledIconButton(
                            onClick = {

                            },
                            shape = RectangleShape
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Localized description")
                        }
                    }
                ) { innerPadding ->
                    HomeView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeView(modifier: Modifier = Modifier) {
    val miLista = crearListaContactos()
    Column(modifier = modifier) {
        miLista.forEach { persona ->
            ContactoCard(persona)
        }
    }
}

@Composable
fun ContactoCard(persona: Contacto){
    Row(modifier = Modifier.fillMaxWidth().padding(12.dp)){

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults
                            .cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer
                            )
            ){
                Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Card(modifier = Modifier.size(30.dp), shape = CircleShape){
                        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center){
                            Text(persona.nombre.substring(0,1).uppercase(), textAlign = TextAlign.Center)
                        }

                    }
                    Spacer(Modifier.padding(7.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(persona.nombre, fontWeight = FontWeight.ExtraBold)
                        Text(persona.apellido)
                    }
                }
            }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FormularioRegistroView() {
    var nombre: String
    Column {
        /*TextField(
            state = rememberTextFieldState(),
            lineLimits = TextFieldLineLimits.SingleLine,
            label = {  },
        )*/
    }
}

//Funciones de datos
fun crearListaContactos(): ArrayList<Contacto> {
    val listContactos: ArrayList<Contacto> = ArrayList()
    listContactos.add(Contacto(0,"Raul","Perez", "H",""))
    listContactos.add(Contacto(1,"Lidia","Lopez", "M",""))
    listContactos.add(Contacto(3,"repe","Rodriguez", "H",""))
    return listContactos
}
