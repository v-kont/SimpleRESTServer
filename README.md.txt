Simple REST Server

This folder contains ready for launch simple REST server, simple HTML-client and source code as well.

Usage:

1. Download SimpleRESTServer.jar and SimpleRESTClient.htm

2. Run server by "java -jar SimpleRESTServer.jar" command

3. Now you should be able to communicate with server (via localhost:8080/rest).

For example: run next command in your web browser to view default data:http://localhost:8080/rest?action=query 

This data can be used for later testing server. Or run next command to transfer money from one account to another (use test data):

http://localhost:8080/rest?action=transfer&srccard=4277600104642372&dstphone=123456787&sum=3&msg=2


4. Use SimpleRESTClient.htm page to transfer money by filling it's fields.

5. Run http://localhost:8080/rest?action=query again to check changed data (if operation completed successfully  account balances was changed and new transaction was created).