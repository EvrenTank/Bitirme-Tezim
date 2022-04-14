# Python code to
# demonstrate readlines()
# Using readlines()
file1 = open('critical_values.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
b=0
# NO, formula,Name, MW, Tb, Tc, Pc, Vc, RHOC, ZC, omega
for i in Lines:
	x=i.split(" ")
	#print(x)
	a=3+len(x)-20+1
	x[3 : a]=[''.join(x[3 : a])]
	x.pop(1)
	x.pop(3)
	x.pop(5)
	x.pop(6)
	x.pop(7)
	x.pop(8)
	x.pop(9)
	x.pop(10)
	x.pop(11)
	formula, name, MW, Tb, Tc, Pc, Vc, RHOC, ZC, omega=x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8],x[9],x[10]
	name = name.replace(',', '')
	name = name.replace('-', '')
	name = name.lower()
	print(' double criticalvalues_{}_{} []={{ {},{},{},{},{},{},{},{} }};'.format(name,formula, MW, Tb, Tc, Pc, Vc, RHOC, ZC, omega))

