import java.sql.*;
import java.util.Scanner;

public class DatabaseExample {
    public static String jdbcURL = "jdbc:mysql://localhost:3307/learnjava?useSSL=false";
    public static String jdbcUsername = "root";
    public static String jdbcPassword = "";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            // Scanner sc =
            Statement statement = connection.createStatement();

            Scanner scanner = new Scanner(System.in);

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (name, age, school, code) VALUES (?, ?, ?, ?)");

            System.out.print("Enter name: ");
            String nameInput = scanner.nextLine();

            System.out.print("Enter age: ");
            int ageInput = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Enter school: ");
            String schoolInput = scanner.nextLine();

            System.out.print("Enter code: ");
            String codeInput = scanner.nextLine();

            preparedStatement.setString(1, nameInput);
            preparedStatement.setInt(2, ageInput);
            preparedStatement.setString(3, schoolInput);
            preparedStatement.setString(4, codeInput);

            preparedStatement.executeUpdate();
            System.out.println("Record inserted successfully.");

            String selectQuery = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            System.out.println("Records in the database:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String school = resultSet.getString("school");
                String code = resultSet.getString("code");

                System.out.println(
                        "ID: " + id + ", name: " + name + ", Age: " + age + ", School: " + school + ", Code: " + code);
            }

            resultSet.close();
            statement.close();
            connection.close();
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}