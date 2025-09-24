    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public final class DBUtil {

        private static final String USERNAME = "****";
        private static final String PASSWORD = "******";
        private static final String URL =
                "jdbc:mysql://localhost:3306/my_library"
                        + "?useSSL=false"
                        + "&allowPublicKeyRetrieval=true"
                        + "&serverTimezone=UTC"
                        + "&useUnicode=true&characterEncoding=utf8"
                        + "&rewriteBatchedStatements=true";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        public static void showErrorMessage(SQLException e) {
            System.err.println("SQLState : " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("Message   : " + e.getMessage());
        }
    }


