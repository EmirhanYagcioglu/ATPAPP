package serialcomm;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static final int STANDARD_DATA_PACKET_SIZE = 4;
    public static byte[] a;

    static public void main(String[] args) {
        SerialPort comPort = findKL25z();
        if (comPort == null) {
            throw new RuntimeException("KL25z connection cannot be made: No such port!");
        }
        System.out.print("Opening connection... ");
        comPort.openPort();
        comPort.setBaudRate(115200);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
        System.out.println("Port open");

        a = new byte[STANDARD_DATA_PACKET_SIZE];
        for (int i = 0; i < STANDARD_DATA_PACKET_SIZE; i++) {
            a[i] = 48;
        }
        Scanner scanner = new Scanner(System.in);
        int scanned = 0;

        System.out.println("Begin comms...");
        while (true) {
            try {
                System.out.print("Sending command...");
                scanned = getScannedInt(scanner);
                a[0] = (byte) (scanned + 48);
                comPort.writeBytes(a, STANDARD_DATA_PACKET_SIZE);
                System.out.println("sent " + Arrays.toString(a));

                System.out.print("Waiting for echo... ");
                byte[] readBuffer = new byte[STANDARD_DATA_PACKET_SIZE];
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                System.out.print("Read " + numRead + " bytes: ");
                for (int i = 0; i < STANDARD_DATA_PACKET_SIZE; i++){
                    System.out.print(readBuffer[i] + " ");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static SerialPort findKL25z() {
        SerialPort comPort = null;
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            if (port.getDescriptivePortName().contains("OpenSDA")) {
                comPort = port;
                break;
            }
        }
        if (comPort == null) {
            System.out.println("No such port!");
            return null;
        }
        System.out.println("Found suitable port at " + comPort.getSystemPortName() + ": " + comPort.getDescriptivePortName());
        return comPort;
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