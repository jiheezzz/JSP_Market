package Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBConn.DBConnection;
import product.ProductVO;

public class BoardDaoImpl implements BoardDao{
	DBConnection DBConn = DBConnection.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static final String INSERT_SQL="insert into board(name,title,img,etc,cnt) values(?,?,?,?,1)";
	public static final String SELECT_SQL="select * from board order by day desc limit ?,?";
	private static final String SELECT_NAME_SQL = "select * from board where name like ? limit ?,?";
	private static final String SELECT_TITLE_SQL = "select * from board where title like ? limit ?,?";
	private static final String SQL_TC = "select count(*) as tc from board";
	private static final String SQL_TC_NAME = "select count(*) as tc from board  where name like ? ";
	private static final String SQL_TC_TITLE = "select count(*) as tc from board  where title like ? ";
	public static final String UPDATE_SQL="update board set name=?,title=?,img=?,etc=?,day=? where idx=?";
	public static final String DELETE_SQL="delete from board where idx=?";
	public static final String UPDATE_EDIT_SQL="update board set name=?,title=?,etc=?,day=? where idx=?";
	public static final String EDIT_SQL="select * from board where idx=? order by idx";
	public static final String CNT_SQL="update board set cnt=cnt+1 where idx=?";
	@Override
	public void insert(BoardVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(INSERT_SQL);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getImg());
			pstmt.setString(4, vo.getEtc());
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public List<BoardVO> select(BoardVO vo) {
		List<BoardVO> li = new ArrayList<BoardVO>();
		try {conn=DBConn.getConnection();
			if (vo.getStr1() == null || vo.getStr1().equals("null")) {	
				pstmt = conn.prepareStatement(SELECT_SQL);
				pstmt.setInt(1, vo.getXidx());
				pstmt.setInt(2, vo.getPageSize());
				rs=pstmt.executeQuery();
			}else if(vo.getStr1().equals("name")) {
				pstmt = conn.prepareStatement(SELECT_NAME_SQL);
				pstmt.setString(1, "%" + vo.getStr2() +"%");
				pstmt.setInt(2, vo.getXidx());
				pstmt.setInt(3, vo.getPageSize());
				rs=pstmt.executeQuery();
			}else if(vo.getStr1().equals("title")) {
				pstmt = conn.prepareStatement(SELECT_TITLE_SQL);
				pstmt.setString(1, "%" + vo.getStr2() +"%");
				pstmt.setInt(2, vo.getXidx());
				pstmt.setInt(3, vo.getPageSize());
				rs=pstmt.executeQuery();
			}while(rs.next()) {BoardVO m = new BoardVO();
				m.setIdx(rs.getString("idx"));
				m.setName(rs.getString("name"));
				m.setTitle(rs.getString("title"));
				m.setImg(rs.getString("img"));
				m.setEtc(rs.getString("etc"));
				m.setCnt(rs.getInt("cnt"));
				m.setDay(rs.getString("day"));
				li.add(m);
			}} catch (Exception e) {}finally {DBConn.close(rs,pstmt, conn);
		}return li;}

	@Override
	public int tc(BoardVO vo) {
		int tc =0;
		try {
			conn=DBConn.getConnection();
			if (vo.getStr1() == null || vo.getStr1().equals("null")) {	
				pstmt= conn.prepareStatement(SQL_TC);
				rs=pstmt.executeQuery();
			}else if(vo.getStr1().equals("name")) {
				pstmt = conn.prepareStatement(SQL_TC_NAME);
				rs=pstmt.executeQuery();
			}else if(vo.getStr1().equals("title")) {
				pstmt = conn.prepareStatement(SQL_TC_TITLE);
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

	@Override
	public void update(BoardVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getImg());
			pstmt.setString(4, vo.getEtc());
			pstmt.setString(5, vo.getDay());
			pstmt.setString(6, vo.getIdx());
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public void delete(String idx) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, idx);
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public BoardVO edit(String idx) {
		BoardVO m = new BoardVO();
		try {
			conn=DBConn.getConnection();
			pstmt = conn.prepareStatement(EDIT_SQL);
			pstmt.setString(1, idx);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				m.setIdx(rs.getString("idx"));
				m.setName(rs.getString("name"));
				m.setTitle(rs.getString("title"));
				m.setImg(rs.getString("img"));
				m.setEtc(rs.getString("etc"));
				m.setCnt(rs.getInt("cnt"));
				m.setDay(rs.getString("day"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(rs,pstmt, conn);
		}
		return m;
	}

	@Override
	public void update_edit(BoardVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(UPDATE_EDIT_SQL);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getEtc());
			pstmt.setString(4, vo.getDay());
			pstmt.setString(5, vo.getIdx());
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
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

}
