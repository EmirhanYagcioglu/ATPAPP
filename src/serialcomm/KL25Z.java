package serialcomm;

import com.fazecast.jSerialComm.SerialPort;
import serialcomm.util.CharUtil;
import serialcomm.util.Log;

import java.util.Arrays;

public class KL25Z {
    private final SerialPort port;

    public KL25Z() {
        this.port = find_KL25z();
    }

    public SerialPort get_port() {
        return port;
    }

    public int read_bytes(byte[] read_buffer, int buffer_length) {
        return this.port.readBytes(read_buffer, buffer_length);
    }

    public void open_port() {
        Log.d("SerialComm", "Open port request");
        this.port.openPort();
        this.port.setBaudRate(115200);
        this.port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
        Log.d("SerialComm", "Port connection configured to 115200 baud rate & wait for read");
    }

    private SerialPort find_KL25z() {
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
            throw new RuntimeException("KL25z connection cannot be made: No such port could be found!");
        }
        System.out.println("Found suitable port at " + comPort.getSystemPortName() + ": " + comPort.getDescriptivePortName());
        return comPort;
    }

    public void send_command(byte[] command) {
        this.port.writeBytes(command, 4);
    }

    public byte[] send_command(String command) {
        byte[] real_command = string_command_interpreter(command);
        if (real_command == null) {
            return null;
        }
        this.port.writeBytes(real_command, 4);
        Log.d("SerialComm", "Command sent: " + Arrays.toString(real_command) + ". Now waiting for echo");
        return real_command;
    }

    private byte[] string_command_interpreter(String command) {
        String[] arrOfStr = command.split(":");
        if (arrOfStr.length != 4) {
            return null;
        }
        byte[] real_command = new byte[4];
        for (int i = 0; i < 4; i++) {
            real_command[i] = CharUtil.hexString_to_byte(arrOfStr[i]);
        }
        System.out.println(Arrays.toString(real_command));
        return real_command;
    }
}
