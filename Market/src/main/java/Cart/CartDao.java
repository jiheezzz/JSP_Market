package Cart;

import java.util.HashMap;
import java.util.List;

public interface CartDao {
	void insert(CartVO vo);
	void delete(CartVO vo);
	void update(CartVO vo);
	List<HashMap<String, Object>>  cartSelect(CartVO vo);
	
	void  orderInsert(OrderVO vo);   
	void order_delete(OrderVO vo);
	List<HashMap<String, Object>>  orderList(OrderVO vo);
	List<HashMap<String, Object>>  manager_orderList(OrderVO vo);
	List<HashMap<String, Object>> editList(OrderVO vo);
	
}
