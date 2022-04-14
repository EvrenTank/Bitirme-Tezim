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
    #print(x)
    a=1+len(x)-11+1
    x[1 : a]=[''.join(x[1 : a])]
    x[-1]=x[-1].split('\n')[0]
    name,formula,A,B,C,D,Tmin,Tmax,T,cp_at_T=x[1],x[2],x[3],x[4],x[5],x[6],x[7],x[8],x[9],x[10]
    name=name.replace(',','')
    name=name.replace('-','')
    print(' double cp_{}_{} []={{ {},{},{},{},{},{},{},{} }};'.format(name,formula,A,B,C,D,Tmin,Tmax,T,cp_at_T))



