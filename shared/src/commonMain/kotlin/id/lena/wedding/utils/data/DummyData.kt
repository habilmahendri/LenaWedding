package id.lena.wedding.utils.data


// ==== Data ====
data class NavItem(val label: String)

val navItems = listOf(
    NavItem("Beranda"),
    NavItem("Tentang Kami"),
    NavItem("Layanan"),
    NavItem("Galeri"),
    NavItem("Kontak Kami"),
)

data class OfferCard(val title: String, val subtitle: String, val imageUrl: String)

val offerCards = listOf(
    OfferCard("Wedding", "Paket Wedding", "https://picsum.photos/seed/offer-wedding/500/500"),
    OfferCard("Catering", "Paket Catering", "https://picsum.photos/seed/offer-catering/500/500"),
    OfferCard("Dekorasi", "Paket Dekorasi", "https://picsum.photos/seed/offer-decor/500/500"),
)

data class MenuCard(val title: String, val imageUrl: String)

val menuCards = listOf(
    MenuCard("Prasmanan", "https://picsum.photos/seed/menu-prasmanan/500/400"),
    MenuCard("Pondokan", "https://picsum.photos/seed/menu-pondokan/500/400"),
    MenuCard("Menu Masakan", "https://picsum.photos/seed/menu-masakan/500/400"),
)

data class HighlightItem(val title: String, val desc: String)

val highlights = listOf(
    HighlightItem("Wedding Eksklusif", "Ciptakan momen sakral dengan sentuhan eksklusif"),
    HighlightItem("Sajian Berkelas", "Hidangan premium dengan cita rasa istimewa"),
    HighlightItem("Pelayanan Terbaik", "Layanan terbaik untuk momen terbaik Anda"),
)

data class GalleryPhoto( val id: String,val url: String, val caption: String)

val galleryPhotos = listOf(
    GalleryPhoto("1","https://picsum.photos/seed/gallery1/500/500", "Akad Nikah - Rina & Dimas"),
    GalleryPhoto("2","https://picsum.photos/seed/gallery2/500/500", "Resepsi Outdoor - Sari & Bayu"),
    GalleryPhoto("3","https://picsum.photos/seed/gallery3/500/500", "Dekorasi Pelaminan"),
    GalleryPhoto("4","https://picsum.photos/seed/gallery4/500/500", "Prewedding - Ayu & Fajar"),
    GalleryPhoto("5","https://picsum.photos/seed/gallery5/500/500", "Sesi Foto Keluarga"),
    GalleryPhoto("6","https://picsum.photos/seed/gallery6/500/500", "Resepsi Adat Jawa"),
    GalleryPhoto("8","https://picsum.photos/seed/gallery7/500/500", "Detail Dekorasi Meja"),
    GalleryPhoto("9","https://picsum.photos/seed/gallery8/500/500", "Momen Bahagia Pengantin"),
)

data class Testimonial(val name: String, val role: String, val text: String, val photoUrl: String)

val testimonials = listOf(
    Testimonial(
        "Rina & Dimas", "Wedding Client",
        "Terima kasih Lena Wedding buat bantuan di acara kami, alhamdulillah semuanya lancar. Dekorasi oke, dokumentasinya keren dan fleksibel sesuai plan yang kami mau.",
        "https://picsum.photos/seed/testi1/100/100"
    ),
    Testimonial(
        "Sari & Bayu", "Wedding Client",
        "Terima kasih untuk bantuan dan kerja samanya di acara kami, semua berjalan lancar. Semoga makin banyak job dan makin sukses untuk tim Lena.",
        "https://picsum.photos/seed/testi2/100/100"
    ),
    Testimonial(
        "Ayu & Fajar", "Wedding Client",
        "Dekorasinya cantik banget, tim sangat responsif dari awal konsultasi sampai hari-H. Sukses terus buat Lena Wedding Organizer!",
        "https://picsum.photos/seed/testi3/100/100"
    ),
)