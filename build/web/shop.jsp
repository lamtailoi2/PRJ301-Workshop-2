
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER}">
            <c:set var="firstName" value="${sessionScope.USER.firstName}" />
            <c:set var="lastName" value="${sessionScope.USER.lastName}" />
            <c:set var="role" value="${sessionScope.USER.role}" />
            <h1>Welcome ${lastName} ${firstName}</h1>
            <c:url var="viewCartUrl" value="/DispatchController">
                <c:param name="btAction" value="View Cart" />
                <c:param name="lastSearchValue" value="${param.txtSearchValue}" />
            </c:url>
            <a href="${viewCartUrl}">View Your Cart</a>
            <form action="DispatchController" method="POST">
                <input type="hidden" name="txtUserId" value="${sessionScope.USER.userId}" />
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>

        <form action="DispatchController">
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" placeholder="Enter product name...." />
            <input type="submit" value="Search Product" name="btAction" />
        </form>
        <c:if test="${not empty param.txtSearchValue}">
            <c:choose>
                <c:when test="${not empty requestScope.SEARCH_RESULT}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Description</th>
                                <th>YearOfProduction</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.SEARCH_RESULT}" var="mobile">
                                <tr>
                            <form action="DispatchController" method="POST">
                                <td>${mobile.mobileId}
                                    <input type="hidden" name="txtMobileId" value="${mobile.mobileId}" />
                                </td>
                                <td>${mobile.mobileName}
                                </td>
                                <td>${mobile.price}
                                </td>
                                <td>${mobile.description}
                                </td>
                                <td>${mobile.yearOfProduction}
                                </td>
                                <td>${mobile.quantity}
                                </td>
                                <td>
                                    <input type="submit" value="Add To Cart" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2>No Mobile Recorded</h2>
        </c:otherwise>
    </c:choose>
</c:if>

</body>
</html>
