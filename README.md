# 🚀 Firebase Authentication + Web Yönlendirme Android Uygulaması

Bu proje, kullanıcıların Firebase ile **giriş/kayıt olabileceği** ve ardından HTML/CSS/JS ile oluşturulmuş web uygulamalarına yönlendiren bir Android uygulamasıdır.

## 🧩 Özellikler

- 🔐 Firebase Authentication (Giriş / Kayıt)
- 📲 Jetpack Compose ile modern kullanıcı arayüzü
- 🗂️ CardView tarzı liste ile servis gösterimi
- 🌐 Harici web uygulamalarına yönlendirme (örnek: link kısaltma, sayı dönüştürücü)
- 🧭 Alt menü navigasyonu (Siteler, Profil, Ayarlar)
- 🎨 Renkli butonlar ve responsif tasarım

---

## 🖼️ Uygulama Ekranları

### 🔐 Giriş / Kayıt Ekranı

Kullanıcılar Firebase ile email ve şifre kullanarak giriş yapabilir veya kayıt olabilir:

![Login Screen](https://resimlink.rf.gd/i/Y4k9SA.png)

### 🧾 Web Servis Yönlendirme Listesi

Kayıt/Giriş sonrası kullanıcı, kartlar halinde listelenmiş servisler sayfasına yönlendirilir:

![Servis Listesi](https://resimlink.rf.gd/i/p7HFEV.png)

---
![Video_Kaydı](https://www.kapwing.com/videos/683b0107f18008670ccbe6d3)
## 🛠️ Teknolojiler ve Kütüphaneler

- Kotlin + Jetpack Compose
- Firebase Authentication
- Material 3 Design
- Compose Navigation
- Coil (resim gösterimi için)
- Custom Card UI (RoundedCorner + Elevation)
- Responsive Modifier kullanımı (`fillMaxWidth`, `weight`, `padding`)

---

## 🔗 Yönlendirme Servisleri

Aşağıdaki web uygulamalarına yönlendirme yapılmaktadır:

- [Link Kısaltma Servisi](https://trlink.rf.gd)
- [Resim Kısaltma Servisi](https://resimlink.rf.gd)
- [Sayı Sistemleri Dönüştürücü](https://b2d.rf.gd)

---

## 📦 Projeyi Çalıştırma

1. Firebase projenizi oluşturun.
2. `google-services.json` dosyasını `app/` klasörüne ekleyin.
3. `build.gradle` dosyalarında Firebase bağımlılıklarını kontrol edin:
   ```kotlin
   implementation("com.google.firebase:firebase-auth-ktx")
   implementation("com.google.gms:google-services:4.3.15")
