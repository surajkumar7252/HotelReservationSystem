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
	public static int specialWeekDayRate;
	public static int specialWeekEndRate;
	
	public static int rating;
	
	public Hotels(String nameOfTheHotel, int weekDayRent,int weekEndRent,int ratingForHotel,int specialWeekDayRateOfHotel,int  specialWeekEndRateOfHotel) {
		
		nameOfHotel = nameOfTheHotel;
		weekDayRate =  weekDayRent;
		weekEndRate =   weekEndRent;
		rating=ratingForHotel;
		specialWeekDayRate= specialWeekDayRateOfHotel;
		specialWeekEndRate= specialWeekDayRateOfHotel;
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
	public int getRating() {
		return rating;
	}
	public void setRating(int  ratingForHotel) {
		rating =  ratingForHotel;
	}
	public int getSpecialWeekDayRate() {
		return specialWeekDayRate;
	}
	public void setSpecialWeekDayRate(int specialWeekDayRateOfHotel) {
		specialWeekDayRate =  specialWeekDayRateOfHotel;
	}
	public int getSpecialWeekEndRate() {
		return specialWeekEndRate;
	}
	public void setspecialWeekEndRate(int  specialWeekEndRateOfHotel) {
		specialWeekEndRate =  specialWeekEndRateOfHotel;
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
			log.info("Enter Rating for your stay in the hotel: ");
			int rating=inputFeed.nextInt();
			log.info("Enter special Week Day rate for the stay in hotel: ");
			int specialWeekDayRate=inputFeed.nextInt();
			log.info("Enter special Week End rate for the stay in hotel: ");
			int specialWeekEndRate=inputFeed.nextInt();
			
			
			Hotels hotelOb=new Hotels(nameOfHotel,weekDayRent,weekEndRent,rating,specialWeekDayRate,specialWeekEndRate);
			listOfHotels.add(hotelOb);
			log.info("Do you want to perform again: ");
			choice=inputFeed.next().charAt(0);
			
		}
	}
	    
		
		public static void cheapestHighestRatedHotel() {
			
		    log.info("Enter  the check-in date in the format DDMMMYYYY (like 10OCT2020): ");
		    String checkInDate=inputFeed.nextLine();
		    log.info("Enter  the check-out date in the format DDMMMYYYY (like 12OCT2020): ");
		    String checkOutDate=inputFeed.nextLine();
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
			LocalDate checkInFeed= LocalDate.parse(checkInDate, dateFormat);
			LocalDate checkOutFeed = LocalDate.parse(checkOutDate, dateFormat);
			List<DayOfWeek> weekContainer = new ArrayList<DayOfWeek>();
			Integer numofWeekEnds = (int) weekContainer.stream().filter(day -> WEEKENDDAYS.contains(day)).count();
			Integer numOfWeekDays = weekContainer.size() - numofWeekEnds;
			
			Map<Hotels, Integer> hotelRateMap=listOfHotels.stream().collect(Collectors.toMap<(hotelOb->hotelOb.getName(),hotelOb->hotelOb.getWeekDayRate()* numOfWeekDays,hotelOb->hotelOb.getWeekEndRate()*numofWeekEnds));
			
			Hotels cheapestHotelInAll=hotelRateMap.keySet().stream().min((firstHotel,secondHotel)->{
			Integer differenceInRate=hotelToTotalRateMap.get(firstHotel) - hotelToTotalRateMap.get(secondHotel);
			Integer differenceInRating =firstHotel.getRating() - secondHotel.getRating();
			if(differenceInRate==0) return differenceInRate=differenceInRating;
			if(differenceInRate!=0) return differenceInRate=differenceInRate;
			}).orElse(null);
			log.info(cheapestHotelInAll.getName()+",Rate(in Dollars) : "+hotelRateMap.get(cheapestHotelInAll));
			log.info("The cheapest Hotel is :"+ cheapestHotelInAll);
		}
	
		public void bestRatedHotel() {
			
			    log.info("Enter  the check-in date in the format DDMMMYYYY (like 10OCT2020): ");
			    String checkInDate=inputFeed.nextLine();
			    log.info("Enter  the check-out date in the format DDMMMYYYY (like 12OCT2020): ");
			    String checkOutDate=inputFeed.nextLine();
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
				LocalDate checkInFeed= LocalDate.parse(checkInDate, dateFormat);
				LocalDate checkOutFeed = LocalDate.parse(checkOutDate, dateFormat);
				List<DayOfWeek> weekContainer = new ArrayList<DayOfWeek>();
				Integer numofWeekEnds = (int) weekContainer.stream().filter(day -> WEEKENDDAYS.contains(day)).count();
				Integer numOfWeekDays = weekContainer.size() - numofWeekEnds;
			    Hotels  bestRatedHotelAvailable = listOfHotels.stream().max((firstHotel, secondHotel) -> firstHotel.getRating() -secondHotel.getRating())
					.orElse(null);
			try {
				int dayBill =bestRatedHotelAvailable.getWeekDayRate() * numOfWeekDays
						+ bestRatedHotelAvailable.getWeekEndRate() * numofWeekEnds;
				log.info(bestRatedHotelAvailable.getName() + " & Total Rates $" + dayBill);
			} catch (NullPointerException e) {
				log.info("Hotel Not found");
			}
			log.info("The Best rated  Hotel available  is :"+ bestRatedHotelAvailable);
			
		}
		public static void cheapestHighestRatedHotelUnderRewardProgram() {
			
			log.info("Enter  the check-in date in the format DDMMMYYYY (like 10OCT2020): ");
		    String checkInDate=inputFeed.nextLine();
		    log.info("Enter  the check-out date in the format DDMMMYYYY (like 12OCT2020): ");
		    String checkOutDate=inputFeed.nextLine();
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
			LocalDate checkInFeed= LocalDate.parse(checkInDate, dateFormat);
			LocalDate checkOutFeed = LocalDate.parse(checkOutDate, dateFormat);
			List<DayOfWeek> weekContainer = new ArrayList<DayOfWeek>();
			Integer numOfSpecialWeekEnds = (int) weekContainer.stream().filter(day -> WEEKENDDAYS.contains(day)).count();
			Integer numOfSpecialWeekDays = weekContainer.size() - numofWeekEnds;
			
			Map<Hotels, Integer> hotelRateMap=listOfHotels.stream().collect(Collectors.toMap<(hotelOb->hotelOb.getName(),hotelOb->hotelOb.getSpecialWeekDayRate()* numOfSpecialWeekDays,hotelOb->hotelOb.getSpecialWeekEndRate()*numOfSpecialWeekEnds));
			
			Hotels cheapestHotelInAll=hotelRateMap.keySet().stream().min((firstHotel,secondHotel)->{
			Integer differenceInRate=hotelToTotalRateMap.get(firstHotel) - hotelToTotalRateMap.get(secondHotel);
			Integer differenceInRating =firstHotel.getRating() - secondHotel.getRating();
			if(differenceInRate==0)  differenceInRate=differenceInRating;
			if(differenceInRate!=0)  differenceInRate=differenceInRate;
			}).orElse(null);
			log.info(cheapestHotelInAll.getName()+",Rate(in Dollars) : "+hotelRateMap.get(cheapestHotelInAll));
			log.info("The cheapest Hotel is :"+ cheapestHotelInAll);
		}
	
		
	public static void main( String[] args )
    {   Integer choice=0;
    
       log.info( "Welcome to Hotel Reservation System" );
       log.info("What operation do you want to perform: ");
       log.info("1.Adding Hotels Data. ");
       log.info("2.Finding the cheapest best rated hotel in-between Dates. ");
       log.info("3.Finding the cheapest best rated hotel in-between Dates under Loyalty Program. ");
	  choice=inputFeed.nextInt();
	  switch(choice) {
	  case 1:addingHotel();
	          break;
	  case 2:  cheapestHighestRatedHotel();
	           break;
	  case 3:  cheapestHighestRatedHotelUnderRewardProgram();
               break;
	  }
       
    }

}
