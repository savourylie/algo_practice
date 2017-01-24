class Node(object):
	def __init__(self, value):
		self.value = value
		self.left = None
		self.right = None

class BinaryTree(object):
	def __init__(self, root):
		self.root = root

	def search(self, find_val):
		 """Return True if the values in the tree, return False otherwise."""

		 node_stack = [root]

		 while len(node_stack):
		 	current_node = node_stack.pop()

			if current_node.value == find_val:
			 	return True

			if current_node.left:
				node_stack.append(current_node.left)

			if current_node.right:
				node_stack.append(current_node.right)

		return False

	def print_tree(self):
		return False

	def preorder_search(self, start, find_val):
		return False

	def preorder_print(self, start, traversal):
		return traversal

class BST(object):
    def __init__(self, root):
        self.root = Node(root)

    def insert(self, new_val):
        current_node = self.root
        
        while True:
            if new_val > current_node.value:
                if current_node.right:
                    current_node = current_node.right
                else:
                    current_node.right = Node(new_val)
                    break
                
            else:
                if current_node.left:
                    current_node = current_node.left
                else:
                    current_node.left = Node(new_val)
                    break
                    

    def search(self, find_val):
        current_node = self.root
        
        while True:
            if find_val > current_node.value:
                if current_node.right:
                    current_node = current_node.right
                else:
                    return False
            elif find_val < current_node.value:
                if current_node.left:
                    current_node = current_node.left
                else:
                    return False
            else:
                return True