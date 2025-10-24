# API Configuration - Sheffield United FC

## 📡 API Details

### Base URL
```
https://api.football-data.org/
```

### API Token
```
194ef2d522c9467ea0e682a20742254f
```

### Team ID
```
356 (Sheffield United FC)
```

---

## ✅ API Service Implementation

### FootballApiService.kt
```kotlin
interface FootballApiService {
    @Headers("X-Auth-Token: 194ef2d522c9467ea0e682a20742254f")
    @GET("v4/teams/{id}")
    fun getTeam(@Path("id") teamId: Int): Call<TeamResponse>
}
```

**Penjelasan:**
- `@Headers` - Token API dikirim di header setiap request
- `@GET("v4/teams/{id}")` - Endpoint untuk mendapatkan data team
- `@Path("id")` - Parameter team ID yang bisa diubah
- `Call<TeamResponse>` - Return type menggunakan Callback (bukan suspend/coroutine)

---

## 🔌 Cara Memanggil API

### Di Fragment/Activity:
```kotlin
val call = RetrofitClient.apiService.getTeam(356)

call.enqueue(object : retrofit2.Callback<TeamResponse> {
    override fun onResponse(
        call: retrofit2.Call<TeamResponse>,
        response: retrofit2.Response<TeamResponse>
    ) {
        if (response.isSuccessful && response.body() != null) {
            val team = response.body()!!
            // Gunakan data team
        }
    }
    
    override fun onFailure(call: retrofit2.Call<TeamResponse>, t: Throwable) {
        // Handle error
    }
})
```

---

## 📱 Implementasi di Aplikasi

### 1. CoachFragment.kt
- Memanggil `getTeam(356)` untuk mendapatkan data Sheffield United FC
- Menampilkan info klub dan pelatih
- Callback `onResponse` untuk handle sukses
- Callback `onFailure` untuk handle error

### 2. ClubHistoryActivity.kt
- Memanggil `getTeam(356)` untuk mendapatkan data klub
- Menampilkan sejarah dan deskripsi klub
- Menggunakan data dari API yang sama

---

## 🧪 Test di Postman

### Request:
```
GET https://api.football-data.org/v4/teams/356
```

### Headers:
```
X-Auth-Token: 194ef2d522c9467ea0e682a20742254f
```

### Response (contoh):
```json
{
  "id": 356,
  "name": "Sheffield United FC",
  "crest": "https://...",
  "founded": 1889,
  "venue": "Bramall Lane",
  "coach": { ... },
  "squad": [ ... ]
}
```

---

## ✅ Checklist Koneksi API

- [x] API Token sudah ada di Headers annotation
- [x] Team ID 356 dipanggil langsung di kode
- [x] Menggunakan `Call<TeamResponse>` dengan callback
- [x] RetrofitClient sudah dikonfigurasi dengan benar
- [x] Base URL: `https://api.football-data.org/`
- [x] Endpoint: `v4/teams/{id}`
- [x] Tested dan siap digunakan

---

**Status: ✅ API SUDAH TERHUBUNG KE FOOTBALL-DATA.ORG & POSTMAN!**
