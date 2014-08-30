package gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.filechooser.WebFileChooser;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.managers.language.LanguageConstants;
import com.alee.managers.language.LanguageManager;

import filtro.FiltroCvr;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JEditorPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTabbedPane;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import proyecto.Proyecto;
import proyecto.Token;

public class MainWindow {
	private JFrame frame;
	private JScrollPane scrollConsola;
	private JTextPane Consola;
	private JEditorPane editor;
	private String titulo = " CVR - ";
	private JTabbedPane tabbedPane;

	private JMenuItem mntmNuevo;
	private JMenuItem mntmAbrir;
	private JMenuItem mntmGuardar;
	private JMenuItem mntmGuardarComo;
	private JButton botonNuevo;
	private JButton botonCargar;
	private JButton botonGuardar;
	private JButton botonGuardarComo;
	private JTable tablaSimbolos;
	private JEditorPane editorLexico;
	
	private static Proyecto proyecto;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Look and feel
					LanguageManager.setDefaultLanguage(LanguageConstants.SPANISH);
					WebLookAndFeel.install();
					
					proyecto = new Proyecto();
					
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(titulo );
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/icono.png")));
		frame.setBounds(0,0,java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoArchivo();
			}
		});
		mntmNuevo.setIcon(new ImageIcon(MainWindow.class.getResource("/images/nuevo.gif")));
		mnArchivo.add(mntmNuevo);

		mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.setIcon(new ImageIcon(MainWindow.class.getResource("/images/open.png")));
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarArchivo();
			}
		});
		mnArchivo.add(mntmAbrir);

		mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setEnabled(false);
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mntmGuardar.setIcon(new ImageIcon(MainWindow.class.getResource("/images/save.png")));
		mnArchivo.add(mntmGuardar);

		mntmGuardarComo = new JMenuItem("Guardar Como..");
		mntmGuardarComo.setEnabled(false);
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		mntmGuardarComo.setIcon(new ImageIcon(MainWindow.class.getResource("/images/saveall.png")));
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnEjecutar = new JMenu("Ejecutar");
		menuBar.add(mnEjecutar);

		JMenuItem mntmEjecutar = new JMenuItem("Ejecutar");
		mntmEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				compilar();
			}
		});
		mntmEjecutar.setIcon(new ImageIcon(MainWindow.class.getResource("/images/run.gif")));
		mnEjecutar.add(mntmEjecutar);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);

		botonNuevo = new JButton("");
		botonNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevoArchivo();
			}
		});
		botonNuevo.setIcon(new ImageIcon(MainWindow.class.getResource("/images/nuevo.gif")));
		botonNuevo.setToolTipText("Nuevo archivo .cvr");
		toolBar.add(botonNuevo);

		botonCargar = new JButton("");
		botonCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarArchivo();				
			}
		});
		botonCargar.setIcon(new ImageIcon(MainWindow.class.getResource("/images/open.png")));
		botonCargar.setToolTipText("Abrir archivo .cvr");
		toolBar.add(botonCargar);

		botonGuardar = new JButton("");
		botonGuardar.setEnabled(false);
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		botonGuardar.setIcon(new ImageIcon(MainWindow.class.getResource("/images/save.png")));
		botonGuardar.setToolTipText("Guardar");
		toolBar.add(botonGuardar);

		botonGuardarComo = new JButton("");
		botonGuardarComo.setEnabled(false);
		botonGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		botonGuardarComo.setIcon(new ImageIcon(MainWindow.class.getResource("/images/saveall.png")));
		botonGuardarComo.setToolTipText("Guardar como..");
		toolBar.add(botonGuardarComo);

		toolBar.addSeparator();

		JButton botonEjecutar = new JButton("");
		botonEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				compilar();
			}
		});
		botonEjecutar.setIcon(new ImageIcon(MainWindow.class.getResource("/images/run.gif")));
		botonEjecutar.setToolTipText("Ejecutar");
		toolBar.add(botonEjecutar);

		//Consola
		Consola = new JTextPane();
		Consola.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Consola.setEditable(false);
		Consola.setToolTipText("Consola");
		Consola.setPreferredSize(new Dimension(0, 175));
		Consola.setDoubleBuffered(true);
		Consola.setFont(new Font("Consolas", Font.PLAIN, 12));
		frame.getContentPane().add(Consola, BorderLayout.SOUTH);

		//Scroll de la Consola
		scrollConsola = new JScrollPane(Consola);
		scrollConsola.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollConsola.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollConsola.setPreferredSize(new Dimension(0,175));
		frame.getContentPane().add(scrollConsola, BorderLayout.SOUTH);

		//Asignamos el ConsolaManager
		ConsolaManager.getInstance(Consola,scrollConsola);

		//Panel de Consola
		final JPanel PanelConsola = new JPanel();
		scrollConsola.setColumnHeaderView(PanelConsola);
		GridBagLayout gbl_PanelConsola = new GridBagLayout();
		gbl_PanelConsola.columnWidths = new int[]{94, 19, 19, 19, 0};
		gbl_PanelConsola.rowHeights = new int[]{21, 0};
		gbl_PanelConsola.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_PanelConsola.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		PanelConsola.setLayout(gbl_PanelConsola);

		//Boton de Borrar Consola
		final JButton Borrar = new JButton("");
		Borrar.setBorder(null);
		Borrar.setToolTipText("Borrar Consola");
		//Insets !! Importante para que el boton sea chiquito.
		Borrar.setMargin(new java.awt.Insets(2, 2, 2, 2));
		Borrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Consola.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Borrar.setBorder(new LineBorder(Color.GRAY, 1));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Borrar.setBorder(null);
			}
		});

		//Label que dice CONSOLA
		JLabel Cons = new JLabel("Consola   ");
		Cons.setIcon(new ImageIcon(MainWindow.class.getResource("/images/monitor_obj.gif")));
		Cons.setHorizontalAlignment(SwingConstants.LEFT);
		Cons.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_Cons = new GridBagConstraints();
		gbc_Cons.anchor = GridBagConstraints.WEST;
		gbc_Cons.insets = new Insets(0, 0, 0, 5);
		gbc_Cons.gridx = 0;
		gbc_Cons.gridy = 0;
		PanelConsola.add(Cons, gbc_Cons);
		Borrar.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/images/borrar_consola.png")));
		Borrar.setIcon(new ImageIcon(MainWindow.class.getResource("/images/borrar_consola.png")));
		Borrar.setPreferredSize(new Dimension(19, 18));
		Borrar.setMargin(new Insets(0, 0, 0, 0));
		Borrar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		GridBagConstraints gbc_Borrar = new GridBagConstraints();
		gbc_Borrar.anchor = GridBagConstraints.EAST;
		gbc_Borrar.insets = new Insets(0, 0, 0, 5);
		gbc_Borrar.gridx = 1;
		gbc_Borrar.gridy = 0;
		PanelConsola.add(Borrar, gbc_Borrar);

		//Boton de maximizar.
		final JButton Max = new JButton("");
		//Boton de minimzar.
		final JButton Min = new JButton("");
		Max.setEnabled(false);
		Max.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Consola.setPreferredSize(new Dimension( java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,175));
				scrollConsola.setPreferredSize(new Dimension( java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,175));
				frame.pack();
				frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
				Max.setEnabled(false);
				Min.setEnabled(true);
			}
		});
		Max.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if ( Max.isEnabled() )
					Max.setBorder(new LineBorder(Color.GRAY, 1));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Max.setBorder(null);
			}
		});

		//Boton de minimizar.
		Min.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if ( Min.isEnabled() )
					Min.setBorder(new LineBorder(Color.GRAY, 1));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Min.setBorder(null);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//Achicar la consola aca.
				Consola.setPreferredSize(new Dimension( java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,0));
				scrollConsola.setPreferredSize(new Dimension( java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,24));
				frame.pack();
				frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
				Max.setEnabled(true);
				Min.setEnabled(false);
			}
		});
		Min.setAlignmentX(Component.CENTER_ALIGNMENT);
		Min.setBorder(null);
		Min.setToolTipText("Minimizar Consola");
		//Insets !! Importante para que el boton sea chiquito.
		Min.setMargin(new java.awt.Insets(2, 2, 2, 2));
		Min.setPreferredSize(new Dimension(19, 18));
		Min.setMargin(new Insets(0, 0, 0, 0));
		Min.setIcon(new ImageIcon(MainWindow.class.getResource("/images/minimizar.png")));
		GridBagConstraints gbc_Min = new GridBagConstraints();
		gbc_Min.insets = new Insets(0, 0, 0, 5);
		gbc_Min.gridx = 2;
		gbc_Min.gridy = 0;
		PanelConsola.add(Min, gbc_Min);
		Max.setAlignmentX(Component.CENTER_ALIGNMENT);
		Max.setBorder(null);
		Max.setToolTipText("Maximizar Consola");
		//Insets !! Importante para que el boton sea chiquito.
		Max.setMargin(new java.awt.Insets(2, 2, 2, 2));
		Max.setPreferredSize(new Dimension(19, 18));
		Max.setMargin(new Insets(0, 0, 0, 0));
		Max.setIcon(new ImageIcon(MainWindow.class.getResource("/images/maximizar.png")));
		GridBagConstraints gbc_Max = new GridBagConstraints();
		gbc_Max.gridx = 3;
		gbc_Max.gridy = 0;
		PanelConsola.add(Max, gbc_Max);

		//tabbedPane = tabs
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		//Editor con su scroll y contador de lineas.
		editor = new JEditorPane();
		editor.setFont(new Font("Consolas", 0, 16));
		JScrollPane scrollPaneEditor = new JScrollPane(editor);
		TextLineNumber tln = new TextLineNumber(editor);
		tln.setDigitAlignment(TextLineNumber.CENTER);
		tln.setFont(new Font("Consolas", 0, 18));
		//Esta linea descomentarla a la hora de ejecutar. Problemas en el design de eclipse..
		scrollPaneEditor.setRowHeaderView( tln );

		//Eventos del editor
		editor.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				operacion();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				operacion();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {	   
			}
			private void operacion(){
				if (proyecto.getFile() != null)
					if (proyecto.getFile().getName().endsWith(".cvr"))
						tabbedPane.setTitleAt(0,proyecto.getFile().getName() + "*");
					else
						tabbedPane.setTitleAt(0,proyecto.getFile().getName() + ".cvr*");
				else
					tabbedPane.setTitleAt(0,"Sin Título" + "*");
				setBotonesGuardar(true);
			}
		});

		//Agregamos el tab del editor.
		tabbedPane.addTab("Sin Título",scrollPaneEditor );

		//Editor léxico
		editorLexico = new JEditorPane();
		editorLexico.setEditable(false);
		editorLexico.setFont(new Font("Consolas", 0, 16));
		JScrollPane scrollPaneLexico = new JScrollPane(editorLexico);

		//Agregamos el tab del analizador léxico
		tabbedPane.addTab("Analizador Léxico", scrollPaneLexico);
		JScrollPane scrollPaneTabla = new JScrollPane();

		//Agregamos el tab de la tabla de simbolos
		tabbedPane.addTab("Tabla de Símbolos", scrollPaneTabla);

		tablaSimbolos = new JTable();
		tablaSimbolos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Categoria", "Lexema", "Palabra Reservada"
				}
				));

		scrollPaneTabla.setViewportView(tablaSimbolos);

		// Tabla de simbolos
		Observer o = new Observer() {
			public void update(Observable o, Object arg) {
				if (arg == null)
					borrarTablaSimbolos();
				else {
					Token t = (Token) arg;
					DefaultTableModel modelo = (DefaultTableModel) tablaSimbolos.getModel();
					modelo.addRow(new Object[] {t.getTipo(),t.getLexema(),t.isReservado()});
				}
			}
		};
		proyecto.getTablaDeSimbolos().addObserver(o);

		Observer obsTokens = new Observer(){
			@Override
			public void update(Observable arg0, Object arg1) {
				if (arg1 == null)
					editorLexico.setText("");
				else
					agregarToken( (int) arg1);
			}
		};
		proyecto.addObserver(obsTokens);
	}
	
	protected void agregarToken(int pos) {
		editorLexico.setText(editorLexico.getText() + "[" + proyecto.getTablaDeSimbolos().getToken(pos) + "]\n");
	}

	private void compilar() {
		if (proyecto.getFile() == null)
			guardarComo();
		
		if (proyecto.getFile() != null){
			editorLexico.setText("");
			proyecto.compilar();
		}
	}

	private void guardar(){
		if (proyecto.getFile() != null){
			String path = proyecto.getFile().getAbsolutePath();
			FileWriter out;
			try {
				out = new FileWriter(path);
				out.write(editor.getText());
				out.close();
				tabbedPane.setSelectedIndex(0);
				frame.setTitle(titulo + proyecto.getFile().getAbsolutePath());
				tabbedPane.setTitleAt(0, proyecto.getFile().getName());
				setBotonesGuardar(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			guardarComo();	
		}
	}

	private void guardarComo() {
		WebFileChooser guardador = new WebFileChooser();
		guardador.setFileFilter(new FiltroCvr());
		guardador.setFileSelectionMode(JFileChooser.FILES_ONLY);  
		guardador.setMultiSelectionEnabled ( false );
		guardador.setDialogTitle("Guardar archivo CVR");
		guardador.setApproveButtonText("Guardar");

		if ( guardador.showSaveDialog ( frame ) == WebFileChooser.APPROVE_OPTION ){
			proyecto.setFile(guardador.getSelectedFile());
			if (!proyecto.getFile().getName().endsWith("cvr"))
				proyecto.setFile(new File(proyecto.getFile().getAbsolutePath() + ".cvr"));
			guardar();
		}
	}

	private void nuevoArchivo() {
		if (tabbedPane.getTitleAt(0).endsWith("*")){
			//Archivo con cambios
			if( WebOptionPane.showConfirmDialog ( null, "El archivo presenta cambios ¿Desea guardar y continuar? [No = descartar]", "Confirmar", WebOptionPane.YES_NO_OPTION,
					WebOptionPane.QUESTION_MESSAGE ) == WebOptionPane.YES_OPTION){
				//Guardar
				guardar();
			}
		}
		//Descartamos los cambios
		editor.setText("");
		proyecto.setFile(null);
		frame.setTitle(titulo);
		tabbedPane.setTitleAt(0, "Sin Título");
		setBotonesGuardar(false);
	}

	private void cargarArchivo() {
		if (tabbedPane.getTitleAt(0).endsWith("*")){
			//Archivo con cambios
			if( WebOptionPane.showConfirmDialog ( null, "El archivo presenta cambios ¿Desea guardar?", "Confirmar", WebOptionPane.YES_NO_OPTION,
					WebOptionPane.QUESTION_MESSAGE ) == WebOptionPane.YES_OPTION){
				//Guardar
				guardar();
			}
		}
		//Pasamos a cargar el archivo
		WebFileChooser fileChooser = new WebFileChooser("sample");
		fileChooser.setFileFilter(new FiltroCvr());
		fileChooser.setMultiSelectionEnabled ( false );
		fileChooser.setDialogTitle("Abrir archivo CVR");
		fileChooser.setApproveButtonText("Abrir");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if ( fileChooser.showOpenDialog ( frame ) == WebFileChooser.APPROVE_OPTION )
		{
			proyecto.setFile(fileChooser.getSelectedFile());
			try {
				editor.setPage(proyecto.getFile().toURI().toURL());
				frame.setTitle(titulo + proyecto.getFile().getAbsolutePath());
				tabbedPane.setSelectedIndex(0);
				tabbedPane.setTitleAt(0, proyecto.getFile().getName());
				setBotonesGuardar(false);
				//Eventos del editor
				editor.getDocument().addDocumentListener(new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent e) {
						operacion();
					}
					@Override
					public void insertUpdate(DocumentEvent e) {
						operacion();
					}
					@Override
					public void changedUpdate(DocumentEvent arg0) {	   
					}

					public void operacion(){
						if (proyecto.getFile() != null)
							if (proyecto.getFile().getName().endsWith(".cvr"))
								tabbedPane.setTitleAt(0,proyecto.getFile().getName() + "*");
							else
								tabbedPane.setTitleAt(0,proyecto.getFile().getName()+ ".cvr*");
						else
							tabbedPane.setTitleAt(0,"Sin Título" + "*");
						//Actualizamos los botones de guardar
						setBotonesGuardar(true);
					}
				});
			} catch (IOException e) {}
		}
	}

	private void setBotonesGuardar(boolean condicion){
		botonGuardar.setEnabled(condicion);
		botonGuardarComo.setEnabled(condicion);
		mntmGuardar.setEnabled(condicion);
		mntmGuardarComo.setEnabled(condicion);
	}

	private void borrarTablaSimbolos(){
		DefaultTableModel modelo = (DefaultTableModel) tablaSimbolos.getModel();
		int a = modelo.getRowCount()-1;
		for(int i=a;i>=0;i--){  
			modelo.removeRow(i);
		}
	}
}