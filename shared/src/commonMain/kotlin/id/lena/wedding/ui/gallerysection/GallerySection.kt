package id.lena.wedding.ui.gallerysection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.size.Scale
import coil3.request.crossfade
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorCard
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.data.GalleryPhoto
import id.lena.wedding.utils.data.galleryPhotos
import id.lena.wedding.utils.icons.ArrowRightIcon


@Composable
fun GallerySection(onPhotoClick: (Int) -> Unit = {}) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth().background(ColorCard).padding(horizontal = 24.dp, vertical = 64.dp)) {
        val isMobile = maxWidth < 760.dp

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
                Text("GALERI KAMI", color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.5.sp)
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
            }
            Spacer(Modifier.height(14.dp))
            Text("Momen Bahagia Mereka", fontSize = if (isMobile) 26.sp else 32.sp, fontWeight = FontWeight.ExtraBold, color = ColorPrimaryDark, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Text("Setiap foto adalah cerita cinta yang kami bantu wujudkan dengan sepenuh hati.", fontSize = 14.sp, color = ColorTextMuted, textAlign = TextAlign.Center)
            Spacer(Modifier.height(18.dp))
            // Filter chips mock
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Semua", "Akad", "Resepsi", "Dekorasi").forEachIndexed { i, label ->
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(if (i == 0) ColorPrimaryDark else Color(0xFFF8F3EE)).border(0.7.dp, if (i == 0) ColorPrimaryDark else ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(20.dp)).padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Text(label, color = if (i == 0) Color.White else ColorPrimaryDark, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))

            // Masonry beneran — 3 kolom desktop / 2 kolom HP, ringan (tanpa nested lazy) tapi tetap staggered rapi
            val columns = if (isMobile) 2 else 3
            val spacing = if (isMobile) 10.dp else 14.dp
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing),
                verticalAlignment = Alignment.Top
            ) {
                repeat(columns) { col ->
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(spacing)
                    ) {
                        galleryPhotos.forEachIndexed { idx, photo ->
                            if (idx % columns == col) {
                                val smallUrl = if (isMobile) photo.url.replace("500/500", "360/360") else photo.url
                                val ctx = LocalPlatformContext.current
                                Box(
                                    modifier = Modifier.fillMaxWidth().height(photo.aspectHeight(isMobile)).clip(RoundedCornerShape(16.dp)).clickable { onPhotoClick(idx) }
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(ctx).data(smallUrl).size(360).scale(Scale.FILL).crossfade(false).build(),
                                        contentDescription = photo.caption, contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxWidth().height(photo.aspectHeight(isMobile))
                                    )
                                    Box(modifier = Modifier.fillMaxWidth().height(70.dp).align(Alignment.BottomCenter).background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xCC2D1E3A)))))
                                    Text(photo.caption, color = Color.White, fontSize = 11.5.sp, fontWeight = FontWeight.Medium, modifier = Modifier.align(Alignment.BottomStart).padding(10.dp), lineHeight = 14.sp)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(22.dp))
            Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).border(0.7.dp, ColorPrimaryDark.copy(alpha = 0.8f), RoundedCornerShape(12.dp)).padding(horizontal = 22.dp, vertical = 10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) { Text("Lihat Galeri Lengkap", color = ColorPrimaryDark, fontSize = 13.sp, fontWeight = FontWeight.SemiBold); ArrowRightIcon(ColorPrimaryDark, size = 12.dp, stroke = 1.4.dp) }
            }
        }
    }
}

private fun GalleryPhoto.aspectHeight(isMobile: Boolean): Dp {
    val baseHeights = if (isMobile) listOf(160.dp, 220.dp, 190.dp, 240.dp) else listOf(200.dp, 280.dp, 240.dp, 320.dp)
    return baseHeights[id.hashCode().mod(baseHeights.size)]
}