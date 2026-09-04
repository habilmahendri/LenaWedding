package id.lena.wedding.ui.herosection

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import coil3.compose.AsyncImage
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.icons.PlayIcon
import id.lena.wedding.utils.icons.StarIcon


@Composable
fun HeroSection(onPrimaryClick: () -> Unit = {}, onSecondaryClick: () -> Unit = {}) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth().height(720.dp)) {
        val isMobile = maxWidth < 760.dp

        // Ken Burns zoom animation
        val infiniteTransition = rememberInfiniteTransition(label = "heroZoom")
        val scale by infiniteTransition.animateFloat(
            initialValue = 1f, targetValue = 1.08f,
            animationSpec = infiniteRepeatable(tween(14000, easing = LinearEasing), RepeatMode.Reverse), label = "scale"
        )
        var isVisible by remember { mutableStateOf(false) }
        val alpha by animateFloatAsState(if (isVisible) 1f else 0f, tween(1200), label = "fadeIn")
        LaunchedEffect(Unit) { isVisible = true }

        AsyncImage(
            model = "https://images.unsplash.com/photo-1519741497674-611481863552?q=80&w=2940&auto=format&fit=crop",
            contentDescription = "Foto pernikahan",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().graphicsLayer { scaleX = scale; scaleY = scale; this.alpha = alpha }
        )
        // Layered gradient — warm, romantic, readable
        Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0x1A2D1E3A), Color(0x552D1E3A), Color(0xDD2D1E3A)))))
        Box(modifier = Modifier.fillMaxSize().background(Brush.horizontalGradient(listOf(Color(0x662D1E3A), Color.Transparent, Color(0x332D1E3A)))))

        // Decorative top gold line
        Box(modifier = Modifier.fillMaxWidth().height(3.dp).background(Brush.horizontalGradient(listOf(Color.Transparent, ColorAccent.copy(alpha = 0.7f), Color.Transparent))))

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = if (isMobile) 20.dp else 48.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Eyebrow badge — frosted glass
            Row(
                modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(Color.White.copy(alpha = 0.14f)).border(0.7.dp, Color.White.copy(alpha = 0.22f), RoundedCornerShape(20.dp)).padding(horizontal = 16.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(ColorAccent))
                Text("EST. 2002  —  BEKASI • JABODETABEK", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.5.sp)
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(ColorAccent))
            }

            Spacer(Modifier.height(22.dp))

            // Headline — editorial serif feel (via spacing & weight contrast)
            Text(
                "Hari Bahagia,",
                color = Color.White.copy(alpha = 0.92f),
                fontSize = if (isMobile) 36.sp else 52.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                lineHeight = if (isMobile) 40.sp else 56.sp,
                letterSpacing = 0.5.sp
            )
            Text(
                "Dirancang Sempurna",
                color = Color.White,
                fontSize = if (isMobile) 36.sp else 52.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                lineHeight = if (isMobile) 40.sp else 56.sp
            )
            // Gold underline accent
            Box(modifier = Modifier.padding(top = 10.dp).width(64.dp).height(3.dp).clip(RoundedCornerShape(2.dp)).background(ColorAccent))

            Spacer(Modifier.height(18.dp))

            Text(
                "Wedding organizer & catering premium — dari akad intim hingga resepsi megah.\nKami urus setiap detail, kamu tinggal menikmati momen cinta.",
                color = Color(0xFFEDE7F3),
                fontSize = if (isMobile) 14.sp else 15.5.sp,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.widthIn(max = 580.dp)
            )

            Spacer(Modifier.height(28.dp))

            // CTA row — gold filled + glass outline
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Primary CTA
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(28.dp)).background(ColorAccent).clickable { onPrimaryClick() }.padding(horizontal = 28.dp, vertical = 14.dp),
                ) {
                    Text("Konsultasi Gratis", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.3.sp)
                }
                // Secondary CTA — glass
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(28.dp)).background(Color.White.copy(alpha = 0.14f)).border(0.9.dp, Color.White.copy(alpha = 0.7f), RoundedCornerShape(28.dp)).clickable { onSecondaryClick() }.padding(horizontal = 24.dp, vertical = 13.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        PlayIcon(Color.White, size = 10.dp)
                        Text("Lihat Galeri", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(Modifier.height(26.dp))

            // Trust row — avatars + rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(Color.White.copy(alpha = 0.11f)).border(0.7.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(20.dp)).padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Row {
                    repeat(4) { i ->
                        Box(
                            modifier = Modifier.size(28.dp).clip(CircleShape).background(Color(0xFFD9CDE8)).border(2.dp, Color.White, CircleShape),
                        ) {
                            AsyncImage(
                                model = "https://picsum.photos/seed/avatar${i + 1}/100/100",
                                contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize().clip(CircleShape)
                            )
                        }
                    }
                }
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) { repeat(5) { StarIcon(Color(0xFFFFD66B), size = 10.dp) } }
                        Spacer(Modifier.width(6.dp))
                        Text("4.9/5", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Text("dari 200+ pasangan bahagia", color = Color.White.copy(alpha = 0.78f), fontSize = 10.sp)
                }
            }

            Spacer(Modifier.height(32.dp))

            // Stats — glass cards
            Row(horizontalArrangement = Arrangement.spacedBy(if (isMobile) 12.dp else 18.dp)) {
                HeroStatGlass("200+", "Pasangan", isMobile)
                HeroStatGlass("24", "Tahun", isMobile)
                HeroStatGlass("50+", "Vendor", isMobile)
                if (!isMobile) HeroStatGlass("4.9", "Rating", isMobile)
            }
        }
    }
}

@Composable
private fun HeroStatGlass(value: String, label: String, isMobile: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clip(RoundedCornerShape(14.dp)).background(Color.White.copy(alpha = 0.10f)).border(0.7.dp, Color.White.copy(alpha = 0.16f), RoundedCornerShape(14.dp)).padding(horizontal = if (isMobile) 14.dp else 18.dp, vertical = 10.dp)
    ) {
        Text(value, color = Color.White, fontSize = if (isMobile) 18.sp else 20.sp, fontWeight = FontWeight.ExtraBold)
        Text(label, color = Color.White.copy(alpha = 0.75f), fontSize = 10.sp, letterSpacing = 0.8.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun AnimatedHeroImage(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "heroZoom")
    val scale by infiniteTransition.animateFloat(1f, 1.12f, infiniteRepeatable(tween(12000, easing = LinearEasing), RepeatMode.Reverse), label = "scale")
    var isVisible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(if (isVisible) 1f else 0f, tween(1200), label = "fadeIn")
    LaunchedEffect(Unit) { isVisible = true }
    AsyncImage(
        model = "https://images.unsplash.com/photo-1519741497674-611481863552?q=80&w=2940&auto=format&fit=crop",
        contentDescription = "Foto pernikahan", contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize().graphicsLayer { scaleX = scale; scaleY = scale; this.alpha = alpha }
    )
}