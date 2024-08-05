package com.ck.app.user;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.FileDAO;
import com.ck.model.dao.UserDAO;
import com.ck.model.dto.FileDTO;
import com.ck.model.dto.UserDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserUpdateOkAction implements Action {
    @Override
    public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {  
        String userid = (String)req.getSession().getAttribute("loginUser");
        String saveFolder = req.getServletContext().getRealPath("file");
        int size = (int)(1024*1024*1024*1.5);
        MultipartRequest multi = new MultipartRequest(req, saveFolder, size, "UTF-8", new DefaultFileRenamePolicy());
        Enumeration<?> temp = multi.getFileNames();
        ArrayList<String> fileNames = new ArrayList<String>();

        while (temp.hasMoreElements()) {
            fileNames.add((String)temp.nextElement());
        }

        UserDTO user = new UserDTO();
        UserDAO udao = new UserDAO();
        FileDAO fdao = new FileDAO();
        FileDTO fdto = new FileDTO();
        
        // 사용자 정보 가져오기
        user = udao.getUserById(userid);

        // 폼 필드에서 데이터 가져오기
        String userpw = multi.getParameter("userpw");  
        String userpw_re = multi.getParameter("userpw_re");  
        String username = multi.getParameter("username");
        String nickname = multi.getParameter("nickname");
        String phone = multi.getParameter("phone");
        String email = multi.getParameter("email");

        // 파일 정보 가져오기
        String systemname = multi.getFilesystemName("file");
        String orgname = multi.getOriginalFileName("file");

        // 파일 정보 설정
        fdto.setSystemname(systemname);
        fdto.setOrgname(orgname);
        fdto.setUserid(userid);

        if (userpw_re != null && !userpw_re.isEmpty()) {
            user.setUserpw(userpw_re);
        } else {
            user.setUsername(username);
            user.setNickname(nickname);
            user.setPhone(phone);
            user.setEmail(email);
        }

        // 파일 업로드가 있었는지 확인하고 조건에 따라 처리
        if (systemname != null) {
            // 새로운 파일이 업로드된 경우 기존 파일 삭제 후 새 파일 저장
            fdao.deleteFileByUserid(fdto);
            fdao.insertFile(fdto);
        } else if (!fileNames.isEmpty() && fileNames.get(0).equals("delete")) {
            // 파일 삭제 요청이 있는 경우
            fdao.deleteFileByUserid(fdto);
        }

        // 데이터베이스 업데이트 실행
        Transfer transfer = new Transfer();
        transfer.setRedirect(true);
        if (udao.updateUser(user)) {
            transfer.setPath(req.getContextPath() + "/app/user/userpage.jsp?userid=" + user.getUserid());
        } else {
            // 실패 시 처리 로직 필요
        }

        return transfer;
    }
}

