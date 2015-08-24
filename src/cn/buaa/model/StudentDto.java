package cn.buaa.model;

public class StudentDto {
	private int sid;
	private String sname;
	private String sex;
	private String cname;
	private String spename;
	
	public StudentDto() {
	}
	
	public StudentDto(int sid, String sname, String sex, String cname,
			String spename) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sex = sex;
		this.cname = cname;
		this.spename = spename;
	}

	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSpename() {
		return spename;
	}
	public void setSpename(String spename) {
		this.spename = spename;
	}
	
	
}
