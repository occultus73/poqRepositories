package io.github.occultus73.weatherforecast.model.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import io.github.occultus73.poqrepositories.util.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(
    private val context: Context
) : Interceptor {
    companion object {
        private const val TAG = "ConnectivityInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    // https://stackoverflow.com/questions/57277759/getactivenetworkinfo-is-deprecated-in-api-29
    private fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                val nw = cm.activeNetwork ?: return false
                val actNw = cm.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } catch (e: Exception) {
                Log.e(TAG, "isOnline: ", e)
            }
        } else {
            try {
                val nwInfo = cm.activeNetworkInfo ?: return false
                return nwInfo.isConnected
            } catch (e: Exception) {
                Log.e(TAG, "isOnline: ", e)
            }
        }
        return false
    }
}