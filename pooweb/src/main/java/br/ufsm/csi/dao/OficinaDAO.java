package br.ufsm.csi.dao;

import br.ufsm.csi.model.Oficina;
import br.ufsm.csi.model.Oficina;
import br.ufsm.csi.model.Oficina;

import java.sql.*;
import java.util.ArrayList;

public class OficinaDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public String cadastrar(Oficina oficina){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "INSERT INTO oficina (nome, endereco) " +
                    " VALUES (?, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, oficina.getNome());
            this.preparedStatement.setString(2, oficina.getEndereco());
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();
            this.status = "ok";

        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public String atualizar(Oficina oficina){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "UPDATE oficina SET " +
                    "nome = ?, " +
                    "endereco = ? " +
                    "WHERE id_oficina = ?";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, oficina.getNome());
            this.preparedStatement.setString(2, oficina.getEndereco());
            this.preparedStatement.setInt(3, oficina.getId());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getInt(3) > 0){
                oficina.setId(this.resultSet.getInt(3));
                this.status = "ok";
            }


        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public Oficina getoficina(int Id){

        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM oficina WHERE oficina.id_oficina = ?";
            this.statement = connection.createStatement();
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, Id);
            this.preparedStatement.execute();
            this.resultSet = this.statement.executeQuery(String.valueOf(this.preparedStatement));
            this.resultSet.next();
            Oficina oficina = new Oficina();
            try{
                oficina.setId(this.resultSet.getInt("id_oficina"));
                oficina.setNome(this.resultSet.getString("nome"));
                oficina.setEndereco(this.resultSet.getString("endereco"));
            }catch (SQLException e){
                e.printStackTrace();
                this.status = "oficina nao encontrada";
            }

            return oficina;
        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }
        return null;
    }

    public String remover(int id) {
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "delete from oficina where id_oficina = ? ";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();

            this.status = "ok";
        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;
    }

    public ArrayList<Oficina> getoficinas() {
        ArrayList<Oficina> ofs = new ArrayList<Oficina>();
        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM oficina";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Oficina of = new Oficina();
                of.setId(this.resultSet.getInt("id_oficina"));
                of.setNome(this.resultSet.getString("nome"));
                of.setEndereco(this.resultSet.getString("endereco"));

                ofs.add(of);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ofs;
    }
}
