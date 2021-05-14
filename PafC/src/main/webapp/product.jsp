<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "com.model.Product_Managment" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function()
		{ 
		if ($("#alertSuccess").text().trim() == "") 
		 { 
		 $("#alertSuccess").hide(); 
		 } 
		 $("#alertError").hide(); 
		}); 
		
	//SAVE ============================================
	$(document).on("click", "#btnSave", function(event) 
		{ 
		// Clear alerts---------------------
		 $("#alertSuccess").text(""); 
		 $("#alertSuccess").hide(); 
		 $("#alertError").text(""); 
		 $("#alertError").hide(); 
		 
		// Form validation-------------------
		var status = validateItemForm(); 
		
		if (status != true) 
		 { 
			 $("#alertError").text(status); 
			 $("#alertError").show(); 
		 	 return; 
		 } 
		
		// If valid------------------------
		var type = ($("#hidProductIDSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
			 url : "ProductAPI", 
			 type : type, 
			 data : $("#formProduct").serialize(), 
			 dataType : "text", 
			 complete : function(response, status) 
		 { 
		 	onItemSaveComplete(response.responseText, status); 
		 } 
	}); 
});

	function onItemSaveComplete(response, status)
	{ 
		if (status == "success") 
		 { 
		 var resultSet = JSON.parse(response); 
		 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divItemsGrid").html(resultSet.data); 
			 
		 } 
		 else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 } 
		else if (status == "error") 
		 { 
			 $("#alertError").text("Error while saving."); 
			 $("#alertError").show(); 
		 } 
		else
		 { 
			 $("#alertError").text("Unknown error while saving.."); 
			 $("#alertError").show(); 
		}
		
			$("#hidProductIDSave").val(""); 
			$("#formProduct")[0].reset(); 
	}
		
	
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event) 
	{ 
		 $("#hidProductIDSave").val($(this).closest("tr").find('#hidProductIDUpdate').text()); 
		 $("#productId").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#productName").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#AstimatedValue").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#ProductDescription").val($(this).closest("tr").find('td:eq(3)').text()); 
	});
	
//REMOVE=====================================================================
	$(document).on("click", ".btnRemove", function(event)
{ 
	$.ajax( 
		{ 
		 url : "ProductAPI", 
		 type : "DELETE", 
		 data : "productId=" + $(this).data("productId"),
		 dataType : "text", 
		 complete : function(response, status) 
		{ 
		 onItemDeleteComplete(response.responseText, status); 
		} 
	}); 
});

	function onItemDeleteComplete(response, status)
	{ 
		if (status == "success") 
		 { 
		 var resultSet = JSON.parse(response); 
		 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divItemsGrid").html(resultSet.data); 
			 
		 } 
		 else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 } 
		else if (status == "error") 
		 { 
			 $("#alertError").text("Error while deleting."); 
			 $("#alertError").show(); 
		 } 
		else
		 { 
			 $("#alertError").text("Unknown error while deleting.."); 
			 $("#alertError").show(); 
		}
		
			
	}
	
	
	
// CLIENT-MODEL================================================================
	function validateItemForm() 
	{ 
		// CODE
		if ($("#productId").val().trim() == "") 
		 { 
		 	return "Insert Product Id."; 
		 } 
		
		// NAME
		if ($("#productName").val().trim() == "") 
		 { 
		 	return "Insert Product Name."; 
		 } 
		
		// PRICE-------------------------------
		if ($("#AstimatedValue").val().trim() == "") 
		 { 
		 	return "Insert Item Price."; 
		 } 
		
		// is numerical value
		var tmpPrice = $("#AstimatedValue").val().trim(); 
		if (!$.isNumeric(tmpPrice)) 
		 { 
		 	return "Insert a numerical value for Astimated Value."; 
		 } 
		
		// convert to decimal price
		 $("#AstimatedValue").val(parseFloat(tmpPrice).toFixed(2)); 
		
		// DESCRIPTION------------------------
		if ($("#ProductDescription").val().trim() == "") 
		 { 
		 	return "Insert Product Description."; 
		 } 
		
		return true; 
}	

</script>

</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-5">
		
		<h1 class="m-3">Product details</h1>
		
		<form id="formProduct" name="formProduct" method="post" action="product.jsp">
		 Product ID: 
		<input id="productId" name="productId" type="text" 
		 class="form-control form-control-sm">
		<br> Product Name: 
		<input id="productName" name="productName" type="text" 
		 class="form-control form-control-sm">
		<br> Astimated Value: 
		<input id="AstimatedValue" name="AstimatedValue" type="text" 
		 class="form-control form-control-sm">
		<br> Product Description: 
		<input id="ProductDescription" name="ProductDescription" type="text" 
		 class="form-control form-control-sm">
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" 
		 class="btn btn-primary"><br>
		<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value="">
		</form>
		
		<div id="alertSussess" class="alert alert-success">
			<%
				out.print(session.getAttribute("statusMsg"));
			%>
		</div>
		<div id="alertError" class="alert alert-danger"></div>
		
		<br>
		<% 
		Product_Managment obj = new Product_Managment();
		out.print(obj.readProducts());
		
    	%>
		
		</div>
	</div>
</div>
</body>
</html>