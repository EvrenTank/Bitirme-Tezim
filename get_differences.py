# Python code to
# demonstrate readlines()
# Using readlines()
file1 = open('critical_values.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
b=0
names1=[]
names2=[]
names3=[]
names4=[]
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
	name = name.replace('(', '')
	name = name.replace(')', '')
	name = name.replace('[', '')
	name = name.replace(']', '')
	name = name.replace('\'', '')
	name = name.replace('\"', '')
	name = name.replace('â', 'a')
	name = name.replace('±','')
	name = name.replace('+','')
	name = name.replace('.','')
	name = name.replace(';','')
	name = name.replace(':','')
	name = name.lower()

	new_name=formula+'_'+name
	names1.append(new_name)

	#print(' double criticalvalues_{}_{} []={{ {},{},{},{},{},{},{},{} }};'.format(name,formula, MW, Tb, Tc, Pc, Vc, RHOC, ZC, omega))
file1 = open('viscosity.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
#centipoise
# log10(μ liq) = A + B/T + C*T + D*T^2
# A,B,C,D,Tmin,Tmax,T,vis@T
for i in Lines:
	x=i.split(" ")
	a=1+len(x)-12+1
	x[1 : a]=[''.join(x[1 : a])]
	x[-1]=x[-1].split('\n')[0]
	x.pop(3)
	name, formula, A, B, C, D, Tmin, Tmax, T, vis_at_T=x[1],x[2],x[3],x[4],x[5],x[6],x[7],x[8],x[9],x[10]
	name = name.replace(',', '')
	name = name.replace('-', '')
	name = name.replace('(', '')
	name = name.replace(')', '')
	name = name.replace('[', '')
	name = name.replace(']', '')
	name = name.replace('\'', '')
	name = name.replace('\"', '')
	name = name.replace('â', 'a')
	name = name.replace('±','')
	name = name.replace('+','')
	name = name.replace('.','')
	name = name.replace(';','')
	name = name.replace(':','')
	name = name.lower()
	new_name = formula + '_' + name
	names2.append(new_name)

file1 = open('hvap_inorganic.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
#kJ/mol
# A*((1 - T/Tc)^n)
# A, Tc, n, Tmin, Tmax, T, Hvap@T
for i in Lines:
	x=i.split(" ")
	a=2+len(x)-12+1
	x[2 : a]=[''.join(x[2 : a])]
	x[-1]=x[-1].split('\n')[0]
	x.pop(9)
	x.pop(3)
	formula, name, A, Tc, n, Tmin, Tmax, T, hvap_at_T = x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9]
	name = name.replace(',', '')
	name = name.replace('-', '')
	name = name.replace('(', '')
	name = name.replace(')', '')
	name = name.replace('[', '')
	name = name.replace(']', '')
	name = name.replace('\'', '')
	name = name.replace('\"', '')
	name = name.replace('â', 'a')
	name = name.replace('±','')
	name = name.replace('+','')
	name = name.replace('.','')
	name = name.replace(';','')
	name = name.replace(':','')
	name = name.lower()
	new_name = formula + '_' + name
	names3.append(new_name)
	# print(' double hvap_{}_{} []={{ {},{},{},{},{},{},{} }};'.format(formula,name, A, Tc, n, Tmin, Tmax, T, hvap_at_T))

file1 = open('density_inorganic.txt', 'r')
Lines = file1.readlines()
count = 0
ar=[]
#g/ml
# A*B^( - (1 - T/C)^n)
# A, B, C, n, Tmin, Tmax, T, density@T
for i in Lines:
  x=i.split(" ")
  a=2+len(x)-13+1
  x[2 : a]=[''.join(x[2 : a])]
  x[-1]=x[-1].split('\n')[0]
  x.pop(10)
  x.pop(3)
  formula, name, A, B, C, n, Tmin, Tmax, T, ro_at_T = x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10]
  name = name.replace(',', '')
  name = name.replace('-', '')
  name = name.replace('(', '')
  name = name.replace(')', '')
  name = name.replace('[', '')
  name = name.replace(']', '')
  name = name.replace('\'', '')
  name = name.replace('\"', '')
  name = name.replace('â', 'a')
  name = name.replace('±', '')
  name = name.replace('+', '')
  name = name.replace('.', '')
  name = name.replace(';', '')
  name = name.replace(':', '')
  name = name.lower()
  new_name = formula + '_' + name
  names4.append(new_name)

name_array= names1+names2+names3+names4
name_array.sort()
print(len(name_array))

name_array=list(dict.fromkeys(name_array))
print(len(name_array))



# set_dif=set(names1)-set(names2)
# list_dif=list(set_dif)
#
# set_dif2=set(names2)-set(names1)
# list_dif2=list(set_dif2)
# list_dif2.sort()
# for i in list_dif2:
# 	print(i)
#
# print(len(names1), "  ",len(names2) ," " , len(list_dif)," ",len(list_dif2))
isim_listesi=''
line_length=0
for sıra,isim in enumerate(name_array,1):
	line_length += len(isim)
	if (line_length >= 100) :
		isim_listesi += ('\"'+isim+'\"'+',\n')
		line_length=0
	else:
		isim_listesi += ('\"'+isim+'\"'+ ",")
print(isim_listesi)
