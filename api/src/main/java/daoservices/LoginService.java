package daoservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.SQLConnection;
import pojos.LoginResponse;
import queries.SQLQueries;

public class LoginService {

	public LoginResponse getInAppLoginResponse(String username, String userPassword)	{
		
		//TODO: use hashed password
		 
		LoginResponse loginResponse = new LoginResponse();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = new SQLConnection().getConnection("test_db");

			preparedStatement = connection.prepareStatement(SQLQueries.SELECT_USER_IN_APP);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, userPassword);
			
			
			resultSet = preparedStatement.executeQuery();
			
			System.out.println(preparedStatement.toString());

			loginResponse = getResponse(resultSet); 
		} catch (Exception e) {
			loginResponse = getResponseWithException("Something went wrong!");
		}
		finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return loginResponse;
	}

	private LoginResponse getResponseWithException(String message) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setAuthenticated(false);
		loginResponse.setUserId(0);
		loginResponse.setUserEmailId("");
		loginResponse.setUsername("");
		loginResponse.setMessage(message);
		return loginResponse;
	}

	private LoginResponse getResponse(ResultSet resultSet) throws SQLException {
		
		if(!resultSet.next()) {
			return getResponseWithException("The given username and password does not match or exist");
		}
		
		int userId = resultSet.getInt(resultSet.findColumn("user_id"));
		String username = resultSet.getString("username");
		String userEmailId = resultSet.getString("user_email_id");
		String userPhone = resultSet.getString("user_phone");

		LoginResponse loginResponse = new LoginResponse();

		loginResponse.setAuthenticated(true);
		loginResponse.setUserId(userId);
		loginResponse.setUserEmailId(userEmailId);
		loginResponse.setUsername(username);
		loginResponse.setUserPhone(userPhone);
		return loginResponse;
	}
}
