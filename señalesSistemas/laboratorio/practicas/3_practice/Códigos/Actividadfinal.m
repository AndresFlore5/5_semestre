
num = [1];
den = [1 3 0];

hc = tf(num, den);
hz = c2d(hc,1,"zoh");

%%funcion de transferencia de sistema retroalimentado
k = 1;
num2 = [k];
den2 = [1 3 k];

hc2 = tf(num2, den2);
hz2 = c2d(hc2,.001,"zoh");

step(hz);
figure
step(hz2);