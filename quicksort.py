import random

def quicksort(lst):
	if len(lst) <= 1:
		return lst

	pivot = lst[0]
	equal = []
	larger = []
	smaller = []

	for x in lst:
		if x > pivot:
			larger.append(x)
		elif x < pivot:
			smaller.append(x)
		else:
			equal.append(x)

	return quicksort(smaller) + equal + quicksort(larger)

def test_quick_sort():
	l1 = [int(1000*random.random()) for i in range(1000)]
	l2 = [int(1000*random.random()) for i in range(1000)]
	l3 = [int(1000*random.random()) for i in range(1000)]
	l4 = [int(1000*random.random()) for i in range(1000)]
	l5 = [int(1000*random.random()) for i in range(1000)]

	assert quicksort(l1) == sorted(l1)
	assert quicksort(l2) == sorted(l2)
	assert quicksort(l3) == sorted(l3)
	assert quicksort(l4) == sorted(l4)
	assert quicksort(l5) == sorted(l5)

	print("All tests passed.")

if __name__ == '__main__':
	test_quick_sort()