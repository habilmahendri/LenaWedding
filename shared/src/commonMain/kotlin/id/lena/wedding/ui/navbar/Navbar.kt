package id.lena.wedding.ui.navbar

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.lena.wedding.utils.MobileBreakpoint
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorCard
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.data.navItems


@Composable
fun NavBar(onNavClick: (String) -> Unit = {}) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, clip = false)
            .background(ColorCard)
    ) {
        val isMobile = maxWidth < MobileBreakpoint
        var menuExpanded by remember { mutableStateOf(false) }

        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo — serif feel with gold accent line
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text("LENA", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = ColorPrimaryDark, letterSpacing = 1.5.sp)
                            Spacer(Modifier.width(6.dp))
                            Text("WEDDING", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = ColorAccent, letterSpacing = 2.5.sp, modifier = Modifier.padding(bottom = 4.dp))
                        }
                        Text("& CATERING", fontSize = 9.sp, fontWeight = FontWeight.Medium, color = Color(0xFF9A8FA8), letterSpacing = 3.sp)
                    }
                    if (!isMobile) {
                        Box(modifier = Modifier.padding(start = 16.dp).width(1.dp).height(32.dp).background(ColorBorder))
                    }
                }

                if (isMobile) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (menuExpanded) ColorPrimaryDark else Color(0xFFF8F3EE))
                            .border(0.7.dp, if (menuExpanded) ColorPrimaryDark else ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                            .clickable { menuExpanded = !menuExpanded }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            if (menuExpanded) "\u2715" else "\u2630",
                            fontSize = 16.sp,
                            color = if (menuExpanded) Color.White else ColorPrimaryDark
                        )
                    }
                } else {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(28.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                            navItems.forEachIndexed { index, item ->
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        item.label,
                                        color = if (index == 0) ColorPrimaryDark else ColorTextMuted,
                                        fontSize = 13.5.sp,
                                        fontWeight = if (index == 0) FontWeight.SemiBold else FontWeight.Medium,
                                        modifier = Modifier.clickable { onNavClick(item.label) }
                                    )
                                    if (index == 0) Box(modifier = Modifier.padding(top = 2.dp).width(18.dp).height(2.dp).clip(RoundedCornerShape(1.dp)).background(ColorAccent))
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(24.dp))
                                .background(ColorAccent)
                                .clickable { onNavClick("Kontak Kami") }
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                        ) {
                            Text("Konsultasi Gratis", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            if (isMobile && menuExpanded) {
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(ColorBorder))
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    navItems.forEachIndexed { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (index == 0) Color(0xFFF8F3EE) else Color.Transparent)
                                .clickable { menuExpanded = false; onNavClick(item.label) }
                                .padding(horizontal = 12.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.width(3.dp).height(16.dp).clip(RoundedCornerShape(2.dp)).background(if (index == 0) ColorAccent else Color.Transparent))
                            Spacer(Modifier.width(10.dp))
                            Text(item.label, color = if (index == 0) ColorPrimaryDark else ColorTextMuted, fontSize = 14.sp, fontWeight = if (index == 0) FontWeight.SemiBold else FontWeight.Medium)
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(ColorAccent).clickable { menuExpanded = false; onNavClick("Kontak Kami") }.padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Konsultasi Gratis — Hubungi Kami", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}
