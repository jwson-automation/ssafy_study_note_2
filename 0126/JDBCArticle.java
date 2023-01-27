
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JDBCArticle {
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://127.0.0.1:3306/ws_board?serverTimezone=Asia/Seoul&useUniCode=yes&characterEncoding=UTF-8";
	static final String ID = "ssafy";
	static final String PASSWORD = "ssafy";

	public static void main(String[] args) {
		JDBCArticle test = new JDBCArticle();

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArticleDto dto = new ArticleDto("홍길동", "제목입력", "내용입력");
		test.insertArticle(dto);
		System.out.println("========================");

		System.out.println(test.selectAllArticle());
		System.out.println("========================");

		test.updateArticle(1, new ArticleDto("홍길동", "제목수정", "내용수정"));
		test.selectArticle(1);
		System.out.println("========================");

		test.deleteArticle(1);
		System.out.println(test.selectAllArticle());
		System.out.println("========================");

	}

	private Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, ID, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;

	}

	private int insertArticle(ArticleDto dto) {
		int result = -1;
		String sql = "insert into article (username, subject, content) value (?,?,?)";

		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setObject(1, dto.getUsername());
			pstmt.setObject(2, dto.getSubject());
			pstmt.setObject(3, dto.getContent());

			System.out.println("insert success");

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	private List<ArticleDto> selectAllArticle() {
		List<ArticleDto> articles = new ArrayList<>();
		
		String sql = "select * from article";

		try (Connection con = getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(sql);) {

			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			
			while (rs.next()) {
				System.out.println(new ArticleDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
				
				articles.add(
						new ArticleDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
			}
		} catch (SQLException e) {
			System.err.println("StampDao select중 에러 발생");
		}

		return articles;

	}

	private ArticleDto selectArticle(int idx) {

		ArticleDto selectedArticle = new ArticleDto("null","null","null");
		String sql = "select * from article where idx = ?";

		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, 4);

			ResultSet rs = pstmt.executeQuery();
				
			rs.next();
			
			selectedArticle = new ArticleDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getDate(5));
			
			System.out.println("selectUser" + selectedArticle);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectedArticle;

	}

	private int updateArticle(int idx, ArticleDto dto) {

		int updatedUserId = -1;
		String sql = "update article set username = ?, subject = ?, content = ? where idx = ?";

		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setObject(1, dto.getUsername());
			pstmt.setObject(2, dto.getSubject());
			pstmt.setObject(3, dto.getContent());
			pstmt.setObject(4, idx);
			
			updatedUserId = pstmt.executeUpdate();

			System.out.println("update success");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updatedUserId;

	}

	private int deleteArticle(int idx) {

		int result = -1;

		String sql = "delete from article where idx = ?";

		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
			System.out.println("delete success");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

}