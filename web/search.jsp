<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search User Page</title>
    </head>
    <body>

        <c:if test="${not empty sessionScope.USER}">
            <c:set var="firstName" value="${sessionScope.USER.firstName}" />
            <c:set var="lastName" value="${sessionScope.USER.lastName}" />
            <c:set var="role" value="${sessionScope.USER.role}" />
            <h1>Welcome ${lastName} ${firstName}</h1>
        </c:if>

        <form action="DispatchController">
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" placeholder="Enter last name..." />
            <input type="submit" value="Search" name="btAction" />
            <input type="hidden" name="txtRole" value="${role}" />
        </form>

        <c:if test="${not empty param.txtSearchValue}">
            <c:choose>
                <c:when test="${not empty SEARCH_RESULT}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>User ID</th>
                                <th>Password</th>
                                <th>Last Name</th>
                                <th>First Name</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${SEARCH_RESULT}">
                                <tr>
                            <form action="DispatchController" method="POST">
                                <td>
                                    ${user.userId}
                                    <input type="hidden" name="txtUserId" value="${user.userId}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${user.password}" />
                                </td>
                                <td>
                                    <input type="text" name="txtLastName" value="${user.lastName}" />
                                </td>
                                <td>
                                    <input type="text" name="txtFirstName" value="${user.firstName}" />
                                </td>
                                <td>

                                    <input type="checkbox" name="chkRole" value="ON" ${user.role == 1 ? "checked=checked" : ""} />
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="submit" value="Delete" name="btAction" />
                                    <input type="hidden" name="txtRole" value="${sessionScope.USER.role}" />
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2>No users found</h2>
        </c:otherwise>
    </c:choose>
</c:if>

</body>
</html>
