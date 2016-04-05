package com.gouuse.checkin.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.security.MessageDigest;

/***
 * 基本工具类
 * 
 * @author Apual
 * 
 */
public class Basic_Util {

	private static Basic_Util baseUtil_instant = null;

	private Basic_Util() {
	}

	/**
	 * 
	 * @return 返回baseUtile实例
	 */
	public static Basic_Util getInstance() {
		synchronized (Basic_Util.class) {
			if (baseUtil_instant == null) {
				baseUtil_instant = new Basic_Util();
			}
		}
		return baseUtil_instant;
	}
	/**
	 * 
	 * @param str
	 *            要加密的字符串
	 * @function 对字符串进行MD5加密(MD5加密，32位 )
	 */
	public String MD5_Encryption(String str) {
		
//		String key = "+bab8af935901d5b86ccb1d27c4985c32";
//		str = str+key;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 获取手机设备号
	 * zhengjiong
	 * @param context
	 * @return
	 */
	public static String getSN(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

		return tm.getDeviceId();
		/*if (tm.getSimSerialNumber() != null) {
			return tm.getSimSerialNumber().toString();
		}else {
			return tm.getDeviceId();
		}*/
	}
	
}
