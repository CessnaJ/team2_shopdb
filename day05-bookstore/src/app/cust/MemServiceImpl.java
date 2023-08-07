package app.cust;

import java.util.List;
import java.util.Optional;

import app.dto.Member;
import app.frame.DaoFrame;
import app.frame.ServiceFrame;

public class MemServiceImpl implements ServiceFrame<Long, Member>{
	DaoFrame<Long, Member> dao;
	
	public MemServiceImpl() {
		dao = new MemDaoImpl();
	}
	
	@Override
	public int register(Member v) throws Exception {
		return dao.insert(v);
	}

	@Override
	public int modify(Member v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Long k) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member get(Long k) throws Exception {
		Optional<Member> result = dao.select(k);
		if(result.isEmpty()) {
			throw new Exception("조회내용이없음");
		}
		return result.get();
	}

	@Override
	public List<Member> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
