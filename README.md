This is a skeleton of Spring Boot application which should be used as a start point to create a working one.
The goal of this task is to create simple web application which allows users to create TODOs via REST API.

Below you may find a proposition of the DB model:

![DB model](DBModel.png)

To complete the exercices please implement all missing classes and functonalites in order to be able to store and retrieve information about tasks and their categories.
Once you are ready, please send it to me (ie link to your git repository) before  our interview.



You can find the Postman projects in the postman-project folder with the already written requests that will allow you to test the backend easily. You just have to import the projects in your Postman workspace.

The architecture of the project is, from Controller to DAO :

	1) Api Interface : Used to fix the exchange contract between front and back office.
	2) Controller : Self-explanatory
	3) RestServices : Orchestation services used in the REST application 
	4) EntityService : They are elementary services that manage the entity. They can seem unusefull in this app but they are interesting if the app grows and we need some particular prework before communicating with the Repository, these services will help to do so.
	5) Repository : Self-explanatory


We used DTOs to send the datas to the backend as it is not recommended to direclty use Entities to do so. As the Objects sents and received are the same (copy of the Entities) we do not have an EntityDtoIn and EntityDtoOut but only an EntityDto.
