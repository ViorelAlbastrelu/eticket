package com.faciee.cti.valbastrelu.eticket.util

import android.content.res.AssetManager
import android.os.Environment
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets

object AppUtils {
	//check SD card availability
	val isSDCARDAvailable: Boolean
		get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

	//copying the file
	@JvmStatic
	@Throws(IOException::class)
	fun copyFile(stream: InputStream, out: OutputStream) {
		val buffer = ByteArray(1024)
		var read: Int
		while (stream.read(buffer).also { read = it } != - 1) {
			out.write(buffer, 0, read)
		}
	}

	// filename = "transurb_schedule.json"
	fun loadJSONFromAsset(assets: AssetManager, fileName: String): String? {
		val json: String?
		json = try {
			val stream = assets.open(fileName)
			val size = stream.available()
			val buffer = ByteArray(size)
			stream.read(buffer)
			stream.close()
			String(buffer, StandardCharsets.UTF_8)
		} catch (ex: IOException) {
			ex.printStackTrace()
			return null
		}
		return json
	}

	fun <C> getObjectFromJSON(json: String, type: Class<C>): C {
		return Gson().fromJson(json, type)
	}
}