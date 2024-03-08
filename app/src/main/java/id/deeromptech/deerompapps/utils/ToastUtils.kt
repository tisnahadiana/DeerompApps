package id.deeromptech.deerompapps.utils

import android.content.Context
import android.widget.Toast

class ToastUtils {
    companion object {
        fun showMessage(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}