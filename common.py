def read_records(name):
	f = open(name,"r")
	lines = [l.strip("\n") for l in f][1:]
	f.close()
	
	fu = []
	fw = []
	nrec = lines[0].split().__len__()//2
	for i in range(nrec):
		fu.append([])
		fw.append([])
	for l in lines:
		l = l.split()
		for i in range(nrec):
			fu[i].append(l[2*i])
			fw[i].append(l[2*i+1])
	
	return fu,fw
