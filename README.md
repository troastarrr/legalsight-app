# Legal Sight App : Speech API

The Speech API provides endpoints for managing speech transactions.

## Configuration

To configure the Speech Controller, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Configure the application properties located in `src/main/resources/application.properties` according to your
   environment. You may need to update the database connection details, server port, or any other relevant
   configuration.

## Building

To build the application using Maven, follow these steps:

1. Open a command-line interface.
2. Navigate to the root directory of the project.
3. Run the following command to build the application:

   ```shell
   mvn clean install
   ```

   This will compile the source code, run tests, and package the application into an executable JAR file.

## Running

To run the application using Maven, follow these steps:

1. Ensure that the application has been built successfully using the previous "Building" step.
2. In the command-line interface, navigate to the root directory of the project.
3. Run the following command to start the application:

   ```shell
   mvn spring-boot:run
   ```

   This will start the application, and you will be able to access the API endpoints from a web browser or API testing
   tool.

4. Once the application is running, you can access the API endpoints by sending HTTP requests to the appropriate URLs,
   as described in the "API Endpoints" section of this README.

## API Endpoints

### Find Speech

Retrieves an existing speech by ID.

- **Endpoint:** `GET /speeches/v1/{id}`
- **Description:** Retrieve an existing speech by ID.
- **Tags:** Speech

### Create Speech

Creates a new speech.

- **Endpoint:** `POST /speeches/v1`
- **Description:** Create a new speech.
- **Tags:** Speech

### Update Speech

Updates an existing speech by ID.

- **Endpoint:** `PUT /speeches/v1/{id}`
- **Description:** Update an existing speech by ID.
- **Tags:** Speech

### Delete Speech

Deletes an existing speech by ID.

- **Endpoint:** `DELETE /speeches/v1/{id}`
- **Description:** Delete an existing speech by ID.
- **Tags:** Speech

### Search Speeches

Retrieves a list of speeches based on the provided filter.

- **Endpoint:** `GET /speeches/v1/search`
- **Description:** Retrieve a list of speeches based on the provided filter.
- **Tags:** Speech

### Find All Speeches

Retrieves all speeches.

- **Endpoint:** `GET /speeches/v1`
- **Description:** Retrieve all speeches.
- **Tags:** Speeches

## Request Parameters

The following request parameters are used in the API endpoints:

- `id` (path variable): The ID of the speech.
- `author` (query parameter, optional): The author of the speech.
- `speech_text` (query parameter, optional): The text content of the speech.
- `subject_area` (query parameter, optional): The subject area of the speech.
- `speech_date` (query parameter, optional): The date of the speech.
- `per_page` (query parameter, optional, default: 10): The number of results per page.
- `page` (query parameter, optional, default: 1): The page number.
- `sort_by` (query parameter, optional, default: author): The field to sort the results by.
- `sort_dir` (query parameter, optional, default: desc): The sorting direction (asc for ascending, desc for descending).

## Response

The API endpoints return various response types based on the operation:

- `ResponseEntity<Speech>`: Response containing a single speech object.
- `ResponseEntity<SpeechesReponse>`: Response containing a list of speeches with pagination information.
- `ResponseEntity<Void>`: Empty response indicating a successful deletion.

## Error Handling

The Speech Controller handles the following error scenarios:

- `SpeechNotFoundException`: Returns a 404 Not Found response with an appropriate error message.
- `MethodArgumentNotValidException`: Returns a 400 Bad Request response with validation error messages.
- Other Exceptions: Returns a 500 Internal Server Error response with an error message.