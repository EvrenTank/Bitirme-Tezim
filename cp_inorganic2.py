# Python code to
# demonstrate readlines()
# Using readlines()
file1 = open('cp_inorganic.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
#centipoise
# Cp liq =A+B*T+C*(T^2)+D*(T^3)	( J/(molK))
# A,B,C,D,Tmin,Tmax
for i in Lines:
	x=i.split(" ")
	a=2+len(x)-9+1
	x[2 : a]=[''.join(x[2 : a])]
	x[-1]=x[-1].split('\n')[0]
	formula, name, A, B, C, D, Tmin, Tmax = x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8]
	name = name.replace(',', '')
	name = name.replace('-', '')
	name = name.lower()
	print(' double cp_{}_{} []={{ {},{},{},{},{},{} }};'.format(name,formula,A,B,C,D,Tmin,Tmax))

