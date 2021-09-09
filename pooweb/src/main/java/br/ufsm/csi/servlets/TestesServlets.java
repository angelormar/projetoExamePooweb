package br.ufsm.csi.servlets;

import br.ufsm.csi.dao.PassageiroDAO;
import br.ufsm.csi.model.Passageiro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("testes-servlet")
public class TestesServlets extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("recebeu uma request por get");

        PrintWriter pw = resp.getWriter();
        String CPF = req.getParameter("CPF");
        System.out.println(CPF);
        Passageiro p = new PassageiroDAO().getpassageiro(CPF);
        System.out.println("Nome: " + p.getNome());

        pw.println("<html>");
        pw.println("<body>");
        pw.println("<p>Primeira servlet respondendo dentro de um método service!<p>:");
        pw.println(p.getNome());
        pw.println("</html>");
        pw.println("</body>");
    }
}
