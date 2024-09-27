package com.mrh.listacontactos

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mrh.listacontactos.ui.theme.ListaContactosTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = ListaContactosViewModel()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
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
                            },
                            navigationIcon = {
                                if(navBackStackEntry?.destination?.route == "formulario_view"){
                                    IconButton(
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = ""
                                        )
                                    }
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FilledIconButton(
                            onClick = {
                                navController.navigate("formulario_view")
                            },
                            shape = RectangleShape
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Localized description")
                        }
                    }
                ) { innerPadding ->
                    NavigationHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ListaContactosViewModel
){
    NavHost(
        navController = navController,
        startDestination = "home_view",
    ){
        composable(route = "home_view"){
            HomeView(modifier, viewModel)
        }
        composable(route = "formulario_view") {
            FormularioRegistroView(modifier, viewModel, navController)
        }
    }
}


@Composable
fun HomeView(modifier: Modifier = Modifier, viewModel: ListaContactosViewModel) {
    val miLista = cargarDatos(viewModel)
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
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
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

@SuppressLint("UnrememberedMutableState")
@Composable
fun FormularioRegistroView(
    modifier: Modifier = Modifier,
    viewModel: ListaContactosViewModel,
    navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var hombre by remember { mutableStateOf(false) }
    var mujer by remember { mutableStateOf(false) }
    var telefono by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Spacer(Modifier.padding(10.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        Spacer(Modifier.padding(10.dp))
        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text("Hombre")
            Checkbox(
                checked = hombre,
                onCheckedChange = { hombre = it },
                enabled = !mujer
            )
            Text("Mujer")
            Checkbox(
                checked = mujer,
                onCheckedChange = { mujer = it },
                enabled = !hombre
            )
        }
        Spacer(Modifier.padding(10.dp))
        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Telefono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(Modifier.padding(10.dp))

        FilledTonalButton(
            onClick = {
                val listaContactos = viewModel.contactos.value as ArrayList<Contacto>
                listaContactos.add(Contacto(
                    id = 0,
                    nombre = nombre,
                    apellido = apellido,
                    sexo = if(hombre) "H" else "M",
                    telefono = telefono.toInt()
                ))
                viewModel.contactos = MutableLiveData(listaContactos)
                navController.popBackStack()
            }
        ) {
            Text("Crear Contacto")
        }

    }
}

//Funciones de datos
fun crearListaContactos(): ArrayList<Contacto> {
    val listContactos: ArrayList<Contacto> = ArrayList()
    listContactos.add(Contacto(0,"Raul","Perez", "H",0))
    listContactos.add(Contacto(1,"Lidia","Lopez", "M",0))
    listContactos.add(Contacto(3,"repe","Rodriguez", "H",0))
    return listContactos
}


fun cargarDatos(
    viewModel: ListaContactosViewModel
) : ArrayList<Contacto>
{
    if(viewModel.contactos.value === null){
        viewModel.contactos = MutableLiveData(crearListaContactos())
    }
     return  viewModel.contactos.value as ArrayList<Contacto>
}
