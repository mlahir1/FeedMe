
**Abstract:**

The main idea is develop an android app that helps to find the events where free food is available and also give the information of the various dishes available at food places at UMBC. The user of the app can post the the free food events on the campus using this application and the other users can give thumbs up to. The user can choose the free food events  based on the upvotes from the other users. We also have the webservice through which the vendors can add new food items to the database. 
The application is developed using Android Studio IDE and the backend services are hosted in  Amazon Web Services.

**Requirements:**
 
Android phone 

**Architecture:**

Service Oriented Architecture is employed while developing this application. Each component in the app is loosely coupled which makes the  maintenance of the app very easy. Following is the architecture of the project.

![Alt text](https://github.com/NikhilKumarM/Fedme/blob/master/appRelatedImages/architecture.png)

*Architecture of FeedMe Android app*

**REST API’s to communicate with server:**

The app makes the REST API calls to the server in AWS to perform various operations like to get free food events. to get the all the dishes available, to upvote the event, to post the free food events on campus.

**Database Schema:**

The database server is also deployed in AWS cloud. The main tables that are present in the database are user_table, items_list_table, freefood_events_table, upvotes_table and places_table.  

**Used Technologies and Software:**

Java, Android Studio, PHP, XAMP, Mysql, HTML, CSS, JavaScript

**Use case Scenario:**

![Alt text](https://github.com/NikhilKumarM/Fedme/blob/master/appRelatedImages/usecase.png)

*user case*

Free Food event can be posted by the users and other users can upvote that event. The user can see the different food items available on campus with price and the place it is available.



**Future Enhancements:**

 Giving reviews to the individual food items.<br />
 Push notification on the post of every new free food event posted.<br />
 Maintaining the hackos for the user if they post new free food events and hackos can be used to redeem in the on-campus food places. <br />


 
  


