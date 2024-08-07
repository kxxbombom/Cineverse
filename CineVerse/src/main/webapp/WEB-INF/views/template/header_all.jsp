<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_all.css" type="text/css">
<header class="site-header">
    <div class="container">
        <nav class="main-navigation">
            <ul class="main-navigation-ul">
                <li class="main-navigation-li"><a href="${pageContext.request.contextPath}/shop/shopBasket" class="main-navigation-a"><img src="${pageContext.request.contextPath}/images/cmj/shoppingcart.png" width="16" height="16"></a></li>
                <li class="main-navigation-li"><a href="${pageContext.request.contextPath}/shop/shopFav" class="main-navigation-a"><img src="${pageContext.request.contextPath}/images/cje/heart.png" width="16" height="18"></a></li>
                <li class="main-navigation-li"><a href="${pageContext.request.contextPath}/member/membershipInfo" class="main-navigation-a">멤버십</a></li>
                <li class="main-navigation-li"><a href="${pageContext.request.contextPath}/support/main" class="main-navigation-a">고객센터</a></li>
                <li class="main-navigation-li"><a href="${pageContext.request.contextPath}/member/pointCharge" class="main-navigation-a">포인트</a></li>
            </ul>
        </nav>
    </div>
</header>

<header class="secondary-header">
    <div class="container">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/main/main"><img src="${pageContext.request.contextPath}/images/cmj/header_logo.png" width="140"></a>
        </div>
        <nav class="main-navigation">
            <ul class="secondary-ul">
                <li class="secondary-li">
                	<a href="#" class="secondary-a">예매</a>
                	<div class="hide_menu">
                        <ul class="hide_menu_ul">
                            <li class="hide_menu_li"><a href="${pageContext.request.contextPath}/movie/movieReserve" title="예매하기" class="hide-menu-a">예매하기</a></li>
                            <li class="hide_menu_li"><a href="${pageContext.request.contextPath}/movie/movieTime" title="상영시간표" class="hide-menu-a">상영시간표</a></li>
                        </ul>
                    </div>
                </li>
                <li class="secondary-li">
                	<a href="${pageContext.request.contextPath}/movie/movieList" class="secondary-a">영화</a>
                	<div class="hide_menu">
                        <ul class="hide_menu_ul">
                            <li class="hide_menu_li"><a href="${pageContext.request.contextPath}/movie/movieList" class="hide-menu-a" title="홈">홈</a></li>
                            <li class="hide_menu_li"><a href="${pageContext.request.contextPath}/movie/movieListCurrent" title="현재상영작" class="hide-menu-a">현재상영작</a></li>
                            <li class="hide_menu_li"><a href="${pageContext.request.contextPath}/movie/movieListSchedule" title="상영예정작" class="hide-menu-a">상영예정작</a></li>
                        </ul>
                    </div>	
                </li>
                <li class="secondary-li">
                	<a href="${pageContext.request.contextPath}/cinema/cinemaList2" class="secondary-a">영화관</a>
                </li>
                <li class="secondary-li">
                	<a href="${pageContext.request.contextPath}/event/event" class="secondary-a">이벤트</a>
               		<div class="hide_menu">
                        <ul class="hide_menu_ul">
                            <li class="hide_menu_li"><a href="${pageContext.request.contextPath}/event/event" class="hide-menu-a" title="홈">홈</a></li>
                            <li class="hide_menu_li"><a href="${pageContext.request.contextPath}/event/event?event_type=1" class="hide-menu-a" title="영화">영화</a></li>
                            <li class="hide_menu_li"><a href="${pageContext.request.contextPath}/event/event?event_type=2" class="hide-menu-a" title="시사회/무대인사">시사회/무대인사</a></li>
                            <li class="hide_menu_li"><a href="${pageContext.request.contextPath}/event/event?event_type=3" class="hide-menu-a" title="영화관">영화관</a></li>
                        </ul>
                    </div>
               	</li>
                <li class="secondary-li">
                	<a href="${pageContext.request.contextPath}/board/list" class="secondary-a">커뮤니티</a>
                	<div class="hide_menu">
                        <ul class="hide_menu_ul">
                            <li class="hide_menu_li"><a class="hide-menu-a" href="${pageContext.request.contextPath}/board/list" title="영화톡톡">영화톡톡</a></li>
                            <li class="hide_menu_li"><a class="hide-menu-a" href="${pageContext.request.contextPath}/assignboard/list" title="양도/교환">양도/교환</a></li>
                        </ul>
                    </div>
                </li>
                <li class="secondary-li"><a href="${pageContext.request.contextPath}/shop/shopMain" class="secondary-a">벌스샵</a></li>
            </ul>
        </nav>
        <div class="user-menu">
        		<c:if test="${!empty user }">
               	<a href="${pageContext.request.contextPath}/member/logout" class="user-menu-a">로그아웃</a>
                </c:if>
                <c:if test="${empty user}">
				<a href="${pageContext.request.contextPath}/member/login" class="user-menu-a">로그인</a>               
                </c:if>
                
                <c:if test="${!empty user }">
               	<a href="${pageContext.request.contextPath}/myPage/myPageMain" class="user-menu-a">마이페이지</a>
                </c:if>
                <c:if test="${empty user}">
				<a href="${pageContext.request.contextPath}/member/register" class="user-menu-a">회원가입</a>               
                </c:if>
        </div>
    </div>
</header>

<script>
document.querySelectorAll('.main-navigation > ul > li').forEach(function(menu) {
    let timeoutId;

    menu.addEventListener('mouseenter', function() {
        clearTimeout(timeoutId); // 마우스를 올리면 기존 타임아웃 제거
        const submenu = this.querySelector('.hide_menu');
        if (submenu) {
            submenu.style.display = 'block';
            submenu.style.opacity = 1;
        }
    });

    menu.addEventListener('mouseleave', function() {
        const submenu = this.querySelector('.hide_menu');
        if (submenu) {
            timeoutId = setTimeout(() => {
                submenu.style.display = 'none';
                submenu.style.opacity = 0;
            }, 250); // 딜레이 더 길게 잡음
        }
    });

    const submenu = menu.querySelector('.hide_menu');
    if (submenu) {
        submenu.addEventListener('mouseenter', function() {
            clearTimeout(timeoutId); // 하위 메뉴에 마우스가 올라갔을 때 타이머 초기화 하여 유지 되게 설정
        });

        submenu.addEventListener('mouseleave', function() {
            timeoutId = setTimeout(() => {
                submenu.style.display = 'none';
                submenu.style.opacity = 0;
            }, 250); // 하위 메뉴를 떠났을 때 250ms 후에 사라지도록 딜레이 설정
        });
    }
});
</script>

