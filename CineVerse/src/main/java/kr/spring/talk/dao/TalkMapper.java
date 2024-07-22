package kr.spring.talk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.talk.vo.TalkMemberVO;
import kr.spring.talk.vo.TalkRoomVO;
import kr.spring.talk.vo.TalkVO;

@Mapper
public interface TalkMapper {
	// 채팅방 목록
	public List<TalkRoomVO> selectTalkRoomList(Map<String, Object> map);
	public Integer selectRowCount(Map<String,Object> map);
	
	// 채팅방 번호 생성하기
	@Select("SELECT talkroom_seq.nextval FROM dual")
	public Integer selectTalkRoomNum();
	// 채팅방 생성하기
	@Insert("INSERT INTO talkroom(talkroom_num, basic_name) VALUES(#{talkroom_num},#{basic_name})")
	public void insertTalkRoom(TalkRoomVO talkRoomVO);
	
	//채팅방 멤버 등록
	@Insert("INSERT INTO talk_member(talkroom_num, room_name, mem_num) VALUES(#{talkroom_num},#{room_name},#{mem_num})")
	public void insertTalkRoomMember(@Param(value="talkroom_num") Long talkroom_num,@Param(value="room_name") String room_name,@Param(value="mem_num") Long mem_num);
	
	// 채팅방 멤버 읽기 
	public List<TalkMemberVO> selectTalkMember(Long talkroom_num);
	
	// 채팅 메세지 번호 생성하기
	@Select("SELECT talk_seq.nextval FROM dual")
	public Integer selectTalkNum();
	// 채팅 메세지 등록하기
	@Insert("INSERT INTO talk (talk_num, talkroom_num, mem_num, message) VALUES (#{talk_num},#{talkroom_num},#{mem_num},#{message})")
	public void insertTalk(TalkVO talkVO);
	
	// 읽지 않은 채팅 기록 저장하기
	@Insert("INSERT INTO talk_read(talkroom_num, talk_num,mem_num) VALUES (#{talkroom_num},#{talk_num},#{mem_num})")
	public void insertTalkRead(@Param(value = "talkroom_num")Long talkroom_num,@Param(value = "talk_num") Long talk_num,@Param(value = "mem_num") Long mem_num);
	
	// 채팅 메세지 읽기
	public List<TalkVO> selectTalkDetail(Map<String, Long> map);
	// 읽은 채팅 기록 삭제
	@Delete("DELETE FROM talk_read WHERE talkroom_num=#{talkroom_num} AND mem_num=#{mem_num}")
	public void deleteTalkRead(Map<String, Long> map);
	
	// 해당 채팅방 넘버의 mem_num인지 확인하기 위해
	@Select("SELECT mem_num FROM talk_member WHERE talkroom_num=#{talkroom_num}")
	public List<Long> selectMembers(Long talkroom_num);
	
	// Talk_read 테이블 내용 삭제
	@Delete("DELETE FROM talk_read WHERE talkroom_num=#{talkroom_num}")
	public void deleteTalk_Read(Long talkroom_num);
	// Talk 테이블 내용 삭제
	@Delete("DELETE FROM talk WHERE talkroom_num=#{talkroom_num}")
	public void deleteTalk(Long talkroom_num);
	// talk_member 테이블 내용 삭제
	@Delete("DELETE FROM talk_member WHERE talkroom_num=#{talkroom_num}")
	public void deleteTalkMember(Long talkroom_num);
	// talkroom 테이블 내용 삭제
	@Delete("DELETE FROM talkroom WHERE talkroom_num=#{talkroom_num}")
	public void deleteTalkRoom(Long talkroom_num);
	
	// 채팅방 이름 변경하기
	@Update("UPDATE talk_member set room_name=#{room_name} WHERE talkroom_num=#{talkroom_num}")
	public void updateTalkRoomName(TalkMemberVO talkMemberVO);
}
