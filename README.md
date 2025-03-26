<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Register Project</title>
    <link 
        rel="stylesheet" 
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" 
    />
</head>
<body>
<div class="container mt-5">
    <h2>Register New Project</h2>
    <form method="POST" action="/project/register/confirm" th:object="${project}">
        <div class="form-group">
            <label for="projectId">Project ID</label>
            <input type="number" class="form-control" id="projectId" name="projectId"
                   th:value="*{projectId}" required />
        </div>
        <div class="form-group">
            <label for="projectName">Project Name</label>
            <input type="text" class="form-control" id="projectName" name="projectName"
                   th:value="*{projectName}" required />
        </div>
        <div class="form-group">
            <label for="difficulty">Difficulty</label>
            <select class="form-control" id="difficulty" name="difficulty" required>
                <option value="E" th:selected="*{difficulty} == 'E'">Easy</option>
                <option value="M" th:selected="*{difficulty} == 'M'">Medium</option>
                <option value="H" th:selected="*{difficulty} == 'H'">Hard</option>
            </select>
        </div>
        <div class="form-group">
            <label for="projectDept">Department</label>
            <select class="form-control" id="projectDept" name="projectDept" required>
                <option th:each="dept : ${departments}"
                        th:value="${dept.deptId}"
                        th:selected="*{projectDept} == ${dept.deptId}"
                        th:text="${dept.deptName}">
                </option>
            </select>
        </div>
        <div class="d-flex justify-content-between">
            <a href="/dashboard" class="btn btn-secondary">Back to Dashboard</a>
            <button type="submit" class="btn btn-primary">Register</button>
        </div>
    </form>
</div>
</body>
</html>
