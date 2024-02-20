# ScrumFlowTracker (Backend)
![scrumbg](https://github.com/Oussama-Aouina/scrum.app.api/assets/92945284/2b9b93bf-c326-4701-9353-def0194555cf)



## Description

Welcome to the backend repository of ScrumFlowTracker, a robust Spring Boot web application catered to Scrum Masters for efficient team management.

This repository contains the backend code responsible for handling team member data, task management, and real-time progress tracking.

This backend repository is designed to work seamlessly with the frontend repository, [ScrumFlowTrucker Frontend](https://github.com/Oussama-Aouina/scrum.app.ui.git), hosted on my GitHub account.


## Features

- [x] Manage team members: Easily select, add, and edit team member information via API endpoints.
- [x] Task Management: Implement endpoints for attaching and updating tasks for each team member.
- [ ] Real-time Progress Tracking: [In Progress] Utilizing websockets or event-based systems for live progress updates.
- [ ] Real-time Chat : [In Progress]Utilizing websockets.

## Technologies Used

- Spring Boot
- Apache Maven
- Project Lombok

## Getting Started

Clone the file and run it on your localhost (don't worry about the data because we're using a cloud Database).

## API Endpoints

### Member Controller

**Create Member**

- Endpoint: `POST /api/v1/members`
- Description: Creates a new member with the provided data and image file.
- Parameters:
  - `memberData` (String) - JSON data containing member information.
  - `file` (File) - Image file for the member.
- Response: Returns the created member object.

**Get All Members**

- Endpoint: `GET /api/v1/members`
- Description: Fetches a list of all existing members.
- Response: Returns a list of member objects.

**Get Member by ID**

- Endpoint: `GET /api/v1/members/{id}`
- Description: Fetches a specific member by their ID.
- Parameters:
  - `id` (int) - ID of the member to retrieve.
- Response: Returns the member object with the specified ID.

**Delete Member by ID**

- Endpoint: `DELETE /api/v1/members/{id}`
- Description: Deletes a member by their ID.
- Parameters:
  - `id` (int) - ID of the member to delete.
- Response: Returns a JSON object indicating whether the member was successfully deleted.

**Update Member by ID**

- Endpoint: `PUT /api/v1/members/{id}`
- Description: Updates an existing member with new data.
- Parameters:
  - `id` (int) - ID of the member to update.
  - `member` (JSON) - Updated member data in JSON format.
- Response: Returns the updated member object.

### Image Upload

**Save Image to Server and Get Image URL**

- Endpoint: `POST /api/v1/members/saveImage`
- Description: Uploads an image to the server and returns its URL.
- Parameters:
  - `file` (File) - Image file to upload.
- Response: Returns the URL of the uploaded image.

**Serve Image**

- Endpoint: `GET /api/v1/members/{fileName:.+}`
- Description: Serves the requested image file to the client.
- Parameters:
  - `fileName` (String) - Name of the image file to serve.

### Tasks

**Delete Task by ID**

- Endpoint: `DELETE /api/v1/members/task/{id}`
- Description: Deletes a task by its ID.
- Parameters:
  - `id` (int) - ID of the task to delete.
- Response: Returns a JSON object indicating whether the task was successfully deleted.

**Get Task by ID**

- Endpoint: `GET /api/v1/members/task/{id}`
- Description: Fetches a specific task by its ID.
- Parameters:
  - `id` (int) - ID of the task to retrieve.
- Response: Returns the task object with the specified ID.

**Update Task by ID**

- Endpoint: `PUT /api/v1/members/task/{id}`
- Description: Updates an existing task with new data.
- Parameters:
  - `id` (int) - ID of the task to update.
  - `task` (JSON) - Updated task data in JSON format.
- Response: Returns the updated task object.

**Add Task to Member**

- Endpoint: `PUT /api/v1/members/task/add/{idMember}`
- Description: Adds a new task to a specific member.
- Parameters:
  - `idMember` (int) - ID of the member to add the task to.
  - `task` (JSON) - Task data to add to the member in JSON format.
- Response: Returns the added task object.

## Database

We're using an AzureSql Database.
The Database access is already setted in the properties file.

## Contact

Email: oussama.aouina@outlook.com 
Linkedin: 
