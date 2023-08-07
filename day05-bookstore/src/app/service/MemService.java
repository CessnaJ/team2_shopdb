package app.service;

import java.util.logging.Logger;

import app.dao.MemMySQLDao;
import app.dto.Member;

public class MemService {
Logger log = Logger.getLogger("CustService");
	
	MemMySQLDao dao;
	
	public MemService() {
		dao = new MemMySQLDao();
	}
	public void register(Member mem) {
		log.info("Security Check ... ");
		dao.insert(mem);
		log.info("Send Email ... ");
	}
	
}
