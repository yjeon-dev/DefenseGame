package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@orcl.cl3ehe0nny5q.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	String user = "scott";
	String password = "tiger333";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	ProjectDAO vo = null;

	public ProjectDAO() {

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			System.out.println("DB fail");
		}

	}

	public boolean isExists(String id, String pw) {

		sb.setLength(0);
		sb.append("SELECT id, pw ");
		sb.append("FROM USER_DATA ");
		sb.append("WHERE id = ? AND pw = ? ");

		boolean isOk = false;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			isOk = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isOk;

	}

	// ********************************************
	public void insertOne(String id, String name, String pwd, int money, int hp, int atk, int dis, int buck, int score,
			int round) {
		sb.setLength(0);
		sb.append("INSERT INTO USER_DATA ");
		sb.append("VALUES (?,?,?, 100, 1000, 10, 0, 0, 0, 1) ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);

			rs = pstmt.executeQuery();

			rs.next();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void close() {

		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
