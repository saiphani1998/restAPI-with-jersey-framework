package daoservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.SQLConnection;
import pojos.ResetPasswordResponse;
import queries.SQLQueries;
import utilities.PasswordValidator;

public class ResetPasswordService {
	public ResetPasswordResponse getResetPasswordResponse(String username, String oldPassword, String newPassword, String confirmNewPassword) {
		
		if (!(newPassword.equals(confirmNewPassword))) {
			return getResponseWithException("Password and confirm passwords do not match");
		}
		
		PasswordValidator passwordValidator = new PasswordValidator();
		if (!passwordValidator.validatePassword(newPassword)) {
			return getResponseWithException("Password should contain a lowercase, uppercase, a special character and should be of length more than 8");
		}
		
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();

		PreparedStatement userPreparedStatement = null;
		PreparedStatement preparedStatement = null;
		ResultSet userDetails = null;
		Connection connection = null;

		try 
		{
			connection = new SQLConnection().getConnection("test_db");
			userPreparedStatement = connection.prepareStatement(SQLQueries.SELECT_USER_IN_APP);
			userPreparedStatement.setString(1, username);
			userPreparedStatement.setString(2, oldPassword);			

			System.out.println(userPreparedStatement.toString());
			userDetails = userPreparedStatement.executeQuery();

			if(!userDetails.next()) {
				return getResponseWithException("The given username and password does not match or exist");
			}

			preparedStatement = connection.prepareStatement(SQLQueries.UPDATE_USER_PASSWORD);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, username);
			
			System.out.println(preparedStatement.toString());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 1) {
				resetPasswordResponse = getResponse(userDetails);
			} else {
				resetPasswordResponse = getResponseWithException("Something went wrong!");
			}
			return resetPasswordResponse;
		} catch (Exception e) {
			resetPasswordResponse= getResponseWithException(e.getMessage());
		} finally {
			try {
				userDetails.close();
				userPreparedStatement.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return resetPasswordResponse;
	}

	private ResetPasswordResponse getResponseWithException(String message) {
		System.out.println("ERROR! "+message);
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
		resetPasswordResponse.setSuccess(false);
		resetPasswordResponse.setUserId(0);
		resetPasswordResponse.setUsername("");
		resetPasswordResponse.setMessage(message);
		return resetPasswordResponse;
	}

	private ResetPasswordResponse getResponse(ResultSet resultSet) throws SQLException {
		int userId = resultSet.getInt("user_id");
		System.out.println("Password reset success for user: "+userId);
		String username = resultSet.getString("username");
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();

		resetPasswordResponse.setSuccess(true);
		resetPasswordResponse.setUserId(userId);
		resetPasswordResponse.setUsername(username);
		return resetPasswordResponse;
	}
}
