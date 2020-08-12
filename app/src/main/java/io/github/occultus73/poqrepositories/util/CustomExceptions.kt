package io.github.occultus73.poqrepositories.util

import android.content.Context
import io.github.occultus73.poqrepositories.R
import java.io.IOException

class CustomExceptions(context: Context) {
    val noConnectivityException = IOException(context.getString(R.string.no_internet))
}