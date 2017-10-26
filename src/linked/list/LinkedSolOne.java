package linked.list;

import java.util.HashSet;
import java.util.Stack;

public class LinkedSolOne {

	class Index {
		int value = 0;
	}

	public void deleteDuplicates(LinkedListNode n) {
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedListNode prev = n;
		while(n != null) {
			if(set.contains(n.data)) {
				prev.next = n.next;
			} else {
				set.add(n.data);
			}
			prev = n;
			n = n.next;
		}
	}

	public void checkDuplicatesNoBuffer(LinkedListNode n) {
		LinkedListNode current = n;
		while(current != null) {
			LinkedListNode runner = current;
			while(runner.next != null) {
				if(current.data == runner.next.data) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}

	public int printKthToLast(LinkedListNode head, int k) {

		if(head == null) {
			return -1;
		}
		int count = printKthToLast(head.next, k) + 1;

		if(count == k) {
			System.out.println(head.data);
		}

		return count;
	}

	public LinkedListNode printKthLastNode(LinkedListNode head, int k) {
		Index index = new Index();
		LinkedListNode node = printKthLastNode(head, k, index);
		System.out.println(index.value);
		return node;
	}

	private LinkedListNode printKthLastNode(LinkedListNode head, int k, Index index) {

		if(head == null) {
			return null;
		}
		LinkedListNode node = printKthLastNode(head.next, k, index);
		index.value = index.value + 1;

		if(k == index.value) {
			return head;
		}
		return node;
	}

	public LinkedListNode partition(LinkedListNode head, int x) {
		LinkedListNode beforeStart = null;
		LinkedListNode beforeEnd = null;
		LinkedListNode afterStart = null;
		LinkedListNode afterEnd = null;

		LinkedListNode node = head;
		while(node != null) {
			LinkedListNode next = node.next;
			node.next = null;
			if(node.data < x) {
				if(beforeStart == null) {
					beforeStart = node;
					beforeEnd = beforeStart;
				} else {
					beforeEnd.next = node;
					beforeEnd = node;
				}
			} else {
				if(afterStart == null) {
					afterStart = node;
					afterEnd = afterStart;
				} else {
					afterEnd.next = node;
					afterEnd = node;
				}
			}
			node = next;
		}

		if(beforeStart == null) {
			return afterStart;
		}
		//Merge
		beforeEnd.next = afterStart;
		return beforeStart;
	}

	public LinkedListNode partition2(LinkedListNode node, int x) {
		LinkedListNode list1 = node;
		LinkedListNode list2 = node;
		while(node != null) {
			LinkedListNode next = node.next;
			node.next = null;
			if(node.data < x) {
				node.next = list1;
				list1 = node;
			} else {
				list2.next = node;
				list2 = node;
			}
			node = next;
		}

		list2.next = null;


		return list1;

	}

	public LinkedListNode addReverse(LinkedListNode node1, LinkedListNode node2) {
		int carry = 0;
		LinkedListNode result = null;
		while(node1 != null && node2 != null) {
			int addition = 0;
			String sum = String.valueOf(node1.data + node2.data + carry);
			char[] sums = sum.toCharArray();
			if(sums.length > 1) {
				carry = Character.getNumericValue(sums[0]);
				addition = Character.getNumericValue(sums[1]);
			} else {
				carry = 0;
				addition = Character.getNumericValue(sums[0]);
			}

			LinkedListNode res = new LinkedListNode(addition);
			res.next = result;
			result = res;

			node1 = node1.next;
			node2 = node2.next;
		}

		while(node1 != null) {
			int addition = 0;
			String sum = String.valueOf(node1.data + carry);
			char[] sums = sum.toCharArray();
			if(sums.length > 1) {
				carry = Character.getNumericValue(sums[0]);
				addition = Character.getNumericValue(sums[1]);
			} else {
				carry = 0;
				addition = Character.getNumericValue(sums[0]);
			}

			LinkedListNode res = new LinkedListNode(addition);
			res.next = result;
			result = res;
		}

		while(node2 != null) {
			int addition = 0;
			String sum = String.valueOf(node2.data + carry);
			char[] sums = sum.toCharArray();
			if(sums.length > 1) {
				carry = Character.getNumericValue(sums[0]);
				addition = Character.getNumericValue(sums[1]);
			} else {
				carry = 0;
				addition = Character.getNumericValue(sums[0]);
			}

			LinkedListNode res = new LinkedListNode(addition);
			res.next = result;
			result = res;
		}
		return result;
	}

	public boolean isPalindrome(LinkedListNode head) {
		LinkedListNode fast = head;
		LinkedListNode slow = head;

		Stack<LinkedListNode> stack = new Stack<LinkedListNode>();

		while(fast != null && fast.next != null) {
			stack.push(slow);
			slow = slow.next;
			fast = fast.next.next;
		}

		if(fast != null) {
			slow = slow.next;
		}

		while(slow != null) {
			LinkedListNode top = stack.pop();
			if(top.data != slow.data) {
				return false;
			}
			slow = slow.next;
		}

		return true;
	}

	public boolean detectLoop(LinkedListNode head) {
		if(head.next != null) {
			LinkedListNode fast = head.next;
			LinkedListNode slow = head;

			while(fast != null && fast.next != null) {
				if(slow == fast) {
					return true;
				}
				slow = slow.next;
				fast = fast.next.next;
			}
		}

		return false;
	}
	
	public LinkedListNode mergeTwoLists(LinkedListNode l1, LinkedListNode l2) {
		LinkedListNode result = null;
		LinkedListNode prev = null;
        if(l1 == null)
            result = l2;
        if(l2 == null)
            result = l1;
        while((l1 != null) && (l2 != null)) {
        	LinkedListNode n = null;
            if(l1.data < l2.data) {
                n = new LinkedListNode(l1.data);
                l1 = l1.next;
            } else {
                n = new LinkedListNode(l2.data);
                l2 = l2.next;
            }
            if(result == null) {
                result = n;
                prev = n;
            } else {
                prev.next = n;
                prev = prev.next;
            }
        }
        
        while(l1 != null) {
        	LinkedListNode n = new LinkedListNode(l1.data);
            if(result == null) {
                result = n;
                prev = n;
            } else {
                prev.next = n;
                prev = prev.next;
            }
            l1 = l1.next;
        }
        
        while(l2 != null) {
        	LinkedListNode n = new LinkedListNode(l2.data);
            if(result == null) {
                result = n;
                prev = n;
            } else {
                prev.next = n;
                prev = prev.next;
            }
            l2 = l2.next;
        }
        
        return result;
    }
}
