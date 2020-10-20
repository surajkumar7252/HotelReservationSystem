package hotelreservationsystem;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 class Hotels {
	public static String nameOfHotel;
	public static int weekDayRate;
	public static int weekEndRate;
	
	public Hotels(String nameOfTheHotel, int weekDayRent,int weekEndRent) {
		
		nameOfHotel = nameOfTheHotel;
		weekDayRate =  weekDayRent;
		weekEndRate =   weekEndRent;
	}
	
	public String getName() {
		return nameOfHotel;
	}
	public void setName(String nameOfTheHotel) {
		nameOfHotel = nameOfTheHotel;
	}
	public int getWeekDayRate() {
		return weekDayRate;
	}
	public void setWeekDayRate(int weekDayRent) {
		 weekDayRate = weekDayRent;
	}
	public int getWeekEndRate() {
		return weekEndRate;
	}
	public void setWeekEndRate(int  weekEndRent) {
		weekEndRate =  weekEndRent;
	}
}

public class HotelReservation {
	public static Scanner inputFeed=new Scanner(System.in);
	public static final Logger log=LogManager.getLogger(HotelReservation.class);
	public static List<Hotels> listOfHotels=new ArrayList<>();
	private static final List<DayOfWeek> WEEKENDDAYS = Arrays.asList(new DayOfWeek[] { DayOfWeek.SATURDAY,DayOfWeek.SUNDAY });
   
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
			List<DayOfWeek> weekContainer = new ArrayList<DayOfWeek>();
			int numofWeekEnds = (int) weekContainer.stream().filter(day -> WEEKENDDAYS.contains(day)).count();
			int numOfWeekDays = weekContainer.size() - numofWeekEnds;
			
			Map<Hotels, Integer> hotelRateMap=listOfHotels.stream().collect(Collectors.toMap<(hotelOb->hotelOb.getName(),hotelOb->hotelOb.getWeekDayRate()* numOfWeekDays,hotelOb->hotelOb.getWeekEndRate()*numofWeekEnds));
			
			Hotels cheapestHotelInAll=hotelRateMap.keySet().stream().min((firstHotel,secondHotel)->hotelRateMap.get(firstHotel)-hotelRateMap.get(secondHotel)).orElse(null);
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
