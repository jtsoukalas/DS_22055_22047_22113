package gr.hua.dit.ds.circularQueue;


public class App {

    /**
     * This code is part of the lab exercises for the Data Structures course at Harokopio
     * University of Athens, Dept. of Informatics and Telematics.
     */
    public static void main(String args[]) {

        Queue<Integer> q = new CircularQueue<>();

        for(int i = 0; i < 100; i++) {
            System.out.println("Adding element " + i + " to queue");
            q.push(i);
        }

        while(!q.isEmpty()) {
            System.out.println("Next element served from queue: " + q.pop());
        }
    }
}

