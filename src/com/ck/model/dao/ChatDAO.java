package com.ck.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ck.model.dto.ChatDTO;
import com.ck.model.dto.MessageDTO;
import com.ck.mybatis.SQLMapConfig;



public class ChatDAO {
	private SqlSession ss;
	
	public ChatDAO() {
		ss = SQLMapConfig.getFactory().openSession(true);
	}
	
	public boolean createChat(ChatDTO chat) {
		return ss.insert("Chat.createChat",chat) == 1;
	}
	
	public List<ChatDTO> getChatListByUserid(String userid) {
		return ss.selectList("Chat.getChatListByUserid",userid);
	}
	
	
	public boolean deleteChatRoom(long chatID) {
		return ss.delete("Chat.delete",chatID) == 1;
	}
	
	public boolean sendMessage(MessageDTO message) {
		return ss.insert("Chat.sendMessage",message) == 1;
	}	

	public List<MessageDTO> getMessageByChatID(long chatID) {
		return ss.selectList("Chat.getMessageByChatid",chatID);
	}
	
	public boolean deleteMessage(long chatID) {
		return ss.delete("Chat.deleteMEssage",chatID) == 1;
	}
}
