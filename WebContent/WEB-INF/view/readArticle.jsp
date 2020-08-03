<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<table border="1" width="100%">
<tr>
	<td>번호</td>
	<td>${articleData.article.number }</td>
</tr>
<tr>
	<td>작성자</td>
	<td>${articleData.article.writer.name }</td>
</tr>
<tr>
	<td>제목</td>
	<td><c:out value="${articleData.article.title }"></c:out></td>
</tr>
<tr>
	<td>내용</td>
	<td style="white-space: pre-wrap;">
	<c:if test="${not empty articleData.fileName }">
		<img src="/images/${articleData.article.number }/${articleData.fileName }" alt="" />
	</c:if>
	
	<c:out value="${articleData.content }" />
	</td>
	<%-- 
	<td><u:pre value="${articleData.content }"/></td>
	 --%>
</tr>
<tr>
	<td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }"></c:set>
		<a href="list.do?pageNo=${pageNo }">[목록]</a>
		<c:if test="${authUser.id == articleData.article.writer.id }">
			<a href="modify.do?no=${articleData.article.number }">[게시글 수정]</a>
			<a href="delete.do?no=${articleData.article.number }">[게시글 삭제]</a>
		
		</c:if>
	
	</td>
</tr>
</table>
</body>
</html>












