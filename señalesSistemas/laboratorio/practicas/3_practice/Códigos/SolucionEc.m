%Teclee trabajo en la linea de comandos por favor%
%y podra correr directamente el trabajo%
% Copyright (c) 2010, Roberto Herrera/QUITO-ECUADOR/ EPN
% All rights reserved.
% 
% Redistribution and use in source and binary forms, with or without 
% modification, are permitted provided that the following conditions are 
% met:
% 
%     * Redistributions of source code must retain the above copyright 
%       notice, this list of conditions and the following disclaimer.
%     * Redistributions in binary form must reproduce the above copyright 
%       notice, this list of conditions and the following disclaimer in 
%       the documentation and/or other materials provided with the distribution
%     * Neither the name of the  nor the names 
%       of its contributors may be used to endorse or promote products derived 
%       from this software without specific prior written permission.
%       
% THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
% AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
% IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
% ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
% LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
% CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
% SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
% INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
% CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
% ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
% POSSIBILITY OF SUCH DAMAGE.%
clear all %limpia todo 
p=4;
while (abs(p)>=3||p==0)
    clc
    disp('')
    disp('Que tipo de ecuacion desea resolver:')
    disp('')
    disp('1. Ecuacion Diferencial')
    disp('2. Ecuacion de Diferencias')
    disp('')
    p=input('Ingresar el numero de la opcion: ')
    disp('')
    switch p
        case 1%Ecuacion Diferencial
            in=input('Ingrese los coeficientes de la salida y(t) en forma de vector: ')
            disp('')
            out=input('Ingrese los coeficientes de la entrada x(t) en forma de vector: ')
            syms  s w i t
            n1=length(in);
            n2=length(out);
            disp('Que tipo de entrada x(t) desea: ')
            disp('')
            disp('1. Funcion impulso d(t)')
            disp('2. Funcion paso u(t)')
            disp('3. Funcion rampa t*u(t)')
            disp('')
            q=input('Ingrese el numero de la opcion: ');
            switch q
                case 1
                    den=0;
                    for k=1:n1
                        den=den+in(1,k)*(i*w)^(n1-k);
                    end
                    num=0;
                    for m=1:n2
                        num=num+out(1,m)*(i*w)^(n2-m);
                    end
                    Transf=num/den;
                    x=1;
                    T_Fourier=Transf*x;
                    y=ifourier(T_Fourier,t);
                    f=tf(out,in);
                    T_Fourier;
                    Laplace=laplace(y,s);
                    E_Discreto=c2d(f,0.01,'tustin');
                case 2
                    den=0;
                    for k=1:n1
                        den=den+in(1,k)*(i*w)^(n1-k);
                    end
                    num=0;
                    for m=1:n2
                        num=num+out(1,m)*(i*w)^(n2-m);
                    end
                    Transf=num/den;
                    x=pi*sym('Dirac(w)')+1/(i*w);
                    T_Fourier=Transf*x;
                    y=ifourier(T_Fourier,t)
                    f=tf(out,in)
                    T_Fourier
                    Laplace=laplace(y,s)
                    E_Discreto=c2d(f,0.01,'tustin')
                case 3
                    den=0;
                    for k=1:n1
                        den=den+in(1,k)*(i*w)^(n1-k);
                    end
                    num=0;
                    for m=1:n2
                        num=num+out(1,m)*(i*w)^(n2-m);
                    end
                    Transfff=num/den;
                    x=i*(pi*sym('Dirac(1,w)')+i/w^2);
                    T_Fourier=Transf*x;
                    y=ifourier(T_Fourier,t)
                    f=tf(out,in)
                    T_Fourier
                    Laplace=laplace(y,s)
                    E_Discreto=c2d(f,0.01,'tustin')
            end
            %
        case 2%Ecuacion de diferencias
            disp('')
            disp('Programa para resolver Ecuaciones de Diferencias')
            disp('')
            disp('Elija el tipo de respuesta: ')
            disp('')
            disp('1. Impulso')
            disp('2. Paso')
            disp('3. Rampa')
            disp('')
            r=4;
            while r>3
            r=input('Ingrese el numero de la opcion: ')
            disp('')
            if r==1 | r==2 | r==3
                switch r
                    case 1
                        %Respuesta Impulso
                        n_max=input('Ingrese el maximo valor de la respuesta ')
                        disp('')
                        n=0:n_max;
                        x(1)=1;
                        for i=2:n_max+1
                            x(i)=0;
                        end
                        a=input('Ingrese los coeficientes de la ecuacion de diferencias ')
                        disp('')
                        b=input('Ingrese los coeficientes de la respuesta ')
                        y=filter(b,a,x)
                        subplot(1,2,1),stem(n,y,'filled')
                    case 2
                        %Respuesta Paso
                        n_max=input('Ingrese el maximo valor de la respuesta ')
                        disp('')
                        n=0:n_max;
                        x=ones(1,n_max+1)
                        a=input('Ingrese los coeficientes de la ecuacion de diferencias ')
                        disp('')
                        b=input('Ingrese los coeficientes de la respuesta ')
                        y=filter(b,a,x)
                        subplot(1,2,1),stem(n,y,'filled')
                    case 3
                        %respuesta rampa
                        pendiente=0;
                        while pendiente==0
                            pendiente=input('Ingrese la pendiente de la rampa ')
                            disp('')
                            if pendiente==0
                                disp('ERROR - LA PENDIENDE DEBE SER DIFERENTE DE CERO')
                                disp('')
                                disp('')
                            end
                        end
                        n_max=input('Ingrese el maximo valor de la respuesta ')
                        disp('')
                        n=0:n_max;
                        for i=1:n_max+1
                            x(i)=n(i).*pendiente;
                        end
                        a=input('Ingrese los coeficientes de la ecuacion de diferencias ')
                        disp('')
                        b=input('Ingrese los coeficientes de la respuesta ')
                        u=filter(b,a,x)
                        subplot(1,2,1),stem(n,y,'filled')
                end
                disp('Transffformada Z')
                TF=tf(b,a,-1)
                subplot(1,2,2),plot(fft(y,1000),'r')
                title('Transffformada de Fourier de la Respuesta')
            else
                disp('ERROR - LA OPCION NO EXISTE')
                disp('')
                disp('')
            end
            end
    end
end