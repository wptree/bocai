package com.bocai.dao.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 针对每个User，将Ta上传分享过的所有的Spot的图片组成一个相册。
 * 每个相册可能会有多个子相册，子相册的名字是Dish的type值，即将相同类型的spot的图片放在一个子相册
 * @author wpeng
 *
 */
@Entity
@Table(name="BC_DISH_ALBUM")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DishAlbum {

	private Long id;
	private String albumName;
	private DishAlbum parent;
	private Set<DishAlbum> subAlbum;
	private Integer imgCount = 0;
	private Set<Spot> spots;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	@Column(name = "ALBUM_NAME")
	public String getAlbumName() {
		return albumName;
	}

	public void setImgCount(Integer imgCount) {
		this.imgCount = imgCount;
	}
	@Column(name = "IMG_COUNT")
	public Integer getImgCount() {
		return imgCount;
	}

	public void setSubAlbum(Set<DishAlbum> subAlbum) {
		this.subAlbum = subAlbum;
	}
	
	
	@OneToMany( mappedBy="parent")
	public Set<DishAlbum> getSubAlbum() {
		return subAlbum;
	}

	public void setParent(DishAlbum parent) {
		this.parent = parent;
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", nullable = false)
	public DishAlbum getParent() {
		return parent;
	}
	
	public void setSpots(Set<Spot> spots) {
		this.spots = spots;
	}
	@OneToMany
	public Set<Spot> getSpots() {
		return spots;
	}
	

}
