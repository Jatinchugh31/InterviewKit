now we are designing the Twitter like service , 

if we talk major feature in tweeter are 
TWEET , RETWEEt 
Like/ comment/reply
TimeLine/FEED 
Profile Mangment / add user follow/unfollow user 


user will hit the LB  we will choose the round robbin algo for LB 
API gate will make sure about the security and decide which service we are calling 
as it is a single point of entery 

we can have different service Like 

Tweet/Retweet service which is backed by NoSQL and AWS like storage to BLOB images . 
this service will be take care of  CRUD operation on tweet 
we will put rate limiter in front of this as boats will not spam with unlimeted tweets 
we are using the No SQL as it work on CAP , and highly avilable mongp cassandra can be best choice 
we can store this on redis cache for faster 

then  we can use reply like as different service so we can easily scale this service 
and store few comments and caceh as some post can have thousend of comments  
we can store this in noSQL 
if we are mainting the parent child relation then graph date base 


FEED service will work on fan out , means when any friend will do post 
it will be published to  friend . 
we will post these tweet to kafka service and timeLine service will 
consume these messages and store timeLine cache and then using feed service will read this 
cache and new post will be avilable 

in this we can skip accounts with billion of followers so system will not crashed 
we can implement seprate logic for this  we can directly read from data base or any other way 


search servic will direclty do fast query will using elastic search 


profile service will work for  register 
follow/unfollow 
we can use SQL and Graph data base for  relationships 

we can user I AM service for auth 

CDN layer for faster access of most common tweets and images 