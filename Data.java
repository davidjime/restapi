import java.sql.*;

public class Data {
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:/dataStore.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    private static Data instance = new Data();
	public static Data getInstance(){
		return instance;
	}
}
