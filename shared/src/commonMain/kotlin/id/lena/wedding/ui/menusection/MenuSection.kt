package id.lena.wedding.ui.menusection

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import id.lena.wedding.utils.color.ColorSectionAlt
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.data.menuCards
import id.lena.wedding.utils.icons.ArrowRightIcon


@Composable
fun MenuSection(onPrasmananClick: () -> Unit = {}, onPondokanClick: () -> Unit = {}, onMasakanClick: () -> Unit = {}) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth().background(ColorSectionAlt).padding(horizontal = 24.dp, vertical = 64.dp)) {
        val isMobile = maxWidth < 760.dp

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
                Text("CATERING KAMI", color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.5.sp)
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
            }
            Spacer(Modifier.height(14.dp))
            Text("Pilihan Menu Favorit", fontSize = if (isMobile) 26.sp else 32.sp, fontWeight = FontWeight.ExtraBold, color = ColorPrimaryDark)
            Spacer(Modifier.height(10.dp))
            Text("Cita rasa bintang lima, penyajian hangat — disukai tamu dari berbagai usia.", fontSize = 14.sp, color = ColorTextMuted, textAlign = TextAlign.Center)
            Spacer(Modifier.height(36.dp))

            if (isMobile) {
                Column(verticalArrangement = Arrangement.spacedBy(18.dp), modifier = Modifier.fillMaxWidth()) {
                    menuCards.forEachIndexed { i, menu -> MenuCardPremium(menu, i, isMobile, onPrasmananClick, onPondokanClick, onMasakanClick) }
                }
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxWidth()) {
                    menuCards.forEachIndexed { i, menu ->
                        Box(modifier = Modifier.weight(1f)) { MenuCardPremium(menu, i, isMobile, onPrasmananClick, onPondokanClick, onMasakanClick) }
                    }
                }
            }

            Spacer(Modifier.height(28.dp))
            // Bottom note — responsive: di HP jadi wrap/column biar tidak kepotong
            if (isMobile) {
                Column(
                    modifier = Modifier.clip(RoundedCornerShape(14.dp)).background(ColorCard).border(0.7.dp, ColorBorder.copy(alpha = 0.6f), RoundedCornerShape(14.dp)).padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF4CAF50)))
                        Text("Bisa request menu custom & test food gratis", color = ColorPrimaryDark, fontSize = 12.5.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center)
                    }
                    Text("•  Hubungi kami", color = ColorAccent, fontSize = 12.5.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                Row(
                    modifier = Modifier.clip(RoundedCornerShape(14.dp)).background(ColorCard).border(0.7.dp, ColorBorder.copy(alpha = 0.6f), RoundedCornerShape(14.dp)).padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF4CAF50)))
                    Text("Bisa request menu custom & test food gratis", color = ColorPrimaryDark, fontSize = 12.5.sp, fontWeight = FontWeight.Medium)
                    Text("•  Hubungi kami", color = ColorAccent, fontSize = 12.5.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun MenuCardPremium(menu: id.lena.wedding.utils.data.MenuCard, index: Int, isMobile: Boolean = false, onPrasmananClick: () -> Unit = {}, onPondokanClick: () -> Unit = {}, onMasakanClick: () -> Unit = {}) {
    val desc = listOf(
        "100+ pax • Buffet lengkap dengan sup, nasi, lauk & dessert",
        "Live cooking • Siomay, bakso, sate & jajanan pasar favorit",
        "Ala carte • Ayam, daging, seafood dengan bumbu nusantara"
    )[index % 3]
    val count = listOf("120+ Menu", "40+ Stall", "80+ Lauk")[index % 3]

    val ctx = LocalPlatformContext.current
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(ColorCard).border(0.6.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(20.dp)).shadow(if (isMobile) 3.dp else 6.dp, RoundedCornerShape(20.dp), ambientColor = ColorBorder.copy(alpha = 0.12f), spotColor = ColorBorder.copy(alpha = 0.12f))
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(180.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(ctx).data(if (isMobile) menu.imageUrl.replace("500/400", "320/240") else menu.imageUrl).size(320).scale(Scale.FILL).crossfade(false).build(),
                contentDescription = menu.title, contentScale = ContentScale.Crop, filterQuality = androidx.compose.ui.graphics.FilterQuality.Low, placeholder = androidx.compose.ui.graphics.painter.ColorPainter(ColorSectionAlt),
                modifier = Modifier.fillMaxWidth().height(180.dp)
            )
            Box(modifier = Modifier.fillMaxWidth().height(70.dp).align(Alignment.BottomCenter).background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xAA2D1E3A)))))
            Box(
                modifier = Modifier.align(Alignment.TopEnd).padding(10.dp).clip(RoundedCornerShape(20.dp)).background(Color.White.copy(alpha = 0.92f)).padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(count, color = ColorPrimaryDark, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
            Text(menu.title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.BottomStart).padding(14.dp))
        }
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(desc, fontSize = 12.5.sp, color = ColorTextMuted, lineHeight = 18.sp)
            Spacer(Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.clickable {
                    when (index) {
                        0 -> onPrasmananClick()
                        1 -> onPondokanClick()
                        2 -> onMasakanClick()
                    }
                }
            ) {
                Text("Lihat menu lengkap", color = ColorAccent, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                ArrowRightIcon(ColorAccent, size = 12.dp, stroke = 1.5.dp)
            }
        }
    }
}