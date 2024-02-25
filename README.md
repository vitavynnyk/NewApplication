This Spring Boot application provides an API endpoint to retrieve information about GitHub repositories.

API Endpoint
Get Repositories
Endpoint: /api/github/repos/{username}
Method: GET
Parameters:
{username}: GitHub username for which repositories are to be retrieved
Example Request: curl http://localhost:8080/api/github/repos/{username}

Handling Not Found
If the specified GitHub user is not found, the API will respond with a 404 status and an error message