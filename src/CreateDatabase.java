import java.sql.*;
public class CreateDatabase {
    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/My_cats.db")) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("База данных создана.");
            }
            String sql = "CREATE TABLE IF NOT EXISTS types (\n"
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " type VARCHAR(100) NOT NULL\n"
                    + ");";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Таблица успешно создана.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

