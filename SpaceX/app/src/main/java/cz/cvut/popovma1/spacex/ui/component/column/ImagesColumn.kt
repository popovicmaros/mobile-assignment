package cz.cvut.popovma1.spacex.ui.component.column

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import cz.cvut.popovma1.spacex.ui.theme.cornerRadius
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium

@Composable
fun ImagesColumn(
    title: String,
    imagesDescription: String,
    imagesList: List<Int>
) {
    ColumnWithTitle(title = title) {
        imagesList.forEach { src ->
            Spacer(modifier = Modifier.height(paddingMedium))
            Image(
                painter = painterResource(src),
                contentDescription = imagesDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius))
            )
        }
    }
}