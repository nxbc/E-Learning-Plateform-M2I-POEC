package com.m2i.elearn.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Seb
 * Classe permettant l'accès à la table ELearning.courses
 * 
 */
@Entity
@Table(name="courses")
@NamedQueries(value={
		@NamedQuery(name="CourseJPA.FIND_ALL_ORDER_BY_ID",
				    query="SELECT e FROM CourseJPA e ORDER BY e.idUser, e.idCourse")		
})
public class CourseJPA {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_course")
	private Integer idCourse; 
		
	@NotNull
	@Column(name="id_user")
	private Integer idUser;
	
	@NotNull
	@Column(name="category_course")
	private String categoryCourse ;
	
	@NotNull
	@Column(name="title_course")
	private String titleCourse ;
	
	@NotNull
	@Column(name="descript_course")
	private String descriptCourse ;
	
	@Column(name="skills_course")
	private String skillsCourse  ;
	
	@Column(name="tags_course")
	private String tagsCourse;
	
	@Column(name="required_course")
	private String requiredCourse;

	public Integer getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getCategoryCourse() {
		return categoryCourse;
	}

	public void setCategoryCourse(String categoryCourse) {
		this.categoryCourse = categoryCourse;
	}

	public String getTitleCourse() {
		return titleCourse;
	}

	public void setTitleCourse(String titleCourse) {
		this.titleCourse = titleCourse;
	}

	public String getDescriptCourse() {
		return descriptCourse;
	}

	public void setDescriptCourse(String descriptCourse) {
		this.descriptCourse = descriptCourse;
	}

	public String getSkillsCourse() {
		return skillsCourse;
	}

	public void setSkillsCourse(String skillsCourse) {
		this.skillsCourse = skillsCourse;
	}

	public String getTagsCourse() {
		return tagsCourse;
	}

	public void setTagsCourse(String tagsCourse) {
		this.tagsCourse = tagsCourse;
	}

	public String getRequiredCourse() {
		return requiredCourse;
	}

	public void setRequiredCourse(String requiredCourse) {
		this.requiredCourse = requiredCourse;
	}

	@Override
	public String toString() {
		return "CourseJPA [idCourse=" + idCourse + ", idUser=" + idUser + ", categoryCourse=" + categoryCourse
				+ ", titleCourse=" + titleCourse + ", descriptCourse=" + descriptCourse + ", skillsCourse="
				+ skillsCourse + ", tagsCourse=" + tagsCourse + ", requiredCourse=" + requiredCourse + "]";
	}
		
	
}
