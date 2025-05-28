package com.example.nesnetabanlproje

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nesnetabanlproje.ui.theme.NesneTabanlıProjeTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var loginRegister: LoginRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        loginRegister = LoginRegister(auth)

        enableEdgeToEdge()

        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            NesneTabanlıProjeTheme(darkTheme = isDarkTheme) {
                var currentUser by remember { mutableStateOf(auth.currentUser) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    if (currentUser != null) {
                        HomeScreen(
                            email = currentUser?.email ?: "Bilinmiyor",
                            onLogout = {
                                auth.signOut()
                                currentUser = null
                            },
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = { isDarkTheme = !isDarkTheme }
                        )
                    } else {
                        LoginRegisterScreen(
                            loginRegister = loginRegister,
                            onLoginSuccess = {
                                currentUser = auth.currentUser
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    email: String,
    onLogout: () -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("siteler") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == "siteler",
                    onClick = { selectedTab = "siteler" },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Siteler") },
                    label = { Text("Siteler") }
                )
                NavigationBarItem(
                    selected = selectedTab == "profil",
                    onClick = { selectedTab = "profil" },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
                    label = { Text("Profil") }
                )
                NavigationBarItem(
                    selected = selectedTab == "ayarlar",
                    onClick = { selectedTab = "ayarlar" },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Ayarlar") },
                    label = { Text("Ayarlar") }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "siteler" -> SitelerScreen(isDarkTheme = isDarkTheme)
                "profil" -> ProfilScreen(email = email, onLogout = onLogout)
                "ayarlar" -> AyarlarScreen(isDarkTheme = isDarkTheme, onToggleTheme = onToggleTheme)
            }
        }
    }
}

@Composable
fun SitelerScreen(isDarkTheme: Boolean) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SiteCard(
            title = "Şifre Oluşturucu",
            description = "Güvenli Şifre Oluşturma Aracı.",
            imageUrl = "https://resimlink.rf.gd/i/VEyMRu.png",
            buttonText = "Adrese Git",
            isDarkTheme = isDarkTheme,
            onClick = {
                openWebsite(context, "https://genpass.rf.gd")
            }
        )

        SiteCard(
            title = "QR Kod Oluşturucu",
            description = "Link ve Metinleri QR Koda Dönüştürme Aracı.",
            imageUrl = "https://resimlink.rf.gd/i/xeeZyZ.png",
            buttonText = "Adrese Git",
            isDarkTheme = isDarkTheme,
            onClick = {
                openWebsite(context, "https://qr-codegenerate.rf.gd")
            }
        )

        SiteCard(
            title = "Sayı Sistemleri Dönüştürücü",
            description = "Sayı sistemleri arasında kolayca dönüşüm yapın.",
            imageUrl = "https://resimlink.rf.gd/i/G9XepK.png",
            buttonText = "Adrese Git",
            isDarkTheme = isDarkTheme,
            onClick = {
                openWebsite(context, "https://b2d.rf.gd")
            }
        )
        SiteCard(
            title = "Link Kısaltma Servisi",
            description = "Link ve metinleri kısa linke dönüştürme aracı.",
            imageUrl = "https://resimlink.rf.gd/i/uBuHRk.png",
            buttonText = "Adrese Git",
            isDarkTheme = isDarkTheme,
            onClick = {
                openWebsite(context, "https://trlink.rf.gd")
            }
        )
        SiteCard(
            title = "Resim Kısaltma Servisi",
            description = "Resimleri kısa linke dönüştürme aracı.",
            imageUrl = "https://resimlink.rf.gd/i/C8DwN2.png",
            buttonText = "Adrese Git",
            isDarkTheme = isDarkTheme,
            onClick = {
                openWebsite(context, "https://resimlink.rf.gd")
            }
        )
        SiteCard(
            title = "Resim Kısaltma Servisi 2",
            description = "Resimleri kısa linke dönüştürme aracı.",
            imageUrl = "https://resimlink.rf.gd/i/3EpmMW.png",
            buttonText = "Adrese Git",
            isDarkTheme = isDarkTheme,
            onClick = {
                openWebsite(context, "https://hizlipict.rf.gd")
            }
        )
    }
}

@Composable
fun SiteCard(
    title: String,
    description: String,
    imageUrl: String,
    buttonText: String,
    isDarkTheme: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .size(96.dp)
                        .padding(end = 20.dp)
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isDarkTheme) Color(0xFFBB86FC) else Color(0xFF6200EE),
                    contentColor = if (isDarkTheme) Color.Black else Color.White
                )
            ) {
                Text(text = buttonText, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Composable
fun ProfilScreen(email: String, onLogout: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .heightIn(min = 220.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Hesabınız:",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "$email",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350))
                ) {
                    Text("Çıkış Yap", color = Color.White, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
fun AyarlarScreen(isDarkTheme: Boolean, onToggleTheme: () -> Unit) {
    val cardColor = if (isDarkTheme) Color(0xFF1E1E2F) else MaterialTheme.colorScheme.surfaceVariant
    val buttonColor = if (isDarkTheme) Color(0xFF03DAC6) else Color(0xFF6200EE)
    val buttonTextColor = if (isDarkTheme) Color.Black else Color.White
    val titleColor = if (isDarkTheme) Color(0xFFE1E1E1) else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .heightIn(min = 180.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Ayarlar",
                    style = MaterialTheme.typography.headlineMedium,
                    color = titleColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onToggleTheme,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor,
                        contentColor = buttonTextColor
                    )
                ) {
                    Text(
                        text = if (isDarkTheme) "Açık Tema" else "Koyu Tema",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}


fun openWebsite(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
