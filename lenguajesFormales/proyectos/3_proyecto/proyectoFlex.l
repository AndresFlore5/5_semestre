/****   Sección de declaraciones   *****/


%{

#include <stdio.h>
#include <string.h>
//Declaracion de entidades para el codigo en C
typedef struct{
	char text[300];
	int tope;
}Nodo;
int n = 10; 
Nodo lista[10];

void Save(int, char[]);
void PrintT(char[], char[]);


%}

declarionList ({PalabraReservada}|{Identificador} | {Delimitador} | {Operadores} | {Comentario} | {NumInt} | {NumReal} | {tipovar} )

PalabraReservada (auto|break|case|char|const|continue|default|do|enum|extern|float|for|goto|if|int|long|else|return|sizeof|static|struct|double|short|signed|register|switch|typedef|union|unsigned|void|volatile|while)

Identificador [a-zA-Z]([a-zA-Z]|[0-9]|['_'])*

Delimitador ("...")|(",")|(("**")("*")*)|("[")|("]")|("{")|("}")|("(")|(")")|("#")|("=")|(";")

Operadores ("++")|("+")|("*")|("==")|("!=")|("->")|("&"{1,2})|("||")|("<=")|(">=")|("<")|(">")

Comentario ("//")|("/*")|("*/")

NumInt [0-9]+

NumReal [0-9]+"."[0-9]+



%option noyywrap


	


/**** Sección de reglas: ****/
%%

{Comentario} {Save(0,yytext);}

{PalabraReservada} {Save(1,yytext);}

{Identificador} {Save(2,yytext);}

{Delimitador} {Save(3,yytext);}

{Operadores} {Save(4,yytext);}

{NumReal} {Save(5,yytext);}

{NumInt} {Save(6,yytext);}

\n {printf("\n                               CADENA RECONOCIDA                               \n");
	printf("+----------------------+--------------------------------------------+----------+\n");
		printf("\u00A6  TOKEN               \u00A6  LEXEMA                                    \u00A6  ESTADO  \u00A6\n");
		printf("+----------------------+--------------------------------------------+----------+\n"); 
		for(int i = 0; i < 8; i++){
			switch(i){
				case 0:
					if(lista[i].tope != 0) 
						PrintT("Comentario", lista[i].text);
				break;
				case 1:
					if(lista[i].tope != 0)  
						PrintT("PalabraReservada", lista[i].text);
				break;
				case 2:
					if(lista[i].tope != 0)  
						PrintT("Identificador", lista[i].text);
				break;
				case 3:
					if(lista[i].tope != 0)  
						PrintT("Delimitador", lista[i].text);
				break;
				case 4:
					if(lista[i].tope != 0)  
						PrintT("Operadores", lista[i].text);
				break;
				case 5: 
					if(lista[i].tope != 0) 
						PrintT("NumInt", lista[i].text);
				break;
				case 6: 
					if(lista[i].tope != 0) 
						PrintT("NumReal", lista[i].text);
				break;


			}
			
		}
		printf("+----------------------+--------------------------------------------+----------+\n"); 
	
	return 1;}
. ;

%%

void Init(){
	for(int i = 0 ; i < n; i++){
		lista[i].tope = 0;
	}
}

void Save(int token, char txt[]){
	strcat(lista[token].text,txt);
	strcat(lista[token].text, " ");
	lista[token].tope ++;
}


void PrintT(char token[],char text[]){
	if(strlen(token)>18)
		printf("\u00A6  %-15.15s...  ", token);
	else
		printf("\u00A6  %-20.20s", token);
	if(strlen(text)-1>40){
		printf("\u00A6  ");
		int aux = 1;
		for(int i = 0; i < strlen(text); i++){
			if(i != 0 && i%40 == 0){
				if(aux == 1)
					printf("  \u00A6  Accept  \u00A6\n\u00A6                      \u00A6  ");
				else
					printf("  \u00A6          \u00A6\n\u00A6                      \u00A6  ");
				aux++;
			}
			printf("%c", text[i]);
		}
		for(int  i = 0; i < (aux*40) - strlen(text) ; i++){
			
			printf(" ");
		}
		printf("  \u00A6          ");
	}
	else
		printf("\u00A6  %-42.42s\u00A6  Accept  ", text);
	printf("\u00A6\n");
}

int main(){
	Init();
	while(yylex()==1);
	return 0;
}