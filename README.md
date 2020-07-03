# Getting Started

The objective is to design an application which enables the sale and purchase of a car. The scope is restricted to designing the api’s to enable the model. The proposed business model is that all cars are auctioned and at the end of auction the highest bid placed on the car is deemed as sale price of the car. There are three types of users in the system : Seller, Buyer and Admin. Seller can list cars by providing some basic details about the car like make, model, year, kms driven etc. Seller would also be able to view the state of auction for any car owned by him. He would also be able to view sales price, if it is available. Admin would have the ability to view all the cars listed by any seller and admin can also view the entire bid history on any given car. Admin would have the ability to start, stop or suspend an auction on any listed car. When an auction ends(Stop state only), at that point the highest bid placed on that car is deemed as sale price of the car. Suspended auctions will not have any sales price.Buyer would be able to view details of all cars except those where auction was suspended. Buyer may chose to place a bid in any number of ongoing auctions. Buyer would also have the ability to view his bidding history on any car. 

### StarUp Steps

Used in Memory H2 Database and also persisting its data between apllication start and stop.
Please configure below from your system
spring.datasource.url = jdbc:h2:file:/Users/shubham/Downloads/CarManagement/data/sample

### Guides

PostMan link for API's
https://www.getpostman.com/collections/82fbd847ce2dff60a1dd
