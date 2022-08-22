package cz.cvut.popovma1.spacex.ui.component.images

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import cz.cvut.popovma1.spacex.ui.component.column.ColumnWithTitle
import cz.cvut.popovma1.spacex.ui.theme.cornerRadius
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium

@Composable
fun UrlImagesColumn(
    title: String,
    imagesDescription: String,
    imagesList: List<String>
) {
    ColumnWithTitle(title = title) {
        imagesList.forEach { url ->
            Spacer(modifier = Modifier.height(paddingMedium))
            AsyncImage(
                model = url,
                contentDescription = imagesDescription,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(cornerRadius)),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        }
    }
}
