package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User_DataDAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@orcl.cl3ehe0nny5q.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	String user = "scott";
	String password = "tiger333";
	StringBuffer sb = new StringBuffer();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public User_DataDAO() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε�����");
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
		}
	}

	public boolean isExists(String id) {

		sb.setLength(0);
		sb.append("SELECT id ");
		sb.append("FROM USER_DATA ");
		sb.append("WHERE id = ? ");
		boolean isOk = false;

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			isOk = rs.next();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return isOk;

	}

	// ****************************************************
	public boolean isExists1(String nick) {

		sb.setLength(0);
		sb.append("SELECT NICK ");
		sb.append("FROM USER_DATA ");
		sb.append("WHERE NICK = ? ");
		boolean isOk = false;

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();

			isOk = rs.next();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return isOk;

	}

	// ***********************************************************//
	public User_DataVO selectone(String id) {
		User_DataVO vo = null;
		sb.setLength(0);
		sb.append("SELECT id ");
		sb.append("FROM USER_ITEM ");
		sb.append("WHERE id = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			rs.next();
			String Id = rs.getString("id");

			vo = new User_DataVO();
			vo.setId(Id);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return vo;

	}

	// ***********************************************************//
	public User_DataVO selecttwo(String id) {

		User_DataVO vo = null;
		sb.setLength(0);
		sb.append("SELECT money, hp, atk, dis, buck, score, round ");
		sb.append("FROM USER_DATA ");
		sb.append("WHERE id = (?) ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			rs.next();

			int money = rs.getInt("money");
			int hp = rs.getInt("hp");
			int atk = rs.getInt("atk");
			int dis = rs.getInt("dis");
			int buck = rs.getInt("buck");
			int score = rs.getInt("score");
			int round = rs.getInt("round");

			vo = new User_DataVO();

			vo.setMoney(money);
			vo.setHp(hp);
			vo.setAtk(atk);
			vo.setDis(dis);
			vo.setBuck(buck);
			vo.setScore(score);
			vo.setRound(round);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return vo;

	}

	// ******************************************************
	   public void updateAll(String id, int hp, int atk, int money, int buck, int dis, int score, int round) {
		      sb.setLength(0);
		      sb.append("update user_data ");
		      sb.append("set atk = ?, hp = ?, money = ?,buck = ?, dis = ?, score = ?, round = ? ");
		      sb.append("where id = ?");

		      try {
		         pstmt = conn.prepareStatement(sb.toString());
		         pstmt.setInt(1, atk);
		         pstmt.setInt(2, hp);
		         pstmt.setInt(3, money);
		         pstmt.setInt(4, buck);
		         pstmt.setInt(5, dis);
		         pstmt.setInt(6, score);
		         pstmt.setInt(7, round);
		         pstmt.setString(8, id);
		         
		         
		         pstmt.executeUpdate();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }

		   }

	// ***********************************************************//
		public User_DataVO selectnick(String id) {
			User_DataVO vo = null;
			sb.setLength(0);
			sb.append("SELECT nick ");
			sb.append("FROM USER_data ");
			sb.append("WHERE id = ? ");

			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				rs.next();
				String nick = rs.getString("nick");

				vo = new User_DataVO();
				vo.setNick(nick);
			} catch (SQLException e) {

				e.printStackTrace();
			}

			return vo;

		}

		// ******************��ũ���� ����Ұ� �ҷ�����*************

		public ArrayList<User_DataVO> selectrank() {
			ArrayList<User_DataVO> list = new ArrayList<User_DataVO>();
			User_DataVO vo = null;

			// 4. sql �� �ۼ�
			sb.setLength(0);
			sb.append("SELECT nick, round, atk, score from( ");
			sb.append("select nick, round, atk, score ");
			sb.append("from user_data ");
			sb.append("order by score desc ) ");
			sb.append("where rownum < 6 ");

			// 5. �ڹٴ� ��ü�̱� ������ �����Ϸ��� "���嵵 ��ü"�� ��� �Ѵ�
			try {
				pstmt = conn.prepareStatement(sb.toString());

				// 1�� ����ǥ����, �μ�

				rs = pstmt.executeQuery();

				while (rs.next()) {
					String nick = rs.getString("nick");
					int round = rs.getInt("round");
					int atk = rs.getInt("atk");
					int score = rs.getInt("score");

					// vo ��ü ����, ���ͷ� ���� ��´� KK)
					vo = new User_DataVO();
					vo.setNick(nick);
					vo.setRound(round);
					vo.setAtk(atk);
					vo.setScore(score);
					list.add(vo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// �� �ʿ�� �ϴ� ������ �����ٰ� vo �� 1))) KK))
			return list;
		}
		// ***********************************************************//
	   
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
