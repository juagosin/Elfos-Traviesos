package com.calleserpis.elfonavidenyo.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/**
 * Banner de prueba de AdMob que ocupa todo el ancho disponible.
 * Usado en la parte inferior de cada pantalla, justo encima de la bottom bar.
 */
@Composable
fun ElfBottomBannerAd(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AndroidView(
        modifier = modifier,
        factory = {
            AdView(context).apply {
                // Banner estándar (320x50) adaptado al ancho
                setAdSize(AdSize.BANNER)
                adUnitId = "ca-app-pub-5233740529251048/8133181703"
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}

