## Tasklist
The goal of this application is to allow users to submit tasks they would like to complete, and track their progress over time.
Each task can be assigned a title, a description, and an optional due date.

This is a new application, so far we've wired up:
- Basic authentication (see `SecurityConfiguration` and the `user` package for more details.)
- A simple rest controller to manage tasks (see `TaskController`)
- Database support with an embedded H2 database, JPA, and flyway

### Instructions:

Now that we have a basic shell of an application, we need features! Your goal is to implement the following features:

**Story 1**

As a task list user, I want to be able to mark a task as complete, so it doesn't show up on my list of things to do
 - [ ] Tasks should have a boolean property to indicate if they have been completed.
 - [ ] Users should be able to mark a task as complete / not-complete via a REST call
 - [ ] Users should only be able to modify tasks associated to them
 - [ ] Tasks should still be returned by API when complete; eventually we will create a UI that hides completed items.

**Story 2**

As a task list administrator, I do not want all users to be able to see all tasks. 
 - [ ] When an admin user invokes a GET to "/tasks" they should see all tasks in the system 
 - [ ] When a normal user tries to access this endpoint, they should be forbidden.
 
**Story 3**

As a task list user, I want to be able to search for a task by its title.
 - [ ] An endpoint should exist that allows me to search for tasks by their title (something like /task/search)
 - [ ] I should be able to supply search criteria to the endpoint, such as a complete title or an individual word
 - [ ] Search should be case-insensitive (e.g. if the title is "Mow the Lawn", I should be able to find it with the keyword "MOW")
 - [ ] The task endpoint should only return tasks for the authenticated user, you should not be able to search for other people's tasks.
 - [ ] Tasks should be sorted by their due dates, soonest first. Null due dates should sort to end of list.
 
Notes/Tips: 
- As a company we value clean, concise, thought-out code. 
- Feel free to use online documentation and examples to guide your solution. Try to attribute code properly, and don't violate any licenses. 
- You are not limited to frameworks and technologies present in the application -- if you want to include additional libraries, go for it!
- Kotlin is available on the classpath. If you are a fan of Kotlin, you are encouraged to convert / use Kotlin to your heart's content.
- We love TDD! It is not required to write tests for the new functionality, but very much appreciated.
- Flyway is being used in the project. To make database changes, please consult its documentation https://flywaydb.org/documentation/migrations#versioned-migrations.
