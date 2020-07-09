t =0 : 50 ;
fs =1; %Frecuencia de muestreo
Ts =1/fs; %Periodo de muestreo
fx =1;
x= sin(2*pi*fx* t/fs);
plot(Ts*t,x); grid 
hold on
stem( Ts* t , x )

%%
%SEGUNDA PARTE

t = 0 : 4999 ;
fs = 4000;
w1 = 2*pi*( 261.63 );
w2 = 2*pi*( 293.70 );
w3 = 2*pi*( 329.6 );
do = sin(w1*t/fs);
re = sin(w1*t/fs);
mi = sin(w1*t/fs);
spa= zeros( 1 , 500);
notas =[ do spa re spa mi spa ] ;
sound( notas , fs );
 %HACER NOTA MUSICAL 
 
 %%
 
audioread(filename);

