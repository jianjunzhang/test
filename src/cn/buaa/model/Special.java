package cn.buaa.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="t_special")
public class Special {
	private int id;
	private String name;
	private String type;
	private Set<Classroom> clas;
	public Special() {
	}
	public Special(String name, String type) {
		this.name = name;
		this.type = type;
	}
	public Special(int id) {
		this.id = id;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@OneToMany(mappedBy="special")
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Classroom> getClas() {
		return clas;
	}
	public void setClas(Set<Classroom> clas) {
		this.clas = clas;
	}
	
	
}
