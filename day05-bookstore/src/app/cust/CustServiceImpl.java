//package app.cust;
//
//import java.util.List;
//
//import app.dto.Cust;
//import app.frame.DaoFrame;
//import app.frame.ServiceFrame;
//
//public class CustServiceImpl implements ServiceFrame<String, Cust> {
//
//	DaoFrame<String, Cust> dao;
//	
//	public CustServiceImpl() {
//		dao = new CustDaoImpl();
//	}
//	
//	@Override
//	public int register(Cust v) throws Exception {
//		
//		return dao.insert(v); 
//	}
//
//	@Override
//	public int modify(Cust v) throws Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int remove(String k) throws Exception {
//		
//		return dao.delete(k);
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Cust get(String k) throws Exception {
//		
//		return dao.select(k);
//	}
//
//	@Override
//	public List<Cust> get() throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
