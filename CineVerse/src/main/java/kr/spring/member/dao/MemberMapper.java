package kr.spring.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.CouponVO;
import kr.spring.member.vo.MemberVO;


@Mapper
public interface MemberMapper {
	
	// 회원 관리 - 일반 회원
	@Select("SELECT member_seq.nextval FROM dual")
	public Long selectMem_num();
	@Insert("INSERT INTO member (mem_num, mem_id, mem_auth, mem_rank, mem_membership) VALUES (#{mem_num}, #{mem_id}, 3, 1, 1)")
	public void insertMember(MemberVO member);
	
	// 카카오 로그인으로 회원가입
	public void insertKakaoMemberDetail(MemberVO member);
	
	// 회원가입시 회원가입 축하 쿠폰 증정
	@Insert("INSERT INTO member_coupon (mc_num, mem_num, coupon_num) VALUES (member_coupon_seq.nextval,#{mem_num}, #{coupon_num})")
	public void insertNewMemCoupon(CouponVO coupon);
    @Select("SELECT coupon_num FROM coupon_db WHERE coupon_num IN (1, 2)")
    public List<Long> selectInitialCoupons();
	
	// xml 작성
	public void insertMember_detail(MemberVO member);
	public MemberVO selectCheckMember(String mem_id);
	// 전화 번호 중복 체크
	public MemberVO selectCheckPMember(String mem_phone);
	// 이메일 중복 체크
	public MemberVO selectCheckEMember(String mem_email);
	public void updateAu_id(String au_id, Long mem_num);
	public void selectAu_id(String au_id);
	public void deleteAu_id(Long mem_num);
	
	//아이디 찾기
	@Select("SELECT * FROM member LEFT OUTER JOIN member_detail USING(mem_num) WHERE mem_name=#{mem_name} AND mem_phone=#{mem_phone}")
	public MemberVO findId(MemberVO member);
	// 비밀번호 찾기
	@Select("SELECT * FROM member LEFT OUTER JOIN member_detail USING(mem_num) WHERE mem_id=#{mem_id} AND mem_phone=#{mem_phone} AND mem_email=#{mem_email}")
	public MemberVO updateRandomPasswd(MemberVO member);
	// 비밀번호 변경
	@Update("UPDATE member_detail SET mem_passwd = #{mem_passwd} WHERE mem_num = (SELECT mem_num FROM member WHERE mem_id = #{mem_id})")
	public void updatePassword(String mem_passwd, String mem_id);
	
	//포인트 충전하기
	@Insert("INSERT INTO point_history(ph_num,ph_point,ph_date,mem_num,ph_type,ph_payment) VALUES(point_history_seq.nextval,#{ph_point},SYSDATE,#{mem_num},2,#{ph_payment})")
	public void chargePoint(Long ph_point, Long mem_num, String ph_payment);
	
	// 포인트 충전/사용/환불 후 멤버의 point 업데이트하기
	public void totalPoint(Long mem_num);
}

