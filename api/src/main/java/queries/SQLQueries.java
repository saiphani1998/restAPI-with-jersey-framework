package queries;

public class SQLQueries
{
	public static final String SELECT_USER_WITH_USERNAME = "SELECT * FROM users WHERE username = ?";
	public static final String INSERT_USER_IN_APP = "INSERT INTO users (user_email_id, user_phone, username, user_password, login_mode) "
			+ "VALUES (?, ?, ?, PASSWORD(?), ?)";
	public static final String SELECT_USER_IN_APP = "SELECT * FROM users WHERE username = ? AND user_password = PASSWORD(?)";
	public static final String UPDATE_USER_PASSWORD = "UPDATE users SET user_password = PASSWORD(?) WHERE username = ?";
}
