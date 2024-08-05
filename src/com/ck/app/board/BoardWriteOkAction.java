package com.ck.app.board;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;
import com.ck.model.dao.CategoryDAO;
import com.ck.model.dao.FileDAO;
import com.ck.model.dto.BoardDTO;
import com.ck.model.dto.CategoryDTO;
import com.ck.model.dto.FileDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOkAction implements Action {
    @Override
    public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 파일이 실제로 저장될 경로
        String saveFolder = req.getServletContext().getRealPath("file");
        System.out.println(saveFolder);

        // 저장될 파일의 최대 크기(1.5GB)
        int size = (int) (1024 * 1024 * 1024 * 1.5);

        // cos 이용
        MultipartRequest multi = new MultipartRequest(req, saveFolder, size, "UTF-8",
                new DefaultFileRenamePolicy());

        // input[type=file]의 name 속성값들
        Enumeration<?> temp = multi.getFileNames();
        ArrayList<String> fileNames = new ArrayList<String>();
        while (temp.hasMoreElements()) {
            fileNames.add((String) temp.nextElement());
        }
        // fileNames 리스트에는 모든 input[type=file]의 name들이 추가되어 있다.
        // 비어있는 input[type=file] name도 추가되어 있으므로
        int len = fileNames.size();
        // 비어있는 그 name 하나 삭제
        fileNames.remove("file" + (len - 1));

        Collections.reverse(fileNames);

        String boardcontents = multi.getParameter("boardcontents");
        String userid = (String) req.getSession().getAttribute("loginUser");
        
       // 수정된 부분: 배열 형태의 파라미터 받아오기
       String[] categorynum = multi.getParameterValues("categorynum");
       if (categorynum != null) {
           for (String category : categorynum) {
               System.out.println("Category number: " + category);
           }
       } else {
           System.out.println("No category selected.");
       }


        BoardDTO board = new BoardDTO();

        board.setBoardcontents(boardcontents);
        board.setUserid(userid);

        System.out.println(board.getBoardcontents());
        System.out.println(board.getUserid());

        BoardDAO bdao = new BoardDAO();
        Transfer transfer = new Transfer();
        transfer.setRedirect(true);

        // 파일 데이터 삽입 성공 여부
        boolean bicheck = false;
        // 게시글을 쓸 때 파일을 업로드 했었는지 여부
        boolean flag = false;
        if (bdao.insertBoard(board)) {
            BoardDTO bdto = new BoardDTO();
            bdto = bdao.getLastNum();
            long boardnum = bdto.getBoardnum();
            FileDAO fdao = new FileDAO();

            for (String name : fileNames) {
                flag = true;
                String orgname = multi.getOriginalFileName(name);

                String systemname = multi.getFilesystemName(name);

                FileDTO fdto = new FileDTO();
                fdto.setBoardnum(boardnum);
                fdto.setOrgname(orgname);
                fdto.setSystemname(systemname);

                bicheck = fdao.insertFile(fdto);

                if (!bicheck) {
                    break;
                }
            }
            CategoryDTO cdto = new CategoryDTO();
            CategoryDAO cdao = new CategoryDAO();
            cdto.setBoardnum(boardnum);
            if (categorynum == null || categorynum.length == 0) {
                cdto.setCategorynum(5000);
                cdao.insertCategory(cdto);
            } else {
                for (int i = 0; i < categorynum.length; i++) {
                    cdto.setCategorynum(Integer.parseInt(categorynum[i]));
                    cdao.insertCategory(cdto);
                }
            }

            // 파일 업로드 했니?
            if (flag) {
                // DB삽입은 실패했니?
                if (!bicheck) {
                    for (String name : fileNames) {
                        FileDTO fdto = new FileDTO();
                        String systemname = multi.getFilesystemName(name);
                        // DB상에 있는 t_file 테이블에 올라갔던 내용들 삭제
                        fdto.setSystemname(systemname);
                        fdto.setBoardnum(boardnum);
                        fdao.deleteFile(fdto);

                        // 실제 경로에 존재하는 파일을 자바의 객체로 가져옴
                        File file = new File(saveFolder, systemname);
                        // 파일이 존재한다면
                        if (file.exists()) {
                            // 삭제
                            file.delete();
                        }
                    }
                    bdao.deleteBoard(boardnum);
                    // 실패 페이지로 이동
                }
            }

            // get
            transfer.setPath(req.getContextPath() + "/app/board/list.jsp");

        } else {
            // list
            Cookie cookie = new Cookie("w", "f");
            cookie.setPath("/");
            cookie.setMaxAge(1);
            resp.addCookie(cookie);
            transfer.setPath(req.getContextPath() + "/app/board/list.jsp");
        }
        return transfer;
    }
}
