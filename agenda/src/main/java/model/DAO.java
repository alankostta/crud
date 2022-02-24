package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Parametros de conexão com o banco postegrs
public class DAO {
	private String url = "jdbc:postgresql://localhost:5432/dbagenda";
	private String user = "postgres";
	private String password = "AL442218sta";

//Metodo responsavel por abrir conexão com o banco
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Banco conectado com sucesso");
			return con;

		} catch (ClassNotFoundException e) {
			// Erro caso o driver JDBC não foi instalado
			System.out.println("Erro caso o driver JDBC não foi instalado");
			return null;

		} catch (SQLException e) {
			// Erro caso haja problemas para se conectar ao banco de dados
			System.out.println("Erro caso haja problemas para se conectar ao banco de dados");
			e.printStackTrace();
			return null;
		}
	}

	// Metodo responsavel por inserir informações no banco que são captadas na
	// camada viw
	public void inserirContatos(JavaBeans contato) {
		String insert = "INSERT INTO contatos (nome, telefone, email) VALUES (?,?,?)";
		try {
			Connection con = conectar();
			// preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(insert);
			// subistituir os parametros (?) pelo contéudo das variaveis da classe
			// JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());
			// executando a query
			pst.executeUpdate();
			// Encerrando a connexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Metodo responsavel por recuperar do banco as informações desejadas do banco
	public ArrayList<JavaBeans> listarContatos() {
		String read = "SELECT * FROM contatos ORDER BY nome";
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// Laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variáveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);
				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, telefone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Selecionar contato
	public void selecionarContato(JavaBeans contato) {
		String readSelect = "SELECT * FROM contatos WHERE idcon = ?::int4";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(readSelect);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Inseri os valores nos atributos da classe JavaBeans
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setTelefone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void alterarContato(JavaBeans contato) {
		String create = "UPDATE contatos SET nome=?,telefone=?,email=? WHERE idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeQuery();
			pst.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//Deletar o contato
	public void deletarContato(JavaBeans contato) {
		String delete = "DELETE FROM contatos WHERE idcon=?::int4";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			pst.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
