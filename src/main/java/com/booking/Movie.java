package com.booking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {
	
	@Id
	private  int movieId;
	private String movieName;
	private String movieType;
	private double rating;
	private  int ticketPrice;
	public  int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieType() {
		return movieType;
	}
	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public  int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	public Movie()
	{
		
	}
	public Movie(int movieId, String movieName, String movieType, double rating, int ticketPrice) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieType = movieType;
		this.rating = rating;
		this.ticketPrice = ticketPrice;
	}
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", movieType=" + movieType + ", rating="
				+ rating + ", ticketPrice=" + ticketPrice + "]";
	}
	
	
	
	

}
