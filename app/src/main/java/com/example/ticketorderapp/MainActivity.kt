package com.example.ticketorderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ticketorderapp.ui.theme.TicketOrderAppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PemesananTiketScreen()
        }
    }
}

@Composable
fun PemesananTiketScreen() {
    var jumlahTiket by remember { mutableIntStateOf(1) }
    val hargaPerTiket = 25000
    val totalBayar = jumlahTiket * hargaPerTiket

    val formatRupiah = { angka: Int ->
        val formatter = NumberFormat.getNumberInstance(Locale("id", "ID"))
        "Rp${formatter.format(angka)}"
    }

    // 1. Root Column sekarang warnanya disamakan dengan header (Biru)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2196F3)) //Background Biru
    ) {
        // --- BAGIAN HEADER (Biru) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 32.dp), // Background biru dihapus karena root sudah biru
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.ConfirmationNumber,
                contentDescription = "Icon Tiket",
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pemesanan Tiket",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Pesan tiket dengan mudah!",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
        }

        // --- BAGIAN BACKGROUND ABU-ABU ROUNDED ---
        // 2. Kita buat Column yang memenuhi sisa layar bawah
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)) // Ujung atas melengkung
                .background(Color(0xFFF3F4F6)) // Warna abu-abu terang
                .padding(top = 24.dp) // Memberi jarak agak turun dari lengkungan abu-abu
        ) {

            // --- BAGIAN KONTEN (Card Putih) ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), // 3. Offset negatif dihapus
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    // Harga Tiket
                    Text(text = "Harga Tiket", fontWeight = FontWeight.Bold, color = Color.DarkGray)
                    Text(
                        text = formatRupiah(hargaPerTiket),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3)
                    )
                    Text(text = "per tiket", color = Color.Gray, fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(24.dp))
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(24.dp))

                    // Jumlah Tiket
                    Text(text = "Jumlah Tiket", fontWeight = FontWeight.Bold, color = Color.DarkGray)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                if (jumlahTiket > 1) jumlahTiket--
                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                            modifier = Modifier.size(48.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(text = "-", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }

                        Text(
                            text = "$jumlahTiket",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Button(
                            onClick = { jumlahTiket++ },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                            modifier = Modifier.size(48.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(text = "+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(24.dp))

                    // Total Bayar
                    Text(text = "Total", fontWeight = FontWeight.Bold, color = Color.DarkGray)
                    Text(
                        text = formatRupiah(totalBayar),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4CAF50) // Hijau
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Tombol Reset
                    Button(
                        onClick = { jumlahTiket = 1 },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)), // Merah
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reset")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "RESET", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicketOrderAppTheme {
        Greeting("Android")
    }
}