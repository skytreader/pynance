# Project Base for a Vaadin application

This project can be used as a starting point to create your own Vaadin application.
It has the necessary dependencies and files to help you get started.
It requires Java 8 or newer and node.js 10.16 or newer.

To run the project, run `mvn spring-boot:run` and open [http://localhost:8080
](http
://localhost:8080) in browser.

To update to the latest available Vaadin release, issue `mvn 
versions:update-properties`

Some useful links:
- [Feature overview](https://vaadin.com/flow)
- [Documentation](https://vaadin.com/docs/flow/Overview.html)
- [Tutorials](https://vaadin.com/tutorials?q=tag:Flow) 
- [Component Java integrations and examples](https://vaadin.com/components)

## Database

1. Set up a local PostgreSQL 15.2 instance.
2. Run `sudo -u postgres psql -f schema.sql`. This creates the base database
 used by the application.
3. Run the migrations via `mvn liquibase:update`. This will create the tables
 in their latest schema. Note that this database is still empty!