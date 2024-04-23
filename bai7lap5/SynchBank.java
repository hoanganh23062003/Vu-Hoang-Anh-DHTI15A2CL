/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bai7lap5;
/**
 *
 * @author admin
 */
public class SynchBank {
    public static void main(String[] args) {
        Bank bank = new Bank(100, 1000); // Create a bank with 100 accounts, each initialized with a balance of 1000
        int numThreads = bank.size(); // Number of accounts, each represented by a thread

        // Create and start a thread for each account
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            TransferMoney transferMoney = new TransferMoney(bank, i, 1000);
            threads[i] = new Thread(transferMoney);
            threads[i].start();
        }

        // Wait for all threads to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException ex) {
            System.err.println("Main thread interrupted while waiting for transfer threads to complete.");
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }

        System.out.println("All transfer threads have completed.");
    }
}
