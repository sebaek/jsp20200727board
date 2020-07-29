package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import article.model.Article;
import jdbc.JdbcUtil;

public class ArticleDao {
	public Article insert(Connection conn, Article article)
			throws SQLException {

		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("INSERT INTO "
					+ " article "
					+ " (writer_id, writer_name, title, "
					+ "  regdate, moddate, red_cnt) "
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
}
