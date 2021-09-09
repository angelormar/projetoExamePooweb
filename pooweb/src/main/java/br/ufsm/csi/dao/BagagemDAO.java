package br.ufsm.csi.dao;

import br.ufsm.csi.model.*;
import br.ufsm.csi.model.Bagagem;

import java.sql.*;
import java.util.ArrayList;

public class BagagemDAO {


    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public String cadastrar(Bagagem bagagem){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "INSERT INTO bagagens (peso, fk_Aviao_placa, fk_passageiro_cpf) " +
                    " VALUES (?, ?, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setFloat(1, bagagem.getPeso());
            this.preparedStatement.setString(2, bagagem.getAviao().getPlaca());
            this.preparedStatement.setString(3, bagagem.getPassageiro().getCPF());
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getInt(1) > 0){
                bagagem.setId(this.resultSet.getInt(1));
                this.status = "ok";
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public String atualizar(Bagagem bagagem){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "UPDATE bagagens SET " +
                    "peso = ? " +
                    "WHERE id_bagagem = ?";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setFloat(1, bagagem.getPeso());
            this.preparedStatement.setInt(2, bagagem.getId());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getInt(1) > 0){
                bagagem.setId(this.resultSet.getInt(1));
                this.status = "ok";
            }


        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public Bagagem getbagagem(int id){

        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM bagagens WHERE bagagens.id_bagagem = ?";
            this.statement = connection.createStatement();
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.execute();
            this.resultSet = this.statement.executeQuery(String.valueOf(this.preparedStatement));
            this.resultSet.next();
            Bagagem b = new Bagagem();
            Aviao a = new Aviao();
            Passageiro p = new Passageiro();
            try{
                b.setId(this.resultSet.getInt("id_bagagem"));
                b.setPeso(this.resultSet.getFloat("peso"));
                a.setPlaca(this.resultSet.getString("fk_aviao_placa"));
                p.setCPF(this.resultSet.getString("fk_passageiro_cpf"));

                b.setAviao(a);
                b.setPassageiro(p);

            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("bagagem nao encontrada");
            }

            return b;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Bagagem> getbagagens() {
        ArrayList<Bagagem> bs = new ArrayList<Bagagem>();
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM bagagens";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Bagagem b = new Bagagem();
                Aviao a = new Aviao();
                Passageiro p = new Passageiro();

                b.setId(this.resultSet.getInt("id_bagagem"));
                b.setPeso(this.resultSet.getFloat("peso"));
                a.setPlaca(this.resultSet.getString("fk_aviao_placa"));
                p.setCPF(this.resultSet.getString("fk_passageiro_cpf"));

                b.setAviao(a);
                b.setPassageiro(p);

                bs.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bs;
    }

    public String remover(int cod){
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "delete from bagagens where id_bagagem = ? ";

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

    public ArrayList<Bagagem> getbagagensPass(String cpf) {
        ArrayList<Bagagem> bs = new ArrayList<Bagagem>();
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM bagagens where fk_passageiro_cpf = ?";
            this.statement = connection.createStatement();
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, cpf);
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.statement.executeQuery(String.valueOf(this.preparedStatement));

            while(this.resultSet.next()){
                Bagagem b = new Bagagem();
                Aviao a = new Aviao();
                Passageiro p = new Passageiro();

                b.setId(this.resultSet.getInt("id_bagagem"));
                b.setPeso(this.resultSet.getFloat("peso"));
                a.setPlaca(this.resultSet.getString("fk_aviao_placa"));
                p.setCPF(this.resultSet.getString("fk_passageiro_cpf"));

                b.setAviao(a);
                b.setPassageiro(p);

                bs.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bs;

    }
}
