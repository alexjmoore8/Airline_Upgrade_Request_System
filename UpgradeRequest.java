package NUA;
import java.util.*;

class UpgradeRequest {
	
	//Variables
		private UpgradeList pq;
		private Map<Integer, FlyerInfo> map;
	
	//Constructors
		
		//constructor
	    public UpgradeRequest() {
	        pq = new UpgradeList(new FlyerInfoComparator());
	        map = new HashMap<>();
	    }
	    
	//Methods
	    
	    //method to generate and dipay Main Menu
	    public void userMenu(Scanner scnr) {

	    		UpgradeRequest request = new UpgradeRequest();
	            int userInput = 0;
				while(userInput != 4) {
					System.out.println("\n-----Main Menu-----"
							+ "\nPlease select from the following options:\n"
							+ "\nEnter 1: To CREATE a new upgrade request"
							+ "\nEnter 2: To DELETE an existing upgrade request"
							+ "\nEnter 3: To PRINT the upgrade list" 
							+ "\nEnter 4: To EXIT the upgrade list\n" 
							+ "\nEnter your selection: ");
					
					//get  and validate userInput
					while(!scnr.hasNextInt() || (userInput = scnr.nextInt()) < 1 || userInput > 4) {
                        scnr.nextLine();
                        System.out.println("Invalid input.\nTry Again.\n\nEnter your selection: ");
                    }//end while
					
					//clear buffer
					scnr.nextLine();
					
					//make new upgrade request prompt
					if (userInput == 1){
						System.out.println("You have choosen to CREATE a new request.\n" 
								+ "How many requests would you like to create?");
						
						//get and validate userInput
						int userInt;
						while(!scnr.hasNextInt() || (userInt = scnr.nextInt()) < 1) {
	                        scnr.nextLine();
	                        System.out.println("Invalid input.\nTry Again.\n\nEnter your selection: ");
	                    }//end while
						
						//clear buffer
						scnr.nextLine();
						
						//call add request method
						for(int i = userInt; i>0; i--) {
							request.addRequestToUpgradeList(scnr);
						    }
					}//end if
					
					//delete existing request prompt
					else if(userInput == 2) {
						System.out.println("You have chosen to DELETE an existing request.\n");
						System.out.println("What is your request code?");
						
						//get and validate userInput
						int userInt;
						while(!scnr.hasNextInt() || (userInt = scnr.nextInt()) < 100 || userInput > 500) {
	                        scnr.nextLine();
	                        System.out.println("Invalid input.\nTry Again.\n\nEnter your selection: ");
	                    }//end while
						
						//clear buffer
						scnr.nextLine();
						
						//call delete request method
						request.deleteRequestFromUpgradeList(userInt);
						continue;
					}//end else if
					
					//print list of upgrade eligible flyers prompt
					else if(userInput == 3){
						System.out.println("You have chosen to PRINT the upgradelist.\n");
						System.out.println("How many upgrade seats are available?");
						
						//get userInput
						int userInt;
						while(!scnr.hasNextInt() || (userInt = scnr.nextInt()) < 1) {
	                        scnr.nextLine();
	                        System.out.println("Invalid input."
	                        				+ "\nTry Again."
	                        				+ "\n\nEnter your selection: ");
	                    }//end while
						
						//clear buffer
						scnr.nextLine();
						
						//call generate Upgrade List method
						request.GenerateUpgradeList(userInt);
						break;
					}//end else if
					
					//exit system prompt
					else if (userInput ==4) {
						System.out.println("You have chosen to exit the system.");
						System.out.println("Program has ended.");
						break;
						
					}//end else if
					
				}//end while

	    }//end userMenu 
	    
	    
	    //Method to get user input and Create Upgrade Request
	    public void addRequestToUpgradeList(Scanner scnr) {
	    	//Variables
	        String flyerName;
	        String flyerStatus;
	        int priorityNum;
	        
	        FlyerInfo flyer = new FlyerInfo();
	        
	    	//Get user input
			System.out.println("Enter your name:");
			flyerName = scnr.nextLine();
			flyer.setName(flyerName);

			System.out.println("Enter your flyer status:"
					+ "(options are silver, gold, platinum, and super)");
			flyerStatus = scnr.nextLine();
			flyer.setStatus(flyerStatus);
			
			//Generate priority number
			priorityNum = flyer.FlyerPriorityNumberGenerator(flyerStatus);
//			flyer.setPriorityNum(priorityNum);
			
			//Validate  status
			if(priorityNum ==-1) {
				for(int i =2; i>0; i--) {
					if(priorityNum ==-1) {
						System.out.println("\nYou entered an invalid  status."
								+ "\nPlease enter silver, gold, platinum, or super."
								+ "\n\tYou have " + i + " attempt(s) remaining.");
						System.out.println("\nEnter your  status:");
						flyerStatus = scnr.nextLine();
						priorityNum = flyer.FlyerPriorityNumberGenerator(flyerStatus);
						flyer.setStatus(flyerStatus);
					
						if((priorityNum ==-1) && (i == 1)) {
							System.out.println("\nNo attempts left."
									+ "\nYour request was not processed.\n");	
							return;
						}//end if

					}//end if
				}//end for
			}//end if
			
			//update FlyerInfo
			flyer.setPriorityNum(priorityNum);
			flyer.setFlyer(flyer);
			pq.insert(flyer);
			map.put(priorityNum, flyer);
	        
			//Print request information
			System.out.println("\n--Upgrade Request Information--\n" + 
							"Flyer Name: " + flyerName + 
							"\nFrequent Flyer Status: " + flyerStatus + 
							"\nRequest Code: " + priorityNum +
							"\nUse this code the change or cancel your upgrade request.\n");

	    }//end addRequestToUpgradeList
	    

	    //Method to delete an Upgrade Request
	    public void deleteRequestFromUpgradeList(int priorityNum) {
            FlyerInfo requestNum = map.get(priorityNum);
            
            //validate requestNum, if invalid returns to main menu
            if(requestNum == null) {
                System.out.println("Invalid Entry.\nTry Again.");
                return;
            }
            pq.remove(requestNum);
            System.out.println("--Upgrade request for " + requestNum.getFlyer().getName()
                + " with confirmation code " + requestNum.getPriorityNum() + " has been cancelled.--");
	    }//end removeRequestFromUpgradeList
	    
	
	    //Method to generate and print Upgrade Request list
	    public void GenerateUpgradeList(int seatsAvailable) {
	    	System.out.println("\n\t-----First Class Upgrades List-----");
	    	System.out.println("\n\t\tSeats Available: "+ seatsAvailable + "\n");
	        for (int i = 1; i <= seatsAvailable && pq.getSize() > 0; i++) {
	        	FlyerInfo request = pq.popMin();
	            int requestCode = request.getPriorityNum();
	            System.out.println("\t--Seat " + i + "--"
	            				+ "\n\tRequest Number: " + request.getPriorityNum()
	            				+ "\n\tFlyer Name: " + request.getFlyer().getName() 
	            				+ "\n\tFlyerStatus: " + request.getFlyer().getStatus() + "\n");
	            map.remove(requestCode);
	
	        }//end for loop
	    }//end GenerateUpgradeList
	    
	    
	 //Class
	    
		//Comparator class to compare priority numbers
		class FlyerInfoComparator implements Comparator<FlyerInfo> {
			
			@Override
			public int compare(FlyerInfo o1, FlyerInfo o2) {
				FlyerInfo o1Flyer = o1.getFlyer();
				FlyerInfo o2Flyer = o2.getFlyer();
				
				//return o1Flyer.getPriorityNum() < o2Flyer.getPriorityNum() ? 1 : -1;
				
				 // check the status first
				if(o1Flyer != null && o2Flyer != null) {
		        if (o1Flyer.getPriorityNum() > o2Flyer.getPriorityNum()) {
		            return 1;
		        } 
		        else if (o1Flyer.getPriorityNum() < o2Flyer.getPriorityNum()) {
		            return -1;
		        } 
		        else {
		                return 0;
		        }
				}
				else {
					return 0;
				}
			}
		}//end class FlyerInfoComparator

}//end class UpgradeRequest