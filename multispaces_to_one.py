fin = open("raw.txt", "rt",encoding="utf-8")
fout = open("cp_inorganic.txt", "wt")
lines=fin.readlines()
for line in lines:
    line=' '.join(line.split())
    #fout.write(' '.join(line.split()))
    fout.write(line)
    fout.write('\n')

fin.close()
fout.close()