package Test;


public class TestServiceImpl implements TestDao{

	@Override
	public void insert(TestVO vo) {
		// TODO Auto-generated method stub
		System.out.println("===>"+vo);
	}

	

}
