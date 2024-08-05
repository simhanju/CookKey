<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<link rel="stylesheet" href="${cp}/assets/css/sidebar.css" />
<script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
	<div id="sidebar">
                  <div class="inner">

                     <!-- Search -->
                        <section id="search" class="alt">
                           <form method="get" action="search.jsp">
                              <input type="text" name="b_keyword" id="b_keyword" placeholder="검색어를 입력하세요" value="${param.b_keyword}"/>
                           </form>
                        </section>

                     <!-- Menu -->
                        <nav id="menu">
                           <header class="major">
                              <h2>Menu</h2>
                           </header>
                           <ul>
                              <li>
                                            <i id="myIcon" class="fa-solid fa-house"></i>
                                            <a href="${cp}/index.jsp">홈</a>
                                        </li>
                                        <li>
                                            <i id="myIcon" class="fa-solid fa-store"></i>
                                            <a href="${cp}/storelist.st">장터</a>
                                        </li>
                              			<li>
                                            <i id="myIcon" class="fa-solid fa-bookmark"></i>
                                            <a href="${cp}/app/board/bmlist.jsp">북마크</a>
                                        </li>
                                        <li>
                                            <i id="myIcon" class="fa-solid fa-heart"></i>
                                            <a href="${cp}/storeBookmarkList.st">찜목록</a>
                                        </li>
                           </ul>
                        </nav>

                     <!-- Footer -->
                        <footer id="footer">
                           <p class="copyright">&copy; Untitled. All rights reserved.</p>
                        </footer>

                  </div>
               </div>