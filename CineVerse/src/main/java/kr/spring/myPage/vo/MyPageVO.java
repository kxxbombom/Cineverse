package kr.spring.myPage.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"photo"})
public class MyPageVO {
	private long mem_num;
	
	@Pattern(regexp = "^[0-9a-zA-Z]{4,12}$")
	private String mem_id;
	
	private int mem_auth;
	private String auto;
	private String au_id;
	
	@NotBlank
	private String mem_name;
	
	@Pattern(regexp = "^[0-9a-zA-Z]{4,12}$")
	private String mem_passwd;
	
	@Pattern(regexp = "^[0-9a-zA-Z]+$")
	private String captcha_cahrs;
	
	@NotBlank
	private String mem_phone;
	
	@NotBlank
	private String mem_email;
	private Date mem_reg_date;
	private Date mem_modify_date;
	private String social_kakao;
	
	private byte[] photo;
	private String photo_name;
	
	private int mem_rank;
	private int mem_membership;
	private int ph_point;
	
	// 비밀번호 변경시 현재 비밀번호를 저장하는 용도로 사용
	@Pattern(regexp = "^[0-9a-zA-Z]{4,12}$")
	private String now_passwd;	// 컬럼은 없지만 필요에 의해 생성해줌
		
		public boolean ischeckedPassword(String userPasswd) {
			if(mem_auth > 1 && mem_passwd.equals(userPasswd)) {
				return true;
			}
			return false;
		}
	
	// 이미지 BLOB 처리
		// (주의) 폼에서 파일 업로드 파라미터 네임은 반드시 upload로 지정해야 한다
		public void setUpload(MultipartFile upload) throws IOException {
			// MultipartFile -> byte []
			setPhoto(upload.getBytes());
			
			//파일 이름
			setPhoto_name(upload.getOriginalFilename());
		}
}