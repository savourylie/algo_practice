import fileinput
import sys

class UF(object):
	def __init__(self, integer):
		"""
		Initialize union-find data structure with N objects (0 to N - 1)
		"""
		raise NotImplementedError

	def union(self, p, q):
		"""
		Add connection between p and q
		(int, int) -> None
		"""
		raise NotImplementedError

	def connected(self, p, q):
		"""
		Are p and q in the same component?
		(int, int) -> boolean
		"""
		raise NotImplementedError

class QuickFindUF(UF):
	def __init__(self, integer):
		self.id = list(range(int(integer)))

	def connected(self, p, q):
		return self.id[p] == self.id[q]

	def union(self, p, q):
		pid = self.id[p]
		qid = self.id[q]

		for i in self.id:
			if self.id[i] == pid:
				self.id[i] = self.id[qid]

class QuickUnionUF(UF):
	def __init__(self, integer):
		QuickFindUF.__init__(self, integer)

	def __root(self, i):
		while i != self.id[i]:
			i = self.id[i]

		return i

	def connected(self, p, q):
		return self.__root(p) == self.__root(q)

	def union(self, p, q):
		p_root = self.__root(p)
		q_root = self.__root(q)
		
		if p_root == q_root:
			return None

		self.id[p_root] = q_root

class SuperQuickUnionUF(UF):
	def __init__(self, integer):
		QuickFindUF.__init__(self, integer)
		self.size = [1] * int(integer)

	def __root(self, i):
		while i != self.id[i]:
			self.id[i] = self.id[self.id[i]]
			i = self.id[i]

		return i

	def connected(self, p, q):
		return self.__root(p) == self.__root(q)

	def union(self, p, q):
		p_root = self.__root(p)
		q_root = self.__root(q)
		
		if p_root == q_root:
			return

		if self.size[p_root] > self.size[q_root]:
			self.id[q_root] = p_root
			self.size[p_root] += self.size[q_root]

		else:
			self.id[p_root] = q_root
			self.size[q_root] += self.size[p_root]

def test_super_quick_uf():
	# with open(sys.argv[1]) as f:
	# 	N = f.readline()

	# 	# Test QuickFindUF
	# 	qfuf = QuickFindUF(N)
		
	# 	for line in f:
	# 		p, q = int(line[0]), int(line[2])
			
	# 		if not qfuf.connected(p, q):
	# 			qfuf.union(p, q)
	# 			print(str(p) + " " + str(q))

	# with open(sys.argv[1]) as f:
	# 	N = f.readline()

	# 	# Test QuickUnionUF
	# 	quuf = QuickUnionUF(N)
		
	# 	for line in f:
	# 		p, q = int(line[0]), int(line[2])
			
	# 		if not quuf.connected(p, q):
	# 			quuf.union(p, q)
	# 			print(str(p) + " " + str(q))

	with open(sys.argv[1]) as f:
		N = f.readline()

		# Test SuperQuickUnionUF
		squuf = SuperQuickUnionUF(N)
		
		for line in f:
			p, q = int(line[0]), int(line[2])
			
			if not squuf.connected(p, q):
				squuf.union(p, q)
				print('Connecting ' + str(p) + " and " + str(q))

			else:
				print(str(p) + ' and ' + str(q) + ' already connected.')


		assert squuf.connected(0, 5) == True
		assert squuf.connected(0, 2) == False
		assert squuf.connected(5, 8) == False
		assert squuf.connected(4, 8) == True
		assert squuf.connected(5, 7) == False
		assert squuf.connected(5, 6) == True

		print("All tests passed.")

if __name__ == '__main__':
	test_super_quick_uf()