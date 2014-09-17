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
    0,    1,    1,    1,    1,    4,    4,    3,    3,    2,
    2,    5,    5,    5,    5,    6,    6,    6,    6,    6,
    6,    7,    8,    9,   11,   11,   14,   14,   10,   10,
   15,   15,   15,   15,   15,   15,   15,   13,   13,   13,
   13,   16,   16,   16,   17,   17,   17,   12,   12,   12,
};
final static short yylen[] = {                            2,
    2,    3,   10,    4,   11,    1,    3,    1,    1,    1,
    2,    1,    2,    2,    2,    6,    8,    5,    5,    6,
    2,    4,    4,    3,    3,    1,    1,    2,    3,    2,
    1,    1,    1,    1,    1,    1,    2,    3,    3,    1,
    2,    3,    3,    1,    1,    1,    1,    1,    4,    2,
};
final static short yydefred[] = {                         0,
    0,    8,    9,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   10,   12,    0,    0,    0,    0,
    6,    0,    0,    0,    0,    0,    0,   46,   47,    0,
    0,    0,   45,    0,    0,   44,    0,    0,   26,    0,
   11,    0,   13,   14,   15,    0,    2,    0,    0,    0,
    0,    0,    0,    0,   41,    0,    0,    0,    0,   32,
   34,   36,    0,    0,   31,   33,   35,    0,    0,    0,
    0,   27,    0,    0,    4,    0,    7,    0,   50,    0,
   49,    0,    0,    0,    0,   37,    0,    0,    0,   42,
   43,   23,   25,   28,   22,    0,    0,   19,    0,    0,
   18,    0,    0,   20,    0,    0,    0,    0,    0,    0,
   17,    3,    0,    5,
};
final static short yydgoto[] = {                          4,
    5,   13,    6,   22,   39,   16,   17,   18,   19,   32,
   40,   33,   34,   73,   68,   35,   36,
};
final static short yysindex[] = {                      -205,
  -77,    0,    0,    0, -134, -221, -219,   -1,  -23,  -29,
   30, -116, -118, -221,    0,    0,    7,   13,   24, -184,
    0,  -25, -164,    0,  -10,   53,   22,    0,    0,   44,
   37,   75,    0,   34,   51,    0, -145, -118,    0, -150,
    0,   -3,    0,    0,    0,   44,    0, -136, -133,   65,
 -146,    8,    0,   44,    0, -122,  -36, -120,   78,    0,
    0,    0, -103, -103,    0,    0,    0,   44, -103, -103,
  103,    0,  -98,   52,    0,   19,    0,   54,    0, -108,
    0,   98,  -96, -101,  105,    0,   51,   51,   19,    0,
    0,    0,    0,    0,    0, -107,   72,    0,  108, -116,
    0, -102,  -99,    0,  -92, -163,  -97, -116,  113, -163,
    0,    0,  115,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,  -94,    0,
    0,    0,  175,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,    0,    0,  -39,    0,    0,    0,
    0,    0,    0,    0,  -12,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -30,    0,  -17,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   -1,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   14,   26,  -41,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   10,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    4,  162,   27,    0,    0,    0,    0,    6,
  -62,   79,   74,    0,    0,   42,   39,
};
final static int YYTABLESIZE=311;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   21,   48,   48,   48,   84,   48,   38,   48,   14,   16,
   31,   46,   46,    7,   46,   30,   46,   29,   48,   48,
   48,   48,   48,   30,   50,   50,   93,   50,   40,   50,
   40,   15,   40,   47,   30,   21,   57,  105,   23,   41,
   48,   30,   50,   50,   50,  111,   40,   40,   40,   40,
   64,    1,   63,   48,   39,   75,   39,   24,   39,    2,
    3,   64,   46,   63,   72,   43,   38,   25,   38,   37,
   38,   44,   39,   39,   39,   39,   64,   56,   63,   95,
   40,   30,   45,   20,   38,   38,   38,   38,   30,   46,
   20,   20,   69,   66,   67,   65,   30,   70,   52,   94,
   81,    2,    3,   55,   87,   88,   39,   90,   91,  109,
   49,   53,   54,  113,   71,   58,   20,   74,   38,   76,
   77,    8,    9,   79,   78,   21,   10,   52,   80,   11,
    2,    3,   12,   82,   16,   85,   86,    8,   27,    8,
   27,   89,   10,   92,   10,   11,   96,   11,   12,   97,
   12,   20,   50,   27,   28,   29,   98,    8,   27,   99,
  100,  102,   10,  101,  103,   11,  104,  106,   12,  107,
  108,  112,  110,  114,    1,   42,    0,    0,   20,   48,
    0,    0,    0,    0,    0,    0,   20,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   48,    0,    0,    0,
   29,    0,   48,    0,    0,   83,   26,   27,   28,   29,
    0,   48,   48,   48,   48,    0,    0,    0,   50,    0,
    0,    0,    0,   40,   30,   50,   27,   51,   29,   40,
    0,    0,    0,   50,   50,   50,   21,   21,   40,   40,
   40,   21,    0,   21,   21,   16,   16,   21,   21,   39,
   16,    0,    0,   16,   50,   39,   16,   16,    0,    0,
    0,   38,    0,    0,   39,   39,   39,   38,    0,   59,
    0,    0,   26,   27,   28,   29,   38,   38,   38,   50,
   27,   28,   29,    0,   60,   61,   62,   26,   27,   28,
   29,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,   41,   42,   43,   41,   45,  123,   47,    5,    0,
   40,   42,   43,   91,   45,   45,   47,   59,   44,   59,
   60,   61,   62,   41,   42,   43,  125,   45,   41,   47,
   43,    5,   45,   59,   45,  257,   31,  100,  258,   13,
   44,   59,   60,   61,   62,  108,   59,   60,   61,   62,
   43,  257,   45,   93,   41,   59,   43,   59,   45,  265,
  266,   43,   93,   45,   38,   59,   41,   91,   43,   40,
   45,   59,   59,   60,   61,   62,   43,   41,   45,   74,
   93,   45,   59,    5,   59,   60,   61,   62,   45,  274,
   12,   13,   42,   60,   61,   62,   45,   47,   25,   73,
   93,  265,  266,   30,   63,   64,   93,   69,   70,  106,
  275,   59,   91,  110,  260,   41,   38,  268,   93,   46,
  257,  256,  257,   59,  258,  125,  261,   54,  275,  264,
  265,  266,  267,  256,  125,  256,   59,  256,  257,  256,
  257,   68,  261,   41,  261,  264,   93,  264,  267,  258,
  267,   73,  256,  257,  258,  259,   59,  256,  257,  256,
  262,  269,  261,   59,   93,  264,   59,  270,  267,  269,
  263,   59,  270,   59,    0,   14,   -1,   -1,  100,  274,
   -1,   -1,   -1,   -1,   -1,   -1,  108,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  256,   -1,   -1,   -1,
  262,   -1,  262,   -1,   -1,  262,  256,  257,  258,  259,
   -1,  271,  272,  273,  274,   -1,   -1,   -1,  256,   -1,
   -1,   -1,   -1,  256,  262,  256,  257,  258,  259,  262,
   -1,   -1,   -1,  271,  272,  273,  256,  257,  271,  272,
  273,  261,   -1,  263,  264,  256,  257,  267,  268,  256,
  261,   -1,   -1,  264,  274,  262,  267,  268,   -1,   -1,
   -1,  256,   -1,   -1,  271,  272,  273,  262,   -1,  256,
   -1,   -1,  256,  257,  258,  259,  271,  272,  273,  256,
  257,  258,  259,   -1,  271,  272,  273,  256,  257,  258,
  259,
};
}
final static short YYFINAL=4;
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
"programa : sent_decl sent_ejec",
"sent_decl : tipo_dato lista_variables ';'",
"sent_decl : IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';'",
"sent_decl : sent_decl tipo_dato lista_variables ';'",
"sent_decl : sent_decl IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';'",
"lista_variables : IDENTIFICADOR",
"lista_variables : lista_variables ',' IDENTIFICADOR",
"tipo_dato : PR_ENTERO",
"tipo_dato : PR_ENTERO_LSS",
"sent_ejec : sentencia",
"sent_ejec : sent_ejec sentencia",
"sentencia : seleccion",
"sentencia : iteracion ';'",
"sentencia : impresion ';'",
"sentencia : asignacion ';'",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque",
"seleccion : PR_SI condicion ')' error ';'",
"seleccion : PR_SI '(' ')' error ';'",
"seleccion : PR_SI '(' condicion PR_ENTONCES error ';'",
"seleccion : error ';'",
"iteracion : PR_ITERAR bloque PR_HASTA condicion",
"impresion : PR_IMPRIMIR '(' CADENA_MULTILINEA ')'",
"asignacion : asignable ASIGNACION e",
"bloque : '{' lista_sentencias '}'",
"bloque : sentencia",
"lista_sentencias : sentencia",
"lista_sentencias : lista_sentencias sentencia",
"condicion : e comparador e",
"condicion : error ';'",
"comparador : '>'",
"comparador : COMP_MAYOR_IGUAL",
"comparador : '<'",
"comparador : COMP_MENOR_IGUAL",
"comparador : '='",
"comparador : COMP_DISTINTO",
"comparador : error ';'",
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
"asignable : error ';'",
};

//#line 97 "gramatica2.y"

private Proyecto proyecto;

private void yyerror(String string) {
	System.out.println(string);	
}

private int yylex(){
	return AnalizadorLexico.yylex();
}

private int lineaActual(){
	return Proyecto.getLineaActual();
}

private void escribirError(String s){
	ConsolaManager.getInstance().escribirError("Sintáctico [Línea " + lineaActual() + "] "+ s);
}

private void indicarSentencia(String s){
	proyecto.addSentenciaToList("Línea " +lineaActual()+ " -> " + s + ".");
}
//#line 348 "Parser.java"
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
case 2:
//#line 13 "gramatica2.y"
{ indicarSentencia("Declaración de tipo básico");}
break;
case 3:
//#line 14 "gramatica2.y"
{ indicarSentencia("Declaración de tipo vector");}
break;
case 4:
//#line 15 "gramatica2.y"
{ indicarSentencia("Declaración de lista de tipo básico");}
break;
case 5:
//#line 16 "gramatica2.y"
{ indicarSentencia("Declaración de lista de tipo vector");}
break;
case 12:
//#line 31 "gramatica2.y"
{ indicarSentencia("Selección");}
break;
case 13:
//#line 32 "gramatica2.y"
{ indicarSentencia("Iteración");}
break;
case 14:
//#line 33 "gramatica2.y"
{ indicarSentencia("Impresión");}
break;
case 15:
//#line 34 "gramatica2.y"
{ indicarSentencia("Asignación");}
break;
case 18:
//#line 39 "gramatica2.y"
{escribirError("Falta '(' en sentencia si.");}
break;
case 19:
//#line 40 "gramatica2.y"
{escribirError("Falta condición en sentencia si.");}
break;
case 20:
//#line 41 "gramatica2.y"
{escribirError("Falta ')' en sentencia si.");}
break;
case 21:
//#line 42 "gramatica2.y"
{escribirError("Sentencia si mal escrita.");}
break;
case 30:
//#line 63 "gramatica2.y"
{escribirError("Condición inválida.");}
break;
case 37:
//#line 72 "gramatica2.y"
{escribirError("Comparador inválido.");}
break;
case 38:
//#line 75 "gramatica2.y"
{ yyval.ival = val_peek(2).ival + val_peek(0).ival; }
break;
case 39:
//#line 76 "gramatica2.y"
{ yyval.ival = val_peek(2).ival - val_peek(0).ival; }
break;
case 40:
//#line 77 "gramatica2.y"
{ yyval.ival = val_peek(0).ival; }
break;
case 41:
//#line 78 "gramatica2.y"
{ yyval.ival = -val_peek(1).ival; }
break;
case 42:
//#line 81 "gramatica2.y"
{ yyval.ival = val_peek(2).ival * val_peek(0).ival; }
break;
case 43:
//#line 82 "gramatica2.y"
{ yyval.ival = val_peek(2).ival / val_peek(0).ival; }
break;
case 44:
//#line 83 "gramatica2.y"
{ yyval.ival = val_peek(0).ival; }
break;
case 50:
//#line 93 "gramatica2.y"
{escribirError("Asignación inválida.");}
break;
//#line 585 "Parser.java"
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
