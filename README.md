# Responsi 1 Pemrograman Mobile

**Nama:** Fina Julianti  
**NIM:** H1D023119  
**Shift Asal:** B  
**Shift Baru:** E  

---

## Demo Aplikasi

![Demo Aplikasi](app/src/main/res/drawable/demoresponsi.gif)

---

## Tentang Aplikasi

Aplikasi ini menampilkan profil Sheffield United FC menggunakan data dari API football-data.org. Ada empat halaman utama yang bisa diakses: homepage, sejarah klub, informasi pelatih, dan daftar pemain.

---

## Alur Data dari API ke Layar

### 1. Konfigurasi Retrofit
Pertama-tama aplikasi setup Retrofit di `RetrofitClient.kt` dengan base URL `https://api.football-data.org/`. Retrofit ini yang nantinya dipakai buat request ke server.

### 2. Interface API Service
Di `FootballApiService.kt` ada interface yang define endpoint apa aja yang bisa dipanggil. Disini cuma ada satu endpoint yaitu `getTeam()` yang manggil `/v4/teams/356` buat dapetin data Sheffield United. Token API dimasukin ke header request pake anotasi `@Headers`.

### 3. Request Data
Waktu user buka aplikasi, masing-masing activity (`HomeActivity`, `ClubHistoryActivity`, `CoachFragment`) manggil fungsi `RetrofitClient.apiService.getTeam(356)`. Ini mengirim HTTP request ke server football-data.org.

### 4. Parsing JSON
Server ngirim balik response berupa JSON. Retrofit otomatis convert JSON ini jadi object Kotlin pake Gson converter. Data disimpan dalam class `TeamResponse` yang isinya informasi klub lengkap, termasuk data coach dan list pemain.

### 5. Update UI
Setelah data berhasil diparsing:
- **Homepage**: Nampilin nama klub, logo (pake Glide buat load dari URL), sama deskripsi klub
- **Club History**: Nampilin gambar history, nama klub, sama text sejarah yang udah disiapkan
- **Head Coach**: Nampilin nama pelatih, tanggal lahir, dan kewarganegaraan dari data API
- **Team Squad**: List pemain ditampilin di RecyclerView. Card tiap pemain dikasih warna beda-beda tergantung posisi mereka (kiper kuning, bek biru, gelandang hijau, striker merah)

### 6. Error Handling
Kalo misalnya request gagal (internet mati atau server bermasalah), aplikasi nampilin Toast notification biar user tau ada error. Buat logo yang gagal load dari internet, ada fallback ke drawable lokal.

---

## Teknologi yang Digunakan

- Kotlin
- Retrofit + Gson
- Glide untuk load image
- ViewBinding
- RecyclerView
- Fragment
- Material Design Components

---

## Fitur Aplikasi

### Splash Screen
Splash screen nampilin logo Sheffield United selama 2 detik sebelum masuk ke homepage.

### Homepage
Ada logo klub di tengah, banner merah dengan nama klub, deskripsi singkat, dan 3 tombol menu:
- Club History (sejarah klub)
- Head Coach (info pelatih)
- Team Squad (daftar pemain)

### Club History
Nampilin sejarah lengkap Sheffield United dari tahun berdiri sampe sekarang, termasuk prestasi dan fakta menarik tentang klub.

### Head Coach
Nampilin foto pelatih dan informasinya: nama, tanggal lahir, kewarganegaraan. Ada tombol buat lanjut ke halaman daftar pemain.

### Team Squad
Daftar pemain ditampilin dalam bentuk card. Tiap card punya warna berbeda sesuai posisi:
- Goalkeeper: kuning
- Defender: biru
- Midfielder: hijau
- Forward: merah

Kalo card diklik, muncul popup yang nampilin detail pemain lebih lengkap.

---

## Cara Install

1. Download file `app-debug.apk` dari folder `app/build/outputs/apk/debug/`
2. Transfer ke HP Android
3. Install APK
4. Buka aplikasi (pastikan ada koneksi internet)

---

## Struktur Project

```
com.unsoed.responsi1mobile_h1d023119
├── data
│   ├── api
│   │   ├── FootballApiService.kt
│   │   └── RetrofitClient.kt
│   └── model
│       └── TeamResponse.kt
├── ui
│   ├── adapter
│   │   └── PlayerAdapter.kt
│   └── fragment
│       ├── CoachFragment.kt
│       └── PlayerFragment.kt
├── ClubHistoryActivity.kt
├── HomeActivity.kt
├── MainActivity.kt
└── SplashActivity.kt
```

---

## Catatan

Aplikasi ini dibuat untuk memenuhi tugas Responsi 1 mata kuliah Pemrograman Mobile. Data yang ditampilkan real-time dari API football-data.org jadi bisa berubah sesuai update terbaru dari server mereka.
