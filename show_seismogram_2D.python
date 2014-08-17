import matplotlib.pyplot as plt
import os

import common

def add_to_plot(misfit,color):	
	x = range(misfit.__len__())
	plt.plot(x, misfit, color)
	
def plot(seismo):	
	plt.title('seismogram '+seismo)
	plt.xlabel('time')
	plt.ylabel('source time function')

	fn = seismo[:-4].split('\\')
	fname = fn[3]+'_'+fn[1]
	print(fname)
	
	fu,fw = common.read_fadj(seismo)
	
	nrec = fu.__len__()
	for i in range(nrec):
		add_to_plot(fu[i],'r-')
		add_to_plot(fw[i],'b-')
		
	plt.savefig("pic_records/"+fname, bbox_inches='tight')
	plt.clf()


fn = []
for root, dirs, files in os.walk(".\\", topdown=False):
	for name in files:
		if name.find('record')>-1 and name.find('txt')>-1:
			fn.append(os.path.join(root, name))

print(seismos)
for s in seismos:
	plot(s)
