<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<u:navbar list="active" />

	<div class="container">

		<h1>
			<c:out value="${articleData.article.title }" />
		</h1>
		<p class="h5">
			<small>by ${articleData.article.writer.name }</small>
		</p>

		<p style="white-space: pre-wrap;"><c:if test="${not empty articleData.fileName }"><img
					src="/images/${articleData.article.number }/${articleData.fileName }"
					alt="" />
			</c:if><c:out value="${articleData.content }" />
		</p>
		
		<c:if
						test="${authUser.id == articleData.article.writer.id }">
						<a class="btn btn-secondary" href="modify.do?no=${articleData.article.number }">수정</a>
						<a class="btn btn-danger" href="delete.do?no=${articleData.article.number }">삭제</a>

					</c:if>
	</div>
</body>
</html>












