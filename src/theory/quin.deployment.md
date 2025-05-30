we hvae buy a resrouces like VM from OVH , (bareMeteal). which is just 20% of aws. 

and we are handling our thing in this 
we hvae installed kubernates in this devops team is doing all netowkrin resource districtuon and all 
As we are startup we need to do this to save money 

so we have bitbucket versioning tool 
when ever we create a branch , the build will be started 
bitbuket is integrated with jenkins using webhooks , 
we have configure a piple line in jenkins which will first  compile 
then unit test 
Then  check code quality 
then xray 
then push atrtificat (docker image) 
then deploye in spinakker 

then we spinakker intigrated with kubernates 
spinakker pull the image from  docker regisotry and deploy 

we are having one pod for one continer . 
pod is smalled working unit in kubernates 
then pod contian contauer -> continar is nothing it our application or microservices . which contain all thing to run 

we can have service for groups of pods
then we have service-> this service work as reverse proxity . it allow outer world to communicate with our application 

as we can't expose our application to outer world we use ingress 
so in ingress we define the routing rules 
like if call is coming www.quin.advisor  so for advisor it will map to advisor service . 