package pojos;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordResponse {

	@SerializedName("successful")
	private boolean isSuccessful;	

	@SerializedName("userId")
	private int userId;

	@SerializedName("username")
	private String username;
    
	@SerializedName("message")
	private String message;
	
	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccess(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId)	{
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message= message;
	}
}
