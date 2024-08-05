package com.ck.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ck.model.dto.FileDTO;
import com.ck.mybatis.SQLMapConfig;

public class FileDAO {
	private SqlSession ss;
	
	public FileDAO() {
		ss = SQLMapConfig.getFactory().openSession(true);
	}

	public boolean insertFile(FileDTO fdto) {
		return ss.insert("File.insert",fdto) == 1;
	}
	public boolean insertUser(FileDTO fdto) {
		return ss.insert("File.insertUser",fdto) == 1;
	}

	public void deleteFile(FileDTO fdto ) {
		ss.delete("File.delete",fdto);
	}
	public void deleteFileByUserid(FileDTO fdto ) {
		ss.delete("File.deleteByUserid",fdto);
	}
	
	public void deleteFileNum(long storenum) {
		ss.delete("File.deleteNum",storenum);
	}
	public void deleteByBoardNum(long boardnum) {
		ss.delete("File.deleteByBoardNum",boardnum);
	}
	
	public List<FileDTO> getFiles(FileDTO fdto) {
		return ss.selectList("File.getFiles", fdto);
	}
	public int getLastNum() {
		return ss.selectOne("File.getLastNum");
	}
	public FileDTO getFile(long storenum) {
		return ss.selectOne("File.getFile", storenum);
	}
	public FileDTO getUserFile(String userid) {
		return ss.selectOne("File.getUserFile", userid);
	}
	public FileDTO getBoardFile(long boardnum) {
		return ss.selectOne("File.getFile",boardnum);
	}

	public int getUserFileCnt(String userid) {
		return ss.selectOne("File.getUserFileCnt", userid);
	}
}





