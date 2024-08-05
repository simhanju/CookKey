package com.ck.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ck.model.dto.BookmarkDTO;
import com.ck.mybatis.SQLMapConfig;

public class BookmarkDAO {
	private SqlSession ss;
	
	public BookmarkDAO() {
		ss = SQLMapConfig.getFactory().openSession(true);
	}
	
	public boolean insertBookmark(BookmarkDTO bkdto) {

		try {
			ss.insert("Bookmark.insert",bkdto);
			return true;
		} catch (Exception e) {
			deleteBookmark(bkdto);
		}
		
		return true;
	}
	public boolean deleteBookmark(BookmarkDTO bkdto) {
		return ss.delete("Bookmark.delete",bkdto) == 1;
	}
	public List<BookmarkDTO> getBookmark(BookmarkDTO bkdto) {
		return ss.selectList("Bookmark.getBookmark", bkdto);
	}
	public long getStoreBookmarkCnt(String userid) {
		return ss.selectOne("Bookmark.getStoreBookmarkCnt", userid);
	}
	public long getBoardBookmarkCnt(String userid) {
		return ss.selectOne("Bookmark.getBoardBookmarkCnt", userid);
	}
}
