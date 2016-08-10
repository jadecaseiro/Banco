import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Consulta extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	
	private final static String url = "jdbc:mysql://localhost:3306/impacta";
	
	private final static String username = "root";
	private final static String password = "Imp@ct@";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	private String nome = null;
	private String telefone = null;
	
	public void openDB(){
		try{
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			System.out.println("\nConexão estabelecida com sucesso!\n");
		}catch(SQLException e){
			System.out.println("\nNão foi possível estabelecer conexão " + e + "\n");
			System.exit(1);
		}
	}
	
	public void closeDB(){
		try{
			con.close();
		}catch(SQLException e){
			System.out.println("\nNão foi possível fechar conexão " + e + "\n");
			System.exit(1);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consulta frame = new Consulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Consulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(39, 32, 46, 14);
		contentPane.add(lblCodigo);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(39, 83, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(39, 148, 46, 14);
		contentPane.add(lblTelefone);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(162, 29, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNom = new JLabel("New label");
		lblNom.setBounds(186, 83, 46, 14);
		contentPane.add(lblNom);
		
		JLabel lblTelefon = new JLabel("New label");
		lblTelefon.setBounds(186, 148, 46, 14);
		contentPane.add(lblTelefon);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				openDB();
				mostra();
				closeDB();
				
				
			}
		});
		btnConsultar.setBounds(39, 214, 91, 23);
		contentPane.add(btnConsultar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(186, 214, 91, 23);
		contentPane.add(btnSair);
	}
	public void mostra(){
		String query;
		
		try{
			query = "SELECT * FROM alunos WHERE código = " + txtCodigo.getText();
			
			rs = stmt.executeQuery(query);
			while(rs.next()){
				lblNom.setText(rs.getString("nome"));
				lblTelefon.setText(rs.getString("telefone"));
			}
		}catch(SQLException e){
			setNome(null);
			setTelefone(null);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
