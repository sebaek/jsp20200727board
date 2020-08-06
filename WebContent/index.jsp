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
<link rel="stylesheet" href="${ctxPath }/res/css/mystyle.css" />
<title>Insert title here</title>
</head>
<body>

${list[0] }
<br />
${list[1] }

	<u:navbar home="active" />

	<div class="container mt-3">
		<div class="jumbotron">
			<h1 class="display-4">중앙 게시판</h1>
			<p class="lead">회원과 게시물을 등록 수정 삭제할 수 있는 프로젝트</p>
			<hr class="my-4">
			<p>회원 가입부터 시작해보세요.</p>
			<a class="btn btn-primary btn-lg" href="join.do" role="button">회원 가입</a>
		</div>
	</div>

</body>
</html>









