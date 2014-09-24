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



//#line 2 "gramatica2.y"
import gui.ConsolaManager;
import lexico.AnalizadorLexico;
import proyecto.Proyecto;
//#line 21 "Parser.java"




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
public final static short NEG=276;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    5,    5,    4,    4,    2,
    2,    8,    6,   10,    6,   12,    6,    6,    7,    7,
    9,   11,   13,   15,   15,   14,   14,   18,   18,   18,
   18,   18,   18,   17,   17,   17,   17,   19,   19,   19,
   20,   20,   20,   16,   16,
};
final static short yylen[] = {                            2,
    2,    2,    0,    3,   10,    1,    3,    1,    1,    1,
    2,    0,    2,    0,    3,    0,    3,    2,    6,    8,
    4,    4,    3,    1,    3,    3,    2,    1,    1,    1,
    1,    1,    1,    3,    3,    1,    2,    3,    3,    1,
    1,    1,    1,    1,    4,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    8,    9,    0,    2,    0,   10,    0,
    0,    0,    0,    0,    0,    0,   11,    6,    0,    0,
   13,    0,    0,    0,    0,   18,    0,    0,   43,    0,
   41,    0,    0,   40,    0,    4,    0,    0,    0,   24,
    0,   15,    0,   17,   42,    0,    0,   37,    0,    0,
   45,    0,    0,    7,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   38,   39,   27,    0,   29,   31,   33,
   28,   30,   32,    0,   25,   21,   22,    0,    0,    0,
    0,    0,    0,    0,    0,   20,    5,
};
final static short yydgoto[] = {                          1,
    2,    6,    7,    8,   19,   40,   21,   10,   23,   11,
   25,   12,   13,   56,   41,   31,   32,   74,   33,   34,
};
final static short yysindex[] = {                         0,
    0, -192,  -74,    0,    0, -237,    0, -226,    0, -219,
 -209, -175,   31, -183,  -38,    6,    0,    0,  -18,   59,
    0, -121,   43,   60,   44,    0,  -33, -171,    0,  -33,
    0,   12,   40,    0,  -33,    0, -151,  -45, -237,    0,
 -160,    0, -150,    0,    0,   19, -149,    0, -173, -173,
    0, -173, -173,    0,   52,   71,  -24, -110,  -45,   72,
   21,   40,   40,    0,    0,    0, -147,    0,    0,    0,
    0,    0,    0,  -33,    0,    0,    0, -152, -121,   19,
 -148, -145, -220, -121,   61,    0,    0,
};
final static short yyrindex[] = {                         0,
    0, -186, -155,    0,    0,    3,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -37,    0,    0,    0,    0,
    0, -186,    0,    0,    0,    0,    0,    5,    0,    0,
    0,    0,  -32,    0,    0,    0,    0,    0, -186,    0,
    0,    0,    0,    0,    0,   64,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -186,    0,    0,
    0,  -27,    8,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -186,   13,
    0,    1,    0, -186,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   85,    0,   42,    0,   38,    0,    0,    0,    0,
    0,    0,    0,   68,    4,   37,   33,    0,   30,   41,
};
final static int YYTABLESIZE=281;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         30,
   19,   39,    1,   44,   44,   44,   30,   44,   36,   44,
   36,   30,   36,   35,   75,   35,   15,   35,   50,   16,
   49,   44,   44,   44,   44,   37,   36,   36,   36,   36,
   18,   35,   35,   35,   35,   72,   73,   71,   14,    9,
   36,   20,   14,   17,    4,    5,   42,   42,   34,   42,
   34,   42,   34,   26,   50,   44,   49,   22,   14,   46,
   36,   50,   48,   49,    3,   35,   34,   34,   34,   34,
   57,   26,    4,    5,   12,   14,    9,   16,   62,   63,
   14,   52,   82,   16,   45,   29,   53,   86,   24,   26,
   27,   57,   64,   65,   14,   17,   35,   42,   38,   43,
   34,   42,   44,   47,   51,   54,   80,   59,   61,   60,
   66,   67,   77,   78,   79,   14,   81,   84,   44,   87,
   14,   83,   23,   58,   85,   19,   76,    0,    0,    0,
    0,    0,    0,    0,    0,   16,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   16,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   55,   16,   45,   29,    0,    0,    0,    0,   16,   28,
   29,    0,    0,   16,   45,   29,    0,    0,    0,    0,
    0,    0,    0,   44,   44,   44,   44,    0,   36,   36,
   36,    0,    0,   35,   35,   35,   68,   69,   70,    0,
    0,    0,    0,    0,    0,    0,    0,   19,    0,    0,
    0,   19,    0,   12,   19,    0,   16,   19,   19,   14,
    0,    0,    0,    0,    0,    0,    0,    0,   34,   34,
   34,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         45,
    0,  123,    0,   41,   42,   43,   45,   45,   41,   47,
   43,   45,   45,   41,  125,   43,   91,   45,   43,  257,
   45,   59,   60,   61,   62,   44,   59,   60,   61,   62,
  257,   59,   60,   61,   62,   60,   61,   62,    2,    2,
   59,  261,    6,    6,  265,  266,   42,   43,   41,   45,
   43,   47,   45,   41,   43,   93,   45,  267,   22,   27,
   93,   43,   30,   45,  257,   93,   59,   60,   61,   62,
   38,   59,  265,  266,  261,   39,   39,  264,   49,   50,
  267,   42,   79,  257,  258,  259,   47,   84,  264,   59,
  274,   59,   52,   53,   58,   58,   91,   93,   40,   40,
   93,   59,   59,  275,   93,  257,   74,  268,  258,  260,
   59,   41,   41,   93,  262,   79,  269,  263,  274,   59,
   84,  270,   59,   39,   83,  125,   59,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  256,  257,  258,  259,   -1,   -1,   -1,   -1,  257,  258,
  259,   -1,   -1,  257,  258,  259,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  271,  272,  273,  274,   -1,  271,  272,
  273,   -1,   -1,  271,  272,  273,  271,  272,  273,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,
   -1,  261,   -1,  261,  264,   -1,  264,  267,  268,  267,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,
  273,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=276;
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
"COMP_MENOR_IGUAL","COMP_DISTINTO","ASIGNACION","PUNTO_PUNTO","NEG",
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
"sentencia : $$2 iteracion ';'",
"$$3 :",
"sentencia : $$3 impresion ';'",
"sentencia : asignacion ';'",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque",
"iteracion : PR_ITERAR bloque PR_HASTA condicion",
"impresion : PR_IMPRIMIR '(' CADENA_MULTILINEA ')'",
"asignacion : asignable ASIGNACION e",
"bloque : sentencia",
"bloque : '{' sent_ejecutable '}'",
"condicion : e comparador e",
"condicion : error ';'",
"comparador : '>'",
"comparador : COMP_MAYOR_IGUAL",
"comparador : '<'",
"comparador : COMP_MENOR_IGUAL",
"comparador : '='",
"comparador : COMP_DISTINTO",
"e : e '+' t",
"e : e '-' t",
"e : t",
"e : '-' e",
"t : t '*' f",
"t : t '/' f",
"t : f",
"f : asignable",
"f : ENTERO",
"f : ENTERO_LSS",
"asignable : IDENTIFICADOR",
"asignable : IDENTIFICADOR '[' e ']'",
};

//#line 99 "gramatica2.y"

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

private boolean chequearNegativo(Integer valor){
	//Chequear valor del negativo
	return false;
}

public Parser(Proyecto p){
	this.proyecto = p;
}
//#line 349 "Parser.java"
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
//#line 23 "gramatica2.y"
{ indicarSentencia("Declaración de tipo básico"); }
break;
case 5:
//#line 24 "gramatica2.y"
{ indicarSentencia("Declaración de tipo vector"); }
break;
case 12:
//#line 39 "gramatica2.y"
{ indicarSentencia("Selección");}
break;
case 14:
//#line 40 "gramatica2.y"
{ indicarSentencia("Iteración");}
break;
case 16:
//#line 41 "gramatica2.y"
{ indicarSentencia("Impresión");}
break;
case 18:
//#line 42 "gramatica2.y"
{ indicarSentencia("Asignación");}
break;
case 27:
//#line 67 "gramatica2.y"
{escribirError("Condición inválida.");}
break;
case 34:
//#line 78 "gramatica2.y"
{ yyval.ival = val_peek(2).ival + val_peek(0).ival; }
break;
case 35:
//#line 79 "gramatica2.y"
{ yyval.ival = val_peek(2).ival - val_peek(0).ival; }
break;
case 36:
//#line 80 "gramatica2.y"
{ yyval.ival = val_peek(0).ival; }
break;
case 37:
//#line 81 "gramatica2.y"
{ chequearNegativo(val_peek(0).ival); yyval.ival = -val_peek(0).ival; }
break;
case 38:
//#line 84 "gramatica2.y"
{ yyval.ival = val_peek(2).ival * val_peek(0).ival; }
break;
case 39:
//#line 85 "gramatica2.y"
{ yyval.ival = val_peek(2).ival / val_peek(0).ival; }
break;
case 40:
//#line 86 "gramatica2.y"
{ yyval.ival = val_peek(0).ival; }
break;
//#line 554 "Parser.java"
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
