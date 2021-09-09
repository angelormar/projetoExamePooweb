package br.ufsm.csi.controller;

import br.ufsm.csi.dao.AviaoDAO;
import br.ufsm.csi.dao.AviaoDAO;
import br.ufsm.csi.dao.ConsertoDAO;
import br.ufsm.csi.dao.PassageiroDAO;
import br.ufsm.csi.model.Aviao;
import br.ufsm.csi.model.Aviao;
import br.ufsm.csi.model.Conserto;
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

@WebServlet("/view/aviao")
public class AviaoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "";

        String action = req.getParameter("opt");
        System.out.println(action);

        if (action.equals("addAviao") || action.equals("editAviao")) {
            if (action.equals("editAviao")) {
                String cod = req.getParameter("param");
                System.out.println(cod);
                Aviao p = new AviaoDAO().getaviao(cod);
                req.setAttribute("aviao", p);
            }
            RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
            rd.forward(req, resp);

        } else if (action.equals("Editar") || action.equals("Cadastrar")) {
            String placa = req.getParameter("placa");
            String fabricante = req.getParameter("fabricante");
            String modelo = req.getParameter("modelo");

            if (action.equals("Cadastrar")) {
                Aviao aviao = new Aviao(placa, modelo, fabricante);
                status = new AviaoDAO().cadastrar(aviao);
                req.setAttribute("status", status);

            } else if (action.equals("Editar")) {
                Aviao aviao = new Aviao(placa, modelo, fabricante);
                status = new AviaoDAO().atualizar(aviao);
                req.setAttribute("status", status);
            }

        } else if (action.equals("deleteAviao")) {
            String placa = req.getParameter("param");
            status = new AviaoDAO().remover(placa);
            req.setAttribute("status", status);


        } else if(action.equals("showConserto")){
            String placa = req.getParameter("param");
            ArrayList<Conserto> cs = new ConsertoDAO().getconsertosaviao(placa);
            req.setAttribute("consertos", cs);

            for(Conserto a: cs){
                System.out.println(a.getData());
            }
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }

        ArrayList<Aviao> as = new AviaoDAO().getavioes();

        req.setAttribute("avioes", as);
        RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
        rd.forward(req, resp);


    }
}
