package com.devmike.gamepedia.android.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.devmike.gamepedia.android.ui.getPlatformLogo
import com.devmike.gamepedia.domain.models.Game
import com.devmike.gamepedia.domain.models.ScoreRating
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin

@Composable
fun GameItem(game: Game) {
    val systemInDarkMode = isSystemInDarkTheme()
    var colorPalette by remember {
        mutableStateOf<Palette?>(null)
    }

    val vibrantColor = colorPalette?.getVibrantColor(0) ?: 0

    val mutedColor = colorPalette?.getMutedColor(0) ?: 0 /*when (systemInDarkMode) {

        true -> colorPalette?.darkMutedSwatch?.rgb
        false -> colorPalette?.lightMutedSwatch?.rgb
    } ?: 0*/

    val gradient = Brush.verticalGradient(
        listOf(
            Color.Transparent,
            Color(vibrantColor).copy(alpha = 0.5f),
            Color.Black

        )
    )

    Card(
        modifier = Modifier.padding(8.dp)

    ) {
        Box(modifier = Modifier.height(250.dp)) {
            GameImage(modifier = Modifier.fillMaxSize(), imageUrl = game.backgroundImage) {
                colorPalette = it
            }

            Box(
                modifier = Modifier
                    .background(
                        brush = gradient,
                        shape = RoundedCornerShape(
                            size = 4.dp
                        )
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .align(Alignment.BottomCenter)
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    game.platforms.map { val logo = getPlatformLogo(it)
                        Icon(painter = painterResource(id = logo), modifier = Modifier.size(20.dp), contentDescription = "Platform logo", tint = Color.White)
                    }
                }
                Text(text = game.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge, color = Color.White)
                GameRating(rating = game.rating)
                // Text(text = game.dominantColor)
            }

            game.scoreRating?.let {
                ScoreBox(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                    rating = it
                )
            }
        }
    }
}

@Composable
fun GameImage(
    modifier: Modifier,
    imageUrl: String,
    imageOptions: ImageOptions = ImageOptions(
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    ),
    onGeneratePallete: (Palette) -> Unit
) {
    CoilImage(
        modifier = modifier,
        imageModel = { imageUrl },
        component = rememberImageComponent {
            +PalettePlugin {
                onGeneratePallete(it)
            }
        },
        imageOptions = imageOptions
    )
}

@Composable
fun ScoreBox(rating: ScoreRating, modifier: Modifier) {
    // val ratingColor = getRatingColor(rating)
    Box(
        modifier = modifier
            .size(width = 40.dp, height = 30.dp)
            //
            // .background(shape = CircleShape, color = Color.Transparent)
            .border(width = 0.5.dp, color = Color(rating.color), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${rating.metaCriticScore}",
            color = Color(rating.color),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(4.dp)
                .padding(bottom = 4.dp)
        )
    }
}

@Composable
fun GameRating(rating: Double, stars: Int = 5) {
    val config = RatingBarConfig()
        .activeColor(Color.Yellow)
        .hideInactiveStars(false)
        .inactiveColor(Color.LightGray)
        .inactiveBorderColor(Color.White)
        .stepSize(StepSize.HALF)
        .numStars(stars)
        .isIndicator(true)
        .size(14.dp)
        .padding(6.dp)
        .style(RatingBarStyle.HighLighted)

    RatingBar(value = rating.toFloat(), config = config, onRatingChanged = {}, onValueChange = {})
}
