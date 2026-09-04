package id.lena.wedding

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform