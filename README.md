# Legal Sight App

# Speech API provides endpoints for managing speech transactions.

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
- `ResponseEntity<SpeechListResponse>`: Response containing a list of speeches with pagination information.
- `ResponseEntity<Void>`: Empty response indicating a successful deletion.

## Error Handling

The Speech Controller handles the following error scenarios:

- `SpeechNotFoundException`: Returns a 404 Not Found response with an appropriate error message.
- `MethodArgumentNotValidException`: Returns a 400 Bad Request response with validation error messages.
- Other Exceptions: Returns a 500 Internal Server Error response with an error message.

Please refer to the respective API endpoint descriptions for more details on their functionality and usage.

Feel free to customize and enhance this README file to provide more specific information or instructions based on your
project requirements.