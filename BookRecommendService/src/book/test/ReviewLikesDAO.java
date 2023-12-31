package book.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReviewLikesDAO {
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "hr";
	private static String password = "hr";
	private static final String jdbcclass = "oracle.jdbc.OracleDriver";
	private ConnectionPool pool;
	
	public ReviewLikesDAO() {
		super();
		try {
			Class.forName(jdbcclass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC 드라이버를 찾을 수 없습니다.");
		}
		try {
			pool = ConnectionPool.getInstance(url, user, password, 3, 5, true, 500);
			System.out.println("연결되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB 연결 오류");
		}
	}
	
	/**
	 * 리뷰 좋아요 기능 메서드 
	 * @param input
	 * @return
	 * @throws SQLException
	 */
	public int insert(ReviewLikesDTO input) throws SQLException {
		String sql = "insert into ReviewLikes(userID, bookID, likeUserID)\r\n" + 
				"	values( "
				+ "'"+input.getUserID()+"',"
				+ "'"+input.getBookID()+"', "
				+ "'"+input.getLikeUserID()
				+"')";
		Connection conn = pool.getConnection(); 
		Statement stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		
		stmt.close();
		pool.releaseConnection(conn);
		return result;
	}
	
	/**
	 * 리뷰 좋아요 체크 메서드
	 * @param input
	 * @return
	 * @throws SQLException
	 */
	public boolean check(ReviewLikesDTO input) throws SQLException {
		String sql = "select likeuserid from ReviewLikes where userid = '" + input.getUserID() + "'and bookID = '" + input.getBookID() + "'";
		Connection conn = pool.getConnection(); 
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		
		ArrayList<ReviewLikesDTO> reviewLikes = new ArrayList();
		ReviewLikesDTO reviewlike = null;
		boolean bool = true;
		
		while(result.next()) {
			if(input.getLikeUserID().equals(result.getString("likeUserID"))) {
				bool = false;
			}
		}
		
		result.close();
		stmt.close();
		pool.releaseConnection(conn);
		return bool;
	}
	
	/**
	 * 리뷰 좋아요 카운트 메서드(미사용)
	 * @param input
	 * @return
	 * @throws SQLException
	 */
	public int count(ReviewLikesDTO input) throws SQLException {
		String sql = "select likeuserid from ReviewLikes where userid = '" + input.getUserID() + "'and bookID = '" + input.getBookID() + "'";
		Connection conn = pool.getConnection(); 
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		
		ArrayList<ReviewLikesDTO> reviewLikes = new ArrayList();
		ReviewLikesDTO reviewlike = null;
		
		while(result.next()) {
			reviewlike = new ReviewLikesDTO(result.getString("likeUserID"));
			reviewLikes.add(reviewlike);
		}
		
		result.close();
		stmt.close();
		pool.releaseConnection(conn);
		return reviewLikes.size();
	}
	
	/**
	 * 리뷰 좋아요 삭제 메서드
	 * @param input
	 * @return
	 * @throws SQLException
	 */
	public int delete(ReviewLikesDTO input) throws SQLException {
		String sql = "delete from ReviewLikes where userid = '" + input.getUserID() + "'and bookID = '" + input.getBookID()  + "'and likeUserID = '" + input.getLikeUserID() + "'";
		Connection conn = pool.getConnection(); 
		Statement stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);
		
		stmt.close();
		pool.releaseConnection(conn);
		return result;
	}
	
	/**
	 * 좋아요 수를 가장 많이 받은 리뷰 리턴 메서드
	 * @return
	 * @throws SQLException
	 */
	public String bestReview(String input) throws SQLException {
		String bestReview = "";
		String sql = "SELECT reviewcontent FROM reviews WHERE likes = (SELECT MAX(likes) FROM reviews WHERE bookid = '" + input + "') AND bookid = '" + input + "'";		
		
		Connection conn = pool.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		
		if (result.next()) {
			bestReview = result.getString(1);
		}

		result.close();
		stmt.close();
		pool.releaseConnection(conn);
		return bestReview;
	}
	
	/***
	 * 좋아요 수를 가장 많이 받은 리뷰의 도서 제목 리턴 메서드
	 * @return
	 * @throws SQLException
	 */
//	public String bestReviewTitle() throws SQLException {
//		String bestReviewBookID = "";
//		String bestReviewTitle = "";
//		String sql = "SELECT bookid FROM reviews WHERE likes = (SELECT MAX(likes) FROM reviews)";
//		Connection conn = pool.getConnection();
//		Statement stmt = conn.createStatement();
//		ResultSet result = stmt.executeQuery(sql);
//		
//		if (result.next()) {
//			bestReviewBookID = result.getString(1);
//		}
//		
//		sql = "select title from books where bookid = '" + bestReviewBookID + "'";
//		result = stmt.executeQuery(sql);
//		
//		if (result.next()) {
//			bestReviewTitle = result.getString(1);
//		}
//		
//		result.close();
//		stmt.close();
//		pool.releaseConnection(conn);
//		return bestReviewTitle;
//	}
	
	/***
	 * 좋아요 수를 가장 많이 받은 리뷰 도서의 bookid 리턴
	 * @return
	 * @throws SQLException
	 */
//	public String bestReviewBookID() throws SQLException {
//		String bestReviewBookID = "";
//		String sql = "SELECT bookid FROM reviews WHERE likes = (SELECT MAX(likes) FROM reviews)";
//		Connection conn = pool.getConnection();
//		Statement stmt = conn.createStatement();
//		ResultSet result = stmt.executeQuery(sql);
//		
//		if (result.next()) {
//			bestReviewBookID = result.getString(1);
//		}
//		
//		result.close();
//		stmt.close();
//		pool.releaseConnection(conn);
//		return bestReviewBookID;
//	}
	
}
