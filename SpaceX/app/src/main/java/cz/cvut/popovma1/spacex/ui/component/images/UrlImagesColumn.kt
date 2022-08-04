package cz.cvut.popovma1.spacex.ui.component.images

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import cz.cvut.popovma1.spacex.ui.component.column.ColumnWithTitle
import cz.cvut.popovma1.spacex.ui.theme.cornerRadius
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium
import cz.cvut.popovma1.spacex.ui.theme.smallBorder

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
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius))
                    .border(
                        width = smallBorder,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(cornerRadius)
                    )
            )
        }
    }
}
