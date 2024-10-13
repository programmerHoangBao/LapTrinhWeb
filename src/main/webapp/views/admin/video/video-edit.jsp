<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "/taglib.jsp" %>

<form action="${pageContext.request.contextPath }/admin/video/update" method="post" enctype="multipart/form-data">
	<input type="text" id="categoryid" name="categoryid" value = "${video.category.categoryid}" hidden ="hidden"> 
	<%-- <input type="text" id="videoid2" name="videoid2" value = "${video.videoId}" hidden ="hidden"> --%>
	
	<label for="videoid">Video ID:</label><br>
	<input type="text" id = "videoid" name = "videoid" value = "${video.videoId}"><br>
	<label for="title">Video title:</label><br> 	
	<input type="text" id="title" name="title" value = "${video.title}"><br> 
	<label for="description">Description:</label><br> 
	<input type="text" id="description" name="description" value = "${video.description}"><br> 
	
	<c:if test="${video.poster.substring(0,5) != 'https'}">
            <c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
        </c:if>
            <c:if test="${video.poster.substring(0,5) == 'https'}">
                <c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
            <img height="150" width="200" src="${imgUrl}" /><br>
				</c:if>
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
	<input type="text" id="views" name="views" value = "${video.views}"><br> 

	<br> <input type="submit" value="Submit">
</form>