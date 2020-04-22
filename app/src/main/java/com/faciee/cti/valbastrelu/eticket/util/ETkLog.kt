package com.faciee.cti.valbastrelu.eticket.util

import android.content.Context
import android.util.Log
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

object ETkLog {
	private const val TAG = "ETkLog"
	private const val LOG_FILE = "eticket_log.txt"
	private var stream: FileOutputStream? = null
	fun f(tag: String?, message: String?, context: Context) {
		Log.d(TAG, "f: writing stream to path " + context.filesDir)
		val time = Calendar.getInstance().time.toString()
		val line = String.format("%s %s : %s \n", time, tag, message)
		try {
			stream = context.openFileOutput(LOG_FILE, Context.MODE_APPEND)
		} catch (fnfe: FileNotFoundException) {
			fnfe.printStackTrace()
		}
		try {
			stream !!.write(line.toByteArray())
		} catch (ioe: IOException) {
			ioe.printStackTrace()
		} finally {
			if (stream != null) {
				try {
					stream !!.close()
				} catch (e1: IOException) {
					e1.printStackTrace()
				}
			}
		}
	}
}