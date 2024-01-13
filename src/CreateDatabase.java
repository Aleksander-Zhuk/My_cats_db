import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

public class CreateDatabase {
    public static void main(String[] args) {

        My_cats my_cats = new My_cats();
        my_cats.add_all_types();

        // запускае функцию insert_type и выполняет её 3 раза
       /* Scanner str = new Scanner(System.in);
        for(int a = 0; a < 3; a++ ){
        my_cats.insert_type(str.nextLine());
        }*/
    }
}
class My_cats {
        public My_cats() {
                try (Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats_1.db")) {
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
                        conn.close();
                    }
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        }
        public void add_all_types() {
            File file = new File("D:/Изображения/ЗАГРУЗКИ/types.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(file));
                 Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats_1.db")) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        String query = "INSERT INTO types (id,type) VALUES (?,?);";
                        PreparedStatement statement = conn.prepareStatement(query);
                        statement.setString(2,line.trim());
                        statement.executeUpdate();
                    }
                    catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }
                }
                conn.close();
                System.out.println("Данные успешно загружены!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    //функция добовляет информацию в базу данных (type, Strihg)
   /* public void insert_type(String type) {
                try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/My_cats.db");
            String query = "INSERT INTO types (id,type) VALUES (?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(2, type);
            connection.close();
        }
                catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
        }*/
}

