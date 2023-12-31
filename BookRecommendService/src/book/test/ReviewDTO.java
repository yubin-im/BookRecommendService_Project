package book.test;

import java.sql.SQLException;
import java.util.Date;

public class ReviewDTO {
	private String reviewContent;
	private Date reviewDate;
	private int rank;
	private String userID;
	private String bookID;
	private int likes;
	
	public ReviewDTO(String reviewContent, Date reviewDate, int rank, String userID, String bookID, int likes) {
		super();
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.rank = rank;
		this.userID = userID;
		this.bookID = bookID;
		this.likes = likes;
	}

	public ReviewDTO() {
		super();
	}
	
	public ReviewDTO(String reviewContent) {
		super();
		this.reviewContent = reviewContent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookID == null) ? 0 : bookID.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewDTO other = (ReviewDTO) obj;
		if (bookID == null) {
			if (other.bookID != null)
				return false;
		} else if (!bookID.equals(other.bookID))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReviewDTO [reviewContent=" + reviewContent + ", reviewDate=" + reviewDate + ", rank=" + rank
				+ ", userID=" + userID + ", bookID=" + bookID + ", likes=" + likes + "]";
	}
	
	public String getReviewContent() {
		return reviewContent;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public int getRank() {
		return rank;
	}

	public String getUserID() {
		return userID;
	}

	public String getBookID() {
		return bookID;
	}

	public int getLikes() {
		return likes;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
}
