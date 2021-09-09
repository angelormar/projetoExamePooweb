package br.ufsm.csi.dao;

import br.ufsm.csi.model.Aviao;
import br.ufsm.csi.model.Aviao;
import br.ufsm.csi.model.Passageiro;

import java.sql.*;
import java.util.ArrayList;

public class AviaoDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public String cadastrar(Aviao aviao){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "INSERT INTO aviao (modelo, placa, fabricante) " +
                    " VALUES (?, ?, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, aviao.getModelo());
            this.preparedStatement.setString(2, aviao.getPlaca());
            this.preparedStatement.setString(3, aviao.getFabricante());
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getString(2) != null){
                aviao.setPlaca(this.resultSet.getString(2));
                this.status = "ok";
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return this.status;

    }

    public String atualizar(Aviao aviao){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "UPDATE aviao SET " +
                    "modelo = ?, " +
                    "fabricante = ? " +
                    "WHERE placa = ?";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, aviao.getModelo());
            this.preparedStatement.setString(2, aviao.getFabricante());
            this.preparedStatement.setString(3, aviao.getPlaca());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getString(1) != null){
                aviao.setPlaca(this.resultSet.getString(1));
                this.status = "ok";
            }


        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public Aviao getaviao(String placa){

        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM aviao WHERE aviao.placa = ?";
            this.statement = connection.createStatement();
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, placa);
            this.preparedStatement.execute();
            this.resultSet = this.statement.executeQuery(String.valueOf(this.preparedStatement));
            this.resultSet.next();
            Aviao aviao = new Aviao();
            try{
                aviao.setPlaca(this.resultSet.getString("placa"));
                aviao.setModelo(this.resultSet.getString("modelo"));
                aviao.setFabricante(this.resultSet.getString("fabricante"));
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("aviao nao encontrado");
            }
            return aviao;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String remover(String placa) {
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "delete from aviao where placa = ? ";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, placa);
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();

            this.status = "ok";
        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;
    }

    public ArrayList<Aviao> getavioes() {
        ArrayList<Aviao> as = new ArrayList<Aviao>();
        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM aviao";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Aviao a = new Aviao();
                a.setPlaca(this.resultSet.getString("placa"));
                a.setFabricante(this.resultSet.getString("fabricante"));
                a.setModelo(this.resultSet.getString("modelo"));

                as.add(a);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return as;
    }
}
