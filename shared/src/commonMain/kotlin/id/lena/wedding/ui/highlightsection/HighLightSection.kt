package id.lena.wedding.ui.highlightsection

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorAccentLight
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorCard
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.color.ColorSectionAlt
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.data.highlights
import id.lena.wedding.utils.icons.DiamondIcon
import id.lena.wedding.utils.icons.HeartIcon
import id.lena.wedding.utils.icons.SparkleIcon


@Composable
fun HighlightSection() {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth().background(ColorSectionAlt).padding(horizontal = 24.dp, vertical = 56.dp)) {
        val isMobile = maxWidth < 760.dp

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text("KENAPA MEMILIH KAMI", color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.5.sp)
            Spacer(Modifier.height(10.dp))
            Text("Keunggulan yang Bikin Tenang", fontSize = if (isMobile) 24.sp else 30.sp, fontWeight = FontWeight.ExtraBold, color = ColorPrimaryDark, textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Text("Kami tidak hanya mengurus acara — kami menjaga perasaan dan momen berhargamu.", fontSize = 13.5.sp, color = ColorTextMuted, textAlign = TextAlign.Center)
            Spacer(Modifier.height(32.dp))

            val icons = listOf("sparkle", "heart", "diamond")
            if (isMobile) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                    highlights.forEachIndexed { i, h -> HighlightCard(h.title, h.desc, icons[i % icons.size]) }
                }
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxWidth()) {
                    highlights.forEachIndexed { i, h ->
                        Box(modifier = Modifier.weight(1f)) { HighlightCard(h.title, h.desc, icons[i % icons.size]) }
                    }
                }
            }
        }
    }
}

@Composable
private fun HighlightCard(title: String, desc: String, icon: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(ColorCard).border(0.6.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(20.dp)).shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = ColorBorder.copy(alpha = 0.10f), spotColor = ColorBorder.copy(alpha = 0.10f)).padding(22.dp)
    ) {
        Box(
            modifier = Modifier.size(56.dp).clip(CircleShape).background(ColorAccentLight).border(0.6.dp, ColorAccent.copy(alpha = 0.18f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            when (icon) {
                "heart" -> HeartIcon(ColorAccent, size = 18.dp)
                "diamond" -> DiamondIcon(ColorAccent, size = 16.dp)
                else -> SparkleIcon(ColorAccent, size = 18.dp)
            }
        }
        Spacer(Modifier.height(14.dp))
        Text(title, fontSize = 15.5.sp, fontWeight = FontWeight.Bold, color = ColorPrimaryDark, textAlign = TextAlign.Center)
        Spacer(Modifier.height(8.dp))
        Box(modifier = Modifier.width(28.dp).height(2.dp).clip(RoundedCornerShape(1.dp)).background(ColorAccent.copy(alpha = 0.5f)))
        Spacer(Modifier.height(8.dp))
        Text(desc, fontSize = 13.sp, color = ColorTextMuted, textAlign = TextAlign.Center, lineHeight = 19.sp)
    }
}