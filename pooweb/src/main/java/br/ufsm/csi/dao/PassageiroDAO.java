package br.ufsm.csi.dao;

import br.ufsm.csi.model.Passageiro;

import java.sql.*;
import java.util.ArrayList;

public class PassageiroDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public String cadastrar(Passageiro passageiro){

        try(Connection connection = new ConectaDB().getConexao()){
ResultSet cpf;
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO passageiro (nome, CPF, endereco) " +
                    " VALUES (?, ?, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, passageiro.getNome());
            this.preparedStatement.setString(2, passageiro.getCPF());
            this.preparedStatement.setString(3, passageiro.getEndereco());

            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();
            this.status = "ok";
            if (this.resultSet.getString(2) != null){
                passageiro.setCPF(this.resultSet.getString(2));
                this.status = "ok";
            }
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public String atualizar(Passageiro passageiro){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "UPDATE passageiro SET " +
                    "nome = ?, " +
                    "endereco = ? " +
                    "WHERE CPF = ?";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, passageiro.getNome());
            this.preparedStatement.setString(2, passageiro.getEndereco());
            this.preparedStatement.setString(3, passageiro.getCPF());
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getString(1) != null){
                passageiro.setCPF(this.resultSet.getString(1));
                this.status = "ok";
            }


        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public String remover(String codigo){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "delete from passageiro where cpf = ? ";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, codigo);
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();

            this.status = "ok";
        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public Passageiro getpassageiro(String CPF){

        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM passageiro WHERE passageiro.CPF = ?";
            this.statement = connection.createStatement();
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, CPF);
            this.preparedStatement.execute();
            this.resultSet = this.statement.executeQuery(String.valueOf(this.preparedStatement));
            this.resultSet.next();
            Passageiro passageiro = new Passageiro();
            try{
                passageiro.setCPF(this.resultSet.getString("CPF"));
                passageiro.setNome(this.resultSet.getString("nome"));
                passageiro.setEndereco(this.resultSet.getString("endereco"));
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("passageiro nao encontrado");
            }
        return passageiro;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Passageiro> getpassageiros(){
        ArrayList<Passageiro> ps = new ArrayList<Passageiro>();
        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM passageiro";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Passageiro p = new Passageiro();
                p.setCPF(this.resultSet.getString("CPF"));
                p.setNome(this.resultSet.getString("nome"));
                p.setEndereco(this.resultSet.getString("endereco"));

                ps.add(p);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ps;
    }

    public ArrayList<Passageiro> getpassageirosviagem(int cod){

        ArrayList<Passageiro> ps = new ArrayList<Passageiro>();
        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM passageiro where exists( select 1 from passageiro_aviao where passageiro_aviao.cod_viagem = ? and passageiro.cpf = fk_passageiro_cpf)";
            this.statement = connection.createStatement();
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, cod);
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.statement.executeQuery(String.valueOf(this.preparedStatement));

            while(this.resultSet.next()){
                Passageiro p = new Passageiro();
                p.setCPF(this.resultSet.getString("CPF"));
                p.setNome(this.resultSet.getString("nome"));
                p.setEndereco(this.resultSet.getString("endereco"));

                ps.add(p);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ps;
    }

    public String insertPassViagem(int cod, String CPF, String placa) {

        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "insert into passageiro_aviao values (?,?,?)";
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, CPF);
            this.preparedStatement.setString(2, placa);
            this.preparedStatement.setInt(3, cod);
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.status = "ok";

        } catch (SQLException e) {
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;
    }

    public String removerPassViagem(String cpf, int cod) {

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "delete from passageiro_aviao where fk_passageiro_cpf = ? and cod_viagem = ? ";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, cpf);
            this.preparedStatement.setInt(2, cod);
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();

            this.status = "ok";
        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }
}
