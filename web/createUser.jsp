<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="loilt.user.CreateUserErrors"%>
<%
    CreateUserErrors errors = (CreateUserErrors) request.getAttribute("CREATE_ERRORS");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create User - Error</title>
    </head>
    <body>
        <h2>Create User</h2>
        <form action="DispatchController" method="POST">
            User ID:
            <input type="text" name="txtUserId" />
            <span style="color:red;"><%= errors != null ? errors.getUserIdNotValid() : "" %></span><br/>

            Password:
            <input type="password" name="txtPassword" />
            <span style="color:red;"><%= errors != null ? errors.getPasswordNotValid() : "" %></span><br/>

            Confirm Password:
            <input type="password" name="txtConfirm" />
            <span style="color:red;"><%= errors != null ? errors.getConfirmNotMatch() : "" %></span><br/>

            First Name:
            <input type="text" name="txtFirstName" />
            <span style="color:red;"><%= errors != null ? errors.getFirstNameNotValid() : "" %></span><br/>

            Last Name:
            <input type="text" name="txtLastName" />
            <span style="color:red;"><%= errors != null ? errors.getLastNameNotValid() : "" %></span><br/>


            <input type="submit" value="Create" name="btAction" /> <br/>
        </form>
        <a href="login.html">Back to login!!!</a>
    </body>
</html>
