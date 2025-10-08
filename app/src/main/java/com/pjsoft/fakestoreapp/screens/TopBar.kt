package com.pjsoft.fakestoreapp.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "ALL IN",
    onSearch: () -> Unit = {},
    onCart: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        actions = {
            IconButton(onClick = onSearch) {
                Text("🔍")
            }
            IconButton(onClick = onCart) {
                Text("🛒")
            }
        }
    )
}
