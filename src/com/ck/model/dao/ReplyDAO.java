package com.ck.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ck.model.dto.ReplyDTO;
import com.ck.mybatis.SQLMapConfig;

public class ReplyDAO {
	private SqlSession ss;
	
	public ReplyDAO() {
		ss = SQLMapConfig.getFactory().openSession(true);
	}
	/**
	 * ReplyDTO를 사용하여 reply 입력하는 메소드
	 * 
	 * @param reply
	 * @return
	 */
	public boolean insertReply(ReplyDTO reply) {
		return ss.insert("Board.insertReply",reply) == 1;
	}
	
	/**
	 * boardnum과 maxNum, pageSize를 사용하여 ReplyDTO 리스트를 불러오는 메소드
	 * maxNum : 마지막 replynum
	 * pageSize : 보여줄 reply의 개수
	 * 
	 * @param boardnum
	 * @param maxNum
	 * @param pageSize
	 * @return
	 */
	public List<ReplyDTO> getReplies(long boardnum, long maxNum, int pageSize){
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("boardnum", boardnum);
		datas.put("maxNum", maxNum);
		datas.put("pageSize", pageSize);
		return ss.selectList("Board.getRepliesByBoardnum", datas);
	}
	
	   public ReplyDTO getLastReplyOfBoardByBoardnum(long boardnum) {
		   return ss.selectOne("Board.getLastReplyOfBoardByBoardnum", boardnum);
	   }

	/**
	 * ReplyDTO를 사용하여 Reply 데이터를 수정하는 메소드
	 *  
	 * @param reply
	 * @return
	 */
	public boolean updateReply(ReplyDTO reply) {
		return ss.update("Board.updateReply",reply) == 1;
	}

	/**
	 * replynum을 사용하여 Reply 데이터를 삭제하는 메소드 
	 * 
	 * @param replynum
	 * @return
	 */
	public boolean deleteReply(long replynum) {
		return ss.delete("Board.deleteReply",replynum) == 1;
	}

	
}
