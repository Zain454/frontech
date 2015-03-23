# frontech

### usage comeon1

**in comeon1 project directory**
> mvn clean install

> cp target/comeon1.war $TOMCAT_HOME/webapps/

**in comeon2 project directory**
> mvn clean install

> cp target/comeon1.war $TOMCAT_HOME/webapps/

**after deployment war files**

run tomcat server 

open in browser [http://localhost:8080/comeon1/](http://localhost:8080/comeon1/) 
and [http://localhost:8080/comeon2/](http://localhost:8080/comeon2/) pages

**update x period parameter**

open in browser [http://localhost:8080/comeon1/update/?period=4](http://localhost:8080/comeon1/update/?period=4)

**update y period parameter**

open in browser [http://localhost:8080/comeon2/update/?period=4](http://localhost:8080/comeon2/update/?period=4)