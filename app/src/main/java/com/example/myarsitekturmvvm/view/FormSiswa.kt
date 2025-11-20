package com.example.myarsitekturmvvm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults.Thickness
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myarsitekturmvvm.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSiswa(
    pilihanJK: List<String>,
    OnSubmitBtnClick: (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var txtNama by rememberSaveable { mutableStateOf("") }
    var txtAlamat by rememberSaveable { mutableStateOf("") }
    var txtGender by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Form Siswa", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    colorResource(id = R.color.teal_700)
                )
            )
        }
    ) { isiRuang ->

        Column(
            modifier = Modifier.padding(isiRuang),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🔹 Nama
            OutlinedTextField(
                value = txtNama,
                onValueChange = { txtNama = it },
                label = { Text("Nama Lengkap") },
                singleLine = true,
                modifier = Modifier
                    .width(350.dp)
                    .padding(top = 20.dp)
            )

            Spacer(Modifier.height(20.dp))

            // 🔹 Gender
            Row {
                pilihanJK.forEach { item ->
                    Row(
                        modifier = Modifier.selectable(
                            selected = txtGender == item,
                            onClick = { txtGender = item }
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = txtGender == item,
                            onClick = { txtGender = item }
                        )
                        Text(item)
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // 🔹 Alamat
            OutlinedTextField(
                value = txtAlamat,
                onValueChange = { txtAlamat = it },
                label = { Text("Alamat Lengkap") },
                singleLine = true,
                modifier = Modifier.width(350.dp)
            )

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    val hasil = mutableListOf(txtNama, txtAlamat, txtGender)
                    OnSubmitBtnClick(hasil)
                },
                enabled = txtNama.isNotEmpty() && txtAlamat.isNotEmpty() && txtGender.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text("Submit")
            }
        }
    }
}
