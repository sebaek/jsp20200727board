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
		<table class="table">
			<thead>
				<tr>
					<th style="width: 10%;">번호</th>
					<th style="width: 70%;">제목</th>
					<th style="width: 10%;">작성자</th>
					<th style="width: 10%;">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${articlePage.hasNoArticles() }">
					<tr>
						<td colspan="4">게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="article" items="${articlePage.content }">
					<tr>
						<td>${article.number }</td>
						<td><a
							href="read.do?no=${article.number }&pageNo=${articlePage.currentPage}">
								<c:out value="${article.title }"></c:out>
						</a></td>
						<td>${article.writer.name }</td>
						<td>${article.readCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="container mt-3">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:if test="${articlePage.startPage > 5 }">
					<li class="page-item disabled"><a class="page-link"
						href="${ctxPath }/article/list.do?pageNo=${articlPage.startPage-5 }"
						tabindex="-1" aria-disabled="true">이전</a></li>
				</c:if>
				<c:forEach var="pNo" begin="${articlePage.startPage }"
					end="${articlePage.endPage }">
					<li class="page-item"><a class="page-link" href="${ctxPath }/article/list.do?pageNo=${pNo }">${pNo }</a></li>
				</c:forEach>
				<c:if test="${articlePage.endPage < articlePae.totalPages }">
					<li class="page-item"><a class="page-link"
						href="${ctxPath }/article/list.do?pageNo=${articlePage.startPage + 5 }">다음</a></li>
				</c:if>
			</ul>
		</nav>


	</div>
</body>
</html>







