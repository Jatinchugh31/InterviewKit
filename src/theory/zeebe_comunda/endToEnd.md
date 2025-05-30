 we use zeebe comunda , we have  tool for this , where we can desgin our orchastration system  
 
orchastrator system is where we define our define our step and we define our next step based on out put , 
We can do retry , we can do Schedule task , we can handel error gracefully and do proper action on error 

we can do zeebeCLient 
using zeebeClient we can deploy our application in clustor  
we have bpmn file in our resource folder .
ZeebeFuture<ProcessInstanceEvent> instanceIdentifierFuture =
zeebeClient.newCreateInstanceCommand().bpmnProcessId(processId).latestVersion().variables(variables).send()

we can create our define in comunda , and assing task id to every and insert property veraible as well in each task. 

these property can be used in same task or java code. 


https://docs.camunda.io/docs/apis-tools/java-client-examples/process-instance-create/

we can have multipule method  under zeebeClient 
like zeebeClient.newPublishMessageCommand() 
these type of method depends on which type of task you want to do  
zeebeClient.newFailCommand()  when some thing fail we will inform the communda by commands.  

@JobWorker(type = "review-individual-details-task")
public void validateIndividualTask(final JobClient jobClient, final ActivatedJob job) {
      Map<String,Object> var = job.getVariablesAsMap();
      processJobWithValidation(jobClient, job, this::reviewIndividualClient, ActivityNameEnum.PERSONAL_DETAILS);
}

the above method is basic example 

componenet in zeeber 
start event end event   , normal start event, message start event , message end event
process task  rectangle 
we can  message event listner
error listner ,catch on process task 
retry  this is based on properties . 
we can schedule , 
XOR gate , Nor gate . 
XOR gate all request should come ,   nor gate if any one request will reach to the point it will forward


https://4513465.fs1.hubspotusercontent-na1.net/hubfs/4513465/Camunda_2025-BPMN-Poster_EN_dark_24x18in.pdf
in zeebe we can have multpul 
like  ligt cricle is start event 
   dark circle is end event . 



