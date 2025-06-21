package loilt.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import loilt.mobile.MobileDAO;
import loilt.mobile.MobileDTO;
import loilt.user.UserDAO;
import loilt.user.UserDTO;
import loilt.util.ValidationHelper;

@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Check role
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
            return;
        } else {
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null || user.getRole() != 1) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        String url = "";
        String userId = request.getParameter("txtUserId");
        String password = request.getParameter("txtPassword");
        String firstName = request.getParameter("txtFirstName");
        String lastName = request.getParameter("txtLastName");
        String chkRole = request.getParameter("chkRole");
        String lastSearchValue = request.getParameter("lastSearchValue");
        String role = request.getParameter("txtRole");

        try {

            UserDAO dao = new UserDAO();
            UserDTO dto = dao.getUserById(userId);

            dto.setPassword(ValidationHelper.matchWithPattern(password, ValidationHelper.VALID_PASSWORD)
                    ? password
                    : dto.getPassword());

            dto.setFirstName(ValidationHelper.matchWithPattern(firstName, ValidationHelper.VALID_NAME)
                    ? firstName
                    : dto.getFirstName());
            dto.setLastName(ValidationHelper.matchWithPattern(lastName, ValidationHelper.VALID_NAME)
                    ? lastName
                    : dto.getLastName());

            dto.setRole(chkRole != null ? 1 : 0);
            System.out.println(">>>>>>>>>>>>>>>>dto" + firstName);
            boolean result = dao.updateUser(dto);
            if (result) {
                url = "DispatchController"
                        + "?btAction=Search"
                        + "&txtSearchValue="
                        + lastSearchValue
                        + "&txtRole="
                        + role;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
