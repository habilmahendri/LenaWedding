package id.lena.wedding.ui.testimonisection

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import id.lena.wedding.utils.MobileBreakpoint
import androidx.compose.ui.graphics.Color
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorBorder
import id.lena.wedding.utils.color.ColorCard
import id.lena.wedding.utils.color.ColorPrimaryDark
import id.lena.wedding.utils.color.ColorSectionAlt
import id.lena.wedding.utils.color.ColorTextMuted
import id.lena.wedding.utils.data.Testimonial
import id.lena.wedding.utils.data.testimonials
import kotlinx.coroutines.delay
import id.lena.wedding.utils.icons.QuoteIcon
import id.lena.wedding.utils.icons.StarIcon

@Composable
fun TestimonialSection() {
    // State index testimoni yang lagi tampil, plus loop auto-slide tiap 5 detik.
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            currentIndex = (currentIndex + 1) % testimonials.size
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth().background(ColorSectionAlt).padding(horizontal = 24.dp, vertical = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("TESTIMONI", color = ColorAccent, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.5.sp)
        Spacer(Modifier.height(10.dp))
        Text("Kata Mereka yang Telah Percaya", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = ColorPrimaryDark)
        Spacer(Modifier.height(8.dp))
        Text("Cerita nyata dari 200+ pasangan yang merayakan hari bahagia bersama kami.", fontSize = 13.5.sp, color = ColorTextMuted)
        Spacer(Modifier.height(28.dp))

        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val isMobile = maxWidth < MobileBreakpoint
            val cardMaxWidth = if (isMobile) 500.dp else 900.dp

            AnimatedContent(
                targetState = currentIndex,
                transitionSpec = {
                    (fadeIn(tween(500)) togetherWith fadeOut(tween(500)))
                },
                modifier = Modifier.fillMaxWidth().widthIn(max = cardMaxWidth).align(Alignment.Center)
            ) { index ->
                val t = testimonials[index]
                if (isMobile) {
                    TestimonialCardVertical(t)
                } else {
                    TestimonialCardHorizontal(t)
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Dot indicator premium
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            testimonials.indices.forEach { i ->
                Box(
                    modifier = Modifier
                        .size(if (i == currentIndex) 24.dp else 8.dp)
                        .height(8.dp)
                        .clip(CircleShape)
                        .background(if (i == currentIndex) ColorAccent else ColorBorder)
                        .clickable { currentIndex = i }
                )
            }
        }
    }
}

// Layout vertikal: foto di atas, quote di tengah, nama di bawah. Cocok untuk layar sempit.
@Composable
private fun TestimonialCardVertical(t: Testimonial) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(ColorCard)
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(3.dp).clip(RoundedCornerShape(2.dp)).background(ColorAccent.copy(alpha = 0.7f)))
        Spacer(Modifier.height(20.dp))
        QuoteIcon(ColorAccent.copy(alpha = 0.25f), size = 28.dp)
        AsyncImage(
            model = t.photoUrl,
            contentDescription = t.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(72.dp).clip(CircleShape).background(id.lena.wedding.utils.color.ColorAccentLight)
        )
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp), modifier = Modifier.align(Alignment.CenterHorizontally)) { repeat(5) { StarIcon(Color(0xFFFFC94A), size = 10.dp) } }
        Spacer(Modifier.height(12.dp))
        Text(
            "\u201C${t.text}\u201D",
            fontSize = 15.sp,
            color = ColorPrimaryDark,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.height(16.dp))
        Text(t.name, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = ColorPrimaryDark)
        Text(t.role, fontSize = 12.sp, color = ColorTextMuted)
    }
}

// Layout horizontal: foto di kiri, quote + nama di kanan. Lebih lebar & tidak numpuk
// tingginya di layar desktop yang lebar.
@Composable
private fun TestimonialCardHorizontal(t: Testimonial) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(ColorCard)
            .padding(32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = t.photoUrl,
                contentDescription = t.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(96.dp).clip(CircleShape)
            )
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) { repeat(5) { StarIcon(Color(0xFFFFC94A), size = 10.dp) } }
        }
        Column(modifier = Modifier.weight(1f)) {
            QuoteIcon(ColorAccent.copy(alpha = 0.18f), size = 28.dp)
            Text(
                "\u201C${t.text}\u201D",
                fontSize = 16.sp,
                color = ColorPrimaryDark,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(14.dp))
            Text(t.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ColorPrimaryDark)
            Text(t.role, fontSize = 13.sp, color = ColorTextMuted)
        }
    }
}