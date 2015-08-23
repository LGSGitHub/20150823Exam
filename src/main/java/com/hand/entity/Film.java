package com.hand.entity;

public class Film {

	private Long film_id;
	private String title;
	private String description;
	private Long language_id;

	public Long getFilm_id() {
		return film_id;
	}

	public void setFilm_id(Long film_id) {
		this.film_id = film_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(Long language_id) {
		this.language_id = language_id;
	}

	@Override
	public String toString() {
		return "Film [film_id=" + film_id + ", title=" + title + ", description=" + description + ", language_id="
				+ language_id + "]";
	}

}
