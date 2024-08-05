package com.ck.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ck.model.dto.BookmarkDTO;
import com.ck.model.dto.StoreDTO;
import com.ck.mybatis.SQLMapConfig;

public class StoreDAO {
	private SqlSession ss;
	
	public StoreDAO() {
		ss = SQLMapConfig.getFactory().openSession(true);
	}
	
	public long getStoreCnt() {
		return ss.selectOne("Store.getStoreCnt");
	}
	
	public long getStoreCnt(String keyword) {
		return ss.selectOne("Store.getStoreCntWithKey",keyword);
	}

	public List<StoreDTO> getList(int startRow, int pageSize) {
		HashMap<String, Integer> datas = new HashMap<String, Integer>();
		datas.put("startRow", startRow);
		datas.put("pageSize", pageSize);
		return ss.selectList("Store.getList",datas);
	}

	public List<StoreDTO> getList(int startRow, int pageSize, String keyword) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("startRow", startRow);
		datas.put("pageSize", pageSize);
		datas.put("keyword",keyword);
		return ss.selectList("Store.getListWithKey",datas);
	}
	

	public boolean insertStore(StoreDTO store) {
		return ss.insert("Store.insert",store) == 1;
	}

	public StoreDTO getStoreByNum(long storenum) {
		return ss.selectOne("Store.get",storenum);
	}

	public void updateReadCount(long storenum) {
		ss.update("Store.updateReadCount",storenum);
	}

	public boolean deleteStore(long storenum) {
		return ss.delete("Store.delete",storenum) == 1;
	}

	public long getLastNum(String userid) {
		return ss.selectOne("Store.getLastNum",userid);
	}
	public boolean updateStore(StoreDTO store) {
		return ss.update("Store.update",store) == 1;
	}
	
}







