compnent used 

user  , 
WebSocket Handler 
websocket manger 
messages service 
Asset service 
Group handler 
group service 


FR ->   one to one message 
group message 
pics/video/docs
last seen 
dilver reciept 
read receipt 


Non fun  very very low latency , allways available 



user can send message 
websocket handler will  send and recicve the message 
webSocket manager will contain the information aout which handle is connected with which user as we can have thousend of handler 
manger will store the information on redis as user can  frequently  go off , or network issue so new connection can be created . 

when user will send the message , it will go to handler to then handler will call the message service. 
message service will store the information in cassandra . 

then web socker will check user 2 connection and try to send a message . 
if user online  it will send the message 
and it will send the delivery recipet to user 1 . 

if user 2 recive the message it will send the information message read and then read recipet will be send in the same way to user  1 

if user 2 is offline we will store the message in temprory in data base 
when user 2 came  online it will call the message service if there is any message 
and then will same way  send the deliver and read recipts, 

we can use message service as last seen service   app will send the heart beat , which will update the time stamp 
in the last seen if last seen  - current time greater then 1 min then user will be showen last seen else green 

for the group ,
user will send a message to   websocket it will call  to message service
it will check the type 
if  it is group type it will create the kafka message amd group handler will read and call the group service about group user infor 
and then same way  send message using handlers 


for documents we will encrypt at the user  end and pass the document to asset service which will give the  doc id 
and that doc if will be send over the handler 
if user click on download it will call the asset service based on doc id 
we can use hash for the same 
