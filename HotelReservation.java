package hotelreservationsystem;


import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 class Hotels {
	private String nameOfHotel;
	private int dailyRent;
	
	public Hotels(String nameOfHotel, int dailyRent) {
		
		this.nameOfHotel = nameOfHotel;
		this.dailyRent = dailyRent;
	}
}

public class HotelReservation {
	public static Scanner inputFeed=new Scanner(System.in);
	public static final Logger logger=LogManager.getLogger(HotelReservation.class);
	public static List<Hotels> listOfHotels=new ArrayList<>();
   
	public static void addingHotel() {
		char choice=0;
		while(choice=='Y'|| choice=='y') {
			 logger.debug("Enter  name of the hotel: ");
			String nameOfHotel=inputFeed.nextLine();
			 logger.debug("Enter cost of staying in hotel: ");
			int dailyRent=inputFeed.nextInt();
			Hotels hotelOb=new Hotels(nameOfHotel,dailyRent);
			listOfHotels.add(hotelOb);
			 logger.debug("Do you want to perform again: ");
			choice=inputFeed.next().charAt(0);
			
		}
	}
	
	public static void main( String[] args )
    {
       logger.debug( "Welcome to Hotel Reservation System" );
       addingHotel();
    }
}
