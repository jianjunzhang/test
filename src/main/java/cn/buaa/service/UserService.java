package cn.buaa.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.buaa.dao.IUserDao;
import cn.buaa.model.TUser;
@Service("userService")
public class UserService implements IUserService {
	private static final Logger logger = Logger.getLogger(UserService.class);

	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(TUser user) {
		userDao.add(user);
	}

}
