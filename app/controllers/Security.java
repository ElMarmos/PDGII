package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import models.Usuario;

public class Security extends Secure.Security {

	private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
	
	public static boolean authenticate(String username, String password) {
		Usuario usuario = Usuario.find("byUsuario", username.toLowerCase()).first();
		return usuario != null && usuario.contrasena.equals(MD5(password));
	}

	public static String MD5(String stringAEncriptar)
	{
		try
		{
			MessageDigest msgd = MessageDigest.getInstance("MD5");
			byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
			StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
			for (int i = 0; i < bytes.length; i++)
			{
				int bajo = (int)(bytes[i] & 0x0f);
				int alto = (int)((bytes[i] & 0xf0) >> 4);
				strbCadenaMD5.append(CONSTS_HEX[alto]);
				strbCadenaMD5.append(CONSTS_HEX[bajo]);
			}
			return strbCadenaMD5.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
