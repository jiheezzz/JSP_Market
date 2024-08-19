package product;

import java.util.List;

public class ProductServiceImpl implements ProductDao{
	ProductDao dao = new ProductDaoImpl();

	@Override
	public void insert(ProductVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public void update(ProductVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void delete(String product_id) {
		// TODO Auto-generated method stub
		dao.delete(product_id);
	}

	@Override
	public List<ProductVO> select(ProductVO vo) {
		// TODO Auto-generated method stub
		return dao.select(vo);
	}

	@Override
	public ProductVO edit(String product_id) {
		// TODO Auto-generated method stub
		return dao.edit(product_id);
	}

	@Override
	public void update_edit(ProductVO vo) {
		// TODO Auto-generated method stub
		dao.update_edit(vo);
	}

}
