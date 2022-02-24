package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		// Invoca o metodo contatos para redirecionar a página main para página
		// agenda.jsp
		if (action.equals("/main")) {
			contatos(request, response);

		} else if (action.equals("/insert")) {
			novoContato(request, response);

		} else if (action.equals("/select")) {
			listarContato(request, response);

		} else if (action.equals("/update")) {
			editarContato(request, response);

		} else if (action.equals("/delete")) {
			removerContato(request, response);

		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);

		} else {
			response.sendRedirect("index.html");
		}
	}

//Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// Encaminhar a lista ao documento agenta.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	// Novo contatos
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setar as variavéis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		// Invocar o metodo inserirContato passando o objeto contato
		dao.inserirContatos(contato);
		// Redirecionar para a página agenda
		response.sendRedirect("main");
	}

	// Metodo que irá editar os contatos no banco
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebendo o id do contato que será editado
		String idcon = request.getParameter("idcon");
		// Setanto o valor do idcon que vem da página novo contato no atributo da classe
		// JavaBeans
		// idcon
		contato.setIdcon(idcon);
		// Executa o método selecionarContato (Class DAO)
		dao.selecionarContato(contato);
		// Setar os atributos do formulário com conteúdo da classe JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("telefone", contato.getTelefone());
		request.setAttribute("email", contato.getEmail());
		// Encaminhar ao documento editar
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

//Metodo edita um registro no banco
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variáveis da JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		dao.alterarContato(contato);
		// Redirecionar página agenda.jsp (atualizando as alterações feitas)
		response.sendRedirect("main");
	}

//Metodo responsavel por remover um registro no banco
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebe do id do contato a ser exclido (validador.js)
		String idcon = request.getParameter("idcon");
		// Seta a variável idcon JavaBeans
		contato.setIdcon(idcon);
		// Executa o método deletarContato (DAO) passando o objeto contato
		dao.deletarContato(contato);
		// Redirecionar página agenda.jsp (atualizando as alterações feitas)
		response.sendRedirect("main");

	}

	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// Tipo de documento
			response.setContentType("apllication/pdf");
			// Nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			PdfWriter.getInstance(documento, response.getOutputStream());
			// Abrir o documento -> contéudo
			documento.open();
			documento.add(new Paragraph("Lista de Contados:"));
			documento.add(new Paragraph(" "));
			// criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			// Cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// Popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getTelefone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			documento.close();
		}
	}
}
