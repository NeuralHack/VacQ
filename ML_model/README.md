We have  created two ML models and have followed two approaches to to train our dataset :-

Due to unavailability of realtime data, the data used in this tutorial is sythetic data hence it cannot used to predict real world seneraios.
however if we get access to the real data, we can train our model on it.

ML Model_1 :-

We have made 1000 datapoints with features Gender, Age,	Diseases,	Zone and Profession . The Priority is taken as 1, 2 and 3 . 
We have used RandomForestClassifiers() to train our dataset .
Our Model gets the accuracy of 72.5 % which is pretty good.

ML Model_2 :-

In this we have taken a synthetic dataset having 10714 rows and 11 features . It had features such as people_ID,	Region,	Gender,	Occupation,	Mode_transport,	comorbidity,	Age,
Insurance,	salary,	FT/month, Infect_Prob . The concept of this model is to predict the infection probability of any individual given these information as our dataset comes with infection probability
for each data value.
We abstracted useful features from our dataset and trained our model with RandomForestregression()
With this method we got accuracy of 91.45%

We conclude that approach 2 was better than approach 1
