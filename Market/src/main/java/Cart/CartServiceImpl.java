package Cart;

import java.util.HashMap;
import java.util.List;

public class CartServiceImpl implements CartDao{
	CartDao dao = new CartDaoImpl();
	@Override
	public void insert(CartVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public void delete(CartVO vo) {
		// TODO Auto-generated method stub
		dao.delete(vo);
	}
	@Override
	public void update(CartVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}
	
	@Override
	public List<HashMap<String, Object>> cartSelect(CartVO vo) {
		// TODO Auto-generated method stub
		return dao.cartSelect(vo);
	}
	@Override
	public void orderInsert(OrderVO vo) {
		// TODO Auto-generated method stub
		dao.orderInsert(vo);
	}
	@Override
	public List<HashMap<String, Object>> orderList(OrderVO vo) {
		// TODO Auto-generated method stub
		return dao.orderList(vo);
	}

	@Override
	public List<HashMap<String, Object>> editList(OrderVO vo) {
		// TODO Auto-generated method stub
		return dao.editList(vo);
	}

	@Override
	public List<HashMap<String, Object>> manager_orderList(OrderVO vo) {
		// TODO Auto-generated method stub
		return dao.manager_orderList(vo);
	}

	@Override
	public void order_delete(OrderVO vo) {
		// TODO Auto-generated method stub
		dao.order_delete(vo);
	}



	
}
