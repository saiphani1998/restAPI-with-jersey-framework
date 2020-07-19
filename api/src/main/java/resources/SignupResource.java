package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import daoservices.SignupService;
import pojos.SignupResponse;

@Path("/signup")
public class SignupResource {	
	private SignupService signupService = new SignupService();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public SignupResponse signup(@DefaultValue("") @FormParam("userEmailId") String userEmailId,
			@DefaultValue("") @FormParam("userPhone") String userPhone,
			@FormParam("username") String username,
			@FormParam("password") String password)	{
		return signupService.getInAppSignupResponse(userEmailId, userPhone, username, password, "inApp");
	}
}
