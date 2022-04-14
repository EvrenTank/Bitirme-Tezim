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
  a=2+len(x)-13+1
  x[2 : a]=[''.join(x[2 : a])]
  x[-1]=x[-1].split('\n')[0]
  x.pop(10)
  x.pop(3)
  formula, name, A, B, C, n, Tmin, Tmax, T, ro_at_T = x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10]
  name = name.replace(',', '')
  name = name.replace('-', '')
  print(' double ro_{}_{} []={{ {},{},{},{},{},{},{},{} }};'.format(name, formula, A, B, C, n, Tmin, Tmax, T, ro_at_T))

