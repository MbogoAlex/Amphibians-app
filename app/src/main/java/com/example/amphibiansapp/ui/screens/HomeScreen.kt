package com.example.amphibiansapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.R
import com.example.amphibiansapp.data.AmphibiansUiState
import com.example.amphibiansapp.data.AppUiState
import com.example.amphibiansapp.model.AmphibianDetails

@Composable
fun HomeScreen(
    appUiState: AppUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(appUiState.amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Success -> AmphibiansScrollableScreen(amphibians = appUiState.amphibiansUiState.amphibians)
        is AmphibiansUiState.Error -> ErrorScreen(retryAction = retryAction)
    }
}

@Composable
fun AmphibiansScrollableScreen(
    amphibians: List<AmphibianDetails>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item { 
            Text(
                text = "Amphibians",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(15.dp)
            )
        }
        items(amphibians.size) { index ->
            AmphibianItemCard(
                amphibian = amphibians[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }
    }
}

@Composable
fun AmphibianItemCard(
    amphibian: AmphibianDetails,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column {
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(10.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image),
                contentScale = ContentScale.Crop,
                contentDescription = amphibian.name,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = amphibian.description,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Failed to load photos")
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(onClick = retryAction) {
            Text(text = "Retry")
        }
    }
}

