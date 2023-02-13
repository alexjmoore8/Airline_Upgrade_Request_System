# Airline_Upgrade_Request_System
Java: MinHeap
>This Java project uses a MinHeap to store airline flyers' requests for upgraded seats and prints a list of flyers eligible for an upgrade based on the number of upgrade seats available and their status in relation to the other flyers who requested upgrades.


## Table of Contents
* [Technologies Used](#technologies-used)
* [Usage](#usage)
* [Project Status](#project-status)
* [Detailed Information](#detailed-information)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)
* [License](#license)


## Technologies Used
- Eclipse IDE - version 4.24.0


## Usage
The project is designed to run in the console and take in information (name and status) provided by the flyer requesting an upgrade. The system allows a flyer to delete their upgrade request using the request code provided. After all upgrade requests are entered, a list of passengers eligible for an upgrade can be printed. Eligibility is limited by the number of upgraded seats available and determined by the status of the flyer and the order in which they entered their upgrade. Statuses in order of priorty are: Super, Platinum, Gold, and Silver.


## Detailed Information
The upgrade system is implemented using a minimum heap that maintains the requests with the minimum value (highest priority) on top,  as well as a hashmap that keeps a record of the requests entered into the heap. As a flyer requests an upgrade, they are added to the heap and map using the addRequestToUpgradeList() method. Their priority within the heap is based on their status and then the order in which they made the request. Each request is given a unique three-digit number which can be scaled up or down depending on the needs of the system. The FlyerPriorityNumberGenerator() method assigns the unique priority number.
The first digit (hundreds place) is assigned based on the flyer status. For instance, Super flyers (assuming that Super is the highest priority flyer) are given the digit 1, e.g.,  101. Similarly, the first silver flyers (assuming silver is the 4th lowest priority flyer) are the digit 4, e.g.,  401. The second and third (ones and tens palace) digit of each number is assigned using an incrementing counter for each status group. 
This ensures that each flyer will be prioritized first by status and then by order of entry within their status group. This system allows for up to 99 flyers in each status group to be counted. If the system needs to track more than 99 flyers per status category, this could be adjusted to make the thousands digit signify the flyer’s status, allowing up to 999 flyers to be tracked per status category.
As flyers are added to the system, the heap restructures itself to ensure that the highest priority flyer is at the top. This allows for the highest-priority flyers to be accessed very quickly (O(1) time) using the popMin() function. 
When ready, the GenerateUpgradeList() method adds the highest-priority flyers to the upgrade list based on the number of upgrade seats specified. The flyer with the highest priority is removed from the heap and printed onto the list. The next highest priority is moved up into the top position based on the minimum heap principles and the priority number assigned. The operation to determine which flyer to replace the removed flyer, requires worst-case O(log n) time. This will be done k times (for each root removal), resulting in O(k log n) time. 
The priority number also serves as a means of quickly finding a flyer who wishes to adjust their upgrade request status and delete their request. This is accomplished by calling the deleteRequestFromUpgradeList() method, which uses the remove() function to locate the index of the provided request number and remove it from the heap. The heap is then restructured to ensure the minimum heap principles are satisfied. The worst case is O(log n) for each such adjustment to the upgrade request.  


## Project Status
Project is: _complete_ 


## Acknowledgements
- This project was completed as part of my Algorithms course.


## Contact
Created by [@alexjmoore8](https://www.linkedin.com/in/alexmoore8/) - feel free to contact me!


## License 
MIT License

Copyright (c) 2023 Alex Moore

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
