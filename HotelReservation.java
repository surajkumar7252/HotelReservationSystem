package hotelreservationsystem;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 class Hotels {
	private String nameOfHotel;
	private int weekDayRate;
	private int weekEndRate;
	
	public Hotels(String nameOfHotel, int weekDayRate,int weekEndRate) {
		
		this.nameOfHotel = nameOfHotel;
		this.weekDayRate =  weekDayRate;
		this.weekEndRate =   weekEndRate;
	}
	
	public String getName() {
		return nameOfHotel;
	}
	public void setName(String nameOfHotel) {
		this.nameOfHotel = nameOfHotel;
	}
	public int getWeekDayRate() {
		return weekDayRate;
	}
	public void setWeekDayRate(int rate) {
		this. weekDayRate = weekDayRate;
	}
	public int getWeekEndRate() {
		return weekEndRate;
	}
	public void setWeekEndRate(int rate) {
		this. weekEndRate = weekEndRate;
	}
}

public class HotelReservation {
	public static Scanner inputFeed=new Scanner(System.in);
	public static final Logger log=LogManager.getLogger(HotelReservation.class);
	public static List<Hotels> listOfHotels=new ArrayList<>();
   
	public static void addingHotel() {
		char choice=0;
		while(choice=='Y'|| choice=='y') {
			log.info("Enter  name of the hotel: ");
			String nameOfHotel=inputFeed.nextLine();
			log.info("which rate do you want to apply : ");
			log.info("Enter Week-Day cost of staying in hotel: ");
			int weekDayRent=inputFeed.nextInt();
			log.info("Enter week-End cost of staying in hotel: ");
			int weekEndRent=inputFeed.nextInt();
			Hotels hotelOb=new Hotels(nameOfHotel,weekDayRent,weekEndRent);
			listOfHotels.add(hotelOb);
			log.info("Do you want to perform again: ");
			choice=inputFeed.next().charAt(0);
			
		}
	}
		
		public static void cheapestHotel() {
			 log.info("Enter  the check-in date in the format DDMMMYYYY (like 10OCT2020): ");
		    String checkInDate=inputFeed.nextLine();
		    log.info("Enter  the check-out date in the format DDMMMYYYY (like 12OCT2020): ");
		    String checkOutDate=inputFeed.nextLine();
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
			LocalDate checkInFeed= LocalDate.parse(checkInDate, dateFormat);
			LocalDate checkOutFeed = LocalDate.parse(checkOutDate, dateFormat);
			Integer numOfDays = (Integer) ChronoUnit.DAYS.between(checkInDate,checkOutDate) + 1;
			Map<Hotels, Integer> hotelRateMap=listOfHotels.stream().collect(Collectors.toMap(hotelOb->hotelOb, hotelOb->hotelOb.getWeekDayRate()*numOfDays));
			Hotels cheapestHotelInAll=hotelRateMap.keySet().stream().min((n1,n2)->hotelRateMap.get(n1)-hotelRateMap.get(n2)).orElse(null);
			log.info(cheapestHotelInAll.getName()+",Rate(in Dollars) : "+hotelRateMap.get(cheapestHotelInAll));
			log.info("The cheapest Hotel is :"+ cheapestHotelInAll);
		}
	
	
	public static void main( String[] args )
    {   Integer choice=0;
    HotelReservation reservation=new HotelReservation ();
       log.info( "Welcome to Hotel Reservation System" );
       log.info("What operation do you want to perform: ");
       log.info("1.Adding Hotels Data. ");
       log.info("2.Finding the cheapest hotel in-between Dates. ");
       
	  choice=inputFeed.nextInt();
	  switch(choice) {
	  case 1:reservation.addingHotel();
	          break;
	  case 2:  log.info("The cheapest Hotel is :");
	          
	           reservation.cheapestHotel();
	           break;
	  }
       
    }

}
