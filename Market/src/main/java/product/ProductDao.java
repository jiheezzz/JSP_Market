package product;

import java.util.List;

public interface ProductDao {
	void insert(ProductVO vo);
	void update(ProductVO vo);
	void delete(String product_id);
	List<ProductVO> select(ProductVO vo);
	ProductVO edit(String product_id);
	
	void update_edit(ProductVO vo);
	
}
