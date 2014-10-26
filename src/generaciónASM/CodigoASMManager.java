package generaciónASM;

import javax.swing.JEditorPane;

public class CodigoASMManager {

	private static CodigoASMManager instancia = null;
	private static JEditorPane editor;

	private CodigoASMManager(){

	}

	private CodigoASMManager(JEditorPane editor){
		CodigoASMManager.setEditor(editor);
	}

	public static CodigoASMManager getInstance(){
		if ( instancia == null ){
			instancia = new CodigoASMManager();
		}
		return instancia;
	}

	public static CodigoASMManager getInstance(JEditorPane editor){
		if ( instancia == null ){
			instancia = new CodigoASMManager(editor);
		}
		return instancia;
	}
	
	
	public void setCodigo(String cod){
		getEditor().setText(cod);
	}

	public static JEditorPane getEditor() {
		return editor;
	}

	public static void setEditor(JEditorPane editor) {
		CodigoASMManager.editor = editor;
	}
	
	public void clearCodigo(){
		getEditor().setText("");
	}
}
