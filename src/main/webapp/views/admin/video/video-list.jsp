<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/taglib.jsp"%>

<%
    // Lấy giá trị id từ URL
    String categoryId = request.getParameter("id");
    // Đặt categoryId vào request scope
    request.setAttribute("categoryId", categoryId);
%>

<a href="<c:url value='/admin/video/add?id=${categoryId}'/>">Add video</a> 
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Active</th>
        <th>VideoID</th>
        <th>Description</th>
        <th>Poster</th>
        <th>Title</th>
        <th>Views</th>
        <th>CategoryID</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${listvideo}" var="video" varStatus="STT">
    <tr>
        <td>${STT.index+1}</td>
        <td>
            <c:if test="${video.active== 1}">
                <span>Hoạt động</span>
            </c:if>


            <c:if test="${video.active!= 1}">
                <span>Khóa</span>
            </c:if>

        </td>
        <td>${video.videoId}</td>
        <td>${video.description}</td>
        <td><c:if test="${video.poster.substring(0,5) != 'https'}">
            <c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
        </c:if>
            <c:if test="${video.poster.substring(0,5) == 'https'}">
                <c:url value="${video.poster}" var="imgUrl"></c:url>
            </c:if>
            <img height="150" width="200" src="${imgUrl}" /></td>
        <td>${video.title}</td>
        <td>${video.views}</td>
        <td>${video.category.categoryid}</td>
	
		<td><a
				href="<c:url value='/admin/video/edit?videoid=${video.videoId}'/>">Sửa</a>
				<a
				href="<c:url value='/admin/video/delete?videoid=${video.videoId}&categoryid=${video.category.categoryid }'/>">Xóa</a>
				</td>
    </tr>
    </c:forEach>
</table>