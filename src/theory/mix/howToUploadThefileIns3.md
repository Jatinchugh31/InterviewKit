// we can create bucket key  ->   any format like in our case it i  document_id + case_id 

this id will be treadted as bucket 
we will stro  bucket key again document in our system to fetch the document again from s3. 

so we can check the extension and  file size 
file extension we can fetch from like ".exe"  so substring  after (".") 

we will enycrypt the image 
 
we will create the
@Inject
private S3Client awsClient 



private String saveUsingAwsClient(DocumentDetailsDto documentDto, long contentLength, InputStream documentStream) {
PutObjectRequest objectMetadata = PutObjectRequest.builder().bucket(bucketName)
.key(documentDto.getBucketKey()).contentLength(contentLength).build();
PutObjectResponse putObjectResponse = awsClient.putObject(objectMetadata, RequestBody.fromInputStream(documentStream, contentLength));
if (putObjectResponse != null) {
log.info(putObjectResponse.toString());
return documentDto.getBucketKey();
} else {
return null;
}
}
so here we are  setting the bucketName and bucketKey 

bucketName is s3ID
application properties ,
s3:
aws:
region: "${aws_region}"
credentials:
type: default


######  what is credential.type = default 

The `credentials: type: default` configuration you're referring to is commonly seen in AWS SDKs, including in the context of configuring AWS access for services like S3, DynamoDB, or others. Let me break it down for you:

### **What is `credentials: type: default`?**

In AWS SDKs, when you specify `type: default` under the `credentials` section, you are telling the AWS SDK to **use the default credentials provider chain** for authentication. This means the SDK will automatically look for the AWS credentials in a predefined set of places and use the first valid credentials it finds.

The **default credentials provider chain** in AWS SDKs includes several locations where AWS looks for credentials:

1. **Environment Variables**: The SDK will check if your system environment has the following variables set:

    * `AWS_ACCESS_KEY_ID`
    * `AWS_SECRET_ACCESS_KEY`
    * (Optional) `AWS_SESSION_TOKEN` (for temporary credentials)

2. **AWS Credentials File**: The SDK will check the `~/.aws/credentials` file for credentials. This is a file that you configure with your AWS access keys (access key ID and secret access key) on your local machine or in your environment.

    * This file can have multiple profiles, such as `[default]`, `[profile-name]`. If no profile is specified in the configuration, the SDK will use the default profile.

   Example of the credentials file:

   ```
   [default]
   aws_access_key_id = YOUR_ACCESS_KEY_ID
   aws_secret_access_key = YOUR_SECRET_ACCESS_KEY
   ```

3. **IAM Role (if running on EC2 or Lambda)**: If the SDK is running on an AWS EC2 instance or AWS Lambda, it will automatically try to use the IAM role associated with the instance or Lambda function. This is very common when you have services running within AWS that should have automatic access to AWS resources.

4. **AWS Config File**: The SDK will also check the `~/.aws/config` file, which may have additional configuration settings, including region settings or specific profiles.

5. **Container Credentials**: If the SDK is running in an Amazon ECS or EKS container, it can also use the credentials associated with the container.

### **Example Configuration:**

Here’s how your configuration could look in an application’s configuration file (YAML or similar) when you’re using the `default` credentials type:

```yaml
s3:
  aws:
    region: "${aws_region}"  # dynamically set region, maybe from environment variables or elsewhere
    credentials:
      type: default  # Use the default credentials provider chain
```

### **What happens when you use `type: default`?**

1. AWS SDK will automatically use the default method to obtain credentials, so you don’t have to explicitly configure your AWS keys in the application.
2. If no credentials are found in any of the default places, the SDK will throw an error indicating that it couldn't find valid credentials.

### **Common Use Cases for `type: default`**:

* **Local Development**: If you're developing locally and have your credentials set in the AWS CLI configuration (`~/.aws/credentials`), you can use `type: default` without needing to manually pass in your access keys.
* **AWS Services**: When running your application on AWS services like EC2, Lambda, or ECS, you can rely on the IAM role to automatically provide the credentials, so you don’t need to manually configure them.

### **Key Takeaways**:

* **`type: default`** leverages the default credentials provider chain, making it convenient to automatically find credentials in the most common locations.
* You don’t need to hardcode credentials into your application, which is a good security practice.
* Make sure your environment is correctly configured with either environment variables, AWS credentials files, or IAM roles depending on where your application is running.

If you have any more questions or need further clarification, feel free to ask!
