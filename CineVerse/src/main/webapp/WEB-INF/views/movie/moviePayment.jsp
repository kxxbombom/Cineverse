<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="movie_payments">
 <div class="movies_info">
 	<div class="all_titles"><p>예매 정보</p></div>
 	<div class="movies_poster">
 	<c:forEach var="movieInfo" items="${movieInfoList}">
       <img alt="영화1" src="${fn:split(movieInfo.m_filename, '|')[0]}">
    </c:forEach>
 	</div>
 	<div style="width: 235px;">
		<div class="reserved_info">
 		<c:forEach var="movieInfo" items="${movieInfoList}">
 			<div class="movie_title">
				<strong class="tit_info"> 
					<span class="ic_grade <c:choose><c:when test="${movieInfo.rating eq '12세관람가' }">gr_12</c:when>
						  <c:when test="${movieInfo.rating eq '12세이상관람가' }">gr_12</c:when>
						  <c:when test="${movieInfo.rating eq '전체관람가' }">gr_all</c:when>
						  <c:when test="${movieInfo.rating eq '15세관람가' }">gr_15</c:when>
						  <c:when test="${movieInfo.rating eq '15세이상관람가' }">gr_15</c:when>
						  <c:when test="${movieInfo.rating eq '18세관람가(청소년관람불가)' }">gr_19</c:when>
						  <c:when test="${movieInfo.rating eq '청소년관람불가' }">gr_19</c:when></c:choose>">
					</span>
					<span class="moviename">${movieInfo.m_name}</span>
				</strong> 
			</div>
			<div class="movie_info_dates">
				<p class="info_title">일시</p>
				<p class="infos_all">${movieInfo.mt_date}</p>
				<p class="infos_all">${movieInfo.mt_start} ~ ${movieInfo.mt_end}</p>
			</div>
        <div class="movie_info_cinema">
        	<p class="info_title">영화관</p>
        	<p class="infos_all">${movieInfo.c_branch} ${movieInfo.th_name}관</p>
        </div>
    </c:forEach>
    <div class="reserved_number">
    	<p class="info_title">인원</p> 
    	<p class="infos_all">${ticketNumber}명</p>
    </div>
 	</div>
 	<div class="reserved_space">
    	<p class="info_title">좌석</p>
    	<p class="infos_all">${selectedSeats}</p>
 	</div>
 	</div>
 </div>
<div class="pay_coupon">
<div class="all_titles"><p>할인적용</p></div>
    <div class="coupon_list">
    <h3>쿠폰</h3>
        <c:if test="${member.coupon_cnt == 0 }">
            보유한 쿠폰이 없습니다.
        </c:if>
        <c:if test="${member.coupon_cnt > 0 }">
            <c:forEach var="couponList" items="${couponList}">
                <c:if test="${couponList.coupon_where==1 && couponList.coupon_use==1}">
                    <input type="radio" class="coupon-select" name="pay	-coupon" data-mcNum="${couponList.mc_num}" data-coupon="${couponList.coupon_sale}">
                    <div class="coupon_detail">
                        <span class="coupon_name">${couponList.coupon_name}</span>
                        <span class="coupon_detail_all">${couponList.coupon_content}</span>
                        <span class="coupon_detail_all">${couponList.coupon_regdate} ~ ${couponList.coupon_enddate}<span class="coupon_time"></span></span>
                    </div>
                </c:if>
                <c:if test="${couponList.coupon_where==2 && couponList.coupon_use==1}">
                    <div class="coupon_detail" style="background-color:#BDBDBD">
                        <span class="coupon_name">(벌스샵 전용 - 사용 불가) ${couponList.coupon_name}</span>
                        <span class="coupon_detail_all">${couponList.coupon_content}</span>
                        <span class="coupon_detail_all">${couponList.coupon_regdate} ~ ${couponList.coupon_enddate}</span>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
    </div>
</div>

 	
 	<div class="pay_select">
 		<div class="all_titles"><p>결제하기</p></div>
 		<div class="point_pay">
 			<h3>포인트 결제</h3>
 				<div class="point_charging">
 					<b><span class="point_detail">${member.point}P</span></b>
 					<button class="point_charge" onclick="location.href='${pageContext.request.contextPath}/member/pointCharge'">포인트 충전</button>
 			</div>
 		</div>
<div class="pay_decision">
 		<div class="pay_origin">
 			<span>티켓 금액</span>
 			<span><strong class="payMoney">${payMoney}</strong>원</span>
 		</div>
 		<div class="pay_discount">
 			<span>할인 금액</span>
 			<span>- <strong class="discount-amount">0</strong>원</span>
 		</div>
 		<div class="pay_total">
 			<span>결제 금액</span>
 			<span>총 <strong class="final-amount">${payMoney}</strong>원</span>
 		</div>
 		<div class="pay_button">
 		<form action="${pageContext.request.contextPath}/movie/confirmPayment" method="post" id="paymentForm">
 				<input type="hidden" name="mt_num" value="${movieInfoList[0].mt_num}">
 				<input type="hidden" name="ticketNumber" value="${ticketNumber}">
 				<input type="hidden" name="selectedSeats" value="${selectedSeats}">
 				<input type="hidden" id="seatNum" name="seatNum" value="${seatNum}">
 				<input type="hidden" id="m_code" name="m_code" value="${movieInfoList[0].m_code}">
 				<input type="hidden" id="mc_num" name="mc_num" value=""> 
				<%-- <input type="hidden" id="userPoints" name="userPoints" value="${member.point}"> --%>
 			<input type="hidden" id="finalAmountInput" name="finalAmount" value="${payMoney}">
 			<input type="button" value="결제하기" class="pay_btn">
 			</form>
 		</div>
 	</div>

 	</div>
 </div>

<script>

document.addEventListener("DOMContentLoaded", function() {
    let lastSelectedCoupon = null; // 마지막으로 선택된 쿠폰을 저장할 변수

    // 쿠폰 선택 시 할인 금액 반영 및 hidden input 업데이트
    document.querySelectorAll('.coupon-select').forEach(function(coupon) {
        coupon.addEventListener('click', function() {
            // 라디오 버튼의 상태가 변경될 때마다 updateDiscount 함수 호출
            if (lastSelectedCoupon === coupon) {
                coupon.checked = false; // 같은 라디오 버튼을 클릭하면 체크 해제
                lastSelectedCoupon = null; // 마지막으로 선택된 쿠폰 초기화
            } else {
                lastSelectedCoupon = coupon; // 다른 라디오 버튼을 클릭하면 선택된 라디오 버튼을 업데이트
            }
            updateDiscount();
            updateHiddenInput(); // hidden input 업데이트 호출
        });
    });

    function updateDiscount() {
        let totalDiscount = 0; // 총 할인 금액을 저장할 변수 초기화

        // 선택된 (체크된) 쿠폰 선택 라디오 버튼을 가져옴
        const selectedCoupon = document.querySelector('.coupon-select:checked');
        if (selectedCoupon) {
            // 선택된 쿠폰의 할인 금액을 가져와서 정수로 변환 후 총 할인 금액에 더함
            totalDiscount += parseInt(selectedCoupon.getAttribute('data-coupon'));
        }

        // 결제 금액을 가져옴 (원 단위를 제외하고 숫자만 추출하여 정수로 변환)
        let payMoney = parseInt(document.querySelector('.payMoney').textContent.replace(/[^0-9]/g, ''));
        // 최종 결제 금액 계산 (결제 금액에서 총 할인 금액을 뺌)
        let finalAmount = payMoney - totalDiscount;

        // 총 할인 금액을 표시하는 요소의 텍스트 내용을 업데이트
        document.querySelector('.discount-amount').textContent = totalDiscount.toLocaleString();
        // 최종 결제 금액을 표시하는 요소의 텍스트 내용을 업데이트
        document.querySelector('.final-amount').textContent = finalAmount.toLocaleString();
        // 최종 결제 금액을 숨겨진 입력 필드에 저장 (서버로 전송될 수 있도록)
        document.getElementById('finalAmountInput').value = finalAmount;
    }

    function updateHiddenInput() {
        const selectedCoupon = document.querySelector('.coupon-select:checked');
        if (selectedCoupon) {
            // 선택된 쿠폰의 data-mcNum 값을 가져와서 hidden input의 value를 업데이트
            const mcNum = selectedCoupon.getAttribute('data-mcNum');
            document.getElementById('mc_num').value = mcNum;
        } else {
            // 선택된 쿠폰이 없는 경우, hidden input을 비워둠
            document.getElementById('mc_num').value = '';
        }
    }

    // 결제 버튼 클릭 시 결제가 완료되었음을 알리는 알림창 표시
    document.querySelector('.pay_btn').addEventListener('click', function(event) {
        event.preventDefault(); // 기본 동작 중단

        // 최종 결제 금액을 가져옴
        let finalAmount = parseInt(document.getElementById('finalAmountInput').value);
        // 사용자의 포인트를 가져옴
        let userPoints = parseInt(document.querySelector('.point_detail').textContent.replace(/[^0-9]/g, ''));

        // 결제 금액이 0 이하인 경우 알림창을 표시하고 결제를 중단
        if (finalAmount < 0) {
            alert('결제 금액이 0원 미만입니다. 다시 확인해주세요.');
            return;
        }

        // 사용자의 포인트가 결제 금액보다 적은 경우 알림창을 표시하고 결제를 중단
        if (userPoints < finalAmount) {
            alert('포인트가 부족합니다. 다시 확인해주세요.');
            return;
        }

        alert('결제가 완료되었습니다.');
        document.getElementById('paymentForm').submit(); // 폼 제출
    });
});

</script>

