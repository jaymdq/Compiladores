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
import gui.ConsolaManager;
import lexico.AnalizadorLexico;
import proyecto.Proyecto;
import proyecto.Token;
//#line 22 "Parser.java"




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
    0,    1,    1,    3,    3,    5,    5,    4,    4,    2,
    2,    8,    6,   10,    6,   12,    6,    6,    7,    7,
    9,   11,   11,   17,   16,   18,   16,   19,   16,   16,
   16,   21,   13,   13,   15,   15,   14,   23,   23,   23,
   23,   23,   23,   22,   22,   22,   24,   24,   24,   25,
   25,   25,   25,   26,   26,   20,   20,
};
final static short yylen[] = {                            2,
    2,    2,    0,    3,   10,    1,    3,    1,    1,    1,
    2,    0,    2,    0,    2,    0,    2,    1,    6,    8,
    5,    5,    1,    0,    5,    0,    5,    0,    5,    3,
    2,    0,    5,    2,    1,    3,    3,    1,    1,    1,
    1,    1,    1,    3,    3,    1,    3,    3,    1,    1,
    1,    2,    2,    1,    1,    1,    4,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    0,    8,    9,    0,    2,    0,   10,
    0,    0,    0,   18,   32,   34,    0,    0,   11,    6,
    0,    0,   13,    0,   15,    0,   17,   23,    0,    0,
   51,    0,   54,    0,    0,   49,   50,    0,    4,    0,
    0,    0,   35,    0,    0,   31,    0,    0,    0,    0,
   52,   53,   57,    0,    0,    0,    0,   55,    7,    0,
    0,    0,    0,   30,    0,   26,    0,    0,    0,    0,
    0,   47,   48,    0,   39,   41,   43,   38,   40,   42,
    0,   36,    0,    0,    0,    0,    0,   33,    0,    0,
    0,   21,   22,   29,   27,   25,    0,    0,    0,    0,
    0,   20,    5,
};
final static short yydgoto[] = {                          1,
    2,    7,    8,    9,   21,   43,   23,   11,   25,   12,
   27,   13,   14,   60,   44,   28,   48,   86,   85,   33,
   29,   34,   81,   35,   36,   37,
};
final static short yysindex[] = {                         0,
    0, -181,  -39,  -49,    0,    0, -164,    0, -200,    0,
 -196, -194, -186,    0,    0,    0,  -43,   -9,    0,    0,
  -13,   47,    0, -111,    0,  -33,    0,    0, -180, -169,
    0, -162,    0,   -2,   17,    0,    0,  -30,    0, -148,
  -30, -164,    0, -158,   52,    0,  -41, -147,  -30, -146,
    0,    0,    0,  -30,  -30,  -30,  -30,    0,    0,   73,
  -24, -108,  -30,    0,   74,    0, -140,   15,   24,   17,
   17,    0,    0, -144,    0,    0,    0,    0,    0,    0,
  -30,    0,   60,   61,   62,   63,   64,    0, -145, -111,
   45,    0,    0,    0,    0,    0, -143, -138, -163, -111,
   70,    0,    0,
};
final static short yyrindex[] = {                         0,
    0, -178,    0, -142,    0,    0,    3,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -37,    0,    0,
    0,    0,    0, -178,    0, -130,    0,    0,    0,    5,
    0,    0,    0,    0,  -32,    0,    0,    0,    0,    0,
    0, -178,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -178,    0,    0,   72,    0,    0,    0,    0,  -27,
    8,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -178,
   13,    0,    0,    0,    0,    0,    0,    1,    0, -178,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   91,    0,   35,    0,   37,    0,    0,    0,    0,
    0,    0,    0,   76,  -19,    0,    0,    0,    0,   38,
    0,   14,    0,   50,   51,    0,
};
final static int YYTABLESIZE=281;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         66,
   19,   32,    1,   56,   56,   56,   47,   56,   46,   56,
   46,   42,   46,   44,   32,   44,   82,   44,   54,   16,
   55,   56,   56,   56,   56,   46,   46,   46,   46,   46,
   40,   44,   44,   44,   44,   79,   80,   78,   10,   15,
   54,   17,   55,   19,   15,   39,   55,   55,   45,   55,
   45,   55,   45,   37,   61,   56,   20,   54,   56,   55,
   46,   15,   68,   57,   22,   44,   45,   45,   45,   45,
   98,   37,   24,   88,    3,    4,   61,   26,   10,   15,
  102,   38,   12,    5,    6,   16,   41,   54,   14,   55,
   53,    3,   18,   49,   91,   51,   52,   55,   19,   15,
   45,    5,    6,   70,   71,   50,   72,   73,   59,   63,
   64,   69,   67,   74,   84,   87,   89,   90,   92,   93,
   94,   95,   96,   97,  100,   19,   99,   15,  103,   24,
   28,   56,   62,  101,    0,    0,    0,   15,   83,    0,
    0,    0,    0,    0,    3,   18,    0,    3,   18,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   18,   30,   31,    0,    0,   65,    0,
    0,    0,   45,    0,    0,    0,   18,   58,   31,    0,
    0,    0,    0,   56,   56,   56,   56,    0,   46,   46,
   46,    0,    0,   44,   44,   44,   75,   76,   77,    0,
    0,    0,    0,    0,    0,    0,   19,   19,    0,    0,
    0,   19,    0,   12,   19,    0,   16,   19,   19,   14,
    0,    0,    0,    0,    0,    0,    0,    0,   45,   45,
   45,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,   45,    0,   41,   42,   43,   40,   45,   41,   47,
   43,  123,   45,   41,   45,   43,  125,   45,   43,   59,
   45,   59,   60,   61,   62,   59,   59,   60,   61,   62,
   44,   59,   60,   61,   62,   60,   61,   62,    2,    2,
   43,   91,   45,    7,    7,   59,   42,   43,   41,   45,
   43,   47,   45,   41,   41,   93,  257,   43,   42,   45,
   93,   24,   49,   47,  261,   93,   59,   60,   61,   62,
   90,   59,  267,   59,  256,  257,   63,  264,   42,   42,
  100,   91,  261,  265,  266,  264,   40,   43,  267,   45,
   93,  256,  257,  274,   81,  258,  259,   93,   62,   62,
   93,  265,  266,   54,   55,  275,   56,   57,  257,  268,
   59,  258,  260,   41,   41,  256,   93,  262,   59,   59,
   59,   59,   59,  269,  263,  125,  270,   90,   59,  260,
   59,  274,   42,   99,   -1,   -1,   -1,  100,   63,   -1,
   -1,   -1,   -1,   -1,  256,  257,   -1,  256,  257,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,   -1,   -1,  260,   -1,
   -1,   -1,  256,   -1,   -1,   -1,  257,  258,  259,   -1,
   -1,   -1,   -1,  271,  272,  273,  274,   -1,  271,  272,
  273,   -1,   -1,  271,  272,  273,  271,  272,  273,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  256,  257,   -1,   -1,
   -1,  261,   -1,  261,  264,   -1,  264,  267,  268,  267,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,
  273,
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
"lista_variables : IDENTIFICADOR",
"lista_variables : lista_variables ',' IDENTIFICADOR",
"tipo_dato : PR_ENTERO",
"tipo_dato : PR_ENTERO_LSS",
"sent_ejecutable : sentencia",
"sent_ejecutable : sent_ejecutable sentencia",
"$$1 :",
"sentencia : $$1 seleccion",
"$$2 :",
"sentencia : $$2 iteracion",
"$$3 :",
"sentencia : $$3 impresion",
"sentencia : asignacion",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque",
"iteracion : PR_ITERAR bloque PR_HASTA condicion ';'",
"impresion : PR_IMPRIMIR '(' CADENA_MULTILINEA ')' ';'",
"impresion : impresion_invalida",
"$$4 :",
"impresion_invalida : PR_IMPRIMIR $$4 CADENA_MULTILINEA error ';'",
"$$5 :",
"impresion_invalida : PR_IMPRIMIR '(' ')' $$5 ';'",
"$$6 :",
"impresion_invalida : PR_IMPRIMIR '(' CADENA_MULTILINEA $$6 ';'",
"impresion_invalida : PR_IMPRIMIR error ';'",
"impresion_invalida : PR_IMPRIMIR ';'",
"$$7 :",
"asignacion : asignable $$7 ASIGNACION e ';'",
"asignacion : error ';'",
"bloque : sentencia",
"bloque : '{' sent_ejecutable '}'",
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
"f : ENTERO_LSS",
"f : '-' ENTERO",
"f : '-' ENTERO_LSS",
"valor : asignable",
"valor : ENTERO",
"asignable : IDENTIFICADOR",
"asignable : IDENTIFICADOR '[' e ']'",
};

//#line 114 "gramatica.y"

private Proyecto proyecto;

private void yyerror(String string) {
	System.out.println(string);	
}

private int yylex(){
	int salida = AnalizadorLexico.yylex();
	if (AnalizadorLexico.yylval != null){
		yylval = AnalizadorLexico.yylval;
		AnalizadorLexico.yylval = null;
	}else{
		yylval = new ParserVal(); 
	}
	return salida;
}

private int lineaActual(){
	return proyecto.getLineaActual();
}

private void escribirError(String s){
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

public int getValor(int pos){
	return Integer.parseInt(proyecto.getTablaDeSimbolos().getToken(pos).getLexema());
}

public void setValor(int pos, Integer valorNuevo){
	//
	
}
//#line 421 "Parser.java"
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
//#line 23 "gramatica.y"
{ indicarSentencia("Declaración de tipo básico"); }
break;
case 5:
//#line 24 "gramatica.y"
{ indicarSentencia("Declaración de tipo vector"); }
break;
case 12:
//#line 39 "gramatica.y"
{ indicarSentencia("Selección");}
break;
case 14:
//#line 40 "gramatica.y"
{ indicarSentencia("Iteración");}
break;
case 16:
//#line 41 "gramatica.y"
{ indicarSentencia("Impresión");}
break;
case 24:
//#line 60 "gramatica.y"
{escribirError("Falta '('.");}
break;
case 26:
//#line 61 "gramatica.y"
{escribirError("No se especificó una cadena multilínea.");}
break;
case 28:
//#line 62 "gramatica.y"
{escribirError("Falta ')'.");}
break;
case 30:
//#line 63 "gramatica.y"
{escribirError("Impresión Inválida cerca del ';'.");}
break;
case 31:
//#line 64 "gramatica.y"
{escribirError("Falta \"('Cadena_Multilinea')\" cerca del ';'.");}
break;
case 32:
//#line 67 "gramatica.y"
{ indicarSentencia("Asignación");}
break;
case 34:
//#line 68 "gramatica.y"
{ escribirError("Asignación inválida.");}
break;
case 44:
//#line 88 "gramatica.y"
{ yyval = new ParserVal(val_peek(2).ival + val_peek(0).ival); }
break;
case 45:
//#line 89 "gramatica.y"
{ yyval = new ParserVal(val_peek(2).ival - val_peek(0).ival); }
break;
case 47:
//#line 93 "gramatica.y"
{ yyval = new ParserVal(val_peek(2).ival * val_peek(0).ival); }
break;
case 48:
//#line 94 "gramatica.y"
{ yyval = new ParserVal(val_peek(2).ival / val_peek(0).ival); }
break;
case 52:
//#line 100 "gramatica.y"
{ chequearNegativo(); }
break;
case 53:
//#line 101 "gramatica.y"
{ escribirError("Negativo malo");}
break;
case 55:
//#line 106 "gramatica.y"
{ chequearRango(); yyval = new ParserVal(getValor(val_peek(0).ival)); }
break;
//#line 646 "Parser.java"
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
