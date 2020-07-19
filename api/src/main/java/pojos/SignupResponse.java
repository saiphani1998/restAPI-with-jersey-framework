package pojos;

import com.google.gson.annotations.SerializedName;

public class SignupResponse
{
	@SerializedName("success")
	private boolean isSuccess;
	
	@SerializedName("userId")
	private int userId;
	
	@SerializedName("message")
	private String message;
	
	@SerializedName("username")
	private String username;

	@SerializedName("userEmailId")
	private String userEmailId;

	@SerializedName("userPhone")
	private String userPhone;

	@SerializedName("userPassword")
	private String userPassword;
	
	public boolean isSuccess()
	{
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess)
	{
		this.isSuccess = isSuccess;
	}
	
	public int getUserId()
	{
		return userId;
	}
	
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
