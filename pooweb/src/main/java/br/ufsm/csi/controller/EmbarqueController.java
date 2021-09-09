package br.ufsm.csi.controller;

import br.ufsm.csi.dao.PassageiroDAO;
import br.ufsm.csi.dao.ViagemDAO;
import br.ufsm.csi.model.Passageiro;
import br.ufsm.csi.model.Viagem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
@WebServlet("/view/embarque")
public class EmbarqueController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "";

        String action = req.getParameter("opt");
        System.out.println(action);

        if (action.equals("add")) {
            String CPF = req.getParameter("CPF");
            String codigo = req.getParameter("param");

            Viagem v = new ViagemDAO().getviagem(parseInt(codigo));
            String placa = v.getPlaca();

            status = new PassageiroDAO().insertPassViagem(parseInt(codigo), CPF, placa );
            req.setAttribute("status", status);

            ArrayList<Passageiro> ps = new PassageiroDAO().getpassageirosviagem(parseInt(codigo));
            req.setAttribute("passageiros", ps);
            ArrayList<Passageiro> p = new PassageiroDAO().getpassageiros();
            req.setAttribute("todospassageiros", p);

            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);

        } else if (action.equals("del")) {
            String codigo = req.getParameter("param");
            String CPF = req.getParameter("CPF");
            status = new PassageiroDAO().removerPassViagem(CPF, parseInt(codigo));
            req.setAttribute("status", status);

            ArrayList<Passageiro> ps = new PassageiroDAO().getpassageirosviagem(parseInt(codigo));
            req.setAttribute("passageiros", ps);
            ArrayList<Passageiro> p = new PassageiroDAO().getpassageiros();
            req.setAttribute("todospassageiros", p);
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);

        }else {

            ArrayList<Passageiro> ps = new PassageiroDAO().getpassageiros();
            req.setAttribute("passageiros", ps);
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }

    }
}
