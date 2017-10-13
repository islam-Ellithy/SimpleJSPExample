package ServletsClasses;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MrHacker
 */
@WebServlet(urlPatterns = {"/ServletCalculator"})
public class ServletCalculator extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletCalculator</title>");
            out.println("</head>");
            out.println("<body>");

            String result;

            String exp = request.getParameter("data");

            ScriptEngineManager mgr = new ScriptEngineManager();

            ScriptEngine engine = mgr.getEngineByName("JavaScript");

            try {
                result = engine.eval(exp).toString();
                HttpSession sessionObject = request.getSession(true);
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("result", result);
                map.put("exp", exp);
                sessionObject.setAttribute("obj", map);
                response.sendRedirect("Display.jsp");

            } catch (ScriptException ex) {
                Logger.getLogger(ServletCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("<h1>Servlet ServletCalculator at " + isMathmaticalExpression(exp) + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private boolean isMathmaticalExpression(String text) {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch != ' ' && (ch < 42 || ch > 57)) {
                return false;
            }
        }
        return true;
    }

    private String getResult(String exp) {
        float result = 0;
        String num = "";
        Stack<String> ops = new Stack<>();
        Stack<String> nums = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (ch >= 42 && ch <= 47) {//Is operator
                nums.add(num);
                ops.add(String.valueOf(ch));
            } else if (ch >= 48 || ch <= 57) {//Is num
                num += ch;
            }
        }
        return null;
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
        processRequest(request, response);
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
