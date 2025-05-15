 we can different type of server like

 apahe web server can do multipule thing
 it can store static data ,  data will be store on disk after first hit it will store on ram , else IO operatio will costly
 it can generate the dynmic data page, it can run perl or phytho scipt
 work as reverse proxy but not a good choice


 #### we have nginx  , which have very much similer as apache web server
 but nginx is good in reverse proxxy

 there is minor difference in both architecture .

 that ngix have one single thread which recive request from clinet or return response .
 after reciving request in single thread ,  singler thread will submit request for async for iput or netwerk access
 ![nginxArchitcure.png](nginxArchitcure.png)
 Where apache web server have  thread pool , there is new thread for every connection
 


???? 

tomcat , jetty , or spring-boot  they work like web continer 