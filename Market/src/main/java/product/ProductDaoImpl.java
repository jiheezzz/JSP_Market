package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBConn.DBConnection;

public class ProductDaoImpl implements ProductDao{
	DBConnection DBConn = DBConnection.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static final String INSERT_SQL="insert into product(product_id,product_name,product_price,product_img,product_etc) values(?,?,?,?,?)";
	public static final String UPDATE_SQL="update product set product_name=?,product_price=?,product_img=?,product_etc=? where product_id=?";
	public static final String DELETE_SQL="delete from product where product_id=?";
	public static final String UPDATE_EDIT_SQL="update product set product_name=?,product_price=?,product_etc=? where product_id=?";
	public static final String SELECT_SQL="select * from product order by product_id desc";
	public static final String EDIT_SQL="select * from product where product_id=? order by product_id";
	
	@Override
	public void insert(ProductVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(INSERT_SQL);
			pstmt.setString(1, vo.getProduct_id());
			pstmt.setString(2, vo.getProduct_name());
			pstmt.setInt(3, vo.getProduct_price());
			pstmt.setString(4, vo.getProduct_img());
			pstmt.setString(5, vo.getProduct_etc());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public void update(ProductVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, vo.getProduct_name());
			pstmt.setInt(2, vo.getProduct_price());
			pstmt.setString(3, vo.getProduct_img());
			pstmt.setString(4, vo.getProduct_etc());
			pstmt.setString(5, vo.getProduct_id());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public void delete(String product_id) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, product_id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public List<ProductVO> select(ProductVO vo) {
		List<ProductVO> li = new ArrayList<ProductVO>();
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(SELECT_SQL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductVO m = new ProductVO();
				m.setProduct_id(rs.getString("product_id"));
				m.setProduct_name(rs.getString("product_name"));
				m.setProduct_img(rs.getString("product_img"));
				m.setProduct_price(rs.getInt("product_price"));
				m.setProduct_etc(rs.getString("product_etc"));
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
	public ProductVO edit(String product_id) {
		ProductVO m = new ProductVO();
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(EDIT_SQL);
			pstmt.setString(1, product_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m.setProduct_id(rs.getString("product_id"));
				m.setProduct_name(rs.getString("product_name"));
				m.setProduct_img(rs.getString("product_img"));
				m.setProduct_price(rs.getInt("product_price"));
				m.setProduct_etc(rs.getString("product_etc"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		return m;
	}

	@Override
	public void update_edit(ProductVO vo) {
		try {
			conn=DBConn.getConnection();
			pstmt=conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, vo.getProduct_name());
			pstmt.setInt(2, vo.getProduct_price());
			pstmt.setString(3, vo.getProduct_etc());
			pstmt.setString(4, vo.getProduct_id());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBConn.close(pstmt, conn);
		}
		
	}

}
