package cn.buaa.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name="t_classroom")
public class Classroom {
	private int id;
	private String name;
	private int grade;
	private Set<Student> stus;
	private Special special;
	public Classroom() {
	}
	public Classroom(String name, int grade, Special special) {
		this.name = name;
		this.grade = grade;
		this.special = special;
	}
	public Classroom(int id) {
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@OneToMany(mappedBy="classroom")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@Fetch(FetchMode.SUBSELECT)
	public Set<Student> getStus() {
		return stus;
	}
	public void setStus(Set<Student> stus) {
		this.stus = stus;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="spe_id")
	public Special getSpecial() {
		return special;
	}
	public void setSpecial(Special special) {
		this.special = special;
	}
	
	
	
}
