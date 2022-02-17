# Python code to
# demonstrate readlines()
# Using readlines()
file1 = open('critical_values.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
# NO, formula, MW, Tb, Tc, Pc, Vc, RHOC, ZC, omega
for i in Lines:
	x=i.split(" ")
	print(x)
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

	print(x)

