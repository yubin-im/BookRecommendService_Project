<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="book.test.*, java.util.*" errorPage="mainError.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="books" type="java.util.ArrayList" scope="request"/>
<!-- 도서 전체 리스트 출력 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewBookList</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f3f3f3;
}
.main-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px auto;
    background-color: #ffffff;
    box-shadow: 0px 0px 10px #888888;
}

.main-table th, .main-table td {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

.main-table th {
    background-color: #f2f2f2;
    text-align: center; /* 헤더 텍스트 가운데 정렬 추가 */
}

.main-table tr:nth-child(even) {
    background-color: #f2f2f2;
}

.paging-table {
    width: 600px;
    margin: 0 auto;
}

/* 추가된 CSS 스타일 */
.paging-table td {
    text-align: center;
}

.green-text {
    color: green;
    display: inline-block; /* 요소를 인라인 블록으로 설정하여 텍스트를 가운데 정렬합니다. */
    text-align: left; /* 텍스트 내용을 왼쪽으로 정렬합니다. */
    margin: 0 auto; /* 좌우 여백을 자동으로 설정하여 가운데 정렬합니다. */
}

.main-table th {
    background-color: #f2f2f2;
    text-align: center; /* 헤더 텍스트 가운데 정렬 추가 */
}
.section-header {
    background-color: #4CAF50;
    color: white;
    padding: 5px;
    margin: 10px 0;
    font-weight: bold;
    text-align: center;
}

.section-header a {
    color: #007BFF;
    text-decoration: none;
}

.section-header a:hover {
    text-decoration: underline;
}

/* Added container and CSS for inline display */
.green-text-container {
    display: flex;
    justify-content: space-between;
}

</style>
</head>
<body>
<div class="green-text-container">
    <h4 class="green-text" style="margin-right: 1px;">
        <span style="font-weight: bold;">★ 평균 별점이 가장 높은 도서 ☆</span>
        <br>제목: 
        <a href="bookDetail.jsp?bookID=<%=request.getAttribute("bookid")%>" target="right" style="text-decoration: none; color: #007BFF;"><%=request.getAttribute("title") %></a>
        <br>출판사:
        <a href="bookDetail.jsp?bookID=<%=request.getAttribute("bookid")%>" target="right" style="text-decoration: none; color: #007BFF;"><%=request.getAttribute("publisher") %></a>
        <br>저자:
        <a href="bookDetail.jsp?bookID=<%=request.getAttribute("bookid")%>" target="right" style="text-decoration: none; color: #007BFF;"><%=request.getAttribute("authors") %></a>
    </h4>

    <h4 class="green-text">
        <span style="font-weight: bold;">♡ 찜한 유저가 가장 많은 도서 ♥</span>
        <br>제목:
        <a href="bookDetail.jsp?bookID=<%=request.getAttribute("favorBookid")%>" target="right" style="text-decoration: none; color: #007BFF;"><%=request.getAttribute("favorTitle") %></a>
        <br>출판사:
        <a href="bookDetail.jsp?bookID=<%=request.getAttribute("favorBookid")%>" target="right" style="text-decoration: none; color: #007BFF;"><%=request.getAttribute("favorPublisher") %></a>
        <br>저자:
        <a href="bookDetail.jsp?bookID=<%=request.getAttribute("favorBookid")%>" target="right" style="text-decoration: none; color: #007BFF;"><%=request.getAttribute("favorAuthors") %></a>
    </h4>
</div>


   <!-- 통합 검색 -->
   <div style="text-align: right;">
        <form action="bookSearch.jsp" method="GET">
            <label for="searchQuery">검색:</label>
            <input type="text" id="searchQuery" name="query" placeholder="통합 검색">
            <button type="submit">검색</button>
        </form>
    </div>


   <!-- 도서 목록 테이블 -->
   <table class="main-table" border="1">
      <thead>
         <tr>
            <th>번호</th>
            <th>도서 제목</th>
            <th>출판사</th>
            <th>저자</th>
            <th>장르</th>
         </tr>
      </thead>
      
      <%
      
      for(Object o: books) {
         BooksDTO book = (BooksDTO)o;
      %>
         <tr>
         <td style="text-align: center;"><%=book.getBookID() %></td>
         <td><a href="bookDetail.jsp?bookID=<%=book.getBookID()%>" target="right"><%=book.getTitle()%></a></td>
            <td><%=book.getPublisher() %></td>
            <td><%=book.getAuthors() %></td>
            <td><%=book.getGenre() %></td>
         </tr>
      <%
      }
      %>  
   </table>
   
   <!-- 페이징 처리 -->
   <table class="paging-table">
      <tr>
         <td align="center">
            <!-- 처음 이전 링크 -->
            <c:if test="${pg>block}">
               [<a href="bookListPaging.jsp?pg=1">◀◀</a>]
               [<a href="bookListPaging.jsp?pg=${fromPage-1}">◀</a>]      
            </c:if>
            <c:if test="${pg<=block}">
               [<span style="color:gray">◀◀</span>]   
               [<span style="color:gray">◀</span>]
            </c:if>
      
            <!-- 블록 범위 찍기 -->
            <c:forEach begin="${fromPage}" end="${toPage}" var="i">
               <c:if test="${i==pg}">[${i}]</c:if>
               <c:if test="${i!=pg}">
                  [<a href="bookListPaging.jsp?pg=${i}">${i}</a>]
               </c:if>
            </c:forEach>
      
            <!-- 다음, 이후 -->
            <c:if test="${toPage<allPage}">
                  [<a href="bookListPaging.jsp?pg=${toPage+1}">▶</a>]
                  [<a href="bookListPaging.jsp?pg=${allPage}">▶▶</a>]
            </c:if>
            <c:if test="${toPage>=allPage}">
                  [<span style="color:gray">▶</span>]
                  [<span style="color:gray">▶▶</span>]
      
            </c:if>      
         </td>
      </tr>
   </table>
</body>
</html>