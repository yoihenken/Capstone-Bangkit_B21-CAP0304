package id.B21_CAP0304.RecheckApps

import android.icu.text.NumberFormat
import java.util.*

fun Long.toRupiah(): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(this).toString()
}