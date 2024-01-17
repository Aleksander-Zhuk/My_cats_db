import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class My_cats {
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
        // функция добавляе список из файла "D:/Изображения/ЗАГРУЗКИ/types.txt"
        public void add_all_types(String file_txt) {
            File file = new File(file_txt);
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
        // функция удаляет type  по id
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
        // функция изменяет type по id
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
        // функция выводит type по id
    public String get_type(int id) throws SQLException {
        String type = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats_1.db");
            String query = "SELECT type FROM types WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                type = resultSet.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        System.out.println(type);
        return type;
    }
    public String[] get_type_where(String where)  throws SQLException {
        List<String> types = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String[] parts = where.split(" ");
        int number = Integer.parseInt(parts[0]);
        String letter = parts[1];

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats_1.db");
            String query = "SELECT type FROM types WHERE id < ? AND type LIKE ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1,number);
            statement.setString(2,"%"+letter+"%");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        String[] typesArray = new String[types.size()];
        types.toArray(typesArray);
        for (String type : typesArray) {
            System.out.println(type);
        }
        return typesArray;
    }
    // функция выводет весь список type
    public String[] get_all_types()  throws SQLException {
        List<String> types = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:/SQL/new/My_cats_1.db");
            String query = "SELECT type FROM types ";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        String[] typesArray = new String[types.size()];
        types.toArray(typesArray);
        for (String type : typesArray) {
            System.out.println(type);
        }
        return typesArray;
    }
}

