package br.ufsm.csi.dao;

import br.ufsm.csi.model.*;
import br.ufsm.csi.model.Conserto;

import java.sql.*;
import java.util.ArrayList;

public class ConsertoDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public String cadastrar(Conserto conserto){

        try(Connection connection = new ConectaDB().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "insert into aviao_oficina (fk_aviao_placa,dt_conserto,fk_oficina_id_oficina) values (?, ?, ?);";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, conserto.getPlaca());
            this.preparedStatement.setDate(2, conserto.getData());
            this.preparedStatement.setInt(3, conserto.getIdOficina());



            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getInt(4) > 0){
                conserto.setIdConserto(this.resultSet.getInt(4));
                this.status = "ok";
            }
            connection.commit();

        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public ArrayList<Conserto> getconsertos(){
        ArrayList<Conserto> vs = new ArrayList<Conserto>();
        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM aviao_oficina";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Conserto v = new Conserto();
                v.setPlaca(this.resultSet.getString("fk_aviao_placa"));
                v.setIdOficina(this.resultSet.getInt("fk_oficina_id_oficina"));
                v.setData(this.resultSet.getDate("dt_conserto"));
                v.setIdConserto(this.resultSet.getInt("id_conserto"));
                vs.add(v);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return vs;
    }

    public ArrayList<Conserto> getconsertosaviao(String placa) {

            ArrayList<Conserto> cs = new ArrayList<Conserto>();
            try(Connection connection = new ConectaDB().getConexao()) {

                this.sql = "SELECT * FROM aviao_oficina where fk_aviao_placa = ?";
                this.statement = connection.createStatement();
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setString(1, placa);
                System.out.println(this.preparedStatement);
                this.preparedStatement.execute();
                this.resultSet = this.statement.executeQuery(String.valueOf(this.preparedStatement));

                while(this.resultSet.next()){
                    Conserto c = new Conserto();
                    c.setIdConserto(this.resultSet.getInt("id_conserto"));
                    c.setPlaca(this.resultSet.getString("fk_aviao_placa"));
                    c.setData(this.resultSet.getDate("dt_conserto"));
                    c.setIdOficina(this.resultSet.getInt("fk_oficina_id_oficina"));
                    cs.add(c);
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
            return cs;

    }

    public String remover(int cod) {
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "delete from aviao_oficina where id_conserto = ? ";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, cod);
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();

            this.status = "ok";
        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;
    }

    public Conserto getconserto(int cod) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM aviao_oficina WHERE id_conserto = ?";
            this.statement = connection.createStatement();
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, cod);
            this.preparedStatement.execute();
            this.resultSet = this.statement.executeQuery(String.valueOf(this.preparedStatement));
            this.resultSet.next();
            Conserto c = new Conserto();
            try{
                c.setPlaca(this.resultSet.getString("fk_aviao_placa"));
                c.setData(this.resultSet.getDate("dt_conserto"));
                c.setIdOficina(this.resultSet.getInt("fk_oficina_id_oficina"));
                c.setIdConserto(this.resultSet.getInt("id_conserto"));
            }catch (SQLException e){
                e.printStackTrace();
                this.status = e.getMessage();
            }
            return c;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String atualizar(Conserto c) {
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "UPDATE aviao_oficina SET fk_aviao_placa = ?, dt_conserto = ?, fk_oficina_id_oficina = ? WHERE id_conserto = ?";
            this.statement = connection.createStatement();
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1,c.getPlaca() );
            this.preparedStatement.setDate(2,c.getData());
            this.preparedStatement.setInt(3,c.getIdOficina());
            this.preparedStatement.setInt(4,c.getIdConserto());
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();

            this.status = "ok";
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = e.getMessage();
        }
        return this.status;
    }
}

