
/*DECLARACIÓN DE VARIABLES*/

especial 	[#,$,&,",!]
letter 		[a-o]
number 		[0-9]*

%%
/*DEFINICIÓN DEL CÓDIGO-FLEX*/

{especial}+{letter}+{number} {
	printf("Ejemplo de salida: ");
}

%%

/*CODE PART - No necesario*/
/*de la "a" a la "o" son 15 elementos */


