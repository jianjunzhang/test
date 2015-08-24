package cn.buaa.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.buaa.model.TUser;
import cn.buaa.service.IUserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport{
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	private String id;
	private String name;
	private String address;
	private String createDate;
	private String updateDate;
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/*public String test(){
		logger.info("hehe");
		return SUCCESS;
	}
	public void test123(){
		logger.info("hehe");
	}
	*/
	public String add(){
		/*TUser user = new TUser(20, "www", "多得多", new Date(), new Date(), "dd");
		userService.add(user);
		logger.info("添加5号用户成功");*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TUser user = null;
		try {
			Date create = sdf.parse(createDate);
			Date update = sdf.parse(updateDate);
			user = new TUser(Integer.parseInt(id),name, address, create, update, remark);
		} catch (ParseException e) {
			logger.error("日期转换异常", e);
			e.printStackTrace();
		}
		userService.add(user);
		logger.info("提交成功！！！");
		ActionContext.getContext().put("url", "/welcome.jsp");
		return "redirect";
		
	}
	public String addInput(){
		return SUCCESS;
	}
	
}
