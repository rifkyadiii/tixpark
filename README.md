# TixPark App - Frontend (Aplikasi Android)

Selamat datang di repositori frontend untuk **TixPark App**. Repositori ini berisi kode sumber untuk aplikasi Android yang berfungsi sebagai antarmuka pengguna untuk layanan pemesanan tiket online TixPark.

Aplikasi ini dirancang untuk memberikan pengalaman pemesanan tiket yang cepat, aman, dan mudah diakses, dengan antarmuka yang modern dan intuitif.

-----

## 📢 Status Proyek

**Penting:** Saat ini, aplikasi berfungsi sebagai **prototipe UI/UX**. Aplikasi **belum terhubung** ke backend microservices mana pun dan masih menggunakan data dummy untuk tampilan.

-----

## 🏛️ Arsitektur Sistem yang Direncanakan

Meskipun repositori ini adalah sisi **frontend**, aplikasi ini **dirancang untuk** nantinya terhubung ke **backend** yang dibangun dengan arsitektur **microservices** untuk memastikan skalabilitas, fleksibilitas, dan kemudahan pemeliharaan.

**Alur arsitektur yang direncanakan:**
`Aplikasi Android (Repo Ini) ↔️ API Gateway (Express.js) ↔️ [Auth Service, Booking Service, Payment Service (Kotlin & Prisma)]`

  * **API Gateway (Express.js)**: Titik masuk tunggal untuk semua permintaan dari aplikasi, yang menangani routing, otentikasi, dan keamanan awal.
  * **Microservices (Kotlin)**: Layanan-layanan independen yang menangani logika bisnis spesifik.
  * **Database (MySQL & Prisma ORM)**: Digunakan untuk manajemen data yang efisien dan aman.

-----

## ✨ Fitur Aplikasi (Tampilan UI)

  * **Tampilan Autentikasi Pengguna**: UI untuk proses pendaftaran dan login yang aman.
  * **Antarmuka Pemesanan Tiket Online**: Tampilan untuk platform pemesanan tiket dari berbagai perangkat.
  * **Tampilan Sistem Pembayaran**: Antarmuka untuk transaksi pembayaran internal.
  * **Antarmuka Modern**: UI/UX yang dirancang agar mudah digunakan oleh semua kalangan.

-----

## 🛠️ Teknologi yang Digunakan

### Frontend (Repositori Ini)

  * **Platform**: Android
  * **Bahasa**: Kotlin
  * **Build Tool**: Gradle dengan Kotlin DSL (.kts)

### Backend yang Direncanakan (Sebagai Konteks)

  * **Arsitektur**: Microservices
  * **API Gateway**: Express.js
  * **Bahasa Layanan**: Kotlin
  * **Database**: MySQL
  * **ORM**: Prisma ORM

-----

## 🚀 Memulai / Cara Build

Ikuti langkah-langkah berikut untuk menjalankan proyek ini di lingkungan pengembangan lokal Anda.

### 1\. Prasyarat

  * [Android Studio](https://developer.android.com/studio) (versi terbaru direkomendasikan).
  * Java Development Kit (JDK) 11 atau yang lebih baru.

### 2\. Instalasi & Build

1.  **Clone repositori ini:**
    ```bash
    git clone [URL_REPOSITORI_ANDA]
    cd tixpark-frontend
    ```
2.  **Buka proyek dengan Android Studio.**
3.  Tunggu hingga Android Studio selesai melakukan **Gradle Sync** untuk mengunduh semua dependensi yang diperlukan.
4.  **Jalankan aplikasi:**
      * Pilih target (Emulator atau perangkat fisik).
      * Klik **Run 'app'** (Shift + F10).

-----

## 📸 Tampilan Aplikasi (Screenshots)
|----------|----------|----------|
| <img src="https://github.com/user-attachments/assets/51531edb-2d8b-4be5-b2c7-ead21bfd9eef" width="250"> | <img src="https://github.com/user-attachments/assets/fa85cffe-4be8-456d-8d30-a611a594bc4d" width="250"> | <img src="https://github.com/user-attachments/assets/b4d8ea70-9b4a-4446-819f-128d6cdacf67" width="250"> |
| <img src="https://github.com/user-attachments/assets/b7759b6d-5e33-4811-9032-1448dd2f1664" width="250"> | <img src="https://github.com/user-attachments/assets/b6e41583-d78e-44f3-b4e3-c15fe946c230" width="250"> | <img src="https://github.com/user-attachments/assets/5a4ac107-4b73-440b-954a-1218dfa88b93" width="250"> |
| <img src="https://github.com/user-attachments/assets/f07345ae-c9af-4e6f-b04c-6544e71b9ab4" width="250"> | <img src="https://github.com/user-attachments/assets/f42cc7bf-9a08-425d-af70-9e2823db8f54" width="250"> |   |
