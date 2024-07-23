<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!-- 영화관 상세 정보 시작 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>

<div class="page-main">
	<div class="branch-title">${cinema.c_branch}</div>
		<!-- 극장 정보 -->
		<h2 class="branch-info">극장 정보</h2>
		<div class="info-box">	
		<div class="info-content">
		<div class="branch-address">위치 : <b>${cinema.c_address}</b></div>					
           <div class="branch-phone">문의전화 : <b>${cinema.c_phone}</b></div>
           <div class="branch-theater">상영관수 :  <b>${theaterCount}관</b></div>
           <c:if test="${cinema.c_parkable == 0}">
           <div class="branch-parkable">주차정보 : <b>불가능</b></div>
		</c:if>
		<c:if test="${cinema.c_parkable == 1}">
           <div class="branch-parkable">주차정보 : <b>가능</b></div>
		</c:if>
		</div>
			<div class="cinema-icon">
				<c:if test="${cinema.c_parkable == 0}">
					<img alt="" src="${pageContext.request.contextPath}/images/hjt/icon1.png" style="width: 80px; height: 80px;">
					<b>주차 불가능</b>
				</c:if>
				
				<c:if test="${cinema.c_parkable == 1}">
					<img alt="" src="${pageContext.request.contextPath}/images/hjt/icon2.png" style="width: 80px; height: 80px;">
					<b>주차 가능</b>
				</c:if>
			</div>
		</div>
		
		<!-- 위치 상세 보기 -->
		<h2 class="branch-position">극장 위치 상세 보기</h2>
		<div class="location-button"> 
    <div id="branch-list">
        <!-- 지점명 목록이 동적으로 여기에 추가됩니다 -->
    </div>
    <div class="location-button-list" id="modal_open_btn">위치/지도보기(클릭하면 지도를 보실 수 있습니다.)</div>
</div>
<div id="modal" style="display: none;">
    <div class="modal_content">
        <button type="button" class="close_btn" id="modal_close_btn">×</button>
        <div class="modal_title">지도</div>
        <hr class="custom-hr">
        <!-- 지도 -->        		
        <!-- 지도를 표시할 div 입니다 -->
        <div id="modal_map" style="width: 100%; height: 700px;"></div>
        <div class="map_wrap">
            <div id="map" class="bg_white">
                <div class="option">
                    <div>
                        <form onsubmit="searchPlaces(); return false;">
                            키워드 : <input type="text" value="영화관" id="keyword" size="15"> 
                            <button type="submit">검색하기</button> 
                        </form>
                    </div>
                </div>
                <hr>
                <ul id="placesList"></ul>
                <div id="pagination"></div>
            </div>
        </div>
    </div>
    <div class="modal_layer"></div>
</div>

<!-- 지도 스크립트 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=23eb84e1f8e8eadb7771addf44c8a193&libraries=services"></script>
<script type="text/javascript">
    var map; // 지도를 전역 변수로 선언

    // 모달 열기 버튼 클릭 시
    document.getElementById("modal_open_btn").onclick = function() {
        document.getElementById("modal").style.display = "block";

        var container = document.getElementById('modal_map'); // 지도를 담을 영역의 DOM 레퍼런스
        var options = { // 지도를 생성할 때 필요한 기본 옵션
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 레벨(확대, 축소 정도)
        };

        // 지도 객체가 없으면 생성
        if (!map) {
            map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
        } else {
            map.relayout(); // 지도 크기 재조정
            map.setCenter(options.center); // 중심 좌표 재설정
        }

        // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch('${cinema.c_address}', function(result, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });

                // 인포윈도우로 장소에 대한 설명을 표시합니다
                var infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;padding:6px 0;">영화관 위치</div>'
                });
                infowindow.open(map, marker);

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);
            }
        });
    }

    // 모달 닫기 버튼 클릭 시
    document.getElementById("modal_close_btn").onclick = function() {
        document.getElementById("modal").style.display = "none";
    }
</script>
		
		
		
		<!-- 상영 시간표 -->
		<div class="time-box">
			<h2 class="time-table">상영시간표</h2>
        <div class="reserve-time">
        	<hr size="1" noshade width="100%">
            <div class="cinema-gallery"></div>
			<script>
			    // 현재 날짜 객체 생성
			    var currentDate = new Date();

			    // 요일 배열 생성
			    var daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];

			    // JSP에서 전달된 c_num 값을 JavaScript 변수로 할당
			    var cNum = ${cinema.c_num};

			    // 영화 날짜 생성 함수
			    function generateMovieDays() {
			        var gallery = document.querySelector('.cinema-gallery'); 
			        gallery.innerHTML = ''; // 기존 내용을 비웁니다.

			        for (var i = 0; i < 12; i++) {
			            var dayElement = document.createElement('div');
			            var displayDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate() + i); // 현재 날짜에 i일을 더함
			            var year = displayDate.getFullYear().toString().slice(2); // 연도를 YY 형식으로 저장
			            var month = ('0' + (displayDate.getMonth() + 1)).slice(-2); // 월을 2자리 형식으로 저장
			            var day = ('0' + displayDate.getDate()).slice(-2); // 일을 2자리 형식으로 저장
			            var dayOfWeek = daysOfWeek[displayDate.getDay()]; // 요일을 가져옴

			            var formattedDate = day + '<br>' + dayOfWeek; // 날짜와 요일 포맷
			            var dayClass = 'movie-day';

			            // 요일에 따라 클래스 추가
			            switch (displayDate.getDay()) {
			                case 0:
			                    dayClass = 'movie-day-sun';
			                    break;
			                case 6:
			                    dayClass = 'movie-day-sat';
			                    break;
			            }

			            dayElement.className = dayClass;
			            dayElement.innerHTML = formattedDate;
			            dayElement.setAttribute('data-date', year + '/' + month + '/' + day); // data-date 속성에 YY/MM/DD 형식의 날짜 추가
			            dayElement.setAttribute('data-cNum', cNum); // data-cNum 속성에 c_num 값 추가

			            // 클릭 이벤트 추가
			            dayElement.addEventListener('click', function() {
			                var mt_date = this.getAttribute('data-date');
			                var c_num = this.getAttribute('data-cNum');
			                $.ajax({
			                    url: '/cinema/getMovieList',
			                    method: 'get',
			                    data: { mt_date: mt_date, c_num: c_num },
			                    success: function(response) {
			                        var theater_list = document.querySelector('.theater-list');
			                        theater_list.innerHTML = ''; // 기존 목록 비우기
			                        response.moviewTimeList.forEach(function(movie) {
			                            var listItem = document.createElement('li');
										listItem.innerHTML = movie.m_name + '<br>' + movie.th_name + '  ' + movie.mt_start + '~' + movie.mt_end;
										theater_list.appendChild(listItem); // 목록에 항목 추가
			                        });
			                    },
			                    error: function() {
			                        alert('네트워크 오류');
			                    }
			                });
			            });

			            // gallery 요소에 dayElement를 자식 요소로 추가
			            gallery.appendChild(dayElement);
			        }
			    }

			    // 페이지 로드 시 영화 날짜 생성 함수 호출
			    window.onload = generateMovieDays;
			</script>
            <hr size="1" noshade width="100%">
					<ul class="theater-list">
						
					</ul>
					
		</div>
		

		
		
		<!-- 관리자 버튼 -->
	<div class="admin-button">
		<input type="button" value="수정"  onclick="location.href='update?c_num=${cinema.c_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script>
			const delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				const choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete?c_num=${cinema.c_num}');
				}
			};
		</script>   
		<input type="button" value="목록"  onclick="location.href='cinemaList2'">
	</div>
</div>
</div>
<!-- 영화관 상세 정보 시작 -->












