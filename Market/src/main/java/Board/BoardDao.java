package Board;

import java.util.List;



public interface BoardDao {
void insert(BoardVO vo);
List<BoardVO> select(BoardVO vo);
int tc(BoardVO vo);

void update(BoardVO vo);
void delete(String idx);
BoardVO edit(String idx);
void update_edit(BoardVO vo);

void cnt(String idx);
}
