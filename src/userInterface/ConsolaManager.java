package userInterface;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class ConsolaManager {

	private static JTextPane Consola;
	private static JScrollPane scr;
	private static ConsolaManager instancia ;
	
	private ConsolaManager (){
		Consola.setContentType("text/html");
	}
	
	private  ConsolaManager( JTextPane consola2,JScrollPane scr ){
		ConsolaManager.setConsola(consola2);
		ConsolaManager.setScr(scr);		
	}
	
	public static ConsolaManager getInstance(){
		if ( instancia == null ){
			instancia = new ConsolaManager();
		}
		return instancia;
	}
	
	public static ConsolaManager getInstance(JTextPane consola2,JScrollPane scr){
		if ( instancia == null ){
			instancia = new ConsolaManager(consola2,scr);
		}
		return instancia;
	}
	
	public  void escribir (String s){
		// Con esto se escribe en la consola.
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setFontSize(attrs, 14);
		Consola.setCaretPosition(Consola.getStyledDocument().getLength());
		try {
			  if ( getConsola() != null )
				  getConsola().getDocument().insertString(getConsola().getDocument().getLength(),"  "+ s +"\n", attrs);
		   } catch(BadLocationException exc) {}
	}
	
	public void escribirWarning(String s){
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setFontSize(attrs, 14);
		Consola.setCaretPosition(Consola.getStyledDocument().getLength());
		Consola.insertIcon(new ImageIcon(mainWindow.class.getResource("/images/warning.gif")));
		try {
			Consola.getStyledDocument().insertString(Consola.getStyledDocument().getLength(), s+"\n", attrs);
		} catch (BadLocationException e) {}
	}

	public void escribirError(String s){
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setFontSize(attrs, 14);
		Consola.setCaretPosition(Consola.getStyledDocument().getLength());
		Consola.insertIcon(new ImageIcon(mainWindow.class.getResource("/images/error.gif")));
		try {
			Consola.getStyledDocument().insertString(Consola.getStyledDocument().getLength(), s+"\n", attrs);
		} catch (BadLocationException e) {}
	}
	
	public void escribirInfo(String s){
		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setFontSize(attrs, 14);
		Consola.setCaretPosition(Consola.getStyledDocument().getLength());
		Consola.insertIcon(new ImageIcon(mainWindow.class.getResource("/images/information.gif")));
		try {
			Consola.getStyledDocument().insertString(Consola.getStyledDocument().getLength(), s+"\n", attrs);
		} catch (BadLocationException e) {}
	}
	
	public void borrar(){
		getConsola().setText("");
	}

	public static JEditorPane getConsola() {
		return Consola;
	}

	public static void setConsola(JTextPane consola2) {
		Consola = consola2;
	}

	public static JScrollPane getScr() {
		return scr;
	}

	public static void setScr(JScrollPane scr) {
		ConsolaManager.scr = scr;
	}
}
