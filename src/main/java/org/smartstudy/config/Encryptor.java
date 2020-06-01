package org.smartstudy.config;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import org.springframework.stereotype.Component;

/**
 * @Description: This classs is used for encryption and decryption purpose.
 * @author: Jitesh Rawat
 
 */
@Component
public class Encryptor {

	private String key = "$e(uRe~K3y_@9^3#";

	private String initVector = "$e(uReIn!tV3(t0r";

	private String encoderPattern = "UTF-8";

	private String algo = "AES";

	private String cipherPattern = "AES/CBC/PKCS5PADDING";

	public String encrypt(String value) {
		try {

			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(encoderPattern));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(encoderPattern), algo);
			Cipher cipher = Cipher.getInstance(cipherPattern);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {
			ex.getMessage();
		}

		return null;
	}

	public String decrypt(String encrypted)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(encoderPattern));
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(encoderPattern), algo);
		Cipher cipher = Cipher.getInstance(cipherPattern);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
		return new String(original);
	}

	public String encode(CharSequence arg0) {
		return encrypt(arg0.toString());
	}

	public boolean matches(CharSequence arg0, String arg1)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return arg0.equals(decrypt(arg1));
	}

}
