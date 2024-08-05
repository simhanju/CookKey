package com.ck.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ck.model.dto.BoardBookmarkDTO;
import com.ck.model.dto.BoardDTO;
import com.ck.model.dto.LikeDTO;
import com.ck.mybatis.SQLMapConfig;

public class BoardDAO {
	private SqlSession ss;
	
	public BoardDAO() {
		ss = SQLMapConfig.getFactory().openSession(true);
	}
	
	/**
	 * BoardDTO로 새로운 게시글을 생성하는 메소드
	 * 
	 * @param board
	 * @return
	 */
	public boolean insertBoard(BoardDTO board) {
		return ss.insert("Board.insert", board) == 1;
	}
	
	/**
	 * boardnum으로 BoardDTO를 찾는 메소드
	 * 
	 * @param boardnum
	 * @return
	 */
	public BoardDTO getBoardByBoardNum(long boardnum) {
		return ss.selectOne("Board.getBoardByBoardNum",boardnum);
	}
	
	/**
	 * 유저 아이디로 BoardDTO 리스트를 찾는 메소드
	 * 
	 * @param userid
	 * @return
	 */
	public List<BoardDTO> getBoardListByUserid(String userid, long maxNum, int pageSize) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("userid", userid);
		datas.put("maxNum", maxNum);
		datas.put("pageSize", pageSize);
		return ss.selectList("Board.getListByUserId", datas);
	}

	
	/**
	 * userid, 이전 boardList의 마지막 boardnum, 가져올 board의 개수로
	 * BoardDTO 리스트를 가져오는 메소드
	 * 
	 * @param userid
	 * @param maxNum
	 * @param pageSize
	 * @return
	 */
	public List<BoardDTO> getMainBoardList(String userid, long maxNum, int pageSize){
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("userid", userid);
		datas.put("maxNum", maxNum);
		datas.put("pageSize", pageSize);
		return ss.selectList("Board.getMainBoardList", datas);
	}

	/**
	 * keyword, 이전 boardList의 마지막 boardnum, 가져올 board의 개수로
	 * BoardDTO 리스트를 가져오는 메소드
	 * 
	 * @param keyword
	 * @param maxNum
	 * @param pageSize
	 * @return
	 */
	public List<BoardDTO> getBoardListByKeyword(String keyword, long maxNum, int pageSize){
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("keyword", keyword);
		datas.put("maxNum", maxNum);
		datas.put("pageSize", pageSize);
		return ss.selectList("Board.getListByKeyword", datas);
	}

	/**
	 * categorynum, 이전 boardList의 마지막 boardnum, 가져올 board의 개수로
	 * BoardDTO 리스트를 가져오는 메소드
	 * 
	 * @param categorynum
	 * @param maxNum
	 * @param pageSize
	 * @return
	 */
	public List<BoardDTO> getBoardList(int categorynum, long maxNum, int pageSize){
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("categorynum", categorynum);
		datas.put("maxNum", maxNum);
		datas.put("pageSize", pageSize);
		return ss.selectList("Board.getListByCategoryNum", datas);
	}
	
	/**
	 * userid, 이전 boardList의 마지막 boardnum, 가져올 board의 개수로
	 * userid에 해당하는 사람의 boardbookmark 리스트를 가져오는 메소드
	 * 
	 * @param userid
	 * @param maxNum
	 * @param pageSize
	 * @return
	 */
	public List<BoardDTO> getBookmarkBoardListByUserid(String userid, long maxNum, int pageSize){
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("userid", userid);
		datas.put("maxNum", maxNum);
		datas.put("pageSize", pageSize);
		return ss.selectList("Board.getBookmarkBoardListByUserid", datas);
	}

	/**
	 * 해당 boardnum의 board 내용을 수정하는 메소드
	 * (boardcontents, updatedate, open을 수정)
	 * 
	 * @param board
	 * @return
	 */
	public boolean modifyBoard(BoardDTO board) {
		return ss.update("Board.update", board) == 1;
	}

	/**
	 * 해당 boardnum의 readcount + 1 하는 메소드
	 * 
	 * @param boardnum
	 * @return
	 */
	public boolean updateReadCount(long boardnum) {
		return ss.update("Board.updateReadCount", boardnum) == 1;
	}
	
	/**
	 * 해당 boardnum의 board를 삭제하는 메소드
	 * 
	 * @param boardnum
	 * @return
	 */
	public boolean deleteBoard(long boardnum) {
		return ss.delete("Board.delete", boardnum) == 1;
	}

	/**
	 * BoardBookmarkDTO의 userid와 boardnum을 사용하여
	 * 해당 북마크를 생성하는 메소드
	 * 
	 * @param boardbookmark
	 * @return
	 */
	public boolean insertBoardBookmark(BoardBookmarkDTO boardbookmark) { 
		return ss.insert("Board.insertBoardBookmark", boardbookmark) == 1;
	}
	
	/**
	 * userid와 boardnum을 받아 해당 boardbookmark를 가져오는 메소드
	 * (null일 경우, 해당 boardbookmark는 없다.)
	 * @param boardbookmark
	 * @return
	 */
	public BoardBookmarkDTO getMyBoardBookmarkByUseridAndBoardnum(BoardBookmarkDTO boardbookmark) {
		return ss.selectOne("Board.getMyBoardBookmarkByUseridAndBoardnum", boardbookmark);
	}
	
	/**
	 * BoardBookmarkDTO의 userid와 boardnum을 사용하여
	 * 해당 북마크를 삭제하는 메소드
	 * 
	 * @param boardbookmark
	 * @return
	 */
	public boolean deleteBoardBookmark(BoardBookmarkDTO boardbookmark) { 
		return ss.delete("Board.deleteBoardBookmark", boardbookmark) == 1;
	}	
	
	/**
	 * LikeDTO의 userid와 boardnum을 사용하여
	 * 해당 좋아요를 생성하는 메소드
	 * 
	 * @param like
	 * @return
	 */
	public boolean insertLike(LikeDTO like) {
		return ss.insert("Board.insertLike", like) == 1;
	}
	
	/**
	 * 해당 boardnum 게시글의 좋아요 개수를 가져오는 메소드
	 * 
	 * @param boardnum
	 * @return
	 */
	public long getLikeCnt(long boardnum) {
	      return ss.selectOne("Board.getLikeCnt", boardnum);
	}
	
	/**
	 * 해당 userid와 boardnum을 받아 like를 가져오는 메소드
	 * (null이라면 유저가 그 게시글의 좋아요를 누르지 않은 것이다.) 
	 * 
	 * @param like
	 * @return
	 */
	public LikeDTO getMyLikeOfBoardByUseridAndBoardnum(LikeDTO like){
		return ss.selectOne("Board.getMyLikeOfBoardByUseridAndBoardnum", like);
	}
	
	/**
	 * LikeDTO의 userid와 boardnum을 사용하여
	 * 해당 좋아요를 삭제하는 메소드
	 * 
	 * @param like
	 * @return
	 */
	public boolean deleteLike(LikeDTO like) {
		return ss.delete("Board.deleteLike", like) == 1;
	}
	
	/**
	 * board의 마지막 번호를 찾는 메소드
	 * 
	 * @param userid
	 * @return
	 */
	public BoardDTO getLastNum() {
		return ss.selectOne("Board.getLastNum");
	}
}
