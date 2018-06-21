import community as cm
import networkx as nx
import matplotlib.pyplot as plt


A = nx.read_weighted_edgelist("community1.net", nodetype=int)
pa = nx.pagerank(A)
a = open('pg1.txt', 'w')
a.write("%s\n" % pa)

B = nx.read_weighted_edgelist("community2.net", nodetype=int)
pb = nx.pagerank(B)
b = open('pg2.txt', 'w')
b.write("%s\n" % pb)

C = nx.read_weighted_edgelist("community3.net", nodetype=int)
pc = nx.pagerank(C)
c = open('pg3.txt', 'w')
c.write("%s\n" % pc)

D = nx.read_weighted_edgelist("community4.net", nodetype=int)
pd = nx.pagerank(D)
d = open('pg4.txt', 'w')
d.write("%s\n" % pd)

E = nx.read_weighted_edgelist("community98.net", nodetype=int)
pe = nx.pagerank(E)
e = open('pg98.txt', 'w')
e.write("%s\n" % pe)

F = nx.read_weighted_edgelist("community5.net", nodetype=int)
pf = nx.pagerank(F)
f = open('pg5.txt', 'w')
f.write("%s\n" % pf)

G = nx.read_weighted_edgelist("community6.net", nodetype=int)
pg = nx.pagerank(G)
g = open('pg6.txt', 'w')
g.write("%s\n" % pg)

H = nx.read_weighted_edgelist("community50.net", nodetype=int)
ph = nx.pagerank(H)
h = open('pg50.txt', 'w')
h.write("%s\n" % ph)

I = nx.read_weighted_edgelist("community8.net", nodetype=int)
pi = nx.pagerank(I)
i = open('pg8.txt', 'w')
i.write("%s\n" % pi)

J = nx.read_weighted_edgelist("community9.net", nodetype=int)
pj = nx.pagerank(J)
j = open('pg9.txt', 'w')
j.write("%s\n" % pj)

K = nx.read_weighted_edgelist("community10.net", nodetype=int)
pk = nx.pagerank(K)
k = open('pg10.txt', 'w')
k.write("%s\n" % pk)

L = nx.read_weighted_edgelist("community11.net", nodetype=int)
pl = nx.pagerank(L)
l = open('pg11.txt', 'w')
l.write("%s\n" % pl)

M = nx.read_weighted_edgelist("community12.net", nodetype=int)
pm = nx.pagerank(M)
m = open('pg12.txt', 'w')
m.write("%s\n" % pm)

N = nx.read_weighted_edgelist("community27.net", nodetype=int)
pn = nx.pagerank(N)
n = open('pg27.txt', 'w')
n.write("%s\n" % pn)

O = nx.read_weighted_edgelist("community98.net", nodetype=int)
po = nx.pagerank(O)
o = open('pg98.txt', 'w')
o.write("%s\n" % po)

P = nx.read_weighted_edgelist("community353.net", nodetype=int)
pp = nx.pagerank(P)
p = open('pg353.txt', 'w')
p.write("%s\n" % pp)

Q = nx.read_weighted_edgelist("community16.net", nodetype=int)
pq = nx.pagerank(Q)
q = open('pg16.txt', 'w')
q.write("%s\n" % pq)

R = nx.read_weighted_edgelist("community17.net", nodetype=int)
pr = nx.pagerank(R)
r = open('pg17.txt', 'w')
r.write("%s\n" % pr)

S = nx.read_weighted_edgelist("community3273.net", nodetype=int)
ps = nx.pagerank(S)
s = open('pg3273.txt', 'w')
s.write("%s\n" % ps)

T = nx.read_weighted_edgelist("community19.net", nodetype=int)
pt = nx.pagerank(T)
t = open('pg19.txt', 'w')
t.write("%s\n" % pt)

U = nx.read_weighted_edgelist("community20.net", nodetype=int)
pu = nx.pagerank(U)
u = open('pg20.txt', 'w')
u.write("%s\n" % pu)

V = nx.read_weighted_edgelist("community21.net", nodetype=int)
pv = nx.pagerank(V)
v = open('pg21.txt', 'w')
v.write("%s\n" % pv)

W = nx.read_weighted_edgelist("community4546.net", nodetype=int)
pw = nx.pagerank(W)
w = open('pg4546.txt', 'w')
w.write("%s\n" % pw)

X = nx.read_weighted_edgelist("community23.net", nodetype=int)
px = nx.pagerank(X)
x = open('pg23.txt', 'w')
x.write("%s\n" % px)

Y = nx.read_weighted_edgelist("community24.net", nodetype=int)
py = nx.pagerank(Y)
y = open('pg24.txt', 'w')
y.write("%s\n" % py)

Z = nx.read_weighted_edgelist("community4595.net", nodetype=int)
pz = nx.pagerank(Z)
z = open('pg4595.txt', 'w')
z.write("%s\n" % pz)

AA = nx.read_weighted_edgelist("community4680.net", nodetype=int)
paa = nx.pagerank(AA)
aa = open('pg4680.txt', 'w')
aa.write("%s\n" % paa)

BB = nx.read_weighted_edgelist("community5246.net", nodetype=int)
pbb = nx.pagerank(BB)
bb = open('pg5246.txt', 'w')
bb.write("%s\n" % pbb)

CC = nx.read_weighted_edgelist("community7306.net", nodetype=int)
pcc = nx.pagerank(CC)
cc = open('pg7306.txt', 'w')
cc.write("%s\n" % pcc)

DD = nx.read_weighted_edgelist("community8243.net", nodetype=int)
pdd = nx.pagerank(DD)
dd = open('pg8243.txt', 'w')
dd.write("%s\n" % pdd)



