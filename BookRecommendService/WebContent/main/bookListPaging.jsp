<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="book.test.*,java.util.*" %>
<jsp:useBean id="booksdao" class="book.test.BooksDAO" scope="application"/>
<jsp:useBean id="reviewlikesdao" class="book.test.ReviewLikesDAO" scope="application"/>
<!-- 도서 전체 리스트 출력 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookList</title>
<%
ArrayList<BooksDTO> books = booksdao.selectAllPaging(request);
System.out.println("도서 개수: " + books.size());
request.setAttribute("books", books);  // 리스트 데이터 가져오기


// 평균 별점이 가장 높은 도서 제목, bookid, 출판사, 저자 가져오기
String title = booksdao.bestRankBook();
String bookid = booksdao.bestRankBookID();
String publisher = booksdao.bestRankBookPub();
String authors = booksdao.bestRankBookAut();

request.setAttribute("title", title);
request.setAttribute("bookid", bookid);
request.setAttribute("publisher", publisher);
request.setAttribute("authors", authors);

System.out.println("평균별점이 가장 높은 도서: " + title);


// 찜한 유저가 가장 많은 도서 제목, bookid, 출판사, 저자 가져오기
String favorTitle = booksdao.bestFavorBook();
String favorBookid = booksdao.bestFavorBookID();
String favorPublisher = booksdao.bestFavorBookPub();
String favorAuthors = booksdao.bestFavorBookAut();

request.setAttribute("favorTitle", favorTitle);
request.setAttribute("favorBookid", favorBookid);
request.setAttribute("favorPublisher", favorPublisher);
request.setAttribute("favorAuthors", favorAuthors);

System.out.println("찜한 유저가 가장 많은 도서: " + favorTitle);
%>
</head>
<body>
<jsp:forward page="/main/viewBookListPaging.jsp"/>
</body>
</html>