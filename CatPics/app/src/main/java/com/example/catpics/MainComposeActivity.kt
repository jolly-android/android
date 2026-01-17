package com.verkada.android.catpictures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// --------------------- DATA MODEL ---------------------
data class Picture(
    val id: String,
    val author: String,
    val download_url: String
)

// --------------------- RETROFIT SERVICE ---------------------
interface PictureService {
    @GET("list")
    suspend fun pictures(
        @Query("page") page: Int = 1,
        @Query("limit") perPage: Int = 30
    ): List<Picture>

    companion object {
        const val ROOT_URL = "https://picsum.photos/v2/"
        fun create(): PictureService {
            val retrofit = Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PictureService::class.java)
        }
    }
}

// --------------------- VIEWMODEL ---------------------
class PictureViewModel : ViewModel() {
    var pictures by mutableStateOf<List<Picture>>(emptyList())
    var isLoading by mutableStateOf(true)
    var errorMessage by mutableStateOf<String?>(null)

    private val service = PictureService.create()

    init {
        viewModelScope.launch {
            try {
                pictures = service.pictures()
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}

// --------------------- MAIN ACTIVITY ---------------------
class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PictureGrid()
                }
            }
        }
    }
}

// --------------------- COMPOSABLE GRID ---------------------
@Composable
fun PictureGrid(viewModel: PictureViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val pictures = viewModel.pictures
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Loading images...")
            }
        }

        error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $error")
            }
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pictures) { picture ->
                    AsyncImage(
                        model = "https://picsum.photos/id/${picture.id}/300/300",
                        contentDescription = picture.author,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}