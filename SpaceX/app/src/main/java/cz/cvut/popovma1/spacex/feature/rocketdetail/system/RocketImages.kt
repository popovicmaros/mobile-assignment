package cz.cvut.popovma1.spacex.feature.rocketdetail.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.images.UrlImagesColumn

@Composable
fun RocketImages(rocketImages: List<String>) {
    UrlImagesColumn(
        title = stringResource(id = R.string.rocket_detail_photos),
        imagesDescription = stringResource(
            id = R.string.rocket_detail_default_rocket_photo_desc
        ),
        imagesList = rocketImages
    )
}

@Preview
@Composable
fun PreviewRocketImages() {
    RocketImages(rocketImages = RocketsSampleData.getRocketImages())
}
