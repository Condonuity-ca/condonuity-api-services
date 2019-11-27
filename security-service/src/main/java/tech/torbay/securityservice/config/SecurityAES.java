package tech.torbay.securityservice.config;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.web.util.UriComponentsBuilder;

import tech.torbay.securityservice.entity.User;

public class SecurityAES {
	
	private static String secretKey = "@torbay.tech";
	private static String salt = "contonuity";
	
	public static String encrypt(String strToEncrypt)
	{
	    try
	    {
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
	         
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
	    }
	    catch (Exception e)
	    {
	        System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	}
	 
	public static String decrypt(String strToDecrypt) {
	    try
	    {
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
	         
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
	        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	    }
	    catch (Exception e) {
	        System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	}
	
	
	public String encode(String raw) {
	    return Base64.getUrlEncoder()
	            .withoutPadding()
	            .encodeToString(raw.getBytes(StandardCharsets.UTF_8));
	}

	public String getRegisterEncodedURL(String email, Integer userId, int userType) {
		// TODO Auto-generated method stub
//		String query = UriComponentsBuilder.fromHttpUrl("http://localhost:8383/api/user/resetPassword")
		String query = UriComponentsBuilder.fromHttpUrl("http://condonuity1.onlinedemo.co/register/accept-invite")
				.queryParam(encode("username"), encode(email))
				.queryParam(encode("userId"), encode(String.valueOf(userId)))
				.queryParam(encode("userType"), encode(String.valueOf(userType)))
				.toUriString(); 
		
		//if UserType 1 - client user proceed registration and update account verification status - register org and update verified
		//if UserType 2 - vendor user update account verification status - /vendor/user/invite/accept - update verified
		return query;
	}
	
	public String getClientUserInviteEncodedURL(String email, Integer userId, Integer organisationId, Integer clientUserType, Integer userRole) {
		// TODO Auto-generated method stub
//		String query = UriComponentsBuilder.fromHttpUrl("http://localhost:8383/api/user/resetPassword")
		String query = UriComponentsBuilder.fromHttpUrl("http://condonuity1.onlinedemo.co/client/accept-invite")
				.queryParam(encode("email"), encode(email))
				.queryParam(encode("userId"), encode(String.valueOf(userId)))
				.queryParam(encode("organisationId"), encode(String.valueOf(organisationId)))
				.queryParam(encode("clientUserType"), encode(String.valueOf(clientUserType))) // clientuserType 1-M,2-AM,3-BM(BoardMemebr)
				.queryParam(encode("userRole"), encode(String.valueOf(userRole)))// 1-Admin, 2-User
				.toUriString(); 
		
		return query;
	}
	
	public String getVendorUserInviteEncodedURL(String email, Integer userId, Integer organisationId) {
		// TODO Auto-generated method stub
//		String query = UriComponentsBuilder.fromHttpUrl("http://localhost:8383/api/user/resetPassword")
		String query = UriComponentsBuilder.fromHttpUrl("http://condonuity1.onlinedemo.co/client/accept-invite")
				.queryParam(encode("email"), encode(email))
				.queryParam(encode("userId"), encode(String.valueOf(userId)))
				.queryParam(encode("organisationId"), encode(String.valueOf(organisationId)))
				.toUriString(); 
		
		return query;
	}
}
