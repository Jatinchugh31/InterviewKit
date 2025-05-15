we can use cdn  to store the  static data or frequenty used data ,
so we can  increase latency and throuput , 

if there is no data present in cdn  it will will cache miss and it will try to fetch from the server and store 
even if it will fetch from the server  it will be fast 
as cdn use persistent connection ,

persistent connection are those connection which remain open . 
no need to create a new connection on every request 
As conection is open , it will do fast process 

As time will saved to create tcp connection