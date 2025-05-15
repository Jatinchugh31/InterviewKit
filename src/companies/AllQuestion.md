                    interView 1st pure software solutions (10-7-2021)  

Question differnce between @primary vs @Qualifier
Question2 Sort using comparetor like List<Employees> sort using employee.name , departmentname
Question3 write a program using java 8 , get a string into coma seprated values , where length is divisble by 4
Question4 , @Bean vs @Autowire Ans :- https://stackoverflow.com/questions/34172888/difference-between-bean-and-autowired
Question5 , what is microServices
Question6 replace vs replaceAll
Question7 optional.of vs optional.nullableIf
question8 URL for getMapping and postMapping
question 9 feature of springBoot
question 10 how to convert properties file into YML file
Question 11 what is actuator how to use it
Question 12 , how to scale up to microservices
question 13 how to handle faultTalurence if microsrivce went down //we need to implement circuit breaker patttern
question 14 if we overide override hasmap and return 1 then what will happen

==============================================================================
JP Morgan before interview disscussion with VP to check my level (09-08-2021) went well

-- one similirty between wrapper class and String Class. I.e both are immutable
-- how to remove finnaly block I.e try with resource
-- Which Map is best usage I.e HAshMap
-- how to make class immutable I.e check google 6 points to remember
-- how immutable class help in time saving during hashCode caluclate ? i.e by add key is varible of class , for more
info check google
-- how to stop overiding of bean in spring . i.e by using @primary annotation
-- executor class in java . i.e which help to run mutlipule thread in one service like fixedthreadpool ,
cachethreadpool , schduledelaythreadpool
-- reducer in spring 8 . which conver double to int
-- type of test scerio
-- readWriteLock and reentrant Lock java Lock collections
-- Queue pool peek
-- how to find largest string using stream this . stram.sort((s1,s2 ->s1.lentg - s2.length)).first()
or
employeesList.stream().sorted(Comparator.comparing(Employee::getAge)).collect(Collectors.toList());
or
by using reducer

employeesList.stream()
.reduce( (e1,e2)-> (e1.getAge() < e2.getAge()? e1:e2))
.ifPresent(System.out::println);

data base query
table
name id salary department
-- select all the name ends with Chugh
-- select max salary
-- select emplyes with max salary
-- select department with the highest employee

============================================================
JP morgan round 1 and 2 questions comment final round se out
-- == vs .equals
-- flatmap vs map in java 8
-- example of how to use java 8 example
-- how to implement rest api
-- how to add authroization
-- how to validate rest call is valid
-- crone jobs and schdulers
-- list vs set
-- exception handling in inheritence
-- improvment in hashmap in java 8

round 2
-- funtional interface in depth
-- optional ,and advantage of this
-- predicates
-- hashmap internal working
-- how to use class as key in map
-- java 8 stream in deapth , goruping by
-- design pattern
-- singlton patter Lazy and eager
-- garbage collector , memory mangment
-- dimond problem how to resolve dimond problem using defaults method in interface
-- executors in collection
-- internal working of set
-- how to implment queue using two stacks
-- All the spring anotation example rest , exception , controller advice
-- how to check memory leak , performece tunning  
-- reverse a string in one ittration withot using any thing
-- recursion vs itttration which one is better
-- fabbnoci sries usingAA recursion
-- design principal

=================================
Kafka properties files
what is offset
how to findmissing offsets

=========================================
Samsung 06 oct 2022 comment clear offer letter

-- Feature of springBoot
-- How to change embded tomcat
-- Write the RestControler with Post implmention.
-- how to validate API request
-- Global error Handling in Rest api I.e @controlerAdvice
-- Annotation used for spring JPA
-- Life cycle of JPA
-- how to setTime out of DB query
-- How to configure database
-- stack using array
-- get dublicate eliment for the list
-- Repostries and build tool
-- Rest call authentication
-- circuit braker ()
-- Spring Security
Basic of oops with example aggregation , composition etc
disadvantage of micro services
===================================
EPAM 22/11/2022 comment :- not answer react question
--java 8 feature
--functionaol interface , how to call static and default method
--load balance factor of hashmap
--equal and hashcode relate with each other.
--supplier and consumer
--cacheThreadPool internal working
--singlton with enum
--character and its count in map using java8
Ans :- Map<String, Long> result = list.stream()
.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

--sudo code for how to test rest point unit test case
--cdli pipeLine
--Solid principal
--strategy design pattern
--kubernates
--authentication and autherization
--memory differnce between java 8 and java7.
--React js version
--cors
--write a code of react js

====================================
OSTTRA(IHS Market) 23/11/2022 comment stuck in programing
programing question only
get the count of distinct number
then fill in the array based on number of occurence;
input int [] arr ={2,2,3,4,2,4,3,4,2,3,12,4,2};
output int arr2[] ={2,2,2,2,2,4,4,4,4,3,3,3,12};
basic design pattern questions;
and micro services

KUBRNTE config file ,// check ai-engine service and deployment file
//sort map based on values ,
//(comparingByValue is default method of Map.Entery
Map<String, Integer> sorted = budget .entrySet() .stream() .sorted(comparingByValue()) .collect( toMap(e -> e.getKey(),
e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

//
==========================================
ACS Areaa 23/11/2022 comment interview went well
All basic question from the above question
Api gateway;
circuit braker pattern
Find out the commonSubtring from the list of string

authentication and authrization
// for this we need to explain the spring security AuhtriationFilter and authenticationFilter
// or @EnablewebSecutiry WebSecurityConfigurerAdapter to implement

=======================================
AllState 2/12/22
program question
first -> find out the index which can return sum 10 ,
1,3,5,3,7,3,2,6
Answer :- https://www.geeksforgeeks.org/given-an-array-a-and-a-number-x-check-for-pair-in-a-with-sum-as-x/

second
-> find out the number which come only once usnig java 8 from the above input;
Answer :- List<Integer> out = list.stream().collect(Collectors.groupingBy(Function.identity())).
values().stream().filter(k -> k.size() == 1).collect(Collectors.toList()).get(0);

circuit braker pattern
annotations in spring boot
what is restfull apis;
docker , docker file , docker.compose.yml
kubernate
predicate
hashset and treeSet
feature of java8
concurrency
what is microservices
differnce between component and service
comparter vs comparable
===============================================
second roud (Manger round)
how to validate custom header of rest call
aNS-> https://www.yawintutor.com/how-to-validate-request-header-in-spring-boot/
voletile keyword
syncrnoize block
basic program get emp name whose salary is < 100000
scrum related questions
================================================
Health cart 16/12/2022
get this subsTring from substring will not contain dublicate , s = "pwwkew"  ans -> kew

basic core java question MCQ questions related to overloading , typecasting , upcasting and downcasting
inheritence , abstract ,default vs protected package etc  
=============================================================================================
sopra sterie 17/12/22 round 2
programing question using java 8,
//You are given an array of positive integers, arr, of size array_length.
// You are asked to build set S which consists of the LCM of every pair of adjacent elements in arr.
// Your task is to find the largest element in set S.
// For example, for the array [1, 2, 3, 4], set S = { lcm(1,2), lcm (2,3), lcm(3,4) } = {2, 6, 12}. The largest element
is 12
// LCM 4 * 6/ highest Common facotr = LCM =24 / 2 =12;

CI/CD pipeline and differnce between Ci and Cd;
docker images
microservice design pattern
====================================================
globalLogic 17/12/22 round 1

filter the string based on length ,
sort a employ based on salary and id

how to exclude autoconfiguration class
@qualifire and @primary
how to manage transaction
design pattern cross question(where to use adapter pattern)
solid principal
what is composite key and hwo to create it
how to manage distribuated transaction (using saga design pattern)
embdded embdebble annotation
what is kafka and what is offset
=====================================================================
GlobalLogic second round 20/12/
ArrayList internal working
LinkList
HashMap internalWorking , Load factor
syncronization , how to make class syncronize
how to make hashMap syncronization
concurrentHashMap
FunctionalInterface   
if parent class have one abstract matheod and child class dosent have any method and implementing the Functional
interface then what will happen
if parent class having static method and child class having static method and make object of child class , and call
method what will output

what is inermididat and terminal operations
when to use stream
what is flatmap
what is predicate

globalException
postcall
postman to controler is serilization or desrilization

how to create two database
what is significant @enableTranctionMangent and @enableJpaRepositry
how to create many to many
how to create jpaquery for distincnames

authenticationa and authrization
==> what is discoveryagent
eurekaclient and server
circuit breaker
api gateway
how to deploy the component
loadbalencing

principal of rest services design
weakhashmap
how you will expose your serives to outer world

=====================================================================
gemniSolution 19/12/22
ways to create object
shellow copy Vs deepCopy
why we have default method in Functional interface
example of marker interface
working of hashSet
transient vs voletile keyword
annotation in springboot
ways to inject bean
//print 2D array using Stream  
WAP to Convert 2 d array to single string using Java streams.
int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
Output:
1 2 3
4 5 6
7 8 9
//
get this subsTring from substring will not contain dublicate , s = "pwwkew"  ans -> kew.
how to use class as key in map

==========================================================================================
gemniSolution second round
what is microservices architecuture
write a program to filter a string with "J" and append chugh and get the result in List.
how to make class immituable , what will happen if dosent make our class as final
intrnalWorking of LinkedHashMap , treeMap , timecomplexity
which framework of spring you have used
how to depoy the image
which software devlopment cycle you are using
which software architecutre you are using.
==========================================================================================
AKQA 29/12
disadavantage of microservices
advantage of springboot
cohason and coupling in microservice
how you will break your application in microservices
what do you mean by stateless
stringbuilder and stringuffer
stringbuffer is syncronuzed where stringbuilder is not
why strings are immuitable
how to change the mysql to oracale
write a program to check valid parnthsis
write a program to get numbers and it count
solid principal
strategy design pattern , disadvantage of this
make a system design for tik tak tok(zero katta) game
Indexes in my sql , how to create which type of fields we can use
throw stackOverflow error , heap outof memory error;
program from java call by value for refernce
prorgam to make immutable class
where we can use our immutubale class
spring dependency vs inversion of control

===========================================

airtel
make new binary tree where parent is containg sum of chid
get all parmuatation of array
system design to create(give url to see the information) get and delte the url after some time  
===========================================
MAgicedtech,
Merger sort or quick sort , (No answer)
ssoopppp . character and its frequency
implement post call and persist into data base , explain all the lairs
query
employe , name , salary, manager.
Manger_id , salary .
get the emplaoyee jiski salary greater then employee
comparotre and comparable
design pattern , write code for strategy design pattern

==============================================
program
stack using LinkList
LRU cache implementation
string of 100000 words find the count of I = 100 am=500 Jatin =100
in less space and On
regular expression program
what cyclic excpetion in spring
n +1 problem in hibernate
namedQuery in jpa or hibernate
how to build image , what is -t in docker build -t ?
JWT Token which algrothim for -> HMAC512 we will pass secret in this to decrypt
how decrypt
how to get the completed thread from executoe thread pool(completionService we can make a thread pool of
ExecutorCompletionService)

============================================
Question given by HR ,
Reading Material-
Can one use an Employee class as a key in a HashMap? (This is a very important question.
If this is not answered satisfactorily, mark the candidate as knowledge gap as best case scenario irrespective of
answers to other questions / scenarios)

You are using a class from a library (say Student). You have a list of Student objects.
You need to sort this list based on first name. How will you do it? Constraint: (You do not have the ability to change
the source code of the Student class)

Consider a class Person with two attributes - String name and List<String>degrees. How will you make this class
immutable.
Ask what are the advantanges of immutable classes. (If candidate is unable to answer atleast 2 of below questions,
they should be marked as knowledge gap irrespective of answers to other questions / sceanrios)

Consider a class A with a synchronized method class A { public void synchronized m1() {Thread.sleep(5000);} }
We create two objects of this class - o1 and o2. We call o1.m1() on one thread and o2.m1() on another thread, at the
same time. What will be the behaviour?
Follow up with - how will you force these calls to execute one after the other

We have a Parent class with 2 methods walk and run. We have a child class which overrides both the methods. Both child
class methods just call their respective Super implementation. Parent class run method calls walk(). class Parent Parent
p = new Child(); p.run(); Tell the order in which each method is called
Given a List of integers (List<Integer>), write code in Java 8 style to get the sum of the squares of all the odd
numbers in the array.

Explain what the following command does on Unix: chmod 764 file1

We have a table called BookAuthor. It has two columns Book and Author, Book being unique column. Write a query to find
the names of the authors who have written more than 10 books.

Given an array of n integers and a number k, find the pairs of numbers in the array such that the difference between the
pair is k. Find the optimal solution with and without extra
storage

Split an array into chunks with a specified size

Can one use an Employee class as a key in a HashMap? (This is a very important question

Employee Table -- Id -- Name -- Salary -- Department id Find one employee from each department who is getting the
highest salary within his department.
Discuss further on server side pagination, boundary cases like 2 emp getting the same salary in the same department
which is highest etc.
[NOTAPPLICABLE] Knows about server side pagination
[NOTAPPLICABLE] Able to provide query for the problem including the boundary cases - like 2 employees getting same
salary in a department which is highest.

.

https://malisper.me/an-algorithm-for-passing-programming-interviews/

Write a program to convert camel case string to snake case string. whatIsYourName ->

Which Data structure will be used when we want to fetch the list of students by dob range (start date, end end)? TreeSet
or TreeMap or Binary Search Tree

Explained - What will happen when multiple students have same dob Which DataStructure should be used to search
effectivity by student id and
also to print the list whenever required in the same order we read from the file. LinkedHashMap or combination of
multiple data structures like List+HashMap

] Explained the difference between Runnable and Callable. What are the pros and cons


Consider a class A with a synchronized method class A { public void synchronized m1() {Thread.sleep(5000);} } We create two objects of this class - o1 and o2. We call o1.m1() on one thread and o2.m1() on another thread, at the same time. What will be the behaviour? Follow up with - how will you force these calls to execute one after the other
============================================================================================================

Turvo Logestic hydrabad 21/12/2022
only program round :
// People usually text stressed words when they get excited.
// A stressed word means one or more characters appear more than they should.
// Ex: hellllooooo -- l and o are stressed characters here.
//
// Problem Statement:
// ===================
// Write a function which takes the string as input and
//return details of stressed characters and the range they got stressed
//
// Ex: For "hellllooooolllll", returned data should contain below information
//
// Character, start index and end index of stressed range
// EX :
// l stressed from index 2 to 5
// o stressed from index 6 to 10
//
// Note: Consider a character is stressed if it appears more than 2 times continuously.
// Use any collection or any custom data structure as return type.

second is clone the LinkList

============================================================================================
kafka class for consumer

@KafkaListener(topics = "${kafka.topics.genericshipmentinboundtopicname}",
containerFactory = "kafkaListenerContainerFactory",
groupId = "${kafka.topics.genericshipmentinboundtopicname}" + "${kafka.groupId}",
id = "${kafka.topics.genericshipmentinboundtopicname}", idIsGroup = false)
public void shipmentListener(String payload,
@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
@Header(KafkaHeaders.OFFSET) int offset, Acknowledgment acknowledgment,
@Headers MessageHeaders messageHeaders) throws Exception {}

kafkaCLass for producer

class{
@Autowire
kafkaTemplate<String ,string > template

send(String message , string key , string topicname){

ProducerRecord<String ,String>  record = new ProducerRecord(topicname,kay , payload);

template.send(record  )
}
}

============================================================================================
Addidas

Parking Lot system design

CAP therom  (System design)
C -> Consistency
A -> Avilablity
P -> Partition

CAP model is all about to achive Consistency , Avilablity and partition in the distributed system
As many says to achive all three together is not possible , so we need to give on one
in most of the case Partition is always improtent so we need to decide on which we can give up
consistency or avilablity  
we have degree in this in which can maintain all three in some instant like 90:80

it all depend on use case like if paymnet distributed or ticket book in this consistency is importent
if just showing number or item , like number of views on you tube or item on website(not there inventry) then can
100 avilable and 80 consitent
and
if client have high budget hen we can achive all by giving backup for network failur(Partition)
we can switch one network to another when it fail , but it will be complicated to costly
https://www.youtube.com/playlist?list=PLTCrU9sGyburBw9wNOHebv9SjlE4Elv5a

--> richardson maturity model
this model tell if our api are fullyRestFull or not we have 4 Level in thos
Level 0 -> sop API , when entry point in the application and action will be defined in the body
LeveL 1 -> we have different UI to enter in the application but action of those URI is not defined
Level 2-> different entry point , plust proper HTTP MEthod and restponse, get put post
Level 3 -> is fullyRestFull above three point + HATEOS in this we send the next state as a url in the reponse

=============================================================================================

Spring security
Spring Secutirty

there are 5 things we need to remember in spring security
1st Authentication
2nd principal
3rd Authrization
4th grantedAuthority
5th role

authentication can be of different type :- username password, token , Oth, APIKeys etc
prinicipal ,when login user switch from then these principal should change
Authrization, is done by grantedAuthority and roles

dependency ->Spring-boot-starter-security
if we just add the dependency it will give the login form  
using the filters (BasicServelFilter)(springsecurityFilter) spring is giving us this login page

default behaviour of spring security

1.Add Mandatory authentication for all urls
2.Add login form
3.Handles login error
4.create default user and password , you will get it from log when ever you start the application
to update this default user/password , we can set properties
spring.security.user
spring.security..user.password

configuration to validate actual user and password

there is a class authenticationManager which have method authenticate() which do all this authentication ,
but we will build the authenticationManagerBuilder and set the configuration on it

we can create our config class and @EnableWebsecurity and extend that class with WebSecurityConfigurerAdapter.
WebSecurityConfigurerAdapter have many method which we can overRide , for authentication and autherization

Like we can overRide method which take argument authenticationManagerBuilder , in this object we can set the  
UserDetailsService . And we need to give the implementation of this interface(UserDetailsService) and overRide the
method called loadUserByName() in which we need to give the object of UserDetails which is predefined entity of spring
which contains fields like grantedAuthority , user/password , active user

and for autherization we can overRide the method which will overRide the method which take httpSecurity object
in which http.authrizeRequer().antMatcher(urlPath).hashRole()

we can create a bean of passwordEnocdder and return the encodder which we want to use

Outh - is autherization
============================================================================================

https://javarevisited.blogspot.com/2015/10/133-java-interview-questions-answers-from-last-5-years.html#axzz7UNJl6uEA

======================================

Task scheduling(Greedy approach)
Activity selection problem(Greedy)
Palindrome of a string
Infix to prefix
Builder design pattern implementation
You are given an integer array coins representing coins of different denominations and an integer amount representing a
total amount of money.Return the fewest number of coins that you need to make up that amount. If that amount of money
cannot be made up by any combination of the coins, return -1.You may assume that you have an infinite number of each
kind of coin.
Longest palindrome in an linked list
Component and configuration annotation difference
How exception handling works in spring boot
Method to persist large amount of data in sql db
Hibernate colon groupby join related sql query
database http networking and write a sample endpoint controller/service layer
Find all subset of given sum in an array
Trie implementation
java can we override static method
few features of Java 8
Functional interface & lamba
Treemap/hash map
Array list/linkedlist
Comparable/comparator
Garbage collection
Optional in java 8
Spring-
B/w component and configuration
Dependency injection
Find a word in a file of string
Give difference approaches to find a loop in linkedlist and tell which one is the best approach and implement that

CAP,

Consistency Patterns in Distributed Systems
caching ,

abstract vs interface
has vs tree
oops
final , keyword
class , objects
procces vs thread
CAP
index
system design
auto boxing
string
grabage collector ,
optmisation ,
memory allocation
default
outof memory
thread dumps ,
memory lek
deadlock ,

data basa schema  (parking )
association.
suystem design.

.....
coading..

Data Structure
Access
Search
Insert
Delete
Best Use Case

ArrayList
O(1)
O(n)
O(n)
O(n)
Fast random access

LinkedList
O(n)
O(n)
O(1)
O(1)
Frequent insert/delete


HashSet
-
O(1)
O(1)
O(1)
Unique unordered elements

TreeSet
O(log n)
O(log n)
O(log n)
O(log n)
Unique sorted elements

HashMap
O(1)
O(1)
O(1)
O(1)
Fast key-value lookup

TreeMap
O(log n)
O(log n)
O(log n)
O(log n)
Sorted key-value pairs

PriorityQueue
O(1)
O(n)
O(log n)
O(log n)
Priority-based tasks

ArrayDeque
O(1)
O(n)
O(1)
O(1)
Stack & queue replacement





