package com.connections;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.booking.Booking;
import com.booking.Movie;

public class HibernateConnections {
	
	
	private static SessionFactory sf=null;
	public static SessionFactory getSessionFactory()
	{
		if(sf==null)
		{
			Configuration cf=new Configuration();
			cf.configure("hibernate.cfg.xml");
			cf.addAnnotatedClass(Movie.class);
			cf.addAnnotatedClass(Booking.class);
			sf=cf.buildSessionFactory();
			return sf;
		}
		else return sf;
	}

}
