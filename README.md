# Fine Coding Challenge Java Spring Boot

This is a solution to [Fine Legal's coding challenge](https://github.com/fine-legal/fine-coding-challenge-java-spring-boot).  

I did not make a fork because the repo has nothing in it and the API they specify you should use no longer works.  I used the challenge, as an excuse to practice more Spring Boot.   

***

## Directions
The task is to implement a simple REST API that allows to manage leads, users and claims and interact with a simple external API as well as handling persistence.

### A - Model
Implement the following entities: Lead, Deal, User and two types of Deal.
- A Lead has a firstname, lastname, address, city, zip code, phone number and an email address
- A User has a firstname, lastname, address, city, zip code, state, court, phone number and an email address 
- Deal A has a name, description, withdrawals, deposits, amount involved, status 
- Deal B has a name, description, amount involved, gross salary, contract start date, status

Deal Status can be one of the following:
- NEW
- IN_PROGRESS
- CLOSED

Please make use of polymorphism and inheritance where it makes sense.
Please choose the appropriate data types for the fields and the appropriate relationships between the entities as well as the appropriate annotations / strategies for the entities and fields. Describe shortly the reasons why you picked the one picked.

### B - Expose REST API
Implement the following endpoints and behaviour:
- GET `/leads`: Returns a list of all leads.
- GET `/leads/{id}`: Returns the details of a specific lead by its ID.
- POST `/leads`: Creates a new lead.
- POST `/leads/{id}/convert`: Converts a lead into a user and resolves the zip code to the appropriate state based on a fixed map provided via the application properties (limit yourself for some example entries).

- POST `/user/{id}/deals`: Creates a new deal associated with the user. The type of the deal should be specified in the request body.
- GET `/user/{id}/deals`: Returns a list of all deals associated with a specific user.

### C - External API
Implement a service that interacts with a fictional external API provided by https://api.courtdata.io/ and resolves the court based on the zip code.
- GET `/court/zip-code/{zip_code}`: Returns the court associated with the zip code.

The returned payload looks like the following:
```json
{
  "court": "string",
  "zipCode": "string"
}
```

### D - Expose Webhook - No implementation needed
Think about the following scenario and design a solution for it:
Implemented is a webhook that can be called by an external service when there is an update on a deal status.
This service also exposes an API that allows to retrieve the current status of a deal as well as send updates to the external service.

How would you design the webhook and the API to make sure that the external service and our own service is always in sync?

### E - Slicing - No implementation needed
Please describe how you would slice the implementation of the above tasks into smaller services if you would have to implement them in a microservice architecture.
Please think about the logic of the above services a little bigger to make the task more interesting.

### F - Deployment & Operations - No implementation needed
Please describe how you would deploy the above services and how you would operate them in a production environment.

***

## Thoughts

- Best to save zip codes as strings because leading zeroes will be lost with `Integer` types.  
- Phone numbers are similar and I used `string` for `phoneNumber` in `lead`.
- `MappedSuperClass` made the most sense for this original implementation.  
- There is a ton of overlap of fields in `user` and `lead`.  `User` only has 2 more fields than `lead`.
- `Lead` was made a seperate entity although it does not need any more fields that the `Base Entity` class. 
- Having base entities can complicate relationships.  I think base entities can only have `unidirectional` relationships.
- I added the `id` field to the base entity class.  You can have `id` in either the base entity or in the primary class.
- I have looked into both approaches and I did not find any consensus on what is best.   
- I used 2 `one-to-many` relationships between user and deal.  
- `Withdrawals` and `Deposits` could be another relationship, but the directions didn't really specify further.  I made `withdrawals` and `deposits` as booleans.  You don't need both, but my idea is that the `amountInvolved` variable will never be negative.  In a real project, you would have to create extra classes and extra relationships here.  

## Useful Resources

- [PlanetScale](https://planetscale.com/learn/courses/mysql-for-developers/schema/unexpected-types) - unexpected types
- [Medium](https://medium.com/@reetesh043/webhooks-for-beginners-a-simple-guide-to-implementing-webhooks-in-spring-boot-8e7ac3d65356) - webhooks for beginners a simple guide to implementing webhooks in spring boot