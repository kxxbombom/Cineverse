package kr.spring.myPage.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.board.vo.BoardCommentVO;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.myPage.service.MyPageService;
import kr.spring.myPage.vo.MyPageVO;
import kr.spring.util.CaptchaUtil;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class MyPageController {
	@Autowired
	public MyPageService mypageService;

	// 자바빈(VO) 초기화
	@ModelAttribute
	public MyPageVO initCommand() {
		return new MyPageVO();
	}

	// 메인 페이지
	@GetMapping("/myPage/myPageMain")
	public String myPageMain(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		member.setCoupon_cnt(mypageService.selectMemberCoupon(user.getMem_num()));
		log.debug("<<마이페이지 >> : " + member);
		model.addAttribute("member", member);
		return "myPageMain";
	}

	// 나의 예매내역
	@GetMapping("/myPage/reservation")
	public String myPageReservation(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		member.setCoupon_cnt(mypageService.selectMemberCoupon(user.getMem_num()));
		model.addAttribute("member", member);
		return "myPageReservation";
	}

	// 나의 쿠폰
	@GetMapping("/myPage/coupon")
	public String myPageCoupon(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		member.setCoupon_cnt(mypageService.selectMemberCoupon(user.getMem_num()));

		// 쿠폰 리스트 불러오기 시작
		Map<String, Object> map = new HashMap<String, Object>();
		// mem_num 지정하기
		map.put("mem_num", user.getMem_num());

		log.debug("<< 쿠폰 개수 >> : " + member.getCoupon_cnt());

		List<MyPageVO> couponList = null;
		// 쿠폰 개수 0보다 크면 list에 삽입
		if (member.getCoupon_cnt() > 0) {
			couponList = mypageService.selectMemCouponList(map);
		}

		model.addAttribute("couponList", couponList);
		model.addAttribute("member", member);

		return "coupon";
	}

	// 나의 활동 - 기대되는 영화
	@GetMapping("/myPage/expectingMovie")
	public String expectingMovie(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		member.setCoupon_cnt(mypageService.selectMemberCoupon(user.getMem_num()));
		model.addAttribute("member", member);
		return "expectingMovie";
	}

	// 나의 활동 - 내가 본 영화
	@GetMapping("/myPage/watchedMovie")
	public String watchedMovie(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		member.setCoupon_cnt(mypageService.selectMemberCoupon(user.getMem_num()));
		model.addAttribute("member", member);
		return "watchedMovie";
	}

	// 나의 활동 - 내가 쓴 별점
	@GetMapping("/myPage/review")
	public String myPageReview(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		member.setCoupon_cnt(mypageService.selectMemberCoupon(user.getMem_num()));
		model.addAttribute("member", member);
		return "review";
	}

	// 북마크
	@GetMapping("/myPage/bookMark")
	public String myPageBookMark(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		member.setCoupon_cnt(mypageService.selectMemberCoupon(user.getMem_num()));
		model.addAttribute("member", member);
		return "bookMark";
	}

	// 게시판 - 내가 쓴 글
	@GetMapping("/myPage/boardWrite")
	public String myPageBoardWrite(@RequestParam(defaultValue = "") String cb_type,
								   @RequestParam(defaultValue = "0") int category, 
								   HttpSession session, 
								   Model model) {

		log.debug("<<카테고리 타입 >> : " + cb_type);
		log.debug("<<카테고리 >> : " + category);

		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cb_type", cb_type);
		map.put("category", category);
		map.put("mem_num", user.getMem_num());

		int count = mypageService.cBoardWriteListcnt(map);
		log.debug("<<게시글 수>> : " + count);

		List<BoardVO> list = null;
		if (count > 0) {
			list = mypageService.selectMemcBoardWriteList(map);
			log.debug("<<글 목록>> : " + list);
		}

		model.addAttribute("member", member);
		model.addAttribute("list", list);
		model.addAttribute("count", count);

		return "myBoardWrite";
	}

	// 게시판 - 내가 쓴 댓글
	@GetMapping("/myPage/boardReply")
	public String myPageBoardReply(@RequestParam(defaultValue = "") String cb_type,
	                               @RequestParam(defaultValue = "0") int category,
	                               HttpSession session,
	                               Model model) {

	    log.debug("<<카테고리 타입 >> : " + cb_type);
	    log.debug("<<카테고리 >> : " + category);

	    MemberVO user = (MemberVO) session.getAttribute("user");
	    MyPageVO member = mypageService.selectMember(user.getMem_num());

	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("cb_type", cb_type);
	    map.put("category", category);
	    map.put("mem_num", user.getMem_num());

	    List<BoardCommentVO> list = null;
	    int count = mypageService.cBoardReplyListcnt(map);
	    if(count > 0) {
	        list = mypageService.cBoardReplyList(map);

	        log.debug("<< 댓글 목록 >> : " + list);
	    }

	    model.addAttribute("member", member);
	    model.addAttribute("list", list);
	    model.addAttribute("count", count);
	    return "myBoardReply";
	}

	// 내 캘린더

	// 이벤트 참여 내역
	@GetMapping("/myPage/myEvent")
	public String myPageEvent(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		model.addAttribute("member", member);
		return "myEvent";
	}

	// 구매 - 포인트 충전 내역
	@GetMapping("/myPage/pointList")
	public String myPagePointList(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_num", user.getMem_num());

		int count = mypageService.pointHistoryCnt(user.getMem_num());
		List<MyPageVO> list = null;

		if (count > 0) {
			list = mypageService.selectMemPointList(map);
			// 리스트 출력할 때 최근 날짜 순으로 출력하도록 설정해주는 문장
			list.sort(Comparator.comparing(MyPageVO::getPh_date).reversed());
		}
		model.addAttribute("list", list);
		model.addAttribute("member", member);
		return "pointList";
	}

	// 구매 - 굿즈 결제 내역
	// 장바구니

	// 채팅 이력
	@GetMapping("/myPage/chatList")
	public String myPageChatList(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		model.addAttribute("member", member);
		return "chatList";
	}

	// 회원 정보 - 비밀번호 변경 폼
	@GetMapping("/myPage/passwdChange")
	public String myPagePasswdChange(MyPageVO myPageVO, HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		model.addAttribute("member", member);

		return "passwdChange";
	}

	//비밀번호 변경 폼
	@PostMapping("/myPage/passwdChange")
	public String submitpasswdChange(@Valid MyPageVO myPageVO, BindingResult result, HttpSession session, Model model, HttpServletRequest request) {
		log.debug("<<비밀번호 변경 처리>> : " + myPageVO);
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasFieldErrors("now_passwd") || result.hasFieldErrors("mem_passwd") || result.hasFieldErrors("captcha_chars")) {
			return "passwdChange";
		}

		// ------- 캡챠 문자 체크 시작 --------- //
		String code = "1"; // 키 발급 0, 캡챠 이미지 비교시 1로 세팅

		// 캡챠 키 발급시 받아서 세션에 저장해둔 키값
		String key = (String)session.getAttribute("captcha_key");
		// 사용자가 입력한 캡챠 이미지 글자값
		String value = myPageVO.getCaptcha_chars();
		String key_apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value=" + value; 

		Map<String,String> requestHeaders = new HashMap<String,String>();
		requestHeaders.put("X-Naver-Client-Id",  "pjCvRXCBMUnA7oYsWNKj");
		requestHeaders.put("X-Naver-Client-Secret", "DYu3HKzI4x");
		String responseBody = CaptchaUtil.get(key_apiURL, requestHeaders);

		log.debug("<<캡챠 결과>> : " + responseBody);
		JSONObject jObject = new JSONObject(responseBody);

		boolean captcha_result = jObject.getBoolean("result");
		if(!captcha_result) {
			result.rejectValue("captcha_chars", "invalidCaptcha");
			return "passwdChange";
		}
		// ------- 캡챠 문자 체크 끝 --------- //

		MemberVO user = (MemberVO) session.getAttribute("user");
		myPageVO.setMem_num(user.getMem_num());
		MyPageVO member = mypageService.selectMember(user.getMem_num());

		model.addAttribute("member", member);

		if(!member.getMem_passwd().equals(myPageVO.getNow_passwd())) {
			result.rejectValue("now_passwd", "invalidPassword");
			return "passwdChange";
		}

		mypageService.updatePassword(myPageVO);

		model.addAttribute("message", "비밀번호 변경 완료");
		model.addAttribute("url",  request.getContextPath() + "/myPage/myPageMain");

		return "common/resultAlert";
	}
	/*====================
	 * 	네이버 캡챠 API 사용\
	 =====================*/

	//캡챠 이미지 호출

	@GetMapping("/myPage/getCaptcha")
	public String getCaptcha(Model model, HttpSession session) {

		String code = "0";//키 발급시 0, 캡챠 이미지 비교시 1로 세팅
		String key_apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;

		Map<String,String> requestHeaders = new HashMap<String,String>();
		requestHeaders.put("X-Naver-Client-Id",  "pjCvRXCBMUnA7oYsWNKj");
		requestHeaders.put("X-Naver-Client-Secret", "DYu3HKzI4x");

		String responseBody = CaptchaUtil.get(key_apiURL, requestHeaders);

		log.debug("<<responseBody>> : " + responseBody);

		JSONObject jObject = new JSONObject(responseBody);

		try {
			//https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값(을 "key"가 읽어옴
			String key = jObject.getString("key");
			session.setAttribute("captcha_key", key);

			String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;

			byte[] response_byte = CaptchaUtil.getCaptchaImage(apiURL, requestHeaders);

			model.addAttribute("imageFile", response_byte);
			model.addAttribute("filename", "captcha.jpg");

		}catch(Exception e) {
			log.error(e.toString());
		}
		return "imageView";
	}





	// 회원 정보 - 개인정보 변경
	@GetMapping("/myPage/modifyUser")
	public String modifyUser(MyPageVO myPageVO, HttpSession session, Model model) {
	    MemberVO user = (MemberVO) session.getAttribute("user");
	    MyPageVO member = mypageService.selectMember(user.getMem_num());
	    model.addAttribute("member", member);
	    return "modifyUser";
	}

	@PostMapping("/myPage/modifyUser")
	public String postModifyUser1(@Valid MyPageVO myPageVO, BindingResult result, HttpSession session, Model model, HttpServletRequest request) {

	    if (result.hasErrors()) {
	        return "modifyUser";
	    }
	    
	    MemberVO user = (MemberVO) session.getAttribute("user");
	    myPageVO.setMem_num(user.getMem_num());
	    MyPageVO member = mypageService.selectMember(user.getMem_num());
	    
	    // 데이터베이스 업데이트 수행
	    mypageService.updateMember_detail(myPageVO);
	    
	    log.debug("<<개인정보 변경>> : " + myPageVO);
	    // 세션에 저장된 정보 변경
	    user.setMem_name(myPageVO.getMem_name());
	    user.setMem_nickname(myPageVO.getMem_nickName());
	    user.setMem_email(myPageVO.getMem_email());
	    
	    model.addAttribute("member",member);
	    model.addAttribute("message", "개인정보 변경 완료");
	    model.addAttribute("url", request.getContextPath() + "/myPage/modifyUser");
	    return "common/resultAlert";
	}
	
	
	

	// 회원 정보 - 회원 탈퇴
	@GetMapping("/myPage/deleteMember")
	public String deleteMember(MyPageVO myPageVO, HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		model.addAttribute("member", member);
		return "deleteMember";
	}

	// 멤버십 구독
	@GetMapping("/myPage/memberShipSub")
	public String myPageMemberShipSub(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		model.addAttribute("member", member);
		return "memberShipSub";
	}

	// 나의 문의 내역 - 1:1문의
	@GetMapping("/myPage/consult")
	public String myPageConsult(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MyPageVO member = mypageService.selectMember(user.getMem_num());
		model.addAttribute("member", member);
		return "consult";
	}

	// 프로필 사진 출력하기(로그인 전용)
	@GetMapping("/member/photoView")
	public String getProfile(HttpSession session, HttpServletRequest request, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		log.debug("<< 프로필 사진 출력 >> : " + user);

		if (user == null) {
			getBasicProfileImage(request, model);
		} else {
			MyPageVO member = mypageService.selectMember(user.getMem_num());
			viewProfile(member, request, model);
		}

		return "imageView";
	}

	// 프로필 사진 출력하기
	@GetMapping("/member/viewProfile")
	public String getProfileByMem_num(long mem_num, HttpServletRequest request, Model model) {
		MyPageVO member = mypageService.selectMember(mem_num);

		viewProfile(member, request, model);

		return "imageView";
	}

	// 회원 프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MyPageVO member, HttpServletRequest request, Model model) {
		if (member == null || member.getPhoto_name() == null) {
			getBasicProfileImage(request, model);
		} else {
			model.addAttribute("imageFile", member.getPhoto());
			model.addAttribute("filename", member.getPhoto_name());
		}
	}

	// 기본 이미지 읽어오기
	public void getBasicProfileImage(HttpServletRequest request, Model model) {
		byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/profile_none.png"));
		model.addAttribute("imageFile", readbyte);
		model.addAttribute("filename", "profile_none.png");
	}

}
