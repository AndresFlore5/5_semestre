clear all
clc
close all
%%%Inicio del programa
pause on

%Intervalos de tiempo para la funci�n x(t)
a=input('Primer intervalo de x(t):\n');
b=input('Segundo intervalo de x(t);\n');
tx=a:0.1:b;

%%%Funci�n x(t) dependiente de tx
x=input('�C�al es la funci�n x(t)?:\n');

%%Intervalos de tiempo para la funci�n h(t)
a1=input('Primer intervalo de h(t):\n');
b1=input('Segundo intervalo de h(t):\n');
th=a1:0.1:b1;

%%%Funci�n h(t) dependiente de th
h=input('�C�al es la funci�n h(t)?:\n');


m=length(x);
n=length(h);

%%%Reflexi�n de la funci�n h(t)
hi=fliplr(h);

%%%%%Caracterizaci�n de las funciones: Inicializaci�n de vectores
k=200;
X=[x,zeros(1,(k*2)-m)];
X=X([end-k+1:end 1:end-k]);
H=[h,zeros(1,(k*2)-n)];
H=H([end-k+1:end 1:end-k]);
Hi=[hi,zeros(1,(k*2)-n)];
xn=-k:k-1;
Y=zeros(1,(k*2));
p=zeros(1,(k*2));

for i=1:(k*2)-n
    p=X.*Hi;
    Y(i+n-1)=sum(p);
    subplot(3,1,1)
    plot(xn,X,'blue')
    ylabel('x(t)')
    title('Se�al invariante 1')
    subplot(3,1,2)
    plot(xn,Hi,'red')
    ylabel('h(t)')
    title('Se�al invariante 2')
    subplot(3,1,3)
    plot(xn,Y,'g')
    ylabel('y(t)')
    title('Convoluci�n')
    Hi=Hi([end 1:end-1]);
    pause(0.01)
       
 end
pause off