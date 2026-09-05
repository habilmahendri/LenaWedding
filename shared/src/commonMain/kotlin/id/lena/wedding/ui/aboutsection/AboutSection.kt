package id.lena.wedding.ui.aboutsection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.size.Scale
import coil3.request.crossfade
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorAccentLight
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.color.ColorSectionAlt
import id.lena.wedding.utils.icons.CheckIcon

@Composable
fun AboutSection() {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth().background(id.lena.wedding.utils.color.ColorBackground).padding(horizontal = 24.dp, vertical = 64.dp)) {
        val isMobile = maxWidth < 760.dp

        Column(modifier = Modifier.fillMaxWidth().align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            // Eyebrow
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
                Text("TENTANG KAMI", color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.5.sp)
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
            }
            Spacer(Modifier.height(16.dp))
            Text("Dua Dekade Lebih Merayakan Cinta", fontSize = if (isMobile) 26.sp else 32.sp, fontWeight = FontWeight.ExtraBold, color = ColorPrimaryDark, lineHeight = if (isMobile) 30.sp else 36.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            Spacer(Modifier.height(12.dp))
            Text(
                "Berdiri sejak 2002 di Rawalumbu, Bekasi — spesialis traditional wedding Jawa, Sunda, Padang & intimate hingga grand wedding 50–1500 tamu.",
                fontSize = 14.sp, color = ColorTextMuted, textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(Modifier.height(40.dp))

            if (isMobile) {
                // Mobile: image stack then text — 600w + no crossfade biar enteng
                val ctx = LocalPlatformContext.current
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = ImageRequest.Builder(ctx).data("https://images.unsplash.com/photo-1511285560929-80b456fea0bc?q=80&w=600&auto=format&fit=crop").size(480).scale(Scale.FILL).crossfade(false).build(),
                        contentDescription = "Tentang kami", contentScale = ContentScale.Crop, filterQuality = androidx.compose.ui.graphics.FilterQuality.Low, placeholder = androidx.compose.ui.graphics.painter.ColorPainter(ColorSectionAlt),
                        modifier = Modifier.fillMaxWidth().height(280.dp).clip(RoundedCornerShape(20.dp))
                    )
                    Box(
                        modifier = Modifier.align(Alignment.BottomStart).padding(14.dp).clip(RoundedCornerShape(16.dp)).background(id.lena.wedding.utils.color.ColorCard).border(0.5.dp, ColorBorder.copy(alpha = 0.35f), RoundedCornerShape(16.dp)).shadow(6.dp, RoundedCornerShape(16.dp), ambientColor = ColorBorder.copy(alpha = 0.10f), spotColor = ColorBorder.copy(alpha = 0.10f)).padding(horizontal = 14.dp, vertical = 10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(ColorAccent), contentAlignment = Alignment.Center) {
                                Text("24", color = androidx.compose.ui.graphics.Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                            Column {
                                Text("Tahun Pengalaman", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ColorPrimaryDark)
                                Text("200+ pernikahan sukses", fontSize = 11.sp, color = ColorTextMuted)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(28.dp))
                AboutText()
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Image collage side — desktop 800w, tetap hemat
                    val ctx2 = LocalPlatformContext.current
                    Box(modifier = Modifier.weight(1f).height(420.dp)) {
                        AsyncImage(
                            model = ImageRequest.Builder(ctx2).data("https://images.unsplash.com/photo-1511285560929-80b456fea0bc?q=80&w=800&auto=format&fit=crop").size(600).scale(Scale.FILL).crossfade(false).build(),
                            contentDescription = "Tentang kami", contentScale = ContentScale.Crop, filterQuality = androidx.compose.ui.graphics.FilterQuality.Low, placeholder = androidx.compose.ui.graphics.painter.ColorPainter(ColorSectionAlt),
                            modifier = Modifier.fillMaxWidth(0.92f).height(380.dp).clip(RoundedCornerShape(22.dp)).align(Alignment.TopStart)
                        )
                        Box(
                            modifier = Modifier.size(160.dp).clip(RoundedCornerShape(18.dp)).background(ColorAccentLight).border(4.dp, id.lena.wedding.utils.color.ColorCard, RoundedCornerShape(18.dp)).shadow(8.dp, RoundedCornerShape(18.dp)).align(Alignment.BottomEnd).padding(4.dp)
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(ctx2).data("https://images.unsplash.com/photo-1465495976277-4387d4b0b4c6?q=80&w=400&auto=format&fit=crop").size(300).scale(Scale.FILL).crossfade(false).build(),
                                contentDescription = null, contentScale = ContentScale.Crop, filterQuality = androidx.compose.ui.graphics.FilterQuality.Low, placeholder = androidx.compose.ui.graphics.painter.ColorPainter(ColorSectionAlt),
                                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp))
                            )
                        }
                        // Experience badge floating top-right
                        Box(
                            modifier = Modifier.align(Alignment.TopEnd).padding(top = 18.dp, end = 8.dp).clip(RoundedCornerShape(14.dp)).background(id.lena.wedding.utils.color.ColorCard).border(0.5.dp, ColorBorder.copy(alpha = 0.35f), RoundedCornerShape(14.dp)).shadow(6.dp, RoundedCornerShape(14.dp), ambientColor = ColorBorder.copy(alpha = 0.10f), spotColor = ColorBorder.copy(alpha = 0.10f)).padding(horizontal = 16.dp, vertical = 12.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("24", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = ColorAccent)
                                Text("TAHUN", fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp, color = ColorPrimaryDark)
                            }
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        AboutText()
                    }
                }
            }
        }
    }
}

@Composable
private fun AboutText() {
    Text(
        "Lena Wedding & Catering lahir 2002 di Bekasi — dari tim kecil yang percaya bahwa setiap cinta punya cerita unik yang layak dirayakan dengan sempurna.",
        fontSize = 15.sp, color = ColorPrimaryDark, fontWeight = FontWeight.Medium, lineHeight = 24.sp
    )
    Spacer(Modifier.height(14.dp))
    Text(
        "Didukung chef berpengalaman, dekorator, dan wedding planner yang obsess pada detail. Kami bukan hanya menyediakan paket — kami merancang pengalaman. Dari konsultasi pertama hingga pelaminan terakhir dibereskan, kamu tidak perlu repot memikirkan apa pun.",
        fontSize = 14.sp, color = ColorTextMuted, lineHeight = 22.sp
    )
    Spacer(Modifier.height(20.dp))
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        AboutCheck("Konsultasi & survey lokasi gratis")
        AboutCheck("Paket fleksibel — bisa custom sesuai budget")
        AboutCheck("Tim standby H-7 hingga acara selesai")
    }
    Spacer(Modifier.height(22.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
        AboutStat("200+", "Pernikahan")
        Box(modifier = Modifier.width(1.dp).height(36.dp).background(ColorBorder))
        AboutStat("98%", "Kepuasan")
        Box(modifier = Modifier.width(1.dp).height(36.dp).background(ColorBorder))
        AboutStat("50+", "Vendor")
    }
}

@Composable
private fun AboutCheck(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(modifier = Modifier.size(22.dp).clip(CircleShape).background(ColorAccent.copy(alpha = 0.15f)), contentAlignment = Alignment.Center) {
            CheckIcon(ColorAccent, size = 12.dp, stroke = 1.6.dp)
        }
        Text(text, fontSize = 13.5.sp, color = ColorPrimaryDark, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun AboutStat(value: String, label: String) {
    Column {
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = ColorPrimaryDark)
        Text(label, fontSize = 11.sp, color = ColorTextMuted, letterSpacing = 0.6.sp)
    }
}