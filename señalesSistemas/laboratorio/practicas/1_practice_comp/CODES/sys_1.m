%Ejercicio 1 - Práctica 1
t = -1:.001:1;

A1 = 1;
A2 = 2;
w = 5*pi;
O = 0;

ft_1 = A1*sin(w*t + O);
ft_2 = A2*sin(w*t + O);
sumFt = ft_1+ft_2;
plot(t,ft_1,'y', t, ft_2,'g', t, sumFt, 'b' );

%Ejercicio 2 - Práctica 1
t = -1:.001:1;
A1 = 1;
A2 = 2*sqrt(2);
w = 5*pi;
O1 = 0;
O2 = pi/4;

ft_1 = A1*sin(w*t + O1);
ft_2 = A2*sin(w*t + O2);
sumFt = ft_1+ft_2;
plot(t,ft_1,'y', t, ft_2,'g', t, sumFt, 'b' );
%Al estar desfasadas teta y amplitud no son la suma-> Incluir reporte

%Ejercicio 3 - Práctica 1 (aperiódico)
t = -20:.1:20;
A1 = 3;
A2 = 2*sqrt(2);
w1 = pi;
w2 = sqrt(2);
O = 0;

%si w de la funcion suma es irracional
ft_1 = A1*sin(w1*t + O);
ft_2 = A2*sin(w2*t + O);
sumFt = ft_1+ft_2;
plot(t,ft_1,'r', t, ft_2,'g', t, sumFt, 'b' );

%Ejercicio 3 - Práctica 1 (periódica)
t = -20:.1:20;
A1 = 3;
A2 = 2*sqrt(2);
w1 = 2;
w2 = 3;
O = 0;

ft_1 = A1*sin(w1*t + O);
ft_2 = A2*sin(w2*t + O);
sumFt = ft_1+ft_2;
plot(t,ft_1,'r', t, ft_2,'g', t, sumFt, 'b' );




