<%@ page import="in.sp.dao.StudentDAO" %>
<%@ page import="in.sp.controller.Data_store" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .no-records {
            text-align: center;
            font-size: 18px;
            margin-top: 20px;
            color: #777;
        }
    </style>
</head>
<body>
    <h1>Student Registration History</h1>
    <%
        // Create an instance of StudentDAO to fetch student data
        StudentDAO dao = new StudentDAO();
        List<Data_store> students = dao.getStudents();
    %>
    <%
        if (students != null && !students.isEmpty()) {
    %>
        <table>
            <tr>
                <th>Name</th>
                <th>Roll</th>
                <th>Class</th>
                <th>Admission Date</th>
            </tr>
            <%
                for (Data_store student : students) {
            %>
            <tr>
                <td><%= student.getName() %></td>
                <td><%= student.getRoll() %></td>
                <td><%= student.getStudentClass() %></td>
                <td><%= student.getAdmissionDate() %></td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <p class="no-records">No records found.</p>
    <%
        }
    %>
</body>
</html>
