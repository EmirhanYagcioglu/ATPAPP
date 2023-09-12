package serialcomm;

import com.fazecast.jSerialComm.SerialPort;
import serialcomm.util.Log;

import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static final int STANDARD_DATA_PACKET_SIZE = 4;

    static public void main(String[] args) {
        KL25Z kl25Z = new KL25Z();
        kl25Z.open_port();
        Scanner scanner = new Scanner(System.in);
        Log.d("SerialComm", "Beginning of communication with KL25Z on " + kl25Z.get_port().getSystemPortName());
        while (true) {
            try {
                Log.d("SerialComm", "Waiting for command. Enter command:");
                String scanned = scanner.nextLine();
                byte[] interpreted_command = kl25Z.send_command(scanned);
                if (interpreted_command != null) {
                    byte[] readBuffer = new byte[STANDARD_DATA_PACKET_SIZE];
                    int numRead = kl25Z.read_bytes(readBuffer, readBuffer.length);
                    Log.d("SerialComm", "Read " + numRead + " bytes: " + Arrays.toString(readBuffer));
                } else {
                    Log.d("SerialComm", "Transmit failure! Interpreted command " + Arrays.toString(interpreted_command) + " returned a transmission error");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int getScannedInt(Scanner scanner) {
        int scanned;
        do {
            System.out.println("Please enter command: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid Comm");
                scanner.next();
            }
            scanned = scanner.nextInt();
        } while (scanned < 0 || scanned > 9);
        return scanned;
    }
}