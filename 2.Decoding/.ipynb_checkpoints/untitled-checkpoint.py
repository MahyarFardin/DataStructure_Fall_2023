code = "()a(b((cd)))e(()f)"
stack = []
queue = []
k = 3

for character in code:
    if character == "(":
        stack.append(character)
    elif character == ")":
        stack.pop()
    else:
        queue.append(character)

if len(stack) != 0:
    print("False pass")
    exit()

for i in range(k):
    stack.append(queue.pop(0))
    
while len(stack) > 0:
    queue.append(stack.pop())
    
for i in range(len(queue) - k):
    queue.append(queue.pop(0))

print(queue)