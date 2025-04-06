# Endpointlere gelen guncellemeler ile bazi fonksiyonlar calismamaktadir. Duzeltilecek :) (bir ara)

# Dart'a port ettigim versiyonu uzerinde calisiyorum. bu repo sadece referans olarak durmakta

Eksi sozluk uygulamasinin butun yapabildiklerini yapabilmeyi amaclayan kotlin librarysidir.

Readme, library pre-release olarak degil de release olarak yayinlaninca guncellenecektir.

Kullanimi basit olup simdi bile kullanabilirsiniz.

Orn.:

```kotlin
suspend fun main() {
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

- detayli hatalar ve unified fonksiyonlar ile daha robust bir yapi
- anonim hesap ile giris yapma
- kullanici adi ile giris yapma
- arama
- detayli arama
- entry:
  - entry cekebilme
  - oylama
  - favorileme / favorilerden cikarma
  - entry favorileri ve caylak favorileri
- baslik:
  - takip etme / takipten cikarma
  - baslik cekebilme
  - takip edilen basligin son entryleri
  - entry filtreleme
  - baslik arama
  - baslikta arayabilme
  - baslikta detayli arayabilme
- anasayfa:
  - debe
  - populer
  - bugun 
  - son 
  - olay
  - caylaklar
  - tarihte bugun
  - popular sorunsallar
  - bugunun sorunsallari
- kullanici:
  - bilgisi
  - takip etme 
  - takipten cikarma
  - engelleme / kaldirma
  - baslik engelleme / kaldirma
  - entryleri
  - favorileri
  - en cok favorilenenler
  - son oylanan
  - bu hafta dikkat ceken
  - el emegi goz nuru
  - en iyi entryleri
  - gorselleri
  - sorunsallari
  - sorunsal yanitlari
- mesaj:
  - gonderme
  - listeleme
- sorunsallar
  - tek bir sorunsali goruntuleme
  - sorunsallar icin 4 farkli siralama

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
    implementation 'com.github.otuva:kotlin-eksisozluk-api:Tag'
}
```
