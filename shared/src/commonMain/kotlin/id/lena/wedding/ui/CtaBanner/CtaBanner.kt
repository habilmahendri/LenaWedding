package id.lena.wedding.ui.CtaBanner

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorPrimaryDark
import kotlinx.browser.window
import id.lena.wedding.utils.icons.ChatIcon
import id.lena.wedding.utils.icons.PhoneIcon


@Composable
private fun CtaText() {
    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Rayakan Cinta,\nTanpa Repot.", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 32.sp)
        Text(
            "Tim kami siap wujudkan hari bahagiamu — dari konsep hingga piring terakhir. Konsultasi sekarang, gratis.",
            color = Color(0xFFE3D9EF), fontSize = 13.5.sp, lineHeight = 20.sp
        )
    }
}


@Composable
private fun CtaTrust(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text(label, color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp)
    }
}

private fun openWhatsApp(phone: String, msg: String) {
    // Simple encode — wasmJs-safe (no kotlin.js)
    val encoded = msg.replace(" ", "%20").replace(",", "%2C").replace("\n", "%0A")
    window.open("https://wa.me/$phone?text=$encoded", "_blank")
}