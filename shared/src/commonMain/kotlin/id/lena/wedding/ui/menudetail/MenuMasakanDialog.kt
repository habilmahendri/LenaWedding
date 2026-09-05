package id.lena.wedding.ui.menudetail

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorCard
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.color.ColorSectionAlt
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.icons.ArrowRightIcon
import kotlinx.browser.window

private data class MasakanMenuFoto(val name: String, val seed: String)

private val masakanMenus = listOf(
    MasakanMenuFoto("Ayam Bakar Madu", "ayam-bakar"),
    MasakanMenuFoto("Ayam Goreng Kremes", "ayam-goreng"),
    MasakanMenuFoto("Rendang Padang", "rendang"),
    MasakanMenuFoto("Gurame Asam Manis", "gurame"),
    MasakanMenuFoto("Udang Saus Padang", "udang"),
    MasakanMenuFoto("Cumi Bakar", "cumi"),
    MasakanMenuFoto("Capcay", "capcay"),
    MasakanMenuFoto("Sop Buntut", "sop-buntut"),
    MasakanMenuFoto("Soto Ayam", "soto-ayam"),
    MasakanMenuFoto("Gado-Gado", "gado"),
    MasakanMenuFoto("Sambal + Kerupuk", "sambal"),
    MasakanMenuFoto("Acar", "acar"),
)

@Composable
fun MenuMasakanDialog(onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth().widthIn(max = 980.dp).clip(RoundedCornerShape(24.dp)).background(ColorCard).verticalScroll(rememberScrollState())) {
        val isMobile = maxWidth < 680.dp
        val cols = if (isMobile) 2 else 3
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth().background(ColorSectionAlt).padding(horizontal = 22.dp, vertical = 20.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(modifier = Modifier.width(20.dp).height(1.2.dp).background(ColorAccent))
                        Text("MENU MASAKAN", color = ColorAccent, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.2.sp)
                        Box(modifier = Modifier.width(20.dp).height(1.2.dp).background(ColorAccent))
                    }
                    Spacer(Modifier.height(10.dp))
                    Text("Aneka Lauk & Masakan", color = ColorPrimaryDark, fontSize = if (isMobile) 20.sp else 22.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(6.dp))
                    Text("Ayam, daging, seafood & sayur — bumbu nusantara, bisa ala carte atau paket", color = ColorTextMuted, fontSize = 12.5.sp, textAlign = TextAlign.Center, lineHeight = 18.sp, modifier = Modifier.widthIn(max = 520.dp))
                }
            }
            Column(modifier = Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                    masakanMenus.chunked(cols).forEach { row ->
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                            row.forEach { item -> MasakanMenuFotoCard(item, Modifier.weight(1f)) }
                            repeat(cols - row.size) { Spacer(Modifier.weight(1f)) }
                        }
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(ColorAccent).clickable {
                        val msg = "Saya tertarik dengan *Menu Masakan Ala Carte* yang ada di section Pilihan Menu Catering. Mohon info harga & paket untuk [isi jumlah tamu] di Bekasi/Jabodetabek."
                        val enc = msg.replace(" ", "%20").replace(",", "%2C").replace("&", "%26").replace("*", "%2A")
                        window.open("https://wa.me/6281218387400?text=$enc", "_blank")
                    }.padding(vertical = 13.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("Konsultasi Menu Masakan", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        ArrowRightIcon(Color.White, size = 12.dp, stroke = 1.4.dp)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(ColorCard).border(0.7.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(12.dp)).clickable { onDismiss() }.padding(vertical = 11.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Tutup", color = ColorPrimaryDark, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
                Text("Atau hubungi 0812-1838-7400 • Bisa ala carte atau paket", color = ColorTextMuted, fontSize = 11.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun MasakanMenuFotoCard(item: MasakanMenuFoto, modifier: Modifier = Modifier) {
    val ctx = LocalPlatformContext.current
    Column(
        modifier = modifier.clip(RoundedCornerShape(14.dp)).background(ColorCard).border(0.6.dp, ColorBorder.copy(alpha = 0.45f), RoundedCornerShape(14.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(ctx).data("https://picsum.photos/seed/${item.seed}/400/300").size(320).scale(Scale.FILL).crossfade(false).build(),
            contentDescription = item.name, contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(110.dp).clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
        )
        Text(item.name, color = ColorPrimaryDark, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp), lineHeight = 16.sp, maxLines = 2)
    }
}
