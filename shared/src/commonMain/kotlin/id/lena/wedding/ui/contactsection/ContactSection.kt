package id.lena.wedding.ui.contactsection

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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorBackground
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorCard
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.icons.ArrowRightIcon
import id.lena.wedding.utils.icons.CalendarIcon
import id.lena.wedding.utils.icons.ChatIcon
import id.lena.wedding.utils.icons.ClockIcon
import id.lena.wedding.utils.icons.EmailIcon
import id.lena.wedding.utils.icons.LocationIcon
import id.lena.wedding.utils.icons.PhoneIcon
import kotlinx.browser.window

@Composable
fun ContactSection() {
    var nama by remember { mutableStateOf("") }
    var wa by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var paket by remember { mutableStateOf("") }
    var pesan by remember { mutableStateOf("") }

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().background(ColorBackground).padding(horizontal = 24.dp, vertical = 56.dp)
    ) {
        val isMobile = maxWidth < 760.dp

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            // Header
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
                Text("KONTAK KAMI", color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.5.sp)
                Box(modifier = Modifier.width(28.dp).height(1.5.dp).background(ColorAccent))
            }
            Spacer(Modifier.height(14.dp))
            Text("Mari Wujudkan Hari Bahagiamu", fontSize = if (isMobile) 26.sp else 32.sp, fontWeight = FontWeight.ExtraBold, color = ColorPrimaryDark, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Text("Konsultasi gratis — ceritakan konsep impianmu, kami bantu wujudkan sesuai budget.", fontSize = 14.sp, color = ColorTextMuted, textAlign = TextAlign.Center, modifier = Modifier.width(520.dp))
            Spacer(Modifier.height(32.dp))

            if (isMobile) {
                Column(verticalArrangement = Arrangement.spacedBy(18.dp), modifier = Modifier.fillMaxWidth()) {
                    ContactForm(nama, { nama = it }, wa, { wa = it }, tanggal, { tanggal = it }, paket, { paket = it }, pesan, { pesan = it })
                    ContactInfo()
                    MapCard()
                }
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(22.dp), modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.weight(1.15f)) {
                        ContactForm(nama, { nama = it }, wa, { wa = it }, tanggal, { tanggal = it }, paket, { paket = it }, pesan, { pesan = it })
                    }
                    Column(modifier = Modifier.weight(0.85f), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        ContactInfo()
                        MapCard()
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactForm(
    nama: String, onNama: (String) -> Unit,
    wa: String, onWa: (String) -> Unit,
    tanggal: String, onTanggal: (String) -> Unit,
    paket: String, onPaket: (String) -> Unit,
    pesan: String, onPesan: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(ColorCard).border(0.6.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(20.dp)).shadow(8.dp, RoundedCornerShape(20.dp), ambientColor = ColorBorder.copy(alpha = 0.15f), spotColor = ColorBorder.copy(alpha = 0.15f)).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(ColorAccent))
            Text("FORM KONSULTASI", color = ColorAccent, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.6.sp)
        }
        Text("Ceritakan Rencanamu", color = ColorPrimaryDark, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("Isi form, kami balas via WhatsApp dalam < 2 jam (08.00-21.00).", color = ColorTextMuted, fontSize = 12.5.sp, lineHeight = 18.sp)

        // Nama & WA
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) { Field("Nama Kamu *", "Rina & Dimas", nama, onNama) }
            Box(modifier = Modifier.weight(1f)) { Field("No. WhatsApp *", "0812-xxxx-xxxx", wa, onWa) }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) { TanggalPickerField("Tanggal Acara", tanggal, onTanggal) }
            Box(modifier = Modifier.weight(1f)) { Field("Paket", "Wedding / Catering / Custom", paket, onPaket) }
        }
        FieldMulti("Pesan / Kebutuhan", "Ceritakan konsep, jumlah tamu, lokasi, budget...", pesan, onPesan)

        val canSubmit = nama.isNotBlank() && wa.isNotBlank()
        Box(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(if (canSubmit) ColorAccent else ColorBorder).clickable(enabled = canSubmit) {
                val text = buildString {
                    append("Halo Lena Wedding & Catering, saya ingin konsultasi.\n")
                    append("Nama: $nama\n")
                    append("WA: $wa\n")
                    if (tanggal.isNotBlank()) append("Tanggal: $tanggal\n")
                    if (paket.isNotBlank()) append("Paket: $paket\n")
                    if (pesan.isNotBlank()) append("Pesan: $pesan\n")
                }
                val enc = text.replace(" ", "%20").replace("\n", "%0A").replace(",", "%2C").replace("&", "%26")
                window.open("https://wa.me/6281218387400?text=$enc", "_blank")
            }.padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) { Text(if (canSubmit) "Kirim via WhatsApp" else "Lengkapi Nama & WA", color = if (canSubmit) Color.White else ColorTextMuted, fontSize = 14.sp, fontWeight = FontWeight.Bold); if (canSubmit) ArrowRightIcon(Color.White, size = 13.dp, stroke = 1.5.dp) }
        }
        Text("Dengan mengirim, kamu setuju kami hubungi via WA. Gratis & tanpa komitmen.", color = ColorTextMuted, fontSize = 10.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun Field(label: String, placeholder: String, value: String, onValue: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, color = ColorPrimaryDark, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = value, onValueChange = onValue, placeholder = { Text(placeholder, color = Color(0xFFB0A6BD), fontSize = 12.5.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ColorAccent, unfocusedBorderColor = ColorBorder.copy(alpha = 0.55f),
                focusedContainerColor = Color.White, unfocusedContainerColor = Color(0xFFFCFAF8),
                cursorColor = ColorAccent
            ),
            singleLine = true
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TanggalPickerField(label: String, value: String, onValue: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    if (isPressed && !showDialog) {
        // will be handled via clickable, but keep for focus
    }
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, color = ColorPrimaryDark, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                placeholder = { Text("Pilih tanggal", color = Color(0xFFB0A6BD), fontSize = 12.5.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ColorAccent, unfocusedBorderColor = ColorBorder.copy(alpha = 0.55f),
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color(0xFFFCFAF8),
                    cursorColor = ColorAccent, disabledBorderColor = ColorBorder.copy(alpha = 0.55f), disabledContainerColor = Color(0xFFFCFAF8)
                ),
                readOnly = true,
                enabled = true,
                singleLine = true,
                trailingIcon = {
                    Box(
                        modifier = Modifier.size(28.dp).clip(CircleShape).background(ColorAccent.copy(alpha = 0.12f)).padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CalendarIcon(ColorAccent, size = 14.dp)
                    }
                },
                interactionSource = interactionSource
            )
            // Transparent clickable overlay biar full field bisa di-tap untuk buka picker
            Box(
                modifier = Modifier.matchParentSize().clip(RoundedCornerShape(10.dp)).clickable { showDialog = true }
            )
        }
    }
    if (showDialog) {
        val state = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    state.selectedDateMillis?.let { millis ->
                        onValue(formatTanggal(millis))
                    }
                    showDialog = false
                }) { Text("Pilih", color = ColorAccent, fontWeight = FontWeight.Bold) }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Batal", color = ColorTextMuted) }
            }
        ) {
            DatePicker(state = state)
        }
    }
}

private fun formatTanggal(millis: Long): String {
    // Pure Kotlin — tanpa kotlinx.datetime biar tidak IrLinkageError di JS/Wasm
    // millis dari DatePicker adalah UTC midnight
    val days = (millis / 86400000L).toInt() // days since 1970-01-01
    // Convert days to date via proleptic Gregorian (Howard Hinnant)
    val z = days + 719468 // shift to civil
    val era = (if (z >= 0) z else z - 146096) / 146097
    val doe = z - era * 146097
    val yoe = (doe - doe / 1460 + doe / 36524 - doe / 146096) / 365
    var y = yoe + era * 400
    val doy = doe - (365 * yoe + yoe / 4 - yoe / 100)
    val mp = (5 * doy + 2) / 153
    val d = doy - (153 * mp + 2) / 5 + 1
    val m = mp + if (mp < 10) 3 else -9
    y += if (m <= 2) 1 else 0
    val bulan = listOf("Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des")[m - 1]
    return "$d $bulan $y"
}

@Composable
private fun FieldMulti(label: String, placeholder: String, value: String, onValue: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, color = ColorPrimaryDark, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = value, onValueChange = onValue, placeholder = { Text(placeholder, color = Color(0xFFB0A6BD), fontSize = 12.5.sp) },
            modifier = Modifier.fillMaxWidth().height(96.dp),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ColorAccent, unfocusedBorderColor = ColorBorder.copy(alpha = 0.55f),
                focusedContainerColor = Color.White, unfocusedContainerColor = Color(0xFFFCFAF8),
                cursorColor = ColorAccent
            )
        )
    }
}

@Composable
private fun ContactInfo() {
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(ColorCard).border(0.6.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(20.dp)).shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = ColorBorder.copy(alpha = 0.12f), spotColor = ColorBorder.copy(alpha = 0.12f)).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("Hubungi Langsung", color = ColorPrimaryDark, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Box(modifier = Modifier.clickable { window.open("https://www.google.com/maps/search/?api=1&query=Lena+Wedding+Organizer+Rawalumbu+Bekasi", "_blank") }) {
            InfoRow({ LocationIcon(ColorAccent, size = 16.dp) }, "Alamat Studio", "Jl. Lumbu Barat III A No.285, Rawalumbu, Bekasi 17116\nTap untuk buka Maps")
        }
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(ColorBorder))
        InfoRow({ ChatIcon(ColorAccent, size = 16.dp) }, "WhatsApp", "+62 812-1838-7400 (Admin)\nRespon < 2 jam • 08.00-21.00")
        InfoRow({ EmailIcon(ColorAccent, size = 16.dp) }, "Email", "info@lenawo.com\nUntuk proposal & pricelist")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            val socials = listOf(
                Triple("IG", "https://www.instagram.com/lenawedding/", "IG"),
                Triple("WA", "https://wa.me/6281218387400", "WA"),
                Triple("TT", "https://www.instagram.com/lenawedding/", "TT")
            )
            socials.forEach { (label, url, _) ->
                Box(
                    modifier = Modifier.size(32.dp).clip(CircleShape).background(ColorAccent.copy(alpha = 0.12f)).border(0.7.dp, ColorAccent.copy(alpha = 0.18f), CircleShape).clickable { window.open(url, "_blank") },
                    contentAlignment = Alignment.Center
                ) {
                    Text(label, color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.width(6.dp))
            Text("Follow untuk inspirasi daily", color = ColorTextMuted, fontSize = 11.sp, modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}

@Composable
private fun InfoRow(icon: @Composable () -> Unit, title: String, desc: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Top) {
        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(ColorAccent.copy(alpha = 0.13f)), contentAlignment = Alignment.Center) {
            icon()
        }
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(title, color = ColorPrimaryDark, fontSize = 12.5.sp, fontWeight = FontWeight.SemiBold)
            Text(desc, color = ColorTextMuted, fontSize = 12.sp, lineHeight = 17.sp)
        }
    }
}

@Composable
private fun MapCard() {
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(ColorCard).border(0.6.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(20.dp)).shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = ColorBorder.copy(alpha = 0.12f), spotColor = ColorBorder.copy(alpha = 0.12f))
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
            AsyncImage(
                model = "https://images.unsplash.com/photo-1524634126442-357e0eac3c14?q=80&w=800&auto=format&fit=crop",
                contentDescription = "Peta lokasi", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth().height(160.dp)
            )
            Box(modifier = Modifier.align(Alignment.Center).clip(RoundedCornerShape(10.dp)).background(Color.White.copy(alpha = 0.92f)).padding(horizontal = 12.dp, vertical = 8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) { LocationIcon(ColorPrimaryDark, size = 12.dp); Text("Studio Lena — Rawalumbu, Bekasi", color = ColorPrimaryDark, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(14.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text("Lihat di Google Maps", color = ColorPrimaryDark, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                Text("Buka navigasi langsung", color = ColorTextMuted, fontSize = 11.sp)
            }
            Box(modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(ColorPrimaryDark).clickable { window.open("https://www.google.com/maps/search/?api=1&query=Lena+Wedding+Organizer+Rawalumbu+Bekasi", "_blank") }.padding(horizontal = 14.dp, vertical = 9.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) { Text("Buka Maps", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold); ArrowRightIcon(Color.White, size = 11.dp, stroke = 1.4.dp) }
            }
        }
    }
}