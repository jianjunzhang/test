package cn.buaa.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user")
public class TUser implements java.io.Serializable {

	private static final long serialVersionUID = -8433508190071088482L;
	private Integer id;
	private String name;
	private String address;
	private Date createDate;
	private Date updateDate;
	private String remark;

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TUser(Integer id, String name, String address, Date createDate,
			Date updateDate, String remark) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remark = remark;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", length = 10)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", length = 10)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public TUser(String name, String address, Date createDate, Date updateDate,
			String remark) {
		super();
		this.name = name;
		this.address = address;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.remark = remark;
	}
	

}