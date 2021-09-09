package br.ufsm.csi.util;

import br.ufsm.csi.dao.*;
import br.ufsm.csi.model.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class teste {
    public static void main(String[] args) throws ParseException {
        //    cadastraPassageiro();
      //  cadastraAviao();
        //  cadastraOficina();
        // cadastraBagagem();
     //   gettudo();
      //  atualizarPassageiro();
       // atualizarAviao();
       // atualizarBagagem();
       // atualizarOficina();

      //  ArrayList<Passageiro> ps = new PassageiroDAO().getpassageirosviagem("abc-defg");
       // for (Passageiro p:ps){
       //     System.out.println(p.getNome());
       // }

        String aa = "";
        String bb = "b";
        System.out.println(aa.isEmpty());
        System.out.println(bb.isEmpty());

    };

    public static void cadastraPassageiro(){

        Aviao a = new Aviao();
        a.setPlaca("eee.fff.312-gg");
      //  Passageiro p = new Passageiro("Angelo", "123.456.789-09", "av.borges", a);
      //  new PassageiroDAO().cadastrar(p);
    };

    public static void cadastraBagagem(){

        Aviao a = new Aviao();
        a.setPlaca("eee.fff.312-gg");
        Passageiro p = new Passageiro();
        p.setCPF("123.456.789-09");
        Bagagem b = new Bagagem(3,33.3f, a ,p);
        new BagagemDAO().cadastrar(b);
    };

    public static void cadastraAviao(){

        Aviao a = new Aviao("eee.fff.312-gg","airFly","Azul");
        new AviaoDAO().cadastrar(a);
    };

    public static void cadastraOficina(){

        Oficina o = new Oficina(2,"Av.teste","Oficina conserta nada");
        new OficinaDAO().cadastrar(o);
    };

    public static void atualizarPassageiro(){

        Passageiro p = new Passageiro();
        p.setNome("Angelo atualizado");
        p.setEndereco("avenida atualizada");
        p.setCPF("123.456.789-09");
        new PassageiroDAO().atualizar(p);
    };

    public static void atualizarBagagem(){

        Bagagem b = new Bagagem();
        b.setPeso(123.13f);
        b.setId(3);
        new BagagemDAO().atualizar(b);
    };

    public static void atualizarAviao(){

        Aviao a = new Aviao();
        a.setPlaca("eee.fff.312-gg");
        a.setFabricante("Ford");
        a.setModelo("ford Airlines");
        new AviaoDAO().atualizar(a);
    };

    public static void atualizarOficina(){

        Oficina o = new Oficina();
        o.setEndereco("avelnida att");
        o.setNome("oficina atualizada");
        o.setId(2);
        new OficinaDAO().atualizar(o);
    };
    public static void getbagagens(){


    };

    public static void gettudo() {
        Oficina o = new OficinaDAO().getoficina(2);
        Passageiro p = new PassageiroDAO().getpassageiro("123.456.789-09");
        Aviao a = new AviaoDAO().getaviao("eee.fff.312-gg");
        Bagagem b = new BagagemDAO().getbagagem(3);

        System.out.println("Nome: " + p.getNome());
        System.out.println("Oficina: " + o.getNome());
        System.out.println("Aviao: " + a.getModelo());
        System.out.println("peso da bagagem: "+ b.getPeso());


    }




};


