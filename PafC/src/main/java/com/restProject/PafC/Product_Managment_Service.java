package com.restProject.PafC;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.Fund_Management;
import com.model.Product_Managment;

@Path("Product_Management_Service")
public class Product_Managment_Service {

	Product_Managment Obj1 = new Product_Managment();
	//Get METHOD
	  @GET
	  @Path("get")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getIt() {
		 return "Got it Samithra!";
		       
	 }
	  
	//Get method for Products
	  @GET
		@Path("getProducts")
		@Produces(MediaType.TEXT_HTML)
		public String readProducts()
		{
			return Obj1.readProducts();
		}
	  
	//POST METHOD FOR insert Products
	  @POST 
		@Path("AddProduct") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN)
		public String addProduct( @FormParam("productId") String productId,
				                @FormParam("productName") String productName,
								@FormParam("AstimatedValue") String AstimatedValue,
								@FormParam("ProductDescription") String ProductDescription)
								
		{
			String output = Obj1.addProduct(productId,productName, AstimatedValue, ProductDescription);  
			return output; 
		}
	  
	//PUT METHOD
	  @PUT 
		@Path("updateProducts") @Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN)
	  public String updateProducts(String product) {
		  
		  //Convert the input string to a JSON object 
		  JsonObject itemObject = new JsonParser().parse(product).getAsJsonObject(); 
		  
		  String productId = itemObject.get("productId").getAsString(); 
		  String productName = itemObject.get("productName").getAsString(); 
		  String AstimatedValue = itemObject.get("AstimatedValue").getAsString(); 
		  String ProductDescription = itemObject.get("ProductDescription").getAsString();
		  
		  String output = Obj1.updateProducts(productId, productName, AstimatedValue, ProductDescription);
		  return output;
	  }
	  
	//DELETE METHOD
	    @DELETE 
		@Path("DeleteProducts") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
	    
	    public String deleteProduct(String product) {
	    	Document doc = Jsoup.parse(product, "", Parser.xmlParser());
	    	String productId = doc.select("productId").text();
	    	 String output = Obj1.deleteProduct(productId);
	    	 
	    	 return output; 
	    }
}
