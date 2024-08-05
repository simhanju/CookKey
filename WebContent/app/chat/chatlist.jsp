<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>index</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${cp}/assets/css/main.css" />
    <link rel="stylesheet" href="${cp}/assets/css/chat.css" />
    <script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
</head>
<body class="is-preload">
    <!-- Wrapper -->
    <div id="wrapper">
        <!-- Main -->
        <div id="main">
            <div class="inner">
                <!-- Header -->
								<%@ include file = "/app/header.jsp" %>
                <section>
                    <div id="chat">
                        <div class="chatlist"> <!-- 채팅 목록 -->
                            <div class="chat-box-header">채팅 목록</div>
                            <div class="tbody"></div>
                        </div>
                        <div class="chat-box">
                            <div class="chat-box-header">
                                사용자 채팅
                                <span class="chat-box-toggle">
                                    <span class="material-symbols-outlined"><a href ="${cp}/app/chat/chatlist.jsp" style="color:white;">close</a></span>
                                    <span class="material-symbols-outlined2" class="">refresh</span>
                                </span>
                            </div>
                            <div class="chat-box-body">
                                <div class="chat-box-overlay"></div>
                                <div class="chat-logs"></div> <!-- 채팅 로그 -->
                                <!--chat-log -->
                            </div>
                            <div class="chat-input">
                                <form id="chatForm" action="${cp}/chatsend.ch" method="post">
                                    <input type="hidden" name="chatID" id="chatID" value="${param.chatID}" >
                                    <input type="hidden" name="userid" id="userid" value="${loginUser}" >
                                    <span class="echo-receiver"></span> <input type="text"
                                        id="chat-input" name="message" placeholder="Send a message..."/>
                                    <button type="submit" class="chat-submit" id="chat-submit">
                                        <span class="material-symbols-outlined">send</span>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <!-- Sidebar -->
               <%@ include file = "/app/sidebar.jsp" %>
    </div>
</body>
    <script src="${cp}/assets/js/jquery.min.js"></script>
    <script src="${cp}/assets/js/browser.min.js"></script>
    <script src="${cp}/assets/js/breakpoints.min.js"></script>
    <script src="${cp}/assets/js/util.js"></script>
    <script src="${cp}/assets/js/main.js"></script>
    <script>
    $(document).ready(function() {
        var loginUser = "${sessionScope.loginUser}";
        var chatID = "${param.chatID}";

        function ChatList() {
            $.ajax({
                url: "${cp}/chatlist.ch", 
                type: "GET", 
                dataType: "json",
                success: function(response) {
                    if (response) {
                        if (response.chatlist && response.chatlist.length > 0) {
                            var str = "";
                            $.each(response.chatlist, function(index, chat) {
                                str += '<div class="c_list">';
                                str += '<div class="link-container">';
                                str += '<a href="${cp}/app/chat/chatlist.jsp?chatID='+chat.chatID+'" class="link" data-chatID="'+ chat.chatID +'">';
                                if (chat.userid_1 === loginUser) {
                                    str += chat.userid_2 + ' 님';
                                } else {
                                    str += chat.userid_1 + ' 님';
                                }
                                str += '</a></div></div>';
                            });
                            $('.tbody').html(str); 
                        } else {
                            $('.tbody').html('<div class="row no-list">대화중인 채팅방이 없습니다.</div>');
                        }
                    } else {
                        console.error("Received empty response.");
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Failed to fetch chat list:", error);
                }
            });
        }

        // 페이지 로드 시 대화 내용을 불러오는 함수
        function loadoldMessages(chatID) {
            $.ajax({
                url: "${cp}/chatview.ch",
                type: "GET",
                dataType: "json",
                data: { chatID: chatID },
                success: function(response) {
                    if (response && response.messageList && response.messageList.length > 0) {
                        var chatLogs = $('.chat-box-body .chat-logs');
                        $.each(response.messageList, function(index, message) {
                            appendMessage(message, chatLogs);
                        });
                    } else {
                        console.log("No messages to display.");
                    }
                },
                error: function(xhr, status, error) {
                	  console.log("No new messages to display.");
                }
            });
        }
     // 새로운 메시지를 불러오고 화면에 추가하는 함수
        function loadNewMessages(chatID) {
                $.ajax({
                    url: "${cp}/chatview.ch",
                    type: "GET",
                    dataType: "json",
                    data: { chatID: chatID },
                    success: function(response) {
                        if (response && response.messageList && response.messageList.length > 0) {
                            var chatLogs = $('.chat-box-body .chat-logs');
                            var existingMessages = chatLogs.find('.chat-message').map(function() {
                                return $(this).text();
                            }).get();

                            $.each(response.messageList, function(index, message) {
                                if (!existingMessages.includes(message.writerID + ": " + message.chatcontent)) {
                                    // 새로운 메시지를 화면에 추가
                                    appendMessage(message, chatLogs);
                                }
                            });
                        } else {
                            console.log("No new messages to display.");
                        }
                    },
                    error: function(xhr, status, error) {
                        console.log("Failed to load new messages:", error);
                    }
                });
        }


        // 대화 내용을 화면에 추가하는 함수
        function appendMessage(message, chatLogs) {
            var messageContainer = $('<div class="chat"></div>');
            if (message.writerID && message.chatcontent) {
                var messageText = $('<p class="chat-message"></p>').text(message.writerID + ": " + message.chatcontent);
                messageContainer.append(messageText);
            }
            if (message.writerID === loginUser) {
                messageContainer.addClass('self');
            } else {
                messageContainer.addClass('other');
            }
            chatLogs.append(messageContainer);
        }

        // 페이지 로드 시 대화 내용을 불러옵니다.
        loadoldMessages(chatID);

   
	
        $('#chat-submit').click(function(e) {
            e.preventDefault();
            var message = $('#chat-input').val();
            sendMessage(message);
        });
        $('.material-symbols-outlined2').click(function(e) {
            e.preventDefault();
            loadNewMessages(chatID);
            appendMessage(message, chatLogs)
        });

        function sendMessage(message) {
            $.ajax({
                url: "${cp}/chatsend.ch",
                type: "POST",
                data: { chatID: chatID, userid: loginUser, message: message },
                success: function(response) {
                    console.log("Message sent successfully.");
                    $('#chat-input').val('');
                    // 전송한 메시지를 즉시 화면에 추가합니다.
                    var chatLogs = $('.chat-box-body .chat-logs');
                    appendMessage({ writerID: loginUser, chatcontent: message }, chatLogs);
                },
                error: function(xhr, status, error) {
                    console.error("Failed to send message:", error);
                }
            });
        }

        // 페이지 로드 시 채팅 업데이트 시작
        ChatList();
    });

    </script>
</html>
