
This project created based on free api site => https://freeapi.hashnode.space/api-guide/apireference/getUsers

1. Every 5 to 10 free api dbs will be deleted. if you have time please check the free api site. it has the all the fetch APIs to learn
2. Now on my project under the Feature folder test cases present. for eg: open the Register.feature file and copy the (@successfulRegistration) tag
3. And open the src/test/java/runner/TestRunner.java file past the tags. you can also run multiple tags (eg: tags = ("@successfulRegistration or @loginValidCredentials"))
4. user the src/test/java/com/steps folder the test cases methods present
5. The AppData.json once you run the (@successfulRegistration) tag. The payload data will be added to this file for further login process
6. Once you run the script under the target/Reports folder where you can find the reports