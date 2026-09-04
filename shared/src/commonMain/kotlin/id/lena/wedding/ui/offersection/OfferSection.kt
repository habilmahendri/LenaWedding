package id.lena.wedding.ui.offersection

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
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorAccentLight
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorCard
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.data.offerCards
import id.lena.wedding.utils.icons.ArrowRightIcon
import id.lena.wedding.utils.icons.CheckIcon
import kotlinx.browser.window


@Composable
fun OfferSection(onCateringClick: () -> Unit = {}) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth().background(ColorCard).padding(horizontal = 24.dp, vertical = 64.dp)) {
        val isMobile = maxWidth < 760.dp

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            // Header
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
                Text("LAYANAN KAMI", color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.5.sp)
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
            }
            Spacer(Modifier.height(14.dp))
            Text("Apa yang Kami Tawarkan", fontSize = if (isMobile) 26.sp else 32.sp, fontWeight = FontWeight.ExtraBold, color = ColorPrimaryDark)
            Spacer(Modifier.height(10.dp))
            Text(
                "Tiga pilar utama untuk mewujudkan pernikahan impian — pilih paket atau custom sesuai keinginanmu.",
                fontSize = 14.sp, color = ColorTextMuted, textAlign = TextAlign.Center, modifier = Modifier.width(560.dp)
            )
            Spacer(Modifier.height(36.dp))

            if (isMobile) {
                Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxWidth()) {
                    offerCards.forEachIndexed { index, card -> OfferCardPremium(card, index, onCateringClick) }
                }
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(22.dp), modifier = Modifier.fillMaxWidth()) {
                    offerCards.forEachIndexed { index, card ->
                        Box(modifier = Modifier.weight(1f)) { OfferCardPremium(card, index, onCateringClick) }
                    }
                }
            }
        }
    }
}

@Composable
private fun OfferCardPremium(card: id.lena.wedding.utils.data.OfferCard, index: Int, onCateringClick: () -> Unit = {}) {
    val badge = listOf("POPULER", "BEST VALUE", "EKSKLUSIF")[index % 3]
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(22.dp)).background(ColorCard).border(0.6.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(22.dp)).shadow(8.dp, RoundedCornerShape(22.dp), ambientColor = ColorBorder.copy(alpha = 0.12f), spotColor = ColorBorder.copy(alpha = 0.12f))
    ) {
        // Image with overlay & badge
        Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
            AsyncImage(model = card.imageUrl, contentDescription = card.title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth().height(200.dp))
            Box(modifier = Modifier.fillMaxWidth().height(80.dp).align(Alignment.BottomCenter).background(Brush.verticalGradient(listOf(Color.Transparent, Color(0x882D1E3A)))))
            // Top badge
            Box(
                modifier = Modifier.align(Alignment.TopStart).padding(12.dp).clip(RoundedCornerShape(8.dp)).background(if (index == 0) ColorAccent else ColorPrimaryDark).padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(badge, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            }
            // Title overlay bottom
            Column(modifier = Modifier.align(Alignment.BottomStart).padding(14.dp)) {
                Text(card.title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(card.subtitle, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
            }
        }
        Column(modifier = Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            // Features
            val features = when (index) {
                0 -> listOf("Dekorasi premium & pelaminan", "Dokumentasi foto & video", "WO full-day standby")
                1 -> listOf("Menu prasmanan 10+ pilihan", "Pondokan & stall live-cooking", "Pramusaji profesional")
                else -> listOf("Konsep custom sesuai tema", "Bunga fresh & lighting", "Team dekor berpengalaman")
            }
            features.forEach { f ->
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.size(18.dp).clip(CircleShape).background(ColorAccent.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) {
                        CheckIcon(ColorAccent, size = 10.dp, stroke = 1.4.dp)
                    }
                    Text(f, fontSize = 13.sp, color = ColorTextMuted)
                }
            }
            Spacer(Modifier.height(4.dp))
            // CTA — Wedding direct WA, Catering buka pilihan paket
            val isWedding = index == 0
            val isCatering = index == 1
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
                    .background(if (isWedding) ColorAccent else ColorPrimaryDark)
                    .then(
                        when {
                            isWedding -> Modifier.clickable { openWeddingPackageWA() }
                            isCatering -> Modifier.clickable { onCateringClick() }
                            else -> Modifier
                        }
                    )
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(if (isWedding) "Konsultasi Sekarang" else "Lihat Detail", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                ArrowRightIcon(Color.White, size = 12.dp, stroke = 1.4.dp)
            }
            if (index != 0) {
                Text("Mulai custom sesuai budget", fontSize = 11.sp, color = ColorTextMuted, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

private fun openWeddingPackageWA() {
    val phone = "6281218387400"
    val message = "Saya tertarik dengan *Paket Wedding*"
    val enc = message.replace(" ", "%20").replace(",", "%2C").replace("&", "%26").replace("*", "%2A").replace("—", "%E2%80%94")
    window.open("https://wa.me/$phone?text=$enc", "_blank")
}