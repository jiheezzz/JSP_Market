package member;

import java.util.List;

import Board.BoardVO;

public interface MemberDao {
	void insert(MemberVO vo);
	void update(MemberVO vo);
	void delete(String mid);
	MemberVO edit(String mid);
	List<MemberVO> m_select(MemberVO vo);

	String login(MemberVO vo);
	void cnt(String idx);
	int tc(MemberVO vo);
}
