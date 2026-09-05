package id.lena.wedding.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private val shownKeys = mutableSetOf<String>()

@Composable
fun AnimatedEntrance(
    key: String = "",
    delayMs: Long = 0,
    durationMs: Int = 700,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(modifier = modifier) {
        val isMobile = maxWidth < 760.dp
        // Di HP matikan animasi biar scroll 60fps — langsung show, tidak retrigger pas recycle LazyColumn
        if (isMobile) {
            Box(modifier = Modifier.fillMaxWidth()) { content() }
        } else {
            var visible by remember(key) { mutableStateOf(shownKeys.contains(key) || key.isEmpty()) }
            LaunchedEffect(key) {
                if (!shownKeys.contains(key) && key.isNotEmpty()) {
                    delay(delayMs)
                    visible = true
                    shownKeys.add(key)
                } else if (key.isEmpty()) {
                    delay(delayMs)
                    visible = true
                }
            }
            val alpha by animateFloatAsState(if (visible) 1f else 0f, animationSpec = tween(durationMs), label = "alpha")
            val offsetY by animateFloatAsState(if (visible) 0f else 24f, animationSpec = tween(durationMs), label = "offset")
            Box(modifier = Modifier.fillMaxWidth().graphicsLayer { this.alpha = alpha; translationY = offsetY }) {
                content()
            }
        }
    }
}
