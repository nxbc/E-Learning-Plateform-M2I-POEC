package com.m2i.elearn.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Seb
 * Classe permettant l'accès à la table ELearning.chapters
 * 
 */
@Entity
@Table(name="chapters")
@NamedQueries(value={
		@NamedQuery(name="ChapterJPA.FIND_ALL_ORDER_BY_ID",
				    query="SELECT e FROM ChapterJPA e ORDER BY e.idCourse,e.idChapter")		
})
public class ChapterJPA {
	
	@Id
	@NotNull
	@Column(name="id_chapter ")
	private Integer idChapter ; 
	
	@NotNull
	@Column(name="id_course")
	private Integer idCourse; 
	
	@NotNull
	@Column(name="title_chapter ")
	private String titleChapter  ;
	
	@NotNull
	@Column(name="num_chapter ")
	private Integer numChapter ;

	public Integer getIdChapter() {
		return idChapter;
	}

	public void setIdChapter(Integer idChapter) {
		this.idChapter = idChapter;
	}

	public Integer getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}

	public String getTitleChapter() {
		return titleChapter;
	}

	public void setTitleChapter(String titleChapter) {
		this.titleChapter = titleChapter;
	}

	public Integer getNumChapter() {
		return numChapter;
	}

	public void setNumChapter(Integer numChapter) {
		this.numChapter = numChapter;
	}

	@Override
	public String toString() {
		return "ChapterJPA [idChapter=" + idChapter + ", idCourse=" + idCourse + ", titleChapter=" + titleChapter
				+ ", numChapter=" + numChapter + "]";
	} 
}
