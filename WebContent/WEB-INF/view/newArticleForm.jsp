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
	<u:navbar write="active" />

	<div class="container">

		<form action="write.do" method="post" enctype="multipart/form-data">

			<div class="form-group">
				<label for="input1">제목</label> <input type="text"
					class="form-control" name="title" id="input1"
					placeholder="제목을 입력하세요." value="${param.title }" required>
				<small class="form-text text-muted"> <c:if
						test="${errors.title }">제목을 입력하세요.</c:if>
				</small>
			</div>
			<div class="form-group">
				<label for="textarea1">내용</label>
				<textarea class="form-control" name="content" id="textarea1"
					rows="3">${param.content }</textarea>
			</div>
			<div class="form-group">
				<label for="file1">파일</label> <input type="file" name="file1"
					accept="image/*" class="form-control-file" id="file1">
			</div>

			<input class="btn btn-primary" type="submit" value="등록" />
		</form>

	</div>
</body>
</html>









