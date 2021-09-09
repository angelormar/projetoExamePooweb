package br.ufsm.csi.controller;

import br.ufsm.csi.dao.AviaoDAO;
import br.ufsm.csi.dao.PassageiroDAO;
import br.ufsm.csi.dao.ViagemDAO;
import br.ufsm.csi.model.Aviao;
import br.ufsm.csi.model.Passageiro;
import br.ufsm.csi.model.Viagem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

@WebServlet("/view/viagens")
public class ViagemController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "";

        String action = req.getParameter("opt");
        System.out.println(action);

        if (action.equals("addViagem") || action.equals("editViagem")) {
            if (action.equals("editViagem")) {
                String cod = req.getParameter("param");
                System.out.println(cod);
                Viagem v = new ViagemDAO().getviagem(parseInt(cod));
                req.setAttribute("viagem", v);
                System.out.println(v.getOrigem());
            }
                ArrayList<Aviao> as = new AviaoDAO().getavioes();
                req.setAttribute("avioesSelect", as);

            RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
            rd.forward(req, resp);

        } else if (action.equals("Editar") || action.equals("Cadastrar")) {
            String cod = req.getParameter("param");
            String placa = req.getParameter("placa");
            String dateUtil = req.getParameter("data");
            String hora = req.getParameter("hora");
            String origem = req.getParameter("origem");
            String destino = req.getParameter("destino");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dataFormatada = null;
            try {
                String aux = dateUtil.concat(" ");
                dataFormatada = formato.parse(aux.concat(hora));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(dataFormatada);
            Timestamp data = new Timestamp(dataFormatada.getTime());
            Time horaTime = new Time(dataFormatada.getTime());

            if (action.equals("Cadastrar")) {
                Viagem viagem = new Viagem(0, placa, data, horaTime, origem, destino);
                status = new ViagemDAO().cadastrar(viagem);
                req.setAttribute("status", status);

            } else {
                Viagem viagem = new Viagem(parseInt(cod), placa, data, horaTime, origem, destino);
                status = new ViagemDAO().atualizar(viagem);
                req.setAttribute("status", status);
            }

        } else if (action.equals("deleteViagem")) {
            String codR = req.getParameter("param");
            status = new ViagemDAO().remover(parseInt(codR));
            req.setAttribute("status", status);


        } else if(action.equals("showViagem")){
            String cod = req.getParameter("param");
            ArrayList<Passageiro> psv = new PassageiroDAO().getpassageirosviagem(parseInt(cod));
            req.setAttribute("passageiros", psv);

            ArrayList<Passageiro> ps = new PassageiroDAO().getpassageiros();

            req.setAttribute("todospassageiros", ps);

            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }

        ArrayList<Viagem> vs = new ViagemDAO().getviagens();
        req.setAttribute("viagens", vs);
        RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
        rd.forward(req, resp);

    }
}
