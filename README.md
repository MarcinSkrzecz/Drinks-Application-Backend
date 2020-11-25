Drinks Application Backend

Link to Frontend - https://github.com/MarcinSkrzecz/Drinks-Application-FrontEnd.git

Good to use after launch to test - http://localhost:8080/swagger-ui.html#/

Application instruction after launch:

###Drinks Entity - Possibility to:
- Create new Drink;
- Update existing Drink;
- Delete existing Drink;
- Get All existing Drinks;
- Get Drink by Id;

###Comments Entity - Possibility to:
- Create new Comment to Drink;
- Update existing Comment;
- Delete existing Comment;
- Get All existing Comments;
- Get Comment by Id;
- Like Comment;

###Ingredients Entity - Possibility to:
######It's fully automated and get ingredients used in Drink and count how many times they were used;
- Get All existing Ingredients;
- Get Ingredient by Id;

###Rating Entity - Possibility to:
######It's fully automated and get rating from comments and calculate average rating for selected drink;
- Get All existing Ratings;
- Get Rating by Id;
- Delete Rating;

###Trello API
######It's fully automated and based on setup send data to trello board and emails;
- Drink rated to selected in application.properties with enough comments;
- Proposed by Users Ingredients;
- Contact Us;

###Recipe Puppy API
- List of all Ingredients from Recipe Puppy filtered to not duplicate ingredients already used in Drinks;
- Propose ingredient by User;
- Vote on Ingredient;
- Change status of Ingredient - Partially automated till enough votes. After create Drink with this ingredient by Admin it give possibility to change status;
- List of all proposed ingredients by users with how many times they were voted by users.

###Proposed Ingredients
- Get list of all proposed ingredients;
- Get proposed ingredient by Id;
- Create Ingredient;
- Vote on Ingredient;
- Delete Ingredient;
- Change status of Ingredient to Completed;