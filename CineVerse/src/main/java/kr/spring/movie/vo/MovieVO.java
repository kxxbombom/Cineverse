package kr.spring.movie.vo;


import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieVO {
	private long m_code;
	@NotBlank
	private String m_name;
	private Date m_opendate;
	@NotBlank
	private String m_companys;
	private long m_status;
	private String m_filename;
	private String m_content;
	private String plot;
	private String m_genre;
	@NotNull
	private MultipartFile m_upload;	//파일
	
	private String actor;
	private String director;
	private String genre;
	
	
	private int bookmark_cnt;			//북마크 개수
} 
