package br.ufsm.csi.dao;

import br.ufsm.csi.model.*;
import br.ufsm.csi.model.Viagem;

import java.sql.*;
import java.util.ArrayList;

public class ViagemDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public String cadastrar(Viagem viagem){

        try(Connection connection = new ConectaDB().getConexao()){
            ResultSet cpf;
            connection.setAutoCommit(false);

            this.sql = "insert into viagem values (?, ?, ?, ?, ?);";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);

            this.preparedStatement.setString(1, viagem.getPlaca());
            this.preparedStatement.setTimestamp(2, (Timestamp) viagem.getData());
            this.preparedStatement.setTime(3, viagem.getHora());
            this.preparedStatement.setString(4, viagem.getOrigem());
            this.preparedStatement.setString(5, viagem.getDestino());

            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getInt(6) > 0){
                viagem.setCodigo(this.resultSet.getInt(6));
                this.status = "ok";
            }
            connection.commit();

        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public String atualizar(Viagem viagem){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "UPDATE viagem SET " +
                    "placa = ?, " +
                    "dt_viagem = ?, " +
                    "hr_viagem = ?, " +
                    "origem = ?, " +
                    "destino = ? " +
                    "WHERE codigo = ?";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, viagem.getPlaca());
            this.preparedStatement.setTimestamp(2, (Timestamp) viagem.getData());
            this.preparedStatement.setTime(3, viagem.getHora());
            this.preparedStatement.setString(4, viagem.getOrigem());
            this.preparedStatement.setString(5, viagem.getDestino());
            this.preparedStatement.setInt(6, viagem.getCodigo());
            System.out.println(this.preparedStatement);
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if (this.resultSet.getInt(6) > 0){
                viagem.setCodigo(this.resultSet.getInt(6));
                this.status = "ok";
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();

        }

        return this.status;

    }

    public String remover(int codigo){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "delete from viagem where codigo = ? ";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, codigo);
            this.preparedStatement.execute();

            this.status = "ok";
        }catch (SQLException e){
            e.printStackTrace();
            this.status = e.getMessage();
        }

        return this.status;

    }

    public Viagem getviagem(int codViagem){

        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM viagem WHERE viagem.codigo = ?";
            this.statement = connection.createStatement();
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setInt(1, codViagem);
            this.preparedStatement.execute();
            this.resultSet = this.statement.executeQuery(String.valueOf(this.preparedStatement));
            this.resultSet.next();
            Viagem v = new Viagem();
            try{
                v.setCodigo(this.resultSet.getInt("codigo"));
                v.setPlaca(this.resultSet.getString("placa"));
                v.setData(this.resultSet.getTimestamp("dt_viagem"));
                v.setHora(this.resultSet.getTime("hr_viagem"));
                v.setOrigem(this.resultSet.getString("origem"));
                v.setDestino(this.resultSet.getString("destino"));
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("viagem nao encontrado");
            }
            return v;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Viagem> getviagens(){
        ArrayList<Viagem> vs = new ArrayList<Viagem>();
        try(Connection connection = new ConectaDB().getConexao()) {

            this.sql = "SELECT * FROM viagem";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while(this.resultSet.next()){
                Viagem v = new Viagem();
                v.setCodigo(this.resultSet.getInt("codigo"));
                v.setPlaca(this.resultSet.getString("placa"));
                v.setData(this.resultSet.getTimestamp("dt_viagem"));
                v.setHora(this.resultSet.getTime("hr_viagem"));
                v.setOrigem(this.resultSet.getString("origem"));
                v.setDestino(this.resultSet.getString("destino"));

                vs.add(v);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return vs;
    }

}

