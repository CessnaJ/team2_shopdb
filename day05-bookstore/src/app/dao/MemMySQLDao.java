package app.dao;

import java.util.logging.Logger;

import app.dto.Member;

public class MemMySQLDao {	
	Logger log = Logger.getLogger("MemMySQLDao");
	
	public void insert(Member mem) {
		log.info(mem.toString()+"INSERTED ... ");
	}
}
