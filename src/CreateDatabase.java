import java.sql.*;
import java.util.Scanner;

public class CreateDatabase {
    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        My_cats my_cats = new My_cats();
        for(int a = 0; a < 3; a++ ){
        my_cats.insert_type(str.nextLine());}
    }
}
class My_cats {
    boolean flag = true;
        My_cats() {
            if(flag){
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
                        flag = false;
                    }
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    public void insert_type(String type) {
                try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/My_cats.db");
            String query = "INSERT INTO types (id, type) VALUES (?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(2, type);
            int rowsInserted = statement.executeUpdate();
            connection.close();
        }
                catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
        }
}

