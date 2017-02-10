package org.mur.service.user;



import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mur.entity.PageData;
import com.mur.model.user.User;
import com.mur.model.user.UserQuery;
import com.mur.model.user.UserVo;
import com.mur.service.user.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserServiceTest {
	
	@Resource
	private UserService userService;
	
	
	@Test
	public void insertUser(){
		User user = new User();
		user.setCode("test");
		user.setName("test");
		userService.addUser(user);
	}
	
	@Test
	public void findUsers(){
		UserQuery query = new UserQuery();
		query.setRoleId("8cc53cb00ec04e79b5228b76ae0fe011");
//		query.setOrderBy("code asc,name desc");
		PageData pageData = userService.findPage(1, 10, query);
		System.out.println("查询结果:=====================>");
		for(Object obj : pageData.getRows()){
			UserVo userVo = (UserVo) obj;
			System.out.println(userVo.getCode());
		}
	}
}
