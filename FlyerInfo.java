package NUA;

class FlyerInfo{
	//Variables
		private String status;
		private String name;
		private Integer priorityNum;
		private FlyerInfo flyer;
		
		//	private int priorityNum;
		static int superCount = 0;
		static int platinumCount = 0;
		static int goldCount = 0;
		static int silverCount = 0;

	//Constructors
		
		//Constructor
		public FlyerInfo() {
//			flyer = this.flyer;
//			priorityNum = this.priorityNum;
//			status = this.status;
//			name = this.name;	
		}

		//Constructor
		public FlyerInfo(int priorityNum, String status, String name) {
			this.priorityNum = priorityNum;
			this.status = status;
			this.name = name;
		}

	//getters and setters
		
		//flyer
	    public FlyerInfo getFlyer() {
	    	return flyer;
	    }
	    public void setFlyer(FlyerInfo flyer) {
	    	this.flyer = flyer;
	    }
		
	    
	    //priorityNum
		public int getPriorityNum() {
			return priorityNum;
		}

		public void setPriorityNum(int priorityNum) {	
			this.priorityNum = priorityNum;
		}

		
		//status
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		
		//name
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
			
	//Methods
		
		//Method to generate priority number
		int FlyerPriorityNumberGenerator(String flyerStatus) {
			int number = 0;
			flyerStatus = flyerStatus.toLowerCase();
			switch (flyerStatus) {
				case "super":
					superCount++;
					number = 100 + superCount;
					break;
				case "platinum":
					platinumCount++;
					number = 200 + platinumCount;
					break;
				case "gold":
					goldCount++;
					number = 300 + goldCount;
					break;
				case "silver":
					silverCount++;
					number = 400 + silverCount;
					break;
				default:
					number = -1;
			}
			return number;
		}//end FlyerPriorityNumberGenerator
		
		
		@Override
		//toString Method
		public String toString() {
			return "\tFlyer Name: " + name + "\tFlyerStatus: " + status + "\tRequest Number: " + priorityNum;
		}//end toString
						
}//end class

