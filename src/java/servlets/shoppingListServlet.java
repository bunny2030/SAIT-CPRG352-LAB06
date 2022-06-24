/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vaibhav
 */
public class shoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        if (request.getParameter("action") != null) {
            if (request.getParameter("action").equals("logout"))
            {
                session.invalidate();
                session = request.getSession();
            }
        }

        if (session.getAttribute("username") != null) {
             ArrayList<String> shoppingList = (ArrayList)session.getAttribute("shoppingList");
            
             if(shoppingList.size()==0)
             {
                 request.setAttribute("show",false);
             }
            
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        ArrayList<String> shoppingList = (ArrayList)session.getAttribute("shoppingList");
        if(shoppingList == null)
            shoppingList = new ArrayList<>();
        
        if (action.equals("register")) {
            String username = request.getParameter("username");
            session.setAttribute("username", username);
//            shoppingList = new ArrayList<>();
//            session.setAttribute("items", shoppingList);
        }

        if (action.equals("add")) {
            String item = request.getParameter("listItem");
            shoppingList.add(item);
        }
        
        if (action.equals("delete")) {
            String itemDel = request.getParameter("itemDel");
            if (itemDel != null) {
                shoppingList.remove(itemDel);
            }
        }
      
        request.setAttribute("show",true);
        session.setAttribute("shoppingList", shoppingList);
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        return;
    }
}