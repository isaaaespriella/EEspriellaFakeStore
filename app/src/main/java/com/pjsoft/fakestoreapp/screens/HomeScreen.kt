package com.pjsoft.fakestoreapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pjsoft.fakestoreapp.components.ProductCard
import com.pjsoft.fakestoreapp.models.Product
import com.pjsoft.fakestoreapp.services.ProductService
import com.pjsoft.fakestoreapp.ui.theme.FakeStoreAppTheme
import com.pjsoft.fakestoreapp.ui.theme.ProductDetailScreenRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun HomeScreen(navController: NavController) {
    var products by remember { mutableStateOf(listOf<Product>()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ProductService::class.java)
            val result = async(Dispatchers.IO) { service.getAllProducts() }
            products = result.await()
            loading = false
        } catch (e: Exception) {
            loading = false
        }
    }

    Scaffold(
        topBar = { FakeStoreTopBar("Fake Store") }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            if (loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn {
                    items(products) { product ->
                        ProductCard(
                            product = product,
                            onClick = {
                                navController.navigate(ProductDetailScreenRoute(product.id))
                            }
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    FakeStoreAppTheme {
        HomeScreen(rememberNavController())
    }
}