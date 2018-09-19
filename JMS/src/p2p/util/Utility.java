package p2p.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class Utility {
	public static final int SALT_LEN = 8;
	protected static final String DESALGORITHM = "PBEWithMD5AndDES";
	public static final byte salt[] = { -41, -96, -6, -67, 46, -37, -21, -118 };

	public static List<String> getUsers() {
		List<String> users = new ArrayList<>();
		users.add("mounica");
		users.add("swetha");
		users.add("prakruthi");
		users.add("gvr");
		return users;
	}

	public static byte[] getSalt() {
		byte b[] = Arrays.copyOf(salt, SALT_LEN);
		return b;
	}

	private static Cipher getCipherDES(int mode, String passwd) {
		try {
			Cipher cipher = null;
			SecretKey secretKey = null;
			PBEParameterSpec paramSpec;
			paramSpec = new PBEParameterSpec(getSalt(), 20);
			PBEKeySpec pbekeyspec = new PBEKeySpec(passwd.toCharArray());
			SecretKeyFactory skf = SecretKeyFactory.getInstance(DESALGORITHM);
			secretKey = skf.generateSecret(pbekeyspec);
			cipher = Cipher.getInstance(DESALGORITHM);
			cipher.init(mode, secretKey, paramSpec);
			return cipher;
		} catch (Exception e) {
			System.out.println("Error in Getting Cipher: " + e.getMessage());
			return null;
		}

	}

	public static String encryptDES(String str, String pwd) {
		String estr = "";
		try {
			Cipher encCipher = getCipherDES(Cipher.ENCRYPT_MODE, pwd);
			byte[] bU = str.getBytes("UTF8");
			byte[] ebU = encCipher.doFinal(bU);
			estr = new String(Base64.encodeBase64(ebU));
		} catch (Exception e) {
			estr = "UTF Encoding id not supported";
			System.out.println(estr);
		}
		return estr;
	}

	public static String decryptDES(String str, String pwd) {
		String dstr = "";
		try {
			Cipher decCipher = getCipherDES(Cipher.DECRYPT_MODE, pwd);
			byte[] ebU = Base64.decodeBase64(str);
			byte[] bU = decCipher.doFinal(ebU);
			dstr = new String(bU, "UTF8");
		} catch (Exception e) {
			System.out.println("UTF Encoding id not supported");
		}
		return dstr;
	}
}
