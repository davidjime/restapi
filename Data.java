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

    public void createTable() {
        Connection c = null;
        Statement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");

            c = this.connect();
            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS movements " +
            "(name TEXT PRIMARY KEY NOT NULL," +
            " primaryMuscle TEXT NOT NULL, " +
            " equipment TEXT, " +
            " auxiliaryMuscle TEXT)";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        }

        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public void insert(String name, String primaryMuscle, 
                        String equipment, String auxiliaryMuscle) {
        String sql = 
        "INSERT INTO movements(name,primaryMuscle,equipment) VALUES(?,?,?,?) ";
        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, primaryMuscle);
            pstmt.setString(3, equipment);
            pstmt.setString(4, auxiliaryMuscle);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private ResultSet executeQuery(String sql) {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            c = this.connect();
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public ResultSet GetMovement(String movementName) {
        String sql = "SELECT DISTINCT * " +
                "FROM movements " +
                "WHERE name = \""+movementName+"\"";
        return executeQuery(sql);
    }
}
