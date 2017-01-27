from uf import SuperQuickUnionUF
import random

def mc_simulation_for_solving_percolation(N):
	"""
	The function takes the matrix size of N and return the ratio of open sites to size of matrix (N x N)
	(int) -> int
	"""
	# Initialize UF object
	uf = SuperQuickUnionUF(N * N + 2)
	site_status_list = [True] * 2 + [False] * N * N
	
	# Top N sites indices
	top_n_indices = list(range(2, 2 + N))
	# Bottom N sites indices
	butt_n_indices = list(range(N * N + 2 - N, N * N + 2))

	# Sanity check
	assert len(site_status_list) == 2 + N * N
	assert len(top_n_indices) == N
	assert len(butt_n_indices) == N

	mc_simulation_set = set(range(N * N))

	while site_status_list != [True] * (N * N + 2):
		# Randomly select a site to open
		index = random.choice(tuple(mc_simulation_set))
		mc_simulation_set.discard(index)
		
		index += 2
		site_status_list[index] = True

		# Check vinicity sites and connect the open ones
		x, y = coord_convert_1D_to_2D(index, N)
		# x_coords = [x, x - 1, x + 1]
		# y_coords = [y, y - 1, y + 1]

		# Get vicinity coordinates
		vicinity_coords = [(x, y - 1), (x, y + 1), (x - 1, y), (x + 1, y)]
		# vicinity_coords = [(i, j) for i in x_coords for j in y_coords]
		# vicinity_coords.remove((x, y))

		while vicinity_coords:
			i, j = vicinity_coords.pop()
			if N > i >= 0 and N > j >= 0:
				v_index = coord_convert_2D_to_1D(i, j, N)

				if site_status_list[v_index]:
					uf.union(index, v_index)

		# Check top/bottom conditions
		if index in top_n_indices:
			uf.union(index, 0)
		if index in butt_n_indices:
			uf.union(index, 1)

		# Check percolation
		if uf.connected(0, 1):
			# print(site_status_list)
			return (sum(site_status_list) - 2)/ (N * N)


def coord_convert_2D_to_1D(x, y, N):
	"""
	Convert the coordinate of a point of an N x N matrix to the corresponding 1d coordinate.
	(int, int, int) -> int
	"""
	return x * N + y + 2 # with two virtual sites at the beginning

def coord_convert_1D_to_2D(index, N):
	"""
	Convert the coordinate of a point of an list to the corresponding coordinate in a N x N matrix.
	(int, int) -> int, int
	"""
	return (index - 2) // N, (index - 2) % N

def main():
	counter = 0
	sum_rate = 0
	num_iter = 1000
	for i in range(num_iter):
		sum_rate += mc_simulation_for_solving_percolation(100)
		counter += 1
		print("Progress: {}%".format((counter / num_iter) * 100))

	print(sum_rate / counter)

def test_coord_convert_2D_to_1D():
	assert coord_convert_2D_to_1D(1, 2, 3) == 7
	assert coord_convert_2D_to_1D(4, 4, 5) == 26

def test_coord_convert_1D_to_2D():
	assert coord_convert_1D_to_2D(7, 3) == (1, 2)
	assert coord_convert_1D_to_2D(26, 5) == (4, 4)

if __name__ == '__main__':
	test_coord_convert_2D_to_1D()
	test_coord_convert_1D_to_2D()

	main()
