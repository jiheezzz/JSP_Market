package Board;

import java.util.List;

public class BoardServiceImpl implements BoardDao{
	BoardDao dao = new BoardDaoImpl();
	@Override
	public void insert(BoardVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public List<BoardVO> select(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.select(vo);
	}


	@Override
	public int tc(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.tc(vo);
	}

	@Override
	public void update(BoardVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void delete(String idx) {
		// TODO Auto-generated method stub
		dao.delete(idx);
	}

	@Override
	public BoardVO edit(String idx) {
		// TODO Auto-generated method stub
		return dao.edit(idx);
	}

	@Override
	public void update_edit(BoardVO vo) {
		// TODO Auto-generated method stub
		dao.update_edit(vo);
	}

	@Override
	public void cnt(String idx) {
		// TODO Auto-generated method stub
		dao.cnt(idx);
	}

}
