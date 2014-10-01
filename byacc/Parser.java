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
public final static short INFERIOR_A_SINO=278;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    3,    6,    7,    6,    6,
    5,    5,    4,    4,    2,    2,   11,    8,   13,    8,
   15,    8,    8,    8,    9,    9,   17,   10,   10,   10,
   21,   20,   22,   20,   12,   12,   24,   23,   23,   23,
   14,   14,   26,   25,   27,   25,   28,   25,   25,   25,
   16,   16,   31,   31,   19,   19,   32,   19,   19,   18,
   33,   33,   33,   33,   33,   33,   30,   30,   30,   34,
   34,   34,   35,   35,   35,   35,   36,   36,   36,   29,
   29,
};
final static short yylen[] = {                            2,
    2,    2,    0,    3,   10,    1,    3,    0,    9,    8,
    1,    3,    1,    1,    1,    2,    0,    2,    0,    2,
    0,    2,    1,    1,    1,    1,    2,    6,    8,    1,
    0,    6,    0,    7,    5,    1,    0,    5,    4,    5,
    5,    1,    0,    5,    0,    5,    0,    5,    5,    2,
    4,    1,    4,    3,    1,    3,    0,    6,    2,    3,
    1,    1,    1,    1,    1,    1,    3,    3,    1,    3,
    3,    1,    1,    2,    2,    1,    1,    1,    1,    1,
    4,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    0,   13,   14,    0,    0,    2,    0,
    6,   15,    0,    0,    0,   23,   24,    0,   52,   27,
    0,    0,   26,   25,   16,    0,   11,    0,    0,   18,
   30,    0,   20,   36,    0,   22,   42,    0,    0,    0,
    0,   79,   76,    0,   77,    0,    0,   72,   73,   54,
    7,    4,    0,    0,    0,    0,   55,    0,    0,   50,
    0,    0,    0,   78,    0,    8,    0,    0,   74,   75,
   81,    0,    0,    0,    0,   12,    0,    0,    0,    0,
   59,    0,   57,    0,    0,    0,    0,    0,   45,    0,
   53,   51,    0,    0,    0,    0,   70,   71,   33,    0,
   62,   64,   66,   61,   63,   65,    0,    0,   56,    3,
    0,    0,   39,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   40,   35,   38,   49,
   41,   48,   46,   44,    0,    0,    0,    0,    0,   32,
    0,    0,   10,    0,   34,    0,   58,    9,    0,   29,
    5,
};
final static short yydgoto[] = {                          1,
    2,    8,   23,   10,   28,   11,   93,   24,   57,   30,
   13,   33,   14,   36,   15,   16,   17,   78,   58,   31,
   55,  122,   34,   59,   37,   62,  118,  117,   45,   79,
   19,  110,  107,   47,   48,   49,
};
final static short yysindex[] = {                         0,
    0, -174,   -3,  -64,    0,    0, -166, -174,    0, -140,
    0,    0, -167, -160, -134,    0,    0, -149,    0,    0,
  -45,   72,    0,    0,    0,   75,    0,   16,   95,    0,
    0, -116,    0,    0,   -2,    0,    0,  -40,   43,   46,
 -137,    0,    0, -139,    0,  -19,   51,    0,    0,    0,
    0,    0, -113,  -32,   21, -114,    0,  -20, -123,    0,
   36, -107,   87,    0,    5,    0,   21, -104,    0,    0,
    0,   21,   21,   21,   21,    0,  114,  118,   18,  120,
    0, -109,    0,   -5,  103,   21,  127,  128,    0,  -85,
    0,    0,  -95,   82,   51,   51,    0,    0,    0,  -86,
    0,    0,    0,    0,    0,    0,   21,  -84,    0,    0,
  125,  126,    0,  129,  130,  131,  132,  133,  134,  -91,
 -217,  -76, -116,   61, -116, -174,    0,    0,    0,    0,
    0,    0,    0,    0, -142,  135,  -83, -116,  -67,    0,
  -93,  138,    0, -142,    0, -116,    0,    0,  140,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0, -146,    0,  -97,    0,    0,    0,    9,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   28,    0,
    0, -159,    0,    0,  -60,    0,    0,    0,    0,  -39,
    2,    0,    0,    0,    0,    0,  -31,    0,    0,    0,
    0,    0,    0,    0,    0, -146,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -146,    0,    0,    0,    0,    0,  142,    0,    0,
    0,    0,    0,    0,  -26,   10,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -146,   27, -146, -146,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -146,    1,    0,
 -146,    0,    0,    0,    0, -146,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
   92,  -38,   40,  -59,    0,    0,    0,   41,   29,    0,
    0,    0,    0,    0,    0,    0,    0,   26,  -24,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   57,   20,
    0,    0,    0,   56,   58,    0,
};
final static int YYTABLESIZE=304;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         44,
   28,   80,   80,   80,   44,   80,   56,   80,    1,   69,
   81,   69,   44,   69,   67,  109,   67,   82,   67,   80,
   80,   80,   80,   72,   44,   73,   21,   69,   69,   69,
   69,  147,   67,   67,   67,   67,   25,   61,  136,   44,
   46,    9,   12,   78,   78,   28,   78,   72,   78,   73,
   68,  137,   68,   80,   68,   20,   60,   65,   18,   53,
   72,   69,   73,   92,   18,   44,   67,   60,   68,   68,
   68,   68,   31,   71,   52,  142,   89,  105,  106,  104,
   80,    3,    4,   85,  149,   60,   46,  141,   18,   22,
    5,    6,   74,   29,   78,   83,   12,   75,  139,    7,
  140,   17,   68,   72,   21,   73,   32,   19,   37,  112,
   25,  114,   18,  145,   17,   26,   27,   21,   69,   70,
   19,  150,    5,    6,   38,   28,  124,   95,   96,   35,
   50,   97,   98,   51,   54,   66,   67,   68,   18,    3,
    4,    3,    4,   76,   86,   91,    3,    4,    5,    6,
    5,    6,   90,   94,   99,    5,    6,    7,  100,    7,
  108,  113,    3,    4,    7,    9,   12,  115,  116,   25,
  119,    5,    6,  120,  121,  123,   80,  125,  135,   18,
    7,   18,   18,  127,  128,  138,  144,  129,  130,  131,
  132,  133,  134,  143,   18,  146,  148,   18,  151,   43,
   47,  126,   18,    0,    0,    0,    0,    0,    0,    0,
   39,   40,   41,   42,    0,   63,   40,   64,   42,    0,
    0,    0,    0,   77,   40,   64,   42,    0,    0,    0,
   43,   80,   80,   80,    0,   43,   40,   64,   42,   69,
   69,   69,    0,   43,   67,   67,   67,   84,    0,    0,
  111,   40,   64,   42,    0,   43,   28,   28,   28,   28,
    0,   28,    0,    0,   28,   28,   28,   28,   28,   17,
   43,    0,   21,    0,   28,   19,   28,   40,   64,   42,
   68,   68,   68,    0,   31,   31,   31,    0,  101,  102,
  103,   87,    0,    0,    0,   88,   43,    0,    0,    0,
    0,    0,    0,   31,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         45,
    0,   41,   42,   43,   45,   45,  123,   47,    0,   41,
  125,   43,   45,   45,   41,  125,   43,   56,   45,   59,
   60,   61,   62,   43,   45,   45,   91,   59,   60,   61,
   62,  125,   59,   60,   61,   62,    8,   40,  256,   45,
   21,    2,    2,   42,   43,   45,   45,   43,   47,   45,
   41,  269,   43,   93,   45,   59,   59,   38,    2,   44,
   43,   93,   45,   59,    8,   45,   93,   41,   59,   60,
   61,   62,   45,   93,   59,  135,   41,   60,   61,   62,
   55,  256,  257,   58,  144,   59,   67,  126,   32,  256,
  265,  266,   42,  261,   93,   56,   56,   47,  123,  274,
  125,  261,   93,   43,  264,   45,  267,  267,  268,   84,
   82,   86,   56,  138,  261,  256,  257,  264,  258,  259,
  267,  146,  265,  266,  274,  125,  107,   72,   73,  264,
   59,   74,   75,   59,   40,   93,   91,  275,   82,  256,
  257,  256,  257,  257,  268,   59,  256,  257,  265,  266,
  265,  266,  260,  258,   41,  265,  266,  274,   41,  274,
   41,   59,  256,  257,  274,  126,  126,   41,   41,  141,
  256,  265,  266,  269,   93,  262,  274,  262,  270,  123,
  274,  125,  126,   59,   59,  262,  270,   59,   59,   59,
   59,   59,   59,   59,  138,  263,   59,  141,   59,  260,
   59,  110,  146,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  256,  257,  258,  259,   -1,  256,  257,  258,  259,   -1,
   -1,   -1,   -1,  256,  257,  258,  259,   -1,   -1,   -1,
  276,  271,  272,  273,   -1,  276,  257,  258,  259,  271,
  272,  273,   -1,  276,  271,  272,  273,  268,   -1,   -1,
  256,  257,  258,  259,   -1,  276,  256,  257,  258,  259,
   -1,  261,   -1,   -1,  264,  265,  266,  267,  268,  261,
  276,   -1,  264,   -1,  274,  267,  276,  257,  258,  259,
  271,  272,  273,   -1,  257,  258,  259,   -1,  271,  272,
  273,  256,   -1,   -1,   -1,  260,  276,   -1,   -1,   -1,
   -1,   -1,   -1,  276,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=278;
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
"NEG","INFERIOR_A_SINO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sent_declarativa sent_ejecutable",
"sent_declarativa : sent_declarativa declaracion",
"sent_declarativa :",
"declaracion : tipo_dato lista_variables ';'",
"declaracion : IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';'",
"declaracion : declaracion_invalida",
"declaracion_invalida : tipo_dato error ';'",
"$$1 :",
"declaracion_invalida : IDENTIFICADOR '[' error ']' $$1 PR_VECTOR PR_DE tipo_dato ';'",
"declaracion_invalida : IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' error ';'",
"lista_variables : IDENTIFICADOR",
"lista_variables : lista_variables ',' IDENTIFICADOR",
"tipo_dato : PR_ENTERO",
"tipo_dato : PR_ENTERO_LSS",
"sent_ejecutable : sentencia_valida",
"sent_ejecutable : sent_ejecutable sentencia",
"$$2 :",
"sentencia_valida : $$2 seleccion",
"$$3 :",
"sentencia_valida : $$3 iteracion",
"$$4 :",
"sentencia_valida : $$4 impresion",
"sentencia_valida : asignacion",
"sentencia_valida : sentencia_invalida",
"sentencia : sentencia_valida",
"sentencia : declaracion",
"sentencia_invalida : error ';'",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque",
"seleccion : seleccion_invalida",
"$$5 :",
"seleccion_invalida : PR_SI $$5 condicion ')' PR_ENTONCES bloque",
"$$6 :",
"seleccion_invalida : PR_SI '(' error ')' $$6 PR_ENTONCES bloque",
"iteracion : PR_ITERAR bloque PR_HASTA condicion ';'",
"iteracion : iteracion_invalida",
"$$7 :",
"iteracion_invalida : PR_ITERAR $$7 PR_HASTA condicion ';'",
"iteracion_invalida : PR_ITERAR bloque condicion ';'",
"iteracion_invalida : PR_ITERAR bloque PR_HASTA error ';'",
"impresion : PR_IMPRIMIR '(' CADENA_MULTILINEA ')' ';'",
"impresion : impresion_invalida",
"$$8 :",
"impresion_invalida : PR_IMPRIMIR $$8 CADENA_MULTILINEA error ';'",
"$$9 :",
"impresion_invalida : PR_IMPRIMIR '(' ')' $$9 ';'",
"$$10 :",
"impresion_invalida : PR_IMPRIMIR '(' CADENA_MULTILINEA $$10 ';'",
"impresion_invalida : PR_IMPRIMIR '(' error ')' ';'",
"impresion_invalida : PR_IMPRIMIR ';'",
"asignacion : asignable ASIGNACION e ';'",
"asignacion : asignacion_invalida",
"asignacion_invalida : asignable ASIGNACION error ';'",
"asignacion_invalida : ASIGNACION error ';'",
"bloque : sentencia",
"bloque : '{' sent_ejecutable '}'",
"$$11 :",
"bloque : '{' declaracion $$11 sent_declarativa sent_ejecutable '}'",
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

//#line 155 "gramatica.y"

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
//#line 475 "Parser.java"
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
//#line 33 "gramatica.y"
{ indicarSentencia("Declaración de tipo básico"); }
break;
case 5:
//#line 34 "gramatica.y"
{ indicarSentencia("Declaración de tipo vector"); }
break;
case 7:
//#line 38 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo básico incorrecta."); }
break;
case 8:
//#line 39 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
break;
case 10:
//#line 40 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
break;
case 17:
//#line 55 "gramatica.y"
{ indicarSentencia("Selección");}
break;
case 19:
//#line 56 "gramatica.y"
{ indicarSentencia("Iteración");}
break;
case 21:
//#line 57 "gramatica.y"
{ indicarSentencia("Impresión");}
break;
case 26:
//#line 63 "gramatica.y"
{ escribirError("No se pueden declarar variables luego de alguna sentencia ejecutable."); }
break;
case 27:
//#line 66 "gramatica.y"
{ escribirError("Sentencia inválida."); }
break;
case 31:
//#line 74 "gramatica.y"
{escribirError("Falta '(' en sentencia si.");}
break;
case 33:
//#line 75 "gramatica.y"
{escribirError("Condición inválida en la sentencia si.");}
break;
case 37:
//#line 88 "gramatica.y"
{ escribirError("No se especificó un bloque a iterar."); }
break;
case 39:
//#line 89 "gramatica.y"
{ escribirError("No se especificó la palabra reservada hasta en la iteración."); }
break;
case 40:
//#line 90 "gramatica.y"
{ escribirError("Condición inválida en la sentencia iterar."); }
break;
case 43:
//#line 97 "gramatica.y"
{escribirError("Falta '('.");}
break;
case 45:
//#line 98 "gramatica.y"
{escribirError("No se especificó una cadena multilínea.");}
break;
case 47:
//#line 99 "gramatica.y"
{escribirError("Falta ')'.");}
break;
case 49:
//#line 100 "gramatica.y"
{escribirError("Impresión Inválida.");}
break;
case 50:
//#line 101 "gramatica.y"
{escribirError("Falta \"('Cadena_Multilinea')\" .");}
break;
case 51:
//#line 104 "gramatica.y"
{ indicarSentencia("Asignación");}
break;
case 53:
//#line 108 "gramatica.y"
{ escribirError("Asignación inválida.");}
break;
case 54:
//#line 109 "gramatica.y"
{ escribirError("Asignación inválida.");}
break;
case 57:
//#line 114 "gramatica.y"
{ escribirError("No se pueden declarar variables dentro de un bloque."); }
break;
case 59:
//#line 115 "gramatica.y"
{ escribirError("Bloque de sentencias vacío."); }
break;
case 74:
//#line 140 "gramatica.y"
{ chequearNegativo(); }
break;
case 75:
//#line 141 "gramatica.y"
{ escribirError("Operación unaria no admitida con tipo entero largo sin signo.");}
break;
case 76:
//#line 142 "gramatica.y"
{escribirError("Constante fuera de rango");}
break;
case 78:
//#line 146 "gramatica.y"
{ chequearRango(); }
break;
//#line 740 "Parser.java"
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
