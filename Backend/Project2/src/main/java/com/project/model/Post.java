package com.project.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="Posts")

public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private int postId;
	
	@Column(name="post_text", nullable=false)
	private String postText;
	
	@Column(name="post_picture")
	private byte[] postPicture;
	
	@CreationTimestamp
	@Column(name="timestamp", nullable=false)
	private LocalDateTime timestamp;
//MERGE CONFLICT HERE DONT KNOW WHICH ONE IS PREFERED
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "post")
	private List<Like> likes;
	@ManyToOne
	@JoinColumn(name="user_id_fk", referencedColumnName="user_id")
	private User author;

	
	//no arg constructor, all arg constructor,
	//getters/setters & toString()
	
	public Post() 
	{
		//Required for Hibernate.
	}

	public Post(int postId, User user, String postText, byte[] postPicture, LocalDateTime timestamp, List<Like> likes) {
		super();
		this.postId = postId;
		this.author = user;
		this.postText = postText;
		this.postPicture = postPicture;
		this.timestamp = timestamp;
		this.likes = likes;
	}
	
	public Post(int postId, User user, String postText, byte[] postPicture) {
		super();
		this.postId = postId;
		this.author = user;
		this.postText = postText;
		this.postPicture = postPicture;
	}

	public Post(User author, String postText, byte[] postPicture) {
		super();
		this.author = author;
		this.postText = postText;
		this.postPicture = postPicture;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public User getAuthor() {
		return  author;
	}

	public void setAuthor( User user) {
		this.author = user;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public byte[] getPostPicture() {
		return postPicture;
	}

	public void setPostPicture(byte[] postPicture) {
		this.postPicture = postPicture;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "\n\tPost [postId=" + postId + ", author=" + author + ", postText=" + postText + ", postPicture="
				+ Arrays.toString(postPicture) + ", timestamp=" + timestamp + ", likes=" + likes + "]";
	}

	
	
}
