package com.example.myarsitekturmvvm

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myarsitekturmvvm.model.DataJK.JenisK
import com.example.myarsitekturmvvm.view.FormSiswa
import com.example.myarsitekturmvvm.view.TampilSiswa
import com.example.myarsitekturmvvm.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulirku,
    Detail
}

@Composable
fun SiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    viewModel: SiswaViewModel = viewModel()
){
    Scaffold { isiRuang ->
        val uiState = viewModel.statusUI.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,
            modifier = Modifier.padding(isiRuang)
        ) {

            // 🔹 Halaman Form
            composable(Navigasi.Formulirku.name) {
                val ctx = LocalContext.current
                FormSiswa(
                    pilihanJK = JenisK.map { id ->
                        ctx.resources.getString(id)
                    },
                    OnSubmitBtnClick = { hasilForm ->
                        viewModel.setSiswa(hasilForm)
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }

