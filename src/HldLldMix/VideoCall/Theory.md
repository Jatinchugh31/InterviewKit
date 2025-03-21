we have two type of communication protocaol , TCP  VS UDP 

where TCP do handshaking and gurntee to package delivery ,
like  user will send one package and then user 2 will acknowdge that package , 

where UDP is peer to peer connection it is fast but dose;t guntee is package will be deliverd or not , or it will be in the same sequnce 
package may be lost or deliver late due network congesstion 

so per this we can't use TCP as it will be slow for video call , 

so we need to use UDP for video call or streaming. 

but we need to use TCP connection as well for some part of this , 
websocket is our TCP based  protocal ,

Video call use  

WebRTC to communicate . 

WebRTC is commbination of three module 

first is webscoket 
then  Connecter(STUNT SERVER) 
then Conner (TURN SERVER). 

using webscoket we will initate the meeting .  like user will make a request to user b using websocket  , 
if user will accept the call ,
(this webscoket we can use for messaging as well)


then both the user wil make a call conner (STUNT SERVER)  to fetch the  public IP of the users involve the call , 

we will use IP address for communation 
we can do peer to peer call as well , but we will not  use peer to peer connection 
as we need to do video recording or group call as well 

so we will user TURN Server . 
both IP will be store in TURN SERVER . 
TURN Server will recive the packer from user A and user b and transfer to each other 


As  bandth width and network speed can't be same for all the user . 
we will use websocket to share the attribute invovle the call 
like format , speed , encoding key etc 
if in between internet speed drop we will use websocket to change the frame size and all . 

we are webscoket manager to track and manage the session of user invoved . redis for fast acces . 


we can use analytic server for how many time speed is changes , network drop adn other thing 


we will communcate wjth TURN server for recording , and we can save that recording in S# bucket .



it is difficult to find the public IP address 
As every one use private ip address ,  router too have private ip address . 

so connector will call router the Internet service provider for the public address 
And add port number , for communication  