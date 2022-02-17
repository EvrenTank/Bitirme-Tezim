
file1 = open('critical_values_inorganic.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
# NO, formula,Name, MW,Tf, Tb, Tc, Pc, Vc, RHOC, Zc, omega
for i in Lines:
	x=i.split(" ")
	#print(x)
	a=2+len(x)-20+1
	x[2 : a]=[''.join(x[2 : a])]
	x[-1]=x[-1].split('\n')[0]

	x.pop(3)
	x.pop(5)
	x.pop(6)
	x.pop(7)
	x.pop(8)
	x.pop(9)
	x.pop(10)
	x.pop(11)
	print(x)

