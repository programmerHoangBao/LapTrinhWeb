<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "/taglib.jsp" %>

<%
    // Lấy giá trị id từ URL
    String categoryId = request.getParameter("id");
    // Đặt categoryId vào request scope
    request.setAttribute("categoryId", categoryId);
%>

<form action="${pageContext.request.contextPath }/admin/video/insert" method="post" enctype="multipart/form-data">
	<input type="text" id="categoryid" name="categoryid" value = "${categoryId}" hidden ="hidden"><br>
	<label for="videoid">Video ID:</label><br>
	<input type="text" id = "videoid" name = "videoid"><br>
	<label for="title">Video title:</label><br> 	
	<input type="text" id="title" name="title"><br> 
	<label for="description">Description:</label><br> 
	<input type="text" id="description" name="description"><br> 
	
	<label for="images">Poster: </label><br>
	<div style="width:100px; height:100px">
	<img alt="images" id="poster" src="" width="120px" height="120px" /> 
	</div><br>
	<br>
<!-- 	Nhập link: <input type="text" id="poster1" name="poster1"><br>
	Hoặc tải file:
	<input type="file" onchange="chooseFile(this)" id="poster" name="poster"><br> -->
	
	<input type="file" onchange="chooseFile(this)" id="poster" name="poster"><br>
	
	<label for="status">Active: </label>
	<input id="statuson" type="radio" name="status" value="1" ${video.active==1?'checked': ''}>
	<label for="statuson">Hoạt động</label>
	<input id="statusoff" type="radio" name="status" value="0" ${video.active==0?'checked': ''}>
	<label for="statusoff">Khóa</label>
	
	<br>
	<label for="views">Số lượt xem:</label><br> 
	<input type="text" id="views" name="views"><br> 

	<br> <input type="submit" value="Submit">
</form>