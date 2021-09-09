package br.ufsm.csi.controller;

import br.ufsm.csi.dao.PassageiroDAO;
import br.ufsm.csi.dao.PassageiroDAO;
import br.ufsm.csi.model.Passageiro;
import br.ufsm.csi.model.Passageiro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

@WebServlet("/view/passageiros")
public class PassageirosController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "";

        String action = req.getParameter("opt");
        System.out.println(action);

        if (action.equals("addPassageiro") || action.equals("editPassageiro")) {
            if (action.equals("editPassageiro")) {
                String cod = req.getParameter("param");
                System.out.println(cod);
                Passageiro p = new PassageiroDAO().getpassageiro(cod);
                req.setAttribute("passageiro", p);
            }
            RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
            rd.forward(req, resp);

        } else if (action.equals("Editar") || action.equals("Cadastrar")) {
            String CPF = req.getParameter("CPF");
            String nome = req.getParameter("nome");
            String endereco = req.getParameter("endereco");

            if (action.equals("Cadastrar")) {
                Passageiro passageiro = new Passageiro(nome, CPF, endereco);
                status = new PassageiroDAO().cadastrar(passageiro);
                req.setAttribute("status", status);

            } else if (action.equals("Editar")) {
                Passageiro passageiro = new Passageiro(nome, CPF, endereco);
                status = new PassageiroDAO().atualizar(passageiro);
                req.setAttribute("status", status);
            }

        } else if (action.equals("deletePassageiro")) {
            String CPF = req.getParameter("param");
            status = new PassageiroDAO().remover(CPF);
            req.setAttribute("status", status);


        }

        ArrayList<Passageiro> ps = new PassageiroDAO().getpassageiros();
        req.setAttribute("passageiros", ps);
        RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
        rd.forward(req, resp);


    }
}
