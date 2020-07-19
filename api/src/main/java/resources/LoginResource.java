package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import daoservices.LoginService;
import pojos.LoginResponse;

@Path("login")
public class LoginResource {	
	private LoginService loginService = new LoginService();

	@POST
	@Path("/inApp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public LoginResponse login (@DefaultValue("") @FormParam("userEmailId") String userEmailId,
			@DefaultValue("") @FormParam("userPhone") String userPhone,
			@DefaultValue("") @FormParam("username") String username, 
			@FormParam("userPassword") String userPassword) {
		return loginService.getInAppLoginResponse(username, userPassword);
	}
}
