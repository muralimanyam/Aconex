#Aconex Coding Challenge - Vehicle Survey Coding Challenge
===========================

#Approach
 ==============
 
   The solution is achieved using Services-based approach. There are 4 primary services:
   
       - Core Controller to which the main program delegates and which does orchestration.
	   - Input File parser - To read the survey data and return them back as validated records.
	   - Vehicle Record Service - To read the validated survey data records and convert the data into List of Vehicle records.
	   - Survey store - To read the vehicle records and provide logical caching for individual analysis components.
	   
   All these services are loaded using Java's Service Loader. This way, client defined implementations can also be plugged in.
   
   Next, the different analysis are done with each implementation of "VehicleSurveyAnalysisIF". Again, the individual analysis components 
   are loaded via Java's Service Loader. This way, client defined analysis implementations can also be plugged in.
   
   
   The algorithmic steps are as :
   
       - Gather the input file location.
	   - Load the InputFileParser service and pass file location to InputFileParser service that would read the contents and return a List of survey data.
	   - Load the Vehicle Record Service and pass List of survey data and get back List of Vehicle records.
	   - Load Survey Store service and pass Vehicle records to it.
	   - Load all the analysis components and initiate them with store service.
	   - Call print analaysis method on analysis components.
   
#Pre-requisites
============================

 - JDK 1.5 or higher
 - Maven

#Set Up Instructions
============================
  - Clone the repository using https://github.com/muralimanyam/Aconex.git
  - Go into "Aconex" directory and run command "mvn clean install".
  - Go into "target" directory.
  - run command "java -jar VehicleSurvey-0.0.1-SNAPSHOT.jar <path-to-data-file>"
  - The results are printed in console.
