package com.mrh.listacontactos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val miLista = CrearListaContactos()
    Column(modifier = modifier) {
        miLista.forEach { persona ->
            ContactoCard(persona)
        }
    }
}

@Composable
fun ContactoCard(persona: Contacto){
    Row(modifier = Modifier.fillMaxWidth().padding(12.dp)){
        if(persona.sexo.equals("H")){
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults
                            .cardColors(
                                containerColor = Color.Cyan
                            )
            ){
                Column(modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(persona.nombre)
                    Spacer(Modifier.padding(4.dp))
                    Text(persona.apellido)
                }
            }
        }else{
            Card(modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults
                            .cardColors(
                                containerColor = Color.Green
                            )
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(persona.nombre)
                    Spacer(Modifier.padding(4.dp))
                    Text(persona.apellido)
                }
            }
        }

    }
}

fun CrearListaContactos(): ArrayList<Contacto> {
    val listContactos: ArrayList<Contacto> = ArrayList()
    listContactos.add(Contacto(0,"Raul","Perez", "H",""))
    listContactos.add(Contacto(1,"Lidia","Lopez", "M",""))
    listContactos.add(Contacto(3,"Pepe","Rodriguez", "H",""))
    return listContactos
}
