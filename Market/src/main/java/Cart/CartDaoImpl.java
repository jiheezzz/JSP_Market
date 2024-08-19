package Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DBConn.DBConnection;

public class CartDaoImpl implements CartDao{
	DBConnection DBConn = DBConnection.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static final String INSERT_SQL="insert into cartTBL(mid,product_id,amount) values(?,?,?)";
	public static final String UPDATE_SQL="update cartTBL set amount=? where cart_id=?";
	public static final String DELETE_SQL="delete from cartTBL where mid=?";
	public static final String ORDER_DELETE_SQL="delete from ordert where orderG=?";
	@Override
	public void insert(CartVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(INSERT_SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getProduct_id());
			pstmt.setString(3, vo.getAmount());
			pstmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace(); // Print the stack trace for debugging
		    }finally {
			DBConn.close(pstmt, conn);
		}
		
	}
	
	@Override
	public void delete(CartVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace(); // Print the stack trace for debugging
		    }finally {
			DBConn.close(pstmt, conn);
		}
		
	}
	@Override
	public void update(CartVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, vo.getAmount());
			pstmt.setString(2, vo.getCart_id());
			pstmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace(); // Print the stack trace for debugging
		    }finally {
			DBConn.close(pstmt, conn);
		}
		
	}
	
	@Override
	public List<HashMap<String, Object>> cartSelect(CartVO vo) {
		List<HashMap<String, Object>> li =new ArrayList<HashMap<String, Object>>();
		try {
		conn = DBConn.getConnection();
		String SQL = "select  p.product_id as pid, product_name, product_price, product_img, cart_id, mid, amount from  product  p  join carttbl c "
				+ " on p.product_id = c.product_id where  mid = ? ";
		pstmt  = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getMid());
		rs = pstmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object>	member = new HashMap<String, Object>();
			member.put("cart_id",rs.getString("cart_id"));
			member.put("mid",rs.getString("mid"));
			member.put("pid",rs.getString("pid"));
			member.put("product_name",rs.getString("product_name"));
			member.put("product_img",rs.getString("product_img"));
			member.put("product_price",rs.getInt("product_price"));
			member.put("amount",rs.getInt("amount"));
			li.add(member);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}
	@Override
	public void orderInsert(OrderVO vo) {
		System.out.println("===> OrderVO vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "INSERT INTO orderT  "
					+ "(orderG, cart_id, mid, product_id, amount, today)"
					+ " VALUES (?,?,?,?,?,sysdate()) ";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getOrderG());
			pstmt.setString(2, vo.getCart_id());
			pstmt.setString(3, vo.getMid());	
			pstmt.setString(4, vo.getProduct_id());	
			pstmt.setString(5, vo.getAmount());	
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	@Override
	public List<HashMap<String, Object>> orderList(OrderVO vo) {
		System.out.println("===> orderList:"+ vo );
		List<HashMap<String, Object>> li =new ArrayList<HashMap<String, Object>>();
		try {
		conn = DBConn.getConnection();
		String SQL = "select  idxOrder,count(*) as tc, orderG, today  from  orderT"
				+ " where mid = ? "
				+ " Group  by orderG, today "
				+ " order by today desc , idxOrder  desc   ";
		pstmt  = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getMid());
		rs = pstmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object>	member = new HashMap<String, Object>();
			member.put("idxOrder",rs.getString("idxOrder"));
			member.put("orderG",rs.getString("orderG"));
			member.put("today",rs.getString("today"));
			member.put("tc",rs.getString("tc"));
			System.out.println("orderList:(put)"+ member );
			li.add(member);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public List<HashMap<String, Object>> editList(OrderVO vo) {
		
		List<HashMap<String, Object>> li =new ArrayList<HashMap<String, Object>>();
		try {
		conn = DBConn.getConnection();
		String SQL = "select  m.mid as mid , mname, mphone, maddr1,maddr2,maddr3,orderG ,p.product_id as pid,"
				+ " product_name, product_price, product_img, o.cart_id, o.amount as camount,o.mid  from  product"
				+ "  p  join  ordert o  join membertbl m on p.product_id = o.product_id and m.mid = o.mid where  orderG= ?";
		pstmt  = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getOrderG());
		rs = pstmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object>	m = new HashMap<String, Object>();
			m.put("mid",rs.getString("mid"));
			m.put("maddr1",rs.getString("maddr1"));
			m.put("maddr2",rs.getString("maddr2"));
			m.put("pid",rs.getString("pid"));
			m.put("maddr3",rs.getString("maddr3"));
			m.put("mphone",rs.getString("mphone"));
			m.put("mname",rs.getString("mname"));
			m.put("product_name",rs.getString("product_name"));
			m.put("product_price",rs.getString("product_price"));
			m.put("product_img",rs.getString("product_img"));
			m.put("camount",rs.getString("camount"));
			m.put("cart_id",rs.getString("cart_id"));
			m.put("orderG",rs.getString("orderG"));
			li.add(m);
		}} catch (Exception e) {
			e.printStackTrace();}
		return li;
	}

	@Override
	public List<HashMap<String, Object>> manager_orderList(OrderVO vo) {
		System.out.println("===> orderList:"+ vo );
		List<HashMap<String, Object>> li =new ArrayList<HashMap<String, Object>>();
		try {
		conn = DBConn.getConnection();
		String SQL = "select  idxOrder,count(*) as tc, orderG, today  from  orderT"
				+ " Group  by orderG, today "
				+ " order by today desc , idxOrder  desc   ";
		pstmt  = conn.prepareStatement(SQL);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object>	member = new HashMap<String, Object>();
			member.put("idxOrder",rs.getString("idxOrder"));
			member.put("orderG",rs.getString("orderG"));
			member.put("today",rs.getString("today"));
			member.put("tc",rs.getString("tc"));
			System.out.println("orderList:(put)"+ member );
			li.add(member);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public void order_delete(OrderVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(ORDER_DELETE_SQL);
			pstmt.setString(1, vo.getOrderG());
			pstmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }finally {
			DBConn.close(pstmt, conn);
		}
		
	}


}
