<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var currentUrl = window.location.href;
        
        if (currentUrl.includes("?type=1")) {
            document.getElementById("link-type1").classList.add("active");
        } else if (currentUrl.includes("?type=2")) {
            document.getElementById("link-type2").classList.add("active");
        } else {
            document.getElementById("link-all").classList.add("active");
        }
    });
</script>

<div class="page-main">
	<h2>커뮤니티</h2>
	<div class="board-main">
		<c:if test="${!empty user}">
			<input type="button" class="board-btn" value="글쓰기" onclick="location.href='write'">
		</c:if>
	
		<table class="board-table">
			<thead>
				<tr>
					<td colspan="2">
						<div class="types">
							<a href="list" id="link-all">전체</a>
							<a href="list?type=1" id="link-type1">영화톡톡</a>
							<a href="list?type=2" id="link-type2">양도/교환</a>
						</div>
					</td>
					<td colspan="2" style="text-align: right;">
						<div class="search">
							<form action="list" id="search_form" method="get" style="margin-left: auto;">
								<input type="hidden" name="type" value="${param.type}">
								<input type="search" class="search-input" name="keyword" placeholder="제목,내용,작성자 검색">
								<button type="submit" class="search-button" aria-label="검색"></button>
								<%--<input type="image" src="${pageContext.request.contextPath}/images/kbm/search.png" width="20" alt="검색"> --%>
		                   </form>
		               </div>
		           </td>
		       </tr>
			</thead>
			<tbody>
			<c:if test="${count == 0}">
				<tr>
 					<td colspan="4">
						<div style="height:250px; display:flex; align-items:center; justify-content: center;">표시할 게시물이 없습니다.</div>
					</td>
				</tr>
			</c:if>
			
			<c:if test="${count > 0}">
				<c:forEach var="board" items="${list}">
				<tr>
					<td colspan="4">
						<div class="content">${board.cb_title}</div>
						<div class="likes">${board.cb_likes}</div>
						<div class="writer">${board.mem_id} ${board.cb_reg_date}</div>
					</td>
				</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>
</div>