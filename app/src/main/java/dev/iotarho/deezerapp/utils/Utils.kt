package dev.iotarho.deezerapp.utils

import java.util.*

object Utils {

    fun randomSearch(): String {
        val listArtist = arrayOf(
            "Linkin Park",
            "Daft Punk",
            "Metallica",
            "Red Hot Chili Peppers",
            "Lenny Kravitz",
            "Bob Marley"
        )
        val r = Random()
        return listArtist[r.nextInt(listArtist.size)]
    }
}