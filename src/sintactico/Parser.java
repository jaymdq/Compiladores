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
    0,    0,    1,    1,    3,    3,    3,    6,    7,    6,
    6,    5,    5,    4,    4,    2,    2,   11,    8,   13,
    8,   15,    8,    8,    8,    9,    9,   10,   10,   10,
   20,   19,   21,   19,   19,   19,   19,   19,   12,   12,
   23,   22,   22,   22,   14,   14,   25,   24,   26,   24,
   27,   24,   24,   24,   16,   16,   30,   30,   18,   18,
   31,   18,   18,   17,   32,   32,   32,   32,   32,   32,
   29,   29,   29,   33,   33,   33,   34,   34,   34,   34,
   35,   35,   35,   28,   28,
};
final static short yylen[] = {                            2,
    2,    2,    2,    0,    3,   10,    1,    3,    0,    9,
    8,    1,    3,    1,    1,    1,    2,    0,    2,    0,
    2,    0,    2,    1,    2,    1,    1,    6,    8,    1,
    0,    6,    0,    6,    6,    8,    6,    3,    5,    1,
    0,    5,    4,    5,    5,    1,    0,    5,    0,    5,
    0,    5,    5,    2,    4,    1,    4,    3,    1,    3,
    0,    6,    2,    3,    1,    1,    1,    1,    1,    1,
    3,    3,    1,    3,    3,    1,    1,    2,    2,    1,
    1,    1,    1,    1,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    2,    0,    0,   14,   15,    0,    0,
    3,    0,    7,   16,    0,    0,    0,   24,    0,   56,
   25,    0,    0,   27,   26,   17,    0,   12,    0,    0,
   19,   30,    0,   21,   40,    0,   23,   46,    0,    0,
    0,    0,   83,   80,    0,   81,    0,    0,   76,   77,
   58,    8,    5,    0,    0,    0,    0,    0,   59,    0,
    0,   54,    0,    0,    0,   82,    0,    9,    0,    0,
   78,   79,   85,    0,    0,    0,    0,   13,   38,    0,
    0,    0,    0,   63,    0,   61,    0,    0,    0,    0,
    0,   49,    0,   57,   55,    0,    0,    0,    0,   74,
   75,    0,    0,    0,   66,   68,   70,   65,   67,   69,
    0,    0,   60,    4,    0,    0,   43,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   44,   39,   42,   53,   45,   52,   50,   48,
    0,    0,    0,    0,   37,    0,   34,   32,    0,    0,
   11,    0,    0,    0,   62,   10,    0,   36,   29,    6,
};
final static short yydgoto[] = {                          2,
    3,   10,   24,   12,   29,   13,   96,   25,   59,   31,
   15,   34,   16,   37,   17,   18,   81,   60,   32,   57,
  104,   35,   61,   38,   64,  122,  121,   19,   82,   20,
  114,  111,   48,   49,   50,
};
final static short yysindex[] = {                      -247,
  -43,    0,  -86,    0,  -18,  -46,    0,    0, -190,  -86,
    0, -162,    0,    0, -187, -180, -148,    0, -172,    0,
    0,  -42,   66,    0,    0,    0,   73,    0,   17,   -9,
    0,    0, -118,    0,    0,  -11,    0,    0,  -34,   44,
   50, -133,    0,    0, -158,    0,   11,   21,    0,    0,
    0,    0,    0, -114, -118,  -13,   51, -112,    0,   48,
 -119,    0,  -27, -110,   92,    0,   19,    0,   51, -106,
    0,    0,    0,   51,   51,   51,   51,    0,    0,  114,
  116,   60,  117,    0,  -97,    0,   -6,  104,   51,  123,
  126,    0,  -83,    0,    0,  -93,   85,   21,   21,    0,
    0,  -81, -207,  -78,    0,    0,    0,    0,    0,    0,
   51,  -77,    0,    0,  128,  134,    0,  135,  136,  137,
  138,  139,  141,  -69, -212, -118, -118, -118, -118,   36,
 -118,  -86,    0,    0,    0,    0,    0,    0,    0,    0,
 -152,  143,  -67,  -59,    0,  -58,    0,    0,  -91,  147,
    0, -152, -118, -118,    0,    0,  148,    0,    0,    0,
};
final static short yyrindex[] = {                       -75,
    0,    0, -149,    0,    0,  -66,    0,    0,    0,   23,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   54,
    0,    0, -137,    0,    0,  -51,    0,    0,    0,    0,
  -35,   -5,    0,    0,    0,    0,    0,  -41,    0,    0,
    0,    0,    0,    0, -149,    0,    0, -149,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -52,    0,    0,    0, -149,    0,    0,    0,    0,    0,
   66,    0,    0,    0,    0,    0,    0,   24,   30,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -149, -149, -149, -149,   -8,
 -149, -149,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,    0,   15,    0,    0, -149,    0,
    0,    0, -149, -149,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   97,  -22,   14,  -82,    0,    0,    0,   40,   12,    0,
    0,    0,    0,    0,    0,    0,   -7,  -20,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  278,    8,    0,
    0,    0,   61,   52,    0,
};
final static int YYTABLESIZE=389;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         73,
   35,   73,   45,   73,   58,   84,   84,   84,    1,   84,
   45,   84,   84,   92,   28,    4,   11,   73,   73,   73,
   73,   26,    1,   84,   84,   84,   84,  113,   63,   47,
   56,   45,   64,  155,   79,   85,   82,   82,   45,   82,
   21,   82,   14,  142,   22,   35,   67,   62,  127,   83,
   64,   73,   88,   74,  128,   75,  143,   84,  150,   28,
   54,   74,   76,   75,   71,   23,   71,   77,   71,  157,
   72,   86,   72,   30,   72,   53,   47,   95,   74,  116,
   75,  118,   71,   71,   71,   71,   33,   82,   72,   72,
   72,   72,   45,   27,   28,   45,   26,   14,   31,   71,
   72,   39,   74,   73,   75,  144,  145,  146,  147,  149,
  148,   18,    7,    8,   22,   36,   71,   20,  130,  109,
  110,  108,   72,   18,   51,   35,   22,  100,  101,   20,
   41,   52,  158,  159,   98,   99,   68,    5,    6,   28,
   69,   70,   78,    5,    6,   11,    7,    8,   89,   93,
   94,   97,    7,    8,  102,    9,  103,  112,    5,    6,
   26,    9,  117,  119,    5,    6,  120,    7,    8,    5,
    6,   14,  123,    7,    8,  124,    9,  125,    7,    8,
  126,    4,    9,  129,  131,    4,  133,    9,    4,    4,
    4,    4,  134,  135,  136,  137,  138,  139,    4,  140,
  141,  151,  152,  153,  154,  156,  160,   84,   47,   33,
  132,    0,    0,   40,   41,   42,   43,    0,    0,    0,
   73,   65,   41,   66,   43,    0,   84,    0,   90,   73,
   73,   73,   91,   44,    0,   84,   84,   84,    0,    0,
    0,   44,   80,   41,   66,   43,   55,    0,    0,  115,
   41,   66,   43,   64,    0,    0,   35,   35,   35,   35,
    0,   35,   44,    0,   35,   35,   35,   35,   35,   44,
   28,   28,   28,   28,   35,   28,   35,    0,   28,   28,
   28,   28,   28,   18,    0,   71,   22,    0,   28,   20,
   28,   72,    0,    0,   71,   71,   71,    0,    0,   46,
   72,   72,   72,    0,   41,   66,   43,   41,   66,   43,
   31,   31,   31,    0,    0,   87,   46,    0,    0,    0,
    0,    0,    0,   44,    0,    0,   44,    0,    0,   31,
  105,  106,  107,   46,   46,    0,    0,   46,    0,    0,
    0,    0,    0,    0,    0,    0,   46,    0,    0,    0,
    0,   46,   46,   46,   46,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   46,    0,   46,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   46,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,   43,   45,   45,  123,   41,   42,   43,  256,   45,
   45,   47,  125,   41,    0,   59,    3,   59,   60,   61,
   62,   10,    0,   59,   60,   61,   62,  125,   40,   22,
   40,   45,   41,  125,   55,   58,   42,   43,   45,   45,
   59,   47,    3,  256,   91,   45,   39,   59,  256,   57,
   59,   93,   60,   43,  262,   45,  269,   93,  141,   45,
   44,   43,   42,   45,   41,  256,   43,   47,   45,  152,
   41,   58,   43,  261,   45,   59,   69,   59,   43,   87,
   45,   89,   59,   60,   61,   62,  267,   93,   59,   60,
   61,   62,   45,  256,  257,   45,   85,   58,   45,  258,
  259,  274,   43,   93,   45,  126,  127,  128,  129,  132,
  131,  261,  265,  266,  264,  264,   93,  267,  111,   60,
   61,   62,   93,  261,   59,  125,  264,   76,   77,  267,
  268,   59,  153,  154,   74,   75,   93,  256,  257,  125,
   91,  275,  257,  256,  257,  132,  265,  266,  268,  260,
   59,  258,  265,  266,   41,  274,   41,   41,  256,  257,
  149,  274,   59,   41,  256,  257,   41,  265,  266,  256,
  257,  132,  256,  265,  266,  269,  274,   93,  265,  266,
  262,  257,  274,  262,  262,  261,   59,  274,  264,  265,
  266,  267,   59,   59,   59,   59,   59,   59,  274,   59,
  270,   59,  270,  263,  263,   59,   59,  274,  260,  262,
  114,   -1,   -1,  256,  257,  258,  259,   -1,   -1,   -1,
  262,  256,  257,  258,  259,   -1,  262,   -1,  256,  271,
  272,  273,  260,  276,   -1,  271,  272,  273,   -1,   -1,
   -1,  276,  256,  257,  258,  259,  256,   -1,   -1,  256,
  257,  258,  259,  262,   -1,   -1,  256,  257,  258,  259,
   -1,  261,  276,   -1,  264,  265,  266,  267,  268,  276,
  256,  257,  258,  259,  274,  261,  276,   -1,  264,  265,
  266,  267,  268,  261,   -1,  262,  264,   -1,  274,  267,
  276,  262,   -1,   -1,  271,  272,  273,   -1,   -1,   22,
  271,  272,  273,   -1,  257,  258,  259,  257,  258,  259,
  257,  258,  259,   -1,   -1,  268,   39,   -1,   -1,   -1,
   -1,   -1,   -1,  276,   -1,   -1,  276,   -1,   -1,  276,
  271,  272,  273,   56,   57,   -1,   -1,   60,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   69,   -1,   -1,   -1,
   -1,   74,   75,   76,   77,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   87,   -1,   89,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  111,
};
}
final static short YYFINAL=2;
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
"programa : error ';'",
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
"sentencia_valida : error ';'",
"sentencia : sentencia_valida",
"sentencia : declaracion",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque",
"seleccion : seleccion_invalida",
"$$5 :",
"seleccion_invalida : PR_SI $$5 condicion ')' PR_ENTONCES bloque",
"$$6 :",
"seleccion_invalida : PR_SI '(' condicion $$6 PR_ENTONCES bloque",
"seleccion_invalida : PR_SI '(' error ')' PR_ENTONCES bloque",
"seleccion_invalida : PR_SI '(' error ')' PR_ENTONCES bloque PR_SINO bloque",
"seleccion_invalida : PR_SI '(' condicion ')' error bloque",
"seleccion_invalida : PR_SI error bloque",
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

//#line 148 "gramatica.y"

/* Código */

private Proyecto proyecto;
private int errores = 0;

private void yyerror(String string) {
	//System.out.println(string);	
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
//#line 495 "Parser.java"
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
case 5:
//#line 31 "gramatica.y"
{ indicarSentencia("Declaración de tipo básico"); }
break;
case 6:
//#line 32 "gramatica.y"
{ indicarSentencia("Declaración de tipo vector"); }
break;
case 8:
//#line 36 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo básico incorrecta."); }
break;
case 9:
//#line 37 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
break;
case 11:
//#line 38 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
break;
case 18:
//#line 53 "gramatica.y"
{ indicarSentencia("Selección");}
break;
case 20:
//#line 54 "gramatica.y"
{ indicarSentencia("Iteración");}
break;
case 22:
//#line 55 "gramatica.y"
{ indicarSentencia("Impresión");}
break;
case 25:
//#line 57 "gramatica.y"
{ escribirError("Sentencia inválida."); }
break;
case 27:
//#line 61 "gramatica.y"
{ escribirError("No se pueden declarar variables luego de alguna sentencia ejecutable."); }
break;
case 31:
//#line 69 "gramatica.y"
{escribirError("Falta '(' en sentencia si.");}
break;
case 33:
//#line 70 "gramatica.y"
{escribirError("Falta ')' en sentencia si.");}
break;
case 35:
//#line 71 "gramatica.y"
{escribirError("Condición inválida en la sentencia si.");}
break;
case 36:
//#line 72 "gramatica.y"
{escribirError("Condición inválida en la sentencia si.");}
break;
case 37:
//#line 73 "gramatica.y"
{escribirError("Sentencia si inválida.");}
break;
case 38:
//#line 74 "gramatica.y"
{escribirError("Sentencia si inválida.");}
break;
case 41:
//#line 81 "gramatica.y"
{ escribirError("No se especificó un bloque a iterar."); }
break;
case 43:
//#line 82 "gramatica.y"
{ escribirError("No se especificó la palabra reservada hasta en la iteración."); }
break;
case 44:
//#line 83 "gramatica.y"
{ escribirError("Condición inválida en la sentencia iterar."); }
break;
case 47:
//#line 90 "gramatica.y"
{escribirError("Falta '(' en sentencia de impresión.");}
break;
case 49:
//#line 91 "gramatica.y"
{escribirError("No se especificó una cadena multilínea en sentencia de impresión.");}
break;
case 51:
//#line 92 "gramatica.y"
{escribirError("Falta ')' en sentencia de impresión.");}
break;
case 53:
//#line 93 "gramatica.y"
{escribirError("Impresión Inválida.");}
break;
case 54:
//#line 94 "gramatica.y"
{escribirError("Falta \"('Cadena_Multilinea')\" .");}
break;
case 55:
//#line 97 "gramatica.y"
{ indicarSentencia("Asignación");}
break;
case 57:
//#line 101 "gramatica.y"
{ escribirError("Asignación inválida.");}
break;
case 58:
//#line 102 "gramatica.y"
{ escribirError("Asignación inválida.");}
break;
case 61:
//#line 107 "gramatica.y"
{ escribirError("No se pueden declarar variables dentro de un bloque."); }
break;
case 63:
//#line 108 "gramatica.y"
{ escribirError("Bloque de sentencias vacío."); }
break;
case 78:
//#line 133 "gramatica.y"
{ chequearNegativo(); }
break;
case 79:
//#line 134 "gramatica.y"
{ escribirError("Constante negativa fuera de rango.");}
break;
case 80:
//#line 135 "gramatica.y"
{escribirError("Constante fuera de rango");}
break;
case 82:
//#line 139 "gramatica.y"
{ chequearRango(); }
break;
//#line 776 "Parser.java"
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
