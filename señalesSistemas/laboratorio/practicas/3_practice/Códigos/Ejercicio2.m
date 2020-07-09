num = [5];
den = [1 1 5];

Hc = tf(num, den);

hz = c2d(Hc,1,'zoh');

%% segunda parte 
%% z    
num2 = [5 0 0];
den2 = [7 -3 1];

hz2 = tf(num2,den2,1);

step(Hc);
hold on;
step(hz);
step(hz2);
