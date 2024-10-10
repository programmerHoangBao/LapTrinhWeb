<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "/taglib.jsp" %>
<form action = "<c:url value = "/admin/category/insert"/>" method ="post" enctype ="multipart/form-data" >
	<label for="catagoryname">Category name:</label><br> 
	<input type="text" id="catagoryname" name="categoryname"><br>
	<label for="images">Images:</label><br> 
	<input type="file" id="images" name="images"><br>
	<label for="status">Status:</label><br> 
	<input type="text" id="status" name="status">
	
	<hr>
	<input type="submit" value = "Insert">
</form>