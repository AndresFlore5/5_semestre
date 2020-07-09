%resolución de ecuación diferencial
%x -> entrada
y= dsolve('D2y+Dy+5*y=5','y(0)=0','Dy(0)=0','x');%Metimos un escalón como entrada
pretty(y);
syms x % no olvidar 
ezplot(y,[0,10]);



