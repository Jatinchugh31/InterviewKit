package src.dataStructure.LinkList;

import java.util.Scanner;

public class QueueUsingLinkList {
    static Node front = null, rear = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("press 1 to insert or 2 to Pop and 3 for print and 4 for end");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    int i = sc.nextInt();
                    addInQueue(i);
                    break;
                }
                case 2: {
                    int i = removeFromQueue();
                    System.out.println("pop element " + i);
                    break;
                }
                case 3: {
                    printList();
                    break;
                }
                case 4: {
                    running = false;
                    break;
                }
            }
        }
    }

    private static void printList() {
        Node temp = front;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    private static int removeFromQueue() {
        if (front == null) {
            return -1;
        }
        int value = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return value;
    }

    private static void addInQueue(int i) {
        Node newNode = new Node(i);
        if (rear == null) {
            front = rear = newNode;
        }
        rear.next = newNode;
        rear = newNode;
    }


}
