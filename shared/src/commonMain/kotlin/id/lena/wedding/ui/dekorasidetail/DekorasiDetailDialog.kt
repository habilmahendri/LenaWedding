package id.lena.wedding.ui.dekorasidetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorCard
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.color.ColorSectionAlt
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.icons.ArrowRightIcon
import id.lena.wedding.utils.icons.CheckIcon
import id.lena.wedding.utils.icons.StarIcon
import kotlinx.browser.window

private data class DekorPaket(
    val name: String,
    val subtitle: String,
    val price: String,
    val perEvent: String,
    val image: String,
    val badge: String,
    val features: List<String>,
    val highlight: Boolean = false,
    val note: String
)

private val dekorList = listOf(
    DekorPaket(
        name = "Akad Intimate",
        subtitle = "Akad & lamaran 50–150 tamu",
        price = "Mulai Rp 8jt",
        perEvent = "/event",
        image = "https://images.unsplash.com/photo-1519225421980-715cb0215aed?q=80&w=800&auto=format&fit=crop",
        badge = "INTIMATE",
        features = listOf("Backdrop akad 3x4m", "Kursi pelaminan 2 + 4", "Bunga fresh mix", "Lighting warm + karpet", "Hand bouquet", "Crew dekor H-1"),
        note = "Khidmat & foto-friendly untuk akad"
    ),
    DekorPaket(
        name = "Resepsi Elegan",
        subtitle = "Best seller 300–800 tamu",
        price = "Mulai Rp 18jt",
        perEvent = "/event",
        image = "https://images.unsplash.com/photo-1465495976277-4387d4b0b4c6?q=80&w=800&auto=format&fit=crop",
        badge = "BEST SELLER",
        features = listOf("Pelaminan 8–12m + backdrop", "Bunga fresh premium", "Lighting & LED backdrop", "Meja akad + dekor aisle", "Standing flower + gate", "Crew full day + bongkar"),
        highlight = true,
        note = "Paling diminati — mewah tapi tetap elegan"
    ),
    DekorPaket(
        name = "Full Custom",
        subtitle = "Grand 500–1500 tamu • Tema bebas",
        price = "Mulai Rp 30jt",
        perEvent = "/event",
        image = "https://images.unsplash.com/photo-1520854221256-17451cc331bf?q=80&w=800&auto=format&fit=crop",
        badge = "EKSKLUSIF",
        features = listOf("Konsep custom sesuai tema", "Pelaminan + backdrop + aisle full", "Bunga fresh import mix", "Lighting pro + fog effect", "Photo corner + gate", "Survey & 3D design gratis"),
        note = "Eksklusif, dirancang khusus untukmu"
    )
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DekorasiDetailDialog(onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth().widthIn(max = 980.dp).clip(RoundedCornerShape(24.dp)).background(ColorCard).verticalScroll(rememberScrollState())) {
        val isMobile = maxWidth < 680.dp

        Column(modifier = Modifier.fillMaxWidth()) {
            // Header
            Box(modifier = Modifier.fillMaxWidth().background(ColorSectionAlt).padding(horizontal = 22.dp, vertical = 20.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(modifier = Modifier.width(20.dp).height(1.2.dp).background(ColorAccent))
                        Text("PAKET DEKORASI", color = ColorAccent, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.2.sp)
                        Box(modifier = Modifier.width(20.dp).height(1.2.dp).background(ColorAccent))
                    }
                    Spacer(Modifier.height(10.dp))
                    Text("Dekorasi yang Bercerita", color = ColorPrimaryDark, fontSize = if (isMobile) 20.sp else 22.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(6.dp))
                    Text("Bunga fresh, lighting warm & backdrop yang foto-friendly — adat hingga modern", color = ColorTextMuted, fontSize = 12.5.sp, textAlign = TextAlign.Center, lineHeight = 18.sp, modifier = Modifier.widthIn(max = 520.dp))
                    Spacer(Modifier.height(14.dp))
                    FlowRow(
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(ColorCard).border(0.6.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(20.dp)).padding(horizontal = 12.dp, vertical = 7.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        TrustInline("Bunga Fresh", ColorAccent)
                        Box(modifier = Modifier.width(1.dp).height(14.dp).background(ColorBorder.copy(alpha = 0.4f)))
                        TrustInline("Lighting Pro", ColorAccent)
                        Box(modifier = Modifier.width(1.dp).height(14.dp).background(ColorBorder.copy(alpha = 0.4f)))
                        TrustInline("Custom Tema", ColorAccent)
                    }
                }
            }

            Column(modifier = Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                dekorList.forEach { paket ->
                    DekorCard(paket, isMobile, onPilih = {
                        val msg = "Saya tertarik dengan *Paket Dekorasi ${paket.name}* — ${paket.subtitle} (${paket.price}${paket.perEvent}) yang ada di section Layanan Kami. Mohon info detail & ketersediaan untuk [isi tanggal] di Bekasi/Jabodetabek."
                        val enc = msg.replace(" ", "%20").replace("\n", "%0A").replace(",", "%2C").replace("&", "%26").replace("*", "%2A")
                        window.open("https://wa.me/6281218387400?text=$enc", "_blank")
                    })
                }

                Column(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(ColorSectionAlt.copy(alpha = 0.7f)).border(0.6.dp, ColorBorder.copy(alpha = 0.35f), RoundedCornerShape(14.dp)).padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Bisa request tema & bunga", color = ColorPrimaryDark, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Text("Mau adat Jawa/Sunda/Padang, rustic, modern minimalis, atau glam? Tim dekor survei lokasi & bikin 3D design gratis — bunga mix fresh & lighting disesuaikan venue.", color = ColorTextMuted, fontSize = 12.sp, lineHeight = 18.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(ColorAccent))
                        Text("Free survey & 3D design • Bongkar pasang crew", color = ColorPrimaryDark, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(ColorCard).border(0.7.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(12.dp)).clickable { onDismiss() }.padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Tutup", color = ColorPrimaryDark, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
                Text("Atau hubungi langsung 0812-1838-7400 • Balas <2 jam", color = ColorTextMuted, fontSize = 11.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun DekorCard(paket: DekorPaket, isMobile: Boolean, onPilih: () -> Unit) {
    val cardModifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(ColorCard)
        .border(0.6.dp, if (paket.highlight) ColorAccent.copy(alpha = 0.5f) else ColorBorder.copy(alpha = 0.45f), RoundedCornerShape(20.dp))
        .shadow(if (paket.highlight) 10.dp else 5.dp, RoundedCornerShape(20.dp), ambientColor = ColorBorder.copy(alpha = 0.10f), spotColor = ColorBorder.copy(alpha = 0.10f))

    if (isMobile) {
        Column(modifier = cardModifier) {
            Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                AsyncImage(model = paket.image, contentDescription = paket.name, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth().height(160.dp))
                Box(modifier = Modifier.fillMaxWidth().height(64.dp).align(Alignment.BottomCenter).background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.55f)))))
                Box(modifier = Modifier.align(Alignment.TopStart).padding(10.dp).clip(RoundedCornerShape(8.dp)).background(if (paket.highlight) ColorAccent else ColorPrimaryDark.copy(alpha = 0.92f)).padding(horizontal = 8.dp, vertical = 4.dp)) {
                    Text(paket.badge, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                }
                if (paket.highlight) {
                    Box(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp).clip(RoundedCornerShape(20.dp)).background(Color.White).padding(horizontal = 8.dp, vertical = 4.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) { StarIcon(ColorAccent, size = 10.dp); Text("Rekomendasi", color = ColorPrimaryDark, fontSize = 9.sp, fontWeight = FontWeight.Bold) }
                    }
                }
                Column(modifier = Modifier.align(Alignment.BottomStart).padding(12.dp)) {
                    Text(paket.name, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(paket.subtitle, color = Color.White.copy(alpha = 0.88f), fontSize = 11.sp)
                }
            }
            DekorCardContent(paket, onPilih)
        }
    } else {
        Row(modifier = cardModifier.heightIn(min = 240.dp)) {
            Box(modifier = Modifier.width(380.dp).fillMaxHeight().heightIn(min = 240.dp)) {
                AsyncImage(model = paket.image, contentDescription = paket.name, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                Box(modifier = Modifier.align(Alignment.TopStart).padding(10.dp).clip(RoundedCornerShape(8.dp)).background(if (paket.highlight) ColorAccent else ColorPrimaryDark.copy(alpha = 0.92f)).padding(horizontal = 8.dp, vertical = 4.dp)) {
                    Text(paket.badge, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                }
                if (paket.highlight) {
                    Box(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp).clip(RoundedCornerShape(20.dp)).background(Color.White).padding(horizontal = 8.dp, vertical = 4.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) { StarIcon(ColorAccent, size = 10.dp); Text("Rekomendasi", color = ColorPrimaryDark, fontSize = 9.sp, fontWeight = FontWeight.Bold) }
                    }
                }
            }
            Column(modifier = Modifier.weight(1f).fillMaxHeight().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                DekorCardContent(paket, onPilih, modifier = Modifier.fillMaxHeight())
            }
        }
    }
}

@Composable
private fun DekorCardContent(paket: DekorPaket, onPilih: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(14.dp).fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Column(modifier = Modifier.weight(1f)) {
                Text(paket.name, color = ColorPrimaryDark, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(paket.note, color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Medium)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(paket.price, color = ColorAccent, fontSize = 17.sp, fontWeight = FontWeight.ExtraBold)
                Text(paket.perEvent, color = ColorTextMuted, fontSize = 11.sp)
            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(ColorBorder.copy(alpha = 0.35f)))
        val half = (paket.features.size + 1) / 2
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                paket.features.take(half).forEach { m -> MenuRow(m) }
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                paket.features.drop(half).forEach { m -> MenuRow(m) }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(if (paket.highlight) ColorAccent else ColorPrimaryDark).clickable { onPilih() }.padding(vertical = 11.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(if (paket.highlight) "Konsultasi Paket Ini" else "Pilih Paket", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                ArrowRightIcon(Color.White, size = 12.dp, stroke = 1.4.dp)
            }
        }
    }
}

@Composable
private fun MenuRow(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(modifier = Modifier.size(16.dp).clip(CircleShape).background(ColorAccent.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
            CheckIcon(ColorAccent, size = 9.dp, stroke = 1.3.dp)
        }
        Text(text, color = ColorTextMuted, fontSize = 12.5.sp, lineHeight = 17.sp, maxLines = 2)
    }
}

@Composable
private fun TrustInline(text: String, dotColor: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(dotColor))
        Text(text, color = ColorPrimaryDark, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}
