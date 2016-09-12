/**
 * @author gcs
 * @createdate 2016年8月9日	
 * @version 1.0
 * 
 */
package com.nbl.junit;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nbl.dao.CustBankCardDao;
import com.nbl.dao.CustPersonDao;
import com.nbl.dao.CustUserDao;
import com.nbl.model.CustBankCard;
import com.nbl.model.CustPerson;
import com.nbl.model.PersonAndUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml", "classpath*:/spring/applicationContext-dataSource.xml", "classpath*:/spring/spring-mvc.xml" })
public class TestCustUserCont {
	
	private final static Logger logger = LoggerFactory.getLogger(TestUserInfoQuery.class);
	
	@Resource
	private CustUserDao custUserDao;
	@Resource
	private CustPersonDao custPersonDao;
	@Resource
	private CustBankCardDao custBankDao;
	
	@Test
	public void testCont(){
		logger.info("----------");
		List<CustPerson> list = custPersonDao.listLastMonthAreaCount();
		CustBankCard cb = new CustBankCard();
		String id = "1";
		cb = custBankDao.selectByPrimaryKey(id);
		PersonAndUser pau = custPersonDao.selectByIdentityCardNumber("1");
		System.out.println("++++++++++++++++++"+pau.getRegChanCode());
		
//		int noCount = custUserDao.queryNoMonthCount();
//		logger.info("{} value"+count);
		if (list !=null && list.size()>0) {
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(""+list.get(i).getCityCode()+"==================="+list.get(i).getNum());
			}
		}else{
			list = null;
		}
		logger.info("<<<<<<<{}>>>>>>> value"+list);
	}
}
