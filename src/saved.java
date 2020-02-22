/*
				Node placeholder = leftNode;
				boolean borrowed = false;
				int columnsOver = 1;

				while (!borrowed)
				{
					if (leftNode.next.data==0)
					{
						leftNode = leftNode.next;
						columnsOver++;
					}
					else
					{
						leftNode.next.data = leftNode.next.data-1;
						leftNode = leftNode.prev;
						borrowed = true;
					}
				}

				while (leftNode != placeholder)
				{
					leftNode.data = (leftNode.data + 9);
					leftNode = leftNode.prev;
				}

				result.add(leftNode.data-rightNode.data);

				leftNode = leftNode.next;
				rightNode = rightNode.next;
				*/