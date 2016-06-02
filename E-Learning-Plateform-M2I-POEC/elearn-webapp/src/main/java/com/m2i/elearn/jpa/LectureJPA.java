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
 * Classe permettant l'accès à la table ELearning.lectures
 * 
 */
@Entity
@Table(name="lectures")
@NamedQueries(value={
		@NamedQuery(name="LectureJPA.FIND_ALL_ORDER_BY_ID",
				    query="SELECT e FROM LectureJPA e ORDER BY e.idChapter,e.idLecture")		
})
public class LectureJPA {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_lecture")
	private Integer idLecture ; 
	 
	@NotNull
	@Column(name="id_chapter")
	private Integer idChapter ; 
	
	@NotNull
	@Column(name="num_lecture")
	private Integer numLecture ; 
	
	@Column(name="link_video_lecture  ")
	private String linkVideoLecture   ;
	
	@Column(name="txt_lecture")
	private String txtLecture;

	public Integer getIdLecture() {
		return idLecture;
	}

	public void setIdLecture(Integer idLecture) {
		this.idLecture = idLecture;
	}

	public Integer getIdChapter() {
		return idChapter;
	}

	public void setIdChapter(Integer idChapter) {
		this.idChapter = idChapter;
	}

	public Integer getNumLecture() {
		return numLecture;
	}

	public void setNumLecture(Integer numLecture) {
		this.numLecture = numLecture;
	}

	public String getLinkVideoLecture() {
		return linkVideoLecture;
	}

	public void setLinkVideoLecture(String linkVideoLecture) {
		this.linkVideoLecture = linkVideoLecture;
	}

	public String getTxtLecture() {
		return txtLecture;
	}

	public void setTxtLecture(String txtLecture) {
		this.txtLecture = txtLecture;
	}

	@Override
	public String toString() {
		return "LectureJPA [idLecture=" + idLecture + ", idChapter=" + idChapter + ", numLecture=" + numLecture
				+ ", linkVideoLecture=" + linkVideoLecture + ", txtLecture=" + txtLecture + "]";
	}
	
 
}
