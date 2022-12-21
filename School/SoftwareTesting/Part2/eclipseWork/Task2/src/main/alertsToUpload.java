package main;

public class alertsToUpload {
	public alertConstruction alerts(int i){
		alertConstruction alert = null;
		
		switch(i){
		case 1 : alert = new alertConstruction(
				6, 
				"laptop",
				"laptop",
				"https://www.maltapark.com/item/details/9536798",
				"https://www.maltapark.com/asset/itemphotos/9536798/9536798_1.jpg?_ts=1",
				"01150cc0-eff8-4df5-a549-eb18cf7c6184",
				24999
				);
		break;
		
		case 2 : alert = new alertConstruction(
				5, 
				"Toy car",
				"good toy",
				"https://www.maltapark.com/item/details/9528676",
				"https://www.maltapark.com/asset/itemphotos/9528676/9528676_1.jpg?_ts=4",
				"01150cc0-eff8-4df5-a549-eb18cf7c6184",
				24999
				);
		break;
		
		case 3 : alert = new alertConstruction(
				2, 
				"Yaucht",
				"bg boat",
				"https://www.maltapark.com/item/details/9538675",
				"https://www.maltapark.com/asset/itemphotos/9538675/9538675_2.jpg?_ts=3",
				"01150cc0-eff8-4df5-a549-eb18cf7c6184",
				24999
				);
		break;
		
		case 4 : alert = new alertConstruction(
				1, 
				"Hyundai",
				"nice car",
				"https://www.maltapark.com/item/details/9539132",
				"https://www.maltapark.com/asset/itemphotos/9539132/9539132_1.jpg?_ts=6",
				"01150cc0-eff8-4df5-a549-eb18cf7c6184",
				24999
				);
		break;
		
		default : System.out.println("Wrong choice!");
		}
		
		return alert;
	}

}
