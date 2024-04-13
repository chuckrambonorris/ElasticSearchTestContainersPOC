## Elasticsearch testcontainers PoC

### Pre-requisites

- Docker, rancher, podman or any other containerization platform
- Access to image repo
- JDK 21
- Gradle

### Getting started

- Clone the repo.
- The integration test is located in `BaseElasticTestContainers`
- If you are using an IDE such as IntelliJ, you should automatically be provided an option to run the test

#### Details

Testcontainers are used for integration testing. The test `persistProduct` seeds some data, makes a REST call to get the
product details.
