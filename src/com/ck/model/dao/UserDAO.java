package com.ck.model.dao;


import org.apache.ibatis.session.SqlSession;

import com.ck.model.dto.UserDTO;
import com.ck.mybatis.SQLMapConfig;

public class UserDAO {
	private SqlSession ss;
	
	public UserDAO() {
		ss = SQLMapConfig.getFactory().openSession(true);
	}
	public boolean insertUser(UserDTO user) {
		return ss.insert("User.insert", user) == 1;
	}
	public UserDTO getUserById(String userid) {
		return ss.selectOne("User.select",userid);
	}
	public boolean updateUser(UserDTO user) {
		return ss.update("User.update",user)==1;
	}

	

}
