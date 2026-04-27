package com.booking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Booking {
	
	
	@Id
	private int bookingId;
	private String customerName;
	private int movieId;
	private  int  noOfTickets;
	
	
	private  double totalAmount;
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public  int getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Booking()
	{
		
	}
	public Booking(int bookingId, String customerName, int movieId, int noOfTickets, double totalAmount) {
		super();
		this.bookingId = bookingId;
		this.customerName = customerName;
		this.movieId = movieId;
		this.noOfTickets = noOfTickets;
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", customerName=" + customerName + ", movieId=" + movieId+ ", noOfTickets=" + noOfTickets + ", totalAmount=" + totalAmount + "]";
	}
	
	
	
	

}
