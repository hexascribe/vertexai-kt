#  VertexAI-KT JVM Sample

This sample exposes some RESTful APIs to demonstrate how the VertexAI-KT SDK can work in a backend environment.

## Setup

Before running the sample, you need to follow these steps:

1. Remove the suffix ".txt" from the `application.properties.txt` file located in `samples/jvm/main/src/resources`
2. Set your Access Token in the `accessToken` variable. For more details, please refer to the [Google Cloud Platform Authentication](https://cloud.google.com/docs/authentication) documentation;
3. Set your Project ID in the `projectId` variable. The Project ID refers to your Google Cloud Project ID.

After you configure, you can run the server with the following command: `./gradlew bootRun -Dserver.port=[port_number]`. Replace `[port_number]` with any unused port number such as `8080`, `8081`, etc.

## How it Works

After running the server, you can start playing around with the following endpoints:

### Prediction Endpoint

This endpoint generates text based on the provided prompt.

##### Endpoint: http://localhost:[port_number]/api/vertex/prediction

##### Parameters:

- `prompt`: The prompt for generating text.

##### Example:

`GET http://localhost:8080/api/vertex/prediction?prompt=Say this is a test`
