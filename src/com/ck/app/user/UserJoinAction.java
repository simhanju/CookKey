package com.ck.app.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.CategoryDAO;
import com.ck.model.dto.CategoryDTO;

public class UserJoinAction implements Action{

   @Override
   public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
      List<CategoryDTO> list = null;
      
      CategoryDAO cdao = new CategoryDAO();
      CategoryDTO cdto = new CategoryDTO();
      list = cdao.getCategories(cdto);
      
      if(list != null) {
         req.setAttribute("list", list);
         System.out.println("카테고리 리스트 잘 불러옴.");
         System.out.println(list);
      }
      else {
         System.out.println("DB에서 카데고리를 불러오지 못함.");
      }
      Transfer transfer = new Transfer();
      transfer.setRedirect(false);
      transfer.setPath("/app/user/join.jsp");
      return transfer;
   }
}