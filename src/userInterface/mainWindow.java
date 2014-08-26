package userInterface;
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
import com.alee.managers.language.LanguageConstants;
import com.alee.managers.language.LanguageManager;

import compiler.ArchivoFuente;
import compiler.Compilador;
import filtro.FiltroCvr;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JButton;
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
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTabbedPane;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;


public class mainWindow {

	private JFrame frame;
	private JScrollPane scrollConsola;
	private JTextPane Consola;
	private static File file = null;
	private JEditorPane editor;
	private String titulo = " CVR - ";
	private JTabbedPane tabbedPane;
	
	private Compilador compilador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*ArchivoFuente arch = new ArchivoFuente(file);
		arch.reset();
		
		AnalizadorLexico lexico = new AnalizadorLexico(arch);
		lexico.getToken();*/
		
		//comp.compilar(new ArchivoFuente(file));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//Look and feel
				    LanguageManager.setDefaultLanguage(LanguageConstants.SPANISH);
					WebLookAndFeel.install();
					
					mainWindow window = new mainWindow();
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
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(titulo );
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(mainWindow.class.getResource("/images/icono.png")));
		frame.setBounds(0,0,java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.setIcon(new ImageIcon(mainWindow.class.getResource("/images/nuevo.gif")));
		mnArchivo.add(mntmNuevo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.setIcon(new ImageIcon(mainWindow.class.getResource("/images/open.png")));
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarArchivo();
			}
		});
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setIcon(new ImageIcon(mainWindow.class.getResource("/images/save.png")));
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como..");
		mntmGuardarComo.setIcon(new ImageIcon(mainWindow.class.getResource("/images/saveall.png")));
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
		mntmEjecutar.setIcon(new ImageIcon(mainWindow.class.getResource("/images/run.gif")));
		mnEjecutar.add(mntmEjecutar);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton botonNuevo = new JButton("");
		botonNuevo.setIcon(new ImageIcon(mainWindow.class.getResource("/images/nuevo.gif")));
		botonNuevo.setToolTipText("Nuevo archivo .cvr");
		toolBar.add(botonNuevo);
		
		JButton botonCargar = new JButton("");
		botonCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarArchivo();				
			}
		});
		botonCargar.setIcon(new ImageIcon(mainWindow.class.getResource("/images/open.png")));
		botonCargar.setToolTipText("Abrir archivo .cvr");
		toolBar.add(botonCargar);
		
		JButton botonGuardar = new JButton("");
		botonGuardar.setIcon(new ImageIcon(mainWindow.class.getResource("/images/save.png")));
		botonGuardar.setToolTipText("Guardar");
		toolBar.add(botonGuardar);
		
		JButton botonGuardarComo = new JButton("");
		botonGuardarComo.setIcon(new ImageIcon(mainWindow.class.getResource("/images/saveall.png")));
		botonGuardarComo.setToolTipText("Guardar como..");
		toolBar.add(botonGuardarComo);
	
		toolBar.addSeparator();
		
		JButton botonEjecutar = new JButton("");
		botonEjecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				compilar();
			}
		});
		botonEjecutar.setIcon(new ImageIcon(mainWindow.class.getResource("/images/run.gif")));
		botonEjecutar.setToolTipText("Ejecutar");
		toolBar.add(botonEjecutar);

		//Consola
		Consola = new JTextPane();
		Consola.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Consola.setEditable(false);
		Consola.setToolTipText("Consola");
		Consola.setPreferredSize(new Dimension(0, 175));
		Consola.setDoubleBuffered(true);
		Consola.setFont(new Font("Verdana", Font.PLAIN, 12));
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
		Cons.setIcon(new ImageIcon(mainWindow.class.getResource("/images/monitor_obj.gif")));
		Cons.setHorizontalAlignment(SwingConstants.LEFT);
		Cons.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_Cons = new GridBagConstraints();
		gbc_Cons.anchor = GridBagConstraints.WEST;
		gbc_Cons.insets = new Insets(0, 0, 0, 5);
		gbc_Cons.gridx = 0;
		gbc_Cons.gridy = 0;
		PanelConsola.add(Cons, gbc_Cons);
		Borrar.setSelectedIcon(new ImageIcon(mainWindow.class.getResource("/images/borrar_consola.png")));
		Borrar.setIcon(new ImageIcon(mainWindow.class.getResource("/images/borrar_consola.png")));
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
		Min.setIcon(new ImageIcon(mainWindow.class.getResource("/images/minimizar.png")));
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
		Max.setIcon(new ImageIcon(mainWindow.class.getResource("/images/maximizar.png")));
		GridBagConstraints gbc_Max = new GridBagConstraints();
		gbc_Max.gridx = 3;
		gbc_Max.gridy = 0;
		PanelConsola.add(Max, gbc_Max);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//Editor con su scroll y contador de lineas.
		editor = new JEditorPane();
		editor.setFont(new Font("Consolas", 0, 16));
		JScrollPane scrollPaneEditor = new JScrollPane(editor);
		TextLineNumber tln = new TextLineNumber(editor);
		tln.setDigitAlignment(TextLineNumber.CENTER);
		//Esta linea descomentarla a la hora de ejecutar. Problemas en el desing de eclipse..
		scrollPaneEditor.setRowHeaderView( tln );
		
		//Eventos del editor
		editor.getDocument().addDocumentListener(new DocumentListener() {
		
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	if (file != null)
	        		tabbedPane.setTitleAt(0,file.getName() + "*");
	        	else
	        		tabbedPane.setTitleAt(0,"Sin Título" + "*");
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	if (file != null)
	        		tabbedPane.setTitleAt(0,file.getName() + "*");
	        	else
	        		tabbedPane.setTitleAt(0,"Sin Título" + "*");
	        }

	        @Override
	        public void changedUpdate(DocumentEvent arg0) {	   
	        }
	    });
		
		//Agregamos el tab del editor.
		tabbedPane.addTab("Sin Título",scrollPaneEditor );
		
		//Editor léxico
		JEditorPane editorLexico = new JEditorPane();
		editorLexico.setEditable(false);
		editorLexico.setFont(new Font("Consolas", 0, 16));
		JScrollPane scrollPaneLexico = new JScrollPane(editorLexico);
		
		//Agregamos el tab del analizador léxico
		tabbedPane.addTab("Analizador Léxico", scrollPaneLexico);

		//Editor léxico
		JEditorPane editorTabla = new JEditorPane();
		editorTabla.setEditable(false);
		editorTabla.setFont(new Font("Consolas", 0, 16));
		JScrollPane scrollPaneTabla = new JScrollPane(editorTabla);

		//Agregamos el tab de la tabla de simbolos
		tabbedPane.addTab("Tabla de Símbolos", scrollPaneTabla);

		//Ejemplos
		ConsolaManager.getInstance().escribir("Texto Normal");
		ConsolaManager.getInstance().escribirInfo("Información");
		ConsolaManager.getInstance().escribirWarning("Warning");
		ConsolaManager.getInstance().escribirError("Error");
		
		compilador = new Compilador();
	}

	private void compilar() {
		if (file != null){
			compilador.compilar(new ArchivoFuente(file));
		}
	}

	private void cargarArchivo() {
		WebFileChooser fileChooser = new WebFileChooser("sample");
		fileChooser.setFileFilter(new FiltroCvr());
		fileChooser.setMultiSelectionEnabled ( false );
		fileChooser.setDialogTitle("Abrir archivo CVR");
		fileChooser.setApproveButtonText("Abrir");
		if ( fileChooser.showOpenDialog ( frame ) == WebFileChooser.APPROVE_OPTION )
		{
			File aux = fileChooser.getSelectedFile();
			if (aux.isFile()){
				file = aux;
				try {
					editor.setPage(file.toURI().toURL());
					frame.setTitle(titulo + file.getAbsolutePath());
					tabbedPane.setSelectedIndex(0);
					tabbedPane.setTitleAt(0, file.getName());
					
					//Eventos del editor
					editor.getDocument().addDocumentListener(new DocumentListener() {
					
				        @Override
				        public void removeUpdate(DocumentEvent e) {
				        	if (file != null)
				        		tabbedPane.setTitleAt(0,file.getName() + "*");
				        	else
				        		tabbedPane.setTitleAt(0,"Sin Título" + "*");
				        }

				        @Override
				        public void insertUpdate(DocumentEvent e) {
				        	if (file != null)
				        		tabbedPane.setTitleAt(0,file.getName() + "*");
				        	else
				        		tabbedPane.setTitleAt(0,"Sin Título" + "*");
				        }

				        @Override
				        public void changedUpdate(DocumentEvent arg0) {	   
				        }
				    });
				} catch (IOException e) {}
			}
		}
	}

}
