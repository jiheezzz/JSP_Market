package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Board.BoardVO;
import DBConn.DBConnection;

public class MemberDaoImpl implements MemberDao{
	DBConnection DBConn = DBConnection.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static final String INSERT_SQL="insert into memberTBL(mid,mpassword1,mpassword2,mphone,maddr1,maddr2,maddr3,mname ,mage,mgender)"
			+ " values(?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_SQL="update memberTBL set mphone=?,maddr1=?,maddr2=?,maddr3=?,mgrade=?,metc=?,mpassword1=?,mpassword2=? where mid=?";
	public static final String DELETE_SQL="delete from memberTBL where mid=?";
	public static final String EDIT_SQL="select * from memberTBL where mid=?";
	public static final String M_SELECT_SQL="select * from memberTBL order by mgrade desc limit ?,?";
	private static final String SELECT_NAME_SQL = "select * from memberTBL where mname like ? limit ?,?";
	private static final String SELECT_MGRADE_SQL = "select * from memberTBL where mgrade like ? limit ?,?";
	private static final String LOGIN_SQL="select * from memberTBL where mid=? and mpassword1=?";
	private static final String SQL_TC = "select count(*) as tc from memberTBL ";
	private static final String SQL_TC_NAME = "select count(*) as tc from memberTBL  where mname like ? ";
	private static final String SQL_TC_MGRADE = "select count(*) as tc from memberTBL  where mgrade like ?";
	public static final String CNT_SQL="update board set cnt=cnt+1 where idx=?";

	@Override
	public void insert(MemberVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(INSERT_SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpassword1());
			pstmt.setString(3, vo.getMpassword2());
			pstmt.setString(4, vo.getMphone());
			pstmt.setString(5, vo.getMaddr1());
			pstmt.setString(6, vo.getMaddr2());
			pstmt.setString(7, vo.getMaddr3());
			pstmt.setString(8, vo.getMname());
			pstmt.setString(9, vo.getMage());
			pstmt.setString(10, vo.getMgender());
			pstmt.executeUpdate();
			 int rowsInserted = pstmt.executeUpdate();
		        if (rowsInserted > 0) {
		            System.out.println("A new member was inserted successfully!");
		        }
		    } catch (Exception e) {
		        e.printStackTrace(); // Print the stack trace for debugging
		    }finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public void update(MemberVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, vo.getMphone());
			pstmt.setString(2, vo.getMaddr1());
			pstmt.setString(3, vo.getMaddr2());
			pstmt.setString(4, vo.getMaddr3());
			pstmt.setString(5, vo.getMgrade());
			pstmt.setString(6, vo.getMetc());
			pstmt.setString(7, vo.getMid());
			pstmt.setString(8, vo.getMpassword1());
			pstmt.setString(9, vo.getMpassword2());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public void delete(String mid) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}


	@Override
	public MemberVO edit(String mid) {
		MemberVO m = new MemberVO();
		System.out.println("===>"+mid);
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(EDIT_SQL);
			pstmt.setString(1, mid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m.setMid(rs.getString("mid"));
				m.setMaddr1(rs.getString("maddr1"));
				m.setMaddr2(rs.getString("maddr2"));
				m.setMaddr3(rs.getString("maddr3"));
				m.setMpassword1(rs.getString("mpassword1"));
				m.setMpassword2(rs.getString("mpassword2"));
				m.setMphone(rs.getString("mphone"));
				m.setMage(rs.getString("mage"));
				m.setMetc(rs.getString("metc"));
				m.setMgender(rs.getString("mgender"));
				m.setMname(rs.getString("mname"));
				m.setMgrade(rs.getString("mgrade"));
				System.out.println("===>"+m);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		return m;
	}

	@Override
	public List<MemberVO> m_select(MemberVO vo) {
		List<MemberVO> li = new ArrayList<MemberVO>();
		try {
			conn=DBConn.getConnection();
			if (vo.getStr1() == null || vo.getStr1().equals("null")) {	
				pstmt=conn.prepareStatement(M_SELECT_SQL);
				pstmt.setInt(1, vo.getXidx());
				pstmt.setInt(2, vo.getPageSize());
				rs=pstmt.executeQuery();
				
			}else if(vo.getStr1().equals("mname")) {
				pstmt = conn.prepareStatement(SELECT_NAME_SQL);
				pstmt.setString(1, "%" + vo.getStr2() +"%");
				pstmt.setInt(2, vo.getXidx());
				pstmt.setInt(3, vo.getPageSize());
				rs=pstmt.executeQuery();
			}else if(vo.getStr1().equals("mgrade")) {
				pstmt = conn.prepareStatement(SELECT_MGRADE_SQL);
				pstmt.setString(1, "%" + vo.getStr2() +"%");
				pstmt.setInt(2, vo.getXidx());
				pstmt.setInt(3, vo.getPageSize());
				rs=pstmt.executeQuery();
			}
			while(rs.next()) {
				MemberVO m = new MemberVO();
				m.setMid(rs.getString("mid"));
				m.setMaddr1(rs.getString("maddr1"));
				m.setMaddr2(rs.getString("maddr2"));
				m.setMaddr3(rs.getString("maddr3"));
				m.setMpassword1(rs.getString("mpassword1"));
				m.setMpassword2(rs.getString("mpassword2"));
				m.setMphone(rs.getString("mphone"));
				m.setMage(rs.getString("mage"));
				m.setMetc(rs.getString("metc"));
				m.setMgender(rs.getString("mgender"));
				m.setMname(rs.getString("mname"));
				m.setMgrade(rs.getString("mgrade"));
				li.add(m);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		return li;
	}

	@Override
	public String login(MemberVO vo) {
		String m ="";
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(LOGIN_SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpassword1());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(BCrypt.checkpw(vo.getMpassword2(), rs.getString("mpassword2"))) {
					m=rs.getString("mid");
				}else {
				m=rs.getString("");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(rs,pstmt, conn);
		}
		return m;
	}

	@Override
	public void cnt(String idx) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(CNT_SQL);
			pstmt.setString(1, idx);
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public int tc(MemberVO vo) {
		int tc =0;
		try {
			conn=DBConn.getConnection();
			if (vo.getStr1() == null || vo.getStr1().equals("null")) {	
				pstmt= conn.prepareStatement(SQL_TC);
				rs=pstmt.executeQuery();
			}else if(vo.getStr1().equals("name")) {
				pstmt = conn.prepareStatement(SQL_TC_NAME);
				pstmt.setString(1, "%" + vo.getStr2() +"%");
				rs=pstmt.executeQuery();
			}else if(vo.getStr1().equals("title")) {
				pstmt = conn.prepareStatement(SQL_TC_MGRADE);
				pstmt.setString(1, "%" + vo.getStr2() +"%");
				rs=pstmt.executeQuery();
			}
			if(rs.next()) {
				tc=rs.getInt("tc");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(rs,pstmt, conn);
		}
		
		return tc;
	}






	
}
