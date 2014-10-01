//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package sintactico;



//#line 2 "gramatica.y"

/* Imports necesarios en la clase Parser */
import gui.ConsolaManager;
import lexico.AnalizadorLexico;
import proyecto.Proyecto;
import proyecto.Token;

//#line 25 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENTIFICADOR=257;
public final static short ENTERO=258;
public final static short ENTERO_LSS=259;
public final static short CADENA_MULTILINEA=260;
public final static short PR_SI=261;
public final static short PR_ENTONCES=262;
public final static short PR_SINO=263;
public final static short PR_IMPRIMIR=264;
public final static short PR_ENTERO=265;
public final static short PR_ENTERO_LSS=266;
public final static short PR_ITERAR=267;
public final static short PR_HASTA=268;
public final static short PR_VECTOR=269;
public final static short PR_DE=270;
public final static short COMP_MAYOR_IGUAL=271;
public final static short COMP_MENOR_IGUAL=272;
public final static short COMP_DISTINTO=273;
public final static short ASIGNACION=274;
public final static short PUNTO_PUNTO=275;
public final static short FUERA_RANGO=276;
public final static short NEG=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    3,    3,    5,    5,    4,
    4,    2,    2,    9,    6,   11,    6,   13,    6,    6,
    7,    7,    8,    8,    8,   18,   17,   10,   12,   12,
   20,   19,   21,   19,   22,   19,   19,   19,   24,   14,
   16,   16,   26,   16,   16,   15,   27,   27,   27,   27,
   27,   27,   25,   25,   25,   28,   28,   28,   29,   29,
   29,   29,   30,   30,   30,   23,   23,
};
final static short yylen[] = {                            2,
    2,    2,    0,    3,   10,    3,    3,    1,    3,    1,
    1,    1,    2,    0,    2,    0,    2,    0,    2,    1,
    1,    1,    6,    8,    1,    0,    6,    5,    5,    1,
    0,    5,    0,    5,    0,    5,    3,    2,    0,    5,
    1,    3,    0,    6,    2,    3,    1,    1,    1,    1,
    1,    1,    3,    3,    1,    3,    3,    1,    1,    2,
    2,    1,    1,    1,    1,    1,    4,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   10,   11,    0,    2,    0,   12,    0,
    0,    0,   20,   39,    0,    0,   22,   21,   13,    0,
    8,    0,    0,   15,   25,    0,   17,    0,   19,   30,
    0,    7,    0,    0,   65,   62,    0,   63,    0,    0,
   58,   59,    6,    4,    0,    0,    0,    0,   41,    0,
    0,   38,    0,    0,    0,    0,    0,   60,   61,   67,
    0,    0,    0,    0,    9,   64,    0,    0,    0,   45,
    0,   43,    0,   37,    0,   33,    0,    0,    0,    0,
    0,   56,   57,    0,   48,   50,   52,   47,   49,   51,
    0,    0,   42,    3,    0,    0,    0,    0,    0,   40,
    0,    0,    0,    0,    0,   28,   29,   36,   34,   32,
    0,    0,   27,    0,    0,    0,   44,    0,   24,    5,
};
final static short yydgoto[] = {                          1,
    2,    6,   17,    8,   22,   18,   49,   24,   10,   27,
   11,   29,   12,   13,   67,   50,   25,   47,   30,   54,
   98,   97,   38,   31,   68,   94,   91,   40,   41,   42,
};
final static short yysindex[] = {                         0,
    0, -174,  -84,    0,    0, -174,    0, -177,    0, -212,
 -215, -162,    0,    0,   28,  -45,    0,    0,    0,   31,
    0,   29,   74,    0,    0, -107,    0,  -13,    0,    0,
 -159,    0,   25, -158,    0,    0, -163,    0,    5,   34,
    0,    0,    0,    0, -139,  -40,  -40, -100,    0, -149,
   61,    0,  -30, -138,  -40,  -40, -137,    0,    0,    0,
  -40,  -40,  -40,  -40,    0,    0,   82,  -19,   83,    0,
  -88,    0,  -40,    0,   84,    0, -129,   23,   35,   34,
   34,    0,    0, -133,    0,    0,    0,    0,    0,    0,
  -40, -132,    0,    0,   72,   73,   75,   77,   78,    0,
 -136, -107,   56, -107, -174,    0,    0,    0,    0,    0,
 -131, -125,    0,  -86, -160, -107,    0,   85,    0,    0,
};
final static short yyrindex[] = {                         0,
    0, -167, -128,    0,    0,    9,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -32,    0,    0, -167,    0, -115,    0,    0,
    0,    0,  -39,   18,    0,    0,    0,    0,    0,  -31,
    0,    0,    0,    0,    0,    0,    0, -167,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -167,    0,    0,    0,   88,    0,    0,    0,    0,  -26,
   10,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -167,   15, -167, -167,    0,    0,    0,    0,    0,
    0,    1,    0, -167,    0, -167,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   54,   -1,   30,   40,    0,   36,   39,    0,    0,    0,
    0,    0,    0,    0,   12,  -27,    0,    0,    0,    0,
    0,    0,   38,    0,    2,    0,    0,   46,   49,    0,
};
final static int YYTABLESIZE=283;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         37,
   23,   66,   66,   66,   37,   66,   16,   66,    1,   55,
   76,   55,   26,   55,   53,   48,   53,   39,   53,   66,
   66,   66,   66,   61,   70,   62,   53,   55,   55,   55,
   55,    7,   53,   53,   53,   53,   93,    9,  117,   14,
   89,   90,   88,   14,   19,   52,   71,   61,   23,   62,
   54,   26,   54,   66,   54,   46,   78,   39,   69,   64,
   64,   55,   64,   14,   64,   61,   53,   62,   54,   54,
   54,   54,   45,   46,  112,   63,  113,   72,   20,   21,
   64,  100,    3,    9,   95,   14,   32,   44,  119,   43,
    4,    5,  103,   14,   58,   59,   18,   60,   61,   16,
   62,   28,   54,  114,    4,    5,   80,   81,   14,   19,
   64,   82,   83,   46,   55,   56,   57,   65,   73,   74,
   79,   77,   84,   92,   96,   23,   99,  101,  102,  104,
  106,  107,  111,  108,    7,  109,  110,  116,  115,   14,
    9,   14,   14,  120,   31,   66,   35,  105,    0,    3,
    0,   14,   19,   14,  118,    0,    3,    4,    5,    0,
    0,    0,    0,    0,    4,    5,    0,    0,    3,    0,
    3,   15,    0,    0,    0,    0,    4,    5,    4,    5,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   33,   34,   35,    0,    0,   33,   66,   35,    0,
    0,    0,    0,    0,   26,   26,   26,    0,    0,   75,
   36,   66,   66,   66,    0,   36,    0,    0,    0,   55,
   55,   55,   51,   26,   53,   53,   53,    0,    0,    0,
    0,   85,   86,   87,    0,    0,    0,   23,    0,    0,
    0,   23,    0,    0,   23,   23,   23,   23,   23,   14,
    0,    0,   18,    0,    0,   16,    0,    0,    0,    0,
   54,   54,   54,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         45,
    0,   41,   42,   43,   45,   45,   91,   47,    0,   41,
   41,   43,   45,   45,   41,  123,   43,   16,   45,   59,
   60,   61,   62,   43,  125,   45,   40,   59,   60,   61,
   62,    2,   59,   60,   61,   62,  125,    2,  125,    2,
   60,   61,   62,    6,    6,   59,   48,   43,  261,   45,
   41,  267,   43,   93,   45,   41,   55,   56,   47,   42,
   43,   93,   45,   26,   47,   43,   93,   45,   59,   60,
   61,   62,   44,   59,  102,   42,  104,   48,  256,  257,
   47,   59,  257,   48,   73,   48,   59,   59,  116,   59,
  265,  266,   91,  261,  258,  259,  264,   93,   43,  267,
   45,  264,   93,  105,  265,  266,   61,   62,   71,   71,
   93,   63,   64,   40,  274,   91,  275,  257,  268,   59,
  258,  260,   41,   41,   41,  125,  256,   93,  262,  262,
   59,   59,  269,   59,  105,   59,   59,  263,  270,  102,
  105,  104,  105,   59,  260,  274,   59,   94,   -1,  257,
   -1,  114,  114,  116,  115,   -1,  257,  265,  266,   -1,
   -1,   -1,   -1,   -1,  265,  266,   -1,   -1,  257,   -1,
  257,  256,   -1,   -1,   -1,   -1,  265,  266,  265,  266,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,   -1,   -1,  257,  258,  259,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,   -1,   -1,  260,
  276,  271,  272,  273,   -1,  276,   -1,   -1,   -1,  271,
  272,  273,  256,  276,  271,  272,  273,   -1,   -1,   -1,
   -1,  271,  272,  273,   -1,   -1,   -1,  257,   -1,   -1,
   -1,  261,   -1,   -1,  264,  265,  266,  267,  268,  261,
   -1,   -1,  264,   -1,   -1,  267,   -1,   -1,   -1,   -1,
  271,  272,  273,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=277;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENTIFICADOR","ENTERO","ENTERO_LSS",
"CADENA_MULTILINEA","PR_SI","PR_ENTONCES","PR_SINO","PR_IMPRIMIR","PR_ENTERO",
"PR_ENTERO_LSS","PR_ITERAR","PR_HASTA","PR_VECTOR","PR_DE","COMP_MAYOR_IGUAL",
"COMP_MENOR_IGUAL","COMP_DISTINTO","ASIGNACION","PUNTO_PUNTO","FUERA_RANGO",
"NEG",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sent_declarativa sent_ejecutable",
"sent_declarativa : sent_declarativa declaracion",
"sent_declarativa :",
"declaracion : tipo_dato lista_variables ';'",
"declaracion : IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';'",
"declaracion : tipo_dato error ';'",
"declaracion : IDENTIFICADOR error ';'",
"lista_variables : IDENTIFICADOR",
"lista_variables : lista_variables ',' IDENTIFICADOR",
"tipo_dato : PR_ENTERO",
"tipo_dato : PR_ENTERO_LSS",
"sent_ejecutable : sentencia_valida",
"sent_ejecutable : sent_ejecutable sentencia",
"$$1 :",
"sentencia_valida : $$1 seleccion",
"$$2 :",
"sentencia_valida : $$2 iteracion",
"$$3 :",
"sentencia_valida : $$3 impresion",
"sentencia_valida : asignacion",
"sentencia : sentencia_valida",
"sentencia : declaracion",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque",
"seleccion : seleccion_invalida",
"$$4 :",
"seleccion_invalida : PR_SI $$4 condicion ')' PR_ENTONCES bloque",
"iteracion : PR_ITERAR bloque PR_HASTA condicion ';'",
"impresion : PR_IMPRIMIR '(' CADENA_MULTILINEA ')' ';'",
"impresion : impresion_invalida",
"$$5 :",
"impresion_invalida : PR_IMPRIMIR $$5 CADENA_MULTILINEA error ';'",
"$$6 :",
"impresion_invalida : PR_IMPRIMIR '(' ')' $$6 ';'",
"$$7 :",
"impresion_invalida : PR_IMPRIMIR '(' CADENA_MULTILINEA $$7 ';'",
"impresion_invalida : PR_IMPRIMIR error ';'",
"impresion_invalida : PR_IMPRIMIR ';'",
"$$8 :",
"asignacion : asignable $$8 ASIGNACION e ';'",
"bloque : sentencia",
"bloque : '{' sent_ejecutable '}'",
"$$9 :",
"bloque : '{' declaracion $$9 sent_declarativa sent_ejecutable '}'",
"bloque : '{' '}'",
"condicion : e comparador e",
"comparador : '>'",
"comparador : COMP_MAYOR_IGUAL",
"comparador : '<'",
"comparador : COMP_MENOR_IGUAL",
"comparador : '='",
"comparador : COMP_DISTINTO",
"e : e '+' t",
"e : e '-' t",
"e : t",
"t : t '*' f",
"t : t '/' f",
"t : f",
"f : valor",
"f : '-' ENTERO",
"f : '-' ENTERO_LSS",
"f : FUERA_RANGO",
"valor : asignable",
"valor : ENTERO",
"valor : ENTERO_LSS",
"asignable : IDENTIFICADOR",
"asignable : IDENTIFICADOR '[' e ']'",
};

//#line 133 "gramatica.y"

/* Código */

private Proyecto proyecto;
private int errores = 0;

private void yyerror(String string) {
	System.out.println(string);	
}

private int yylex(){
	int salida = AnalizadorLexico.yylex();
	if (AnalizadorLexico.yylval != null){
		yylval = AnalizadorLexico.yylval;
		//AnalizadorLexico.yylval = null;
	}else{
		yylval = new ParserVal(); 
	}
	return salida;
}

private int lineaActual(){
	return proyecto.getLineaActual();
}

private void escribirError(String s){
	errores++;
	ConsolaManager.getInstance().escribirError("Sintáctico [Línea " + lineaActual() + "] "+ s);
}

private void indicarSentencia(String s){
	proyecto.addSentenciaToList("Línea " +lineaActual()+ " -> " + s + ".");
}

public Parser(Proyecto p){
	this.proyecto = p;
}

private void chequearNegativo(){
	//Chequear valor del negativo
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if ( t.getContador() == 1){
		//Se pisa el token existente
		Token tnuevo = new Token(Token.TipoToken.ENTERO,"-"+t.getLexema());
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) )
			t.setLexema("-"+t.getLexema());
		else{
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
			proyecto.getTablaDeSimbolos().remove(t.getLexema());
		}
	}else{
		//Se crea otro token nuevo.
		t.disminuirContador();
		
		Token tnuevo = new Token(Token.TipoToken.ENTERO,"-"+t.getLexema());
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) )
			proyecto.getTablaDeSimbolos().add(tnuevo);
		else
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
	}
	//Se actualiza la tabla de simbolos visualmente.
	actualizarTablaDeSimbolos();
}

public void chequearRango(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if (Integer.parseInt(t.getLexema()) == 32768){
		t.setTipo(Token.TipoToken.ENTERO_LSS);
	}
	actualizarTablaDeSimbolos();
}

public void actualizarTablaDeSimbolos(){
	proyecto.getTablaDeSimbolos().setearCambios();
	proyecto.getTablaDeSimbolos().notifyObservers();
	for (Token tok : proyecto.getTablaDeSimbolos().getList()){
		proyecto.getTablaDeSimbolos().setearCambios();
		proyecto.getTablaDeSimbolos().notifyObservers(tok);
	}
}

public int getCantidadErrores(){
	return errores;
}
//#line 438 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 4:
//#line 29 "gramatica.y"
{ indicarSentencia("Declaración de tipo básico"); }
break;
case 5:
//#line 30 "gramatica.y"
{ indicarSentencia("Declaración de tipo vector"); }
break;
case 6:
//#line 31 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo básico incorrecta."); }
break;
case 7:
//#line 32 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
break;
case 14:
//#line 48 "gramatica.y"
{ indicarSentencia("Selección");}
break;
case 16:
//#line 49 "gramatica.y"
{ indicarSentencia("Iteración");}
break;
case 18:
//#line 50 "gramatica.y"
{ indicarSentencia("Impresión");}
break;
case 22:
//#line 55 "gramatica.y"
{ escribirError("No se pueden declarar variables luego de alguna sentencia ejecutable."); }
break;
case 26:
//#line 63 "gramatica.y"
{escribirError("Falta '(' en sentencia si.");}
break;
case 31:
//#line 79 "gramatica.y"
{escribirError("Falta '('.");}
break;
case 33:
//#line 80 "gramatica.y"
{escribirError("No se especificó una cadena multilínea.");}
break;
case 35:
//#line 81 "gramatica.y"
{escribirError("Falta ')'.");}
break;
case 37:
//#line 82 "gramatica.y"
{escribirError("Impresión Inválida cerca del ';'.");}
break;
case 38:
//#line 83 "gramatica.y"
{escribirError("Falta \"('Cadena_Multilinea')\" cerca del ';'.");}
break;
case 39:
//#line 86 "gramatica.y"
{ indicarSentencia("Asignación");}
break;
case 43:
//#line 91 "gramatica.y"
{ escribirError("No se pueden declarar variables dentro de un bloque."); }
break;
case 45:
//#line 92 "gramatica.y"
{ escribirError("Bloque de sentencias vacío."); }
break;
case 60:
//#line 118 "gramatica.y"
{ chequearNegativo(); }
break;
case 61:
//#line 119 "gramatica.y"
{ escribirError("Operación unaria no admitida.");}
break;
case 62:
//#line 120 "gramatica.y"
{escribirError("Constante fuera de rango");}
break;
case 64:
//#line 124 "gramatica.y"
{ chequearRango(); }
break;
//#line 671 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
