# Python code to
# demonstrate readlines()
# Using readlines()
file1 = open('viscosity.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
#centipoise
# log10(μ liq) = A + B/T + C*T + D*T^2
# MW,A,B,C,D,Tmin,Tmax,T,vis@T
for i in Lines:
	x=i.split(" ")
	print(x)
	a=1+len(x)-12+1
	x[1 : a]=[''.join(x[1 : a])]
	x[-1]=x[-1].split('\n')[0]



	print(x)

