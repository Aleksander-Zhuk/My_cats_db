import java.sql.SQLException;
import java.util.Scanner;

public class CreateDatabase {
    public static void main(String[] args) {

        Scanner str = new Scanner(System.in);
        My_cats my_cats = new My_cats();

        while (true){
            System.out.println("Если вам надо добавить сторку (Y/N)");
            String answer = str.nextLine();
            if(answer.equals("N")){
                break;
            }
            else  if (answer.equals("Y")){
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
            }
            else {
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
            }
            else if (answer2.equals("Y")) {
                System.out.println("Введите id изменяемой строки:");
                int id = str.nextInt();
                str.nextLine();
                System.out.println("Введите type:");
                String new_type = str.nextLine();
                my_cats.update_type(id, new_type);
            }
        }
        while (true) {
            System.out.println("Если вам надо добавить список из .txt файла (Y/N)");
            String answer2 = str.nextLine();
            if (answer2.equals("N")) {
                break;
            }
            else if (answer2.equals("Y")) {
                System.out.println("Введите адрес .txt файла :");
                String file_txt  = "\"" + str.nextLine() + "\"";
                my_cats.add_all_types(file_txt);
            }
        }
        while (true) {
            System.out.println("Если вы хотите получить type по id (Y/N)");
            String answer2 = str.nextLine();
            if (answer2.equals("N")) {
                break;
            }
            else if (answer2.equals("Y")) {
                System.out.println("Введите id:");
                int id = str.nextInt();
                str.nextLine();
                try {
                    my_cats.get_type(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        while (true) {
            System.out.println("Если вы хотите получить определённый type (Y/N)");
            String answer2 = str.nextLine();
            if (answer2.equals("N")) {
                break;
            }
            else if (answer2.equals("Y")) {
                System.out.println("Введите сначало max id:");
                int id = str.nextInt();
                str.nextLine();
                System.out.println("Введите первую букву type:");
                String a = str.nextLine().toUpperCase();
                char symbol = a.charAt(0);
                String where = id + " " + symbol;
                try {
                    my_cats.get_type_where(where);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        while (true) {
            System.out.println("Если вы хотите получить все type (Y/N)");
            String answer2 = str.nextLine();
            if (answer2.equals("N")) {
                break;
            }
            else if (answer2.equals("Y")) {
                try {
                    my_cats.get_all_types();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}



