package cz.cvut.popovma1.spacex.presentation.ui.rocketList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.Rocket
import cz.cvut.popovma1.spacex.presentation.theme.cornerRadius
import cz.cvut.popovma1.spacex.presentation.theme.paddingMedium
import cz.cvut.popovma1.spacex.presentation.theme.spacerSizeSmall

@Composable
fun RocketListWithTitle(
    title: String = stringResource(id = R.string.rocket_list_title_rockets),
    rockets: List<Rocket>,
    onItemClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingMedium),
    ) {
        LargeTitle(title)
        Spacer(modifier = Modifier.width(spacerSizeSmall))
        RocketList(rockets = rockets, onItemClick = onItemClick)
    }
}

@Composable
fun LargeTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
    )
}

@Composable
fun RocketList(rockets: List<Rocket>, onItemClick: (Int) -> Unit) {
    Surface (
        shape = RoundedCornerShape(cornerRadius),
        elevation = 1.dp
    ){
        LazyColumn {
            items(rockets) { rocket ->
                RocketItem(rocket, onItemClick)
            }
        }
    }
}
