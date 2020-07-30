package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import jdbc.JdbcUtil;

public class ArticleDao {
	
	public int delete(Connection conn, int no)
			throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("DELETE FROM article "
						+ " WHERE article_no=?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}

	public Article selectById(Connection conn, int no)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn
					.prepareStatement("SELECT * FROM article "
							+ " WHERE article_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Article article = null;
			if (rs.next()) {
				article = convertArticle(rs);
			}
			return article;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public void increaseReadCount(Connection conn, int no)
			throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"UPDATE article SET read_cnt=read_cnt+1 "
						+ " WHERE article_no=? ")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT count(*) FROM article ");
			if (rs.next()) {
				return rs.getInt(1);
			}

			return 0;
		} finally {
			JdbcUtil.close(rs, stmt);
		}
	}

	public List<Article> select(Connection conn, int startRow,
			int size) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn
					.prepareStatement("SELECT * FROM article "
							+ "ORDER BY article_no DESC LIMIT ?, ? ");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);

			rs = pstmt.executeQuery();
			List<Article> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertArticle(rs));
			}
			return result;

		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	private Article convertArticle(ResultSet rs)
			throws SQLException {
		return new Article(rs.getInt("article_no"),
				new Writer(rs.getString("writer_id"),
						rs.getString("writer_name")),
				rs.getString("title"),
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")),
				rs.getInt("read_cnt"));
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public Article insert(Connection conn, Article article)
			throws SQLException {

		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("INSERT INTO "
					+ " article "
					+ " (writer_id, writer_name, title, "
					+ "  regdate, moddate, read_cnt) "
					+ " VALUES (?, ?, ?, ?, ?, 0) ");
			pstmt.setString(1, article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			pstmt.setTimestamp(4,
					toTimestamp(article.getRegDate()));
			pstmt.setTimestamp(5,
					toTimestamp(article.getModifiedDate()));
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(
						"SELECT last_insert_id() ");

				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Article(newNum,
							article.getWriter(),
							article.getTitle(),
							article.getRegDate(),
							article.getModifiedDate(), 0);
				}
			}

			return null;
		} finally {
			JdbcUtil.close(rs, stmt, pstmt);
		}
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public int update(Connection conn, int no, String title)
			throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"UPDATE article SET title=?, moddate=now()"
						+ " WHERE article_no=?")) {
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
}
