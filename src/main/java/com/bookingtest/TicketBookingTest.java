package com.bookingtest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;

import com.booking.Booking;
import com.booking.Movie;
import com.connections.HibernateConnections;

public class TicketBookingTest {
	
	
	public static void main(String[] args) {
		
		SessionFactory sf=HibernateConnections.getSessionFactory();
		Session s=sf.openSession();
		movieInsertion(s);
		
		
		bookingInsertion(s);
		
		bookingDetails(s);
		displayAllBookings(s);
		
		displayAllMovies(s);
		updatingTicketPrice(s);
		updatingNoOfTickets(s);
		
		updatingTotalPrice(s);
		
		deletionOfRecord(s);
		
	}

	private static void deletionOfRecord(Session s) {
		s.beginTransaction();
		Booking booking = s.find(Booking.class, 102);
		if(booking==null)
		{
			System.out.println("No booking found!!");
			s.getTransaction().rollback();
			return;
		}
		s.remove(booking);
		s.getTransaction().commit();
		System.out.println("Booking ID Deleted Successfully!!!!");
	}

	private static void updatingTotalPrice(Session s) {
		s.beginTransaction();
		Booking booking = s.find(Booking.class, 104);
		if (booking == null) {
		    System.out.println("No Data Found");
		    s.getTransaction().rollback();
		    return;
		}
		
		
		Movie movie=s.find(Movie.class, booking.getMovieId());
		if (movie == null) {
		    System.out.println("Movie not found!");
		    s.getTransaction().rollback();
		    return;
		}
		int updatedTotal=movie.getTicketPrice()*booking.getNoOfTickets();
		booking.setTotalAmount(updatedTotal);
		s.getTransaction().commit();
		System.out.println("Total amount updated successfully!!!!");
	
	}

	private static void updatingNoOfTickets(Session s) {
		s.beginTransaction();
		Booking booking = s.find(Booking.class, 104);
		if(booking==null)
		{
			System.out.println("Please book tickets");
			s.getTransaction().rollback();
			return;
		}
		booking.setNoOfTickets(5);
		s.getTransaction().commit();
		System.out.println("Tickets Updated Successfully!!!");
	}

	private static void updatingTicketPrice(Session s) {
		s.beginTransaction();
		Movie movie = s.find(Movie.class, 201);
		if(movie==null)
		{
			System.out.println("Movie Not Found!!!");
			s.getTransaction().rollback();
			return;
		}
		movie.setTicketPrice(200);
		s.getTransaction().commit();
		System.out.println("Ticket Price Updated!!!");
		
	}

	private static void displayAllMovies(Session s) {
		SelectionQuery<Movie> sq=s.createSelectionQuery("From Movie",Movie.class);
		List<Movie> allMovies = sq.list();
		for(Movie movie:allMovies)
		{
			System.out.println("Movie ID : "+movie.getMovieId()+"\nMovie Name : "+movie.getMovieName()+"\nMovie Type : "+movie.getMovieType()
			+"\nMovie Rating : "+movie.getRating()+"\nMovie Ticket Price : "+movie.getTicketPrice());
			System.out.println("--------------------");
			
		}
	}

	private static void displayAllBookings(Session s) {
		SelectionQuery<Booking> sq=s.createSelectionQuery("From Booking",Booking.class);
		List<Booking> allBookings = sq.list();
		for(Booking b:allBookings)
		{
			int movieId = b.getMovieId();
			Movie m = s.find(Movie.class, movieId);
			System.out.println(
				    "Customer: " + b.getCustomerName() +
				    "\nMovie: " + m.getMovieName() +
				    "\nTickets: " + b.getNoOfTickets() +
				    "\nTotal: " + b.getTotalAmount()
				);
				System.out.println("----------------------");
		}
	}

	private static void bookingDetails(Session s) {

	    Booking booking = s.find(Booking.class, 103);

	    if (booking == null) {
	        System.out.println("Booking not found!");
	        return;
	    }

	    Movie movie = s.find(Movie.class, booking.getMovieId());

	    if (movie == null) {
	        System.out.println("Movie not found!");
	        return;
	    }

	    System.out.println(
	        "Customer: " + booking.getCustomerName() +
	        "\nMovie: " + movie.getMovieName() +
	        "\nTickets: " + booking.getNoOfTickets() +
	        "\nTotal: " + booking.getTotalAmount()
	    );
	}

	private static void bookingInsertion(Session s) {

	    Movie movie2 = s.find(Movie.class, 201);
	    Movie movie3 = s.find(Movie.class, 200);

	    if (movie2 == null || movie3 == null) {
	        System.out.println("Movie not found!");
	        return;
	    }

	   
	    double price1 = movie2.getTicketPrice();
	    double price2 = movie3.getTicketPrice();

	    
	    Booking b1 = new Booking(102, "Nandan", movie2.getMovieId(), 5, 5 * price1);
	    Booking b2 = new Booking(103, "Nandan", movie3.getMovieId(), 3, 3 * price2);
	    Booking b3 = new Booking(104, "Nandan", movie3.getMovieId(), 4, 4 * price2);

	    s.beginTransaction();
	    s.persist(b1);
	    s.persist(b2);
	    s.persist(b3);
	    s.getTransaction().commit();

	    System.out.println("Ticket Booked!!!");
	}

	private static void movieInsertion(Session s) {
		Movie m1=new Movie(200,"RRR","Action",4.8d,150);
		Movie m2=new Movie(201,"OG","Action",4.9d,140);
		s.beginTransaction();
		s.persist(m1);
		s.persist(m2);
		s.getTransaction().commit();
		System.out.println("Movie Added Successfully!!!!!!");
	}

}
