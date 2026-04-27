package com.bookingtest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.SelectionQuery;

import com.booking.Movie;
import com.connections.HibernateConnections;

public class TicketTest {
	
	public static void main(String[] args) {
		
		SessionFactory sf=HibernateConnections.getSessionFactory();
		Session s=sf.openSession();
		
		fetchingAllMovies(s);
		System.out.println("---------------------------");
		
		fetchingMoviesByRating(s);
		System.out.println("---------------------------");
		
		extracted(s);
		System.out.println("---------------------------");
		
		sortingBasedOnTicketPrice(s);
		System.out.println("---------------------------");
		
		findingMaxTicketPrice(s);
		System.out.println("---------------------------");
		
		averageOfTicketPrices(s);
		System.out.println("---------------------------");
		
		updateTicketPriceUsingHQL(s);
		System.out.println("---------------------------");
		
		deleteMoviesByRating(s);
		System.out.println("---------------------------");
		
		s.close();
		sf.close();
	}

	private static void deleteMoviesByRating(Session s) {
		s.beginTransaction();

	    MutationQuery mq =
	        s.createMutationQuery("DELETE FROM Movie WHERE rating < :rating");

	    mq.setParameter("rating", 4);

	    int rowsDeleted = mq.executeUpdate(); 

	    s.getTransaction().commit();

	    System.out.println("Movies deleted: " + rowsDeleted);
	}

	private static void updateTicketPriceUsingHQL(Session s) {
		s.beginTransaction();
		int rowsUpdate = s.createMutationQuery("UPDATE Movie SET ticketPrice = ticketPrice + (ticketPrice * 10 / 100)").executeUpdate();
		s.getTransaction().commit();
		System.out.println("Rows Updated : "+rowsUpdate);
	}

	private static void averageOfTicketPrices(Session s) {
		SelectionQuery<Double> sq =
			    s.createSelectionQuery("SELECT AVG(ticketPrice) FROM Movie", Double.class);

			Double avgPrice = sq.getSingleResult();

			System.out.println("Average Ticket Price: " + avgPrice);
	}

	private static void findingMaxTicketPrice(Session s) {
		SelectionQuery<Movie> sq =s.createSelectionQuery("FROM Movie ORDER BY ticketPrice DESC", Movie.class);

			sq.setMaxResults(1);

			List<Movie> list = sq.list();

			for (Movie m : list) {
			    System.out.println(m);
			}
	}

	private static void sortingBasedOnTicketPrice(Session s) {
		SelectionQuery<Movie> sq = s.createSelectionQuery("From Movie Order By ticketPrice desc",Movie.class);
		List<Movie> list = sq.list();
		for(Movie m:list)
		{
			System.out.println("Movie Name : "+m.getMovieName()+"\nMovie Ticket Price : "+m.getTicketPrice());
			
		}
	}

	private static void extracted(Session s) {
		SelectionQuery<Movie> sq = s.createSelectionQuery("From Movie",Movie.class);
		List<Movie> list = sq.list();
		for(Movie m:list)
		{
			System.out.println("Movie Name : "+m.getMovieName());
			System.out.println("---------");
		}
	}

	private static void fetchingMoviesByRating(Session s) {
		SelectionQuery<Movie> sq = s.createSelectionQuery("From Movie where rating> :rating and rating<=5",Movie.class);
		sq.setParameter("rating", 4);
		List<Movie> list = sq.list();
		for(Movie m:list)
		{
			System.out.println(m);
		}
	}

	private static void fetchingAllMovies(Session s) {
		SelectionQuery<Movie> selectionQuery = s.createSelectionQuery("From Movie",Movie.class);
		List<Movie> list = selectionQuery.list();
		for(Movie m:list)
		{
			System.out.println(m);
		}
	}

}
