package br.ufsm.csi.controller;

import br.ufsm.csi.dao.*;
import br.ufsm.csi.dao.ConsertoDAO;
import br.ufsm.csi.model.*;
import br.ufsm.csi.model.Conserto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

@WebServlet("/view/conserto")
public class ConsertoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "";

        String action = req.getParameter("opt");
        System.out.println(action);

        if (action.equals("addConserto") || action.equals("editConserto")) {
            if (action.equals("editConserto")) {
                String cod = req.getParameter("param");
                System.out.println(cod);
                Conserto c = new ConsertoDAO().getconserto(parseInt(cod));
                req.setAttribute("conserto", c);
            }
                ArrayList<Aviao> as = new AviaoDAO().getavioes();
                req.setAttribute("avioesSelect", as);

            ArrayList<Oficina> ofs = new OficinaDAO().getoficinas();
            req.setAttribute("oficinaSelect", ofs);

            RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
            rd.forward(req, resp);

        } else if (action.equals("Editar") || action.equals("Cadastrar")) {
            String placa = req.getParameter("placa");
            String idOficina = req.getParameter("idOficina");
            String idConserto = req.getParameter("idConserto");
            String data = req.getParameter("data");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dataFormatada = null;
            try {
                dataFormatada = formato.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(dataFormatada);

            if (action.equals("Cadastrar")) {
                Conserto conserto = new Conserto(0, placa, parseInt(idOficina), new java.sql.Date(dataFormatada.getTime()));
                status = new ConsertoDAO().cadastrar(conserto);
                req.setAttribute("status", status);

            } else if (action.equals("Editar")) {
                System.out.println(idConserto);
                Conserto conserto = new Conserto(parseInt(idConserto), placa, parseInt(idOficina), new java.sql.Date(dataFormatada.getTime()));
                status = new ConsertoDAO().atualizar(conserto);
                req.setAttribute("status", status);
            }

        } else if (action.equals("deleteConserto")) {
            String cod = req.getParameter("param");
            status = new ConsertoDAO().remover(parseInt(cod));
            req.setAttribute("status", status);


        } else if(action.equals("showConserto")){
            String placa = req.getParameter("param");
            ArrayList<Conserto> cs = new ConsertoDAO().getconsertosaviao(placa);
            req.setAttribute("consertos", cs);

            for(Conserto c: cs){
                System.out.println(c.getData());
            }
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }

        ArrayList<Conserto> as = new ConsertoDAO().getconsertos();

        req.setAttribute("consertos", as);
        RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
        rd.forward(req, resp);


    }
}