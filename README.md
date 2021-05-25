# How can I test endpoints?
After starting the project, open http://localhost:8080/playground
# Video tutorial
https://www.youtube.com/watch?v=t9He4vHZC24x
# More examples
https://github.com/ExpediaGroup/graphql-kotlin/tree/master/examples/server/spring-server
#Example queries
##Filter by max age
{
    mentors(maxAge: 18) {
        name
        sessions {
            secretData
            time
        }
    }
}
