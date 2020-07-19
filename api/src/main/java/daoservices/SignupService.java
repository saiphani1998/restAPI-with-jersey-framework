package daoservices;

import java.sql.*;

import connections.SQLConnection;
import pojos.SignupResponse;
import queries.SQLQueries;
import utilities.PasswordValidator;

public class SignupService {
	
	public SignupResponse getInAppSignupResponse (String userEmailId, String userPhone,
			String username, String userPassword, String loginMode) {
		SignupResponse signupResponse = new SignupResponse();

		PasswordValidator passwordValidator = new PasswordValidator();
		if (!passwordValidator.validatePassword(userPassword)) {
			return getResponseWithException("Password should contain a lowercase, uppercase, a special character and should be of length more than 8");
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;

		try {
			connection = new SQLConnection().getConnection("test_db");

			preparedStatement = connection.prepareStatement(SQLQueries.SELECT_USER_WITH_USERNAME);
			preparedStatement.setString(1, username);

			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				preparedStatement = connection.prepareStatement(SQLQueries.INSERT_USER_IN_APP,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, userEmailId);
				preparedStatement.setString(2, userPhone);
				preparedStatement.setString(3, username);
				preparedStatement.setString(4, userPassword);
				preparedStatement.setString(5, loginMode);

				System.out.println(preparedStatement.toString());

				if (preparedStatement.execute()) {
					return getResponseWithException("something went Wrong!");
				}

				resultSet = preparedStatement.getGeneratedKeys();
				resultSet.next();
				signupResponse = getResponse(resultSet.getInt(1), userPhone, userEmailId, username, userPassword);
			} else {
				signupResponse = getResponseWithException("Username unavailable");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			signupResponse = getResponseWithException("Something went wrong");
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				System.out.println("E " + e.getMessage());
				e.printStackTrace();
			}
		}

		return signupResponse;
	}

	private SignupResponse getResponseWithException(String err) {
		SignupResponse signupResponse = new SignupResponse();
		
		signupResponse.setSuccess(false);
		signupResponse.setUserId(0);
		signupResponse.setMessage(err);
		return signupResponse;
	}


	private SignupResponse getResponse(int userId, String userPhone, String userEmailId, String username, String userPassword) {
		SignupResponse signupResponse = new SignupResponse();

		signupResponse.setSuccess(true);
		signupResponse.setUserId(userId);
		signupResponse.setUserPhone(userPhone);
		signupResponse.setUserEmailId(userEmailId);
		signupResponse.setUsername(username);
		signupResponse.setUserPassword(userPassword);
		return signupResponse;
	}
}
