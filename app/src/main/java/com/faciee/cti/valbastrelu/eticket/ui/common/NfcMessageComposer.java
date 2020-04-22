package com.faciee.cti.valbastrelu.eticket.ui.common;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.util.Log;

import com.faciee.cti.valbastrelu.eticket.base.ETicketApp;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class NfcMessageComposer {
	private static final String TAG = "NfcMessageComposer";
	
	public static void formatTag(Tag tag, NdefMessage ndefMessage) {
		try {
			NdefFormatable ndefFormatable = NdefFormatable.get(tag);
			if (ndefFormatable == null) {
				//TODO toast extension function when converting to kotlin
//				ETicketApp.getCurrentETicketApp().toastMessageShort("Tag is not ndef formatable!");
				return;
			}
			ndefFormatable.connect();
			ndefFormatable.format(ndefMessage);
			ndefFormatable.close();
		} catch (Exception e) {
			Log.e(TAG, "formatTag: " + e.getMessage());
		}
	}
	
	public static void writeNdefMessage(Tag tag, NdefMessage ndefMessage) {
		try {
			if (tag == null) {
				//TODO toast extension function when converting to kotlin
//				ETicketApp.getCurrentETicketApp().toastMessageShort("Tag object cannot be null!");
				return;
			}
			Ndef ndef = Ndef.get(tag);
			if (ndef == null) {
				formatTag(tag, ndefMessage);
			} else {
				ndef.connect();
				if (!ndef.isWritable()) {
					//TODO toast extension function when converting to kotlin
//					ETicketApp.toastMessageShort("Tag is not writable!");
					ndef.close();
				}
				ndef.writeNdefMessage(ndefMessage);
				ndef.close();
				//TODO toast extension function when converting to kotlin
//				ETicketApp.toastMessageShort("Tag written!");
			}
			
		} catch (Exception e) {
			Log.e(TAG, "writeNdefMessage: " + e.getMessage());
		}
	}
	
	public static NdefRecord createTextRecord(String content) {
		try {
			byte[] language;
			language = Locale.getDefault().getLanguage().getBytes("UTF-8");
			
			final byte[] text = content.getBytes("UTF-8");
			final int languageSize = language.length;
			final int textLength = text.length;
			final ByteArrayOutputStream payload = new ByteArrayOutputStream(1 + languageSize + textLength);
			
			payload.write((byte) (languageSize & 0x1F));
			payload.write(language, 0, languageSize);
			payload.write(text, 0, textLength);
			
			return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload.toByteArray());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static NdefMessage createNdefMessage(String content) {
		NdefRecord ndefRecord = createTextRecord(content);
		NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ndefRecord});
		return ndefMessage;
	}
	
	public static String readTextFromTag(NdefMessage ndefMessage) {
		NdefRecord[] ndefRecord = ndefMessage.getRecords();
		if (ndefRecord != null && ndefRecord.length > 0) {
			NdefRecord record = ndefRecord[0];
			return getTextFromNdefRecord(record);
		} else {
			//TODO
//			ETicketApp.toastMessageShort("No NDEF Records found!");
		}
		return null;
	}
	
	private static String getTextFromNdefRecord(NdefRecord ndefRecord) {
		String tagContent = null;
		try{
			byte[] payload = ndefRecord.getPayload();
			String textEncoding = ((payload[0] & 128) == 0)? "UTF-8" : "UTF-16";
			int languageSize = payload[0] & 0063;
			tagContent = new String(payload, languageSize + 1,
					payload.length - languageSize - 1, textEncoding);
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "getTextFromNdefRecord: " + e.getMessage(), e);
		}
		return tagContent;
	}
}
