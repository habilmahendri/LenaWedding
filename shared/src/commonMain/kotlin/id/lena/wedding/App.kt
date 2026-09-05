package id.lena.wedding
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import id.lena.wedding.ui.aboutsection.AboutSection
import id.lena.wedding.ui.cateringdetail.CateringDetailDialog
import id.lena.wedding.ui.contactsection.ContactSection
import id.lena.wedding.ui.dekorasidetail.DekorasiDetailDialog
import id.lena.wedding.ui.footersection.FooterSection
import id.lena.wedding.ui.gallerysection.GallerySection
import id.lena.wedding.ui.herosection.HeroSection
import id.lena.wedding.ui.highlightsection.HighlightSection
import id.lena.wedding.ui.menudetail.MenuMasakanDialog
import id.lena.wedding.ui.menudetail.MenuPondokanDialog
import id.lena.wedding.ui.menudetail.MenuPrasmananDialog
import id.lena.wedding.ui.menusection.MenuSection
import id.lena.wedding.ui.navbar.NavBar
import id.lena.wedding.ui.offersection.OfferSection
import id.lena.wedding.ui.testimonisection.TestimonialSection
import id.lena.wedding.utils.AnimatedEntrance
import id.lena.wedding.utils.color.ColorAccent
import id.lena.wedding.utils.color.ColorBackground
import id.lena.wedding.utils.data.galleryPhotos
import id.lena.wedding.utils.icons.ChevronLeftIcon
import id.lena.wedding.utils.icons.ChevronRightIcon
import id.lena.wedding.utils.icons.CloseIcon
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

object SectionIds {
    const val BERANDA = "beranda"
    const val TENTANG = "tentang"
    const val LAYANAN = "layanan"
    const val GALERI = "galeri"
    const val KONTAK = "kontak"
}

@Composable
fun WeddingOrganizerApp() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components { add(KtorNetworkFetcherFactory()) }
            .crossfade(true)
            .build()
    }

    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var galleryIndex by remember { mutableStateOf<Int?>(null) }
    var showCateringDetail by remember { mutableStateOf(false) }
    var showDekorasiDetail by remember { mutableStateOf(false) }
    var showPrasmananDetail by remember { mutableStateOf(false) }
    var showPondokanDetail by remember { mutableStateOf(false) }
    var showMasakanDetail by remember { mutableStateOf(false) }
    val currentItemIndex by remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val navSelectedIndex = when (currentItemIndex) {
        0 -> 0 // Beranda
        1 -> 1 // Tentang Kami
        2 -> 2 // Layanan
        3, 4 -> 3 // Galeri + Testimoni tetap highlight Galeri
        5 -> 4 // Kontak Kami
        else -> 0
    }

    // Index mapping untuk LazyColumn: 0=Beranda,1=Tentang,2=Layanan,3=Galeri,4=Testimoni,5=Kontak
    fun scrollTo(id: String) {
        val index = when (id) {
            SectionIds.BERANDA -> 0
            SectionIds.TENTANG -> 1
            SectionIds.LAYANAN -> 2
            SectionIds.GALERI -> 3
            SectionIds.KONTAK -> 5
            else -> 0
        }
        scope.launch { lazyListState.animateScrollToItem(index) }
    }

    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize().background(ColorBackground)) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Sticky navbar — di luar scroll + auto selected
                NavBar(selectedIndex = navSelectedIndex, onNavClick = { label ->
                    val id = when (label) {
                        "Beranda" -> SectionIds.BERANDA
                        "Tentang Kami" -> SectionIds.TENTANG
                        "Layanan" -> SectionIds.LAYANAN
                        "Galeri" -> SectionIds.GALERI
                        "Kontak Kami" -> SectionIds.KONTAK
                        else -> SectionIds.BERANDA
                    }
                    scrollTo(id)
                })
                LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f), state = lazyListState) {
                    item(key = "hero") { HeroSection(onPrimaryClick = { scrollTo(SectionIds.KONTAK) }, onSecondaryClick = { scrollTo(SectionIds.GALERI) }) }
                    item(key = "about") { AnimatedEntrance(key = "about", delayMs = 80) { AboutSection() } }
                    item(key = "layanan") {
                        AnimatedEntrance(key = "layanan", delayMs = 160) {
                            Column {
                                OfferSection(
                                    onCateringClick = { showCateringDetail = true },
                                    onDekorasiClick = { showDekorasiDetail = true }
                                )
                                MenuSection(
                                    onPrasmananClick = { showPrasmananDetail = true },
                                    onPondokanClick = { showPondokanDetail = true },
                                    onMasakanClick = { showMasakanDetail = true }
                                )
                                HighlightSection()
                            }
                        }
                    }
                    item(key = "galeri") { AnimatedEntrance(key = "galeri", delayMs = 240) { GallerySection(onPhotoClick = { galleryIndex = it }) } }
                    item(key = "testimoni") { AnimatedEntrance(key = "testimoni", delayMs = 320) { TestimonialSection() } }
                    item(key = "kontak") {
                        AnimatedEntrance(key = "kontak", delayMs = 380) {
                            Column {
                                ContactSection()
                                FooterSection()
                            }
                        }
                    }
                }
            }
            // Full-screen gallery lightbox — full besar + zoomable
            AnimatedVisibility(
                visible = galleryIndex != null,
                enter = fadeIn(tween(260)),
                exit = fadeOut(tween(200)),
                modifier = Modifier.fillMaxSize()
            ) {
                galleryIndex?.let { idx ->
                    val photo = galleryPhotos[idx]
                    Box(
                        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.92f)).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { galleryIndex = null },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Top bar: counter + close — tap tidak tutup
                            Row(modifier = Modifier.fillMaxWidth().widthIn(max = 900.dp).align(Alignment.CenterHorizontally).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {}), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(Color.White.copy(alpha = 0.14f)).border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(20.dp)).padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text("${idx + 1} / ${galleryPhotos.size}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                }
                                Box(
                                    modifier = Modifier.size(36.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.14f)).border(1.dp, Color.White.copy(alpha = 0.20f), CircleShape).clickable { galleryIndex = null },
                                    contentAlignment = Alignment.Center
                                ) {
                                    CloseIcon(Color.White, size = 16.dp, stroke = 1.8.dp)
                                }
                            }
                            // Image full tanpa scale awal — Fit, tidak ke-zoom, tetap center di HP
                            Box(
                                modifier = Modifier.fillMaxWidth().weight(1f).widthIn(max = 1100.dp).clip(RoundedCornerShape(16.dp)).background(Color.Black.copy(alpha = 0.35f)).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {}),
                                contentAlignment = Alignment.Center
                            ) {
                                ZoomableImage(
                                    model = photo.url,
                                    contentDescription = photo.caption,
                                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                                )
                                // Prev — fixed, tidak ikut zoom
                                Box(
                                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 12.dp).size(42.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.18f)).border(1.dp, Color.White.copy(alpha = 0.24f), CircleShape).clickable {
                                        galleryIndex = if (idx - 1 < 0) galleryPhotos.lastIndex else idx - 1
                                    },
                                    contentAlignment = Alignment.Center
                                ) { ChevronLeftIcon(Color.White, size = 20.dp) }
                                // Next
                                Box(
                                    modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp).size(42.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.18f)).border(1.dp, Color.White.copy(alpha = 0.24f), CircleShape).clickable {
                                        galleryIndex = (idx + 1) % galleryPhotos.size
                                    },
                                    contentAlignment = Alignment.Center
                                ) { ChevronRightIcon(Color.White, size = 20.dp) }
                                // Hint zoom
                                Box(
                                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 12.dp).clip(RoundedCornerShape(20.dp)).background(Color.Black.copy(alpha = 0.45f)).padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text("Pinch untuk zoom • Drag untuk geser • Double tap reset", color = Color.White.copy(alpha = 0.85f), fontSize = 10.sp)
                                }
                            }
                            // Caption — tap tidak tutup
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.widthIn(max = 900.dp).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {})) {
                                Text(photo.caption, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                                Text("Tap di luar gambar untuk tutup", color = Color.White.copy(alpha = 0.62f), fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
            // Catering paket detail — popup pilihan biar konsumen tertarik
            AnimatedVisibility(
                visible = showCateringDetail,
                enter = fadeIn(tween(260)),
                exit = fadeOut(tween(200)),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.72f)).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { showCateringDetail = false },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).padding(bottom = 8.dp), horizontalArrangement = Arrangement.End) {
                            Box(
                                modifier = Modifier.size(36.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.14f)).border(1.dp, Color.White.copy(alpha = 0.20f), CircleShape).clickable { showCateringDetail = false },
                                contentAlignment = Alignment.Center
                            ) { CloseIcon(Color.White, size = 16.dp, stroke = 1.8.dp) }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).heightIn(max = 720.dp).clip(RoundedCornerShape(22.dp)).background(Color.Transparent).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {})
                        ) {
                            CateringDetailDialog(onDismiss = { showCateringDetail = false })
                        }
                    }
                }
            }
            // Dekorasi paket detail — popup serupa
            AnimatedVisibility(
                visible = showDekorasiDetail,
                enter = fadeIn(tween(260)),
                exit = fadeOut(tween(200)),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.72f)).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { showDekorasiDetail = false },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).padding(bottom = 8.dp), horizontalArrangement = Arrangement.End) {
                            Box(
                                modifier = Modifier.size(36.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.14f)).border(1.dp, Color.White.copy(alpha = 0.20f), CircleShape).clickable { showDekorasiDetail = false },
                                contentAlignment = Alignment.Center
                            ) { CloseIcon(Color.White, size = 16.dp, stroke = 1.8.dp) }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).heightIn(max = 720.dp).clip(RoundedCornerShape(22.dp)).background(Color.Transparent).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {})
                        ) {
                            DekorasiDetailDialog(onDismiss = { showDekorasiDetail = false })
                        }
                    }
                }
            }
            // Prasmanan menu detail — popup kategori
            AnimatedVisibility(
                visible = showPrasmananDetail,
                enter = fadeIn(tween(260)),
                exit = fadeOut(tween(200)),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.72f)).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { showPrasmananDetail = false },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).padding(bottom = 8.dp), horizontalArrangement = Arrangement.End) {
                            Box(
                                modifier = Modifier.size(36.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.14f)).border(1.dp, Color.White.copy(alpha = 0.20f), CircleShape).clickable { showPrasmananDetail = false },
                                contentAlignment = Alignment.Center
                            ) { CloseIcon(Color.White, size = 16.dp, stroke = 1.8.dp) }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).heightIn(max = 720.dp).clip(RoundedCornerShape(22.dp)).background(Color.Transparent).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {})
                        ) {
                            MenuPrasmananDialog(onDismiss = { showPrasmananDetail = false })
                        }
                    }
                }
            }
            // Pondokan detail
            AnimatedVisibility(
                visible = showPondokanDetail,
                enter = fadeIn(tween(260)),
                exit = fadeOut(tween(200)),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.72f)).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { showPondokanDetail = false },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).padding(bottom = 8.dp), horizontalArrangement = Arrangement.End) {
                            Box(
                                modifier = Modifier.size(36.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.14f)).border(1.dp, Color.White.copy(alpha = 0.20f), CircleShape).clickable { showPondokanDetail = false },
                                contentAlignment = Alignment.Center
                            ) { CloseIcon(Color.White, size = 16.dp, stroke = 1.8.dp) }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).heightIn(max = 720.dp).clip(RoundedCornerShape(22.dp)).background(Color.Transparent).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {})
                        ) {
                            MenuPondokanDialog(onDismiss = { showPondokanDetail = false })
                        }
                    }
                }
            }
            // Masakan detail
            AnimatedVisibility(
                visible = showMasakanDetail,
                enter = fadeIn(tween(260)),
                exit = fadeOut(tween(200)),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.72f)).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { showMasakanDetail = false },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).padding(bottom = 8.dp), horizontalArrangement = Arrangement.End) {
                            Box(
                                modifier = Modifier.size(36.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.14f)).border(1.dp, Color.White.copy(alpha = 0.20f), CircleShape).clickable { showMasakanDetail = false },
                                contentAlignment = Alignment.Center
                            ) { CloseIcon(Color.White, size = 16.dp, stroke = 1.8.dp) }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth().widthIn(max = 960.dp).heightIn(max = 720.dp).clip(RoundedCornerShape(22.dp)).background(Color.Transparent).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = {})
                        ) {
                            MenuMasakanDialog(onDismiss = { showMasakanDetail = false })
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ZoomableImage(model: String, contentDescription: String?, modifier: Modifier = Modifier) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    // Reset zoom when image changes
    androidx.compose.runtime.LaunchedEffect(model) {
        scale = 1f
        offset = Offset.Zero
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .pointerInput(model) {
                detectTapGestures(onDoubleTap = {
                    if (scale > 1.1f) {
                        scale = 1f
                        offset = Offset.Zero
                    } else {
                        scale = 2.2f
                    }
                })
            }
            .pointerInput(model) {
                detectTransformGestures { _, pan, zoom, _ ->
                    val newScale = (scale * zoom).coerceIn(1f, 4f)
                    // Only pan when zoomed
                    if (newScale > 1f) {
                        offset = Offset(
                            x = (offset.x + pan.x).coerceIn(-800f, 800f),
                            y = (offset.y + pan.y).coerceIn(-800f, 800f)
                        )
                    } else {
                        offset = Offset.Zero
                    }
                    scale = newScale
                }
            }
            .graphicsLayer(scaleX = scale, scaleY = scale, translationX = offset.x, translationY = offset.y),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = model, contentDescription = contentDescription, contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
        )
    }
}
