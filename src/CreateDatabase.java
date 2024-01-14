import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

public class CreateDatabase {
    public static void main(String[] args) {

        Scanner str = new Scanner(System.in);
        My_cats my_cats = new My_cats();

        // добовляет списки из файла
        // my_cats.add_all_types();

        while (true){
            System.out.println("Если вам надо добавить сторку (Y/N)");
            String answer = str.nextLine();
            if(answer.equals("N")){
                break;
            } else  if (answer.equals("Y")){
                System.out.println("Введите id :");
                int id = str.nextInt();
                str.nextLine();
                System.out.println("Введите type:");
                String type = str.nextLine();
                my_cats.insert_type(id,type);
            }
        }

        while (true) {
            System.out.println("Если вам удалить сторку (Y/N)");
            String answer1 = str.nextLine();
            if(answer1.equals("N")){
                break;
            } else {
                if (answer1.equals("Y")){
                System.out.println("Введите id строки:");
                int id = str.nextInt();
                str.nextLine();
                my_cats.delete_type(id);
                }
            }
        }

        while (true) {
            System.out.println("Если вам надо изменить type (Y/N)");
            String answer2 = str.nextLine();
            if (answer2.equals("N")) {
                break;
            } else if (answer2.equals("Y")) {
                System.out.println("Введите id изменяемой строки:");
                int id = str.nextInt();
                str.nextLine();
                System.out.println("Введите type:");
                String new_type = str.nextLine();
                my_cats.update_type(id, new_type);
            }
        }
    }
}
class My_cats {
        public My_cats() {
                try (Connection conn = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats_1.db")) {
                    if (conn != null) {
                        DatabaseMetaData meta = conn.getMetaData();
                    }
                    String sql = "CREATE TABLE IF NOT EXISTS types (\n"
                        + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " type VARCHAR(100) NOT NULL\n"
                        + ");";
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(sql);
                        conn.close();
                    }
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        }
        // функция добавляе список из файла
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
        public void insert_type(int id,String type) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats_1.db");
                String query = "INSERT INTO types (id,type) VALUES (?,?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                statement.setString(2, type);
                statement.executeUpdate();
                connection.close();
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        public void delete_type(int id){
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats_1.db");
                String query = "DELETE FROM types WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                statement.executeUpdate();
                connection.close();
                System.out.println("Строка с id = " + id + " удалена.");
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        public void update_type(int id, String new_type){
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats_1.db");
                String query = "UPDATE types SET type = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(2, id);
                statement.setString(1, new_type);
                statement.executeUpdate();
                connection.close();
                System.out.println("Строка c id = " + id + " изменена на type = \" " + new_type +" \"");
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
}


