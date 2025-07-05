package loilt.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import loilt.user.CreateUserErrors;
import loilt.user.UserDAO;
import loilt.user.UserDTO;
import loilt.util.ValidationHelper;

@WebServlet(name = "CreateUserController", urlPatterns = {"/CreateUserController"})
public class CreateUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ERROR_PAGE = "createUser.jsp";
    private final String LOGIN_PAGE = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("txtUserId");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String firstName = request.getParameter("txtFirstName");
        String lastName = request.getParameter("txtLastName");
        boolean hasError = false;
        CreateUserErrors errors = new CreateUserErrors();

        if (!ValidationHelper.matchWithPattern(userId, ValidationHelper.VALID_USER_ID)) {
            hasError = true;
            errors.setUserIdNotValid("User ID must be 3-20 alphanumeric characters.");
        }
        if (!ValidationHelper.matchWithPattern(password, ValidationHelper.VALID_PASSWORD)) {
            hasError = true;
            errors.setPasswordNotValid("Password must be 6-30 characters.");
        }
        if (!ValidationHelper.isPasswordConfirmed(password, confirm)) {
            hasError = true;
            errors.setConfirmNotMatch("Password confirmation does not match.");
        }
        if (!ValidationHelper.matchWithPattern(firstName, ValidationHelper.VALID_NAME)) {
            hasError = true;
            errors.setFirstNameNotValid("First name must be 2-50 alphabetic characters.");
        }
        if (!ValidationHelper.matchWithPattern(lastName, ValidationHelper.VALID_NAME)) {
            hasError = true;
            errors.setLastNameNotValid("Last name must be 2-50 alphabetic characters.");
        }

        if (hasError) {
            request.setAttribute("CREATE_ERRORS", errors);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            return;
        }

        try {
            UserDAO dao = new UserDAO();
            UserDTO dto = new UserDTO(userId, password, lastName, firstName, 0);
            boolean result = dao.createUser(dto);
            if (result) {
                response.sendRedirect(LOGIN_PAGE);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
