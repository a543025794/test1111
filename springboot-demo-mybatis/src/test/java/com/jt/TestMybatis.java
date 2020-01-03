package com.jt;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMybatis {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testFind() {
		List<User> userList = userMapper.findAll();
		System.out.println(userList);
	}
	
	/**
	 * 参数说明:queryWrapper表示条件构造器
	 * sql:select*from user;
	 */
	@Test
	public void testSelect() {
		List<User> userList = userMapper.selectList(null);
		System.out.println(userList);
	}
	
	/**
	 * 要求:查询id=1的数据
	 */
	@Test
	public void testFindOne() {
		
		//方法一
		User user = userMapper.selectById(1);
		System.out.println(user);
		
		//方法二
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", 1);
		User user2 = userMapper.selectOne(queryWrapper);
		System.out.println(user2);
	}
	
	/**
	 * 要求:查询age>1 age<150的数据
	 */
	@Test
	public void testFindAge() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.between("age", 1, 150);
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	/**
	 * 3.模糊查询like
	 */
	@Test
	public void testByLike() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("name", "精");
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	/**
	 * 4.查询年龄=18岁或者女神仙age>300 and sex=女
	 */
	
	@Test
	public void testBywhere() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("age", 18).or().ge("age", 300).eq("sex", "女");
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setId(null).setName("苍老师").setAge(18).setSex("女");
		
		userMapper.insert(user);
	}
	
	/**
	 * 将苍老师名称改为林志玲
	 * 年龄改为20
	 * 1.entity 代表需要修改后的数据
	 * 2.updateWrapper
	 */
	@Test
	public void testUpdate() {
		
		User user = new User();
		user.setName("林志玲").setAge(20);
		
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("name", "苍老师");
		userMapper.update(user, updateWrapper);
	}
}
