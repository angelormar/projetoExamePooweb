package br.ufsm.csi.controller;

import br.ufsm.csi.dao.OficinaDAO;
import br.ufsm.csi.model.Oficina;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@WebServlet("/view/oficina")
public class OficinaController extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "";

        String action = req.getParameter("opt");
        System.out.println(action);

        if (action.equals("addOficina") || action.equals("editOficina")) {
            if (action.equals("editOficina")) {
                String id = req.getParameter("param");
                System.out.println(id);
                Oficina p = new OficinaDAO().getoficina(parseInt(id));
                req.setAttribute("oficina", p);
            }
            RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
            rd.forward(req, resp);

        } else if (action.equals("Editar") || action.equals("Cadastrar")) {
            String id = req.getParameter("id");
            String nome = req.getParameter("nome");
            String endereco = req.getParameter("endereco");

            if (action.equals("Cadastrar")) {
                Oficina oficina = new Oficina(0, endereco, nome);
                status = new OficinaDAO().cadastrar(oficina);
                req.setAttribute("status", status);

            } else if (action.equals("Editar")) {
                Oficina oficina = new Oficina(parseInt(id), endereco, nome);
                status = new OficinaDAO().atualizar(oficina);
                req.setAttribute("status", status);

            }
            ArrayList<Oficina> ps = new OficinaDAO().getoficinas();
            req.setAttribute("oficinas", ps);
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);

        } else if (action.equals("deleteOficina")) {
            String id = req.getParameter("param");
            status = new OficinaDAO().remover(parseInt(id));
            req.setAttribute("status", status);
            ArrayList<Oficina> ps = new OficinaDAO().getoficinas();
            req.setAttribute("oficinas", ps);
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);

        }else {

            ArrayList<Oficina> ps = new OficinaDAO().getoficinas();
            req.setAttribute("oficinas", ps);
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }

    }
}