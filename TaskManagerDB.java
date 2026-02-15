import java.sql.*;
import java.util.Scanner;

public class TaskManagerDB {

    static final String URL = "jdbc:mysql://localhost:3306/task_manager";
    static final String USER = "root";       // your username
    static final String PASS = "Poojitha@20506";   // your password

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- TASK MANAGER ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> completeTask();
                case 4 -> deleteTask();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    static void addTask() {
        try (Connection con = getConnection()) {
            System.out.print("Enter task title: ");
            String title = sc.nextLine();

            PreparedStatement ps =
                con.prepareStatement("INSERT INTO tasks (title, status) VALUES (?, 'Pending')");
            ps.setString(1, title);
            ps.executeUpdate();

            System.out.println("Task added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewTasks() {
        try (Connection con = getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tasks");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + ". " +
                    rs.getString("title") + " [" +
                    rs.getString("status") + "]"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void completeTask() {
        try (Connection con = getConnection()) {
            System.out.print("Enter task ID: ");
            int id = sc.nextInt();

            PreparedStatement ps =
                con.prepareStatement("UPDATE tasks SET status='Completed' WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Task marked completed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteTask() {
        try (Connection con = getConnection()) {
            System.out.print("Enter task ID: ");
            int id = sc.nextInt();

            PreparedStatement ps =
                con.prepareStatement("DELETE FROM tasks WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Task deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}