package student.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class ManagementSystem {
	static Connection con = null;

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("WELCOME TO MY OWN SOFTWARE");
		while (true) {
			System.out.println("press 1 for insert student details");
			System.out.println("press 2 for update student details");
			System.out.println("press 3 for delete student details");
			System.out.println("press 4 for show student details");
			System.out.println("press 5 for search student records");

			Scanner sc = new Scanner(System.in);
			System.out.println("enter your choice");
			int ch = sc.nextShort();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "");

				int choice = ch;
				switch (choice) {
				case 1:

					System.out.println("please insert student details");

					System.out.println("enter id");
					int id = sc.nextInt();
					System.out.println("enter name");
					String name = sc.next();
					System.out.println("enter department");
					String dpt = sc.next();
					PreparedStatement ps = con.prepareStatement("insert into managemnt values(?,?,?)");
					ps.setInt(1, id);
					ps.setString(2, name);
					ps.setString(3, dpt);
					int result = ps.executeUpdate();
					System.out.println("data inserted successfully");
					break;
				case 2:

					System.out.println("update student details");

					System.out.println("enter id");
					int inputid = sc.nextInt();
					System.out.println("enter department that you want to update");
					String newdpt = sc.next();
					String sql = "update managemnt set dept=? where id=?;";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, newdpt);
					stmt.setInt(2, inputid);

					int i = stmt.executeUpdate();
					if (i > 0) {
						System.out.println("record updated successfully");
					} else {
						System.out.println("no such type of record found");
					}

					break;
				case 3:

					System.out.println("delete student details");
					System.out.println("enter your id for delete the reord");
					int dId = sc.nextInt();
					String dql = "delete from managemnt where id=?; ";
					PreparedStatement ps1 = con.prepareStatement(dql);
					ps1.setInt(1, dId);
					int i1 = ps1.executeUpdate();

					if (i1 > 0) {
						System.out.println("record deleted successfully");
					} else {
						System.out.println("no such type of record found");
					}

					break;
				case 4:

					System.out.println("please see your all data");

					String s = "select * from managemnt;";
					PreparedStatement stmt2 = con.prepareStatement(s);
					ResultSet rs = stmt2.executeQuery();
					while (rs.next()) {
						System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
					}
					break;
				case 5:
					System.out.println("type to serch record");
					System.out.println("enter name for searching records ");
					String sname = sc.next();
					String search = "select * from managemnt where name=?;";
					PreparedStatement stmt3 = con.prepareStatement(search);
					stmt3.setString(1, sname);
					ResultSet rs3 = stmt3.executeQuery();
					if (rs3.next() == false) {
						System.out.println("there is no such records");
					} else {
						rs3.previous();
						while (rs3.next()) {
							System.out.println("id=" + rs3.getInt(1) + " " + "name=" + rs3.getString(2) + " "
									+ "department=" + rs3.getString(3));
						}
					}

					break;

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				con.close();
				System.out.println("connection closed");
				System.out.println("thanks for using our software");
			}
		}

	}

}
