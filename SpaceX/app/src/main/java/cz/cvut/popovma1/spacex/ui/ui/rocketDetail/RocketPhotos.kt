package cz.cvut.popovma1.spacex.ui.ui.rocketDetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.column.ImagesColumn

@Composable
fun RocketPhotos(rocketPhotos: List<Int>) {
    ImagesColumn(
        title = stringResource(id = R.string.rocket_detail_photos),
        imagesDescription = stringResource(
            id = R.string.rocket_detail_default_rocket_photo_desc
        ),
        imagesList = rocketPhotos
    )
}

@Preview
@Composable
fun PreviewRocketPhotos() {
    RocketPhotos(rocketPhotos = RocketsSampleData.getRocketPhotos())
}
