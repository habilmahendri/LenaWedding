package id.lena.wedding.utils.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// Asset vector icons — ganti emoticon biar ke-render di web (Skiko canvas, tanpa emoji font)

@Composable
fun CheckIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp, stroke: Dp = 1.8.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx()
        val h = size.toPx()
        val path = Path().apply {
            moveTo(w * 0.22f, h * 0.52f)
            lineTo(w * 0.44f, h * 0.74f)
            lineTo(w * 0.78f, h * 0.28f)
        }
        drawPath(path, color = tint, style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Composable
fun LocationIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val path = Path().apply {
            moveTo(w * 0.5f, h * 0.12f)
            cubicTo(w * 0.28f, h * 0.12f, w * 0.14f, h * 0.30f, w * 0.14f, h * 0.48f)
            cubicTo(w * 0.14f, h * 0.68f, w * 0.5f, h * 0.92f, w * 0.5f, h * 0.92f)
            cubicTo(w * 0.5f, h * 0.92f, w * 0.86f, h * 0.68f, w * 0.86f, h * 0.48f)
            cubicTo(w * 0.86f, h * 0.30f, w * 0.72f, h * 0.12f, w * 0.5f, h * 0.12f)
            close()
        }
        drawPath(path, color = tint)
        drawCircle(color = Color.White, radius = w * 0.14f, center = Offset(w * 0.5f, h * 0.45f))
    }
}

@Composable
fun PhoneIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        drawRoundRect(color = tint, topLeft = Offset(w * 0.22f, h * 0.08f), size = androidx.compose.ui.geometry.Size(w * 0.56f, h * 0.84f), style = Stroke(width = w * 0.12f))
        drawCircle(color = tint, radius = w * 0.06f, center = Offset(w * 0.5f, h * 0.78f))
    }
}

@Composable
fun EmailIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val rectPath = Path().apply {
            moveTo(w * 0.12f, h * 0.22f)
            lineTo(w * 0.88f, h * 0.22f)
            lineTo(w * 0.88f, h * 0.78f)
            lineTo(w * 0.12f, h * 0.78f)
            close()
        }
        drawPath(rectPath, color = tint, style = Stroke(width = w * 0.12f))
        val mailPath = Path().apply {
            moveTo(w * 0.12f, h * 0.24f)
            lineTo(w * 0.5f, h * 0.52f)
            lineTo(w * 0.88f, h * 0.24f)
        }
        drawPath(mailPath, color = tint, style = Stroke(width = w * 0.10f, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Composable
fun ClockIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val c = Offset(w / 2, w / 2); val r = w * 0.38f
        drawCircle(color = tint, radius = r, center = c, style = Stroke(width = w * 0.12f))
        drawLine(color = tint, start = c, end = Offset(c.x, c.y - r * 0.55f), strokeWidth = w * 0.12f, cap = StrokeCap.Round)
        drawLine(color = tint, start = c, end = Offset(c.x + r * 0.45f, c.y), strokeWidth = w * 0.12f, cap = StrokeCap.Round)
    }
}

@Composable
fun CalendarIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val r = w * 0.12f
        // outer rect
        drawRoundRect(color = tint, topLeft = Offset(w * 0.12f, h * 0.18f), size = androidx.compose.ui.geometry.Size(w * 0.76f, h * 0.68f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(r, r), style = Stroke(width = w * 0.11f))
        // header line
        drawLine(color = tint, start = Offset(w * 0.12f, h * 0.36f), end = Offset(w * 0.88f, h * 0.36f), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        // top knobs
        drawLine(color = tint, start = Offset(w * 0.32f, h * 0.12f), end = Offset(w * 0.32f, h * 0.24f), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(w * 0.68f, h * 0.12f), end = Offset(w * 0.68f, h * 0.24f), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        // dots
        drawCircle(color = tint, radius = w * 0.06f, center = Offset(w * 0.32f, h * 0.56f))
        drawCircle(color = tint, radius = w * 0.06f, center = Offset(w * 0.50f, h * 0.56f))
        drawCircle(color = tint, radius = w * 0.06f, center = Offset(w * 0.68f, h * 0.56f))
        drawCircle(color = tint, radius = w * 0.06f, center = Offset(w * 0.32f, h * 0.72f))
        drawCircle(color = tint, radius = w * 0.06f, center = Offset(w * 0.50f, h * 0.72f))
        drawCircle(color = tint, radius = w * 0.06f, center = Offset(w * 0.68f, h * 0.72f))
    }
}

@Composable
fun ChatIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val path = Path().apply {
            moveTo(w * 0.14f, h * 0.18f)
            lineTo(w * 0.86f, h * 0.18f)
            lineTo(w * 0.86f, h * 0.64f)
            lineTo(w * 0.36f, h * 0.64f)
            lineTo(w * 0.22f, h * 0.82f)
            lineTo(w * 0.22f, h * 0.64f)
            lineTo(w * 0.14f, h * 0.64f)
            close()
        }
        drawPath(path, color = tint)
        // bubble dots
        val dotY = h * 0.41f
        drawCircle(color = Color.White, radius = w * 0.07f, center = Offset(w * 0.34f, dotY))
        drawCircle(color = Color.White, radius = w * 0.07f, center = Offset(w * 0.50f, dotY))
        drawCircle(color = Color.White, radius = w * 0.07f, center = Offset(w * 0.66f, dotY))
    }
}

@Composable
fun StarIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 14.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val cx = w / 2; val cy = w / 2; val rOut = w * 0.45f; val rIn = w * 0.20f
        val path = Path()
        for (i in 0 until 5) {
            val angleOut = (-90.0 + i * 72.0) * PI / 180.0
            val xOut = cx + rOut * cos(angleOut).toFloat()
            val yOut = cy + rOut * sin(angleOut).toFloat()
            if (i == 0) path.moveTo(xOut, yOut) else path.lineTo(xOut, yOut)
            val angleIn = (-90.0 + i * 72.0 + 36.0) * PI / 180.0
            val xIn = cx + rIn * cos(angleIn).toFloat()
            val yIn = cy + rIn * sin(angleIn).toFloat()
            path.lineTo(xIn, yIn)
        }
        path.close()
        drawPath(path, color = tint)
    }
}

@Composable
fun QuoteIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 28.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx()
        // two quotes
        for (offset in listOf(0f, w * 0.52f)) {
            val path = Path().apply {
                moveTo(offset + w * 0.08f, w * 0.72f)
                cubicTo(offset + w * 0.06f, w * 0.42f, offset + w * 0.18f, w * 0.18f, offset + w * 0.28f, w * 0.18f)
                cubicTo(offset + w * 0.18f, w * 0.18f, offset + w * 0.16f, w * 0.46f, offset + w * 0.26f, w * 0.46f)
                cubicTo(offset + w * 0.34f, w * 0.46f, offset + w * 0.38f, w * 0.40f, offset + w * 0.38f, w * 0.34f)
                cubicTo(offset + w * 0.38f, w * 0.26f, offset + w * 0.30f, w * 0.18f, offset + w * 0.22f, w * 0.22f)
                lineTo(offset + w * 0.08f, w * 0.72f)
                close()
            }
            drawPath(path, color = tint)
        }
    }
}

@Composable
fun PlayIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 12.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val path = Path().apply {
            moveTo(w * 0.18f, h * 0.12f)
            lineTo(w * 0.18f, h * 0.88f)
            lineTo(w * 0.84f, h * 0.50f)
            close()
        }
        drawPath(path, color = tint)
    }
}

@Composable
fun HeartIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 18.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val path = Path().apply {
            moveTo(w * 0.5f, h * 0.78f)
            cubicTo(w * 0.20f, h * 0.58f, w * 0.08f, h * 0.34f, w * 0.28f, h * 0.20f)
            cubicTo(w * 0.38f, h * 0.12f, w * 0.46f, h * 0.16f, w * 0.5f, h * 0.24f)
            cubicTo(w * 0.54f, h * 0.16f, w * 0.62f, h * 0.12f, w * 0.72f, h * 0.20f)
            cubicTo(w * 0.92f, h * 0.34f, w * 0.80f, h * 0.58f, w * 0.5f, h * 0.78f)
            close()
        }
        drawPath(path, color = tint)
    }
}

@Composable
fun DiamondIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val path = Path().apply {
            moveTo(w * 0.5f, h * 0.12f)
            lineTo(w * 0.88f, h * 0.46f)
            lineTo(w * 0.5f, h * 0.88f)
            lineTo(w * 0.12f, h * 0.46f)
            close()
        }
        drawPath(path, color = tint)
    }
}

@Composable
fun SparkleIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val cx = w / 2; val cy = w / 2
        val path = Path().apply {
            moveTo(cx, cy - w * 0.42f)
            cubicTo(cx + w * 0.08f, cy - w * 0.18f, cx + w * 0.18f, cy - w * 0.08f, cx + w * 0.42f, cy)
            cubicTo(cx + w * 0.18f, cy + w * 0.08f, cx + w * 0.08f, cy + w * 0.18f, cx, cy + w * 0.42f)
            cubicTo(cx - w * 0.08f, cy + w * 0.18f, cx - w * 0.18f, cy + w * 0.08f, cx - w * 0.42f, cy)
            cubicTo(cx - w * 0.18f, cy - w * 0.08f, cx - w * 0.08f, cy - w * 0.18f, cx, cy - w * 0.42f)
            close()
        }
        drawPath(path, color = tint)
        drawCircle(color = tint.copy(alpha = 0.35f), radius = w * 0.08f, center = Offset(cx + w * 0.18f, cy - w * 0.18f))
    }
}

@Composable
fun ArrowRightIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 14.dp, stroke: Dp = 1.6.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        // shaft
        drawLine(color = tint, start = Offset(w * 0.14f, h * 0.50f), end = Offset(w * 0.72f, h * 0.50f), strokeWidth = stroke.toPx(), cap = StrokeCap.Round)
        // arrow head
        val path = Path().apply {
            moveTo(w * 0.72f, h * 0.50f)
            lineTo(w * 0.52f, h * 0.30f)
            moveTo(w * 0.72f, h * 0.50f)
            lineTo(w * 0.52f, h * 0.70f)
        }
        drawPath(path, color = tint, style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Composable
fun CloseIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 20.dp, stroke: Dp = 1.7.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx()
        drawLine(color = tint, start = Offset(w * 0.22f, w * 0.22f), end = Offset(w * 0.78f, w * 0.78f), strokeWidth = stroke.toPx(), cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(w * 0.78f, w * 0.22f), end = Offset(w * 0.22f, w * 0.78f), strokeWidth = stroke.toPx(), cap = StrokeCap.Round)
    }
}

@Composable
fun ChevronLeftIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 20.dp, stroke: Dp = 1.7.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val path = Path().apply {
            moveTo(w * 0.68f, h * 0.18f)
            lineTo(w * 0.32f, h * 0.50f)
            lineTo(w * 0.68f, h * 0.82f)
        }
        drawPath(path, color = tint, style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Composable
fun ChevronRightIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 20.dp, stroke: Dp = 1.7.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val path = Path().apply {
            moveTo(w * 0.32f, h * 0.18f)
            lineTo(w * 0.68f, h * 0.50f)
            lineTo(w * 0.32f, h * 0.82f)
        }
        drawPath(path, color = tint, style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Composable
fun HamburgerIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 18.dp, stroke: Dp = 1.8.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx(); val h = size.toPx()
        val strokePx = stroke.toPx()
        drawLine(color = tint, start = Offset(w * 0.15f, h * 0.28f), end = Offset(w * 0.85f, h * 0.28f), strokeWidth = strokePx, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(w * 0.15f, h * 0.50f), end = Offset(w * 0.85f, h * 0.50f), strokeWidth = strokePx, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(w * 0.15f, h * 0.72f), end = Offset(w * 0.85f, h * 0.72f), strokeWidth = strokePx, cap = StrokeCap.Round)
    }
}

@Composable
fun ExpandIcon(tint: Color, modifier: Modifier = Modifier, size: Dp = 16.dp) {
    Canvas(modifier = modifier.size(size)) {
        val w = size.toPx()
        val s = w * 0.14f
        // corners
        drawLine(color = tint, start = Offset(s, s), end = Offset(s, s + w * 0.28f), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(s, s), end = Offset(s + w * 0.28f, s), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(w - s, s), end = Offset(w - s, s + w * 0.28f), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(w - s, s), end = Offset(w - s - w * 0.28f, s), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(s, w - s), end = Offset(s, w - s - w * 0.28f), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(s, w - s), end = Offset(s + w * 0.28f, w - s), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(w - s, w - s), end = Offset(w - s, w - s - w * 0.28f), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
        drawLine(color = tint, start = Offset(w - s, w - s), end = Offset(w - s - w * 0.28f, w - s), strokeWidth = w * 0.11f, cap = StrokeCap.Round)
    }
}
