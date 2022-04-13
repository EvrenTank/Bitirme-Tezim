# Python code to
# demonstrate readlines()
# Using readlines()
file1 = open('density_inorganic.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
#g/ml
# A*B^( - (1 - T/C)^n)
# A, B, C, n, Tmin, Tmax, T, density@T
for i in Lines:
	x=i.split(" ")
	print(x)
	a=2+len(x)-13+1
	x[2 : a]=[''.join(x[2 : a])]
	x[-1]=x[-1].split('\n')[0]

	x.pop(10)
	x.pop(3)

	print(x)

