# Python code to
# demonstrate readlines()
# Using readlines()
file1 = open('cp.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
#centipoise
# Cp liq =A+B*T+C*(T^2)+D*(T^3)	( J/(molK))
# A,B,C,D,Tmin,Tmax,T,cp @T
for i in Lines:
	x=i.split(" ")
	print(x)
	a=1+len(x)-11+1
	x[1 : a]=[''.join(x[1 : a])]
	x[-1]=x[-1].split('\n')[0]



	print(x)

