package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import daoservices.ResetPasswordService;
import pojos.ResetPasswordResponse;

@Path("resetpassword")
public class ResetPasswordResource {
	
	private ResetPasswordService resetPasswordService = new ResetPasswordService();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ResetPasswordResponse resetPassword(@DefaultValue("") @FormParam("username") String username,
			@DefaultValue("") @FormParam("oldPassword") String oldPassword,
			@DefaultValue("") @FormParam("newPassword") String newPassword,
			@DefaultValue("") @FormParam("confirmNewPassword") String confirmNewPassword) {
		return resetPasswordService.getResetPasswordResponse(username, oldPassword, newPassword, confirmNewPassword);
	}
}
