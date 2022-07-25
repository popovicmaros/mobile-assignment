package cz.cvut.popovma1.spacex.ui.ui.rocketDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme

class RocketDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setContent {
            SpaceXTheme {
                RocketDetailScreen()
            }
        }
    }
}