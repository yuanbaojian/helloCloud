package com.ybj.auth.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD转换
 * @author caicai.gao
 */
@Slf4j
public class Md5EncryptionUtil {

	/**
	 * 将指定的字符进行MD5的转换 
	 * @param string 指定的字符 
	 * @return String 进行MD转换后的字符    
	 * @author yanggl
	 * @since 2010/07/01 
	 */
	public static String convertMd5(String string) {
		MessageDigest md;
		StringBuilder sb = new StringBuilder();

		try {
			md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes());
			byte[] r = md.digest();
			for (byte b : r) {
				sb.append(Character.forDigit((b >> 4 & 0x0F), 16));
				sb.append(Character.forDigit((b & 0x0F), 16));
			}
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		}

		return sb.toString();
	}
}
