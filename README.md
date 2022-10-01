Eksi sozluk uygulamasinin butun yapabildiklerini yapabilmeyi amaclayan kotlin librarysidir.

Readme, library pre-release olarak degil de release olarak yayinlaninca guncellenecektir.

Kullanimi basit olup simdi bile kullanabilirsiniz.

Orn.:

```kotlin
private suspend fun main() {
    val eksiSozlukAnonim = EksiSozluk() // anonim giris
    val eksiSozluk = EksiSozluk("username", "password") // kullanici hesabi
    
    val entries = eksiSozluk.user.entries("ssg")
    val images = eksiSozlukAnonim.user.images("ssg")
    
    println(entries)
    println(images)
}
```

Tum dosyalarda class ve methodlarin kdoc dokumantasyonu mevcuttur. Eger yoksa da kisa bir sure sonra eklenecektir :)

Suan librarynin yapabildikleri:

- anonim hesap ile giris yapma
- kullanici adi ile giris yapma
- entry cekebilme
- entry oylama
- entry favorileme / favorilerden cikarma
- baslik cekebilme
- baslikta entry filtreleme
- baslikta arayabilme
- debe, populer ve bugun basliklarini cekebilme
- kullanici:
  - bilgisi
  - takip etme 
  - takipten cikarma
  - engelleme / kaldirma
  - entryleri
  - favorileri
  - en cok favorilenenler
  - son oylanan
  - bu hafta dikkat ceken
  - el emegi goz nuru
  - en iyi entryleri
  - gorselleri

## Kurulum

proje rootundaki `build.gradle` dosyasina jitpack repositorysini ekleyin:

```groovy
repositories {
    maven { url "https://jitpack.io" }
}
```

ve `app/build.gradle` dosyasina (module gradle'ina) libraryi ekleyin:

```groovy
dependencies {
    implementation 'com.github.otuva:kotlin-eksisozluk-api:0.0.17'
}
```