package member;

import java.util.List;

import Board.BoardVO;

public class MemberServiceImpl implements MemberDao{
	MemberDao dao = new MemberDaoImpl();

	@Override
	public void insert(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public void update(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void delete(String mid) {
		// TODO Auto-generated method stub
		dao.delete(mid);
	}



	@Override
	public MemberVO edit(String mid) {
		// TODO Auto-generated method stub
		return dao.edit(mid);
	}

	@Override
	public List<MemberVO> m_select(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.m_select(vo);
	}

	@Override
	public String login(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.login(vo);
	}

	@Override
	public void cnt(String idx) {
		// TODO Auto-generated method stub
		dao.cnt(idx);
	}

	@Override
	public int tc(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.tc(vo);
	}


}
