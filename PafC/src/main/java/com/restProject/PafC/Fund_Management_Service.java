package com.restProject.PafC;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;
import com.model.Fund_Management;

@Path("Fund_Management_Service")
public class Fund_Management_Service {

	Fund_Management Obj = new Fund_Management();
	//Get METHOD
  @GET
  @Path("get")
  @Produces(MediaType.TEXT_PLAIN)
  public String getIt() {
	 return "Got it aruni!";
	       
 }
  //Get method for fund
  @GET
	@Path("getFunds")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	{
		return Obj.readFunds();
	}
  
//POST METHOD FOR FUND APPLICATION
  @POST 
	@Path("Application") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertApplication( @FormParam("ID") int ID,
			                @FormParam("patientID") String full_name,
							@FormParam("doctorID") String email,
							@FormParam("appointmentDate") String phone,
							@FormParam("appointmentDate") String research_category,
							@FormParam("appointmentTime") String purpose)
							
	{
		String output = Obj.insertApplication(ID,full_name, email, phone, research_category,purpose);  
		return output; 
	}
 
//POST METHOD FOR ADDING NEW FUND
  @POST 
	@Path("NewFund") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund( @FormParam("ID") String ID,
			                @FormParam("Title") String Title,
							@FormParam("Relesed_Date") String Relesed_Date,
							@FormParam("Expire_Date") String Expire_Date,
							@FormParam("Type_of_Reserch") String Type_of_Reserch,
							@FormParam("Anoucement_type") String Anoucement_type,
							@FormParam("Purpose_of_funding") String Purpose_of_funding,
							@FormParam("Applicant_Instruction") String Applicant_Instruction)
							
	{
		String output = Obj.insertFund(ID,Title, Relesed_Date, Expire_Date, Type_of_Reserch,Anoucement_type,Purpose_of_funding,Applicant_Instruction);  
		return output; 
	}
  
//PUT METHOD
    @PUT 
	@Path("UpdateFunds") @Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFunds(String fund) {  
		JsonObject Object = new JsonParser().parse(fund).getAsJsonObject(); 
		 
		String idi = Object.get("idFunds").getAsString();  
		String Title = Object.get("Title").getAsString();  
		String Relesed_Date = Object.get("Relesed_Date").getAsString();  
		String Expire_Date = Object.get("Expire_Date").getAsString();
		String Type_of_Reserch = Object.get("Type_of_Reserch").getAsString();
		String Anoucement_type = Object.get("Anoucement_type").getAsString();
		String Purpose_of_funding = Object.get("Purpose_of_funding").getAsString();
		String Applicant_Instruction = Object.get("Applicant_Instruction").getAsString();
		
		
		 
		String output = Obj.updateFunds(idi,Title, Relesed_Date, Expire_Date, Type_of_Reserch,Anoucement_type,Purpose_of_funding,Applicant_Instruction); 
		 
		 return output; 
		 
	}
  
//DELETE METHOD
    @DELETE 
	@Path("DeleteFunds") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFunds(String fund) {  
		
		Document doc = Jsoup.parse(fund, "", Parser.xmlParser());  
		String idFunds = doc.select("idFunds").text(); 
		 
		 String output = Obj.deleteFunds(idFunds);
		 
		 return output; 
   }
    
    
    
  
}
