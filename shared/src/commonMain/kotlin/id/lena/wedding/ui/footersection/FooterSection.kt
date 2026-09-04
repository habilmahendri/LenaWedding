package id.lena.wedding.ui.footersection

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
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.data.navItems
import id.lena.wedding.utils.icons.ArrowRightIcon
import id.lena.wedding.utils.icons.ClockIcon
import id.lena.wedding.utils.icons.EmailIcon
import id.lena.wedding.utils.icons.LocationIcon
import id.lena.wedding.utils.icons.PhoneIcon
import kotlinx.browser.window


@Composable
fun FooterSection() {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth().background(ColorPrimaryDark)) {
        val isMobile = maxWidth < 760.dp

        Column(modifier = Modifier.fillMaxWidth()) {
            // Gold top accent
            Box(modifier = Modifier.fillMaxWidth().height(3.dp).background(Brush.horizontalGradient(listOf(Color.Transparent, ColorAccent, Color.Transparent))))

            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 40.dp)) {
                if (isMobile) {
                    // Mobile stacked
                    Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
                        FooterBrand()
                        FooterLinks()
                        FooterContact()
                        FooterCta()
                    }
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(32.dp), modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.weight(1.4f)) { FooterBrand() }
                        Box(modifier = Modifier.weight(0.8f)) { FooterLinks() }
                        Box(modifier = Modifier.weight(1.1f)) { FooterContact() }
                        Box(modifier = Modifier.weight(1f)) { FooterCta() }
                    }
                }

                Spacer(Modifier.height(32.dp))
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFF3E2A52)))
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("© 2026 Lena Wedding & Catering. All rights reserved.", color = Color(0xFF9A8FA8), fontSize = 11.sp)
                    if (!isMobile) {
                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            Text("Privacy", color = Color(0xFF9A8FA8), fontSize = 11.sp)
                            Text("Terms", color = Color(0xFF9A8FA8), fontSize = 11.sp)
                            Text("Instagram", color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable { window.open("https://www.instagram.com/lenawedding/", "_blank") })
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FooterBrand() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text("LENA", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.5.sp)
            Spacer(Modifier.width(6.dp))
            Text("WEDDING", color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp, modifier = Modifier.padding(bottom = 3.dp))
        }
        Text("& CATERING  —  EST. 2002", color = Color(0xFFB8A9C8), fontSize = 9.sp, letterSpacing = 2.sp, fontWeight = FontWeight.Medium)
        Text(
            "Merayakan cinta sejak 2002. Wedding organizer premium di Rawalumbu, Bekasi — melayani Jabodetabek — kami urus detail, kamu nikmati momen.",
            color = Color(0xFFC9BAD9), fontSize = 12.5.sp, lineHeight = 19.sp
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            val socials = listOf(
                Triple("IG", "https://www.instagram.com/lenawedding/", "Instagram"),
                Triple("WA", "https://wa.me/6281218387400", "WhatsApp"),
                Triple("TT", "https://www.instagram.com/lenawedding/", "TikTok")
            )
            socials.forEach { (label, url, _) ->
                Box(
                    modifier = Modifier.size(30.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.08f)).border(0.7.dp, Color.White.copy(alpha = 0.12f), CircleShape).clickable { window.open(url, "_blank") },
                    contentAlignment = Alignment.Center
                ) {
                    Text(label, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun FooterLinks() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("JELAJAHI", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
        Spacer(Modifier.height(4.dp))
        navItems.forEach {
            Text(it.label, color = Color(0xFFC9BAD9), fontSize = 13.sp, modifier = Modifier.padding(vertical = 2.dp))
        }
    }
}

@Composable
private fun FooterContact() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("HUBUNGI KAMI", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
        Spacer(Modifier.height(4.dp))
        FooterContactRow("location", "Jl. Lumbu Barat III A No.285\nRawalumbu, Bekasi 17116")
        FooterContactRow("email", "info@lenawo.com")
        FooterContactRow("phone", "+62 812-1838-7400 (Admin)")
        FooterContactRow("clock", "Senin — Minggu 08.00 - 21.00")
    }
}

@Composable
private fun FooterContactRow(icon: String, text: String) {
    val isLocation = icon == "location"
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top,
        modifier = if (isLocation) Modifier.clickable { window.open("https://www.google.com/maps/search/?api=1&query=Lena+Wedding+Organizer+Rawalumbu+Bekasi", "_blank") } else Modifier
    ) {
        Box(modifier = Modifier.size(14.dp), contentAlignment = Alignment.Center) {
            when (icon) {
                "location" -> LocationIcon(ColorAccent, size = 12.dp)
                "email" -> EmailIcon(ColorAccent, size = 12.dp)
                "phone" -> PhoneIcon(ColorAccent, size = 12.dp)
                "clock" -> ClockIcon(ColorAccent, size = 12.dp)
                else -> Text(icon, fontSize = 11.sp)
            }
        }
        Text(text, color = Color(0xFFC9BAD9), fontSize = 12.5.sp, lineHeight = 18.sp)
    }
}

@Composable
private fun FooterCta() {
    Column(
        modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(Color.White.copy(alpha = 0.06f)).border(0.7.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(16.dp)).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Konsultasi Gratis", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Text("Cerita dulu, kami dengarkan. Tanpa komitmen, tanpa hard selling.", color = Color(0xFFC9BAD9), fontSize = 11.5.sp, lineHeight = 17.sp)
        Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(ColorAccent).padding(vertical = 10.dp), contentAlignment = Alignment.Center) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) { Text("Chat WhatsApp", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold); ArrowRightIcon(Color.White, size = 12.dp, stroke = 1.4.dp) }
        }
        Text("Respon rata-rata < 2 jam", color = Color(0xFF9A8FA8), fontSize = 10.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}