package com.faciee.cti.valbastrelu.eticket.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
fun <L> LiveData<L>.testableValue(): L {
	val data = arrayOf<Any>(1)
	val latch = CountDownLatch(1)
	val value = object : Observer<L> {
		override fun onChanged(t: L) {
			data[0] = t as Any
			latch.countDown()
			this@testableValue.removeObserver(this)
		}
	}

	this.observeForever(value);
	latch.await(2, TimeUnit.SECONDS);
	return data[0] as L
}