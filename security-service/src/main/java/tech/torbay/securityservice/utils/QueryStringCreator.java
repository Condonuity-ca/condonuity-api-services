package tech.torbay.securityservice.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.web.util.UriComponentsBuilder;

import tech.torbay.securityservice.entity.User;

public class QueryStringCreator {

	public String encode(String raw) {
	    return Base64.getUrlEncoder()
	            .withoutPadding()
	            .encodeToString(raw.getBytes(StandardCharsets.UTF_8));
	}

	public String getRegisterEncodedURL(String email, Integer userId, int userType) {
		// TODO Auto-generated method stub
//		String query = UriComponentsBuilder.fromHttpUrl("http://localhost:8383/api/user/resetPassword")
		String query = UriComponentsBuilder.fromHttpUrl("http://condonuityui-dev.azurewebsites.net/register/accept-invite/450")
				.queryParam(encode("username"), encode(email))
				.queryParam(encode("userId"), encode(String.valueOf(userId)))
				.queryParam(encode("userType"), encode(String.valueOf(userType)))
				.toUriString(); 
		
		//if UserType 1 API Call to- client user proceed registration and update account verification status - register org and update verified
		//if UserType 2 API Call to- vendor user update account verification status - /vendor/user/invite/accept - update verified
		return query;
	}
	
	public String getClientUserInviteEncodedURL(String email, Integer userId, Integer organisationId, Integer clientUserType, Integer userRole) {
		// TODO Auto-generated method stub
//		String query = UriComponentsBuilder.fromHttpUrl("http://localhost:8383/api/user/resetPassword")
		String query = UriComponentsBuilder.fromHttpUrl("http://condonuityui-dev.azurewebsites.net/register/accept-invite/450")
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
//		String query = UriComponentsBuilder.fromHttpUrl("http://condonuity1.onlinedemo.co/client/accept-invite")
			 	String query = UriComponentsBuilder.fromHttpUrl("http://condonuityui-dev.azurewebsites.net/register/accept-invite/450")
				.queryParam(encode("email"), encode(email))
				.queryParam(encode("userId"), encode(String.valueOf(userId)))
				.queryParam(encode("organisationId"), encode(String.valueOf(organisationId)))
				.toUriString(); 
		
		return query;
	}
	
	//reset password
		public String getResetPasswordEncodedURL(User user) {
			
			String query = UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:8762/api/condonuity/user/resetPassword")
					.queryParam(encode("username"), encode(user.getUsername()))
					.queryParam(encode("userId"), encode(String.valueOf(user.getUserId())))
					.queryParam(encode("userType"), encode(String.valueOf(user.getUserType())))
					.toUriString(); 
			return query;
		}
		
}
