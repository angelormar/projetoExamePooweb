package br.ufsm.csi.controller;

import br.ufsm.csi.dao.AviaoDAO;
import br.ufsm.csi.dao.BagagemDAO;
import br.ufsm.csi.dao.PassageiroDAO;
import br.ufsm.csi.model.Aviao;
import br.ufsm.csi.model.Bagagem;
import br.ufsm.csi.model.Passageiro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@WebServlet("/view/bagagem")
public class BagagemController extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String status = "";

        String action = req.getParameter("opt");
        System.out.println(action);

        if(action != null){
        if (action.equals("addBagagem") || action.equals("editBagagem")) {
            if (action.equals("editBagagem")) {
                String cod = req.getParameter("param");
                System.out.println(cod);
                Bagagem b = new BagagemDAO().getbagagem(parseInt(cod));
               Passageiro p = new PassageiroDAO().getpassageiro(b.getPassageiro().getCPF());
               b.setPassageiro(p);
                req.setAttribute("bagagem", b);
            } else {
                String cpf = req.getParameter("CPF");
                req.setAttribute("cpf", cpf);
            }
                ArrayList<Aviao> as = new AviaoDAO().getavioes();
                req.setAttribute("avioesSelect", as);

            RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
            rd.forward(req, resp);

        } else if (action.equals("Editar") || action.equals("Cadastrar")) {
            String cod = req.getParameter("ID");
            String peso = req.getParameter("peso");
            String pass = req.getParameter("passageiro");
            String aviao = req.getParameter("placa");
            Passageiro p = new Passageiro();
            p.setCPF(pass);
            Aviao a = new Aviao();
            a.setPlaca(aviao);

            if (action.equals("Cadastrar")) {
                Bagagem bagagem = new Bagagem(0, parseFloat(peso), a, p);
                status = new BagagemDAO().cadastrar(bagagem);
                ArrayList<Bagagem> ps = new BagagemDAO().getbagagens();
                req.setAttribute("status", status);
                req.setAttribute("bagagens", ps);
                RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
                rd.forward(req, resp);
            } else if (action.equals("Editar")) {
                Bagagem bagagem = new Bagagem(parseInt(cod), parseFloat(peso), a, p);
                status = new BagagemDAO().atualizar(bagagem);
                ArrayList<Bagagem> ps = new BagagemDAO().getbagagens();
                req.setAttribute("status", status);
                req.setAttribute("bagagens", ps);
                RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
                rd.forward(req, resp);
            }

        } else if (action.equals("deleteBagagem")) {
            String cod = req.getParameter("param");
            status = new BagagemDAO().remover(parseInt(cod));
            ArrayList<Bagagem> bs = new BagagemDAO().getbagagens();
            req.setAttribute("status", status);
            req.setAttribute("bagagens", bs);
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }else if(action.equals("passBagagem")){
            String cpf = req.getParameter("param");
            ArrayList<Bagagem> bs = new BagagemDAO().getbagagensPass(cpf);
            req.setAttribute("bagagens", bs);
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }else{
            ArrayList<Bagagem> ps = new BagagemDAO().getbagagens();
            req.setAttribute("bagagens", ps);
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }}else{
            ArrayList<Bagagem> ps = new BagagemDAO().getbagagens();
            req.setAttribute("bagagens", ps);
            RequestDispatcher rd = req.getRequestDispatcher("/list.jsp");
            rd.forward(req, resp);
        }

    }
}
