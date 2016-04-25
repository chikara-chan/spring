package playplane.test;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import playplane.model.SessionInfo;
import playplane.service.CustomerServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml", "classpath:spring-druid.xml" })
public class ProjectTest {
	@Autowired
	CustomerServiceI customerService;
	
	@Test
	public void test() throws Exception {
		SessionInfo sessionInfo=new SessionInfo();
		sessionInfo.setId("c69ac461-3071-498b-b6d0-dc023edbff4e");
		customerService.editName("陈", sessionInfo);
	}
}
