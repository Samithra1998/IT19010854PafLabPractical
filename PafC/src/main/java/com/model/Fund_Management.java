package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Fund_Management {
	
	
	//getting mysql connection
	private Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password

			con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/fund_management",
					"root", "aruni077/");
			System.out.println("Successfully Connected");

		} catch (Exception e) {

			e.printStackTrace();
			
		}
		return con;
	}

	//insert fund application details
	public String insertApplication(int id,String full_name, String email, String phone, String research_category,String purpose) {

		String output = ""; 
		
		try {
			Connection con = connect();

			if (con == null)

			{
				return "Error while connecting to the database for inserting applicationt details.";
			}
			// create a prepared statement
			String query = " insert into fund_application (Application_ID,Full_Name,Email,Phone,Research_category,purpose)" + " values (?,?,?,?,?,?)"; 
			
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values to appointment table
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, full_name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, research_category);
			preparedStmt.setString(6, purpose);
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully"; 

			
			} 
			catch (Exception e) {
			output = "Error while inserting the application details.";
			System.err.println(e.getMessage());
			}
			return output;

	}
	//insert new fund into the System
	public String insertFund(String id,String Title, String Relesed_Date, String Expire_Date, String Type_of_Reserch,String Anoucement_type,String Purpose_of_funding,String Applicant_Instruction) {

		String output = ""; 
		
		try {
			Connection con1 = connect();

			if (con1 == null)

			{
				return "Error while connecting to the database for inserting new fund detail details.";
			}
			// create a prepared statement
			String query = " insert into funds (idFunds,Title,Relesed_Date,Expire_Date,Type_of_Reserch,Anoucement_type,Purpose_of_funding,Applicant_Instruction)" + " values (?,?,?,?,?,?,?,?)"; 
			//String query = " insert into funds (idFunds,Title,Relesed_Date)" + " values (?,?,?)"; 
			
			java.sql.PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			// binding values to appointment table
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, Title);
			preparedStmt.setString(3, Relesed_Date);
			preparedStmt.setString(4, Expire_Date);
			preparedStmt.setString(5, Type_of_Reserch);
			preparedStmt.setString(6, Anoucement_type);
			preparedStmt.setString(7, Purpose_of_funding);
			preparedStmt.setString(8, Applicant_Instruction);
			preparedStmt.execute();
			con1.close();
			output = "Inserted successfully"; 

			
			} 
			catch (Exception e) {
			output = "Error while inserting the new fund details.";
			System.err.println(e.getMessage());
			}
			return output;

	}
	//read fund from the system
	public String readFunds() {
		String output = "";
		try {

			Connection con = connect();

			if (con == null) {

				return "Error while connecting to the database for reading the Avilable Funds.";

			}
			output = "<table border=\"1\"><tr>" + "<th>Fund ID</th>" + "<th>Title</th>" + "<th>Relesed Date</th>"
					+ "<th>Expire Date</th>" + "<th>Type of Reserch</th>" + "<th>Anoucement Type</th>" + "<th>Purpose of funding</th>"+"<th>Applicant Instruction</th></tr>";

			String query = "select * from funds";

			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				
				String id = rs.getString("idFunds");
				System.out.println(id);
				String Title = rs.getString("Title");
			    String Relesed_Date = rs.getString("Relesed_Date");
			    String Expire_Date = rs.getString("Expire_Date");
			    String Type_of_Reserch = rs.getString("Type_of_Reserch");
			    String Anoucement_type = rs.getString("Anoucement_type");
			    String Purpose_of_funding = rs.getString("Purpose_of_funding");
			    String Applicant_Instruction = rs.getString("Applicant_Instruction");

				/*String payID = Integer.toString(rs.getInt("payID"));
				String patientID = rs.getString("patientID");
				String doctorID = rs.getString("doctorID");
				String date = rs.getString("date");
				String amount = Double.toString(rs.getDouble("amount"));
				String cardnumber = Integer.toString(rs.getInt("cardnumber"));
				String postlnumber = Integer.toString(rs.getInt("postalnumber"));*/

				output += "<td>" + id  + "</td>";
				output += "<td>" + Title + "</td>";
				output += "<td>" + Relesed_Date + "</td>";
				output += "<td>" + Expire_Date + "</td>";
				output += "<td>" + Type_of_Reserch + "</td>";
				output += "<td>" + Anoucement_type + "</td>";
				output += "<td>" + Purpose_of_funding + "</td>";
				output += "<td>" + Applicant_Instruction + "</td></tr>";

			}

			con.close();

			output += "</table>";

		} catch (Exception e) {

			output = "An error occurred while reading the Fund details. ";
			System.err.println(e.getMessage());

		}

		return output;

	}
	//update existing fund
	public String updateFunds(String id,String Title, String Relesed_Date, String Expire_Date, String Type_of_Reserch,String Anoucement_type,String Purpose_of_funding,String Applicant_Instruction)
	{
		String output = "";

		try {

			Connection con = connect();

			if (con == null) {

				return "Error occured while updating the Fund details.";
			}

			// create a prepared statement
			
			String query = "UPDATE funds SET Title=?,Relesed_Date=?,Expire_Date=?,Type_of_Reserch=?,Anoucement_type=?,Purpose_of_funding=?,Applicant_Instruction=?  WHERE idFunds=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values to query
			
			preparedStmt.setString(1, Title);
			preparedStmt.setString(2, Relesed_Date);
			preparedStmt.setString(3, Expire_Date);
			preparedStmt.setString(4, Type_of_Reserch);
			preparedStmt.setString(5, Anoucement_type);
			preparedStmt.setString(6, Purpose_of_funding);
			preparedStmt.setString(7, Applicant_Instruction);
			preparedStmt.setString(8, id);

			// Executeing the statement
			 preparedStmt.execute(); 
			 con.close();

			output = "Fund details update successfully.";
		
		}

		catch (Exception e) {

			output = "An error occurred while updating the fund details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//delete specific fund
    public String deleteFunds(String idFunds) {
		
		String output = "";

		try {

			Connection con = connect();

			if (con == null) {

				return "Error occured while deleting the Funds.";
			}

			// create a prepared statement
			String query = "delete from funds where idFunds=?";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, idFunds);

			// executing the statements
			preparedStmt.execute();

			con.close();

			output = "Fund Details deleted successfully.";
			
		} catch (Exception e) {

			output = " An error occurred while deleting the fund details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String insertAppointments(int id,String pID, String dID, String appDate, String appTime) {

		String output = ""; 
		
		try {
			Connection con = connect();

			if (con == null)

			{
				return "Error while connecting to the database for inserting appointment details.";
			}
			// create a prepared statement
			String query = " insert into contact (id,fname,lname,email,comment)" + " values (?,?,?,?,?)"; 
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values to appointment table
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, pID);
			preparedStmt.setString(3, dID);
			preparedStmt.setString(4, appDate);
			preparedStmt.setString(5, appTime);
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully"; 

			
			} 
			catch (Exception e) {
			output = "Error while inserting the appointment details.";
			System.err.println(e.getMessage());
			}
			return output;

	}

}
