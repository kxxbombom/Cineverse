<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.reportForm.css" type="text/css">


<div class="board-report-form">
	<h3>신고하기</h3>
	<hr size="1" width="100%;">
	<div class="flexbox-p info-con">
		<div class="report-info"><label>작성자</label><span>${param.writer}</span></div>
		<div class="report-info"><label>내용</label><span><c:out value="${param.cb_title}" escapeXml="true" /></span></div>
	</div>
	<hr size="1" width="100%;">
	<form:form action="submitBoardReport" method="post" id="reportForm" modelAttribute="boardReportVO">
		<form:input path="mem_num" type="hidden" name="mem_num" value="${param.user_num}"/>
		<form:input path="cb_num" type="hidden" name="cb_num" value="${param.cb_num}"/>
		<div class="category-con">
		<form:label path="br_category" class="category-label">신고사유</form:label>
		<div class="flexbox-p radio-con">
			<form:errors path="br_category" cssClass="error-color"/>
	        <span><form:radiobutton path="br_category" value="1"/>스팸홍보/도배</span>
	        <span><form:radiobutton path="br_category" value="2"/>음란물</span>
	        <span><form:radiobutton path="br_category" value="3"/>불법정보</span>
	        <span><form:radiobutton path="br_category" value="4"/>청소년에게 유해한 내용</span>
	        <span><form:radiobutton path="br_category" value="5"/>욕설/생명경시/혐오/차별적 표현</span>
	        <span><form:radiobutton path="br_category" value="6"/>개인정보 노출</span>
	        <span><form:radiobutton path="br_category" value="7"/>불쾌한 표현</span>
	        <span><form:radiobutton path="br_category" value="8"/>기타</span>
        </div>
        
        <form:label path="br_content" class="br-content">신고내용(선택)</form:label>
        <form:textarea path="br_content" name="br_content"></form:textarea><br>
		</div>
        <form:button class="report-submit">신고하기</form:button>
    
    </form:form>
    <script type="text/javascript">
    $(document).ready(function(){
    	$('#reportForm').submit(function(){
    		var selectedValue = document.querySelector('input[name="br_category"]:checked');
		    if (!selectedValue) {
		        alert('신고사유를 선택해주세요.');
		        return false;
		    }
    	});
    	
    	
    });
    
    </script>
</div>